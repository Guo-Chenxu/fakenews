package com.guochenxu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.article.dto.ArticleDto;
import com.guochenxu.model.article.dto.ArticleHomeDto;
import com.guochenxu.model.article.entity.ApArticle;
import com.guochenxu.model.common.dto.ResponseResult;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: fakenews
 * @description: app文章服务
 * @author: 郭晨旭
 * @create: 2023-09-02 16:49
 * @version: 1.0
 **/
public interface ApArticleService extends IService<ApArticle> {

    /**
     * 加载文章
     *
     * @param articleHomeDto 参数
     * @param type           1 加载更多  2  加载最新
     */
    ResponseResult load(ArticleHomeDto articleHomeDto, Short type);

    ResponseResult saveArticle(ArticleDto dto) throws InvocationTargetException, IllegalAccessException;
}
