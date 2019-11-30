package com.zlead.fplat.entity;

import java.util.Set;

public class OaAgentMasRequest {

    /**
     * 会员id
     */
    private String memberId;

    /**
     *代理商级别
     */
    private Integer levelId;

    /**
     *联系人电话
     */
    private String linkTel;

    /**
     *联系人姓名
     */
    private String linkName;

    /**
     * 代理商名称
     */
    private String agentName;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
