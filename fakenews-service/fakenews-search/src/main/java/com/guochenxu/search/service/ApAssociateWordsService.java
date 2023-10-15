package com.guochenxu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.search.dto.UserSearchDto;
import com.guochenxu.search.entity.ApAssociateWords;

/**
 * <p>
 * 联想词表 服务类
 * </p>
 *
 * @author itheima
 */
public interface ApAssociateWordsService extends IService<ApAssociateWords> {

    /**
     * 联想词
     *
     * @param userSearchDto
     * @return
     */
    ResponseResult findAssociate(UserSearchDto userSearchDto);


}