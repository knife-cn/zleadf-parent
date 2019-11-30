package com.zlead.fplat.entity;

import java.util.Date;

public class OaMacketCont {
    /**
     * 字段名称: 活动id .
     * 字段定义: oa_macket_cont.cont_id
     *
     * @ET
     */
    private Integer contId;

    /**
     * 字段名称: 活动代码 .
     * 字段定义: oa_macket_cont.cont_code
     *
     * @ET
     */
    private String contCode;

    /**
     * 字段名称: 活动名称 .
     * 字段定义: oa_macket_cont.cont_name
     *
     * @ET
     */
    private String contName;

    /**
     * 字段名称: 活动类型 .
     * 字段定义: oa_macket_cont.cont_type
     *
     * @ET
     */
    private String contType;

    /**
     * 字段名称: 活动有效期 .
     * 字段定义: oa_macket_cont.eff_date
     *
     * @ET
     */
    private Date effDate;

    /**
     * 字段名称: 活动有效期 .
     * 字段定义: oa_macket_cont.exp_date
     *
     * @ET
     */
    private Date expDate;

    /**
     * 字段名称: 活动展示 .
     * 字段定义: oa_macket_cont.terminal
     *
     * @ET
     */
    private String terminal;

    /**
     * 字段名称: 活动标题 .
     * 字段定义: oa_macket_cont.cont_title
     *
     * @ET
     */
    private String contTitle;

    /**
     * 字段名称: 地址 .
     * 字段定义: oa_macket_cont.cont_url
     *
     * @ET
     */
    private String contUrl;

    /**
     * 字段名称: 发布时间 .
     * 字段定义: oa_macket_cont.push_time
     *
     * @ET
     */
    private Date pushTime;

    /**
     * 字段名称: 发布人 .
     * 字段定义: oa_macket_cont.push_user
     *
     * @ET
     */
    private Integer pushUser;

    /**
     * 字段名称: 状态 .
     * 字段定义: oa_macket_cont.cont_state
     *
     * @ET
     */
    private String contState;

    /**
     * 字段名称: 系统 .
     * 字段定义: oa_macket_cont.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: oa_macket_cont.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: oa_macket_cont.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: oa_macket_cont.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: oa_macket_cont.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * 字段名称: 静态内容 .
     * 字段定义: oa_macket_cont.static_cont
     *
     * @ET
     */
    private String staticCont;

    /**
     * This method:getContId
     * oa_macket_cont.cont_id
     *
     * @return the value of oa_macket_cont.cont_id
     *
     * @ET
     */
    public Integer getContId() {
        return contId;
    }

    /**
     * This method:setContId
     *  oa_macket_cont.cont_id
     *
     * @param contId the value for oa_macket_cont.cont_id
     *
     * @ET
     */
    public void setContId(Integer contId) {
        this.contId = contId;
    }

    /**
     * This method:getContCode
     * oa_macket_cont.cont_code
     *
     * @return the value of oa_macket_cont.cont_code
     *
     * @ET
     */
    public String getContCode() {
        return contCode;
    }

    /**
     * This method:setContCode
     *  oa_macket_cont.cont_code
     *
     * @param contCode the value for oa_macket_cont.cont_code
     *
     * @ET
     */
    public void setContCode(String contCode) {
        this.contCode = contCode == null ? null : contCode.trim();
    }

    /**
     * This method:getContName
     * oa_macket_cont.cont_name
     *
     * @return the value of oa_macket_cont.cont_name
     *
     * @ET
     */
    public String getContName() {
        return contName;
    }

    /**
     * This method:setContName
     *  oa_macket_cont.cont_name
     *
     * @param contName the value for oa_macket_cont.cont_name
     *
     * @ET
     */
    public void setContName(String contName) {
        this.contName = contName == null ? null : contName.trim();
    }

    /**
     * This method:getContType
     * oa_macket_cont.cont_type
     *
     * @return the value of oa_macket_cont.cont_type
     *
     * @ET
     */
    public String getContType() {
        return contType;
    }

    /**
     * This method:setContType
     *  oa_macket_cont.cont_type
     *
     * @param contType the value for oa_macket_cont.cont_type
     *
     * @ET
     */
    public void setContType(String contType) {
        this.contType = contType == null ? null : contType.trim();
    }

    /**
     * This method:getEffDate
     * oa_macket_cont.eff_date
     *
     * @return the value of oa_macket_cont.eff_date
     *
     * @ET
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * This method:setEffDate
     *  oa_macket_cont.eff_date
     *
     * @param effDate the value for oa_macket_cont.eff_date
     *
     * @ET
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * This method:getExpDate
     * oa_macket_cont.exp_date
     *
     * @return the value of oa_macket_cont.exp_date
     *
     * @ET
     */
    public Date getExpDate() {
        return expDate;
    }

    /**
     * This method:setExpDate
     *  oa_macket_cont.exp_date
     *
     * @param expDate the value for oa_macket_cont.exp_date
     *
     * @ET
     */
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    /**
     * This method:getTerminal
     * oa_macket_cont.terminal
     *
     * @return the value of oa_macket_cont.terminal
     *
     * @ET
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * This method:setTerminal
     *  oa_macket_cont.terminal
     *
     * @param terminal the value for oa_macket_cont.terminal
     *
     * @ET
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    /**
     * This method:getContTitle
     * oa_macket_cont.cont_title
     *
     * @return the value of oa_macket_cont.cont_title
     *
     * @ET
     */
    public String getContTitle() {
        return contTitle;
    }

    /**
     * This method:setContTitle
     *  oa_macket_cont.cont_title
     *
     * @param contTitle the value for oa_macket_cont.cont_title
     *
     * @ET
     */
    public void setContTitle(String contTitle) {
        this.contTitle = contTitle == null ? null : contTitle.trim();
    }

    /**
     * This method:getContUrl
     * oa_macket_cont.cont_url
     *
     * @return the value of oa_macket_cont.cont_url
     *
     * @ET
     */
    public String getContUrl() {
        return contUrl;
    }

    /**
     * This method:setContUrl
     *  oa_macket_cont.cont_url
     *
     * @param contUrl the value for oa_macket_cont.cont_url
     *
     * @ET
     */
    public void setContUrl(String contUrl) {
        this.contUrl = contUrl == null ? null : contUrl.trim();
    }

    /**
     * This method:getPushTime
     * oa_macket_cont.push_time
     *
     * @return the value of oa_macket_cont.push_time
     *
     * @ET
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * This method:setPushTime
     *  oa_macket_cont.push_time
     *
     * @param pushTime the value for oa_macket_cont.push_time
     *
     * @ET
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * This method:getPushUser
     * oa_macket_cont.push_user
     *
     * @return the value of oa_macket_cont.push_user
     *
     * @ET
     */
    public Integer getPushUser() {
        return pushUser;
    }

    /**
     * This method:setPushUser
     *  oa_macket_cont.push_user
     *
     * @param pushUser the value for oa_macket_cont.push_user
     *
     * @ET
     */
    public void setPushUser(Integer pushUser) {
        this.pushUser = pushUser;
    }

    /**
     * This method:getContState
     * oa_macket_cont.cont_state
     *
     * @return the value of oa_macket_cont.cont_state
     *
     * @ET
     */
    public String getContState() {
        return contState;
    }

    /**
     * This method:setContState
     *  oa_macket_cont.cont_state
     *
     * @param contState the value for oa_macket_cont.cont_state
     *
     * @ET
     */
    public void setContState(String contState) {
        this.contState = contState == null ? null : contState.trim();
    }

    /**
     * This method:getSysId
     * oa_macket_cont.sys_id
     *
     * @return the value of oa_macket_cont.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  oa_macket_cont.sys_id
     *
     * @param sysId the value for oa_macket_cont.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * oa_macket_cont.creator
     *
     * @return the value of oa_macket_cont.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  oa_macket_cont.creator
     *
     * @param creator the value for oa_macket_cont.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * oa_macket_cont.modifier
     *
     * @return the value of oa_macket_cont.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  oa_macket_cont.modifier
     *
     * @param modifier the value for oa_macket_cont.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * oa_macket_cont.create_time
     *
     * @return the value of oa_macket_cont.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  oa_macket_cont.create_time
     *
     * @param createTime the value for oa_macket_cont.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * oa_macket_cont.modify_time
     *
     * @return the value of oa_macket_cont.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  oa_macket_cont.modify_time
     *
     * @param modifyTime the value for oa_macket_cont.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method:getStaticCont
     * oa_macket_cont.static_cont
     *
     * @return the value of oa_macket_cont.static_cont
     *
     * @ET
     */
    public String getStaticCont() {
        return staticCont;
    }

    /**
     * This method:setStaticCont
     *  oa_macket_cont.static_cont
     *
     * @param staticCont the value for oa_macket_cont.static_cont
     *
     * @ET
     */
    public void setStaticCont(String staticCont) {
        this.staticCont = staticCont == null ? null : staticCont.trim();
    }
}