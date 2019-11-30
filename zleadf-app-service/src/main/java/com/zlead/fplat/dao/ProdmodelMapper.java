package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Prodmodel;
import com.zlead.fplat.entity.ProdmodelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProdmodelMapper {
    /**
     * This method:deleteByPrimaryKey
     *   crm_prd_model
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer modelId);

    /**
     * This method:insert
     *   crm_prd_model
     *
     * @ET
     */
    int insert(Prodmodel record);

    /**
     * This method:insertSelective
     *   crm_prd_model
     *
     * @ET
     */
    int insertSelective(Prodmodel record);

    /**
     * This method:selectByExample
     *   crm_prd_model
     *
     * @ET
     */
    List<Prodmodel> selectByExample(ProdmodelExample example);

    /**
     * This method:selectByPrimaryKey
     *   crm_prd_model
     *
     * @ET
     */
    Prodmodel selectByPrimaryKey(Integer modelId);

    /**
     * This method:updateByPrimaryKeySelective
     *   crm_prd_model
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prodmodel record);

    /**
     * This method:updateByPrimaryKey
     *   crm_prd_model
     *
     * @ET
     */
    int updateByPrimaryKey(Prodmodel record);

    /**
     * 首页搜索型号
     * @param agentId
     * @param listIds
     * @param bandIds
     * @return
     */
    List<Prodmodel> queryAllModel(@Param("agentId") Long agentId, @Param("listIds")List<Long> listIds, @Param("bandIds")List<Long> bandIds);
}