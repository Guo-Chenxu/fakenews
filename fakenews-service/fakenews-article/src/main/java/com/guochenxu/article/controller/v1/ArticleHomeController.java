package com.guochenxu.article.controller.v1;

import com.guochenxu.article.service.ApArticleService;
import com.guochenxu.common.constants.ArticleConstants;
import com.guochenxu.model.article.dto.ArticleHomeDto;
import com.guochenxu.model.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: fakenews
 * @description: 文章管理器
 * @author: 郭晨旭
 * @create: 2023-09-02 16:42
 * @version: 1.0
 **/

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

    @Resource
    private ApArticleService apArticleService;

    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    @PostMapping("/loadmore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }

    @PostMapping("/loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto articleHomeDto) {
        return apArticleService.load(articleHomeDto, ArticleConstants.LOADTYPE_LOAD_NEW);
    }
}