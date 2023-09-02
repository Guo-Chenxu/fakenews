package com.guochenxu.model.article.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: fakenews
 * @description: 文章传输模型
 * @author: 郭晨旭
 * @create: 2023-09-02 16:32
 * @version: 1.0
 **/

@Data
public class ArticleHomeDto {

    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;
}