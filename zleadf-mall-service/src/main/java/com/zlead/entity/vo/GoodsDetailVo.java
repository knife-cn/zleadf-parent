package com.zlead.entity.vo;

import java.math.BigDecimal;
import java.util.List;

public class GoodsDetailVo {
    private Integer goodsId;
    private Integer prodId;
    private String[] goodsImgs;
    private String imgs;
    private String fullName;
    private BigDecimal showPrice;
    private String brandName;
    private Integer stock;
    private Integer prodcnt;
    private String intro;
    private String remark;
    private List<GoodsAttrValVo> goodsAttrValVos;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String[] getGoodsImgs() {
        return goodsImgs;
    }

    public void setGoodsImgs(String[] goodsImgs) {
        this.goodsImgs = goodsImgs;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(BigDecimal showPrice) {
        this.showPrice = showPrice;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getProdcnt() {
        return prodcnt;
    }

    public void setProdcnt(Integer prodcnt) {
        this.prodcnt = prodcnt;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<GoodsAttrValVo> getGoodsAttrValVos() {
        return goodsAttrValVos;
    }

    public void setGoodsAttrValVos(List<GoodsAttrValVo> goodsAttrValVos) {
        this.goodsAttrValVos = goodsAttrValVos;
    }
}
