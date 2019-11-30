package com.zlead.entity.vo;

import com.zlead.entity.dto.GoodsAttrValDto;

import java.math.BigDecimal;
import java.util.List;

public class OrderGoodsDetailVo {
    private Integer goodsId;
    private String goodsName;
    private String goodsImg;
    private BigDecimal price;
    private Integer count;
    private List<GoodsAttrValDto> goodsAttrValDtos;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<GoodsAttrValDto> getGoodsAttrValDtos() {
        return goodsAttrValDtos;
    }

    public void setGoodsAttrValDtos(List<GoodsAttrValDto> goodsAttrValDtos) {
        this.goodsAttrValDtos = goodsAttrValDtos;
    }
}
