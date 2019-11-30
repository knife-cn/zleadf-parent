package com.zlead.fplat.entity;

import java.util.Date;

public class Custlevel {
    /**
     * 字段名称: 等级id .
     * 字段定义: crm_cust_level.level_id
     *
     * @ET
     */
    private Integer levelId;

    /**
     * 字段名称: 编号 .
     * 字段定义: crm_cust_level.level_no
     *
     * @ET
     */
    private String levelNo;

    /**
     * 字段名称: 级别名称 .
     * 字段定义: crm_cust_level.level_name
     *
     * @ET
     */
    private String levelName;

    /**
     * 字段名称: 排序 .
     * 字段定义: crm_cust_level.show_sort
     *
     * @ET
     */
    private Integer showSort;

    /**
     * 字段名称: 折扣区间 .
     * 字段定义: crm_cust_level.min_dis
     *
     * @ET
     */
    private Double minDis;

    /**
     * 字段名称: 折扣区间 .
     * 字段定义: crm_cust_level.max_dis
     *
     * @ET
     */
    private Double maxDis;

    /**
     * 字段名称: 是否有返点 .
     * 字段定义: crm_cust_level.back_flag
     *
     * @ET
     */
    private String backFlag;

    /**
     * 字段名称: 返点 .
     * 字段定义: crm_cust_level.min_back
     *
     * @ET
     */
    private Double minBack;

    /**
     * 字段名称: 返点 .
     * 字段定义: crm_cust_level.max_back
     *
     * @ET
     */
    private Double maxBack;

    /**
     * 字段名称: 状态 .
     * 字段定义: crm_cust_level.level_state
     *
     * @ET
     */
    private String levelState;

    /**
     * 字段名称: 备注 .
     * 字段定义: crm_cust_level.level_desc
     *
     * @ET
     */
    private String levelDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: crm_cust_level.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 所属系统 .
     * 字段定义: crm_cust_level.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: crm_cust_level.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: crm_cust_level.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: crm_cust_level.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: crm_cust_level.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getLevelId
     * crm_cust_level.level_id
     *
     * @return the value of crm_cust_level.level_id
     *
     * @ET
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * This method:setLevelId
     *  crm_cust_level.level_id
     *
     * @param levelId the value for crm_cust_level.level_id
     *
     * @ET
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * This method:getLevelNo
     * crm_cust_level.level_no
     *
     * @return the value of crm_cust_level.level_no
     *
     * @ET
     */
    public String getLevelNo() {
        return levelNo;
    }

    /**
     * This method:setLevelNo
     *  crm_cust_level.level_no
     *
     * @param levelNo the value for crm_cust_level.level_no
     *
     * @ET
     */
    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo == null ? null : levelNo.trim();
    }

    /**
     * This method:getLevelName
     * crm_cust_level.level_name
     *
     * @return the value of crm_cust_level.level_name
     *
     * @ET
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * This method:setLevelName
     *  crm_cust_level.level_name
     *
     * @param levelName the value for crm_cust_level.level_name
     *
     * @ET
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * This method:getShowSort
     * crm_cust_level.show_sort
     *
     * @return the value of crm_cust_level.show_sort
     *
     * @ET
     */
    public Integer getShowSort() {
        return showSort;
    }

    /**
     * This method:setShowSort
     *  crm_cust_level.show_sort
     *
     * @param showSort the value for crm_cust_level.show_sort
     *
     * @ET
     */
    public void setShowSort(Integer showSort) {
        this.showSort = showSort;
    }

    /**
     * This method:getMinDis
     * crm_cust_level.min_dis
     *
     * @return the value of crm_cust_level.min_dis
     *
     * @ET
     */
    public Double getMinDis() {
        return minDis;
    }

    /**
     * This method:setMinDis
     *  crm_cust_level.min_dis
     *
     * @param minDis the value for crm_cust_level.min_dis
     *
     * @ET
     */
    public void setMinDis(Double minDis) {
        this.minDis = minDis;
    }

    /**
     * This method:getMaxDis
     * crm_cust_level.max_dis
     *
     * @return the value of crm_cust_level.max_dis
     *
     * @ET
     */
    public Double getMaxDis() {
        return maxDis;
    }

    /**
     * This method:setMaxDis
     *  crm_cust_level.max_dis
     *
     * @param maxDis the value for crm_cust_level.max_dis
     *
     * @ET
     */
    public void setMaxDis(Double maxDis) {
        this.maxDis = maxDis;
    }

    /**
     * This method:getBackFlag
     * crm_cust_level.back_flag
     *
     * @return the value of crm_cust_level.back_flag
     *
     * @ET
     */
    public String getBackFlag() {
        return backFlag;
    }

    /**
     * This method:setBackFlag
     *  crm_cust_level.back_flag
     *
     * @param backFlag the value for crm_cust_level.back_flag
     *
     * @ET
     */
    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag == null ? null : backFlag.trim();
    }

    /**
     * This method:getMinBack
     * crm_cust_level.min_back
     *
     * @return the value of crm_cust_level.min_back
     *
     * @ET
     */
    public Double getMinBack() {
        return minBack;
    }

    /**
     * This method:setMinBack
     *  crm_cust_level.min_back
     *
     * @param minBack the value for crm_cust_level.min_back
     *
     * @ET
     */
    public void setMinBack(Double minBack) {
        this.minBack = minBack;
    }

    /**
     * This method:getMaxBack
     * crm_cust_level.max_back
     *
     * @return the value of crm_cust_level.max_back
     *
     * @ET
     */
    public Double getMaxBack() {
        return maxBack;
    }

    /**
     * This method:setMaxBack
     *  crm_cust_level.max_back
     *
     * @param maxBack the value for crm_cust_level.max_back
     *
     * @ET
     */
    public void setMaxBack(Double maxBack) {
        this.maxBack = maxBack;
    }

    /**
     * This method:getLevelState
     * crm_cust_level.level_state
     *
     * @return the value of crm_cust_level.level_state
     *
     * @ET
     */
    public String getLevelState() {
        return levelState;
    }

    /**
     * This method:setLevelState
     *  crm_cust_level.level_state
     *
     * @param levelState the value for crm_cust_level.level_state
     *
     * @ET
     */
    public void setLevelState(String levelState) {
        this.levelState = levelState == null ? null : levelState.trim();
    }

    /**
     * This method:getLevelDesc
     * crm_cust_level.level_desc
     *
     * @return the value of crm_cust_level.level_desc
     *
     * @ET
     */
    public String getLevelDesc() {
        return levelDesc;
    }

    /**
     * This method:setLevelDesc
     *  crm_cust_level.level_desc
     *
     * @param levelDesc the value for crm_cust_level.level_desc
     *
     * @ET
     */
    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc == null ? null : levelDesc.trim();
    }

    /**
     * This method:getOrgId
     * crm_cust_level.org_id
     *
     * @return the value of crm_cust_level.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  crm_cust_level.org_id
     *
     * @param orgId the value for crm_cust_level.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * crm_cust_level.sys_id
     *
     * @return the value of crm_cust_level.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  crm_cust_level.sys_id
     *
     * @param sysId the value for crm_cust_level.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * crm_cust_level.creator
     *
     * @return the value of crm_cust_level.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  crm_cust_level.creator
     *
     * @param creator the value for crm_cust_level.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * crm_cust_level.modifier
     *
     * @return the value of crm_cust_level.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  crm_cust_level.modifier
     *
     * @param modifier the value for crm_cust_level.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * crm_cust_level.create_time
     *
     * @return the value of crm_cust_level.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  crm_cust_level.create_time
     *
     * @param createTime the value for crm_cust_level.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * crm_cust_level.modify_time
     *
     * @return the value of crm_cust_level.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  crm_cust_level.modify_time
     *
     * @param modifyTime the value for crm_cust_level.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}