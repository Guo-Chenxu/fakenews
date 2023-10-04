package com.guochenxu.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmNewsPageReqDto;
import com.guochenxu.model.wemedia.entity.WmNews;
import com.guochenxu.model.wemedia.dto.WmNewsDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;


public interface WmNewsService extends IService<WmNews> {

    ResponseResult findAll(WmNewsPageReqDto dto);

    ResponseResult submitNews(WmNewsDto dto) throws InvocationTargetException, IllegalAccessException;

    ResponseResult downOrUp(@RequestBody WmNewsDto dto);
}
