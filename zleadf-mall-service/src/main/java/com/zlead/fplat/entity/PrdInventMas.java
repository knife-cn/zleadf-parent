package com.zlead.fplat.entity;

import java.util.Date;

public class PrdInventMas {
    /**
     * 字段名称: 库存id .
     * 字段定义: prd_invent_mas.im_id
     *
     * @ET
     */
    private Integer imId;

    /**
     * 字段名称: 商品 .
     * 字段定义: prd_invent_mas.item_id
     *
     * @ET
     */
    private Integer itemId;

    /**
     * 字段名称: 仓库id .
     * 字段定义: prd_invent_mas.wh_id
     *
     * @ET
     */
    private Integer whId;

    /**
     * 字段名称: 库位id .
     * 字段定义: prd_invent_mas.loc_id
     *
     * @ET
     */
    private Integer locId;

    /**
     * 字段名称: 库存数量 .
     * 字段定义: prd_invent_mas.im_qty
     *
     * @ET
     */
    private Integer imQty;

    /**
     * 字段名称: 库存单位 .
     * 字段定义: prd_invent_mas.item_uom
     *
     * @ET
     */
    private String itemUom;

    /**
     * 字段名称: 总成本 .
     * 字段定义: prd_invent_mas.im_amt
     *
     * @ET
     */
    private Double imAmt;

    /**
     * 字段名称: 变更id .
     * 字段定义: prd_invent_mas.bill_id
     *
     * @ET
     */
    private Integer billId;

    /**
     * 字段名称: 变更单号 .
     * 字段定义: prd_invent_mas.bill_no
     *
     * @ET
     */
    private String billNo;

    /**
     * 字段名称: 变更明细 .
     * 字段定义: prd_invent_mas.detail_id
     *
     * @ET
     */
    private Integer detailId;

    /**
     * 字段名称: 变更类型 .
     * 字段定义: prd_invent_mas.bill_type
     *
     * @ET
     */
    private String billType;

    /**
     * 字段名称: 变更日期 .
     * 字段定义: prd_invent_mas.bill_date
     *
     * @ET
     */
    private Date billDate;

    /**
     * 字段名称: 变更数 .
     * 字段定义: prd_invent_mas.io_qty
     *
     * @ET
     */
    private Integer ioQty;

    /**
     * 字段名称: 变更成本 .
     * 字段定义: prd_invent_mas.io_amt
     *
     * @ET
     */
    private Double ioAmt;

    /**
     * 字段名称: 出入区分 .
     * 字段定义: prd_invent_mas.io_flag
     *
     * @ET
     */
    private String ioFlag;

    /**
     * 字段名称: 状态 .
     * 字段定义: prd_invent_mas.im_state
     *
     * @ET
     */
    private String imState;

    /**
     * 字段名称: 备注 .
     * 字段定义: prd_invent_mas.mas_desc
     *
     * @ET
     */
    private String masDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: prd_invent_mas.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: prd_invent_mas.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: prd_invent_mas.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: prd_invent_mas.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 入库时间 .
     * 字段定义: prd_invent_mas.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: prd_invent_mas.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getImId
     * prd_invent_mas.im_id
     *
     * @return the value of prd_invent_mas.im_id
     *
     * @ET
     */
    public Integer getImId() {
        return imId;
    }

    /**
     * This method:setImId
     *  prd_invent_mas.im_id
     *
     * @param imId the value for prd_invent_mas.im_id
     *
     * @ET
     */
    public void setImId(Integer imId) {
        this.imId = imId;
    }

    /**
     * This method:getItemId
     * prd_invent_mas.item_id
     *
     * @return the value of prd_invent_mas.item_id
     *
     * @ET
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method:setItemId
     *  prd_invent_mas.item_id
     *
     * @param itemId the value for prd_invent_mas.item_id
     *
     * @ET
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method:getWhId
     * prd_invent_mas.wh_id
     *
     * @return the value of prd_invent_mas.wh_id
     *
     * @ET
     */
    public Integer getWhId() {
        return whId;
    }

    /**
     * This method:setWhId
     *  prd_invent_mas.wh_id
     *
     * @param whId the value for prd_invent_mas.wh_id
     *
     * @ET
     */
    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    /**
     * This method:getLocId
     * prd_invent_mas.loc_id
     *
     * @return the value of prd_invent_mas.loc_id
     *
     * @ET
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * This method:setLocId
     *  prd_invent_mas.loc_id
     *
     * @param locId the value for prd_invent_mas.loc_id
     *
     * @ET
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * This method:getImQty
     * prd_invent_mas.im_qty
     *
     * @return the value of prd_invent_mas.im_qty
     *
     * @ET
     */
    public Integer getImQty() {
        return imQty;
    }

    /**
     * This method:setImQty
     *  prd_invent_mas.im_qty
     *
     * @param imQty the value for prd_invent_mas.im_qty
     *
     * @ET
     */
    public void setImQty(Integer imQty) {
        this.imQty = imQty;
    }

    /**
     * This method:getItemUom
     * prd_invent_mas.item_uom
     *
     * @return the value of prd_invent_mas.item_uom
     *
     * @ET
     */
    public String getItemUom() {
        return itemUom;
    }

    /**
     * This method:setItemUom
     *  prd_invent_mas.item_uom
     *
     * @param itemUom the value for prd_invent_mas.item_uom
     *
     * @ET
     */
    public void setItemUom(String itemUom) {
        this.itemUom = itemUom == null ? null : itemUom.trim();
    }

    /**
     * This method:getImAmt
     * prd_invent_mas.im_amt
     *
     * @return the value of prd_invent_mas.im_amt
     *
     * @ET
     */
    public Double getImAmt() {
        return imAmt;
    }

    /**
     * This method:setImAmt
     *  prd_invent_mas.im_amt
     *
     * @param imAmt the value for prd_invent_mas.im_amt
     *
     * @ET
     */
    public void setImAmt(Double imAmt) {
        this.imAmt = imAmt;
    }

    /**
     * This method:getBillId
     * prd_invent_mas.bill_id
     *
     * @return the value of prd_invent_mas.bill_id
     *
     * @ET
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method:setBillId
     *  prd_invent_mas.bill_id
     *
     * @param billId the value for prd_invent_mas.bill_id
     *
     * @ET
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method:getBillNo
     * prd_invent_mas.bill_no
     *
     * @return the value of prd_invent_mas.bill_no
     *
     * @ET
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * This method:setBillNo
     *  prd_invent_mas.bill_no
     *
     * @param billNo the value for prd_invent_mas.bill_no
     *
     * @ET
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    /**
     * This method:getDetailId
     * prd_invent_mas.detail_id
     *
     * @return the value of prd_invent_mas.detail_id
     *
     * @ET
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * This method:setDetailId
     *  prd_invent_mas.detail_id
     *
     * @param detailId the value for prd_invent_mas.detail_id
     *
     * @ET
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * This method:getBillType
     * prd_invent_mas.bill_type
     *
     * @return the value of prd_invent_mas.bill_type
     *
     * @ET
     */
    public String getBillType() {
        return billType;
    }

    /**
     * This method:setBillType
     *  prd_invent_mas.bill_type
     *
     * @param billType the value for prd_invent_mas.bill_type
     *
     * @ET
     */
    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    /**
     * This method:getBillDate
     * prd_invent_mas.bill_date
     *
     * @return the value of prd_invent_mas.bill_date
     *
     * @ET
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * This method:setBillDate
     *  prd_invent_mas.bill_date
     *
     * @param billDate the value for prd_invent_mas.bill_date
     *
     * @ET
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * This method:getIoQty
     * prd_invent_mas.io_qty
     *
     * @return the value of prd_invent_mas.io_qty
     *
     * @ET
     */
    public Integer getIoQty() {
        return ioQty;
    }

    /**
     * This method:setIoQty
     *  prd_invent_mas.io_qty
     *
     * @param ioQty the value for prd_invent_mas.io_qty
     *
     * @ET
     */
    public void setIoQty(Integer ioQty) {
        this.ioQty = ioQty;
    }

    /**
     * This method:getIoAmt
     * prd_invent_mas.io_amt
     *
     * @return the value of prd_invent_mas.io_amt
     *
     * @ET
     */
    public Double getIoAmt() {
        return ioAmt;
    }

    /**
     * This method:setIoAmt
     *  prd_invent_mas.io_amt
     *
     * @param ioAmt the value for prd_invent_mas.io_amt
     *
     * @ET
     */
    public void setIoAmt(Double ioAmt) {
        this.ioAmt = ioAmt;
    }

    /**
     * This method:getIoFlag
     * prd_invent_mas.io_flag
     *
     * @return the value of prd_invent_mas.io_flag
     *
     * @ET
     */
    public String getIoFlag() {
        return ioFlag;
    }

    /**
     * This method:setIoFlag
     *  prd_invent_mas.io_flag
     *
     * @param ioFlag the value for prd_invent_mas.io_flag
     *
     * @ET
     */
    public void setIoFlag(String ioFlag) {
        this.ioFlag = ioFlag == null ? null : ioFlag.trim();
    }

    /**
     * This method:getImState
     * prd_invent_mas.im_state
     *
     * @return the value of prd_invent_mas.im_state
     *
     * @ET
     */
    public String getImState() {
        return imState;
    }

    /**
     * This method:setImState
     *  prd_invent_mas.im_state
     *
     * @param imState the value for prd_invent_mas.im_state
     *
     * @ET
     */
    public void setImState(String imState) {
        this.imState = imState == null ? null : imState.trim();
    }

    /**
     * This method:getMasDesc
     * prd_invent_mas.mas_desc
     *
     * @return the value of prd_invent_mas.mas_desc
     *
     * @ET
     */
    public String getMasDesc() {
        return masDesc;
    }

    /**
     * This method:setMasDesc
     *  prd_invent_mas.mas_desc
     *
     * @param masDesc the value for prd_invent_mas.mas_desc
     *
     * @ET
     */
    public void setMasDesc(String masDesc) {
        this.masDesc = masDesc == null ? null : masDesc.trim();
    }

    /**
     * This method:getOrgId
     * prd_invent_mas.org_id
     *
     * @return the value of prd_invent_mas.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  prd_invent_mas.org_id
     *
     * @param orgId the value for prd_invent_mas.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * prd_invent_mas.sys_id
     *
     * @return the value of prd_invent_mas.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  prd_invent_mas.sys_id
     *
     * @param sysId the value for prd_invent_mas.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * prd_invent_mas.creator
     *
     * @return the value of prd_invent_mas.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  prd_invent_mas.creator
     *
     * @param creator the value for prd_invent_mas.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * prd_invent_mas.modifier
     *
     * @return the value of prd_invent_mas.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  prd_invent_mas.modifier
     *
     * @param modifier the value for prd_invent_mas.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * prd_invent_mas.create_time
     *
     * @return the value of prd_invent_mas.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  prd_invent_mas.create_time
     *
     * @param createTime the value for prd_invent_mas.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * prd_invent_mas.modify_time
     *
     * @return the value of prd_invent_mas.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  prd_invent_mas.modify_time
     *
     * @param modifyTime the value for prd_invent_mas.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}