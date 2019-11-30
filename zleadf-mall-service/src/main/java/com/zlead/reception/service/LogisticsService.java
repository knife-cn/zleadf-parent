package com.zlead.reception.service;

import java.util.Map;

/**
 * 物流信息
 * @author: fqf
 * @create: 2018-04-18 11:40
 **/
public interface LogisticsService {

    /**
     * 查询物流跟踪信息实时查询
     * @param orderSn 订单编号
     * @return
     * @author fqf
     */
    Map<String,Object> getLogisticsInfoRealTime(String orderSn);

    /**
     * 查询订单所属的快递公司编号
     * @param expressNu 快递单号
     * @return
     * @author fqf
     */
    String getExpressComNum(String expressNu);

    /**
     * 快递100物流信息订阅
     * @author fqf
     */
    void expressInfoPush();

    /**
     * 快递100信息推送回调方法（保存返回的快递信息）
     * @param param
     * @author fqf
     */
    void getExpressPushInfo(String param);

    /**
     * 查询保存的最新的物流信息
     * @param orderSn
     * @return
     * @author fqf
     */
    Map<String,Object> getLogisticsInfo(String orderSn);
}
