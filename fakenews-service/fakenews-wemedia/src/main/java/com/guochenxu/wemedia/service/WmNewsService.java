package com.guochenxu.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmNewsPageReqDto;
import com.guochenxu.model.wemedia.entity.WmNews;

;

public interface WmNewsService extends IService<WmNews> {

    ResponseResult findAll(WmNewsPageReqDto dto);
}
