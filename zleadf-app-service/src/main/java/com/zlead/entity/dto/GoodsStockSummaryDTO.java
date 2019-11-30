package com.zlead.entity.dto;

import java.util.Set;

public class GoodsStockSummaryDTO {

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 库房
     */
    private Integer warehouseId;

    /**
     * 品牌
     */
    private Integer brandId;

    /**
     * 商品类型
     */
    private Integer modelId;

    /**
     * 商品系列
     */
    private Integer listId;

    /**
     * 商品分类
     */
    private Integer catagoryId;

    private Set<Integer> whIds;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(Integer catagoryId) {
        this.catagoryId = catagoryId;
    }


    public Set<Integer> getWhIds() {
        return whIds;
    }

    public void setWhIds(Set<Integer> whIds) {
        this.whIds = whIds;
    }
}
