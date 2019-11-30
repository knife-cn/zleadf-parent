package com.zlead.fplat.entity;

import java.util.Date;

public class ShopGoodsrela {
    /**
     * 字段名称: 促销ID .
     * 字段定义: t_shop_goods_rela.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 店铺 .
     * 字段定义: t_shop_goods_rela.shop_id
     *
     * @ET
     */
    private Integer shopId;

    /**
     * 字段名称: 产品 .
     * 字段定义: t_shop_goods_rela.goods_id
     *
     * @ET
     */
    private Integer goodsId;

    /**
     * 字段名称: 显示区域 1首页 2其他 .
     * 字段定义: t_shop_goods_rela.district
     *
     * @ET
     */
    private Integer district;

    /**
     * 字段名称: 排序 .
     * 字段定义: t_shop_goods_rela.sort
     *
     * @ET
     */
    private Integer sort;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: t_shop_goods_rela.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: t_shop_goods_rela.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getId
     * t_shop_goods_rela.id
     *
     * @return the value of t_shop_goods_rela.id
     *
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_shop_goods_rela.id
     *
     * @param id the value for t_shop_goods_rela.id
     *
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getShopId
     * t_shop_goods_rela.shop_id
     *
     * @return the value of t_shop_goods_rela.shop_id
     *
     * @ET
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method:setShopId
     *  t_shop_goods_rela.shop_id
     *
     * @param shopId the value for t_shop_goods_rela.shop_id
     *
     * @ET
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getGoodsId
     * t_shop_goods_rela.goods_id
     *
     * @return the value of t_shop_goods_rela.goods_id
     *
     * @ET
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method:setGoodsId
     *  t_shop_goods_rela.goods_id
     *
     * @param goodsId the value for t_shop_goods_rela.goods_id
     *
     * @ET
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method:getDistrict
     * t_shop_goods_rela.district
     *
     * @return the value of t_shop_goods_rela.district
     *
     * @ET
     */
    public Integer getDistrict() {
        return district;
    }

    /**
     * This method:setDistrict
     *  t_shop_goods_rela.district
     *
     * @param district the value for t_shop_goods_rela.district
     *
     * @ET
     */
    public void setDistrict(Integer district) {
        this.district = district;
    }

    /**
     * This method:getSort
     * t_shop_goods_rela.sort
     *
     * @return the value of t_shop_goods_rela.sort
     *
     * @ET
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method:setSort
     *  t_shop_goods_rela.sort
     *
     * @param sort the value for t_shop_goods_rela.sort
     *
     * @ET
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method:getCreateTime
     * t_shop_goods_rela.create_time
     *
     * @return the value of t_shop_goods_rela.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  t_shop_goods_rela.create_time
     *
     * @param createTime the value for t_shop_goods_rela.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * t_shop_goods_rela.modify_time
     *
     * @return the value of t_shop_goods_rela.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  t_shop_goods_rela.modify_time
     *
     * @param modifyTime the value for t_shop_goods_rela.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}