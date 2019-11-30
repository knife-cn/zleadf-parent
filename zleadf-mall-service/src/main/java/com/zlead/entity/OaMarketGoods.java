package com.zlead.entity;

import java.math.BigDecimal;

public class OaMarketGoods {
    private Integer id;

    private Integer actId;

    private Integer goodsId;

    private Integer saleSort;

    private Integer saleQty;

    private BigDecimal salePrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSaleSort() {
        return saleSort;
    }

    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }

    public Integer getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(Integer saleQty) {
        this.saleQty = saleQty;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}