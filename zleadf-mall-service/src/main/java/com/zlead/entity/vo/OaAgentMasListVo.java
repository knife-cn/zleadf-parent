package com.zlead.entity.vo;

public class OaAgentMasListVo{

    /**
     * 客户id
     */
    private Integer agentId = 0;

    /**
     * 工厂id
     */
    private Integer factoryId = 0;

    private Integer shopId;//店铺ID

    /**
     * 客户级别
     */
    private String agentLevel = "";

    /**
     * 客户名称
     */
    private String agentName = "";

    /**
     * 客户联系人姓名
     */
    private String linkName = "";

    /**
     * 客户地址
     */
    private String agentAddr = "";


    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getAgentAddr() {
        return agentAddr;
    }

    public void setAgentAddr(String agentAddr) {
        this.agentAddr = agentAddr;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
