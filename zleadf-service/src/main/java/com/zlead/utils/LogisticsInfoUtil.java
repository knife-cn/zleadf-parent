package com.zlead.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 快递类读取文件
 * @author: fqf
 * @create: 2018-04-26 17:19
 **/
public class LogisticsInfoUtil {
    /**
     * 调试变量，用以打印调试错误信息
     */
    public static boolean DEBUG = false;
    /**
     * 系统配置文件名称
     */
    private final static String SYSTEM_CONFIG = "logisticsInfo.properties";
    /**
     * 单子模式实例
     */
    private static LogisticsInfoUtil config;
    /**
     * map
     */
    private Map<String, String> params;

    private LogisticsInfoUtil() {
        load();
    }

    /**
     * 单子模式实例方法
     *
     * @return
     */
    public static LogisticsInfoUtil instants() {
        if (null == config) {
            config = new LogisticsInfoUtil();
        }
        return config;
    }

    /**
     * 重新载入资源文件
     */
    public void reload() {
        load();
    }

    /*
     * 载入properties资源文件
     */
    @SuppressWarnings({"unchecked"})
    private void load() {
        if (null == this.params) {
            this.params = new HashMap<String, String>();
        } else {
            this.params.clear();
        }
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(SYSTEM_CONFIG);
        try {
            properties.load(inputStream);
            Iterator it = properties.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                if (null != key && !key.toString().equals("")) {
                    String value = properties.getProperty(key.toString());
                    params.put(key.toString().trim(), value.trim());
                }
            }
        } catch (IOException e) {
            if (DEBUG) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得资源文件中指定key的的value值
     */
    public String getValue(String key) {
        return this.params.get(key);
    }
}
