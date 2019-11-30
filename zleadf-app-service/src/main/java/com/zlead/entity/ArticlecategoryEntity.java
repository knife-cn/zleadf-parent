package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-09 15:54:21
 */
public class ArticlecategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类编号
	 */
		private Long id;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	/**
	 * 序号
	 */
	private Integer orders;
	/**
	 * 级别分
	 */
	private Integer grade;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * seo描述
	 */
	private String seoDescription;
	/**
	 * seo关键词
	 */
	private String seoKeywords;
	/**
	 * seo标题
	 */
	private String seoTitle;
	/**
	 * 
	 */
	private String treePath;
	/**
	 * 父类
	 */
	private Long parent;
	/**
	 *
	 */
	private Integer type;

	/**
	 * 设置：分类编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：分类编号
	 */
	public Long getId() {
		return id;
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
	 * 设置：序号
	 */
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	/**
	 * 获取：序号
	 */
	public Integer getOrders() {
		return orders;
	}
	/**
	 * 设置：级别分
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * 获取：级别分
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
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
	 * 设置：
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	/**
	 * 获取：
	 */
	public String getTreePath() {
		return treePath;
	}
	/**
	 * 设置：父类
	 */
	public void setParent(Long parent) {
		this.parent = parent;
	}
	/**
	 * 获取：父类
	 */
	public Long getParent() {
		return parent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
