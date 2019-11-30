package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-03 16:42:39
 */
public class AdsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	 /**
     * 字段名称:  .
     * 字段定义: t_ads.id
     *
     * @ET
     */
    private Long id;

    /**
     * 字段名称: 广告分类ID 0：商城1：企业 .
     * 字段定义: t_ads.adstype
     *
     * @ET
     */
    private Long adstype;

    /**
     * 字段名称: 广告性质0广告1轮播  .
     * 字段定义: t_ads.catagory
     *
     * @ET
     */
    private Integer catagory;

    /**
     * 字段名称: 首页板块1-5,0非首页 .
     * 字段定义: t_ads.channel
     *
     * @ET
     */
    private Integer channel;

    /**
     * 字段名称: 首页显示顺序 .
     * 字段定义: t_ads.sort
     *
     * @ET
     */
    private Integer sort;

    /**
     * 字段名称: 广告标题 .
     * 字段定义: t_ads.title
     *
     * @ET
     */
    private String title;

    /**
     * 字段名称: 0广告图片 1文章 2商品 3视频 4外链 .
     * 字段定义: t_ads.content_type
     *
     * @ET
     */
    private Integer contentType;

    /**
     * 字段名称:业务单号,链接方式 1 活动时是活动id 2 数据查询 是显示品牌id
     * 字段定义:t_ads.sn
     *
     * @ET
     */
    private String sn;

    /**
     * 字段名称: 店铺编号 .
     * 字段定义: t_ads.shop_id
     *
     * @ET
     */
    private Long shopId;

    /**
     * 字段名称: 缩略图 .
     * 字段定义: t_ads.thumbnail
     *
     * @ET
     */
    private String thumbnail;

    /**
     * 字段名称: 内容地址 .
     * 字段定义: t_ads.content_path
     *
     * @ET
     */
    private String contentPath;

    /**
     * 字段名称: 广告大图 .
     * 字段定义: t_ads.ads_img
     *
     * @ET
     */
    private String adsImg;

    /**
     * 字段名称: 简介 .
     * 字段定义: t_ads.introduce
     *
     * @ET
     */
    private String introduce;

    /**
     * 字段名称: 浏览量 .
     * 字段定义: t_ads.hits
     *
     * @ET
     */
    private Long hits;

    /**
     * 字段名称: 是否置顶 .
     * 字段定义: t_ads.is_top
     *
     * @ET
     */
    private Boolean isTop;

    /**
     * 字段名称: 0 未发布 1已发布 2删除 .
     * 字段定义: t_ads.status
     *
     * @ET
     */
    private Integer status;

    /**
     * 字段名称: 备注 .
     * 字段定义: t_ads.remark
     *
     * @ET
     */
    private String remark;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: t_ads.create_date
     *
     * @ET
     */
    private Date createDate;

    /**
     * 字段名称: 修改日期 .
     * 字段定义: t_ads.modify_date
     *
     * @ET
     */
    private Date modifyDate;

    /**
     * 字段名称: 添加用户的id  后台添加显示sys表id，企业添加显示member表id .
     * 字段定义: t_ads.adduser_id
     *
     * @ET
     */
    private Long adduserId;

    /**
     * 字段名称: 广告内容 .
     * 字段定义: t_ads.ads_content
     *
     * @ET
     */
    private String adsContent;

    /**
     * This method:getId
     * t_ads.id
     *
     * @return the value of t_ads.id
     *
     * @ET
     */
    public Long getId() {
        return id;
    }

    /**
     * This method:setId
     *  t_ads.id
     *
     * @param id the value for t_ads.id
     *
     * @ET
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method:getAdstype
     * t_ads.adstype
     *
     * @return the value of t_ads.adstype
     *
     * @ET
     */
    public Long getAdstype() {
        return adstype;
    }

    /**
     * This method:setAdstype
     *  t_ads.adstype
     *
     * @param adstype the value for t_ads.adstype
     *
     * @ET
     */
    public void setAdstype(Long adstype) {
        this.adstype = adstype;
    }

    /**
     * This method:getCatagory
     * t_ads.catagory
     *
     * @return the value of t_ads.catagory
     *
     * @ET
     */
    public Integer getCatagory() {
        return catagory;
    }

    /**
     * This method:setCatagory
     *  t_ads.catagory
     *
     * @param catagory the value for t_ads.catagory
     *
     * @ET
     */
    public void setCatagory(Integer catagory) {
        this.catagory = catagory;
    }

    /**
     * This method:getChannel
     * t_ads.channel
     *
     * @return the value of t_ads.channel
     *
     * @ET
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * This method:setChannel
     *  t_ads.channel
     *
     * @param channel the value for t_ads.channel
     *
     * @ET
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * This method:getSort
     * t_ads.sort
     *
     * @return the value of t_ads.sort
     *
     * @ET
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method:setSort
     *  t_ads.sort
     *
     * @param sort the value for t_ads.sort
     *
     * @ET
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method:getTitle
     * t_ads.title
     *
     * @return the value of t_ads.title
     *
     * @ET
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method:setTitle
     *  t_ads.title
     *
     * @param title the value for t_ads.title
     *
     * @ET
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method:getContentType
     * t_ads.content_type
     *
     * @return the value of t_ads.content_type
     *
     * @ET
     */
    public Integer getContentType() {
        return contentType;
    }

    /**
     * This method:setContentType
     *  t_ads.content_type
     *
     * @param contentType the value for t_ads.content_type
     *
     * @ET
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    /**
     * This method:getSn
     *  t_ads.sn
     *
     * @return the value for t_ads.sn
     *
     * @ET
     */
    public String getSn() { return sn; }

    /**
     * This method:getSn
     *  t_ads.sn
     *
     * @param sn the value for t_ads.sn
     *
     * @ET
     */
    public void setSn(String sn) { this.sn = sn; }

    /**
     * This method:getShopId
     * t_ads.shop_id
     *
     * @return the value of t_ads.shop_id
     *
     * @ET
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method:setShopId
     *  t_ads.shop_id
     *
     * @param shopId the value for t_ads.shop_id
     *
     * @ET
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getThumbnail
     * t_ads.thumbnail
     *
     * @return the value of t_ads.thumbnail
     *
     * @ET
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * This method:setThumbnail
     *  t_ads.thumbnail
     *
     * @param thumbnail the value for t_ads.thumbnail
     *
     * @ET
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * This method:getContentPath
     * t_ads.content_path
     *
     * @return the value of t_ads.content_path
     *
     * @ET
     */
    public String getContentPath() {
        return contentPath;
    }

    /**
     * This method:setContentPath
     *  t_ads.content_path
     *
     * @param contentPath the value for t_ads.content_path
     *
     * @ET
     */
    public void setContentPath(String contentPath) {
        this.contentPath = contentPath == null ? null : contentPath.trim();
    }

    /**
     * This method:getAdsImg
     * t_ads.ads_img
     *
     * @return the value of t_ads.ads_img
     *
     * @ET
     */
    public String getAdsImg() {
        return adsImg;
    }

    /**
     * This method:setAdsImg
     *  t_ads.ads_img
     *
     * @param adsImg the value for t_ads.ads_img
     *
     * @ET
     */
    public void setAdsImg(String adsImg) {
        this.adsImg = adsImg == null ? null : adsImg.trim();
    }

    /**
     * This method:getIntroduce
     * t_ads.introduce
     *
     * @return the value of t_ads.introduce
     *
     * @ET
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * This method:setIntroduce
     *  t_ads.introduce
     *
     * @param introduce the value for t_ads.introduce
     *
     * @ET
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * This method:getHits
     * t_ads.hits
     *
     * @return the value of t_ads.hits
     *
     * @ET
     */
    public Long getHits() {
        return hits;
    }

    /**
     * This method:setHits
     *  t_ads.hits
     *
     * @param hits the value for t_ads.hits
     *
     * @ET
     */
    public void setHits(Long hits) {
        this.hits = hits;
    }

    /**
     * This method:getIsTop
     * t_ads.is_top
     *
     * @return the value of t_ads.is_top
     *
     * @ET
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     * This method:setIsTop
     *  t_ads.is_top
     *
     * @param isTop the value for t_ads.is_top
     *
     * @ET
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     * This method:getStatus
     * t_ads.status
     *
     * @return the value of t_ads.status
     *
     * @ET
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method:setStatus
     *  t_ads.status
     *
     * @param status the value for t_ads.status
     *
     * @ET
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method:getRemark
     * t_ads.remark
     *
     * @return the value of t_ads.remark
     *
     * @ET
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method:setRemark
     *  t_ads.remark
     *
     * @param remark the value for t_ads.remark
     *
     * @ET
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method:getCreateDate
     * t_ads.create_date
     *
     * @return the value of t_ads.create_date
     *
     * @ET
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method:setCreateDate
     *  t_ads.create_date
     *
     * @param createDate the value for t_ads.create_date
     *
     * @ET
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method:getModifyDate
     * t_ads.modify_date
     *
     * @return the value of t_ads.modify_date
     *
     * @ET
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * This method:setModifyDate
     *  t_ads.modify_date
     *
     * @param modifyDate the value for t_ads.modify_date
     *
     * @ET
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * This method:getAdduserId
     * t_ads.adduser_id
     *
     * @return the value of t_ads.adduser_id
     *
     * @ET
     */
    public Long getAdduserId() {
        return adduserId;
    }

    /**
     * This method:setAdduserId
     *  t_ads.adduser_id
     *
     * @param adduserId the value for t_ads.adduser_id
     *
     * @ET
     */
    public void setAdduserId(Long adduserId) {
        this.adduserId = adduserId;
    }

    /**
     * This method:getAdsContent
     * t_ads.ads_content
     *
     * @return the value of t_ads.ads_content
     *
     * @ET
     */
    public String getAdsContent() {
        return adsContent;
    }

    /**
     * This method:setAdsContent
     *  t_ads.ads_content
     *
     * @param adsContent the value for t_ads.ads_content
     *
     * @ET
     */
    public void setAdsContent(String adsContent) {
        this.adsContent = adsContent == null ? null : adsContent.trim();
    }
}
