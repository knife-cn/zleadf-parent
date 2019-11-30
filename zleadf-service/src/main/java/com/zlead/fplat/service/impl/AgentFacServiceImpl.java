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
    public List<Map<String, Object>> findFactoryIdListByAgentId(Long agentId, Long factoryId) {
        return mapper.findFactoryIdListByAgentId(agentId, factoryId);
    }
    //APP
    @Override
    public List<Map<String, Object>> findFactoryIdListByAgentAPP(Long agentId, Long factoryId) {
        return mapper.findFactoryIdListByAgentId(agentId, factoryId);
    }

    /**
     * 通过agentId 查询当前代理商的shopId
     * @param agentId
     * @return
     */
    public List<Integer> queryByShopIdAll(Long agentId){
        return mapper.queryByShopIdAll(agentId);
    }

    /**
     * 通过agentId更新t_agent_fac
     * @author 廖巨会
     * @date
     * @param agentFac
     * @return
     */
    public Integer updateByPrimaryKeySelective(AgentFac agentFac){
        return mapper.updateByPrimaryKeySelective(agentFac);
    }

    /**通过agentId修改数据
     * @author 廖巨会
     * @date
     * @param
     * @return
     */
    public Integer updateAgentFacAtter(AgentFac agentFac){
        return mapper.updateAgentFacAtter(agentFac);
    }

    @Override
    public List<AgentFac> findAgentFac(Long agentId) {
        return mapper.findAgentFac(agentId);
    }


}
