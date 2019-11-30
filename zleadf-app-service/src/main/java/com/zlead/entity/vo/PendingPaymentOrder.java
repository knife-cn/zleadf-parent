package com.zlead.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 待还款订单列表，用于统计待还款金额
 */
public class PendingPaymentOrder implements Serializable {
    private Integer orderId;//订单ID
    private Date createDate;//订单创建时间
    private BigDecimal orderAmount;//当前订单金额
    private BigDecimal totalAmount;//当前订单之前的总金额（包括当前订单）
    private Integer voucherId;//凭证ID
    private BigDecimal voucherAmount;//凭证金额
    private BigDecimal pendingPaymentAmount;//待还款金额

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public BigDecimal getPendingPaymentAmount() {
        return pendingPaymentAmount;
    }

    public void setPendingPaymentAmount(BigDecimal pendingPaymentAmount) {
        this.pendingPaymentAmount = pendingPaymentAmount;
    }
}
