package com.zlead.service;

import com.zlead.entity.OaItemState;

public interface OaItemStateService {
    int deleteByPrimaryKey(Integer itemId);

    int insert(OaItemState record);

    OaItemState selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(OaItemState record);

    int updateByPrimaryKey(OaItemState record);
}