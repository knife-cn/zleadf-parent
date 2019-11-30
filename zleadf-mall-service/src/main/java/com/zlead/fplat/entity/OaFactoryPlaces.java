package com.zlead.fplat.entity;

import java.util.Date;

public class OaFactoryPlaces {
    /**
     * 字段名称: 地点 .
     * 字段定义: oa_factory_places.place_id
     *
     * @ET
     */
    private Integer placeId;

    /**
     * 字段名称: 关联编号 .
     * 字段定义: oa_factory_places.fact_id
     *
     * @ET
     */
    private Integer factId;

    /**
     * 字段名称: 地点 .
     * 字段定义: oa_factory_places.place_name
     *
     * @ET
     */
    private String placeName;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: oa_factory_places.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: oa_factory_places.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getPlaceId
     * oa_factory_places.place_id
     *
     * @return the value of oa_factory_places.place_id
     *
     * @ET
     */
    public Integer getPlaceId() {
        return placeId;
    }

    /**
     * This method:setPlaceId
     *  oa_factory_places.place_id
     *
     * @param placeId the value for oa_factory_places.place_id
     *
     * @ET
     */
    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    /**
     * This method:getFactId
     * oa_factory_places.fact_id
     *
     * @return the value of oa_factory_places.fact_id
     *
     * @ET
     */
    public Integer getFactId() {
        return factId;
    }

    /**
     * This method:setFactId
     *  oa_factory_places.fact_id
     *
     * @param factId the value for oa_factory_places.fact_id
     *
     * @ET
     */
    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    /**
     * This method:getPlaceName
     * oa_factory_places.place_name
     *
     * @return the value of oa_factory_places.place_name
     *
     * @ET
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * This method:setPlaceName
     *  oa_factory_places.place_name
     *
     * @param placeName the value for oa_factory_places.place_name
     *
     * @ET
     */
    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    /**
     * This method:getCreateTime
     * oa_factory_places.create_time
     *
     * @return the value of oa_factory_places.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  oa_factory_places.create_time
     *
     * @param createTime the value for oa_factory_places.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * oa_factory_places.modify_time
     *
     * @return the value of oa_factory_places.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  oa_factory_places.modify_time
     *
     * @param modifyTime the value for oa_factory_places.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}