package com.zlead.entity;

public class ProdCatEntity {
    /**
     * id
     */
    private Long id;
    /**
     * t_product.id
     */
    private Long prodId;
    /**
     * crm_prd_cat.cat_id
     */
    private Long catId;
    /**
     * crm_prd_cat.cat_name
     */
    private String catName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
