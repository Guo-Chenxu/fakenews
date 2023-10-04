package com.guochenxu.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.article.entity.ApArticleConfig;

import java.util.Map;

/**
 * @author: 郭晨旭
 * @create: 2023-10-04 23:53
 * @version: 1.0
 */
public interface ApArticleConfigService extends IService<ApArticleConfig> {
    public void updateByMap(Map map);
}
