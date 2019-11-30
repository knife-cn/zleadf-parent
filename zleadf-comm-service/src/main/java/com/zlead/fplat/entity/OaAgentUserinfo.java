package com.zlead.fplat.entity;

public class OaAgentUserinfo {
    /**
     * 字段名称: 用户id .
     * 字段定义: oa_agent_userinfo.user_id
     *
     * @ET
     */
    private Integer userId;

    /**
     * 字段名称: 代理商id .
     * 字段定义: oa_agent_userinfo.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 联系人姓名 .
     * 字段定义: oa_agent_userinfo.user_name
     *
     * @ET
     */
    private String userName;

    /**
     * 字段名称: 联系电话 .
     * 字段定义: oa_agent_userinfo.link_tel
     *
     * @ET
     */
    private String linkTel;

    /**
     * 字段名称: 是否主要联系人 .
     * 字段定义: oa_agent_userinfo.main_user
     *
     * @ET
     */
    private String mainUser;

    /**
     * This method:getUserId
     * oa_agent_userinfo.user_id
     *
     * @return the value of oa_agent_userinfo.user_id
     *
     * @ET
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method:setUserId
     *  oa_agent_userinfo.user_id
     *
     * @param userId the value for oa_agent_userinfo.user_id
     *
     * @ET
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method:getAgentId
     * oa_agent_userinfo.agent_id
     *
     * @return the value of oa_agent_userinfo.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  oa_agent_userinfo.agent_id
     *
     * @param agentId the value for oa_agent_userinfo.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getUserName
     * oa_agent_userinfo.user_name
     *
     * @return the value of oa_agent_userinfo.user_name
     *
     * @ET
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method:setUserName
     *  oa_agent_userinfo.user_name
     *
     * @param userName the value for oa_agent_userinfo.user_name
     *
     * @ET
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method:getLinkTel
     * oa_agent_userinfo.link_tel
     *
     * @return the value of oa_agent_userinfo.link_tel
     *
     * @ET
     */
    public String getLinkTel() {
        return linkTel;
    }

    /**
     * This method:setLinkTel
     *  oa_agent_userinfo.link_tel
     *
     * @param linkTel the value for oa_agent_userinfo.link_tel
     *
     * @ET
     */
    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel == null ? null : linkTel.trim();
    }

    /**
     * This method:getMainUser
     * oa_agent_userinfo.main_user
     *
     * @return the value of oa_agent_userinfo.main_user
     *
     * @ET
     */
    public String getMainUser() {
        return mainUser;
    }

    /**
     * This method:setMainUser
     *  oa_agent_userinfo.main_user
     *
     * @param mainUser the value for oa_agent_userinfo.main_user
     *
     * @ET
     */
    public void setMainUser(String mainUser) {
        this.mainUser = mainUser == null ? null : mainUser.trim();
    }
}