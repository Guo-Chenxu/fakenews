package com.guochenxu.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.article.entity.ApArticleContent;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fakenews
 * @description: 文章内容接口
 * @author: 郭晨旭
 * @create: 2023-09-03 17:55
 * @version: 1.0
 **/

@Mapper
public interface ApArticleContentMapper extends BaseMapper<ApArticleContent> {

}
