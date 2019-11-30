package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-09 15:53:48
 */
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**

	 * 编号
	 */
		private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 文章点赞数
	 */
	private Integer likes;
	/**
	 * 分类编号
	 */
	private Long categoryid;
	/**
	 * 缩略图
	 */
	private String thumbnail;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 点击数
	 */
	private Integer hits;
	/**
	 * 
	 */
	private Integer isTop;
	/**
	 * 
	 */
	private Integer isPublication;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * seo标题
	 */
	private String seoTitle;
	/**
	 * seo关键词
	 */
	private String seoKeywords;
	/**
	 * seo描述
	 */
	private String seoDescription;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	/**
	 * 发布类型0：平台发布1：企业发布
	 */
	private Integer publishType;
	/**
	 * 企业发布者的member_id
	 */
	private Long userId;

	/**
	 * 是否审核
	 */
	private Integer isAudit;
 
 
	public ArticleEntity(Long shopId) {
		this.shopId = shopId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 企业id
	 */
	private Long shopId;
	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：文章点赞数
	 */
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	/**
	 * 获取：文章点赞数
	 */
	public Integer getLikes() {
		return likes;
	}
	/**
	 * 设置：分类编号
	 */
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	/**
	 * 获取：分类编号
	 */
	public Long getCategoryid() {
		return categoryid;
	}
	/**
	 * 设置：缩略图
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/**
	 * 获取：缩略图
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	/**
	 * 设置：文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：文章内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：点击数
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	/**
	 * 获取：点击数
	 */
	public Integer getHits() {
		return hits;
	}
	/**
	 * 设置：
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：
	 */
	public Integer getIsTop() {
		return isTop;
	}
	/**
	 * 设置：
	 */
	public void setIsPublication(Integer isPublication) {
		this.isPublication = isPublication;
	}
	/**
	 * 获取：
	 */
	public Integer getIsPublication() {
		return isPublication;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：seo标题
	 */
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	/**
	 * 获取：seo标题
	 */
	public String getSeoTitle() {
		return seoTitle;
	}
	/**
	 * 设置：seo关键词
	 */
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	/**
	 * 获取：seo关键词
	 */
	public String getSeoKeywords() {
		return seoKeywords;
	}
	/**
	 * 设置：seo描述
	 */
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	/**
	 * 获取：seo描述
	 */
	public String getSeoDescription() {
		return seoDescription;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：修改日期
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：修改日期
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 设置：发布类型0：平台发布1：企业发布
	 */
	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}
	/**
	 * 获取：发布类型0：平台发布1：企业发布
	 */
	public Integer getPublishType() {
		return publishType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Long getShopId() {
		return shopId;
	}

	public ArticleEntity() {
	}

	public ArticleEntity(Long id, String title, Long categoryid, String thumbnail, String content, Integer hits, Integer isTop, Integer isPublication, String author, String seoTitle, String seoKeywords, String seoDescription, String remark, Date createDate, Date modifyDate, Integer publishType, Long userId, Integer isAudit, Long shopId) {
		this.id = id;
		this.title = title;
		this.categoryid = categoryid;
		this.thumbnail = thumbnail;
		this.content = content;
		this.hits = hits;
		this.isTop = isTop;
		this.isPublication = isPublication;
		this.author = author;
		this.seoTitle = seoTitle;
		this.seoKeywords = seoKeywords;
		this.seoDescription = seoDescription;
		this.remark = remark;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.publishType = publishType;
		this.userId = userId;
		this.isAudit = isAudit;
		this.shopId = shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}





	@Override
	public String toString() {
		return "ArticleEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", categoryid=" + categoryid +
				", thumbnail='" + thumbnail + '\'' +
				", content='" + content + '\'' +
				", hits=" + hits +
				", isTop=" + isTop +
				", isPublication=" + isPublication +
				", author='" + author + '\'' +
				", seoTitle='" + seoTitle + '\'' +
				", seoKeywords='" + seoKeywords + '\'' +
				", seoDescription='" + seoDescription + '\'' +
				", remark='" + remark + '\'' +
				", createDate=" + createDate +
				", modifyDate=" + modifyDate +
				", publishType=" + publishType +
				", userId=" + userId +
				", isAudit=" + isAudit +
				", shopId=" + shopId +
				'}';
	}
}
