package com.zlead.fplat.entity;

import java.util.Date;

public class BaseWhLoc {
    /**
     * 字段名称: id .
     * 字段定义: base_wh_loc.loc_id
     *
     * @ET
     */
    private Integer locId;

    /**
     * 字段名称: 库区编号 .
     * 字段定义: base_wh_loc.loc_no
     *
     * @ET
     */
    private String locNo;

    /**
     * 字段名称: 库区名称 .
     * 字段定义: base_wh_loc.loc_name
     *
     * @ET
     */
    private String locName;

    /**
     * 字段名称: 归属仓库 .
     * 字段定义: base_wh_loc.wh_id
     *
     * @ET
     */
    private Integer whId;

    /**
     * 字段名称: 库区状态 .
     * 字段定义: base_wh_loc.loc_state
     *
     * @ET
     */
    private String locState;

    /**
     * 字段名称: 备注 .
     * 字段定义: base_wh_loc.loc_desc
     *
     * @ET
     */
    private String locDesc;

    /**
     * 字段名称: 系统id .
     * 字段定义: base_wh_loc.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: base_wh_loc.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: base_wh_loc.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称:  .
     * 字段定义: base_wh_loc.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称:  .
     * 字段定义: base_wh_loc.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getLocId
     * base_wh_loc.loc_id
     *
     * @return the value of base_wh_loc.loc_id
     *
     * @ET
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * This method:setLocId
     *  base_wh_loc.loc_id
     *
     * @param locId the value for base_wh_loc.loc_id
     *
     * @ET
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * This method:getLocNo
     * base_wh_loc.loc_no
     *
     * @return the value of base_wh_loc.loc_no
     *
     * @ET
     */
    public String getLocNo() {
        return locNo;
    }

    /**
     * This method:setLocNo
     *  base_wh_loc.loc_no
     *
     * @param locNo the value for base_wh_loc.loc_no
     *
     * @ET
     */
    public void setLocNo(String locNo) {
        this.locNo = locNo == null ? null : locNo.trim();
    }

    /**
     * This method:getLocName
     * base_wh_loc.loc_name
     *
     * @return the value of base_wh_loc.loc_name
     *
     * @ET
     */
    public String getLocName() {
        return locName;
    }

    /**
     * This method:setLocName
     *  base_wh_loc.loc_name
     *
     * @param locName the value for base_wh_loc.loc_name
     *
     * @ET
     */
    public void setLocName(String locName) {
        this.locName = locName == null ? null : locName.trim();
    }

    /**
     * This method:getWhId
     * base_wh_loc.wh_id
     *
     * @return the value of base_wh_loc.wh_id
     *
     * @ET
     */
    public Integer getWhId() {
        return whId;
    }

    /**
     * This method:setWhId
     *  base_wh_loc.wh_id
     *
     * @param whId the value for base_wh_loc.wh_id
     *
     * @ET
     */
    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    /**
     * This method:getLocState
     * base_wh_loc.loc_state
     *
     * @return the value of base_wh_loc.loc_state
     *
     * @ET
     */
    public String getLocState() {
        return locState;
    }

    /**
     * This method:setLocState
     *  base_wh_loc.loc_state
     *
     * @param locState the value for base_wh_loc.loc_state
     *
     * @ET
     */
    public void setLocState(String locState) {
        this.locState = locState == null ? null : locState.trim();
    }

    /**
     * This method:getLocDesc
     * base_wh_loc.loc_desc
     *
     * @return the value of base_wh_loc.loc_desc
     *
     * @ET
     */
    public String getLocDesc() {
        return locDesc;
    }

    /**
     * This method:setLocDesc
     *  base_wh_loc.loc_desc
     *
     * @param locDesc the value for base_wh_loc.loc_desc
     *
     * @ET
     */
    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc == null ? null : locDesc.trim();
    }

    /**
     * This method:getSysId
     * base_wh_loc.sys_id
     *
     * @return the value of base_wh_loc.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  base_wh_loc.sys_id
     *
     * @param sysId the value for base_wh_loc.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * base_wh_loc.creator
     *
     * @return the value of base_wh_loc.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  base_wh_loc.creator
     *
     * @param creator the value for base_wh_loc.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * base_wh_loc.modifier
     *
     * @return the value of base_wh_loc.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  base_wh_loc.modifier
     *
     * @param modifier the value for base_wh_loc.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * base_wh_loc.create_time
     *
     * @return the value of base_wh_loc.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  base_wh_loc.create_time
     *
     * @param createTime the value for base_wh_loc.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * base_wh_loc.modify_time
     *
     * @return the value of base_wh_loc.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  base_wh_loc.modify_time
     *
     * @param modifyTime the value for base_wh_loc.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}