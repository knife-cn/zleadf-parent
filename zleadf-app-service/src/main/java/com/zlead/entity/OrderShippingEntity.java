package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 发货管理
 * 
 * @author fqf
 * @date 2018-08-14 10:13:01
 */
public class OrderShippingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
		private Long id;
	/**
	 * 订单ID
	 */
	private Long orderId;
	/**
	 * 快递公司编号
	 */
	private String exCmpNo;
	/**
	 * 物流单号
	 */
	private String deliveryNumber;
	/**
	 * 寄件人
	 */
	private String sender;
	/**
	 * 寄件地址
	 */
	private String sendAddress;
	/**
	 * 寄件人手机
	 */
	private String sendMobile;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 创建都ID
	 */
	private Long creatorId;

	/**
	 * 发货类别
	 */
	private Integer sendType;

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
	 * 设置：快递公司编号
	 */
	public void setExCmpNo(String exCmpNo) {
		this.exCmpNo = exCmpNo;
	}
	/**
	 * 获取：快递公司编号
	 */
	public String getExCmpNo() {
		return exCmpNo;
	}
	/**
	 * 设置：物流单号
	 */
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	/**
	 * 获取：物流单号
	 */
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	/**
	 * 设置：寄件人
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/**
	 * 获取：寄件人
	 */
	public String getSender() {
		return sender;
	}
	/**
	 * 设置：寄件地址
	 */
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	/**
	 * 获取：寄件地址
	 */
	public String getSendAddress() {
		return sendAddress;
	}
	/**
	 * 设置：寄件人手机
	 */
	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}
	/**
	 * 获取：寄件人手机
	 */
	public String getSendMobile() {
		return sendMobile;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建都ID
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：创建都ID
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
}
