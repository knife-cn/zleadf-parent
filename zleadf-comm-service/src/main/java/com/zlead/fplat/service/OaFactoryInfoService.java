package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;

public interface OaFactoryInfoService {
    /**
     * 获取未与该代理商关联的工厂
     *
     * @param agentId 代理商id
     */
    List<Map<String, Object>> unRelationFactoryListByAgentId(Long agentId);
}
