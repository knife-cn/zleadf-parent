package com.zlead.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class OaItemState implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    private Integer itemId;
    /**
     *展销价
     */
    private Double salePrice;
    /**
     *是否显示价格
     */
    private String showPrice;
    /**
     *可见代理商范围
     */
    private String custLevels;
    /**
     * 显示端口
     */
    private String terminal;
    /**
     *上架库存
     */
    private Integer saleQty;
    /**
     *是否显示库存
     */
    private String showQty;
    /**
     *自动下架日期
     */
    private Date autoDate;
    /**
     *上架时间
     */
    private Date onDate;
    /**
     *下架时间
     */
    private Date offDate;
    /**
     *机构
     */
    private Integer orgId;
    /**
     *系统
     */
    private Integer sysId;
    /**
     *创建人
     */
    private Integer creator;
    /**
     *创建日期
     */
    private Date createTime;
    /**
     *修改人编号
     */
    private Integer modifier;
    /**
     *修改日期
     */
    private Date modifyTime;
    /**
     *
     */
    private String saleState;
    /**
     *
     */
    private String offType;
    /**
     *
     */
    private String levelNames;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice == null ? null : showPrice.trim();
    }

    public String getCustLevels() {
        return custLevels;
    }

    public void setCustLevels(String custLevels) {
        this.custLevels = custLevels == null ? null : custLevels.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public Integer getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(Integer saleQty) {
        this.saleQty = saleQty;
    }

    public String getShowQty() {
        return showQty;
    }

    public void setShowQty(String showQty) {
        this.showQty = showQty == null ? null : showQty.trim();
    }

    public Date getAutoDate() {
        return autoDate;
    }

    public void setAutoDate(Date autoDate) {
        this.autoDate = autoDate;
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getSaleState() {
        return saleState;
    }

    public void setSaleState(String saleState) {
        this.saleState = saleState == null ? null : saleState.trim();
    }

    public String getOffType() {
        return offType;
    }

    public void setOffType(String offType) {
        this.offType = offType == null ? null : offType.trim();
    }

    public String getLevelNames() {
        return levelNames;
    }

    public void setLevelNames(String levelNames) {
        this.levelNames = levelNames == null ? null : levelNames.trim();
    }
}