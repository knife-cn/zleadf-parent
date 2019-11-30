package com.zlead.fplat.entity;

public class Goodscat {
    /**
     * id
     */
    private Long id;
    /**
     * t_product.id
     */
    private Long prodId;
    /**
     * t_goods.id
     */
    private Long goodsId;
    /**
     * crm_prd_cat.cat_id
     */
    private Long catId;
    /**
     * crm_prd_cat.cat_name
     */
    private String catName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态 0 未启用 1启用
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
