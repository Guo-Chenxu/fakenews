package com.guochenxu.article.service;

import com.guochenxu.model.article.entity.ApArticle;
import org.springframework.scheduling.annotation.Async;

/**
 * @author: 郭晨旭
 * @create: 2023-10-07 18:32
 * @version: 1.0
 */
public interface ArticleFreemarkerService {
    @Async
    void buildArticleToMinIO(ApArticle apArticle, String content);
}
