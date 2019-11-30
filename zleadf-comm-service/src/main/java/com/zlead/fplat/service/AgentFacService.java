package com.zlead.fplat.service;

import com.zlead.fplat.entity.AgentFac;

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
    List<Map<String, Object>> findFactoryIdListByAgentId(Long agentId);
}
