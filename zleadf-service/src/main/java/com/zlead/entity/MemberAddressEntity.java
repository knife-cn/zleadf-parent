package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员收货地址
 *
 * @author fqf
 * @date 2018-07-31 15:06:05
 */
public class MemberAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
		private Long id;
	/**
	 * 会员ID
	 */
	private Long memberId;
	/**
	 * 会员姓名
	 */
	private String memberName;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 省ID
	 */
	private Long provinceId;
	/**
	 * 市ID
	 */
	private Long cityId;
	/**
	 * 区或县ID
	 */
	private Long regionId;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 是否是默认地址(0-否，1-是)
	 */
	private Integer isDefault;

	/**
	 * 代理商地址ID
	 */
	private Integer revId;

	/**
	 * 是否是工厂创建的地址  1：是 2：不是
	 */
	private Integer isFact;

	/**
	 * 工厂主键
	 */
	private Integer factId;

	public Integer getFactId() {
		return factId;
	}

	public void setFactId(Integer factId) {
		this.factId = factId;
	}

	public Integer getIsFact() {
		return isFact;
	}

	public void setIsFact(Integer isFact) {
		this.isFact = isFact;
	}

	public Integer getRevId() {
		return revId;
	}

	public void setRevId(Integer revId) {
		this.revId = revId;
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
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：省ID
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省ID
	 */
	public Long getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市ID
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市ID
	 */
	public Long getCityId() {
		return cityId;
	}
	/**
	 * 设置：区或县ID
	 */
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	/**
	 * 获取：区或县ID
	 */
	public Long getRegionId() {
		return regionId;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * 设置：电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮件
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：是否是默认地址(0-否，1-是)
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：是否是默认地址(0-否，1-是)
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	@Override
	public String toString() {
		return "MemberAddressEntity{" +
				"id=" + id +
				", memberId=" + memberId +
				", memberName='" + memberName + '\'' +
				", phone='" + phone + '\'' +
				", provinceId=" + provinceId +
				", cityId=" + cityId +
				", regionId=" + regionId +
				", address='" + address + '\'' +
				", postcode='" + postcode + '\'' +
				", email='" + email + '\'' +
				", isDefault=" + isDefault +
				", revId=" + revId +
				'}';
	}
}
