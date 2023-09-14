package com.guochenxu.wemedia.controller.v1;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmNewsDto;
import com.guochenxu.model.wemedia.dto.WmNewsPageReqDto;
import com.guochenxu.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: fakenews
 * @description: news
 * @author: 郭晨旭
 * @create: 2023-09-14 17:27
 * @version: 1.0
 **/

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {


    @Autowired
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto) {
        return wmNewsService.findAll(dto);
    }


    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto) throws InvocationTargetException, IllegalAccessException {
        return wmNewsService.submitNews(dto);
    }
}
