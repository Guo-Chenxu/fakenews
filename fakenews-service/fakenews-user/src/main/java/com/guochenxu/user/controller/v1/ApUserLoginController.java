package com.guochenxu.user.controller.v1;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.user.dto.LoginDto;
import com.guochenxu.user.service.ApUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: fakenews
 * @description: app用户登录接口
 * @author: 郭晨旭
 * @create: 2023-09-01 15:58
 * @version: 1.0
 **/

@RestController
@RequestMapping("/api/v1/login")
public class ApUserLoginController {

    @Resource
    private ApUserService apUserService;

    /**
     * app 端用户登录
     */
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        return apUserService.login(loginDto);
    }
}
