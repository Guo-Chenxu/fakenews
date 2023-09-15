package com.guochenxu.apis.article;

import com.guochenxu.apis.article.fallback.IArticleClientFallback;
import com.guochenxu.model.article.dto.ArticleDto;
import com.guochenxu.model.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: fakenews
 * @description: 文章接口
 * @author: 郭晨旭
 * @create: 2023-09-15 18:06
 * @version: 1.0
 **/

@FeignClient(name = "fakenews-article", fallback = IArticleClientFallback.class)
public interface IArticleClient {

    @PostMapping("/api/v1/article/save")
    ResponseResult saveArticle(@RequestBody ArticleDto dto) throws InvocationTargetException, IllegalAccessException;
}
