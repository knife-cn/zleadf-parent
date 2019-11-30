package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Inventmas;
import com.zlead.fplat.entity.InventmasExample;
import java.util.List;

public interface InventmasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   prd_invent_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer imId);

    /**
     * This method:insert
     *   prd_invent_mas
     *
     * @ET
     */
    int insert(Inventmas record);

    /**
     * This method:insertSelective
     *   prd_invent_mas
     *
     * @ET
     */
    int insertSelective(Inventmas record);

    /**
     * This method:selectByExample
     *   prd_invent_mas
     *
     * @ET
     */
    List<Inventmas> selectByExample(InventmasExample example);

    /**
     * This method:selectByPrimaryKey
     *   prd_invent_mas
     *
     * @ET
     */
    Inventmas selectByPrimaryKey(Integer imId);

    /**
     * This method:updateByPrimaryKeySelective
     *   prd_invent_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Inventmas record);

    /**
     * This method:updateByPrimaryKey
     *   prd_invent_mas
     *
     * @ET
     */
    int updateByPrimaryKey(Inventmas record);
}