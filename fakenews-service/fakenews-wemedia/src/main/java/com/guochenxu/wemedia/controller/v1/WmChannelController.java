package com.guochenxu.wemedia.controller.v1;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmMaterialDto;
import com.guochenxu.wemedia.service.WmChannelService;
import com.guochenxu.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/v1/channel")
public class WmChannelController {

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll() {
        return wmChannelService.findAll();
    }
}

