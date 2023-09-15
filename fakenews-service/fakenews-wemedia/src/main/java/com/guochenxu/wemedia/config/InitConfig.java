package com.guochenxu.wemedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: fakenews
 * @description: 初始化
 * @author: 郭晨旭
 * @create: 2023-09-15 19:06
 * @version: 1.0
 **/

@Configuration
@ComponentScan("com.guochenxu.apis.article.fallback")
public class InitConfig {
}
