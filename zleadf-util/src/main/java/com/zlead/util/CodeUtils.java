package com.zlead.util;

public enum CodeUtils {
	SUCCESS(1,"成功"),
	FAIL(2,"失败"),
	PHONE_REPEAT(3,"此手机号已被占用"),
	PHONE_ISNULL(4,"手机号为空"),
	CODE_ISNULL(5,"邀请码为空"),
	LOGIN_ERROR(6,"用户名或密码错误"),
	LOGIN_INFO_ISNULL(7,"用户名/密码不能为空"),
	NO_PAY_PASSWORD(8,"请先注册提现支付密码信息"),
	ERROR_PAY_PASSWORD(9,"支付密码错误"),
	FINISH_PAY(10,"已完成支付,不可重复支付"),
	SUCCESS_YUE_PAY(11,"成功完成余额支付"),
	ORDER_INVALID(12,"订单已失效"),
	STOCKNUM_INVALID(13,"商品库存不足"),
	NO_ORDER_ID(14,"订单号缺失"),
	NO_USER_ID(15,"客户ID缺失"),
	NO_PAY_TYPE(16,"支付方式缺失"),
	NO_PASSWORD(17,"支付密码缺失"),
	COMMISION_PAY_SUCCESS(18,"成长金支付完成"),
	ALI_CHONG_SUCCESS(19,"支付宝支付成功"),
	ALI_CHONG_FAIL(20,"支付宝支付失败"),
	NO_DEFAULT_ADDRESS(20,"未设置默认地址"),
	KEHU_JIAQIAN_FAIL(21,"	客户端加签失败"),
	KEHU_JIAQIAN_SUCCESS(22,"客户端加签成功"),
	TRANFER_SUCCESS(23,"提现转账成功"),
	TRANFER_FAIL(24,"提现转账失败"),
	QUERY_TRANFER_SUCCESS(25,"查询转账信息成功"),
	QUERY_TRANFER_FAIL(26,"查询转账信息失败"),
	NO_ENOUGH_MONEY(27,"成长金不足"),
	TIXIAN_NO_MONEY(28,"可用金额不足以提现"),
	TOO_MIN_MONEY(29,"提现金额不能小于一毛钱"),
	NO_LOGIN(30,"用户未登录请先登录"),
	NO_GET_PARAMETER(31,"未获取有效参数"),
	CHECK_ORDER_INFO(32,"确认订单信息"),
	Success_Chg_Passwd(32,"修改密码成功"),
	Failure_Chg_Passwd(33,"修改密码失败"),
	NO_ENOUTH_GROUTHBEI(34,"成长贝不足以支付"),
	GROUTHBEI_SUCCESS_PAY(35,"成长贝已完成支付"),
	NO_ENOUTH_GROUTHJIN(36,"成长金不足以支付"),
	INVALID_ORDER(37,"订单无效"),
	BACK_TOKENID(38,"返回tokenId数据"),
	DISMATCH_PASSWORD(39,"两次输入密码不匹配"),
	TOKENID_ERROR(40,"tokenId校验不通过"),
	NEED_UPDATE_VERSION(41,"版本号过低需要更新版本"),
	WX_ORDER_SUCCESS(42,"微信下单成功"),
	DEFAULT_ADDRESS_CANT_DELETE(43,"默认地址不能删除"),
	WX_PAY_SUCCESS(44,"微信支付成功"),
	WX_PAY_FAIL(45,"微信支付失败"),
	INVALID_CONFIRM_RECEIPT(46,"非已发货订单,无法确认收货"),
	CONFIRM_RECEIPT_SUCCESS(47,"完成确认收货"),
	GET_API_USERINFO(48,"获取用户支付宝账号"),
	NEED_BIND_ACCOUNT(49,"请先绑定第三方账号"),
	PHONE_CODE_ERROR(1008, "手机验证码错误"),
	PHONE_MESSAGE_FAIL(1010, "手机认证短信发送失败"),
	PHONE_MESSAGE_SUCCESS(200, "手机认证短信发送成功"),
	INPUT_CODE_ERROR(1018,"验证码输入有误"),
	SEND_CODE_ERROR(101,"验证码有误"),
	CHECK_CODE_ERROE(302,"验证码校验失败"),
	PRODUCT_RELATION_ID_ISNULL(303,"商品不存在"),
	PRODUCT_ATTRIBUTE_ID_ISNULL(304,"商品属性分类不存在"),
	LOGIN_USER_ISNULL(314,"用户未登录"),
	PRODUCT_NUM_BEYOND(105,"所选商品数量超过库存量"),
	SHOPCAR_UPDATE_SUCCESS(102,"购物车商品信息修改成功"),
	PHONE_ACCESS(666,"该手机号可用"),
	SHOPCAR_ISNULL(325,"购物车空空如也");
	
	private Integer code;
	private String message;
	
	private CodeUtils() {
	}
	
	private CodeUtils(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
