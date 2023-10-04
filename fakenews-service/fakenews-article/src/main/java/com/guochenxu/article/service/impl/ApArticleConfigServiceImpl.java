package com.guochenxu.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.article.service.ApArticleConfigService;
import com.guochenxu.model.article.entity.ApArticleConfig;
import com.guochenxu.article.mapper.ApArticleConfigMapper;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author: 郭晨旭
 * @create: 2023-10-04 23:53
 * @version: 1.0
 */

@Service
public class ApArticleConfigServiceImpl extends ServiceImpl<ApArticleConfigMapper, ApArticleConfig> implements ApArticleConfigService {
    @Override
    public void updateByMap(Map map) {
        //0 下架 1 上架
        Object enable = map.get("enable");
        boolean isDown = true;
        if (enable.equals(1)) {
            isDown = false;
        }
        //修改文章配置
        update(Wrappers.<ApArticleConfig>lambdaUpdate().eq(ApArticleConfig::getArticleId, map.get("articleId")).set(ApArticleConfig::getIsDown, isDown));

    }
}