package com.zlead.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 11:04:28
 */
public class ShoppingCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
		private Long id;
	/**
	 * 会员ID
	 */
	private Long memberId;
	/**
	 * 供应商ID
	 */
	private Long shopId;
	/**
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 
	 */
	private BigDecimal price;
	/**
	 * 数量
	 */
	private Integer count;
	/**
	 * 渠道类型（0 平台，1 店铺,2 企业）
	 */
	private Integer channelType;
	/**
	 * 广告id
	 */
	private Long adsId;
	/**
	 * 类型：0线上购物车，1线下扫码购物车 
	 */
	private Integer buyType;
	/**
	 * 添加时间
	 */
	private Date createDate;
	/**
	 * 
	 */
	private String remark;

	private Integer isBuy;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：会员ID
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员ID
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：供应商ID
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：供应商ID
	 */
	public Long getShopId() {
		return shopId;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPrice() {
		if(price==null) return BigDecimal.valueOf(0);
		else
			return price.setScale(2);
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：渠道类型（0 平台，1 店铺）
	 */
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	/**
	 * 获取：渠道类型（0 平台，1 店铺）
	 */
	public Integer getChannelType() {
		return channelType;
	}
	/**
	 * 设置：广告id
	 */
	public void setAdsId(Long adsId) {
		this.adsId = adsId;
	}
	/**
	 * 获取：广告id
	 */
	public Long getAdsId() {
		return adsId;
	}
	/**
	 * 设置：类型：0线上购物车，1线下扫码购物车 
	 */
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	/**
	 * 获取：类型：0线上购物车，1线下扫码购物车 
	 */
	public Integer getBuyType() {
		return buyType;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}

	public Integer getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(Integer isBuy) {
		this.isBuy = isBuy;
	}
}
