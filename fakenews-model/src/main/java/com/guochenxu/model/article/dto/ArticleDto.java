package com.guochenxu.model.article.dto;

import com.guochenxu.model.article.entity.ApArticle;
import lombok.Data;

/**
 * @program: fakenews
 * @description: dto
 * @author: 郭晨旭
 * @create: 2023-09-15 18:04
 * @version: 1.0
 **/
@Data
public class ArticleDto extends ApArticle {
    /**
     * 文章内容
     */
    private String content;
}
