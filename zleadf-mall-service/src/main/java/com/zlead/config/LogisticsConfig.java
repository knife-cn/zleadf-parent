package com.zlead.config;


import com.zlead.utils.LogisticsInfoUtil;

/**
 * 读取物流信息配置
 * @author: fqf
 * @create: 2018-04-26 17:17
 **/
public class LogisticsConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 获取快递100key值
    public static String key = LogisticsInfoUtil.instants().getValue("key");

    //获取快递100公司编号
    public static String customer = LogisticsInfoUtil.instants().getValue("customer");

    //快递100物流推送回调地址
    public static String back_url = LogisticsInfoUtil.instants().getValue("back_url");
}
