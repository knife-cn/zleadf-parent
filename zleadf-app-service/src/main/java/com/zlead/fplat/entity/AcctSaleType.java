package com.zlead.fplat.entity;

import java.util.Date;

public class AcctSaleType {
    /**
     * 字段名称: id .
     * 字段定义: acct_sale_type.type_id
     *
     * @ET
     */
    private Integer typeId;

    /**
     * 字段名称: 类型代码 .
     * 字段定义: acct_sale_type.type_code
     *
     * @ET
     */
    private String typeCode;

    /**
     * 字段名称: 类型名称 .
     * 字段定义: acct_sale_type.type_name
     *
     * @ET
     */
    private String typeName;

    /**
     * 字段名称: 状态 .
     * 字段定义: acct_sale_type.type_state
     *
     * @ET
     */
    private String typeState;

    /**
     * 字段名称: 备注 .
     * 字段定义: acct_sale_type.type_desc
     *
     * @ET
     */
    private String typeDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: acct_sale_type.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: acct_sale_type.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: acct_sale_type.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: acct_sale_type.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改人编号 .
     * 字段定义: acct_sale_type.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: acct_sale_type.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getTypeId
     * acct_sale_type.type_id
     *
     * @return the value of acct_sale_type.type_id
     *
     * @ET
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method:setTypeId
     *  acct_sale_type.type_id
     *
     * @param typeId the value for acct_sale_type.type_id
     *
     * @ET
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method:getTypeCode
     * acct_sale_type.type_code
     *
     * @return the value of acct_sale_type.type_code
     *
     * @ET
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method:setTypeCode
     *  acct_sale_type.type_code
     *
     * @param typeCode the value for acct_sale_type.type_code
     *
     * @ET
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method:getTypeName
     * acct_sale_type.type_name
     *
     * @return the value of acct_sale_type.type_name
     *
     * @ET
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method:setTypeName
     *  acct_sale_type.type_name
     *
     * @param typeName the value for acct_sale_type.type_name
     *
     * @ET
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method:getTypeState
     * acct_sale_type.type_state
     *
     * @return the value of acct_sale_type.type_state
     *
     * @ET
     */
    public String getTypeState() {
        return typeState;
    }

    /**
     * This method:setTypeState
     *  acct_sale_type.type_state
     *
     * @param typeState the value for acct_sale_type.type_state
     *
     * @ET
     */
    public void setTypeState(String typeState) {
        this.typeState = typeState == null ? null : typeState.trim();
    }

    /**
     * This method:getTypeDesc
     * acct_sale_type.type_desc
     *
     * @return the value of acct_sale_type.type_desc
     *
     * @ET
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * This method:setTypeDesc
     *  acct_sale_type.type_desc
     *
     * @param typeDesc the value for acct_sale_type.type_desc
     *
     * @ET
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }

    /**
     * This method:getOrgId
     * acct_sale_type.org_id
     *
     * @return the value of acct_sale_type.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  acct_sale_type.org_id
     *
     * @param orgId the value for acct_sale_type.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * acct_sale_type.sys_id
     *
     * @return the value of acct_sale_type.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  acct_sale_type.sys_id
     *
     * @param sysId the value for acct_sale_type.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * acct_sale_type.creator
     *
     * @return the value of acct_sale_type.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  acct_sale_type.creator
     *
     * @param creator the value for acct_sale_type.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getCreateTime
     * acct_sale_type.create_time
     *
     * @return the value of acct_sale_type.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  acct_sale_type.create_time
     *
     * @param createTime the value for acct_sale_type.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifier
     * acct_sale_type.modifier
     *
     * @return the value of acct_sale_type.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  acct_sale_type.modifier
     *
     * @param modifier the value for acct_sale_type.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getModifyTime
     * acct_sale_type.modify_time
     *
     * @return the value of acct_sale_type.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  acct_sale_type.modify_time
     *
     * @param modifyTime the value for acct_sale_type.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}