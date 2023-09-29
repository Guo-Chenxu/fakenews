package com.guochenxu.schedule.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guochenxu.common.constants.ScheduleConstants;
import com.guochenxu.common.redis.CacheService;
import com.guochenxu.model.schedule.dto.Task;
import com.guochenxu.model.schedule.entity.Taskinfo;
import com.guochenxu.model.schedule.entity.TaskinfoLogs;
import com.guochenxu.schedule.mapper.TaskinfoLogsMapper;
import com.guochenxu.schedule.mapper.TaskinfoMapper;
import com.guochenxu.schedule.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.rocksdb.Cache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author: 郭晨旭
 * @create: 2023-09-29 17:10
 * @version: 1.0
 */

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskinfoMapper taskinfoMapper;

    @Resource
    private TaskinfoLogsMapper taskinfoLogsMapper;

    @Resource
    private CacheService cacheService;

    @Override
    @Transactional
    public long addTask(Task task) throws InvocationTargetException, IllegalAccessException {
        boolean success = addTaskToDb(task);

        if (success) {
            addTaskToCache(task);
        }

        return task.getTaskId();
    }

    private void addTaskToCache(Task task) {

        String key = task.getTaskType() + "_" + task.getPriority();

        //获取5分钟之后的时间  毫秒值
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        long nextScheduleTime = calendar.getTimeInMillis();

        //2.1 如果任务的执行时间小于等于当前时间，存入list
        if (task.getExecuteTime() <= System.currentTimeMillis()) {
            cacheService.lLeftPush(ScheduleConstants.TOPIC + key, JSON.toJSONString(task));
        } else if (task.getExecuteTime() <= nextScheduleTime) {
            //2.2 如果任务的执行时间大于当前时间 && 小于等于预设时间（未来5分钟） 存入zset中
            cacheService.zAdd(ScheduleConstants.FUTURE + key, JSON.toJSONString(task), task.getExecuteTime());
        }
    }

    private boolean addTaskToDb(Task task) throws InvocationTargetException, IllegalAccessException {
        Taskinfo taskinfo = new Taskinfo();
        BeanUtils.copyProperties(taskinfo, task);
        taskinfo.setExecuteTime(new Date(task.getExecuteTime()));
        taskinfoMapper.insert(taskinfo);

        TaskinfoLogs taskinfoLogs = new TaskinfoLogs();
        BeanUtils.copyProperties(taskinfoLogs, taskinfo);
        taskinfoLogs.setVersion(1);
        taskinfoLogs.setStatus(ScheduleConstants.SCHEDULED);
        taskinfoLogsMapper.insert(taskinfoLogs);

        return true;
    }

    /**
     * 取消任务
     *
     * @param taskId
     * @return
     */
    @Override
    public boolean cancelTask(long taskId) {

        boolean flag = false;

        //删除任务，更新日志
        Task task = updateDb(taskId, ScheduleConstants.EXECUTED);

        //删除redis的数据
        if (task != null) {
            removeTaskFromCache(task);
            flag = true;
        }


        return flag;
    }

    /**
     * 删除redis中的任务数据
     *
     * @param task
     */
    private void removeTaskFromCache(Task task) {

        String key = task.getTaskType() + "_" + task.getPriority();

        if (task.getExecuteTime() <= System.currentTimeMillis()) {
            cacheService.lRemove(ScheduleConstants.TOPIC + key, 0, JSON.toJSONString(task));
        } else {
            cacheService.zRemove(ScheduleConstants.FUTURE + key, JSON.toJSONString(task));
        }
    }

    /**
     * 删除任务，更新任务日志状态
     *
     * @param taskId
     * @param status
     * @return
     */
    private Task updateDb(long taskId, int status) {
        Task task = null;
        try {
            //删除任务
            taskinfoMapper.deleteById(taskId);

            TaskinfoLogs taskinfoLogs = taskinfoLogsMapper.selectById(taskId);
            taskinfoLogs.setStatus(status);
            taskinfoLogsMapper.updateById(taskinfoLogs);

            task = new Task();
            BeanUtils.copyProperties(taskinfoLogs, task);
            task.setExecuteTime(taskinfoLogs.getExecuteTime().getTime());
        } catch (Exception e) {
            log.error("task cancel exception taskid={}", taskId);
        }

        return task;
    }

    /**
     * 按照类型和优先级拉取任务
     *
     * @return
     */
    @Override
    public Task poll(int type, int priority) {
        Task task = null;
        try {
            String key = type + "_" + priority;
            String task_json = cacheService.lRightPop(ScheduleConstants.TOPIC + key);
            if (StringUtils.isNotBlank(task_json)) {
                task = JSON.parseObject(task_json, Task.class);
                //更新数据库信息
                updateDb(task.getTaskId(), ScheduleConstants.EXECUTED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("poll task exception");
        }

        return task;
    }

    /**
     * 定时刷新
     * 每分钟刷新一次
     */
    /**
     * 未来数据定时刷新
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void refresh() {
        String token = cacheService.tryLock("FUTURE_TASK_SYNC", 1000 * 30);
        if (StringUtils.isNotBlank(token)) {
            log.info("未来数据定时刷新---定时任务");

            //获取所有未来数据的集合key
            Set<String> futureKeys = cacheService.scan(ScheduleConstants.FUTURE + "*");
            for (String futureKey : futureKeys) {//future_100_50

                //获取当前数据的key  topic
                String topicKey = ScheduleConstants.TOPIC + futureKey.split(ScheduleConstants.FUTURE)[1];

                //按照key和分值查询符合条件的数据
                Set<String> tasks = cacheService.zRangeByScore(futureKey, 0, System.currentTimeMillis());

                //同步数据
                if (!tasks.isEmpty()) {
                    cacheService.refreshWithPipeline(futureKey, topicKey, tasks);
                    log.info("成功的将" + futureKey + "刷新到了" + topicKey);
                }
            }
        }
    }

    @Scheduled(cron = "0 */5 * * * ?")
    @PostConstruct
    public void reloadData() throws InvocationTargetException, IllegalAccessException {
        clearCache();
        log.info("数据库数据同步到缓存");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);

        //查看小于未来5分钟的所有任务
        List<Taskinfo> allTasks = taskinfoMapper.selectList(Wrappers.<Taskinfo>lambdaQuery().lt(Taskinfo::getExecuteTime, calendar.getTime()));
        if (allTasks != null && allTasks.size() > 0) {
            for (Taskinfo taskinfo : allTasks) {
                Task task = new Task();
                BeanUtils.copyProperties(taskinfo, task);
                task.setExecuteTime(taskinfo.getExecuteTime().getTime());
                addTaskToCache(task);
            }
        }
    }

    private void clearCache() {
        // 删除缓存中未来数据集合和当前消费者队列的所有key
        Set<String> futurekeys = cacheService.scan(ScheduleConstants.FUTURE + "*");// future_
        Set<String> topickeys = cacheService.scan(ScheduleConstants.TOPIC + "*");// topic_
        cacheService.delete(futurekeys);
        cacheService.delete(topickeys);
    }
}
