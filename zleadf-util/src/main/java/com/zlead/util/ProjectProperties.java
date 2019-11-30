package com.zlead.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 项目配置，对应project.properties文件
 */
public class ProjectProperties {

    public static String urlPrefix;//url前缀，是整个项目的url前缀，不止是微信用到，支付宝与银联也用到。
    public static String solrUrl;// solr地址
    public static String juheExpApi;// 聚合数据的快递api的key
    public static String juheBarcodeApi;//聚合数据的条形码的api的key
    public static String fileCtx;// 文件服务上下文
    public static long adminRoleId;// admin角色id
    public static long agentRoleId;// 代理商角色id
    public static long supplierRoleId;// 供应商角色id
    public static long enterpriseRoleId;// 企业角色id
    public static long cwRoleId;// 财务角色id
    public static long glyRoleId;// 管理员角色id
    public static long defaultAgentUserId;// 默认代理商用户ID
    public static long meiRongCatId;// 美容分类ID
    public static long shengXianCatId;// 生鲜分类ID
    public static String lvbBizid;//腾讯直播bizid
    public static String lvbKey;//腾讯直播防盗链秘钥
    public static String lvbApiKey;//腾讯直播接口秘钥
    public static String lvbAppid;//腾讯直播appid
    public static String imSdkAppId;   //腾讯云通信业务id
    public static String imAccountType;    //腾讯云通信业务账号类型
    public static String imAdmin;   //腾讯云通信管理员
    public static String playSecretId;  //点播APIID
    public static String playSecretKey;  //点播API秘钥

    //本系统调用其他平台接口配置
//    public static String tradeUrl; //交易盘接口地址
//    public static String tradeApiKey;//交易盘接口key
//    public static String tradeApiSecret;//交易盘接口Secret

    //其他平台调用本系统接口验证
    public static String shopApiKey; //本系统接口key
    public static String shopApiSecret;//本系统接口Secret

    //微信公众号配置
    public static String appId; //公众号appid
    public static String appSecret; //公众号appSecret

    //终端接口签名
    public static String secretId; //鉴权id
    public static String secretKey; //鉴权key

    //接口token值
    public static String accountToken;

    //企业相关的信息
    public static String companyAppAddress;

    //B端注册接口
    public static String bServerUrl;


    private static Logger logger = LoggerFactory.getLogger(ProjectProperties.class);

    static {
        loadProperty(); // 加载配置文件
    }

    // 加载配置文件
    public static void loadProperty() {
        Properties properties = getProperties();
        urlPrefix = properties.getProperty("urlPrefix");
//        solrUrl = properties.getProperty("solrUrl");
//        juheExpApi = properties.getProperty("juheExpApi");
//        juheBarcodeApi = properties.getProperty("juheBarcodeApi");
        fileCtx = properties.getProperty("fileCtx");
//        adminRoleId = Long.parseLong(properties.getProperty("adminRoleId"));
//        glyRoleId = Long.parseLong(properties.getProperty("glyRoleId"));
//        agentRoleId = Long.parseLong(properties.getProperty("agentRoleId"));
//        supplierRoleId = Long.parseLong(properties.getProperty("supplierRoleId"));
//        enterpriseRoleId = Long.parseLong(properties.getProperty("enterpriseRoleId"));
//        cwRoleId = Long.parseLong(properties.getProperty("cwRoleId"));
//        defaultAgentUserId = Long.parseLong(properties.getProperty("defaultAgentUserId"));
//        meiRongCatId = Long.parseLong(properties.getProperty("meiRongCatId"));
//        shengXianCatId = Long.parseLong(properties.getProperty("shengXianCatId"));
//        lvbBizid = properties.getProperty("lvbBizid");
//        lvbKey = properties.getProperty("lvbKey");
//        lvbApiKey = properties.getProperty("lvbApiKey");
//        lvbAppid = properties.getProperty("lvbAppid");
//        imSdkAppId = properties.getProperty("imSdkAppId");
//        imAccountType = properties.getProperty("imAccountType");
//        imAdmin = properties.getProperty("imAdmin");
//        playSecretId = properties.getProperty("playSecretId");
//        playSecretKey = properties.getProperty("playSecretKey");

//        tradeUrl = properties.getProperty("tradeUrl");
//        tradeApiKey = properties.getProperty("tradeApiKey");
//        tradeApiSecret = properties.getProperty("tradeApiSecret");
//        shopApiKey = properties.getProperty("shopApiKey");
//        shopApiSecret = properties.getProperty("shopApiSecret");
//
//        appId = properties.getProperty("appId");
//        appSecret = properties.getProperty("appSecret");
//        secretId = properties.getProperty("secretId");
//        secretKey = properties.getProperty("secretKey");
//        accountToken = properties.getProperty("accountToken");

        //企业相关信息
//        companyAppAddress = properties.getProperty("companyAppAddress");
        //B端注册接口
        bServerUrl = properties.getProperty("BServerUrl");
//        if (urlPrefix == null || solrUrl == null || juheExpApi == null || fileCtx == null || lvbBizid == null || lvbKey == null || lvbApiKey == null || lvbAppid == null) {
//            logger.error("获取项目配置出错");
//        }
    }

    // 获取配置文件
    private static Properties getProperties() {
        InputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = ProjectProperties.class.getClassLoader().getResourceAsStream("project.properties");
            if (inputStream == null) {
                logger.error("配置文件不存在：project.properties");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("加载配置文件出错", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (properties == null) {
            logger.error("加载配置文件失败");
        }
        return properties;
    }
}
