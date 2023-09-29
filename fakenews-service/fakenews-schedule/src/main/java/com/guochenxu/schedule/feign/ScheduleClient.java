package com.guochenxu.schedule.feign;

import com.guochenxu.apis.schedule.IScheduleClient;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.schedule.dto.Task;
import com.guochenxu.schedule.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: 郭晨旭
 * @create: 2023-09-29 21:46
 * @version: 1.0
 */

@RestController
public class ScheduleClient implements IScheduleClient {

    @Resource
    private TaskService taskService;

    @Override
    @PostMapping("/api/v1/task/add")
    public ResponseResult addTask(@RequestBody Task task) throws InvocationTargetException, IllegalAccessException {
        return ResponseResult.okResult(taskService.addTask(task));
    }

    @Override
    @PostMapping("/api/v1/task/{taskId}")
    public ResponseResult cancelTask(@PathVariable("taskId") long taskId) {
        return ResponseResult.okResult(taskService.cancelTask(taskId));
    }

    @Override
    @GetMapping("/api/v1/task/{type}/{priority}")
    public ResponseResult poll(@PathVariable("type") int type, @PathVariable("priority") int priority) {
        return ResponseResult.okResult(taskService.poll(type, priority));
    }
}