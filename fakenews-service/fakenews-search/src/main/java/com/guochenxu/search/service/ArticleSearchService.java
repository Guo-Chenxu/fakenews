package com.guochenxu.search.service;

import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.search.dto.UserSearchDto;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto userSearchDto) throws IOException;
}