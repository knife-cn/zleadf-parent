package com.zlead.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FREEZE_ORDER_VALID_TIME = "2018-03-10 00:00:00";

	public List<OrderGoodsEntity> OrderGoods;

	private String factory;
	public List<OrderGoodsEntity> getOrderGoods() {
		return OrderGoods;
	}

	public void setOrderGoods(List<OrderGoodsEntity> orderGoods) {
		OrderGoods = orderGoods;
	}


	private int voucherId;

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}


	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 订单编号
	 */
	private String sn;
	/**
	 * 订单类型：0商品订单，1会员卡订单，2积分商品订单，3实体商家入驻订单，4积分换订单，5积分购订单，6虚拟商家入驻订单,7附近商家订单 8,代理商入驻订单 9,代理商首单
	 */
	private Integer orderType;
	/**
	 * 购买方式：1线上购买商品，2实体店扫描购买商品，3线上购买会员卡快递发货，4线上下单线下支付并自提会员卡，5从销售人员手中购买会员卡，6从实体店购买会员卡
	 * 订单来源:1-小程序、2-商城、3-内部后台、4-业务员APP
	 */
	private Integer buyType;
	/**
	 * 店铺编号
	 */
	private Long shopId;
	/**
	 * 送货类型  0自提  1送货
	 */
	private Integer shippingType;
	/**
	 * 购买实体店/代理商名称
	 */
	private String shopName;
	/**
	 * 支付方式(0-线上支付，1-线下支付, 2对公转账 3-线上组合支付)
	 */
	private Integer payType;
	/**
	 * 商品总金额
	 */
	private BigDecimal goodsAmount;
	/**
	 * 运单费
	 */
	private BigDecimal shippingCost;
	/**
	 * 应付订单金额
	 */
	private BigDecimal payableAmount;
	/**
	 * 支付积分金额
	 */
	private Long payPoint;
	/**
	 * 实付订单金额
	 */
	private BigDecimal actualPayments;
	/**
	 * 会员ID
	 */
	private Long memberId;
	/**
	 * 会员姓名
	 */
	private String memberName;
	/**
	 * 快递公司编号
	 */
	private String shippingCorpno;
	/**
	 * 快递公司名称
	 */
	private String shippingCorpname;
	/**
	 * 物流单号
	 */
	private String deliveryNo;
	/**
	 * 收货人姓名
	 */
	private String consignee;
	/**
	 * 收货人详细地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 收货人电话
	 */
	private String phone;
	/**
	 * 收货人固定电话
	 */
	private String telephone;
	/**
	 * 收货人邮箱
	 */
	private String email;
	/**
	 * 是否开发票
	 */
	private Integer needInvoide;
	/**
	 * 下单时间
	 */
	private Date createDate;
	/**
	 * 订单过期时间
	 */
	private Date expireDate;
	/**
	 * 是否过期
	 */
	private Integer isExpire;
	/**
	 * 组合支付订单:以C结尾的，比单笔支付多一位
	 */
	private String paySn;
	/**
	 * 支付完成时间
	 */
	private Date paymentDate;
	/**
	 * 修改时间
	 */
	private Date modifyDate;
	//期望到货日期
	private Date needDate;
	/**
	 * 是否删除 1 是 0 否
	 */
	private Integer isDelete;
	/**
	 * 折扣
	 */
	private Float discount;
	/**
	 * 状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消，5已退货)
	 */
	private Integer status;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 返点金额
	 */
	private BigDecimal rebateAmount;
	
	private Integer goodscnt;

	private String contactMan;

	private String contactTel;
	/**
	 *业务员编号
	 */
	private Integer userId;

	/**
	 * 价格类型:1-经销价,2-批发价,3-零售价,4-成本价
	 */
	private String priceType;

	/**
	 * 订单创建者
	 */
	private Integer createId;
	/**
	 * 购买公司编号
	 */
	private Long buyerCorpid;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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
	 * 设置：订单编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取：订单编号
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置：订单类型：0商品订单，1会员卡订单，2积分商品订单，3实体商家入驻订单，4积分换订单，5积分购订单，6虚拟商家入驻订单,7附近商家订单 8,代理商入驻订单 9,代理商首单
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：订单类型：0商品订单，1会员卡订单，2积分商品订单，3实体商家入驻订单，4积分换订单，5积分购订单，6虚拟商家入驻订单,7附近商家订单 8,代理商入驻订单 9,代理商首单
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * 设置：购买方式：1线上购买商品，2实体店扫描购买商品，3线上购买会员卡快递发货，4线上下单线下支付并自提会员卡，5从销售人员手中购买会员卡，6从实体店购买会员卡
	 */
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	/**
	 * 获取：购买方式：1线上购买商品，2实体店扫描购买商品，3线上购买会员卡快递发货，4线上下单线下支付并自提会员卡，5从销售人员手中购买会员卡，6从实体店购买会员卡
	 */
	public Integer getBuyType() {
		return buyType;
	}
	/**
	 * 设置：店铺编号
	 */
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	/**
	 * 获取：店铺编号
	 */
	public Long getShopId() {
		return shopId;
	}
	/**
	 * 设置：送货类型  0自提  1送货
	 */
	public void setShippingType(Integer shippingType) {
		this.shippingType = shippingType;
	}
	/**
	 * 获取：送货类型  0自提  1送货
	 */
	public Integer getShippingType() {
		return shippingType;
	}
	/**
	 * 设置：购买实体店/代理商名称
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 获取：购买实体店/代理商名称
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * 设置：支付方式(0-线上支付，1-线下支付, 2对公转账 3-线上组合支付)
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付方式(0-线上支付，1-线下支付, 2对公转账 3-线上组合支付)
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：商品总金额
	 */
	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}
	/**
	 * 获取：商品总金额
	 */
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}
	/**
	 * 设置：运单费
	 */
	public void setShippingCost(BigDecimal shippingCost) {
		this.shippingCost = shippingCost;
	}
	/**
	 * 获取：运单费
	 */
	public BigDecimal getShippingCost() {
		if(shippingCost==null) return BigDecimal.valueOf(0.0);
		else
		return shippingCost;
	}
	/**
	 * 设置：应付订单金额
	 */
	public void setPayableAmount(BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}
	/**
	 * 获取：应付订单金额
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}
	/**
	 * 设置：支付积分金额
	 */
	public void setPayPoint(Long payPoint) {
		this.payPoint = payPoint;
	}
	/**
	 * 获取：支付积分金额
	 */
	public Long getPayPoint() {
		return payPoint;
	}
	/**
	 * 设置：实付订单金额
	 */
	public void setActualPayments(BigDecimal actualPayments) {
		this.actualPayments = actualPayments;
	}
	/**
	 * 获取：实付订单金额
	 */
	public BigDecimal getActualPayments() {
		return actualPayments;
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
	 * 设置：会员姓名
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	/**
	 * 获取：会员姓名
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * 设置：快递公司编号
	 */
	public void setShippingCorpno(String shippingCorpno) {
		this.shippingCorpno = shippingCorpno;
	}
	/**
	 * 获取：快递公司编号
	 */
	public String getShippingCorpno() {
		return shippingCorpno;
	}
	/**
	 * 设置：快递公司名称
	 */
	public void setShippingCorpname(String shippingCorpname) {
		this.shippingCorpname = shippingCorpname;
	}
	/**
	 * 获取：快递公司名称
	 */
	public String getShippingCorpname() {
		return shippingCorpname;
	}
	/**
	 * 设置：物流单号
	 */
	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}
	/**
	 * 获取：物流单号
	 */
	public String getDeliveryNo() {
		return deliveryNo;
	}
	/**
	 * 设置：收货人姓名
	 */
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	/**
	 * 获取：收货人姓名
	 */
	public String getConsignee() {
		return consignee;
	}
	/**
	 * 设置：收货人详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：收货人详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：收货人电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：收货人电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：收货人固定电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：收货人固定电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：收货人邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：收货人邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：是否开发票
	 */
	public void setNeedInvoide(Integer needInvoide) {
		this.needInvoide = needInvoide;
	}
	/**
	 * 获取：是否开发票
	 */
	public Integer getNeedInvoide() {
		return needInvoide;
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
	 * 设置：订单过期时间
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * 获取：订单过期时间
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * 设置：是否过期
	 */
	public void setIsExpire(Integer isExpire) {
		this.isExpire = isExpire;
	}
	/**
	 * 获取：是否过期
	 */
	public Integer getIsExpire() {
		return isExpire;
	}
	/**
	 * 设置：组合支付订单:以C结尾的，比单笔支付多一位
	 */
	public void setPaySn(String paySn) {
		this.paySn = paySn;
	}
	/**
	 * 获取：组合支付订单:以C结尾的，比单笔支付多一位
	 */
	public String getPaySn() {
		return paySn;
	}
	/**
	 * 设置：支付完成时间
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * 获取：支付完成时间
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 设置：是否删除 1 是 0 否
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除 1 是 0 否
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：折扣
	 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	/**
	 * 获取：折扣
	 */
	public Float getDiscount() {
		return discount;
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

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public Integer getGoodscnt() {
		return goodscnt;
	}

	public void setGoodscnt(Integer goodscnt) {
		this.goodscnt = goodscnt;
	}
	/**
	 * 价格类型:1-经销价,2-批发价,3-零售价,4-成本价
	 */
	public String getPriceType() {
		return priceType;
	}
	/**
	 * 价格类型:1-经销价,2-批发价,3-零售价,4-成本价
	 */
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Long getBuyerCorpid() {
		return buyerCorpid;
	}

	public void setBuyerCorpid(Long buyerCorpid) {
		this.buyerCorpid = buyerCorpid;
	}

	public Date getNeedDate() {
		return needDate;
	}

	public void setNeedDate(Date needDate) {
		this.needDate = needDate;
	}
}
