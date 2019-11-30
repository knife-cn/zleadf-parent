package com.zlead.entity.vo;

import java.math.BigDecimal;

public class GoodsStockSummaryListVO {

    /**
     * 商品(主键)id
     */
    private Long goodsId = 0L;

    /**
     * 品牌名称
     */
    private String brandName = "";

    /**
     * 商品型号
     */
    private String goodsModel = "";

    /**
     * 商品系列
     */
    private String goodsList = "";

    /**
     * 商品规格
     */
    private String goodsSpec = "";

    /**
     * 参数名称
     */
    private String attrName = "";

    /**
     * 商品全称
     */
    private String goodsFullName = "";

    /**
     * 库房名称
     */
    private String warehouseName = "";

    /**
     * 库存数量
     */
    private Integer stock = 0;

    /**
     * 批发价
     */
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * 零售价
     */
    private BigDecimal marketPrice = BigDecimal.ZERO;

    /**
     * 成本价
     */
    private BigDecimal supplyPrice = BigDecimal.ZERO;

    /**
     * 经销价
     */
    private BigDecimal agentPrice = BigDecimal.ZERO;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }

    public String getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(String goodsList) {
        this.goodsList = goodsList;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getGoodsFullName() {
        return goodsFullName;
    }

    public void setGoodsFullName(String goodsFullName) {
        this.goodsFullName = goodsFullName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(BigDecimal supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public BigDecimal getAgentPrice() {
        return agentPrice;
    }

    public void setAgentPrice(BigDecimal agentPrice) {
        this.agentPrice = agentPrice;
    }

}
