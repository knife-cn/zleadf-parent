package com.zlead.entity.dto;

import com.zlead.entity.vo.GoodsVo;
import com.zlead.entity.vo.OrderMemberInfoVo;

import java.util.Set;

public class OrderGoodsDto {
    private OrderMemberInfoVo orderMemberInfoVo;
    private Set<GoodsVo> goodsVos;


    public OrderMemberInfoVo getOrderMemberInfoVo() {
        return orderMemberInfoVo;
    }

    public void setOrderMemberInfoVo(OrderMemberInfoVo orderMemberInfoVo) {
        this.orderMemberInfoVo = orderMemberInfoVo;
    }

    public Set<GoodsVo> getGoodsVos() {
        return goodsVos;
    }

    public void setGoodsVos(Set<GoodsVo> goodsVos) {
        this.goodsVos = goodsVos;
    }
}
