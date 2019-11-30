package com.zlead.fplat.entity.vo;

import java.io.Serializable;

/**
 * 商品分类信息
 */
public class KindVo implements Serializable {
    private Integer shopId;//店铺ID
    private Integer brandId;//品牌ID
    private String brandName;//品牌名称
    private Integer listId;//系列ID
    private String listName;//系列名称
    private Integer modelId;//型号ID
    private String modelName;//型号名称
    private Integer catId;//分类ID
    private String catName;//分类名称
    private String catDesc;//分类备注

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("【");
        sb.append("shopId=").append(this.shopId);
        sb.append(",brandId=").append(this.brandId);
        sb.append(",brandName=").append(this.brandName);
        sb.append(",listId=").append(this.listId);
        sb.append(",listName=").append(this.listName);
        sb.append(",catId=").append(this.catId);
        sb.append("】");
        return sb.toString();
    }
}
