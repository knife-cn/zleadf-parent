package com.zlead.fplat.dao;

import com.zlead.fplat.entity.InvOutMas;
import com.zlead.fplat.entity.InvOutMasExample;
import java.util.List;

public interface InvOutMasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   inv_out_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer billId);

    /**
     * This method:insert
     *   inv_out_mas
     *
     * @ET
     */
    int insert(InvOutMas record);

    /**
     * This method:insertSelective
     *   inv_out_mas
     *
     * @ET
     */
    int insertSelective(InvOutMas record);

    /**
     * This method:selectByExample
     *   inv_out_mas
     *
     * @ET
     */
    List<InvOutMas> selectByExample(InvOutMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   inv_out_mas
     *
     * @ET
     */
    InvOutMas selectByPrimaryKey(Integer billId);

    /**
     * This method:updateByPrimaryKeySelective
     *   inv_out_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(InvOutMas record);

    /**
     * This method:updateByPrimaryKey
     *   inv_out_mas
     *
     * @ET
     */
    int updateByPrimaryKey(InvOutMas record);
}