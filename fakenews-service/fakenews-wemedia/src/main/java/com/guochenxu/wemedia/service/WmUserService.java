package com.guochenxu.wemedia.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmLoginDto;
import com.guochenxu.model.wemedia.entity.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     *
     * @param dto
     * @return
     */
    ResponseResult login(WmLoginDto dto);

}