package com.guochenxu.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.article.dto.ArticleHomeDto;
import com.guochenxu.model.article.entity.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: fakenews
 * @description: 文章 数据库
 * @author: 郭晨旭
 * @create: 2023-09-02 16:45
 * @version: 1.0
 **/
@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);
}

