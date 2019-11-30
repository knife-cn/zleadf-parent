package com.zlead.entity.dto;

import java.util.Date;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/17.
 * @Desoription TODO
 */
public class GoodsPageDto {

    private int id;

    /**商品名称 **/
    private String goodsName;

    /**商品价格 **/
    private Double price;

    /**展示图片 **/
    private String image;

    /**
     * 活动生效时间
     */
    private String effDate;
    /**
     * 活动结束时间
     */
    private String expDate;
    /**
     * 活动Id
     */
    private Integer actId;
    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商品系列Id
     */
    private Integer listId;

    /**
     * 商品类型  0 普通商品，2为活动商品，1为积分商品
     */
    private  Integer prodType;



    /**
     * 商品上架状态  1.上架 0.未上架
     */
    private Integer isMarket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
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

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(Integer isMarket) {
        this.isMarket = isMarket;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }
}
