package com.zlead.entity;

public class Region {
    /**
     * 字段名称:  .
     * 字段定义: region.ID
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_CODE
     *
     * @ET
     */
    private String regionCode;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_NAME
     *
     * @ET
     */
    private String regionName;

    /**
     * 字段名称:  .
     * 字段定义: region.PARENT_ID
     *
     * @ET
     */
    private Long parentId;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_LEVEL
     *
     * @ET
     */
    private Double regionLevel;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_ORDER
     *
     * @ET
     */
    private Double regionOrder;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_NAME_EN
     *
     * @ET
     */
    private String regionNameEn;

    /**
     * 字段名称:  .
     * 字段定义: region.REGION_SHORTNAME_EN
     *
     * @ET
     */
    private String regionShortnameEn;

    /**
     * 字段名称: 备注 .
     * 字段定义: region.Remark
     *
     * @ET
     */
    private String remark;

    /**
     * This method:getId
     * region.ID
     *
     * @return the value of region.ID
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  region.ID
     *
     * @param id the value for region.ID
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getRegionCode
     * region.REGION_CODE
     *
     * @return the value of region.REGION_CODE
     *
     * @ET
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * This method:setRegionCode
     *  region.REGION_CODE
     *
     * @param regionCode the value for region.REGION_CODE
     *
     * @ET
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    /**
     * This method:getRegionName
     * region.REGION_NAME
     *
     * @return the value of region.REGION_NAME
     *
     * @ET
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * This method:setRegionName
     *  region.REGION_NAME
     *
     * @param regionName the value for region.REGION_NAME
     *
     * @ET
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    /**
     * This method:getParentId
     * region.PARENT_ID
     *
     * @return the value of region.PARENT_ID
     *
     * @ET
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method:setParentId
     *  region.PARENT_ID
     *
     * @param parentId the value for region.PARENT_ID
     *
     * @ET
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method:getRegionLevel
     * region.REGION_LEVEL
     *
     * @return the value of region.REGION_LEVEL
     *
     * @ET
     */
    public Double getRegionLevel() {
        return regionLevel;
    }

    /**
     * This method:setRegionLevel
     *  region.REGION_LEVEL
     *
     * @param regionLevel the value for region.REGION_LEVEL
     *
     * @ET
     */
    public void setRegionLevel(Double regionLevel) {
        this.regionLevel = regionLevel;
    }

    /**
     * This method:getRegionOrder
     * region.REGION_ORDER
     *
     * @return the value of region.REGION_ORDER
     *
     * @ET
     */
    public Double getRegionOrder() {
        return regionOrder;
    }

    /**
     * This method:setRegionOrder
     *  region.REGION_ORDER
     *
     * @param regionOrder the value for region.REGION_ORDER
     *
     * @ET
     */
    public void setRegionOrder(Double regionOrder) {
        this.regionOrder = regionOrder;
    }

    /**
     * This method:getRegionNameEn
     * region.REGION_NAME_EN
     *
     * @return the value of region.REGION_NAME_EN
     *
     * @ET
     */
    public String getRegionNameEn() {
        return regionNameEn;
    }

    /**
     * This method:setRegionNameEn
     *  region.REGION_NAME_EN
     *
     * @param regionNameEn the value for region.REGION_NAME_EN
     *
     * @ET
     */
    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn == null ? null : regionNameEn.trim();
    }

    /**
     * This method:getRegionShortnameEn
     * region.REGION_SHORTNAME_EN
     *
     * @return the value of region.REGION_SHORTNAME_EN
     *
     * @ET
     */
    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    /**
     * This method:setRegionShortnameEn
     *  region.REGION_SHORTNAME_EN
     *
     * @param regionShortnameEn the value for region.REGION_SHORTNAME_EN
     *
     * @ET
     */
    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn == null ? null : regionShortnameEn.trim();
    }

    /**
     * This method:getRemark
     * region.Remark
     *
     * @return the value of region.Remark
     *
     * @ET
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method:setRemark
     *  region.Remark
     *
     * @param remark the value for region.Remark
     *
     * @ET
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}