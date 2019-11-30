package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Agentband;
import com.zlead.fplat.entity.AgentbandList; 

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AgentbandListMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_agent_band_lists
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     *   oa_agent_band_lists
     *
     * @ET
     */
    int insert(AgentbandList record);

    /**
     * This method:insertSelective
     *   oa_agent_band_lists
     *
     * @ET
     */
    int insertSelective(AgentbandList record);

 
    /**
     * This method:selectByPrimaryKey
     *   oa_agent_band_lists
     *
     * @ET
     */
    AgentbandList selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_agent_band_lists
     *
     * @ET
     */
    int updateByPrimaryKeySelective(AgentbandList record);

    /**
     * This method:updateByPrimaryKey
     *   oa_agent_band_lists
     *
     * @ET
     */
    int updateByPrimaryKey(AgentbandList record);
    
    List<AgentbandList>  selectByAgentFacIds(@Param("agentId") Integer agentId,@Param("facIds")List<Integer> facIds);

    /**
     * 通过代理系列，代理商id，工厂id查询当前代理系列是否存在
     */
    AgentbandList queryAgencyList(@Param("agentId") Integer agentId,@Param("factId") Integer factId,@Param("listId")Integer listId);
}