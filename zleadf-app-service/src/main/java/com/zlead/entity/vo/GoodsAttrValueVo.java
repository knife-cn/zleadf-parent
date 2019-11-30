package com.zlead.entity.vo;

import java.math.BigDecimal;

public class GoodsAttrValueVo {
    private Integer goodsId;
    private BigDecimal showPrice;
    private Integer stock;
    private String stockType;//上架库存:0-空、1-自定义/2-实际库存

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(BigDecimal showPrice) {
        this.showPrice = showPrice;
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
}
