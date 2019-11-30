package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Prodcat;
import com.zlead.fplat.entity.ProdcatExample;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProdcatMapper {
    /**
     * This method:deleteByPrimaryKey
     *   crm_prd_cat
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer catId);

    /**
     * This method:insert
     *   crm_prd_cat
     *
     * @ET
     */
    int insert(Prodcat record);

    /**
     * This method:insertSelective
     *   crm_prd_cat
     *
     * @ET
     */
    int insertSelective(Prodcat record);

    /**
     * This method:selectByExample
     *   crm_prd_cat
     *
     * @ET
     */
    List<Prodcat> selectByExample(ProdcatExample example);

    /**
     * This method:selectByPrimaryKey
     *   crm_prd_cat
     *
     * @ET
     */
    Prodcat selectByPrimaryKey(Integer catId);

    /**
     * This method:updateByPrimaryKeySelective
     *   crm_prd_cat
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prodcat record);

    /**
     * This method:updateByPrimaryKey
     *   crm_prd_cat
     *
     * @ET
     */
    int updateByPrimaryKey(Prodcat record);

    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByFactoryIds(Set<Long> factoryIds);
}