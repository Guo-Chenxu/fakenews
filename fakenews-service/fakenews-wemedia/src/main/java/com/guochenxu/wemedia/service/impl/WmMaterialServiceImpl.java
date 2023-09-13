package com.guochenxu.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.file.service.FileStorageService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.common.enums.AppHttpCodeEnum;
import com.guochenxu.model.wemedia.entity.WmMaterial;
import com.guochenxu.utils.thread.WmThreadLocalUtil;
import com.guochenxu.wemedia.mapper.WmMaterialMapper;
import com.guochenxu.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Resource
    private FileStorageService fileStorageService;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        String filename = UUID.randomUUID().toString().replace("-", "");
        String originalName = multipartFile.getOriginalFilename();
        filename = filename + "." + originalName.substring(originalName.indexOf('.'));
        String fileId = fileStorageService.uploadImgFile("", filename, multipartFile.getInputStream());

        WmMaterial material = new WmMaterial();
        material.setUserId(WmThreadLocalUtil.getUser().getApUserId());
        material.setUrl(fileId);
        material.setIsCollection((short) 0);
        material.setType((short) 0);
        material.setCreatedTime(new Date());
        this.save(material);

        return ResponseResult.okResult(material);
    }
}
