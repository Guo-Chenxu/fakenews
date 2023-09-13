package com.guochenxu.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.dto.WmMaterialDto;
import com.guochenxu.model.wemedia.entity.WmMaterial;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WmMaterialService extends IService<WmMaterial> {

    ResponseResult uploadPicture(MultipartFile multipartFile) throws IOException;

    ResponseResult findList(@RequestBody WmMaterialDto dto);
}