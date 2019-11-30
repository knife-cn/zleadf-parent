package com.zlead.fplat.entity;

public class Prodattrval {
    /**
     * 字段名称: 唯一ID .
     * 字段定义: t_prod_attr_val.id
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称: 主键 .
     * 字段定义: t_prod_attr_val.value_id
     *
     * @ET
     */
    private Integer valueId;

    /**
     * 字段名称: 产品id .
     * 字段定义: t_prod_attr_val.prod_id
     *
     * @ET
     */
    private Integer prodId;

    /**
     * 字段名称: 参数名称id .
     * 字段定义: t_prod_attr_val.attr_id
     *
     * @ET
     */
    private Integer attrId;

    /**
     * 字段名称: 参数名称 .
     * 字段定义: t_prod_attr_val.attr_name
     *
     * @ET
     */
    private String attrName;

    /**
     * 字段名称: 参数值 .
     * 字段定义: t_prod_attr_val.value_name
     *
     * @ET
     */
    private String valueName;

    /**
     * 字段名称: 排序 .
     * 字段定义: t_prod_attr_val.line_sort
     *
     * @ET
     */
    private Integer lineSort;

    /**
     * This method:getId
     * t_prod_attr_val.id
     *
     * @return the value of t_prod_attr_val.id
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_prod_attr_val.id
     *
     * @param id the value for t_prod_attr_val.id
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getValueId
     * t_prod_attr_val.value_id
     *
     * @return the value of t_prod_attr_val.value_id
     *
     * @ET
     */
    public Integer getValueId() {
        return valueId;
    }

    /**
     * This method:setValueId
     *  t_prod_attr_val.value_id
     *
     * @param valueId the value for t_prod_attr_val.value_id
     *
     * @ET
     */
    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    /**
     * This method:getProdId
     * t_prod_attr_val.prod_id
     *
     * @return the value of t_prod_attr_val.prod_id
     *
     * @ET
     */
    public Integer getProdId() {
        return prodId;
    }

    /**
     * This method:setProdId
     *  t_prod_attr_val.prod_id
     *
     * @param prodId the value for t_prod_attr_val.prod_id
     *
     * @ET
     */
    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    /**
     * This method:getAttrId
     * t_prod_attr_val.attr_id
     *
     * @return the value of t_prod_attr_val.attr_id
     *
     * @ET
     */
    public Integer getAttrId() {
        return attrId;
    }

    /**
     * This method:setAttrId
     *  t_prod_attr_val.attr_id
     *
     * @param attrId the value for t_prod_attr_val.attr_id
     *
     * @ET
     */
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    /**
     * This method:getAttrName
     * t_prod_attr_val.attr_name
     *
     * @return the value of t_prod_attr_val.attr_name
     *
     * @ET
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * This method:setAttrName
     *  t_prod_attr_val.attr_name
     *
     * @param attrName the value for t_prod_attr_val.attr_name
     *
     * @ET
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    /**
     * This method:getValueName
     * t_prod_attr_val.value_name
     *
     * @return the value of t_prod_attr_val.value_name
     *
     * @ET
     */
    public String getValueName() {
        return valueName;
    }

    /**
     * This method:setValueName
     *  t_prod_attr_val.value_name
     *
     * @param valueName the value for t_prod_attr_val.value_name
     *
     * @ET
     */
    public void setValueName(String valueName) {
        this.valueName = valueName == null ? null : valueName.trim();
    }

    /**
     * This method:getLineSort
     * t_prod_attr_val.line_sort
     *
     * @return the value of t_prod_attr_val.line_sort
     *
     * @ET
     */
    public Integer getLineSort() {
        return lineSort;
    }

    /**
     * This method:setLineSort
     *  t_prod_attr_val.line_sort
     *
     * @param lineSort the value for t_prod_attr_val.line_sort
     *
     * @ET
     */
    public void setLineSort(Integer lineSort) {
        this.lineSort = lineSort;
    }
}