package com.zlead.entity.vo;

import com.zlead.entity.ShoppingCartEntity;

import java.math.BigDecimal;

/**
 * 购物车扩展
 */
public class ShoppingCartVo extends ShoppingCartEntity {
    private BigDecimal goodsPrice; // 商品价格
    private Long point; // 商品所值积分
    private BigDecimal pointPrice; // 商品所值金额
    private Integer stock; // 商品库存
    private String goodsName; // 商品名称
    private String specNames; // 商品规格
    private String goodsImg; // 商品图片
    private String shopName; // 供应商名称
    private String storeName; // 店铺名称
    private Integer isMarketable; // 是否上架

    public BigDecimal getGoodsPrice() {
        if (goodsPrice == null) { // 防止商品被删除的情况
            return new BigDecimal(999999);
        }else{
            return this.goodsPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        //return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSpecNames() {
        return specNames;
    }

    public void setSpecNames(String specNames) {
        this.specNames = specNames;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public Integer getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(Integer isMarketable) {
        this.isMarketable = isMarketable;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public BigDecimal getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(BigDecimal pointPrice) {
        this.pointPrice = pointPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
