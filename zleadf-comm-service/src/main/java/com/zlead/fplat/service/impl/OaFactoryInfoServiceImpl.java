package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.OaFactoryInfoMapper;
import com.zlead.fplat.service.OaFactoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OaFactoryInfoServiceImpl implements OaFactoryInfoService {
    @Autowired
    private OaFactoryInfoMapper mapper;

    @Override
    public List<Map<String, Object>> unRelationFactoryListByAgentId(Long agentId) {
        return mapper.unRelationFactoryListByAgentId(agentId);
    }
}
