package com.zlead.entity.constant;

import java.text.SimpleDateFormat;

public class SysMsgConstant {

    /**
     * 关联工厂申请系统消息内容
     */
    public static String APPLY_AGENT_FAC_CONTENT = "【直链网】代理商{0}，您于{1}时发起申请关联工厂{2}";

    /**
     * 上传凭证系统消息内容
     */
    public static String UPLOAD_VOUCHER_CONTENT = "【直链网】代理商{0}，您于{1}时，成功上传了付款凭证，付款金额{2}元";

    /**
     * 取消订单系统消息内容
     */
    public static String CANCLE_ORDER_CONTENT = "【直链网】代理商{0}，您于{1}时，取消收货，订单号{2}";

    /**
     * 确认收货申请系统消息内容
     */
    public static String RECEIVE_ORDER_CONTENT = "【直链网】代理商{0}，您于{1}时，确认收货，订单号{2}";

    /**
     * 关联工厂申请系统消息标题
     */
    public static String APPLY_AGENT_FAC_TITLE = "关联工厂";

    /**
     * 上传凭证系统消息标题
     */
    public static String UPLOAD_VOUCHER_TITLE = "上传凭证";

    /**
     * 取消订单系统消息标题
     */
    public static String CANCLE_ORDER_TITLE = "取消订单";

    /**
     * 确认收货申请系统消息标题
     */
    public static String RECEIVE_ORDER_TITLE = "确认收货";

    /**
     * 日期格式化
     */
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
