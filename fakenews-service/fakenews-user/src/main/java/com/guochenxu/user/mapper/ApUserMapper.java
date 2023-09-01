package com.guochenxu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.user.entity.ApUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: fakenews
 * @description: app user mapper
 * @author: 郭晨旭
 * @create: 2023-09-01 16:05
 * @version: 1.0
 **/

@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
