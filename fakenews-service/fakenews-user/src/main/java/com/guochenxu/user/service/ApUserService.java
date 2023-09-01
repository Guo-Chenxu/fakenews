package com.guochenxu.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.user.dto.LoginDto;
import com.guochenxu.model.user.entity.ApUser;

/**
 * @program: fakenews
 * @description: app user service
 * @author: 郭晨旭
 * @create: 2023-09-01 16:06
 * @version: 1.0
 **/

public interface ApUserService extends IService<ApUser> {

    /**
     * app 端登录功能
     */
    ResponseResult login(LoginDto loginDto);
}
