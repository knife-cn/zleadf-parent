package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品颜色图片
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-07-26 11:35:28
 */
public class ProdImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 惟一ID
	 */
		private Long id;
	/**
	 * 产品ID
	 */
	private Long prodId;
	/**
	 * 颜色规格ID
	 */
	private Long specId;
	/**
	 * 颜色规格值ID
	 */
	private Long specValueId;
	/**
	 * 图片，逗号分隔
	 */
	private String imgs;
	/**
	 * 第一张图
	 */
	private String firstImg;

	/**
	 * 设置：惟一ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：惟一ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：产品ID
	 */
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}
	/**
	 * 获取：产品ID
	 */
	public Long getProdId() {
		return prodId;
	}
	/**
	 * 设置：颜色规格ID
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	/**
	 * 获取：颜色规格ID
	 */
	public Long getSpecId() {
		return specId;
	}
	/**
	 * 设置：颜色规格值ID
	 */
	public void setSpecValueId(Long specValueId) {
		this.specValueId = specValueId;
	}
	/**
	 * 获取：颜色规格值ID
	 */
	public Long getSpecValueId() {
		return specValueId;
	}
	/**
	 * 设置：图片，逗号分隔
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：图片，逗号分隔
	 */
	public String getImgs() {
		return imgs;
	}
	/**
	 * 设置：第一张图
	 */
	public void setFirstImg(String firstImg) {
		this.firstImg = firstImg;
	}
	/**
	 * 获取：第一张图
	 */
	public String getFirstImg() {
		return firstImg;
	}
}
