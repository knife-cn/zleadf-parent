package com.zlead.fplat.entity;

import java.util.Date;

public class BaseWarehouse {
    /**
     * 字段名称: 仓库id .
     * 字段定义: base_warehouse.wh_id
     *
     * @ET
     */
    private Integer whId;

    /**
     * 字段名称: 编号 .
     * 字段定义: base_warehouse.wh_no
     *
     * @ET
     */
    private String whNo;

    /**
     * 字段名称: 名称 .
     * 字段定义: base_warehouse.wh_name
     *
     * @ET
     */
    private String whName;

    /**
     * 字段名称: 库位管理 .
     * 字段定义: base_warehouse.loc_flag
     *
     * @ET
     */
    private String locFlag;

    /**
     * 字段名称: 托盘管理 .
     * 字段定义: base_warehouse.box_flag
     *
     * @ET
     */
    private String boxFlag;

    /**
     * 字段名称: 批次管理 .
     * 字段定义: base_warehouse.batch_flag
     *
     * @ET
     */
    private String batchFlag;

    /**
     * 字段名称: 单件管理 .
     * 字段定义: base_warehouse.sn_flag
     *
     * @ET
     */
    private String snFlag;

    /**
     * 字段名称: 管理员 管理员：check多选 .
     * 字段定义: base_warehouse.mgr_user
     *
     * @ET
     */
    private String mgrUser;

    /**
     * 字段名称: 管理员 管理员：check多选 .
     * 字段定义: base_warehouse.mgr_user
     *
     * @ET
     */
    private String mgrUserid;

    /**
     * 字段名称: 状态 .
     * 字段定义: base_warehouse.wh_state
     *
     * @ET
     */
    private String whState;

    /**
     * 字段名称: 备注 .
     * 字段定义: base_warehouse.wh_desc
     *
     * @ET
     */
    private String whDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: base_warehouse.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: base_warehouse.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: base_warehouse.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: base_warehouse.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称:  .
     * 字段定义: base_warehouse.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称:  .
     * 字段定义: base_warehouse.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getWhId
     * base_warehouse.wh_id
     *
     * @return the value of base_warehouse.wh_id
     *
     * @ET
     */
    public Integer getWhId() {
        return whId;
    }

    /**
     * This method:setWhId
     *  base_warehouse.wh_id
     *
     * @param whId the value for base_warehouse.wh_id
     *
     * @ET
     */
    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    /**
     * This method:getWhNo
     * base_warehouse.wh_no
     *
     * @return the value of base_warehouse.wh_no
     *
     * @ET
     */
    public String getWhNo() {
        return whNo;
    }

    /**
     * This method:setWhNo
     *  base_warehouse.wh_no
     *
     * @param whNo the value for base_warehouse.wh_no
     *
     * @ET
     */
    public void setWhNo(String whNo) {
        this.whNo = whNo == null ? null : whNo.trim();
    }

    /**
     * This method:getWhName
     * base_warehouse.wh_name
     *
     * @return the value of base_warehouse.wh_name
     *
     * @ET
     */
    public String getWhName() {
        return whName;
    }

    /**
     * This method:setWhName
     *  base_warehouse.wh_name
     *
     * @param whName the value for base_warehouse.wh_name
     *
     * @ET
     */
    public void setWhName(String whName) {
        this.whName = whName == null ? null : whName.trim();
    }

    /**
     * This method:getLocFlag
     * base_warehouse.loc_flag
     *
     * @return the value of base_warehouse.loc_flag
     *
     * @ET
     */
    public String getLocFlag() {
        return locFlag;
    }

    /**
     * This method:setLocFlag
     *  base_warehouse.loc_flag
     *
     * @param locFlag the value for base_warehouse.loc_flag
     *
     * @ET
     */
    public void setLocFlag(String locFlag) {
        this.locFlag = locFlag == null ? null : locFlag.trim();
    }

    /**
     * This method:getBoxFlag
     * base_warehouse.box_flag
     *
     * @return the value of base_warehouse.box_flag
     *
     * @ET
     */
    public String getBoxFlag() {
        return boxFlag;
    }

    /**
     * This method:setBoxFlag
     *  base_warehouse.box_flag
     *
     * @param boxFlag the value for base_warehouse.box_flag
     *
     * @ET
     */
    public void setBoxFlag(String boxFlag) {
        this.boxFlag = boxFlag == null ? null : boxFlag.trim();
    }

    /**
     * This method:getBatchFlag
     * base_warehouse.batch_flag
     *
     * @return the value of base_warehouse.batch_flag
     *
     * @ET
     */
    public String getBatchFlag() {
        return batchFlag;
    }

    /**
     * This method:setBatchFlag
     *  base_warehouse.batch_flag
     *
     * @param batchFlag the value for base_warehouse.batch_flag
     *
     * @ET
     */
    public void setBatchFlag(String batchFlag) {
        this.batchFlag = batchFlag == null ? null : batchFlag.trim();
    }

    /**
     * This method:getSnFlag
     * base_warehouse.sn_flag
     *
     * @return the value of base_warehouse.sn_flag
     *
     * @ET
     */
    public String getSnFlag() {
        return snFlag;
    }

    /**
     * This method:setSnFlag
     *  base_warehouse.sn_flag
     *
     * @param snFlag the value for base_warehouse.sn_flag
     *
     * @ET
     */
    public void setSnFlag(String snFlag) {
        this.snFlag = snFlag == null ? null : snFlag.trim();
    }

    /**
     * This method:getMgrUser
     * base_warehouse.mgr_user
     *
     * @return the value of base_warehouse.mgr_user
     *
     * @ET
     */
    public String getMgrUser() {
        return mgrUser;
    }

    /**
     * This method:setMgrUser
     *  base_warehouse.mgr_user
     *
     * @param mgrUser the value for base_warehouse.mgr_user
     *
     * @ET
     */
    public void setMgrUser(String mgrUser) {
        this.mgrUser = mgrUser == null ? null : mgrUser.trim();
    }

    /**
     * This method:getWhState
     * base_warehouse.wh_state
     *
     * @return the value of base_warehouse.wh_state
     *
     * @ET
     */
    public String getWhState() {
        return whState;
    }

    /**
     * This method:setWhState
     *  base_warehouse.wh_state
     *
     * @param whState the value for base_warehouse.wh_state
     *
     * @ET
     */
    public void setWhState(String whState) {
        this.whState = whState == null ? null : whState.trim();
    }

    /**
     * This method:getWhDesc
     * base_warehouse.wh_desc
     *
     * @return the value of base_warehouse.wh_desc
     *
     * @ET
     */
    public String getWhDesc() {
        return whDesc;
    }

    /**
     * This method:setWhDesc
     *  base_warehouse.wh_desc
     *
     * @param whDesc the value for base_warehouse.wh_desc
     *
     * @ET
     */
    public void setWhDesc(String whDesc) {
        this.whDesc = whDesc == null ? null : whDesc.trim();
    }

    /**
     * This method:getOrgId
     * base_warehouse.org_id
     *
     * @return the value of base_warehouse.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  base_warehouse.org_id
     *
     * @param orgId the value for base_warehouse.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * base_warehouse.sys_id
     *
     * @return the value of base_warehouse.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  base_warehouse.sys_id
     *
     * @param sysId the value for base_warehouse.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * base_warehouse.creator
     *
     * @return the value of base_warehouse.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  base_warehouse.creator
     *
     * @param creator the value for base_warehouse.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * base_warehouse.modifier
     *
     * @return the value of base_warehouse.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  base_warehouse.modifier
     *
     * @param modifier the value for base_warehouse.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * base_warehouse.create_time
     *
     * @return the value of base_warehouse.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  base_warehouse.create_time
     *
     * @param createTime the value for base_warehouse.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * base_warehouse.modify_time
     *
     * @return the value of base_warehouse.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  base_warehouse.modify_time
     *
     * @param modifyTime the value for base_warehouse.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMgrUserid() {
        return mgrUserid;
    }

    public void setMgrUserid(String mgrUserid) {
        this.mgrUserid = mgrUserid;
    }
}