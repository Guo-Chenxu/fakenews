package com.guochenxu.search.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guochenxu.model.common.dto.ResponseResult;
import com.guochenxu.model.common.enums.AppHttpCodeEnum;
import com.guochenxu.model.search.dto.UserSearchDto;
import com.guochenxu.search.entity.ApAssociateWords;
import com.guochenxu.search.mapper.ApAssociateWordsMapper;
import com.guochenxu.search.service.ApAssociateWordsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 联想词表 服务实现类
 * </p>
 *
 * @author itheima
 */
@Slf4j
@Service
public class ApAssociateWordsServiceImpl extends ServiceImpl<ApAssociateWordsMapper, ApAssociateWords> implements ApAssociateWordsService {

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 联想词
     *
     * @param userSearchDto
     * @return
     */
    @Override
    public ResponseResult findAssociate(UserSearchDto userSearchDto) {
        //1 参数检查
        if (userSearchDto == null || StringUtils.isBlank(userSearchDto.getSearchWords())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //分页检查
        if (userSearchDto.getPageSize() > 20) {
            userSearchDto.setPageSize(20);
        }

        //3 执行查询 模糊查询
        Query query = Query.query(Criteria.where("associateWords").regex(".*?\\" + userSearchDto.getSearchWords() + ".*"));
        query.limit(userSearchDto.getPageSize());
        List<ApAssociateWords> wordsList = mongoTemplate.find(query, ApAssociateWords.class);

        return ResponseResult.okResult(wordsList);
    }

}