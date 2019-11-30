package com.zlead.fplat.service;

import com.zlead.fplat.entity.Agentband;

import java.util.List;
import java.util.Map;

public interface AgentbandService {
    int insert(Agentband agentband);

    List<Map<String, Object>> findByAgentIdAndKey(Long factoryId, Long agentId, String key);

    List<Map<String, Object>> findByAgentIdAndKeyAPP(Long factoryId, String key);

    List<Integer> findListByAgentId(Long agentId);

    List<Integer> findCatByAgentId(Long agentId);



//    List<Integer> findPrdCatByAgentId(Long agentId);
}
