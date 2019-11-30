package com.zlead.fplat.entity;

import java.util.Date;

public class BaseShipType {
    /**
     * 字段名称: id .
     * 字段定义: base_ship_type.type_id
     *
     * @ET
     */
    private Integer typeId;

    /**
     * 字段名称: 类型代码 .
     * 字段定义: base_ship_type.type_code
     *
     * @ET
     */
    private String typeCode;

    /**
     * 字段名称: 配送方式名称 .
     * 字段定义: base_ship_type.type_name
     *
     * @ET
     */
    private String typeName;

    /**
     * 字段名称: 适应供应商级别 适应供应商级别：checksql多选 .
     * 字段定义: base_ship_type.type_level
     *
     * @ET
     */
    private String typeLevel;

    /**
     * 字段名称: 状态 .
     * 字段定义: base_ship_type.type_state
     *
     * @ET
     */
    private String typeState;

    /**
     * 字段名称: 备注 .
     * 字段定义: base_ship_type.type_desc
     *
     * @ET
     */
    private String typeDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: base_ship_type.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: base_ship_type.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: base_ship_type.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: base_ship_type.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改人编号 .
     * 字段定义: base_ship_type.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: base_ship_type.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * 字段名称:  .
     * 字段定义: base_ship_type.operation
     *
     * @ET
     */
    private String operation;

    /**
     * This method:getTypeId
     * base_ship_type.type_id
     *
     * @return the value of base_ship_type.type_id
     *
     * @ET
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method:setTypeId
     *  base_ship_type.type_id
     *
     * @param typeId the value for base_ship_type.type_id
     *
     * @ET
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method:getTypeCode
     * base_ship_type.type_code
     *
     * @return the value of base_ship_type.type_code
     *
     * @ET
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * This method:setTypeCode
     *  base_ship_type.type_code
     *
     * @param typeCode the value for base_ship_type.type_code
     *
     * @ET
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    /**
     * This method:getTypeName
     * base_ship_type.type_name
     *
     * @return the value of base_ship_type.type_name
     *
     * @ET
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method:setTypeName
     *  base_ship_type.type_name
     *
     * @param typeName the value for base_ship_type.type_name
     *
     * @ET
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    /**
     * This method:getTypeLevel
     * base_ship_type.type_level
     *
     * @return the value of base_ship_type.type_level
     *
     * @ET
     */
    public String getTypeLevel() {
        return typeLevel;
    }

    /**
     * This method:setTypeLevel
     *  base_ship_type.type_level
     *
     * @param typeLevel the value for base_ship_type.type_level
     *
     * @ET
     */
    public void setTypeLevel(String typeLevel) {
        this.typeLevel = typeLevel == null ? null : typeLevel.trim();
    }

    /**
     * This method:getTypeState
     * base_ship_type.type_state
     *
     * @return the value of base_ship_type.type_state
     *
     * @ET
     */
    public String getTypeState() {
        return typeState;
    }

    /**
     * This method:setTypeState
     *  base_ship_type.type_state
     *
     * @param typeState the value for base_ship_type.type_state
     *
     * @ET
     */
    public void setTypeState(String typeState) {
        this.typeState = typeState == null ? null : typeState.trim();
    }

    /**
     * This method:getTypeDesc
     * base_ship_type.type_desc
     *
     * @return the value of base_ship_type.type_desc
     *
     * @ET
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * This method:setTypeDesc
     *  base_ship_type.type_desc
     *
     * @param typeDesc the value for base_ship_type.type_desc
     *
     * @ET
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }

    /**
     * This method:getOrgId
     * base_ship_type.org_id
     *
     * @return the value of base_ship_type.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  base_ship_type.org_id
     *
     * @param orgId the value for base_ship_type.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * base_ship_type.sys_id
     *
     * @return the value of base_ship_type.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  base_ship_type.sys_id
     *
     * @param sysId the value for base_ship_type.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * base_ship_type.creator
     *
     * @return the value of base_ship_type.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  base_ship_type.creator
     *
     * @param creator the value for base_ship_type.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getCreateTime
     * base_ship_type.create_time
     *
     * @return the value of base_ship_type.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  base_ship_type.create_time
     *
     * @param createTime the value for base_ship_type.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifier
     * base_ship_type.modifier
     *
     * @return the value of base_ship_type.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  base_ship_type.modifier
     *
     * @param modifier the value for base_ship_type.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getModifyTime
     * base_ship_type.modify_time
     *
     * @return the value of base_ship_type.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  base_ship_type.modify_time
     *
     * @param modifyTime the value for base_ship_type.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method:getOperation
     * base_ship_type.operation
     *
     * @return the value of base_ship_type.operation
     *
     * @ET
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method:setOperation
     *  base_ship_type.operation
     *
     * @param operation the value for base_ship_type.operation
     *
     * @ET
     */
    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }
}