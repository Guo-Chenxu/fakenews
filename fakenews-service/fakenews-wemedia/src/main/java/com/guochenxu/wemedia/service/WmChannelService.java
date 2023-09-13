package com.guochenxu.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.entity.WmChannel;

;

public interface WmChannelService extends IService<WmChannel> {


    ResponseResult findAll();
}