package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvTakeItem;
import com.zlead.fplat.entity.InvTakeItemExample;
import java.util.List;

public interface InvTakeItemMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_take_item
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * This method:insert
     *   inv_take_item
     *
     * @ET
     */
    int insert(InvTakeItem record);

    /**
     * This method:insertSelective
     *   inv_take_item
     *
     * @ET
     */
    int insertSelective(InvTakeItem record);

    /**
     * This method:selectByExample
     *   inv_take_item
     *
     * @ET
     */
    List<InvTakeItem> selectByExample(InvTakeItemExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_take_item
     *
     * @ET
     */
    InvTakeItem selectByPrimaryKey(Integer detailId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_take_item
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvTakeItem record);

    /**
     * This method:updateByPrimaryKey
     *   inv_take_item
     *
     * @ET
     */
    int updateByPrimaryKey(InvTakeItem record);
}