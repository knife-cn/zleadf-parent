package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvTakeMas;
import com.zlead.fplat.entity.InvTakeMasExample;
import java.util.List;

public interface InvTakeMasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_take_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer billId);

    /**
     * This method:insert
     *   inv_take_mas
     *
     * @ET
     */
    int insert(InvTakeMas record);

    /**
     * This method:insertSelective
     *   inv_take_mas
     *
     * @ET
     */
    int insertSelective(InvTakeMas record);

    /**
     * This method:selectByExample
     *   inv_take_mas
     *
     * @ET
     */
    List<InvTakeMas> selectByExample(InvTakeMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_take_mas
     *
     * @ET
     */
    InvTakeMas selectByPrimaryKey(Integer billId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_take_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvTakeMas record);

    /**
     * This method:updateByPrimaryKey
     *   inv_take_mas
     *
     * @ET
     */
    int updateByPrimaryKey(InvTakeMas record);
}