package com.guochenxu.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.wemedia.entity.WmChannel;
import com.guochenxu.wemedia.mapper.WmChannelMapper;
import com.guochenxu.wemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {


    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}