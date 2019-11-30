package com.zlead.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderListVo implements Serializable {
    private Long agentId;
    private String agentName;
    private BigDecimal shippingCost = BigDecimal.ZERO;
    private BigDecimal goodsAmount = BigDecimal.ZERO;
    private Integer payType;
    private Long orderId;
    private String sn;
    private Integer status;
    private List<OrderGoodsListVo> orderGoodsListVos;
    private Integer goodscnt = 0;
    private String factoryId;

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderGoodsListVo> getOrderGoodsListVos() {
        return orderGoodsListVos;
    }

    public void setOrderGoodsListVos(List<OrderGoodsListVo> orderGoodsListVos) {
        this.orderGoodsListVos = orderGoodsListVos;
    }

    public Integer getGoodscnt() {
        return goodscnt;
    }

    public void setGoodscnt(Integer goodscnt) {
        this.goodscnt = goodscnt;
    }

}
