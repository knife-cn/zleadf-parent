package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方入驻店铺
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
public class ShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
		private Long id;
	/**
	 * 店铺sn
	 */
	private String sn;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 所属分类：1-8分别对应：吃喝玩乐衣食住行
	 */
	private Integer catatoryId;
	/**
	 * 商家类型(1厂家供应商 2平台自营店 3代理商 4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
	 */
	private Integer shopType;
	/**
	 * 店铺logo
	 */
	private String shopLogo;
	/**
	 * BANNER图
	 */
	private String bannerImg;
	/**
	 * 模板
	 */
	private String template;
	/**
	 * 保证金
	 */
	private Integer bond;
	/**
	 * 保证金状态 1 未缴纳  2  已缴纳
	 */
	private Integer bondStatus;
	/**
	 * 经营范围
	 */
	private String businessScope;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 所属省
	 */
	private Long provinceId;
	/**
	 * 所属市
	 */
	private Long cityId;
	/**
	 * 所属区
	 */
	private Long regionId;
	/**
	 * 公司地址
	 */
	private String companyAddress;
	/**
	 * 公司法人名字
	 */
	private String legalName;
	/**
	 * 公司法人身份证编号
	 */
	private String legalIdcard;
	/**
	 * 公司注册资本
	 */
	private String companyCapital;
	/**
	 * 公司电话
	 */
	private String companyPhone;
	/**
	 * 联系人名字
	 */
	private String contactName;
	/**
	 * 联系人微信
	 */
	private String contactWeixin;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 联系人邮箱
	 */
	private String contactEmail;
	/**
	 * 身份证正面图片
	 */
	private String idcardImg;
	/**
	 * 身份证反面图片
	 */
	private String idcardBackimg;
	/**
	 * 营业执照编号
	 */
	private String businessSn;
	/**
	 * 营业执照图片
	 */
	private String businessImg;
	/**
	 * 银行账户
	 */
	private String bankAccount;
	/**
	 * 银行开户名称
	 */
	private String bankOpenName;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行地址
	 */
	private String bankAddress;
	/**
	 * 银行开户支行行号
	 */
	private String bankSn;
	/**
	 * 店铺佣金百分比
	 */
	private Integer commissionRate;
	/**
	 * 结算日期(1-31)
	 */
	private Integer settlementDate;
	/**
	 * 是否为平台直营店
	 */
	private Integer isPlatform;
	/**
	 * 代理商级别
	 */
	private Integer agentLevel;
	/**
	 * 上一级代理商编号
	 */
	private Long agentShopId;
	/**
	 * 购买人次
	 */
	private Integer buyernum;
	/**
	 * 折扣
	 */
	private Float discount;
	/**
	 * 店铺的类别1：零售业2：服务业
	 */
	private Integer storetype;
	/**
	 * 审核进度 1 个人信息 2 公司信息 3 审核流程 4 保证金 5 已缴纳
	 */
	private Integer auditSchedule;
	/**
	 * 审核时间
	 */
	private Long auditTime;
	/**
	 * 申请人
	 */
	private Long auditUserId;
	/**
	 * 店铺的业务管理员-对应平台公司的编制业务员
	 */
	private Long mgrUserId;
	/**
	 * 是否禁用
	 */
	private Integer disable;
	/**
	 * 店铺申请审核状态 1 申请中 2 审核失败  3 审核通过 4营业中 5 关店
	 */
	private Integer status;
	/**
	 * 展示商品方式 1：品牌 2：系列 3：分类
	 */
	private Integer showType;
	/**
	 * 申请时间
	 */
	private Date createDate;
	/**
	 *
	 */
	private Date updateDate;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * member表的memberid
	 */
	private String memberid;

	/**
	 * 联系人职位
	 */
	private String position;

	/**
	 * 联系人qq
	 */
	private String contactQQ;

	/**
	 * 公司介绍
	 */
	private String introduce;

	/**
	 * 服务内容
	 */
	private String service;

	/**
	 * 企业风采照
	 */
	private String mien;

	/**
	 * 微信二维码
	 */
	private String qrCode;


	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：店铺sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取：店铺sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置：微信二维码
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	/**
	 * 获取：微信二维码
	 */
	public String getQrCode() {
		return qrCode;
	}


	/**
	 * 设置：店铺名称
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}



	/**
	 * 获取：店铺名称
	 */

	public String getShopName() {
		return shopName;
	}

	/**
	 * 设置：所属分类：1-8分别对应：吃喝玩乐衣食住行
	 */
	public void setCatatoryId(Integer catatoryId) {
		this.catatoryId = catatoryId;
	}
	/**
	 * 获取：所属分类：1-8分别对应：吃喝玩乐衣食住行
	 */
	public Integer getCatatoryId() {
		return catatoryId;
	}
	/**
	 * 设置：商家类型(1厂家供应商 2平台自营店 3代理商 4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
	 */
	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}
	/**
	 * 获取：商家类型(1厂家供应商 2平台自营店 3代理商 4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
	 */
	public Integer getShopType() {
		return shopType;
	}
	/**
	 * 设置：店铺logo
	 */
	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}
	/**
	 * 获取：店铺logo
	 */
	public String getShopLogo() {
		if(shopLogo==null || shopLogo==""){
			shopLogo="/shopping/img/index/sl3.png";
		}
		return shopLogo;
	}
	/**
	 * 设置：BANNER图
	 */
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
	/**
	 * 获取：BANNER图
	 */
	public String getBannerImg() {
		if(bannerImg==null || bannerImg==""){
			bannerImg="/shopping/img/index/sl3.png";
		}
		return bannerImg;
	}

	/**
	 * 设置：保证金
	 */
	public void setBond(Integer bond) {
		this.bond = bond;
	}
	/**
	 * 获取：保证金
	 */
	public Integer getBond() {
		return bond;
	}
	/**
	 * 设置：保证金状态 1 未缴纳  2  已缴纳
	 */
	public void setBondStatus(Integer bondStatus) {
		this.bondStatus = bondStatus;
	}
	/**
	 * 获取：保证金状态 1 未缴纳  2  已缴纳
	 */
	public Integer getBondStatus() {
		return bondStatus;
	}
	/**
	 * 设置：经营范围
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	/**
	 * 获取：经营范围
	 */
	public String getBusinessScope() {
		return businessScope;
	}
	/**
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：所属省
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：所属省
	 */
	public Long getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：所属市
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：所属市
	 */
	public Long getCityId() {
		return cityId;
	}
	/**
	 * 设置：所属区
	 */
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	/**
	 * 获取：所属区
	 */
	public Long getRegionId() {
		return regionId;
	}
	/**
	 * 设置：公司地址
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * 获取：公司地址
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * 设置：公司法人名字
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	/**
	 * 获取：公司法人名字
	 */
	public String getLegalName() {
		return legalName;
	}
	/**
	 * 设置：公司法人身份证编号
	 */
	public void setLegalIdcard(String legalIdcard) {
		this.legalIdcard = legalIdcard;
	}
	/**
	 * 获取：公司法人身份证编号
	 */
	public String getLegalIdcard() {
		return legalIdcard;
	}
	/**
	 * 设置：公司注册资本
	 */
	public void setCompanyCapital(String companyCapital) {
		this.companyCapital = companyCapital;
	}
	/**
	 * 获取：公司注册资本
	 */
	public String getCompanyCapital() {
		return companyCapital;
	}
	/**
	 * 设置：公司电话
	 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	/**
	 * 获取：公司电话
	 */
	public String getCompanyPhone() {
		return companyPhone;
	}
	/**
	 * 设置：联系人名字
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：联系人名字
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * 设置：
	 */
	public void setContactWeixin(String contactWeixin) {
		this.contactWeixin = contactWeixin;
	}
	/**
	 * 获取：
	 */
	public String getContactWeixin() {
		return contactWeixin;
	}
	/**
	 * 设置：联系人电话
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：联系人电话
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：联系人邮箱
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * 获取：联系人邮箱
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * 设置：身份证正面图片
	 */
	public void setIdcardImg(String idcardImg) {
		this.idcardImg = idcardImg;
	}
	/**
	 * 获取：身份证正面图片
	 */
	public String getIdcardImg() {
		return idcardImg;
	}
	/**
	 * 设置：身份证反面图片
	 */
	public void setIdcardBackimg(String idcardBackimg) {
		this.idcardBackimg = idcardBackimg;
	}
	/**
	 * 获取：身份证反面图片
	 */
	public String getIdcardBackimg() {
		return idcardBackimg;
	}
	/**
	 * 设置：营业执照编号
	 */
	public void setBusinessSn(String businessSn) {
		this.businessSn = businessSn;
	}
	/**
	 * 获取：营业执照编号
	 */
	public String getBusinessSn() {
		return businessSn;
	}
	/**
	 * 设置：营业执照图片
	 */
	public void setBusinessImg(String businessImg) {
		this.businessImg = businessImg;
	}
	/**
	 * 获取：营业执照图片
	 */
	public String getBusinessImg() {
		return businessImg;
	}
	/**
	 * 设置：银行账户
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 获取：银行账户
	 */
	public String getBankAccount() {
		return bankAccount;
	}
	/**
	 * 设置：银行开户名称
	 */
	public void setBankOpenName(String bankOpenName) {
		this.bankOpenName = bankOpenName;
	}
	/**
	 * 获取：银行开户名称
	 */
	public String getBankOpenName() {
		return bankOpenName;
	}
	/**
	 * 设置：银行名称
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取：银行名称
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置：银行地址
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * 获取：银行地址
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	/**
	 * 设置：银行开户支行行号
	 */
	public void setBankSn(String bankSn) {
		this.bankSn = bankSn;
	}
	/**
	 * 获取：银行开户支行行号
	 */
	public String getBankSn() {
		return bankSn;
	}
	/**
	 * 设置：店铺佣金百分比
	 */
	public void setCommissionRate(Integer commissionRate) {
		this.commissionRate = commissionRate;
	}
	/**
	 * 获取：店铺佣金百分比
	 */
	public Integer getCommissionRate() {
		return commissionRate;
	}
	/**
	 * 设置：结算日期(1-31)
	 */
	public void setSettlementDate(Integer settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * 获取：结算日期(1-31)
	 */
	public Integer getSettlementDate() {
		return settlementDate;
	}
	/**
	 * 设置：是否为平台直营店
	 */
	public void setIsPlatform(Integer isPlatform) {
		this.isPlatform = isPlatform;
	}
	/**
	 * 获取：是否为平台直营店
	 */
	public Integer getIsPlatform() {
		return isPlatform;
	}
	/**
	 * 设置：代理商级别
	 */
	public void setAgentLevel(Integer agentLevel) {
		this.agentLevel = agentLevel;
	}
	/**
	 * 获取：代理商级别
	 */
	public Integer getAgentLevel() {
		return agentLevel;
	}
	/**
	 * 设置：上一级代理商编号
	 */
	public void setAgentShopId(Long agentShopId) {
		this.agentShopId = agentShopId;
	}
	/**
	 * 获取：上一级代理商编号
	 */
	public Long getAgentShopId() {
		return agentShopId;
	}
	/**
	 * 设置：购买人次
	 */
	public void setBuyernum(Integer buyernum) {
		this.buyernum = buyernum;
	}
	/**
	 * 获取：购买人次
	 */
	public Integer getBuyernum() {
		return buyernum;
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
	 * 设置：店铺的类别1：零售业2：服务业
	 */
	public void setStoretype(Integer storetype) {
		this.storetype = storetype;
	}
	/**
	 * 获取：店铺的类别1：零售业2：服务业
	 */
	public Integer getStoretype() {
		return storetype;
	}
	/**
	 * 设置：审核进度 1 个人信息 2 公司信息 3 审核流程 4 保证金 5 已缴纳
	 */
	public void setAuditSchedule(Integer auditSchedule) {
		this.auditSchedule = auditSchedule;
	}
	/**
	 * 获取：审核进度 1 个人信息 2 公司信息 3 审核流程 4 保证金 5 已缴纳
	 */
	public Integer getAuditSchedule() {
		return auditSchedule;
	}
	/**
	 * 设置：审核时间
	 */
	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 获取：审核时间
	 */
	public Long getAuditTime() {
		return auditTime;
	}
	/**
	 * 设置：申请人
	 */
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	/**
	 * 获取：申请人
	 */
	public Long getAuditUserId() {
		return auditUserId;
	}
	/**
	 * 设置：店铺的业务管理员-对应平台公司的编制业务员
	 */
	public void setMgrUserId(Long mgrUserId) {
		this.mgrUserId = mgrUserId;
	}
	/**
	 * 获取：店铺的业务管理员-对应平台公司的编制业务员
	 */
	public Long getMgrUserId() {
		return mgrUserId;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * 设置：是否禁用
	 */
	public void setDisable(Integer disable) {
		this.disable = disable;
	}
	/**
	 * 获取：是否禁用
	 */
	public Integer getDisable() {
		return disable;
	}
	/**
	 * 设置：店铺申请审核状态 1 申请中 2 审核失败  3 审核通过 4营业中 5 关店
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：店铺申请审核状态 1 申请中 2 审核失败  3 审核通过 4营业中 5 关店
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：申请时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：申请时间
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

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMien() {
		return mien;
	}

	public void setMien(String mien) {
		this.mien = mien;
	}

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }
}
