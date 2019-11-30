package com.zlead.entity.vo;

import com.zlead.entity.OrderEntity;
import com.zlead.entity.OrderGoodsEntity;

import java.util.List;

public class OrderVo extends OrderEntity {

    private List<OrderGoodsEntity> orderGoodsList;

    // ENDO

    public List<OrderGoodsEntity> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoodsEntity> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }
}
