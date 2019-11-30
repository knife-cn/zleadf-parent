package com.zlead.fplat.dao;


import com.zlead.fplat.entity.OaAgentRevinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OaAgentRevinfoMapper {

    /**
     * 根据条件查询收货地址信息
     * @param oaAgentRevinfo
     * @return
     */
    List<OaAgentRevinfo> findRevinfo(OaAgentRevinfo oaAgentRevinfo);


    /**
     * 根据代理人ID和店铺ID查询地址信息
     * @param agentId
     * @param shopId
     * @return
     */
    List<OaAgentRevinfo> findRevinfoByAgentAndShop(@Param("agentId") Number agentId,@Param("shopId") Number shopId);

    /**
     * 根据ID查询地址信息
     * @param id
     * @return
     */
    OaAgentRevinfo findRevinfoById(@Param("id") Number id);
}