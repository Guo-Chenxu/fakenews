package com.guochenxu.wemedia.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guochenxu.model.wemedia.entity.WmNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface WmNewsMapper extends BaseMapper<WmNews> {

}
