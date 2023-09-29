package com.guochenxu.schedule.service;

import com.guochenxu.model.schedule.dto.Task;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: 郭晨旭
 * @create: 2023-09-29 17:09
 * @version: 1.0
 */
public interface TaskService {
    long addTask(Task task) throws InvocationTargetException, IllegalAccessException;

    boolean cancelTask(long taskId);

    Task poll(int type,int priority);
}
