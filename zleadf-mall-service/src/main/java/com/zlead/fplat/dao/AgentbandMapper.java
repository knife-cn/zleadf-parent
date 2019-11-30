package com.zlead.fplat.dao;

import com.zlead.entity.vo.AgentBrandListVO;
import com.zlead.fplat.entity.Agentband;
import com.zlead.fplat.entity.AgentbandExample;
import com.zlead.util.page.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AgentbandMapper {
    /**
     * This method:deleteByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     * oa_agent_band
     *
     * @ET
     */
    int insert(Agentband record);

    /**
     * This method:insertSelective
     * oa_agent_band
     *
     * @ET
     */
    int insertSelective(Agentband record);

    /**
     * This method:selectByExample
     * oa_agent_band
     *
     * @ET
     */
    List<Agentband> selectByExample(AgentbandExample example);

    /**
     * This method:selectByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    Agentband selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     * oa_agent_band
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Agentband record);

    /**
     * This method:updateByPrimaryKey
     * oa_agent_band
     *
     * @ET
     */
    int updateByPrimaryKey(Agentband record);

    /*根据工厂ids与代理商  得到该代理商绑定工厂与该代理商代理的品牌*/
    List<Agentband> findBandByAgentId(Long id);

    /**
     * 根据代理商id和关键字检索品牌
     */
    List<Map<String, Object>> findByAgentIdAndKey(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId, @Param("key") String key);

    List<Map<String, Object>> findByAgentIdAndKeyAPP(@Param("factoryId") Long factoryId, @Param("key") String key);

    List<Agentband> selectByAgentFacIds(@Param("agentId") Integer agentId, @Param("facIds") List<Integer> facIds);

    List<Agentband> selectAgentBandByAgentId(Long agentId);

    /**
     * 查询客户授权品牌列表
     * @param agentId
     * @param ownShopid
     * @return
     */
    List<AgentBrandListVO> getAgentBrandList(@Param("agentId") Integer agentId, @Param("shopId") Long ownShopid, PageBounds pageBounds);


    List<Agentband> selectBrandByAgentId(@Param("agentId")Long agentId,@Param("factoryId")Long factoryId);

    /**
     * 根据工厂id和代理商id查询品牌
     * @param agentId
     * @param factId
     * @return
     */
    List<Agentband> getBrandByFctIdAndAgentId(@Param("agentId")Long agentId,@Param("factId")Long factId);

   /* *//**
     * 根据工厂id和代理商id查询品牌
     * @param agentId
     * @param factId
     * @return
     *//*
    List<Agentband> getBrandByFctIdAndAgentId(@Param("agentId")Long agentId,@Param("factId")Long factId);*/
}