package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.entity.OaFactoryInfoExample;

import java.util.List;
import java.util.Map;

public interface OaFactoryInfoMapper {
    /**
     * This method:deleteByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer factId);

    /**
     * This method:insert
     * oa_factory_info
     *
     * @ET
     */
    int insert(OaFactoryInfo record);

    /**
     * This method:insertSelective
     * oa_factory_info
     *
     * @ET
     */
    int insertSelective(OaFactoryInfo record);

    /**
     * This method:selectByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    OaFactoryInfo selectByPrimaryKey(Integer factId);

    /**
     * This method:updateByPrimaryKeySelective
     * oa_factory_info
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaFactoryInfo record);

    /**
     * This method:updateByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    int updateByPrimaryKey(OaFactoryInfo record);

    /**
     * 获取未与该代理商关联的工厂
     *
     * @param agentId 代理商id
     */
    List<Map<String, Object>> unRelationFactoryListByAgentId(Long agentId);
}