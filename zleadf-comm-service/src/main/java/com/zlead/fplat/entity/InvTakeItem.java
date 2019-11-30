package com.zlead.fplat.entity;

public class InvTakeItem {
    /**
     * 字段名称: 明细id .
     * 字段定义: inv_take_item.detail_id
     *
     * @ET
     */
    private Integer detailId;

    /**
     * 字段名称: 盘点单id .
     * 字段定义: inv_take_item.bill_id
     *
     * @ET
     */
    private Integer billId;

    /**
     * 字段名称: 排序 .
     * 字段定义: inv_take_item.line_sort
     *
     * @ET
     */
    private Integer lineSort;

    /**
     * 字段名称: 物料 .
     * 字段定义: inv_take_item.item_id
     *
     * @ET
     */
    private Integer itemId;

    /**
     * 字段名称: 计量单位 .
     * 字段定义: inv_take_item.item_uom
     *
     * @ET
     */
    private String itemUom;

    /**
     * 字段名称: 库存数 .
     * 字段定义: inv_take_item.inv_qty
     *
     * @ET
     */
    private Integer invQty;

    /**
     * 字段名称: 盘点数 .
     * 字段定义: inv_take_item.line_qty
     *
     * @ET
     */
    private Integer lineQty;

    /**
     * 字段名称: 差异数 .
     * 字段定义: inv_take_item.ldef_qty
     *
     * @ET
     */
    private Integer ldefQty;

    /**
     * 字段名称: 成本价 .
     * 字段定义: inv_take_item.cost_price
     *
     * @ET
     */
    private Double costPrice;

    /**
     * 字段名称: 盘点额 .
     * 字段定义: inv_take_item.line_amt
     *
     * @ET
     */
    private Double lineAmt;

    /**
     * 字段名称: 账面额 .
     * 字段定义: inv_take_item.linc_amt
     *
     * @ET
     */
    private Double lincAmt;

    /**
     * 字段名称: 差异额 .
     * 字段定义: inv_take_item.ldef_amt
     *
     * @ET
     */
    private Double ldefAmt;

    /**
     * 字段名称: 行备注 .
     * 字段定义: inv_take_item.line_desc
     *
     * @ET
     */
    private String lineDesc;

    /**
     * This method:getDetailId
     * inv_take_item.detail_id
     *
     * @return the value of inv_take_item.detail_id
     *
     * @ET
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * This method:setDetailId
     *  inv_take_item.detail_id
     *
     * @param detailId the value for inv_take_item.detail_id
     *
     * @ET
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * This method:getBillId
     * inv_take_item.bill_id
     *
     * @return the value of inv_take_item.bill_id
     *
     * @ET
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method:setBillId
     *  inv_take_item.bill_id
     *
     * @param billId the value for inv_take_item.bill_id
     *
     * @ET
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method:getLineSort
     * inv_take_item.line_sort
     *
     * @return the value of inv_take_item.line_sort
     *
     * @ET
     */
    public Integer getLineSort() {
        return lineSort;
    }

    /**
     * This method:setLineSort
     *  inv_take_item.line_sort
     *
     * @param lineSort the value for inv_take_item.line_sort
     *
     * @ET
     */
    public void setLineSort(Integer lineSort) {
        this.lineSort = lineSort;
    }

    /**
     * This method:getItemId
     * inv_take_item.item_id
     *
     * @return the value of inv_take_item.item_id
     *
     * @ET
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method:setItemId
     *  inv_take_item.item_id
     *
     * @param itemId the value for inv_take_item.item_id
     *
     * @ET
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method:getItemUom
     * inv_take_item.item_uom
     *
     * @return the value of inv_take_item.item_uom
     *
     * @ET
     */
    public String getItemUom() {
        return itemUom;
    }

    /**
     * This method:setItemUom
     *  inv_take_item.item_uom
     *
     * @param itemUom the value for inv_take_item.item_uom
     *
     * @ET
     */
    public void setItemUom(String itemUom) {
        this.itemUom = itemUom == null ? null : itemUom.trim();
    }

    /**
     * This method:getInvQty
     * inv_take_item.inv_qty
     *
     * @return the value of inv_take_item.inv_qty
     *
     * @ET
     */
    public Integer getInvQty() {
        return invQty;
    }

    /**
     * This method:setInvQty
     *  inv_take_item.inv_qty
     *
     * @param invQty the value for inv_take_item.inv_qty
     *
     * @ET
     */
    public void setInvQty(Integer invQty) {
        this.invQty = invQty;
    }

    /**
     * This method:getLineQty
     * inv_take_item.line_qty
     *
     * @return the value of inv_take_item.line_qty
     *
     * @ET
     */
    public Integer getLineQty() {
        return lineQty;
    }

    /**
     * This method:setLineQty
     *  inv_take_item.line_qty
     *
     * @param lineQty the value for inv_take_item.line_qty
     *
     * @ET
     */
    public void setLineQty(Integer lineQty) {
        this.lineQty = lineQty;
    }

    /**
     * This method:getLdefQty
     * inv_take_item.ldef_qty
     *
     * @return the value of inv_take_item.ldef_qty
     *
     * @ET
     */
    public Integer getLdefQty() {
        return ldefQty;
    }

    /**
     * This method:setLdefQty
     *  inv_take_item.ldef_qty
     *
     * @param ldefQty the value for inv_take_item.ldef_qty
     *
     * @ET
     */
    public void setLdefQty(Integer ldefQty) {
        this.ldefQty = ldefQty;
    }

    /**
     * This method:getCostPrice
     * inv_take_item.cost_price
     *
     * @return the value of inv_take_item.cost_price
     *
     * @ET
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * This method:setCostPrice
     *  inv_take_item.cost_price
     *
     * @param costPrice the value for inv_take_item.cost_price
     *
     * @ET
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * This method:getLineAmt
     * inv_take_item.line_amt
     *
     * @return the value of inv_take_item.line_amt
     *
     * @ET
     */
    public Double getLineAmt() {
        return lineAmt;
    }

    /**
     * This method:setLineAmt
     *  inv_take_item.line_amt
     *
     * @param lineAmt the value for inv_take_item.line_amt
     *
     * @ET
     */
    public void setLineAmt(Double lineAmt) {
        this.lineAmt = lineAmt;
    }

    /**
     * This method:getLincAmt
     * inv_take_item.linc_amt
     *
     * @return the value of inv_take_item.linc_amt
     *
     * @ET
     */
    public Double getLincAmt() {
        return lincAmt;
    }

    /**
     * This method:setLincAmt
     *  inv_take_item.linc_amt
     *
     * @param lincAmt the value for inv_take_item.linc_amt
     *
     * @ET
     */
    public void setLincAmt(Double lincAmt) {
        this.lincAmt = lincAmt;
    }

    /**
     * This method:getLdefAmt
     * inv_take_item.ldef_amt
     *
     * @return the value of inv_take_item.ldef_amt
     *
     * @ET
     */
    public Double getLdefAmt() {
        return ldefAmt;
    }

    /**
     * This method:setLdefAmt
     *  inv_take_item.ldef_amt
     *
     * @param ldefAmt the value for inv_take_item.ldef_amt
     *
     * @ET
     */
    public void setLdefAmt(Double ldefAmt) {
        this.ldefAmt = ldefAmt;
    }

    /**
     * This method:getLineDesc
     * inv_take_item.line_desc
     *
     * @return the value of inv_take_item.line_desc
     *
     * @ET
     */
    public String getLineDesc() {
        return lineDesc;
    }

    /**
     * This method:setLineDesc
     *  inv_take_item.line_desc
     *
     * @param lineDesc the value for inv_take_item.line_desc
     *
     * @ET
     */
    public void setLineDesc(String lineDesc) {
        this.lineDesc = lineDesc == null ? null : lineDesc.trim();
    }
}