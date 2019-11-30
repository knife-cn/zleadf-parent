package com.zlead.fplat.dao;


import com.zlead.fplat.entity.OaAgentRevinfo;

import java.util.List;

public interface OaAgentRevinfoMapper {

    /**
     * 根据条件查询收货地址信息
     * @param oaAgentRevinfo
     * @return
     */
    List<OaAgentRevinfo> findRevinfo(OaAgentRevinfo oaAgentRevinfo);
}