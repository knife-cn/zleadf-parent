package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;

public interface AgentbandService {
    /**
     * 根据传入的agent_id返回 该代理商所代理的所有工厂的品牌以及系列
     */
    List<Map<String, Object>> selectBrandAndListByAgentId(Long agentId);
}
