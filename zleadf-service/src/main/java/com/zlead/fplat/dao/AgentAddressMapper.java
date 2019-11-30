package com.zlead.fplat.dao;

import com.zlead.fplat.entity.AgentAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AgentAddressMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_agent_revinfo
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer revId);

    /**
     * This method:insert
     *   oa_agent_revinfo
     *
     * @ET
     */
    int insert(AgentAddress record);

    /**
     * This method:insertSelective
     *   oa_agent_revinfo
     *
     * @ET
     */
    int insertSelective(AgentAddress record);


    /**
     * This method:selectByPrimaryKey
     *   oa_agent_revinfo
     *
     * @ET
     */
    AgentAddress selectByPrimaryKey(@Param("memberAddrId") Integer memberAddrId);

    List<AgentAddress> selectByPrimaryKeyList(@Param("memberAddrId") Integer memberAddrId);

    /**
     *
     *   oa_agent_revinfo
     *
     * @ET
     */
    int updateByPrimaryKeySelective(AgentAddress record);

    /**
     * This method:updateByPrimaryKey
     *   oa_agent_revinfo
     *
     * @ET
     */
    int updateByPrimaryKey(AgentAddress record);

    /**
     * 修改其他地址为非默认地址
     * @param memberAddrId
     * @param agentId
     */
    void  updateAgentAddressOtherIsDefault(@Param("memberAddrId") Integer memberAddrId,@Param("agentId") Integer agentId);


    AgentAddress findAddressRevByIds(@Param("agentId")Integer agentId, @Param("shopId")Long shopId);
}