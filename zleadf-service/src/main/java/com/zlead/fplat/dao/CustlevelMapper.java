package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Custlevel;
import com.zlead.fplat.entity.CustlevelExample;
import java.util.List;

public interface CustlevelMapper {
    /**
     * This method:deleteByPrimaryKey
     *   crm_cust_level
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer levelId);

    /**
     * This method:insert
     *   crm_cust_level
     *
     * @ET
     */
    int insert(Custlevel record);

    /**
     * This method:insertSelective
     *   crm_cust_level
     *
     * @ET
     */
    int insertSelective(Custlevel record);

    /**
     * This method:selectByExample
     *   crm_cust_level
     *
     * @ET
     */
    List<Custlevel> selectByExample(CustlevelExample example);

    /**
     * This method:selectByPrimaryKey
     *   crm_cust_level
     *
     * @ET
     */
    Custlevel selectByPrimaryKey(Integer levelId);

    /**
     * This method:updateByPrimaryKeySelective
     *   crm_cust_level
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Custlevel record);

    /**
     * This method:updateByPrimaryKey
     *   crm_cust_level
     *
     * @ET
     */
    int updateByPrimaryKey(Custlevel record);
}