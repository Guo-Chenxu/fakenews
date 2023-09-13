package com.guochenxu.wemedia.config;

import com.guochenxu.wemedia.interceptor.WmTokenInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: fakenews
 * @description: config
 * @author: 郭晨旭
 * @create: 2023-09-13 15:50
 * @version: 1.0
 **/
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WmTokenInterceptor()).addPathPatterns("/**");
    }
}
