package com.guochenxu.model.wemedia.dto;

import com.guochenxu.model.common.dto.PageRequestDto;
import lombok.Data;

import java.util.Date;

/**
 * @program: fakenews
 * @description: dto
 * @author: 郭晨旭
 * @create: 2023-09-13 16:49
 * @version: 1.0
 **/
@Data
public class WmNewsPageReqDto extends PageRequestDto {

    /**
     * 状态
     */
    private Short status;
    /**
     * 开始时间
     */
    private Date beginPubDate;
    /**
     * 结束时间
     */
    private Date endPubDate;
    /**
     * 所属频道ID
     */
    private Integer channelId;
    /**
     * 关键字
     */
    private String keyword;
}