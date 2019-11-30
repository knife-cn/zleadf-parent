package com.zlead.fplat.entity;

public class AgentAddress {
    /**
     * 字段名称: id .
     * 字段定义: oa_agent_revinfo.rev_id
     *
     * @ET
     */
    private Integer revId;

    /**
     * 字段名称: 代理商id .
     * 字段定义: oa_agent_revinfo.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 收货人姓名 .
     * 字段定义: oa_agent_revinfo.rev_name
     *
     * @ET
     */
    private String revName;

    /**
     * 字段名称: 收货电话 .
     * 字段定义: oa_agent_revinfo.rev_tel
     *
     * @ET
     */
    private String revTel;

    /**
     * 字段名称: 省 .
     * 字段定义: oa_agent_revinfo.rev_province
     *
     * @ET
     */
    private String revProvince;

    /**
     * 字段名称: 市 .
     * 字段定义: oa_agent_revinfo.rev_city
     *
     * @ET
     */
    private String revCity;

    /**
     * 字段名称: 县 .
     * 字段定义: oa_agent_revinfo.rev_county
     *
     * @ET
     */
    private String revCounty;

    /**
     * 字段名称: 具体地址 .
     * 字段定义: oa_agent_revinfo.rev_addr
     *
     * @ET
     */
    private String revAddr;

    /**
     * 字段名称: 是否默认 是否默认（1:是;0:否） .
     * 字段定义: oa_agent_revinfo.rev_default
     *
     * @ET
     */
    private String revDefault;

    /**
     * 工厂ID
     */
    private Integer sysId;

    /**
     * 是否是工厂端创建  0:不是 1：是
     */
    private String isFact;

    /**
     * 关联t_member_address主键ID
     */
    private Integer memberAddrId;

    public Integer getMemberAddrId() {
        return memberAddrId;
    }

    public void setMemberAddrId(Integer memberAddrId) {
        this.memberAddrId = memberAddrId;
    }

    public String getIsFact() {
        return isFact;
    }

    public void setIsFact(String isFact) {
        this.isFact = isFact;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }


    /**
     * This method:getRevId
     * oa_agent_revinfo.rev_id
     *
     * @return the value of oa_agent_revinfo.rev_id
     *
     * @ET
     */
    public Integer getRevId() {
        return revId;
    }

    /**
     * This method:setRevId
     *  oa_agent_revinfo.rev_id
     *
     * @param revId the value for oa_agent_revinfo.rev_id
     *
     * @ET
     */
    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    /**
     * This method:getAgentId
     * oa_agent_revinfo.agent_id
     *
     * @return the value of oa_agent_revinfo.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  oa_agent_revinfo.agent_id
     *
     * @param agentId the value for oa_agent_revinfo.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getRevName
     * oa_agent_revinfo.rev_name
     *
     * @return the value of oa_agent_revinfo.rev_name
     *
     * @ET
     */
    public String getRevName() {
        return revName;
    }

    /**
     * This method:setRevName
     *  oa_agent_revinfo.rev_name
     *
     * @param revName the value for oa_agent_revinfo.rev_name
     *
     * @ET
     */
    public void setRevName(String revName) {
        this.revName = revName == null ? null : revName.trim();
    }

    /**
     * This method:getRevTel
     * oa_agent_revinfo.rev_tel
     *
     * @return the value of oa_agent_revinfo.rev_tel
     *
     * @ET
     */
    public String getRevTel() {
        return revTel;
    }

    /**
     * This method:setRevTel
     *  oa_agent_revinfo.rev_tel
     *
     * @param revTel the value for oa_agent_revinfo.rev_tel
     *
     * @ET
     */
    public void setRevTel(String revTel) {
        this.revTel = revTel == null ? null : revTel.trim();
    }

    /**
     * This method:getRevProvince
     * oa_agent_revinfo.rev_province
     *
     * @return the value of oa_agent_revinfo.rev_province
     *
     * @ET
     */
    public String getRevProvince() {
        return revProvince;
    }

    /**
     * This method:setRevProvince
     *  oa_agent_revinfo.rev_province
     *
     * @param revProvince the value for oa_agent_revinfo.rev_province
     *
     * @ET
     */
    public void setRevProvince(String revProvince) {
        this.revProvince = revProvince == null ? null : revProvince.trim();
    }

    /**
     * This method:getRevCity
     * oa_agent_revinfo.rev_city
     *
     * @return the value of oa_agent_revinfo.rev_city
     *
     * @ET
     */
    public String getRevCity() {
        return revCity;
    }

    /**
     * This method:setRevCity
     *  oa_agent_revinfo.rev_city
     *
     * @param revCity the value for oa_agent_revinfo.rev_city
     *
     * @ET
     */
    public void setRevCity(String revCity) {
        this.revCity = revCity == null ? null : revCity.trim();
    }

    /**
     * This method:getRevCounty
     * oa_agent_revinfo.rev_county
     *
     * @return the value of oa_agent_revinfo.rev_county
     *
     * @ET
     */
    public String getRevCounty() {
        return revCounty;
    }

    /**
     * This method:setRevCounty
     *  oa_agent_revinfo.rev_county
     *
     * @param revCounty the value for oa_agent_revinfo.rev_county
     *
     * @ET
     */
    public void setRevCounty(String revCounty) {
        this.revCounty = revCounty == null ? null : revCounty.trim();
    }

    /**
     * This method:getRevAddr
     * oa_agent_revinfo.rev_addr
     *
     * @return the value of oa_agent_revinfo.rev_addr
     *
     * @ET
     */
    public String getRevAddr() {
        return revAddr;
    }

    /**
     * This method:setRevAddr
     *  oa_agent_revinfo.rev_addr
     *
     * @param revAddr the value for oa_agent_revinfo.rev_addr
     *
     * @ET
     */
    public void setRevAddr(String revAddr) {
        this.revAddr = revAddr == null ? null : revAddr.trim();
    }

    /**
     * This method:getRevDefault
     * oa_agent_revinfo.rev_default
     *
     * @return the value of oa_agent_revinfo.rev_default
     *
     * @ET
     */
    public String getRevDefault() {
        return revDefault;
    }

    /**
     * This method:setRevDefault
     *  oa_agent_revinfo.rev_default
     *
     * @param revDefault the value for oa_agent_revinfo.rev_default
     *
     * @ET
     */
    public void setRevDefault(String revDefault) {
        this.revDefault = revDefault == null ? null : revDefault.trim();
    }

    @Override
    public String toString() {
        return "AgentAddress{" +
                "revId=" + revId +
                ", agentId=" + agentId +
                ", revName='" + revName + '\'' +
                ", revTel='" + revTel + '\'' +
                ", revProvince='" + revProvince + '\'' +
                ", revCity='" + revCity + '\'' +
                ", revCounty='" + revCounty + '\'' +
                ", revAddr='" + revAddr + '\'' +
                ", revDefault='" + revDefault + '\'' +
                '}';
    }
}