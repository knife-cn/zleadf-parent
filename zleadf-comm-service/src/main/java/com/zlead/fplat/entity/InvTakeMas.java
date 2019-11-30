package com.zlead.fplat.entity;

import java.util.Date;

public class InvTakeMas {
    /**
     * 字段名称: 盘点单id .
     * 字段定义: inv_take_mas.bill_id
     *
     * @ET
     */
    private Integer billId;

    /**
     * 字段名称: 盘点单号 .
     * 字段定义: inv_take_mas.bill_no
     *
     * @ET
     */
    private String billNo;

    /**
     * 字段名称: 盘点类型 .
     * 字段定义: inv_take_mas.bill_type
     *
     * @ET
     */
    private String billType;

    /**
     * 字段名称: 盘点库房 .
     * 字段定义: inv_take_mas.wh_id
     *
     * @ET
     */
    private Integer whId;

    /**
     * 字段名称: 盘点库区 .
     * 字段定义: inv_take_mas.loc_id
     *
     * @ET
     */
    private Integer locId;

    /**
     * 字段名称: 制单人 .
     * 字段定义: inv_take_mas.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 盘点日期 .
     * 字段定义: inv_take_mas.bill_date
     *
     * @ET
     */
    private Date billDate;

    /**
     * 字段名称: 盘点结果 .
     * 字段定义: inv_take_mas.bill_flag
     *
     * @ET
     */
    private String billFlag;

    /**
     * 字段名称: 账面金额 .
     * 字段定义: inv_take_mas.bill_amt
     *
     * @ET
     */
    private Double billAmt;

    /**
     * 字段名称: 盘点金额 .
     * 字段定义: inv_take_mas.mas_amt
     *
     * @ET
     */
    private Double masAmt;

    /**
     * 字段名称: 差异金额（元） .
     * 字段定义: inv_take_mas.def_amt
     *
     * @ET
     */
    private Double defAmt;

    /**
     * 字段名称: 附件 .
     * 字段定义: inv_take_mas.pics_path
     *
     * @ET
     */
    private String picsPath;

    /**
     * 字段名称: 状态 .
     * 字段定义: inv_take_mas.bill_state
     *
     * @ET
     */
    private String billState;

    /**
     * 字段名称: 备注 .
     * 字段定义: inv_take_mas.mas_desc
     *
     * @ET
     */
    private String masDesc;

    /**
     * 字段名称: 所属公司 .
     * 字段定义: inv_take_mas.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: inv_take_mas.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: inv_take_mas.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: inv_take_mas.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: inv_take_mas.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: inv_take_mas.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getBillId
     * inv_take_mas.bill_id
     *
     * @return the value of inv_take_mas.bill_id
     *
     * @ET
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method:setBillId
     *  inv_take_mas.bill_id
     *
     * @param billId the value for inv_take_mas.bill_id
     *
     * @ET
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method:getBillNo
     * inv_take_mas.bill_no
     *
     * @return the value of inv_take_mas.bill_no
     *
     * @ET
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * This method:setBillNo
     *  inv_take_mas.bill_no
     *
     * @param billNo the value for inv_take_mas.bill_no
     *
     * @ET
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    /**
     * This method:getBillType
     * inv_take_mas.bill_type
     *
     * @return the value of inv_take_mas.bill_type
     *
     * @ET
     */
    public String getBillType() {
        return billType;
    }

    /**
     * This method:setBillType
     *  inv_take_mas.bill_type
     *
     * @param billType the value for inv_take_mas.bill_type
     *
     * @ET
     */
    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    /**
     * This method:getWhId
     * inv_take_mas.wh_id
     *
     * @return the value of inv_take_mas.wh_id
     *
     * @ET
     */
    public Integer getWhId() {
        return whId;
    }

    /**
     * This method:setWhId
     *  inv_take_mas.wh_id
     *
     * @param whId the value for inv_take_mas.wh_id
     *
     * @ET
     */
    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    /**
     * This method:getLocId
     * inv_take_mas.loc_id
     *
     * @return the value of inv_take_mas.loc_id
     *
     * @ET
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * This method:setLocId
     *  inv_take_mas.loc_id
     *
     * @param locId the value for inv_take_mas.loc_id
     *
     * @ET
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * This method:getAgentId
     * inv_take_mas.agent_id
     *
     * @return the value of inv_take_mas.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  inv_take_mas.agent_id
     *
     * @param agentId the value for inv_take_mas.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getBillDate
     * inv_take_mas.bill_date
     *
     * @return the value of inv_take_mas.bill_date
     *
     * @ET
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * This method:setBillDate
     *  inv_take_mas.bill_date
     *
     * @param billDate the value for inv_take_mas.bill_date
     *
     * @ET
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * This method:getBillFlag
     * inv_take_mas.bill_flag
     *
     * @return the value of inv_take_mas.bill_flag
     *
     * @ET
     */
    public String getBillFlag() {
        return billFlag;
    }

    /**
     * This method:setBillFlag
     *  inv_take_mas.bill_flag
     *
     * @param billFlag the value for inv_take_mas.bill_flag
     *
     * @ET
     */
    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag == null ? null : billFlag.trim();
    }

    /**
     * This method:getBillAmt
     * inv_take_mas.bill_amt
     *
     * @return the value of inv_take_mas.bill_amt
     *
     * @ET
     */
    public Double getBillAmt() {
        return billAmt;
    }

    /**
     * This method:setBillAmt
     *  inv_take_mas.bill_amt
     *
     * @param billAmt the value for inv_take_mas.bill_amt
     *
     * @ET
     */
    public void setBillAmt(Double billAmt) {
        this.billAmt = billAmt;
    }

    /**
     * This method:getMasAmt
     * inv_take_mas.mas_amt
     *
     * @return the value of inv_take_mas.mas_amt
     *
     * @ET
     */
    public Double getMasAmt() {
        return masAmt;
    }

    /**
     * This method:setMasAmt
     *  inv_take_mas.mas_amt
     *
     * @param masAmt the value for inv_take_mas.mas_amt
     *
     * @ET
     */
    public void setMasAmt(Double masAmt) {
        this.masAmt = masAmt;
    }

    /**
     * This method:getDefAmt
     * inv_take_mas.def_amt
     *
     * @return the value of inv_take_mas.def_amt
     *
     * @ET
     */
    public Double getDefAmt() {
        return defAmt;
    }

    /**
     * This method:setDefAmt
     *  inv_take_mas.def_amt
     *
     * @param defAmt the value for inv_take_mas.def_amt
     *
     * @ET
     */
    public void setDefAmt(Double defAmt) {
        this.defAmt = defAmt;
    }

    /**
     * This method:getPicsPath
     * inv_take_mas.pics_path
     *
     * @return the value of inv_take_mas.pics_path
     *
     * @ET
     */
    public String getPicsPath() {
        return picsPath;
    }

    /**
     * This method:setPicsPath
     *  inv_take_mas.pics_path
     *
     * @param picsPath the value for inv_take_mas.pics_path
     *
     * @ET
     */
    public void setPicsPath(String picsPath) {
        this.picsPath = picsPath == null ? null : picsPath.trim();
    }

    /**
     * This method:getBillState
     * inv_take_mas.bill_state
     *
     * @return the value of inv_take_mas.bill_state
     *
     * @ET
     */
    public String getBillState() {
        return billState;
    }

    /**
     * This method:setBillState
     *  inv_take_mas.bill_state
     *
     * @param billState the value for inv_take_mas.bill_state
     *
     * @ET
     */
    public void setBillState(String billState) {
        this.billState = billState == null ? null : billState.trim();
    }

    /**
     * This method:getMasDesc
     * inv_take_mas.mas_desc
     *
     * @return the value of inv_take_mas.mas_desc
     *
     * @ET
     */
    public String getMasDesc() {
        return masDesc;
    }

    /**
     * This method:setMasDesc
     *  inv_take_mas.mas_desc
     *
     * @param masDesc the value for inv_take_mas.mas_desc
     *
     * @ET
     */
    public void setMasDesc(String masDesc) {
        this.masDesc = masDesc == null ? null : masDesc.trim();
    }

    /**
     * This method:getOrgId
     * inv_take_mas.org_id
     *
     * @return the value of inv_take_mas.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  inv_take_mas.org_id
     *
     * @param orgId the value for inv_take_mas.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * inv_take_mas.sys_id
     *
     * @return the value of inv_take_mas.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  inv_take_mas.sys_id
     *
     * @param sysId the value for inv_take_mas.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * inv_take_mas.creator
     *
     * @return the value of inv_take_mas.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  inv_take_mas.creator
     *
     * @param creator the value for inv_take_mas.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * inv_take_mas.modifier
     *
     * @return the value of inv_take_mas.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  inv_take_mas.modifier
     *
     * @param modifier the value for inv_take_mas.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * inv_take_mas.create_time
     *
     * @return the value of inv_take_mas.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  inv_take_mas.create_time
     *
     * @param createTime the value for inv_take_mas.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * inv_take_mas.modify_time
     *
     * @return the value of inv_take_mas.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  inv_take_mas.modify_time
     *
     * @param modifyTime the value for inv_take_mas.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}