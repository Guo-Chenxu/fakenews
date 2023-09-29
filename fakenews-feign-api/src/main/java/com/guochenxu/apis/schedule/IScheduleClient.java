package com.guochenxu.apis.schedule;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.schedule.dto.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: 郭晨旭
 * @create: 2023-09-29 21:42
 * @version: 1.0
 */

@FeignClient("fakenews-schedule")
public interface IScheduleClient {

    @PostMapping("/api/v1/task/add")
    ResponseResult addTask(@RequestBody Task task) throws InvocationTargetException, IllegalAccessException;

    @PostMapping("/api/v1/task/{taskId}")
    ResponseResult cancelTask(@PathVariable("taskId") long taskId);

    @GetMapping("/api/v1/task/{type}/{priority}")
    ResponseResult poll(@PathVariable("type") int type, @PathVariable("priority") int priority);
}
