package com.zlead.entity.vo;

import java.util.Date;

public class OaAgentMasBaseInfoVo {

    /**
     * 客户id
     */
    private Integer agentId = 0;

    /**
     * 客户联系人姓名
     */
    private String linkName = "";

    /**
     * 客户头像
     */
    private String headImg = "";

    /**
     * 客户合同名称
     */
    private String agentContract = "";

    /**
     * 合同地址
     */
    private String contractUrl = "";

    /**
     * 合同开始时间
     */
    private Date agentFmdate;

    /**
     * 合同结束时间
     */
    private Date agentTodate;

    /**
     * 客户联系人电话
     */
    private String linkTel = "";

    /**
     * 客户公司名称
     */
    private String agentName = "";

    /**
     * 客户地址
     */
    private String agentAddr = "";

    /**
     * 收货地址
     */
    private String receiveAddr = "";

    /**
     * 客户折扣
     */
    private String agentDiscount = "";

    private String agentDiscountType = "";

    /**
     * Html内容
     */
    private String htmlContent = "";

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getAgentContract() {
        return agentContract;
    }

    public void setAgentContract(String agentContract) {
        this.agentContract = agentContract;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public Date getAgentFmdate() {
        return agentFmdate;
    }

    public void setAgentFmdate(Date agentFmdate) {
        this.agentFmdate = agentFmdate;
    }

    public Date getAgentTodate() {
        return agentTodate;
    }

    public void setAgentTodate(Date agentTodate) {
        this.agentTodate = agentTodate;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentAddr() {
        return agentAddr;
    }

    public void setAgentAddr(String agentAddr) {
        this.agentAddr = agentAddr;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }

    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getAgentDiscount() {
        return agentDiscount;
    }

    public void setAgentDiscount(String agentDiscount) {
        this.agentDiscount = agentDiscount;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getAgentDiscountType() {
        return agentDiscountType;
    }

    public void setAgentDiscountType(String agentDiscountType) {
        this.agentDiscountType = agentDiscountType;
    }
}
