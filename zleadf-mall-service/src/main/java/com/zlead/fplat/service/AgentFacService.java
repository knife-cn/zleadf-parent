package com.zlead.fplat.service;

import com.zlead.fplat.entity.AgentFac;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 代理商和工厂之间的关联接口
 */
public interface AgentFacService {
    /**
     * 插入关联数据
     */
    void insert(AgentFac agentFac);

    /**
     * 根据代理商id、工厂id以及状态获取count
     */
    int queryCountByAgentIdAndFactoryIdAndStatus(Long agentId, Long factoryId, Integer status);

    /**
     * 根据代理商id获取工厂id
     */
    List<Map<String, Object>> findFactoryIdListByAgentId(Long agentId, Long factoryId);

    /**
     * 根据代理商id获取工厂id APP
     */
    List<Map<String, Object>> findFactoryIdListByAgentAPP(Long agentId, Long factoryId);

    List<Integer> queryByShopIdAll(Long agentId);

    /**
     * 通过agentId更新t_agent_fac
     *
     * @param agentFac
     * @return
     * @author 廖巨会
     * @date
     */
    Integer updateByPrimaryKeySelective(AgentFac agentFac);

    /**
     * 通过agentId修改数据
     *
     * @param
     * @return
     * @author 廖巨会
     * @date
     */
    Integer updateAgentFacAtter(AgentFac agentFac);

    /**
     * 根据ID 查 agentFac
     * @param agentId
     * @return
     */
    List<AgentFac> findAgentFac(Long agentId);

    AgentFac findByAgentId(@Param("id") Integer id);



}
