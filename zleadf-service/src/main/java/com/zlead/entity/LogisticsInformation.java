package com.zlead.entity;

import java.util.Date;

/**
* 物流信息实体
* @Author: fqf
* @Date: 14:28 2018/4/25
**/
public class LogisticsInformation {
    private Long logisticsInformationId;

    private Long orderId;

    private Date createDate;

    private String expressNu;

    private String comOld;

    private String comNew;

    private String comCode;

    private String message;

    private String state;

    private String stateCondition;

    private String isCheck;

    private String expressData;

    private String dataType;

    private String currentState;

    public LogisticsInformation(Long logisticsInformationId, Long orderId, Date createDate, String expressNu, String comOld, String comNew, String comCode, String message, String state, String stateCondition, String isCheck, String expressData, String dataType, String currentState) {
        this.logisticsInformationId = logisticsInformationId;
        this.orderId = orderId;
        this.createDate = createDate;
        this.expressNu = expressNu;
        this.comOld = comOld;
        this.comNew = comNew;
        this.comCode = comCode;
        this.message = message;
        this.state = state;
        this.stateCondition = stateCondition;
        this.isCheck = isCheck;
        this.expressData = expressData;
        this.dataType = dataType;
        this.currentState = currentState;
    }

    public LogisticsInformation() {
        super();
    }

    public Long getLogisticsInformationId() {
        return logisticsInformationId;
    }

    public void setLogisticsInformationId(Long logisticsInformationId) {
        this.logisticsInformationId = logisticsInformationId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId ;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExpressNu() {
        return expressNu;
    }

    public void setExpressNu(String expressNu) {
        this.expressNu = expressNu == null ? null : expressNu.trim();
    }

    public String getComOld() {
        return comOld;
    }

    public void setComOld(String comOld) {
        this.comOld = comOld == null ? null : comOld.trim();
    }

    public String getComNew() {
        return comNew;
    }

    public void setComNew(String comNew) {
        this.comNew = comNew == null ? null : comNew.trim();
    }

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode == null ? null : comCode.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStateCondition() {
        return stateCondition;
    }

    public void setStateCondition(String stateCondition) {
        this.stateCondition = stateCondition == null ? null : stateCondition.trim();
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
    }

    public String getExpressData() {
        return expressData;
    }

    public void setExpressData(String expressData) {
        this.expressData = expressData == null ? null : expressData.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }


    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState == null ? null : currentState.trim();
    }
}