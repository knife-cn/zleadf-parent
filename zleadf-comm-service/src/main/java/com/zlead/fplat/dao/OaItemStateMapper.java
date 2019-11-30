package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaItemState;
import com.zlead.fplat.entity.OaItemStateExample;
import java.util.List;

public interface OaItemStateMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_item_state
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * This method:insert
     *   oa_item_state
     *
     * @ET
     */
    int insert(OaItemState record);

    /**
     * This method:insertSelective
     *   oa_item_state
     *
     * @ET
     */
    int insertSelective(OaItemState record);

    /**
     * This method:selectByExample
     *   oa_item_state
     *
     * @ET
     */
    List<OaItemState> selectByExample(OaItemStateExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_item_state
     *
     * @ET
     */
    OaItemState selectByPrimaryKey(Integer itemId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_item_state
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaItemState record);

    /**
     * This method:updateByPrimaryKey
     *   oa_item_state
     *
     * @ET
     */
    int updateByPrimaryKey(OaItemState record);
}