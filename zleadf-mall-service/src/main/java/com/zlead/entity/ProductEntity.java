package com.zlead.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 产品
 *
 * @author fqf
 * @date 2018-07-25 11:41:32
 */
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 分类ID
     */
    private Long catagoryId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 货号
     */
    private String itemNo;
    /**
     * 品牌
     */
    private Long bandId;
    /**
     * 系列
     */
    private Long listId;
    /**
     * 型号
     */
    private Long modelId;
    /**
     * 产品介绍
     */
    private String intro;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 店铺/工厂id
     */
    private Long shopId;
    /**
     * 供应商ID
     */
    private Long supplierShopId;
    /**
     * 代理价
     */
    private BigDecimal agentPrice;
    /**
     * 商品原价
     */
    private BigDecimal supplyPrice;
    /**
     * 建议市场价
     */
    private BigDecimal marketPrice;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 是否上架
     */
    private Integer isMarket;
    /**
     * 是否开启规格
     */
    private Integer isSpec;
    /**
     * 是否显示在首页
     */
    private Integer isHome;
    /**
     * 添加人ID
     */
    private Long userId;
    /**
     * 添加时间
     */
    private Date createDate;
    /**
     *
     */
    private Date updateDate;
    /**
     *
     */
    private String remark;
    /**
     * 是否审核
     */
    private Integer isAudit;

    private List<ProdCatEntity> catEntities;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：分类ID
     */
    public void setCatagoryId(Long catagoryId) {
        this.catagoryId = catagoryId;
    }

    /**
     * 获取：分类ID
     */
    public Long getCatagoryId() {
        return catagoryId;
    }

    /**
     * 设置：产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取：产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置：货号
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    /**
     * 获取：货号
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * 设置：产品介绍
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 获取：产品介绍
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置：排序
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 获取：排序
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 设置：供应商ID
     */
    public void setSupplierShopId(Long supplierShopId) {
        this.supplierShopId = supplierShopId;
    }

    /**
     * 获取：供应商ID
     */
    public Long getSupplierShopId() {
        return supplierShopId;
    }

    /**
     * 设置：代理价
     */
    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

    /**
     * 获取：代理价
     */
    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    /**
     * 设置：商品原价
     */
    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    /**
     * 获取：商品原价
     */
    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    /**
     * 设置：建议市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：建议市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置：折扣
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取：折扣
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * 设置：是否上架
     */
    public void setIsMarket(Integer isMarket) {
        this.isMarket = isMarket;
    }

    /**
     * 获取：是否上架
     */
    public Integer getIsMarket() {
        return isMarket;
    }

    /**
     * 设置：是否开启规格
     */
    public void setIsSpec(Integer isSpec) {
        this.isSpec = isSpec;
    }

    /**
     * 获取：是否开启规格
     */
    public Integer getIsSpec() {
        return isSpec;
    }

    /**
     * 设置：是否显示在首页
     */
    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    /**
     * 获取：是否显示在首页
     */
    public Integer getIsHome() {
        return isHome;
    }

    /**
     * 设置：添加人ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：添加人ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：添加时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：添加时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：
     */
    public String getRemark() {
        return remark;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Long getBandId() {
        return bandId;
    }

    public void setBandId(Long bandId) {
        this.bandId = bandId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getModelId() {
        return modelId;
    }

    public List<ProdCatEntity> getCatEntities() {
        return catEntities;
    }

    public void setCatEntities(List<ProdCatEntity> catEntities) {
        this.catEntities = catEntities;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
