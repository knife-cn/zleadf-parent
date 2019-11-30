package com.zlead.fplat.entity;

import java.util.Date;

public class PrdCustPrice {
    /**
     * 字段名称: id .
     * 字段定义: prd_cust_price.item_id
     *
     * @ET
     */
    private Integer itemId;

    /**
     * 字段名称: 最小数量 .
     * 字段定义: prd_cust_price.min_qty
     *
     * @ET
     */
    private Integer minQty;

    /**
     * 字段名称: 成本价（元） .
     * 字段定义: prd_cust_price.cost_price
     *
     * @ET
     */
    private Double costPrice;

    /**
     * 字段名称: 经销价（元） .
     * 字段定义: prd_cust_price.batch_price
     *
     * @ET
     */
    private Double batchPrice;

    /**
     * 字段名称: 批发价（元） .
     * 字段定义: prd_cust_price.item_price
     *
     * @ET
     */
    private Double itemPrice;

    /**
     * 字段名称: 零售价（元） .
     * 字段定义: prd_cust_price.retail_price
     *
     * @ET
     */
    private Double retailPrice;

    /**
     * 字段名称: 总数量 .
     * 字段定义: prd_cust_price.total_qty
     *
     * @ET
     */
    private Integer totalQty;

    /**
     * 字段名称: 排序 .
     * 字段定义: prd_cust_price.line_sort
     *
     * @ET
     */
    private Integer lineSort;

    /**
     * 字段名称: 生效日期 .
     * 字段定义: prd_cust_price.eff_date
     *
     * @ET
     */
    private Date effDate;

    /**
     * 字段名称: 状态 .
     * 字段定义: prd_cust_price.price_state
     *
     * @ET
     */
    private String priceState;

    /**
     * 字段名称: 备注 .
     * 字段定义: prd_cust_price.price_desc
     *
     * @ET
     */
    private String priceDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: prd_cust_price.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: prd_cust_price.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: prd_cust_price.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: prd_cust_price.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: prd_cust_price.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: prd_cust_price.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * 字段名称: 净剩余数 .
     * 字段定义: prd_cust_price.spare_qty
     *
     * @ET
     */
    private Integer spareQty;

    /**
     * This method:getItemId
     * prd_cust_price.item_id
     *
     * @return the value of prd_cust_price.item_id
     *
     * @ET
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method:setItemId
     *  prd_cust_price.item_id
     *
     * @param itemId the value for prd_cust_price.item_id
     *
     * @ET
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method:getMinQty
     * prd_cust_price.min_qty
     *
     * @return the value of prd_cust_price.min_qty
     *
     * @ET
     */
    public Integer getMinQty() {
        return minQty;
    }

    /**
     * This method:setMinQty
     *  prd_cust_price.min_qty
     *
     * @param minQty the value for prd_cust_price.min_qty
     *
     * @ET
     */
    public void setMinQty(Integer minQty) {
        this.minQty = minQty;
    }

    /**
     * This method:getCostPrice
     * prd_cust_price.cost_price
     *
     * @return the value of prd_cust_price.cost_price
     *
     * @ET
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * This method:setCostPrice
     *  prd_cust_price.cost_price
     *
     * @param costPrice the value for prd_cust_price.cost_price
     *
     * @ET
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * This method:getBatchPrice
     * prd_cust_price.batch_price
     *
     * @return the value of prd_cust_price.batch_price
     *
     * @ET
     */
    public Double getBatchPrice() {
        return batchPrice;
    }

    /**
     * This method:setBatchPrice
     *  prd_cust_price.batch_price
     *
     * @param batchPrice the value for prd_cust_price.batch_price
     *
     * @ET
     */
    public void setBatchPrice(Double batchPrice) {
        this.batchPrice = batchPrice;
    }

    /**
     * This method:getItemPrice
     * prd_cust_price.item_price
     *
     * @return the value of prd_cust_price.item_price
     *
     * @ET
     */
    public Double getItemPrice() {
        return itemPrice;
    }

    /**
     * This method:setItemPrice
     *  prd_cust_price.item_price
     *
     * @param itemPrice the value for prd_cust_price.item_price
     *
     * @ET
     */
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * This method:getRetailPrice
     * prd_cust_price.retail_price
     *
     * @return the value of prd_cust_price.retail_price
     *
     * @ET
     */
    public Double getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method:setRetailPrice
     *  prd_cust_price.retail_price
     *
     * @param retailPrice the value for prd_cust_price.retail_price
     *
     * @ET
     */
    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method:getTotalQty
     * prd_cust_price.total_qty
     *
     * @return the value of prd_cust_price.total_qty
     *
     * @ET
     */
    public Integer getTotalQty() {
        return totalQty;
    }

    /**
     * This method:setTotalQty
     *  prd_cust_price.total_qty
     *
     * @param totalQty the value for prd_cust_price.total_qty
     *
     * @ET
     */
    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    /**
     * This method:getLineSort
     * prd_cust_price.line_sort
     *
     * @return the value of prd_cust_price.line_sort
     *
     * @ET
     */
    public Integer getLineSort() {
        return lineSort;
    }

    /**
     * This method:setLineSort
     *  prd_cust_price.line_sort
     *
     * @param lineSort the value for prd_cust_price.line_sort
     *
     * @ET
     */
    public void setLineSort(Integer lineSort) {
        this.lineSort = lineSort;
    }

    /**
     * This method:getEffDate
     * prd_cust_price.eff_date
     *
     * @return the value of prd_cust_price.eff_date
     *
     * @ET
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * This method:setEffDate
     *  prd_cust_price.eff_date
     *
     * @param effDate the value for prd_cust_price.eff_date
     *
     * @ET
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * This method:getPriceState
     * prd_cust_price.price_state
     *
     * @return the value of prd_cust_price.price_state
     *
     * @ET
     */
    public String getPriceState() {
        return priceState;
    }

    /**
     * This method:setPriceState
     *  prd_cust_price.price_state
     *
     * @param priceState the value for prd_cust_price.price_state
     *
     * @ET
     */
    public void setPriceState(String priceState) {
        this.priceState = priceState == null ? null : priceState.trim();
    }

    /**
     * This method:getPriceDesc
     * prd_cust_price.price_desc
     *
     * @return the value of prd_cust_price.price_desc
     *
     * @ET
     */
    public String getPriceDesc() {
        return priceDesc;
    }

    /**
     * This method:setPriceDesc
     *  prd_cust_price.price_desc
     *
     * @param priceDesc the value for prd_cust_price.price_desc
     *
     * @ET
     */
    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc == null ? null : priceDesc.trim();
    }

    /**
     * This method:getOrgId
     * prd_cust_price.org_id
     *
     * @return the value of prd_cust_price.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  prd_cust_price.org_id
     *
     * @param orgId the value for prd_cust_price.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * prd_cust_price.sys_id
     *
     * @return the value of prd_cust_price.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  prd_cust_price.sys_id
     *
     * @param sysId the value for prd_cust_price.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * prd_cust_price.creator
     *
     * @return the value of prd_cust_price.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  prd_cust_price.creator
     *
     * @param creator the value for prd_cust_price.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * prd_cust_price.modifier
     *
     * @return the value of prd_cust_price.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  prd_cust_price.modifier
     *
     * @param modifier the value for prd_cust_price.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * prd_cust_price.create_time
     *
     * @return the value of prd_cust_price.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  prd_cust_price.create_time
     *
     * @param createTime the value for prd_cust_price.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * prd_cust_price.modify_time
     *
     * @return the value of prd_cust_price.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  prd_cust_price.modify_time
     *
     * @param modifyTime the value for prd_cust_price.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method:getSpareQty
     * prd_cust_price.spare_qty
     *
     * @return the value of prd_cust_price.spare_qty
     *
     * @ET
     */
    public Integer getSpareQty() {
        return spareQty;
    }

    /**
     * This method:setSpareQty
     *  prd_cust_price.spare_qty
     *
     * @param spareQty the value for prd_cust_price.spare_qty
     *
     * @ET
     */
    public void setSpareQty(Integer spareQty) {
        this.spareQty = spareQty;
    }
}