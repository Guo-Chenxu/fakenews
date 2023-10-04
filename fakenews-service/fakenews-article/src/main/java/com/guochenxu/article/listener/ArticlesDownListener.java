package com.guochenxu.article.listener;

import com.alibaba.fastjson.JSON;
import com.guochenxu.article.service.ApArticleConfigService;
import com.guochenxu.common.constants.WmNewsMessageConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: 郭晨旭
 * @create: 2023-10-04 23:51
 * @version: 1.0
 */

@Component
public class ArticlesDownListener {

    @Resource
    private ApArticleConfigService apArticleConfigService;

    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC)
    public void onMessage(String message) {
        if (StringUtils.isNotBlank(message)) {
            Map map = JSON.parseObject(message, Map.class);
            apArticleConfigService.updateByMap(map);
        }
    }
}
