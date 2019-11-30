package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvInItem;
import com.zlead.fplat.entity.InvInItemExample;
import java.util.List;

public interface InvInItemMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_in_item
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * This method:insert
     *   inv_in_item
     *
     * @ET
     */
    int insert(InvInItem record);

    /**
     * This method:insertSelective
     *   inv_in_item
     *
     * @ET
     */
    int insertSelective(InvInItem record);

    /**
     * This method:selectByExample
     *   inv_in_item
     *
     * @ET
     */
    List<InvInItem> selectByExample(InvInItemExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_in_item
     *
     * @ET
     */
    InvInItem selectByPrimaryKey(Integer detailId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_in_item
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvInItem record);

    /**
     * This method:updateByPrimaryKey
     *   inv_in_item
     *
     * @ET
     */
    int updateByPrimaryKey(InvInItem record);
}