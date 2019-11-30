package com.zlead.fplat.dao;

import com.zlead.fplat.entity.PrdInventMas;
import com.zlead.fplat.entity.PrdInventMasExample;
import java.util.List;

public interface PrdInventMasMapper {
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
    int insert(PrdInventMas record);

    /**
     * This method:insertSelective
     *   prd_invent_mas
     *
     * @ET
     */
    int insertSelective(PrdInventMas record);

    /**
     * This method:selectByExample
     *   prd_invent_mas
     *
     * @ET
     */
    List<PrdInventMas> selectByExample(PrdInventMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   prd_invent_mas
     *
     * @ET
     */
    PrdInventMas selectByPrimaryKey(Integer imId);

    /**
     * This method:updateByPrimaryKeySelective
     *   prd_invent_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(PrdInventMas record);

    /**
     * This method:updateByPrimaryKey
     *   prd_invent_mas
     *
     * @ET
     */
    int updateByPrimaryKey(PrdInventMas record);
}