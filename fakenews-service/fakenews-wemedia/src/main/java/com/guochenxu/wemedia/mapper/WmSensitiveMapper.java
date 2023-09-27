package com.guochenxu.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.wemedia.entity.WmSensitive;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fakenews
 * @description: 敏感词
 * @author: 郭晨旭
 * @create: 2023-09-17 16:34
 * @version: 1.0
 **/

@Mapper
public interface WmSensitiveMapper extends BaseMapper<WmSensitive> {
}
