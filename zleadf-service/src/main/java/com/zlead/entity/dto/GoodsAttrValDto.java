package com.zlead.entity.dto;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/17.
 * @Desoription TODO
 */
public class GoodsAttrValDto {

    private Integer goodsId;

    private String name;

    private String attrValue;

    private String attrName;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
