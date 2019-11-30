package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvInMas;
import com.zlead.fplat.entity.InvInMasExample;
import java.util.List;

public interface InvInMasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_in_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer billId);

    /**
     * This method:insert
     *   inv_in_mas
     *
     * @ET
     */
    int insert(InvInMas record);

    /**
     * This method:insertSelective
     *   inv_in_mas
     *
     * @ET
     */
    int insertSelective(InvInMas record);

    /**
     * This method:selectByExample
     *   inv_in_mas
     *
     * @ET
     */
    List<InvInMas> selectByExample(InvInMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_in_mas
     *
     * @ET
     */
    InvInMas selectByPrimaryKey(Integer billId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_in_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvInMas record);

    /**
     * This method:updateByPrimaryKey
     *   inv_in_mas
     *
     * @ET
     */
    int updateByPrimaryKey(InvInMas record);
}