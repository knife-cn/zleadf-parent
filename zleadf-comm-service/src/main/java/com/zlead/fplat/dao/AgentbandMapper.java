package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Agentband;
import com.zlead.fplat.entity.AgentbandExample;

import java.util.List;
import java.util.Map;

public interface AgentbandMapper {
    /**
     * This method:deleteByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     * oa_agent_band
     *
     * @ET
     */
    int insert(Agentband record);

    /**
     * This method:insertSelective
     * oa_agent_band
     *
     * @ET
     */
    int insertSelective(Agentband record);

    /**
     * This method:selectByExample
     * oa_agent_band
     *
     * @ET
     */
    List<Agentband> selectByExample(AgentbandExample example);

    /**
     * This method:selectByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    Agentband selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     * oa_agent_band
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Agentband record);

    /**
     * This method:updateByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    int updateByPrimaryKey(Agentband record);

    /**
     * 根据传入的agent_id返回 该代理商所代理的所有工厂的品牌以及系列
     */
    List<Map<String, Object>> selectBrandAndListByAgentId(Long agentId);
}