package com.guochenxu.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.article.mapper.ApArticleMapper;
import com.guochenxu.article.service.ApArticleService;
import com.guochenxu.common.constants.ArticleConstants;
import com.guochenxu.model.article.dto.ArticleHomeDto;
import com.guochenxu.model.article.entity.ApArticle;
import com.guochenxu.model.common.dto.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: fakenews
 * @description: app文章服务实现类
 * @author: 郭晨旭
 * @create: 2023-09-02 16:50
 * @version: 1.0
 **/

@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Resource
    private ApArticleMapper apArticleMapper;

    private final static short MAX_PAGE_SIZE = 50;

    @Override
    public ResponseResult load(ArticleHomeDto articleHomeDto, Short type) {
        // 校验分页大小
        Integer size = articleHomeDto.getSize();
        if (size == null || size <= 0) {
            size = 10;
        }
        size = Math.min(size, MAX_PAGE_SIZE);
        articleHomeDto.setSize(size);
        // 校验查询类型
        if (type == null || (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE)
                && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW))) {
            type = ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        // 校验频道
        if (StringUtils.isBlank(articleHomeDto.getTag())) {
            articleHomeDto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        // 校验时间
        if (articleHomeDto.getMaxBehotTime() == null) {
            articleHomeDto.setMaxBehotTime(new Date());
        }
        if (articleHomeDto.getMinBehotTime() == null) {
            articleHomeDto.setMinBehotTime(new Date());
        }

        List<ApArticle> apArticles = apArticleMapper.loadArticleList(articleHomeDto, type);
        return ResponseResult.okResult(apArticles);
    }
}
