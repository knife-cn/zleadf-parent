package com.zlead.fplat.service;

import java.util.Map;

import com.zlead.fplat.entity.AgentbandList;
import org.apache.ibatis.annotations.Param;

public interface AgentBandListService {
    /**
     * 新增品牌系列记录
     * @param agentbandList
     * @return
     */
    int insertSelective(AgentbandList agentbandList);
    
    /**
     * 
     * @param factoryId
     * @param agentId
     * @param bKey
     * @param lKey
     * @param mKey
     * @param cKey
     * @param key
     * @return
     */
    Map<String, Object> queryBandListOrModelList(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key,String x);


    /**
     * 通过代理系列，代理商id，工厂id查询当前代理系列是否存在
     */
    AgentbandList queryAgencyList(@Param("agentId") Integer agentId, @Param("factId") Integer factId, @Param("listId")Integer listId);
}
