package com.zlead.fplat.dao;

import com.zlead.fplat.entity.AgentFac;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AgentFacMapper {
    int insert(AgentFac agentFac);

    int queryCountByAgentIdAndFactoryIdAndStatus(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId, @Param("status") Integer status);

    List<Map<String, Object>> findFactoryIdListByAgentId(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId);

    List<Map<String, Object>> findFactoryIdListByAgentAPP(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId);

    List<Integer> findFacByagentId(Long agentId);

    List<Integer> queryByShopIdAll(Long agentId);

    Integer findShopByFactId(Integer factId);

    AgentFac findByAgentId(@Param("id") Integer id);

    List<AgentFac> findFacListByAgentId(@Param("id") Integer id);

    AgentFac findByAgentIdAndGoodsId(@Param("id") Integer id, @Param("goodsId") Integer goodsId);

    AgentFac findByAgentIdAndShopId(@Param("agentId") Integer agentId, @Param("shopId") Integer shopId);

    AgentFac findByAgentIdAndShopId2(@Param("agentId") Integer agentId, @Param("shopId") Integer shopId);

    List<Integer> findIdByFacIds(List<Integer> ids);

    List<Integer> findIdByAgentId(Long agentId);
/**
 * 通过agentId更新t_agent_fac
 * @author 廖巨会
 * @date
 * @param agentFac
 * @return
 */
    Integer updateByPrimaryKeySelective(AgentFac agentFac);

    /**通过agentId修改数据
     * @author 廖巨会
     * @date
     * @param
     * @return
     */
    Integer updateAgentFacAtter(AgentFac agentFac);

    /**
     * 根据ID 查 agentFac
     * @param agentId
     * @return
     */
    List<AgentFac> findAgentFac(Long agentId);

    Integer findFacByMemberId(Long memberId);

    Integer findAgentFacBySysId(Integer sysId);

    List<AgentFac> getAgentFactByMemberId(Long memberId);

    AgentFac findAgentByShopIdAndAgentName(@Param("shopId")Integer shopId,@Param("agentName")String agentName);

    List<AgentFac> findByShopId(Long ownShopid);

    List<AgentFac> findByShopIdByUserIds(@Param("memberId")Long memberId, @Param("userIds")Set<Integer> userIds);

    Set<Integer> findAgentListByUserIds(@Param("userIds")Set<Integer> userIds);
 }
