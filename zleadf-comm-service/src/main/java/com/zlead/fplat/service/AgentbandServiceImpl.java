package com.zlead.fplat.service;

import com.zlead.fplat.dao.AgentbandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AgentbandServiceImpl implements AgentbandService {
    @Autowired
    private AgentbandMapper mapper;

    @Override
    public List<Map<String, Object>> selectBrandAndListByAgentId(Long agentId) {
        return mapper.selectBrandAndListByAgentId(agentId);
    }
}
