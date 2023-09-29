package com.guochenxu.model.schedule.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * task
 *
 * @author: 郭晨旭
 * @create: 2023-09-29 17:09
 * @version: 1.0
 */

@Data
public class Task implements Serializable {

    /**
     * 任务id
     */
    private Long taskId;
    /**
     * 类型
     */
    private Integer taskType;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 执行id
     */
    private long executeTime;

    /**
     * task参数
     */
    private byte[] parameters;

}