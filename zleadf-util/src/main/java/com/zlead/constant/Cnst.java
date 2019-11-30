package com.zlead.constant;

import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 常量
 */
public class Cnst {
    // 根路径
    public static String ROOTPATH = "";
    // 将日期格式化为20121212的格式，不带时分秒,
    public static final SimpleDateFormat SDF_SHORT = new SimpleDateFormat("yyyyMMdd");
    // 将日期格式化为2012-12-12的格式，不带时分秒
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    // 将日期格式化为2012-12-12 12:12:12的格式，带时分秒
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 将日期格式化为20121212121212222的格式，用于转换成long类型的数据
    public static final SimpleDateFormat SDF_TIME_NUM = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    // 订单号前缀，前面是年和月
    public static final SimpleDateFormat SDF_FOR_ORDER = new SimpleDateFormat("yyMM");
    // 用户登录session key
    public static final String MEMBER_SESSION_KEY = "memberSessionKey";
    // 随机数
    public static final Random RAN = new Random();
    // redis默认失效时间(秒)
    public static final long REDIS_EXPIRE_SECONDS = 30 * 60; // 半小时
    // 对接交易盘系统 处理的controller路径
    public static final String TRADE_CTRL = "/zxlg/trade/";
    // 请求加密校验 key
    public static final String ENCODER_KEY = "3b38e11ffd65698aedeb5ffc";

    public static final Pattern IDS = Pattern.compile("(\\d+,)*\\d+$"); // 逗号分隔的IDS的判断

    public static final BigDecimal ZERO = new BigDecimal(0); // 零
    public static final BigDecimal TEN = new BigDecimal(10); // 10

    public static final double PLUS_MONEY_MIN = 0.01; // 平台加价最小金额
    public static final double PLUS_MONEY_MAX = 1000000; // 平台加价最大金额

    // 记录上一次调用查询物流接口的时间，毫秒数
    public static Map<Long, Long> logisticsExpMap = Maps.newHashMap();

    public static final String PHONE_CODE = "SEND_PHONE_CODE"; //发送手机验证码
    public static final int GOODS_PAGE_SIZE = 6; // 每页查询多少条商品
    
    public static final int ALLIANCE_SHOP_TYPE = 4;//加盟商店铺类型
    public static final int ALLIANCE_TYPE = 5;//会员类型
    /**
     * 获取方便识别的当前时间毫秒数，如20151103173941521
     */
    public static Long getCurTime() {
        return Long.parseLong(SDF_TIME_NUM.format(new Date()));
    }

    /**
     * 把Long类型的时间转换成格式化时间，如把20151103173941521转换成2015-11-03
     */
    public static String getFormatDate(Long time) {
        if (time == null) {
            return "";
        }
        String tmp = time.toString();
        if (tmp.length() != 17) {
            return "";
        }
        return tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8);
    }

    /**
     * 把String类型时间转换成Long，如把2015-11-03转换成20151103000000000
     */
    public static Long getFormatDate(String time) {
        try {
            Date date = SDF.parse(time);
            return Long.parseLong(SDF_TIME_NUM.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把Long类型的时间转换成格式化时间，如把20151103173941521转换成2015-11-03 17:39:41
     */
    public static String getFormatTime(Long time) {
        if (time == null) {
            return "";
        }
        String tmp = time.toString();
        if (tmp.length() != 17) {
            return "";
        }
        return tmp.substring(0, 4) + "-" + tmp.substring(4, 6) + "-" + tmp.substring(6, 8) +
                " " + tmp.substring(8, 10) + ":" + tmp.substring(10, 12) + ":" + tmp.substring(12, 14);
    }

    /**
     * 把String类型时间转换成Long，如把2015-11-03 17:39:41转换成20151103173941521
     */
    public static Long getFormatTime(String time) {
        try {
            Date date = SDF_TIME.parse(time);
            return Long.parseLong(SDF_TIME_NUM.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    // 订单常量
    public static class OrderCnst {
        // 订单类型：0商品订单，1会员卡订单，2积分商品订单，3商家入驻订单，4积分换订单，5积分购订单 6爱聚益服务订单
        public static final int TYPE_GOODS = 0;
        public static final int TYPE_CARD = 1;
        public static final int TYPE_POINT_GOODS = 2;
        public static final int TYPE_SHOP = 3;
        public static final int TYPE_POINT = 4;
        public static final int TYPE_MONEYPOINT = 5;
        public static final int TYPE_AJYSERVER = 7;
        public static final int TYPE_ALLIANCE = 14;//会员购买加盟商的订单
        public static final Map<Integer, String> TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(TYPE_GOODS, "商品订单");
            put(TYPE_CARD, "会员卡订单");
            put(TYPE_POINT_GOODS, "积分商品订单");
            put(TYPE_SHOP, "商家入驻订单");
            put(TYPE_POINT, "积分换商品订单");
            put(TYPE_MONEYPOINT, "积分购商品订单");
            put(TYPE_AJYSERVER, "爱聚益服务订单");
        }};

        // 状态：0-待付款，1-待发货，2-待收货，3-已完成,4已失败取消，5拒收，6已退款，7申请退货中，8订单异常
        public static final int STATUS_WAIT_PAY = 0;
        public static final int STATUS_WAIT_SEND = 1;
        public static final int STATUS_SENDED = 2;
        public static final int STATUS_SUCCESS = 3;
        public static final int STATUS_CANCEL = 4;
        public static final int STATUS_REJECTION = 5;
        public static final int STATUS_REFUNDED = 6;
        public static final int STATUS_APPLY_REFUNDED = 7;
        public static final int STATUS_ERROR = 8;
        public static final int STATUS_OUTPUT = 9;
        public static final Map<Integer, String> STATUS_MAP = new LinkedHashMap<Integer, String>() {{
            put(STATUS_WAIT_PAY, "待付款");
            put(STATUS_WAIT_SEND, "待发货");
            put(STATUS_SENDED, "已发货");
            put(STATUS_SUCCESS, "交易成功");
            put(STATUS_CANCEL, "已取消");
            put(STATUS_REJECTION, "拒收");
            put(STATUS_REFUNDED, "已退款");
            put(STATUS_APPLY_REFUNDED, "申请退货中");
            put(STATUS_ERROR, "订单异常");
            put(STATUS_OUTPUT, "出库");
        }};

        // 订单来源:1-小程序、2-商城、3-内部后台、4-业务员APP
        public static final int BUY_TYPE_1 = 1;
        public static final int BUY_TYPE_2 = 2;
        public static final int BUY_TYPE_3 = 3;
        public static final int BUY_TYPE_4 = 4;
        public static final int BUY_TYPE_5 = 5;
        public static final int BUY_TYPE_6 = 6;
        public static final int BUY_TYPE_7 = 7;
        public static final Map<Integer, String> BUY_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(BUY_TYPE_1, "线上购买商品");
            put(BUY_TYPE_2, "实体店扫码购买商品");
            put(BUY_TYPE_3, "线上购买会员卡快递发货");
            put(BUY_TYPE_4, "线上下单线下支付并自提会员卡");
            put(BUY_TYPE_5, "从销售人员手中购买会员卡");
            put(BUY_TYPE_6, "从实体店购买会员卡");
            put(BUY_TYPE_7, "线上购买积分商品");
        }};

        // 支付方式(0-线上支付，1-线下支付,2对公转账)
        public static final int PAY_TYPE_ONLINE = 0; // 线上支付
        public static final int PAY_TYPE_OFFLINE_CASH = 1; // 线下现金支付
        public static final int PAY_TYPE_OFFLINE_TRANSFER = 2; // 线下对公转账
        public static final Map<Integer, String> PAY_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(PAY_TYPE_ONLINE, "线上支付");
            put(PAY_TYPE_OFFLINE_CASH, "线下现金支付");
            put(PAY_TYPE_OFFLINE_TRANSFER, "线下对公转账");
        }};

        // 快递公司
        public static final Map<String, String> EXP_COM_MAP = new LinkedHashMap<String, String>() {{
            put("zjs", "宅急送");
            put("sf", "顺丰");
            put("sto", "申通");
            put("yt", "圆通");
            put("yd", "韵达");
            put("tt", "天天");
            put("ems", "EMS");
            put("zto", "中通");
            put("ht", "汇通");
            put("qf", "全峰");
            put("db", "德邦");
        }};

        public static final int STORE_BUY_TYPE_SELF_CARRY = 0; // 自提
        public static final int STORE_BUY_TYPE_DELIVERY = 1; // 送货
    }

    // 订单商品常量
    public static class OrderGoodsCnst {
        // 评论状态：1_未评论，2_买家已评论，3_卖家已评论，4_双方已评
        public static final int COMMENT_STATUS_NO = 1;
        public static final int COMMENT_STATUS_BUYER = 2;
        public static final int COMMENT_STATUS_SELLER = 3;
        public static final int COMMENT_STATUS_BOTH = 4;
        public static final Map<Integer, String> COMMENT_STATUS_MAP = new LinkedHashMap<Integer, String>() {{
            put(COMMENT_STATUS_NO, "未评论");
            put(COMMENT_STATUS_BUYER, "买家已评论");
            put(COMMENT_STATUS_SELLER, "卖家已评论");
            put(COMMENT_STATUS_BOTH, "双方已评");
        }};

        // 渠道类型（0平台，1广告，2主播）
        public static final int CHANNEL_TYPE_PLATFORM = 1;
        public static final int CHANNEL_TYPE_AD = 2;
        public static final int CHANNEL_TYPE_PRESENTER = 3;
        public static final Map<Integer, String> CHANNEL_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(CHANNEL_TYPE_PLATFORM, "平台");
            put(CHANNEL_TYPE_AD, "广告");
            put(CHANNEL_TYPE_PRESENTER, "主播");
        }};
    }

    //会员卡常量
    public static class CardCnst {
        //会员卡状态 0未分配，1未售卖，2待支付，3已售卖，4已激活，5使用中，6作废
        public static final int STATUS_UNDISTRIBUTED = 0;
        public static final int STATUS_UNSALE = 1;
        public static final int STATUS_WAIT_PAY = 2;
        public static final int STATUS_PAY = 3;
        public static final int STATUS_ACTIVATE = 4;
        public static final int STATUS_USE = 5;
        public static final int STATUS_SCRAP = 6;
        public static final Map<Integer, String> STATUS_MAP = new LinkedHashMap<Integer, String>() {{
            put(STATUS_UNDISTRIBUTED, "未分配");
            put(STATUS_UNSALE, "未售卖");
            put(STATUS_WAIT_PAY, "待支付");
            put(STATUS_PAY, "已售卖");
            put(STATUS_ACTIVATE, "已激活");
            put(STATUS_USE, "使用中");
            put(STATUS_SCRAP, "作废");
        }};

        //售卖类型（0未售卖，1总部，2代理商，3销售员，4实体店）
        public static final int SALE_TYPE_UNSALE = 0;
        public static final int SALE_TYPE_HEADQUARTERS = 1;
        public static final int SALE_TYPE_AGENT = 2;
        public static final int SALE_TYPE_SALE = 3;
        public static final int SALE_TYPE_STORE = 4;
        public static final Map<Integer, String> SALE_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(SALE_TYPE_UNSALE, "未售卖");
            put(SALE_TYPE_HEADQUARTERS, "总部");
            put(SALE_TYPE_AGENT, "代理商");
            put(SALE_TYPE_SALE, "销售员");
            put(SALE_TYPE_STORE, "实体店");
        }};
    }

    // 购物车常量
    public static class ShoppingCartCnst {
        // 类型：0线上购物车，1线下扫码购物车，2积分购物车
        public static final int TYPE_ONLINE = 0;
        public static final int TYPE_OFFLINE = 1;
        public static final int TYPE_POINT = 2;
        public static final Map<Integer, String> TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(TYPE_ONLINE, "线上购物车");
            put(TYPE_OFFLINE, "线下扫码购物车");
            put(TYPE_POINT, "积分购物车");
        }};
    }

    /**
     * 生成固定位数的随机数
     *
     * @param strLength
     * @return
     */
    public static String getFixLengthString(int strLength) {
        Random rm = new Random();
        // 获得随机数
        double pross = (1 + rm.nextDouble());
        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);
        
        // 返回固定的长度的随机数
        if(fixLenthString.length()>strLength + 1)
        	return fixLenthString.substring(2, strLength + 2);
        else if(fixLenthString.length()>2)
        	return fixLenthString.substring(2);
        else  
        	return fixLenthString;
    }

    // 系统配置常量
    public static class SysConfigCnst {
        // 会员卡状态： 1直推比例%，2一级管理津贴比例%，3二级管理津贴比例%，
        // 4三级管理津贴比例%，5提现手续费比例%，6未冻结佣金中可提现比例%，
        // 7会员的提现日，8默认顶级用户ID，9需要登录码的角色IDS，10供应商的提现日
        public static final long DERECT_SPREAD_RATE = 1;
        public static final long FIRST_LEVEL_RATE = 2;
        public static final long SECOND_LEVEL_RATE = 3;
        public static final long THIRD_LEVEL_RATE = 4;
        public static final long WITHDRAW_FEE_RATE = 5;
        public static final long WITHDRAW_RATE_OF_UNFREEZE = 6;
        public static final long WITHDRAW_DATE_MEMBER = 7;
        public static final long DEFAULT_MEMBER_ID = 8;
        public static final long NEED_LOGIN_CODE_ROLE_IDS = 9;
        public static final long WITHDRAW_DATE_SUPPLIER = 10; //
        public static final long PERSONAL_INCOME_TAX = 11;  //个人所得税
        public static final long PERSONAL_INCOME_TAX_LEVIED = 12;   //个税征收人：1全部2员工
        public static final long WITHDRAW_DATE_STORE = 13;    //实体店的提现日
        public static final long BANK_INFO = 14;    //对公银行卡信息
        public static final long POINT_EXCHANGE_RATE = 15;    // 积分兑换比例，即一块钱可以兑换多少个积分
        public static final long ACTIVATE_CARD_RETURN_POINT_RATE = 16;    // 激活会员卡赠送积分比例
        public static final long OFFLINE_CASH_RETURN_POINT_RATE = 17;    // 线下付现金赠送积分比例
        public static final long STORE_WITHDRAW_FEE_RATE = 18;    //店铺提现手续费率

        public static final int WITHDRAW_BY_MONTH = 1; // 按月申请提现
        public static final int WITHDRAW_BY_WEEK = 2;  // 按周申请提现

        public static final int PERSONAL_INCOME_TAX_GOV = 1;  //政府税率
        public static final int PERSONAL_INCOME_TAX_CUSTOM = 2;  //自定义税率
        public static final int PERSONAL_INCOME_TAX_LEVIED_ALL = 1;  //个税征收人为全部
        public static final int PERSONAL_INCOME_TAX_LEVIED_EMPLOYEE = 2;  //个税征收人为员工

    }

    // 佣金列表
    public static class memberBrokerage {
        //佣金基本 0直推佣金，1一级管理佣金，2二级管理佣金，3三级管理佣金
        public static final int LEVEL_TYPE_0 = 0;
        public static final int LEVEL_TYPE_1 = 1;
        public static final int LEVEL_TYPE_2 = 2;
        public static final int LEVEL_TYPE_3 = 3;

    }

    // 会员卡订单支付记录
    public static class AppVersionCnst {
        //1.用户IOS，2.用户安卓，3.收款IOS，4.收款安卓
        public static final int APP_TYPE_USER_ANDROID = 0;
        public static final int APP_TYPE_USER_IOS = 1;
        public static final int APP_TYPE_RECEIVE_ANDROID = 2;
        public static final int APP_TYPE_RECEIVE_IOS = 3;
        public static Map<Integer, String> APP_TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(APP_TYPE_USER_ANDROID, "用户安卓");
            put(APP_TYPE_USER_IOS, "用户IOS");
            put(APP_TYPE_RECEIVE_ANDROID, "收款安卓");
            put(APP_TYPE_RECEIVE_IOS, "收款IOS");
        }};
    }

    // 会员卡订单支付记录
    public static class OrderPayLogCnst {
        //1支付宝，2银联，3微信，4线下现金支付，5对公转账支付，6刷卡支付 7拉卡拉支付
        public static final int CHANNEL_ALIPAY = 1;
        public static final int CHANNEL_WEIXIN = 2;
        public static final int CHANNEL_YINLIAN = 3;
        public static final int CHANNEL_OFFLINE = 4;
        public static final int CHANNEL_DUIGONG = 5;
        public static final int CHANNEL_CARD = 6;
        public static final int CHANNEL_LAKALA = 7;
        public static Map<Integer, String> CHANNEL_MAP = new LinkedHashMap<Integer, String>() {{
            put(CHANNEL_ALIPAY, "支付宝");
            put(CHANNEL_WEIXIN, "银联");
            put(CHANNEL_YINLIAN, "微信");
            put(CHANNEL_OFFLINE, "线下现金支付");
            put(CHANNEL_DUIGONG, "对公转账支付");
            put(CHANNEL_CARD, "刷卡支付");
        }};
    }

    // 余额消费记录
    public static class BalancePayLogCnst {
        public static final int PAY_TYPE_ORDER = 0; // 订单支付
        public static final int PAY_TYPE_LIVETV = 1; // 直播支付
        public static final int PAY_TYPE_FACE = 2; // 面对面支付
        public static final int PAY_TYPE_POINT = 3; // 兑换积分
    }


    //广告
    public static class ZxAds {
        //性质
        public static class AdsCat {
            public static Integer GOODS = 0;       //商品广告
            public static Integer SELLER = 1;    //商家广告

            public static Map<Integer, String> MAP = new LinkedHashMap<Integer, String>() {{
                put(GOODS, "商品广告");
                put(SELLER, "商家广告");
            }};
        }

        //首页版块
        public static class HomeType {
            public static Integer NOT = 0;      //不显示
            public static Integer ONE = 1;       //轮播图
            public static Integer TWO = 2;    //广告1
            public static Integer THREE = 3;    //广告2
            public static Integer FOUR = 4;     //广告3
            //public static Integer FIVE = 5;     //广告4
            public static Integer POINT = 6;     //积分商城轮播图
            public static Integer LEAGUE = 7;    //商盟轮播图

            public static Map<Integer, String> MAP = new LinkedHashMap<Integer, String>() {{
                put(NOT, "不显示");
                put(ONE, "轮播图");
                put(TWO, "广告1");
                put(THREE, "广告2");
                put(FOUR, "广告3");
                //put(FIVE, "广告4");
                put(POINT, "积分商城轮播图");
                put(LEAGUE, "商盟轮播图");
            }};
        }
    }

    //税率
    public static class ZxTaxRate {
        //类型
        public static class Type {
            public static Integer SALARY_TAX_EXEMPTION = 0; //工资免税额
            public static Integer SALARY_TAX = 1; //工资含税级
            public static Integer SALARY_NO_TAX = 2;  //工资不含税级

            public static Map<Integer, String> MAP = new LinkedHashMap<Integer, String>() {{
                put(SALARY_TAX_EXEMPTION, "免税额");
                put(SALARY_TAX, "工资含税级");
                //put(SALARY_NO_TAX, "工资不含税级");
            }};
        }
    }

    // 供应商常量
    public static class ZxShopCnst {
        // 供应商状态 1审核中，2审核失败，3审核通过，4营业中，5关店
        public static final int STATUS_TO_BE_AUDIT = 1;
        public static final int STATUS_FAIL = 2;
        public static final int STATUS_SUCCESS = 3;
        public static final int STATUS_IN_BUSINESS = 4;
        public static final int STATUS_CLOSE = 5;
        public static final Map<Integer, String> STATUS_MAP = new LinkedHashMap<Integer, String>() {{
            put(STATUS_TO_BE_AUDIT, "审核中");
            put(STATUS_FAIL, "审核失败");
            put(STATUS_SUCCESS, "审核通过");
            put(STATUS_IN_BUSINESS, "营业中");
            put(STATUS_CLOSE, "关店");
        }};
    }

    // 退款常量
    public static class ZxChargbackRequestCnst {
        // 0：申请退款；1：卖家通过申请、退款完成，2：卖家驳回申请，3：申诉，4：申诉通过、退款完成，5：申诉不通过
        public static final int STATUS_APPLY = 0;
        public static final int STATUS_SELLER_PASS = 1;
        public static final int STATUS_SELLER_REJECT = 2;
        public static final int STATUS_APPEAL = 3;
        public static final int STATUS_APPEAL_PASS = 4;
        public static final int STATUS_APPEAL_NOT_PASS = 5;
        public static final Map<Integer, String> STATUS_MAP = new LinkedHashMap<Integer, String>() {{
            put(STATUS_APPLY, "申请退款");
            put(STATUS_SELLER_PASS, "卖家通过申请、退款完成");
            put(STATUS_SELLER_REJECT, "卖家驳回申请");
            put(STATUS_APPEAL, "申诉");
            put(STATUS_APPEAL_PASS, "申诉通过、退款完成");
            put(STATUS_APPEAL_NOT_PASS, "申诉不通过");
        }};
    }


    // 退款日志常量
    public static class ZxChargbackRequestLogCnst {
        // 类型，0买家；1卖家
        public static final int TYPE_BUYER = 0;
        public static final int TYPE_SELLER = 1;
    }

    // 积分日志常量
    public static class PointPayLogCnst {
        // 类型，1购买会员卡返积分，2实体店现金支付返积分，3余额兑换积分，4订单退款退还积分，6线上订单支付，7直播送礼物，8实体店换商品，9主播礼物积分换余额
        public static final int TYPE_BUY_CARD = 1;
        public static final int TYPE_STORE_CASH = 2;
        public static final int TYPE_EXCHANGE = 3;
        public static final int TYPE_ORDER_REFUND = 3;
        public static final int TYPE_ONLINE_ORDER = 6;
        public static final int TYPE_PRESENT = 7;
        public static final int TYPE_STORE_GOODS = 8;
        public static final int TYPE_ZHUBO_POINT_TO_MONEY = 9;
    }

    // 会员常量
    public static class MemberCnst {
        public static final int GENDER_SECRET = 0; // 保密
        public static final int GENDER_MAN = 1; // 男
        public static final int GENDER_WOMAN = 2; // 女
    }

    // 二维码常量
    public static class QrcodeCnst {
//        二维码内容规则
        // 1_卡号  会员卡
        // 2_会员id 付款码
        // 3_店员id 店员
        // 4_积分卡号 积分卡
        // 5_宝贝id

        public static final String PREFIX_CARD = "1_"; // 会员卡上的二维码
        public static final String PREFIX_PAY_QRCODE = "2_"; // 用户的付款码
        public static final String PREFIX_STORE_USER = "3_"; // 店员的收款码
        public static final String PREFIX_POINT_CARD = "4_"; // 积分卡上的二维码
        public static final String PREFIX_GOODS = "5_"; // 宝贝
    }

    // 实体店
    public static class StoreCnst {
        // 实体店类型：1_直营店，2_联盟店
        public static final int TYPE_DIRECT_STORE = 1;
        public static final int TYPE_LEAGUE = 2;
        public static final Map<Integer, String> TYPE_MAP = new LinkedHashMap<Integer, String>() {{
            put(TYPE_DIRECT_STORE, "直营店");
            put(TYPE_LEAGUE, "联盟店");
        }};

        public static final String[] CAT_NAME_ARR = new String[]{"吃喝", "住宿", "超市", "娱乐", "健康养生", "丽人美妆", "生活服务", "热门商圈"}; // 分类
    }



}
