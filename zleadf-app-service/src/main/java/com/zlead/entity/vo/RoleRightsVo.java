package com.zlead.entity.vo;

import java.util.Map;

public class RoleRightsVo {
    /**
     * 客户列表功能权限
     */
   // private Integer agentList = 0;
    /**
     * 活动管理功能权限
     */
    private Integer activityList = 0;
    /**
     * 库存详情功能权限
     */
    private Integer stockList = 0;
    /**
     * 订单列表功能权限
     */
   // private Integer orderList = 0;

//    public Integer getAgentList() {
//        return agentList;
//    }
//
//    public void setAgentList(Integer agentList) {
//        this.agentList = agentList;
//    }

    public Integer getActivityList() {
        return activityList;
    }

    public void setActivityList(Integer activityList) {
        this.activityList = activityList;
    }

    public Integer getStockList() {
        return stockList;
    }

    public void setStockList(Integer stockList) {
        this.stockList = stockList;
    }

//    public Integer getOrderList() {
//        return orderList;
//    }
//
//    public void setOrderList(Integer orderList) {
//        this.orderList = orderList;
//    }
}
