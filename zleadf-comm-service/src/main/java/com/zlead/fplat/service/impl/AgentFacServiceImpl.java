package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.entity.AgentFac;
import com.zlead.fplat.service.AgentFacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AgentFacServiceImpl implements AgentFacService {
    @Autowired
    private AgentFacMapper mapper;

    @Override
    public void insert(AgentFac agentFac) {
        mapper.insert(agentFac);
    }

    @Override
    public int queryCountByAgentIdAndFactoryIdAndStatus(Long agentId, Long factoryId, Integer status) {
        return mapper.queryCountByAgentIdAndFactoryIdAndStatus(agentId, factoryId, status);
    }

    @Override
    public List<Map<String, Object>> findFactoryIdListByAgentId(Long agentId) {
        return mapper.findFactoryIdListByAgentId(agentId);
    }
}
