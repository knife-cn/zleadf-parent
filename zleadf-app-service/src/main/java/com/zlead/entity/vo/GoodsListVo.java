package com.zlead.entity.vo;

import java.math.BigDecimal;

public class GoodsListVo {
    private Integer goodsId;
    private Integer if_show_price;
    private Integer if_show_stock;
    private String fullName;
    private String firstImg;
    private BigDecimal marketPrice;
    private Integer stock;
    private String stockType;//上架库存类型:0-空、1-自定义/2-实际库存
    private BigDecimal costPrice;
    private BigDecimal batchPrice;
    private BigDecimal itemPrice;
    private BigDecimal retailPrice;
    private Integer whId;//库存Id

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getIf_show_price() {
        return if_show_price;
    }

    public void setIf_show_price(Integer if_show_price) {
        this.if_show_price = if_show_price;
    }

    public Integer getIf_show_stock() {
        return if_show_stock;
    }

    public void setIf_show_stock(Integer if_show_stock) {
        this.if_show_stock = if_show_stock;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(BigDecimal batchPrice) {
        this.batchPrice = batchPrice;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getWhId() {
        return whId;
    }

    public void setWhId(Integer whId) {
        this.whId = whId;
    }
}
