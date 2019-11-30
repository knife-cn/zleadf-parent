package com.zlead.fplat.entity;

public class GoodsAgentLevel {
    /**
     * 字段名称: 唯一ID .
     * 字段定义: t_goods_level.id
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称: 商品id .
     * 字段定义: t_goods_level.goods_id
     *
     * @ET
     */
    private Integer goodsId;

    /**
     * 字段名称: 参数名称id .
     * 字段定义: t_goods_level.level_id
     *
     * @ET
     */
    private Integer levelId;

    /**
     * 字段名称: 参数名称 .
     * 字段定义: t_goods_level.level_name
     *
     * @ET
     */
    private String levelName;

    /**
     * 字段名称: 排序 .
     * 字段定义: t_goods_level.sort
     *
     * @ET
     */
    private Integer sort;

    /**
     * 字段名称: 是否启用 0 未启用 1 启用 .
     * 字段定义: t_goods_level.status
     *
     * @ET
     */
    private Integer status;

    /**
     * This method:getId
     * t_goods_level.id
     *
     * @return the value of t_goods_level.id
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_goods_level.id
     *
     * @param id the value for t_goods_level.id
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getGoodsId
     * t_goods_level.goods_id
     *
     * @return the value of t_goods_level.goods_id
     *
     * @ET
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method:setGoodsId
     *  t_goods_level.goods_id
     *
     * @param goodsId the value for t_goods_level.goods_id
     *
     * @ET
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method:getLevelId
     * t_goods_level.level_id
     *
     * @return the value of t_goods_level.level_id
     *
     * @ET
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * This method:setLevelId
     *  t_goods_level.level_id
     *
     * @param levelId the value for t_goods_level.level_id
     *
     * @ET
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * This method:getLevelName
     * t_goods_level.level_name
     *
     * @return the value of t_goods_level.level_name
     *
     * @ET
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * This method:setLevelName
     *  t_goods_level.level_name
     *
     * @param levelName the value for t_goods_level.level_name
     *
     * @ET
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    /**
     * This method:getSort
     * t_goods_level.sort
     *
     * @return the value of t_goods_level.sort
     *
     * @ET
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method:setSort
     *  t_goods_level.sort
     *
     * @param sort the value for t_goods_level.sort
     *
     * @ET
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method:getStatus
     * t_goods_level.status
     *
     * @return the value of t_goods_level.status
     *
     * @ET
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method:setStatus
     *  t_goods_level.status
     *
     * @param status the value for t_goods_level.status
     *
     * @ET
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}