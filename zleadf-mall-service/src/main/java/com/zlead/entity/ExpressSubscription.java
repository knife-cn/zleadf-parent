package com.zlead.entity;

import java.util.Date;

public class ExpressSubscription {
    private Long expressSubscriptionId;

    private String orderSn;

    private String requestData;

    private String expressNu;

    private String result;

    private String returnCode;

    private String message;

    private String data;

    private Integer dataType;

    private Date createDate;

    public ExpressSubscription(Long expressSubscriptionId, String orderSn, String requestData, String expressNu, String result, String returnCode, String message, String data, Integer dataType, Date createDate) {
        this.expressSubscriptionId = expressSubscriptionId;
        this.orderSn = orderSn;
        this.requestData = requestData;
        this.expressNu = expressNu;
        this.result = result;
        this.returnCode = returnCode;
        this.message = message;
        this.data = data;
        this.dataType = dataType;
        this.createDate = createDate;
    }

    public ExpressSubscription() {
        super();
    }

    public Long getExpressSubscriptionId() {
        return expressSubscriptionId;
    }

    public void setExpressSubscriptionId(Long expressSubscriptionId) {
        this.expressSubscriptionId = expressSubscriptionId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData == null ? null : requestData.trim();
    }

    public String getExpressNu() {
        return expressNu;
    }

    public void setExpressNu(String expressNu) {
        this.expressNu = expressNu == null ? null : expressNu.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}