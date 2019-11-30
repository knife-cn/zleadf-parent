package com.zlead.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员账户
 * 
 * @author fqf
 * @date 2018-07-23 14:56:41
 */
public class MemberAcctEntity implements Serializable {
	public static final int BASE_ACCOUNT=0;
	public static final int POINT_ACCOUNT=1;
	public static final int SHOPPINGPOINT_ACCOUNT=2;
	public static final int SHOPPINGCASH_ACCOUNT=3;
	public static final int AGENT_CASH_ACCOUNT=4;
	public static final int VOUCHER_ACCOUNT=5;//代金券账户

	private static final long serialVersionUID = 1L;

	/**
	 * 会员账户ID 0-10000留用内部会员  10000-为分享会员
	 */
		private Long id;
	/**
	 * 
	 */
	private String memberid;
	/**
	 * 会员名称
	 */
	private String username;
	/**
	 * 账户类型   0-基本账户 1-积分账户  
	 */
	private Integer accounttype;
	/**
	 * 
	 */
	private String acctName;
	/**
	 * 账户金额
	 */
	private BigDecimal account;
	/**
	 * 可用账户余额
	 */
	private BigDecimal blance;
	/**
	 * 冻结金额
	 */
	private BigDecimal freezeBlance;
	/**
	 * 用户类型,根据会员表的membertype
	 */
	private Integer membertype;
	/**
	 * 
	 */
	private String systemid;
	/**
	 * 账户创建日期
	 */
	private Date createDate;
	/**
	 * 账户修改日期
	 */
	private Date modifyDate;
	/**
	 * 冻结修改日期
	 */
	private Date freezeDate;
	/**
	 * 
	 */
	private String remark;

	/**
	 * 设置：会员账户ID 0-10000留用内部会员  10000-为分享会员
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：会员账户ID 0-10000留用内部会员  10000-为分享会员
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	/**
	 * 获取：
	 */
	public String getMemberid() {
		return memberid;
	}
	/**
	 * 设置：会员名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：会员名称
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：账户类型   0-基本账户 1-积分账户  
	 */
	public void setAccounttype(Integer accounttype) {
		this.accounttype = accounttype;
	}
	/**
	 * 获取：账户类型   0-基本账户 1-积分账户  
	 */
	public Integer getAccounttype() {
		return accounttype;
	}
	/**
	 * 设置：
	 */
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	/**
	 * 获取：
	 */
	public String getAcctName() {
		return acctName;
	}
	/**
	 * 设置：账户金额
	 */
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	/**
	 * 获取：账户金额
	 */
	public BigDecimal getAccount() {
		return account;
	}
	/**
	 * 设置：可用账户余额
	 */
	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}
	/**
	 * 获取：可用账户余额
	 */
	public BigDecimal getBlance() {
		return blance;
	}
	/**
	 * 设置：冻结金额
	 */
	public void setFreezeBlance(BigDecimal freezeBlance) {
		this.freezeBlance = freezeBlance;
	}
	/**
	 * 获取：冻结金额
	 */
	public BigDecimal getFreezeBlance() {
		return freezeBlance;
	}
	/**
	 * 设置：用户类型,根据会员表的membertype
	 */
	public void setMembertype(Integer membertype) {
		this.membertype = membertype;
	}
	/**
	 * 获取：用户类型,根据会员表的membertype
	 */
	public Integer getMembertype() {
		return membertype;
	}
	/**
	 * 设置：
	 */
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	/**
	 * 获取：
	 */
	public String getSystemid() {
		return systemid;
	}
	/**
	 * 设置：账户创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：账户创建日期
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：账户修改日期
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * 获取：账户修改日期
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 设置：冻结修改日期
	 */
	public void setFreezeDate(Date freezeDate) {
		this.freezeDate = freezeDate;
	}
	/**
	 * 获取：冻结修改日期
	 */
	public Date getFreezeDate() {
		return freezeDate;
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

	public static MemberAcctEntity newInstance(MemberEntity member,Integer acctType,String remark,String systemId){
		MemberAcctEntity newMemberAcct = new MemberAcctEntity();
		newMemberAcct.setUsername(member.getUsername());
		newMemberAcct.setAccounttype(acctType);
		if(acctType==0){
			newMemberAcct.setAcctName("现金账户");
			if(remark==null) remark="新增现金账户";
		}else if(acctType==1){
			newMemberAcct.setAcctName("积分账户");
			if(remark==null) remark="新增积分账户";
		}else if(acctType==2){
			newMemberAcct.setAcctName("购物积分账户");
			if(remark==null) remark="新增购物积分账户";
		}else if(acctType==3){
			newMemberAcct.setAcctName("购物现金账户");
			if(remark==null) remark="新增购物现金账户";
		}else if(acctType==4){
			newMemberAcct.setAcctName("代理商现金账户");
			if(remark==null) remark="新增代理商现金账户";
		}else if(acctType==5){
			newMemberAcct.setAcctName("代金券账户");
			if(remark==null) remark="新增代金券账户";
		}

		newMemberAcct.setAccount(new BigDecimal(0.0));
		newMemberAcct.setBlance(new BigDecimal(0.0));
		newMemberAcct.setFreezeBlance(new BigDecimal(0.0));
		newMemberAcct.setMembertype(member.getMemberType());
		if (member != null ) {
			newMemberAcct.setMemberid(member.getMemberId());
		}
		newMemberAcct.setSystemid(systemId);
		newMemberAcct.setCreateDate(new Date());
		newMemberAcct.setRemark(remark);
		return newMemberAcct;
	}
}
