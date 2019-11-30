package com.zlead.constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by huKaiXuan on 2015/5/21.
 */
public class SysCnst {
    /** 将日期格式化为2012-12-12的格式，不带时分秒 */
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    /** 将日期格式化为2012-12-12 12:12:12的格式，带时分秒 */
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** 随机数 */
    public static final Random RANDOM = new Random();
    /** 请求加密校验 key */
    public static final String ENCODER_KEY = "3b38e11ffd65698aedeb5ffc";
    /** 用户 */
    public static class UserCnst {

        public static final int GENDER_FEMALE = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_SECRET = 2;

        /** 名称数组 */
        public static final String[] GENDER_NAME_ARR = new String[]{"女","男","保密"};
        /** Map，键为ID，值为名称 */
        public static final Map<Integer, String> GENDER_MAP_ID_NAME = new LinkedHashMap<Integer, String>() {{
            put(GENDER_FEMALE, GENDER_NAME_ARR[0]);
            put(GENDER_MALE, GENDER_NAME_ARR[1]);
            put(GENDER_SECRET, GENDER_NAME_ARR[2]);
        }};
        /** Map，键为名称，值为ID */
        public static final Map<String, Integer> GENDER_MAP_NAME_ID = new LinkedHashMap<String, Integer>() {{
            put(GENDER_NAME_ARR[0], GENDER_FEMALE);
            put(GENDER_NAME_ARR[1], GENDER_MALE);
            put(GENDER_NAME_ARR[2], GENDER_SECRET);
        }};
    }

    public static class MenuCnst{
        public static final int MENU_TOP = 0;
        public static final int MENU_LEFT = 1;
        public static final int MENU_NAV = 2;

        public static final int OP_TYPE_MENU = 0;
        public static final int OP_TYPE_OPERA = 1;

        public static final Map<Integer, String> OP_TYPE_MAP = new LinkedHashMap<Integer, String>(){{
            put(OP_TYPE_MENU,"菜单型");
            put(OP_TYPE_OPERA,"操作型");
        }};
    }

    /**
     * 将日期格式化为20121212121212222的格式，用于转换成long类型的数据
     */
    public static final SimpleDateFormat SDF_TIME_NUM = new SimpleDateFormat("yyyyMMddHHmmssSSS");
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

    // 登录常量
    public static class LoginCnst {
        public static final String USER_NAME_OR_PWD_ERROR = "用户名或密码错误";
        public static final String USER_NAME_OR_PWD_BLANK = "用户名或密码为空";
        public static final String KAPTCHA_ERROR = "验证码不正确";
        public static final String USER_NOT_EXISTS = "用户不存在";
        public static final String USER_IS_DISABLE = "用户已禁用，请联系管理员";
        public static final String USER_IS_DEL = "用户已删除，请联系管理员";
        public static final String PWD_ERROR = "密码错误";
        public static final String LOGIN_SUCCESS = "登录成功";
        public static final String SYSTEM_ERROR = "系统异常，请联系管理员";
        public static final String PWD_ERROR_OVER_TIMES = "密码错误超过{0}次";
        /** 登录尝试次数的session key*/
        public static final String LOGIN_TRY_TIMES_KEY = "loginTryTimes";
        /** 出现验证码之前允许尝试多少次 */
        public static int ALLOW_TRY_TIMES_BEFORE_KAPTCHA = 3;
        /** 锁定用户之前允许尝试多少次 */
        public static int ALLOW_TRY_TIMES_BEFORE_LOCK_USER = 10;
        /** 尝试多次之后是否锁定用户 */
        public static boolean IS_LOCK_USER = false;

    }

    public static String NO_PERMISSION = "没有访问权限";
    public static String CANT_DEL_SYSTEM_MENU = "系统菜单不允许删除";
}
