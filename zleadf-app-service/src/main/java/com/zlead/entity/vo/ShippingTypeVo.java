package com.zlead.entity.vo;

public class ShippingTypeVo {
    /**
     * 配送方式Id
     */
    private Integer typeId;
    /**
     * 配送方式名称
     */
    private String typeName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
