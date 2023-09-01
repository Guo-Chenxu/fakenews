package com.guochenxu.model.user.dto;

import lombok.Data;

/**
 * @program: fakenews
 * @description: 登录dto
 * @author: 郭晨旭
 * @create: 2023-09-01 15:59
 * @version: 1.0
 **/

@Data
public class LoginDto {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
}
