package com.zlead.entity;


import java.math.BigDecimal;

/**
 * 转账资金订单表
 * */
public class FinorderEntity implements java.io.Serializable{

	public FinorderEntity() {
	}

	private Long id;
	
	/**
	 * 交易类型:1001 "充值",1002 "提现",1003 "付款"
	 * */
	private String dealtype;
	
	/**
	 * 交易用户id
	 * */
	private Long memberid;
	
	/**
	 * 
	 * */
	private String requesttime;
	
	/**
	 * 金额
	 * */
	private BigDecimal amount;
	
	/**
	 * 返回报文
	 * */
	private String responsemsg;
	
	/**
	 * 请求报文
	 * */
	private String requestmsg;

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * 	 审批状态: 0:待审批 1:已完成审批 2:审批不通过 3:无需审批4:失败
	 * */
	private String auditStatus;
	/**
	 * 0：交易未处理；1：交易处理成功；2：第三方支付交易失败；3：平台处理失败
	 * */
	private Integer status;
	
	/**
	 * 交易单号
	 * */
	private Long out_trade_no;
	
	/**
	 * 描述
	 * */
	private String remark;
	
	/**
	 * 0:微信转账
	 * */
	private Integer paytype;
	/**
	 * 提现账户类型：0.基本账户3.经营账户
	 * */
	private Integer accountType;
	
	

	/**
	 * 
	 * */
	private String responsetime;
	
	/**
	 * 收款方微信账号
	 * */
	private String payeeAccount;
	
	/**
	 * 付款方商户显示姓名
	 * */
	private String payerShowName;
	
	/**
	 * 10:资金提现 
	 * */
	private String acctType;
	
	/**
	 * 提现审批状态: 0:待审批 1:已完成审批 2:审批不通过 3:无需审批
	 * */
	private String withDrawStatus;
	
	/**
	 * 提现备注说明
	 * */
	private String withDrawRemark;

	/**
	 * 用户名称
	 * */
	private String membername;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDealtype() {
		return dealtype;
	}

	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}

	public Long getMemberid() {
		return memberid;
	}

	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}

	public String getRequesttime() {
		return requesttime;
	}

	public void setRequesttime(String requesttime) {
		this.requesttime = requesttime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getResponsemsg() {
		return responsemsg;
	}

	public void setResponsemsg(String responsemsg) {
		this.responsemsg = responsemsg;
	}

	public String getRequestmsg() {
		return requestmsg;
	}

	public void setRequestmsg(String requestmsg) {
		this.requestmsg = requestmsg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(Long out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getResponsetime() {
		return responsetime;
	}

	public void setResponsetime(String responsetime) {
		this.responsetime = responsetime;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayerShowName() {
		return payerShowName;
	}

	public void setPayerShowName(String payerShowName) {
		this.payerShowName = payerShowName;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public String getWithDrawStatus() {
		return withDrawStatus;
	}

	public void setWithDrawStatus(String withDrawStatus) {
		this.withDrawStatus = withDrawStatus;
	}

	public String getWithDrawRemark() {
		return withDrawRemark;
	}

	public void setWithDrawRemark(String withDrawRemark) {
		this.withDrawRemark = withDrawRemark;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	
	
	
}
