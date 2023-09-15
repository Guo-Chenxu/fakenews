package com.guochenxu.apis.article.fallback;

import com.guochenxu.apis.article.IArticleClient;
import com.guochenxu.model.article.dto.ArticleDto;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: fakenews
 * @description: 熔断降级
 * @author: 郭晨旭
 * @create: 2023-09-15 19:04
 * @version: 1.0
 **/

@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto) throws InvocationTargetException, IllegalAccessException {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR, "相应失败");
    }
}
