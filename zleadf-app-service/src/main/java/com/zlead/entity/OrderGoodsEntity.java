package com.zlead.entity;



import com.zlead.fplat.entity.CrmPrdModel;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单商品
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
public class OrderGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	//型号--勿动
	public CrmPrdModel CrmPrdModel;

	public CrmPrdModel getCrmPrdModel() {
		return CrmPrdModel;
	}

	public void setCrmPrdModel(CrmPrdModel crmPrdModel) {
		CrmPrdModel = crmPrdModel;
	}

	/**
	 * 主键
	 */
		private Long id;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 商品ID
	 */
	private Long goodsId;
	/**
	 * 商品编号
	 */
	private String goodsSn;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品缩略图
	 */
	private String goodsImg;
	/**
	 * 商品价格
	 */
	private BigDecimal price;
	/**
	 * 抵扣积分
	 */
	private Integer point;
	/**
	 * 商品所值积分
	 */
	private Long goodsPoint;
	/**
	 * 折扣
	 */
	private BigDecimal discount;
	/**
	 * 数量
	 */
	private Integer count;
	/**
	 * 评论状态 0：未评论,1：买家评论,2：卖家评论,3：双方已评
	 */
	private Integer commentStatus;
	/**
	 * 状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消，5已退货)
	 */
	private Integer status;
	/**
	 * 下单时间
	 */
	private Date createDate;
	/**
	 * 
	 */
	private Date updateDate;
	/**
	 * 
	 */
	private String remark;

	/**
	 * 市场价
	 */
	private BigDecimal marketPrice;
	/**
	 * 产品参数 DB不存在此字段
	 */
	private String goodsAttriValue;
	/**
	 * 剩余订单数
	 */
	private Integer spareQty;
	/**
	 * 订单商品总价
	 */
	private BigDecimal goodsTotalPrice;

	/**
	 * 商品原价（不打折之前的原价）
	 */
	private BigDecimal originPrice;
	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：订单ID
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单ID
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置：商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：商品编号
	 */
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	/**
	 * 获取：商品编号
	 */
	public String getGoodsSn() {
		return goodsSn;
	}
	/**
	 * 设置：商品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：商品缩略图
	 */
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	/**
	 * 获取：商品缩略图
	 */
	public String getGoodsImg() {
		return goodsImg;
	}
	/**
	 * 设置：商品价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：商品价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：抵扣积分
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}
	/**
	 * 获取：抵扣积分
	 */
	public Integer getPoint() {
		return point;
	}
	/**
	 * 设置：商品所值积分
	 */
	public void setGoodsPoint(Long goodsPoint) {
		this.goodsPoint = goodsPoint;
	}
	/**
	 * 获取：商品所值积分
	 */
	public Long getGoodsPoint() {
		return goodsPoint;
	}
	/**
	 * 设置：折扣
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	/**
	 * 获取：折扣
	 */
	public BigDecimal getDiscount() {
		return discount;
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
	 * 设置：评论状态 0：未评论,1：买家评论,2：卖家评论,3：双方已评
	 */
	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
	/**
	 * 获取：评论状态 0：未评论,1：买家评论,2：卖家评论,3：双方已评
	 */
	public Integer getCommentStatus() {
		return commentStatus;
	}
	/**
	 * 设置：状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消，5已退货)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消，5已退货)
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：下单时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：下单时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
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

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getGoodsAttriValue() {
		return goodsAttriValue;
	}

	public void setGoodsAttriValue(String goodsAttriValue) {
		this.goodsAttriValue = goodsAttriValue;
	}

	public Integer getSpareQty() {
		return spareQty;
	}

	public void setSpareQty(Integer spareQty) {
		this.spareQty = spareQty;
	}

	public BigDecimal getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BigDecimal getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(BigDecimal originPrice) {
		this.originPrice = originPrice;
	}
}
