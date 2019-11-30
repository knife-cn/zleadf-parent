package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaAgentMasExample;
import java.util.List;

public interface OaAgentMasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_agent_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer agentId);

    /**
     * This method:insert
     *   oa_agent_mas
     *
     * @ET
     */
    int insert(OaAgentMas record);

    /**
     * This method:insertSelective
     *   oa_agent_mas
     *
     * @ET
     */
    int insertSelective(OaAgentMas record);

    /**
     * This method:selectByExample
     *   oa_agent_mas
     *
     * @ET
     */
    List<OaAgentMas> selectByExample(OaAgentMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_agent_mas
     *
     * @ET
     */
    OaAgentMas selectByPrimaryKey(Integer agentId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_agent_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaAgentMas record);

    /**
     * This method:updateByPrimaryKey
     *   oa_agent_mas
     *
     * @ET
     */
    int updateByPrimaryKey(OaAgentMas record);
}