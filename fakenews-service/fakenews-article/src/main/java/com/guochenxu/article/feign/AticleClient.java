package com.guochenxu.article.feign;

import com.guochenxu.apis.article.IArticleClient;
import com.guochenxu.article.service.ApArticleService;
import com.guochenxu.model.article.dto.ArticleDto;
import com.guochenxu.model.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: fakenews
 * @description: 文章接口实现
 * @author: 郭晨旭
 * @create: 2023-09-15 18:09
 * @version: 1.0
 **/

@RestController
public class AticleClient implements IArticleClient {

    @Resource
    private ApArticleService apArticleService;

    @Override
    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(ArticleDto dto) throws InvocationTargetException, IllegalAccessException {
        return apArticleService.saveArticle(dto);
    }
}
