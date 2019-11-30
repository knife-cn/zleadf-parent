package com.xhs.util;

import com.zlead.entity.GoodsEntity;
import com.zlead.fplat.entity.OaAgentMas;

import java.math.BigDecimal;

public class PriceUtil {
    /**
     * 代理商折扣类型(1:经销价;2:批发价;3:零售价)
     * AGENT_DISCOUNT_TYPE
     * AGENT_DISCOUNT
     * @param goods
     * @param agentMas
     * @return
     */
    public static BigDecimal getPrice(GoodsEntity goods, OaAgentMas agentMas){
        switch (agentMas.getAgentDiscountType()){
            //1:经销价;2:批发价;3:零售价
            case "1":
                return goods.getAgentPrice().multiply(new BigDecimal(agentMas.getAgentDiscount()));
            case "2":
                return goods.getSupplyPrice().multiply(new BigDecimal(agentMas.getAgentDiscount()));
            case "3":
                return goods.getMarketPrice().multiply(new BigDecimal(agentMas.getAgentDiscount()));
            default:
                return goods.getPrice();
        }
    }
}
