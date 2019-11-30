package com.zlead.fplat.entity;

import java.util.Date;

public class OaItemState {
    /**
     * 字段名称: 商品 .
     * 字段定义: oa_item_state.item_id
     *
     * @ET
     */
    private Integer itemId;

    /**
     * 字段名称: 展销价 .
     * 字段定义: oa_item_state.sale_price
     *
     * @ET
     */
    private Double salePrice;

    /**
     * 字段名称: 是否显示价格 .
     * 字段定义: oa_item_state.show_price
     *
     * @ET
     */
    private String showPrice;

    /**
     * 字段名称: 可见代理商范围 .
     * 字段定义: oa_item_state.cust_levels
     *
     * @ET
     */
    private String custLevels;

    /**
     * 字段名称: 显示端口 .
     * 字段定义: oa_item_state.terminal
     *
     * @ET
     */
    private String terminal;

    /**
     * 字段名称: 上架库存 .
     * 字段定义: oa_item_state.sale_qty
     *
     * @ET
     */
    private Integer saleQty;

    /**
     * 字段名称: 是否显示库存 .
     * 字段定义: oa_item_state.show_qty
     *
     * @ET
     */
    private String showQty;

    /**
     * 字段名称: 自动下架日期 .
     * 字段定义: oa_item_state.auto_date
     *
     * @ET
     */
    private Date autoDate;

    /**
     * 字段名称: 上架时间 .
     * 字段定义: oa_item_state.on_date
     *
     * @ET
     */
    private Date onDate;

    /**
     * 字段名称: 下架时间 .
     * 字段定义: oa_item_state.off_date
     *
     * @ET
     */
    private Date offDate;

    /**
     * 字段名称: 机构 .
     * 字段定义: oa_item_state.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: oa_item_state.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: oa_item_state.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: oa_item_state.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改人编号 .
     * 字段定义: oa_item_state.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: oa_item_state.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getItemId
     * oa_item_state.item_id
     *
     * @return the value of oa_item_state.item_id
     *
     * @ET
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method:setItemId
     *  oa_item_state.item_id
     *
     * @param itemId the value for oa_item_state.item_id
     *
     * @ET
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method:getSalePrice
     * oa_item_state.sale_price
     *
     * @return the value of oa_item_state.sale_price
     *
     * @ET
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * This method:setSalePrice
     *  oa_item_state.sale_price
     *
     * @param salePrice the value for oa_item_state.sale_price
     *
     * @ET
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * This method:getShowPrice
     * oa_item_state.show_price
     *
     * @return the value of oa_item_state.show_price
     *
     * @ET
     */
    public String getShowPrice() {
        return showPrice;
    }

    /**
     * This method:setShowPrice
     *  oa_item_state.show_price
     *
     * @param showPrice the value for oa_item_state.show_price
     *
     * @ET
     */
    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice == null ? null : showPrice.trim();
    }

    /**
     * This method:getCustLevels
     * oa_item_state.cust_levels
     *
     * @return the value of oa_item_state.cust_levels
     *
     * @ET
     */
    public String getCustLevels() {
        return custLevels;
    }

    /**
     * This method:setCustLevels
     *  oa_item_state.cust_levels
     *
     * @param custLevels the value for oa_item_state.cust_levels
     *
     * @ET
     */
    public void setCustLevels(String custLevels) {
        this.custLevels = custLevels == null ? null : custLevels.trim();
    }

    /**
     * This method:getTerminal
     * oa_item_state.terminal
     *
     * @return the value of oa_item_state.terminal
     *
     * @ET
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * This method:setTerminal
     *  oa_item_state.terminal
     *
     * @param terminal the value for oa_item_state.terminal
     *
     * @ET
     */
    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    /**
     * This method:getSaleQty
     * oa_item_state.sale_qty
     *
     * @return the value of oa_item_state.sale_qty
     *
     * @ET
     */
    public Integer getSaleQty() {
        return saleQty;
    }

    /**
     * This method:setSaleQty
     *  oa_item_state.sale_qty
     *
     * @param saleQty the value for oa_item_state.sale_qty
     *
     * @ET
     */
    public void setSaleQty(Integer saleQty) {
        this.saleQty = saleQty;
    }

    /**
     * This method:getShowQty
     * oa_item_state.show_qty
     *
     * @return the value of oa_item_state.show_qty
     *
     * @ET
     */
    public String getShowQty() {
        return showQty;
    }

    /**
     * This method:setShowQty
     *  oa_item_state.show_qty
     *
     * @param showQty the value for oa_item_state.show_qty
     *
     * @ET
     */
    public void setShowQty(String showQty) {
        this.showQty = showQty == null ? null : showQty.trim();
    }

    /**
     * This method:getAutoDate
     * oa_item_state.auto_date
     *
     * @return the value of oa_item_state.auto_date
     *
     * @ET
     */
    public Date getAutoDate() {
        return autoDate;
    }

    /**
     * This method:setAutoDate
     *  oa_item_state.auto_date
     *
     * @param autoDate the value for oa_item_state.auto_date
     *
     * @ET
     */
    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }

    /**
     * This method:getOnDate
     * oa_item_state.on_date
     *
     * @return the value of oa_item_state.on_date
     *
     * @ET
     */
    public Date getOnDate() {
        return onDate;
    }

    /**
     * This method:setOnDate
     *  oa_item_state.on_date
     *
     * @param onDate the value for oa_item_state.on_date
     *
     * @ET
     */
    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    /**
     * This method:getOffDate
     * oa_item_state.off_date
     *
     * @return the value of oa_item_state.off_date
     *
     * @ET
     */
    public Date getOffDate() {
        return offDate;
    }

    /**
     * This method:setOffDate
     *  oa_item_state.off_date
     *
     * @param offDate the value for oa_item_state.off_date
     *
     * @ET
     */
    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    /**
     * This method:getOrgId
     * oa_item_state.org_id
     *
     * @return the value of oa_item_state.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  oa_item_state.org_id
     *
     * @param orgId the value for oa_item_state.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * oa_item_state.sys_id
     *
     * @return the value of oa_item_state.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  oa_item_state.sys_id
     *
     * @param sysId the value for oa_item_state.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * oa_item_state.creator
     *
     * @return the value of oa_item_state.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  oa_item_state.creator
     *
     * @param creator the value for oa_item_state.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getCreateTime
     * oa_item_state.create_time
     *
     * @return the value of oa_item_state.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  oa_item_state.create_time
     *
     * @param createTime the value for oa_item_state.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifier
     * oa_item_state.modifier
     *
     * @return the value of oa_item_state.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  oa_item_state.modifier
     *
     * @param modifier the value for oa_item_state.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getModifyTime
     * oa_item_state.modify_time
     *
     * @return the value of oa_item_state.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  oa_item_state.modify_time
     *
     * @param modifyTime the value for oa_item_state.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}