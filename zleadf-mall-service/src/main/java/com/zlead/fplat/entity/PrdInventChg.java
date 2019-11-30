package com.zlead.fplat.entity;

import java.util.Date;

public class PrdInventChg {
    /**
     * 字段名称: 记录id .
     * 字段定义: prd_invent_chg.rec_id
     *
     * @ET
     */
    private Integer recId;

    /**
     * 字段名称: 库存id .
     * 字段定义: prd_invent_chg.im_id
     *
     * @ET
     */
    private Integer imId;

    /**
     * 字段名称: 物料id .
     * 字段定义: prd_invent_chg.item_id
     *
     * @ET
     */
    private Integer itemId;

    /**
     * 字段名称: 仓库 .
     * 字段定义: prd_invent_chg.wh_id
     *
     * @ET
     */
    private Integer whId;

    /**
     * 字段名称: 库位id .
     * 字段定义: prd_invent_chg.loc_id
     *
     * @ET
     */
    private Integer locId;

    /**
     * 字段名称: 期初数 .
     * 字段定义: prd_invent_chg.pre_qty
     *
     * @ET
     */
    private Integer preQty;

    /**
     * 字段名称: 变更数 .
     * 字段定义: prd_invent_chg.io_qty
     *
     * @ET
     */
    private Integer ioQty;

    /**
     * 字段名称: 库存数量 .
     * 字段定义: prd_invent_chg.im_qty
     *
     * @ET
     */
    private Integer imQty;

    /**
     * 字段名称: 库存单位 .
     * 字段定义: prd_invent_chg.item_uom
     *
     * @ET
     */
    private String itemUom;

    /**
     * 字段名称: 库存成本 .
     * 字段定义: prd_invent_chg.cost_price
     *
     * @ET
     */
    private Double costPrice;

    /**
     * 字段名称: 期初成本 .
     * 字段定义: prd_invent_chg.pre_amt
     *
     * @ET
     */
    private Double preAmt;

    /**
     * 字段名称: 变更成本 .
     * 字段定义: prd_invent_chg.io_amt
     *
     * @ET
     */
    private Double ioAmt;

    /**
     * 字段名称: 当前成本 .
     * 字段定义: prd_invent_chg.im_amt
     *
     * @ET
     */
    private Double imAmt;

    /**
     * 字段名称: 变更id .
     * 字段定义: prd_invent_chg.bill_id
     *
     * @ET
     */
    private Integer billId;

    /**
     * 字段名称: 变更单号 .
     * 字段定义: prd_invent_chg.bill_no
     *
     * @ET
     */
    private String billNo;

    /**
     * 字段名称: 变更明细 .
     * 字段定义: prd_invent_chg.detail_id
     *
     * @ET
     */
    private Integer detailId;

    /**
     * 字段名称: 出入区分 .
     * 字段定义: prd_invent_chg.io_flag
     *
     * @ET
     */
    private String ioFlag;

    /**
     * 字段名称: 变更类型 .
     * 字段定义: prd_invent_chg.bill_type
     *
     * @ET
     */
    private String billType;

    /**
     * 字段名称: 变更日期 .
     * 字段定义: prd_invent_chg.bill_date
     *
     * @ET
     */
    private Date billDate;

    /**
     * 字段名称: 附件 .
     * 字段定义: prd_invent_chg.pics_path
     *
     * @ET
     */
    private String picsPath;

    /**
     * 字段名称: 状态 .
     * 字段定义: prd_invent_chg.im_state
     *
     * @ET
     */
    private String imState;

    /**
     * 字段名称: 备注 .
     * 字段定义: prd_invent_chg.mas_desc
     *
     * @ET
     */
    private String masDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: prd_invent_chg.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 所属系统 .
     * 字段定义: prd_invent_chg.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: prd_invent_chg.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: prd_invent_chg.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: prd_invent_chg.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: prd_invent_chg.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getRecId
     * prd_invent_chg.rec_id
     *
     * @return the value of prd_invent_chg.rec_id
     *
     * @ET
     */
    public Integer getRecId() {
        return recId;
    }

    /**
     * This method:setRecId
     *  prd_invent_chg.rec_id
     *
     * @param recId the value for prd_invent_chg.rec_id
     *
     * @ET
     */
    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    /**
     * This method:getImId
     * prd_invent_chg.im_id
     *
     * @return the value of prd_invent_chg.im_id
     *
     * @ET
     */
    public Integer getImId() {
        return imId;
    }

    /**
     * This method:setImId
     *  prd_invent_chg.im_id
     *
     * @param imId the value for prd_invent_chg.im_id
     *
     * @ET
     */
    public void setImId(Integer imId) {
        this.imId = imId;
    }

    /**
     * This method:getItemId
     * prd_invent_chg.item_id
     *
     * @return the value of prd_invent_chg.item_id
     *
     * @ET
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method:setItemId
     *  prd_invent_chg.item_id
     *
     * @param itemId the value for prd_invent_chg.item_id
     *
     * @ET
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method:getWhId
     * prd_invent_chg.wh_id
     *
     * @return the value of prd_invent_chg.wh_id
     *
     * @ET
     */
    public Integer getWhId() {
        return whId;
    }

    /**
     * This method:setWhId
     *  prd_invent_chg.wh_id
     *
     * @param whId the value for prd_invent_chg.wh_id
     *
     * @ET
     */
    public void setWhId(Integer whId) {
        this.whId = whId;
    }

    /**
     * This method:getLocId
     * prd_invent_chg.loc_id
     *
     * @return the value of prd_invent_chg.loc_id
     *
     * @ET
     */
    public Integer getLocId() {
        return locId;
    }

    /**
     * This method:setLocId
     *  prd_invent_chg.loc_id
     *
     * @param locId the value for prd_invent_chg.loc_id
     *
     * @ET
     */
    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    /**
     * This method:getPreQty
     * prd_invent_chg.pre_qty
     *
     * @return the value of prd_invent_chg.pre_qty
     *
     * @ET
     */
    public Integer getPreQty() {
        return preQty;
    }

    /**
     * This method:setPreQty
     *  prd_invent_chg.pre_qty
     *
     * @param preQty the value for prd_invent_chg.pre_qty
     *
     * @ET
     */
    public void setPreQty(Integer preQty) {
        this.preQty = preQty;
    }

    /**
     * This method:getIoQty
     * prd_invent_chg.io_qty
     *
     * @return the value of prd_invent_chg.io_qty
     *
     * @ET
     */
    public Integer getIoQty() {
        return ioQty;
    }

    /**
     * This method:setIoQty
     *  prd_invent_chg.io_qty
     *
     * @param ioQty the value for prd_invent_chg.io_qty
     *
     * @ET
     */
    public void setIoQty(Integer ioQty) {
        this.ioQty = ioQty;
    }

    /**
     * This method:getImQty
     * prd_invent_chg.im_qty
     *
     * @return the value of prd_invent_chg.im_qty
     *
     * @ET
     */
    public Integer getImQty() {
        return imQty;
    }

    /**
     * This method:setImQty
     *  prd_invent_chg.im_qty
     *
     * @param imQty the value for prd_invent_chg.im_qty
     *
     * @ET
     */
    public void setImQty(Integer imQty) {
        this.imQty = imQty;
    }

    /**
     * This method:getItemUom
     * prd_invent_chg.item_uom
     *
     * @return the value of prd_invent_chg.item_uom
     *
     * @ET
     */
    public String getItemUom() {
        return itemUom;
    }

    /**
     * This method:setItemUom
     *  prd_invent_chg.item_uom
     *
     * @param itemUom the value for prd_invent_chg.item_uom
     *
     * @ET
     */
    public void setItemUom(String itemUom) {
        this.itemUom = itemUom == null ? null : itemUom.trim();
    }

    /**
     * This method:getCostPrice
     * prd_invent_chg.cost_price
     *
     * @return the value of prd_invent_chg.cost_price
     *
     * @ET
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * This method:setCostPrice
     *  prd_invent_chg.cost_price
     *
     * @param costPrice the value for prd_invent_chg.cost_price
     *
     * @ET
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * This method:getPreAmt
     * prd_invent_chg.pre_amt
     *
     * @return the value of prd_invent_chg.pre_amt
     *
     * @ET
     */
    public Double getPreAmt() {
        return preAmt;
    }

    /**
     * This method:setPreAmt
     *  prd_invent_chg.pre_amt
     *
     * @param preAmt the value for prd_invent_chg.pre_amt
     *
     * @ET
     */
    public void setPreAmt(Double preAmt) {
        this.preAmt = preAmt;
    }

    /**
     * This method:getIoAmt
     * prd_invent_chg.io_amt
     *
     * @return the value of prd_invent_chg.io_amt
     *
     * @ET
     */
    public Double getIoAmt() {
        return ioAmt;
    }

    /**
     * This method:setIoAmt
     *  prd_invent_chg.io_amt
     *
     * @param ioAmt the value for prd_invent_chg.io_amt
     *
     * @ET
     */
    public void setIoAmt(Double ioAmt) {
        this.ioAmt = ioAmt;
    }

    /**
     * This method:getImAmt
     * prd_invent_chg.im_amt
     *
     * @return the value of prd_invent_chg.im_amt
     *
     * @ET
     */
    public Double getImAmt() {
        return imAmt;
    }

    /**
     * This method:setImAmt
     *  prd_invent_chg.im_amt
     *
     * @param imAmt the value for prd_invent_chg.im_amt
     *
     * @ET
     */
    public void setImAmt(Double imAmt) {
        this.imAmt = imAmt;
    }

    /**
     * This method:getBillId
     * prd_invent_chg.bill_id
     *
     * @return the value of prd_invent_chg.bill_id
     *
     * @ET
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method:setBillId
     *  prd_invent_chg.bill_id
     *
     * @param billId the value for prd_invent_chg.bill_id
     *
     * @ET
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method:getBillNo
     * prd_invent_chg.bill_no
     *
     * @return the value of prd_invent_chg.bill_no
     *
     * @ET
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * This method:setBillNo
     *  prd_invent_chg.bill_no
     *
     * @param billNo the value for prd_invent_chg.bill_no
     *
     * @ET
     */
    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    /**
     * This method:getDetailId
     * prd_invent_chg.detail_id
     *
     * @return the value of prd_invent_chg.detail_id
     *
     * @ET
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * This method:setDetailId
     *  prd_invent_chg.detail_id
     *
     * @param detailId the value for prd_invent_chg.detail_id
     *
     * @ET
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * This method:getIoFlag
     * prd_invent_chg.io_flag
     *
     * @return the value of prd_invent_chg.io_flag
     *
     * @ET
     */
    public String getIoFlag() {
        return ioFlag;
    }

    /**
     * This method:setIoFlag
     *  prd_invent_chg.io_flag
     *
     * @param ioFlag the value for prd_invent_chg.io_flag
     *
     * @ET
     */
    public void setIoFlag(String ioFlag) {
        this.ioFlag = ioFlag == null ? null : ioFlag.trim();
    }

    /**
     * This method:getBillType
     * prd_invent_chg.bill_type
     *
     * @return the value of prd_invent_chg.bill_type
     *
     * @ET
     */
    public String getBillType() {
        return billType;
    }

    /**
     * This method:setBillType
     *  prd_invent_chg.bill_type
     *
     * @param billType the value for prd_invent_chg.bill_type
     *
     * @ET
     */
    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    /**
     * This method:getBillDate
     * prd_invent_chg.bill_date
     *
     * @return the value of prd_invent_chg.bill_date
     *
     * @ET
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * This method:setBillDate
     *  prd_invent_chg.bill_date
     *
     * @param billDate the value for prd_invent_chg.bill_date
     *
     * @ET
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * This method:getPicsPath
     * prd_invent_chg.pics_path
     *
     * @return the value of prd_invent_chg.pics_path
     *
     * @ET
     */
    public String getPicsPath() {
        return picsPath;
    }

    /**
     * This method:setPicsPath
     *  prd_invent_chg.pics_path
     *
     * @param picsPath the value for prd_invent_chg.pics_path
     *
     * @ET
     */
    public void setPicsPath(String picsPath) {
        this.picsPath = picsPath == null ? null : picsPath.trim();
    }

    /**
     * This method:getImState
     * prd_invent_chg.im_state
     *
     * @return the value of prd_invent_chg.im_state
     *
     * @ET
     */
    public String getImState() {
        return imState;
    }

    /**
     * This method:setImState
     *  prd_invent_chg.im_state
     *
     * @param imState the value for prd_invent_chg.im_state
     *
     * @ET
     */
    public void setImState(String imState) {
        this.imState = imState == null ? null : imState.trim();
    }

    /**
     * This method:getMasDesc
     * prd_invent_chg.mas_desc
     *
     * @return the value of prd_invent_chg.mas_desc
     *
     * @ET
     */
    public String getMasDesc() {
        return masDesc;
    }

    /**
     * This method:setMasDesc
     *  prd_invent_chg.mas_desc
     *
     * @param masDesc the value for prd_invent_chg.mas_desc
     *
     * @ET
     */
    public void setMasDesc(String masDesc) {
        this.masDesc = masDesc == null ? null : masDesc.trim();
    }

    /**
     * This method:getOrgId
     * prd_invent_chg.org_id
     *
     * @return the value of prd_invent_chg.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  prd_invent_chg.org_id
     *
     * @param orgId the value for prd_invent_chg.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * prd_invent_chg.sys_id
     *
     * @return the value of prd_invent_chg.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  prd_invent_chg.sys_id
     *
     * @param sysId the value for prd_invent_chg.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * prd_invent_chg.creator
     *
     * @return the value of prd_invent_chg.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  prd_invent_chg.creator
     *
     * @param creator the value for prd_invent_chg.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * prd_invent_chg.modifier
     *
     * @return the value of prd_invent_chg.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  prd_invent_chg.modifier
     *
     * @param modifier the value for prd_invent_chg.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * prd_invent_chg.create_time
     *
     * @return the value of prd_invent_chg.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  prd_invent_chg.create_time
     *
     * @param createTime the value for prd_invent_chg.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * prd_invent_chg.modify_time
     *
     * @return the value of prd_invent_chg.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  prd_invent_chg.modify_time
     *
     * @param modifyTime the value for prd_invent_chg.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}