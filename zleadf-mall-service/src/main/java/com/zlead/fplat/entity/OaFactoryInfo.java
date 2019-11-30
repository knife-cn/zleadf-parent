package com.zlead.fplat.entity;

import java.util.Date;

public class OaFactoryInfo {
    /**
     * 字段名称: id .
     * 字段定义: oa_factory_info.fact_id
     *
     * @ET
     */
    private Integer factId;

    /**
     * 字段名称: 编号 .
     * 字段定义: oa_factory_info.fact_no
     *
     * @ET
     */
    private String factNo;

    /**
     * 字段名称: 工厂名称 .
     * 字段定义: oa_factory_info.fact_name
     *
     * @ET
     */
    private String factName;
    /**
     * 字段名称: 工厂名称 (后端设置的工厂名称前端展示使用这个).
     * 字段定义: oa_factory_info.fact_name
     *
     * @ET
     */
    private String factName2;

    /**
     * 字段名称: 状态 .
     * 字段定义: oa_factory_info.fact_state
     *
     * @ET
     */
    private String factState;

    /**
     * 字段名称: 联系电话 .
     * 字段定义: oa_factory_info.contact_no
     *
     * @ET
     */
    private String contactNo;

    /**
     * 字段名称: 邮箱 .
     * 字段定义: oa_factory_info.email
     *
     * @ET
     */
    private String email;

    /**
     * 字段名称: 联系人1 .
     * 字段定义: oa_factory_info.contact_man1
     *
     * @ET
     */
    private String contactMan1;

    /**
     * 字段名称: 联系人2 .
     * 字段定义: oa_factory_info.contact_man2
     *
     * @ET
     */
    private String contactMan2;

    /**
     * 字段名称: 工厂简介 .
     * 字段定义: oa_factory_info.fact_desc
     *
     * @ET
     */
    private String factDesc;

    /**
     * 字段名称: 工厂实力认证 .
     * 字段定义: oa_factory_info.powerpic_path
     *
     * @ET
     */
    private String powerpicPath;

    /**
     * 字段名称: 工厂风貌 .
     * 字段定义: oa_factory_info.apapic_path
     *
     * @ET
     */
    private String apapicPath;

    /**
     * 字段名称: 店铺id t_shop.id.
     * 字段定义: oa_factory_info.shop_id
     *
     * @ET
     */
    private Integer shopId;

    /**
     *
     * 字段名称: 创建人 .
     * 字段定义: oa_factory_info.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: oa_factory_info.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改人编号 .
     * 字段定义: oa_factory_info.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: oa_factory_info.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getFactId
     * oa_factory_info.fact_id
     *
     * @return the value of oa_factory_info.fact_id
     * @ET
     */
    public Integer getFactId() {
        return factId;
    }

    /**
     * This method:setFactId
     * oa_factory_info.fact_id
     *
     * @param factId the value for oa_factory_info.fact_id
     * @ET
     */
    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    /**
     * This method:getFactNo
     * oa_factory_info.fact_no
     *
     * @return the value of oa_factory_info.fact_no
     * @ET
     */
    public String getFactNo() {
        return factNo;
    }

    /**
     * This method:setFactNo
     * oa_factory_info.fact_no
     *
     * @param factNo the value for oa_factory_info.fact_no
     * @ET
     */
    public void setFactNo(String factNo) {
        this.factNo = factNo == null ? null : factNo.trim();
    }
    
    public void OaFactoryInfo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * This method:getFactName
     * oa_factory_info.fact_name
     *
     * @return the value of oa_factory_info.fact_name
     * @ET
     */
    public String getFactName() {
        return factName;
    }

    /**
     * This method:setFactName
     * oa_factory_info.fact_name
     *
     * @param factName the value for oa_factory_info.fact_name
     * @ET
     */
    public void setFactName(String factName) {
        this.factName = factName == null ? null : factName.trim();
    }

    /**
     * This method:getFactState
     * oa_factory_info.fact_state
     *
     * @return the value of oa_factory_info.fact_state
     * @ET
     */
    public String getFactState() {
        return factState;
    }

    /**
     * This method:setFactState
     * oa_factory_info.fact_state
     *
     * @param factState the value for oa_factory_info.fact_state
     * @ET
     */
    public void setFactState(String factState) {
        this.factState = factState == null ? null : factState.trim();
    }

    /**
     * This method:getContactNo
     * oa_factory_info.contact_no
     *
     * @return the value of oa_factory_info.contact_no
     * @ET
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * This method:setContactNo
     * oa_factory_info.contact_no
     *
     * @param contactNo the value for oa_factory_info.contact_no
     * @ET
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo == null ? null : contactNo.trim();
    }

    /**
     * This method:getEmail
     * oa_factory_info.email
     *
     * @return the value of oa_factory_info.email
     * @ET
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method:setEmail
     * oa_factory_info.email
     *
     * @param email the value for oa_factory_info.email
     * @ET
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method:getContactMan1
     * oa_factory_info.contact_man1
     *
     * @return the value of oa_factory_info.contact_man1
     * @ET
     */
    public String getContactMan1() {
        return contactMan1;
    }

    /**
     * This method:setContactMan1
     * oa_factory_info.contact_man1
     *
     * @param contactMan1 the value for oa_factory_info.contact_man1
     * @ET
     */
    public void setContactMan1(String contactMan1) {
        this.contactMan1 = contactMan1 == null ? null : contactMan1.trim();
    }

    /**
     * This method:getContactMan2
     * oa_factory_info.contact_man2
     *
     * @return the value of oa_factory_info.contact_man2
     * @ET
     */
    public String getContactMan2() {
        return contactMan2;
    }

    /**
     * This method:setContactMan2
     * oa_factory_info.contact_man2
     *
     * @param contactMan2 the value for oa_factory_info.contact_man2
     * @ET
     */
    public void setContactMan2(String contactMan2) {
        this.contactMan2 = contactMan2 == null ? null : contactMan2.trim();
    }

    /**
     * This method:getFactDesc
     * oa_factory_info.fact_desc
     *
     * @return the value of oa_factory_info.fact_desc
     * @ET
     */
    public String getFactDesc() {
        return factDesc;
    }

    /**
     * This method:setFactDesc
     * oa_factory_info.fact_desc
     *
     * @param factDesc the value for oa_factory_info.fact_desc
     * @ET
     */
    public void setFactDesc(String factDesc) {
        this.factDesc = factDesc == null ? null : factDesc.trim();
    }

    /**
     * This method:getPowerpicPath
     * oa_factory_info.powerpic_path
     *
     * @return the value of oa_factory_info.powerpic_path
     * @ET
     */
    public String getPowerpicPath() {
        return powerpicPath;
    }

    /**
     * This method:setPowerpicPath
     * oa_factory_info.powerpic_path
     *
     * @param powerpicPath the value for oa_factory_info.powerpic_path
     * @ET
     */
    public void setPowerpicPath(String powerpicPath) {
        this.powerpicPath = powerpicPath == null ? null : powerpicPath.trim();
    }

    /**
     * This method:getApapicPath
     * oa_factory_info.apapic_path
     *
     * @return the value of oa_factory_info.apapic_path
     * @ET
     */
    public String getApapicPath() {
        return apapicPath;
    }

    /**
     * This method:setApapicPath
     * oa_factory_info.apapic_path
     *
     * @param apapicPath the value for oa_factory_info.apapic_path
     * @ET
     */
    public void setApapicPath(String apapicPath) {
        this.apapicPath = apapicPath == null ? null : apapicPath.trim();
    }

    /**
     * This method:getSysId
     * oa_factory_info.sys_id
     *
     * @return the value of oa_factory_info.sys_id
     * @ET
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method:setSysId
     * oa_factory_info.sys_id
     *
     * @param shopId the value for oa_factory_info.sys_id
     * @ET
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getCreator
     * oa_factory_info.creator
     *
     * @return the value of oa_factory_info.creator
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     * oa_factory_info.creator
     *
     * @param creator the value for oa_factory_info.creator
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getCreateTime
     * oa_factory_info.create_time
     *
     * @return the value of oa_factory_info.create_time
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     * oa_factory_info.create_time
     *
     * @param createTime the value for oa_factory_info.create_time
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifier
     * oa_factory_info.modifier
     *
     * @return the value of oa_factory_info.modifier
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     * oa_factory_info.modifier
     *
     * @param modifier the value for oa_factory_info.modifier
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getModifyTime
     * oa_factory_info.modify_time
     *
     * @return the value of oa_factory_info.modify_time
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     * oa_factory_info.modify_time
     *
     * @param modifyTime the value for oa_factory_info.modify_time
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getFactName2() {
        return factName2;
    }

    public void setFactName2(String factName2) {
        this.factName2 = factName2;
    }
}