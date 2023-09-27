package com.guochenxu.schedule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.schedule.entity.TaskinfoLogs;
import com.guochenxu.model.schedule.entity.Taskinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author itheima
 */
@Mapper
public interface TaskinfoMapper extends BaseMapper<Taskinfo> {

    public List<Taskinfo> queryFutureTime(@Param("taskType") int type, @Param("priority") int priority, @Param("future") Date future);
}
