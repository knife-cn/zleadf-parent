package com.zlead.fplat.entity;

import java.math.BigDecimal;

public class Marketgoods {
    /**
     * 字段名称: id .
     * 字段定义: oa_market_goods.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 活动id .
     * 字段定义: oa_market_goods.act_id
     *
     * @ET
     */
    private Integer actId;

    /**
     * 字段名称: 商品 .
     * 字段定义: oa_market_goods.goods_id
     *
     * @ET
     */
    private Integer goodsId;

    /**
     * 字段名称: 排序 .
     * 字段定义: oa_market_goods.sale_sort
     *
     * @ET
     */
    private Integer saleSort;

    /**
     * 字段名称: 活动商品数量 .
     * 字段定义: oa_market_goods.sale_qty
     *
     * @ET
     */
    private Integer saleQty;

    /**
     * 字段名称: 活动商品价格（元） .
     * 字段定义: oa_market_goods.sale_price
     *
     * @ET
     */
    private BigDecimal salePrice;

    /**
     * This method:getId
     * oa_market_goods.id
     *
     * @return the value of oa_market_goods.id
     *
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     *  oa_market_goods.id
     *
     * @param id the value for oa_market_goods.id
     *
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getActId
     * oa_market_goods.act_id
     *
     * @return the value of oa_market_goods.act_id
     *
     * @ET
     */
    public Integer getActId() {
        return actId;
    }

    /**
     * This method:setActId
     *  oa_market_goods.act_id
     *
     * @param actId the value for oa_market_goods.act_id
     *
     * @ET
     */
    public void setActId(Integer actId) {
        this.actId = actId;
    }

    /**
     * This method:getGoodsId
     * oa_market_goods.goods_id
     *
     * @return the value of oa_market_goods.goods_id
     *
     * @ET
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method:setGoodsId
     *  oa_market_goods.goods_id
     *
     * @param goodsId the value for oa_market_goods.goods_id
     *
     * @ET
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method:getSaleSort
     * oa_market_goods.sale_sort
     *
     * @return the value of oa_market_goods.sale_sort
     *
     * @ET
     */
    public Integer getSaleSort() {
        return saleSort;
    }

    /**
     * This method:setSaleSort
     *  oa_market_goods.sale_sort
     *
     * @param saleSort the value for oa_market_goods.sale_sort
     *
     * @ET
     */
    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }

    /**
     * This method:getSaleQty
     * oa_market_goods.sale_qty
     *
     * @return the value of oa_market_goods.sale_qty
     *
     * @ET
     */
    public Integer getSaleQty() {
        return saleQty;
    }

    /**
     * This method:setSaleQty
     *  oa_market_goods.sale_qty
     *
     * @param saleQty the value for oa_market_goods.sale_qty
     *
     * @ET
     */
    public void setSaleQty(Integer saleQty) {
        this.saleQty = saleQty;
    }

    /**
     * This method:getSalePrice
     * oa_market_goods.sale_price
     *
     * @return the value of oa_market_goods.sale_price
     *
     * @ET
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * This method:setSalePrice
     *  oa_market_goods.sale_price
     *
     * @param salePrice the value for oa_market_goods.sale_price
     *
     * @ET
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}