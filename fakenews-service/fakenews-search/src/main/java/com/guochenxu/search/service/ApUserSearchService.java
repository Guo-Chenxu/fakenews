package com.guochenxu.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.search.dto.HistorySearchDto;
import com.guochenxu.search.entity.ApUserSearch;


public interface ApUserSearchService extends IService<ApUserSearch> {
    /**
     * 保存用户搜索历史记录
     * @param keyword
     * @param userId
     */
    void insert(String keyword,Integer userId);

    ResponseResult findUserSearch();

    /**
     删除搜索历史
     @param historySearchDto
     @return
     */
    ResponseResult delUserSearch(HistorySearchDto historySearchDto);
}