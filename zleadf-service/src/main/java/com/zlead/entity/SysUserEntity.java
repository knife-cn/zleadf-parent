package com.zlead.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 平台用户
 * 
 * @author fqf
 * @date 2018-07-19 14:20:20
 */
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序列
	 */
		private Long id;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 登录用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别0 女 1男 2 保密
	 */
	private Integer gender;
	/**
	 * 头像地址
	 */
	private String imgPath;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 是否启用
	 */
	private Integer enabled;
	/**
	 * 禁用原因
	 */
	private String disableReason;
	/**
	 * 最后访问IP
	 */
	private String lastIp;
	/**
	 * 最后访问时间
	 */
	private Long lastVisiTime;
	/**
	 * 是否删除
	 */
	private Integer isDel;
	/**
	 * 添加时间
	 */
	private Long addTime;
	/**
	 * 添加人名称
	 */
	private String addUserName;
	/**
	 * 添加人ID
	 */
	private Long addUserId;
	/**
	 * VIP会员类型： 0：不是vip，1：是vip	
	 */
	private Integer vipType;
	/**
	 * 购买物品获得积分数量
	 */
	private BigDecimal integral;
	/**
	 * 用户类型 代理商5 供应商2
	 */
	private Integer userType;

	/**
	 * 设置：序列
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：序列
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 设置：登录用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：登录用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * 设置：性别0 女 1男 2 保密
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别0 女 1男 2 保密
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：头像地址
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * 获取：头像地址
	 */
	public String getImgPath() {
		return imgPath;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：是否启用
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	/**
	 * 获取：是否启用
	 */
	public Integer getEnabled() {
		return enabled;
	}
	/**
	 * 设置：禁用原因
	 */
	public void setDisableReason(String disableReason) {
		this.disableReason = disableReason;
	}
	/**
	 * 获取：禁用原因
	 */
	public String getDisableReason() {
		return disableReason;
	}
	/**
	 * 设置：最后访问IP
	 */
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	/**
	 * 获取：最后访问IP
	 */
	public String getLastIp() {
		return lastIp;
	}
	/**
	 * 设置：最后访问时间
	 */
	public void setLastVisiTime(Long lastVisiTime) {
		this.lastVisiTime = lastVisiTime;
	}
	/**
	 * 获取：最后访问时间
	 */
	public Long getLastVisiTime() {
		return lastVisiTime;
	}
	/**
	 * 设置：是否删除
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsDel() {
		return isDel;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Long getAddTime() {
		return addTime;
	}
	/**
	 * 设置：添加人名称
	 */
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	/**
	 * 获取：添加人名称
	 */
	public String getAddUserName() {
		return addUserName;
	}
	/**
	 * 设置：添加人ID
	 */
	public void setAddUserId(Long addUserId) {
		this.addUserId = addUserId;
	}
	/**
	 * 获取：添加人ID
	 */
	public Long getAddUserId() {
		return addUserId;
	}
	/**
	 * 设置：VIP会员类型： 0：不是vip，1：是vip	
	 */
	public void setVipType(Integer vipType) {
		this.vipType = vipType;
	}
	/**
	 * 获取：VIP会员类型： 0：不是vip，1：是vip	
	 */
	public Integer getVipType() {
		return vipType;
	}
	/**
	 * 设置：购买物品获得积分数量
	 */
	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
	/**
	 * 获取：购买物品获得积分数量
	 */
	public BigDecimal getIntegral() {
		return integral;
	}
	/**
	 * 设置：用户类型 代理商5 供应商2
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型 代理商5 供应商2
	 */
	public Integer getUserType() {
		return userType;
	}
}
