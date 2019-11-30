package com.zlead.fplat.entity;

public class Goodsattr {
    /**
     * 字段名称: 唯一ID .
     * 字段定义: t_goods_attr.id
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称: 商品id .
     * 字段定义: t_goods_attr.goods_id
     *
     * @ET
     */
    private Integer goodsId;

    /**
     * 字段名称: 产品id .
     * 字段定义: t_goods_attr.prod_id
     *
     * @ET
     */
    private Integer prodId;

    /**
     * 字段名称: 参数名称id .
     * 字段定义: t_goods_attr.attr_id
     *
     * @ET
     */
    private Integer attrId;

    /**
     * 字段名称: 参数名称 .
     * 字段定义: t_goods_attr.attr_name
     *
     * @ET
     */
    private String attrName;

    /**
     * 字段名称: 排序 .
     * 字段定义: t_goods_attr.sort
     *
     * @ET
     */
    private Integer sort;

    /**
     * 字段名称: 是否启用 0 未启用 1 启用 .
     * 字段定义: t_goods_attr.status
     *
     * @ET
     */
    private Integer status;

    /**
     * This method:getId
     * t_goods_attr.id
     *
     * @return the value of t_goods_attr.id
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_goods_attr.id
     *
     * @param id the value for t_goods_attr.id
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getGoodsId
     * t_goods_attr.goods_id
     *
     * @return the value of t_goods_attr.goods_id
     *
     * @ET
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method:setGoodsId
     *  t_goods_attr.goods_id
     *
     * @param goodsId the value for t_goods_attr.goods_id
     *
     * @ET
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method:getProdId
     * t_goods_attr.prod_id
     *
     * @return the value of t_goods_attr.prod_id
     *
     * @ET
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * This method:setProdId
     *  t_goods_attr.prod_id
     *
     * @param prodId the value for t_goods_attr.prod_id
     *
     * @ET
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    /**
     * This method:getAttrId
     * t_goods_attr.attr_id
     *
     * @return the value of t_goods_attr.attr_id
     *
     * @ET
     */
    public Integer getAttrId() {
        return attrId;
    }

    /**
     * This method:setAttrId
     *  t_goods_attr.attr_id
     *
     * @param attrId the value for t_goods_attr.attr_id
     *
     * @ET
     */
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    /**
     * This method:getAttrName
     * t_goods_attr.attr_name
     *
     * @return the value of t_goods_attr.attr_name
     *
     * @ET
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * This method:setAttrName
     *  t_goods_attr.attr_name
     *
     * @param attrName the value for t_goods_attr.attr_name
     *
     * @ET
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    /**
     * This method:getSort
     * t_goods_attr.sort
     *
     * @return the value of t_goods_attr.sort
     *
     * @ET
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method:setSort
     *  t_goods_attr.sort
     *
     * @param sort the value for t_goods_attr.sort
     *
     * @ET
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method:getStatus
     * t_goods_attr.status
     *
     * @return the value of t_goods_attr.status
     *
     * @ET
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method:setStatus
     *  t_goods_attr.status
     *
     * @param status the value for t_goods_attr.status
     *
     * @ET
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}