package com.zlead.exception;

public enum ResultEnum {
    
    SUCCESS(1, "成功"),
    
    PARAM_ERROR(400, "参数不正确"),
    
    PRODUCT_NOT_EXIST(401, "商品不存在"),
    
    PRODUCT_STOCK_ERROR(402, "商品库存不正确"),
    
    ORDER_NOT_EXIST(403, "订单不存在"),
    
    ORDERDETAIL_NOT_EXIST(404, "订单详情不存在"),
    
    ORDER_STATUS_ERROR(500, "订单状态不正确"),
    
    ORDER_UPDATE_FAIL(501, "订单更新失败"),
    
    ORDER_DETAIL_EMPTY(502, "订单详情为空"),
    
    ORDER_PAY_STATUS_ERROR(503, "订单支付状态不正确"),
    

    
    CART_EMPTY(504, "购物车为空"),
    
    ORDER_OWNER_ERROR(505, "该订单不属于当前用户"),
    
    WECHAT_MP_ERROR(506, "微信公众账号方面错误"),
    
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(507, "微信支付异步通知金额校验不通过"),
    
    PRODUCT_STATUS_ERROR(508, "商品状态不正确"),
    
    LOGIN_FAIL(509, "登录失败, 登录信息不正确"),
    
    USER_NOT_LOGIN(510,"用户未登录"),
    
    ORDER_TYPE_ERROR(511,"订单类型不正确"),
    
    ACCOUNT_VOUCHER_NOT_ENOUGH(512,"抵用券不足"),
    ;
    
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
    
    private Integer code;
    
    private String message;
    
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
