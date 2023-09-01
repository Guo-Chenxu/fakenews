package com.guochenxu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.common.enums.AppHttpCodeEnum;
import com.guochenxu.model.user.dto.LoginDto;
import com.guochenxu.model.user.entity.ApUser;
import com.guochenxu.user.mapper.ApUserMapper;
import com.guochenxu.user.service.ApUserService;
import com.guochenxu.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: fakenews
 * @description: app user service impl
 * @author: 郭晨旭
 * @create: 2023-09-01 16:06
 * @version: 1.0
 **/

@Service
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Resource
    private ApUserMapper apUserMapper;

    @Override
    public ResponseResult login(LoginDto loginDto) {
        String phone = loginDto.getPhone();
        String password = loginDto.getPassword();
        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(password)) {
            // 用户登录
            ApUser user = getOne(new LambdaQueryWrapper<ApUser>().eq(ApUser::getPhone, phone));

            if (user == null) {
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST, "用户不存在");
            }

            String salt = user.getSalt();
            String password_digest = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!StringUtils.equals(user.getPassword(), password_digest)) {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR, "密码错误");
            }

            String token = AppJwtUtil.getToken(Long.valueOf(user.getId()));
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            user.setPassword("");
            user.setSalt("");
            map.put("user", user);
            return ResponseResult.okResult(map);
        } else {
            // 游客登录
            String token = AppJwtUtil.getToken(1L);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            return ResponseResult.okResult(map);
        }
    }
}
