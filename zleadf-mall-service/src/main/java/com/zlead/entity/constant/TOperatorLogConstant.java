package com.zlead.entity.constant;

/**
 * 系统操作日志相关常量
 * @author mengyouming
 */
public class TOperatorLogConstant {
	
	/**
	 * 日志模块-订单
	 */
	public static final String MODULE_ORDER = "ORDER";
	
	/**
	 * 日志模块-订单 下级分类：创建
	 */
	public static final String TITLE_ORDER_CREATE = "CREATE";
	
	/**
	 * 日志模块-订单 下级分类：支付
	 */
	public static final String TITLE_ORDER_PAY = "PAY";
	
	/**
	 * 日志模块-订单 下级分类：出库
	 */
	public static final String TITLE_ORDER_SEND = "SEND";
	
	/**
	 * 日志模块-订单 下级分类：收货
	 */
	public static final String TITLE_ORDER_RECEIVE = "RECEIVE";
	
	/**
	 * 操作结果-成功状态
	 */
	public static final Integer OPERATOR_STATUS_OK = 200;
	
	/**
	 * 日志种类-系统日志
	 */
	public static final Integer CATEGORY_SYSTEM = 0;
	
	/**
	 * 日志种类-操作日志
	 */
	public static final Integer CATEGORY_OPERATOR = 1;
	
	/**
	 * 系统编号-后台管理
	 */
	public static final Integer SOURCE_MGT = 2;
	
	/**
	 * 系统编号-电商平台
	 */
	public static final Integer SOURCE_MALL = 1;

}
