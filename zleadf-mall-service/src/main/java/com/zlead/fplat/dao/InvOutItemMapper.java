package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvOutItem;
import com.zlead.fplat.entity.InvOutItemExample;
import java.util.List;

public interface InvOutItemMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_out_item
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * This method:insert
     *   inv_out_item
     *
     * @ET
     */
    int insert(InvOutItem record);

    /**
     * This method:insertSelective
     *   inv_out_item
     *
     * @ET
     */
    int insertSelective(InvOutItem record);

    /**
     * This method:selectByExample
     *   inv_out_item
     *
     * @ET
     */
    List<InvOutItem> selectByExample(InvOutItemExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_out_item
     *
     * @ET
     */
    InvOutItem selectByPrimaryKey(Integer detailId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_out_item
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvOutItem record);

    /**
     * This method:updateByPrimaryKey
     *   inv_out_item
     *
     * @ET
     */
    int updateByPrimaryKey(InvOutItem record);
}