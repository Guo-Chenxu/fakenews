package com.guochenxu.model.wemedia.dto;

import com.guochenxu.model.common.dto.PageRequestDto;
import lombok.Data;

/**
 * @program: fakenews
 * @description: dto
 * @author: 郭晨旭
 * @create: 2023-09-13 16:14
 * @version: 1.0
 **/

@Data
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 未收藏
     */
    private Short isCollection;
}
