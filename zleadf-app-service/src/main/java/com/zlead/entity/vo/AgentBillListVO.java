package com.zlead.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AgentBillListVO {

    /**
     * 客户id
     */
    private Integer agentId;

    /**
     * 客户名称
     */
    private String agentName = "";

    /**
     * 帐单id
     */
    private Long orderId;

    /**
     * 帐单编号
     */
    private String sn;

    /**
     * 账单总金额
     */
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**
     * 账单状态 1.已结清 2.欠款
     */
    private Integer status;
    /**
     * 包含的所有的账单状态
     */
    private String statusStr;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
