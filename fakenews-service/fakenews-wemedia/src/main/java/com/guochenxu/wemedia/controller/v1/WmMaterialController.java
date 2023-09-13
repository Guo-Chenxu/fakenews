package com.guochenxu.wemedia.controller.v1;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmMaterialDto;
import com.guochenxu.wemedia.service.WmMaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @program: fakenews
 * @description: controller
 * @author: 郭晨旭
 * @create: 2023-09-13 15:53
 * @version: 1.0
 **/

@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {

    @Resource
    WmMaterialService wmMaterialService;

    @PostMapping("/upload _picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) throws IOException {
        return wmMaterialService.uploadPicture(multipartFile);
    }

    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto) {
        return wmMaterialService.findList(dto);
    }
}
