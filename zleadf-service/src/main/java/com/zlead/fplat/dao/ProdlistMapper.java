package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Prodlist;
import com.zlead.fplat.entity.ProdlistExample;
import java.util.List;

public interface ProdlistMapper {
    /**
     * This method:deleteByPrimaryKey
     *   crm_prd_list
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer listId);

    /**
     * This method:insert
     *   crm_prd_list
     *
     * @ET
     */
    int insert(Prodlist record);

    /**
     * This method:insertSelective
     *   crm_prd_list
     *
     * @ET
     */
    int insertSelective(Prodlist record);

    /**
     * This method:selectByExample
     *   crm_prd_list
     *
     * @ET
     */
    List<Prodlist> selectByExample(ProdlistExample example);

    /**
     * This method:selectByPrimaryKey
     *   crm_prd_list
     *
     * @ET
     */
    Prodlist selectByPrimaryKey(Integer listId);

    /**
     * This method:updateByPrimaryKeySelective
     *   crm_prd_list
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prodlist record);

    /**
     * This method:updateByPrimaryKey
     *   crm_prd_list
     *
     * @ET
     */
    int updateByPrimaryKey(Prodlist record);
}