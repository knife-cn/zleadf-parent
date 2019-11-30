package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaAgentUserinfo;
import com.zlead.fplat.entity.OaAgentUserinfoExample;
import java.util.List;

public interface OaAgentUserinfoMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_agent_userinfo
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method:insert
     *   oa_agent_userinfo
     *
     * @ET
     */
    int insert(OaAgentUserinfo record);

    /**
     * This method:insertSelective
     *   oa_agent_userinfo
     *
     * @ET
     */
    int insertSelective(OaAgentUserinfo record);

    /**
     * This method:selectByExample
     *   oa_agent_userinfo
     *
     * @ET
     */
    List<OaAgentUserinfo> selectByExample(OaAgentUserinfoExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_agent_userinfo
     *
     * @ET
     */
    OaAgentUserinfo selectByPrimaryKey(Integer userId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_agent_userinfo
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaAgentUserinfo record);

    /**
     * This method:updateByPrimaryKey
     *   oa_agent_userinfo
     *
     * @ET
     */
    int updateByPrimaryKey(OaAgentUserinfo record);
}