package com.zlead.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class SystemConfig {

    /**
     * 系统公用配置
     */
    public static Properties SYS_CONFIG = null;

    static {
        init();
    }

    public static void init() {
        SYS_CONFIG = null;
        SYS_CONFIG = new Properties();
        try {
            InputStream is = SystemConfig.class.getClassLoader()
                    .getResourceAsStream("system.properties");
            SYS_CONFIG.load(is);
        } catch (IOException e) {
            SYS_CONFIG = null;
        }
    }

    /** *********************************ngan************************ */
    /**
     * 调试变量，用以打印调试错误信息
     */
    public static boolean DEBUG = false;
    /**
     * 系统配置文件名称
     */
    private final static String SYSTEM_CONFIG = "system.properties";
    /**
     * 单子模式实例
     */
    private static SystemConfig config;
    /**
     * map
     */
    private Map<String, String> params;

    private SystemConfig() {
        load();
    }

    /**
     * 单子模式实例方法
     *
     * @return
     */
    public static SystemConfig instants() {
        if (null == config) {
            config = new SystemConfig();
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

    /**
     * 获得资源文件的指定key的int型值
     *
     * @param key
     * @return
     */
    public Integer getIntValue(String key) {
        Integer result = 0;
        String value = this.params.get(key);
        if (null != value && !value.trim().equals("")) {
            result =Integer.parseInt(value);
        }
        return result;
    }

    /**
     * 获得路径classpath:开头的会转换成classpath的相对路径WEB-INF/classes/XX/xx/末尾会追加/
     *
     * @param key
     * @return
     */
    public String getPath(String key) {
        String result = null;
        String value = this.params.get(key);
        if (null != value && !value.trim().equals("")) {
            if (value.trim().toLowerCase().startsWith("classpath:")) {
                value = value.substring("classpath:".length());
                if (value.startsWith("/")) {
                    result = "WEB-INF/classes";
                } else {
                    result = "WEB-INF/classes/";
                }
                result = result + value;
            }
        }
        return result;
    }

    /**
     * 获得键值对value值对应的key值
     *
     * @param value
     * @return String
     */
    public String getKey(String value) {
        String result = null;
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String keyvalue = params.get(key);
            if (null != keyvalue && keyvalue.trim().equals(value)) {
                result = key;
            }
        }
        return result;
    }

    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);

        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 获得键值对value值对应的key值列表
     *
     * @param value
     * @return String
     */
    public List<String> getKeys(String value) {
        List<String> result = new ArrayList<String>();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String keyvalue = params.get(key);
            if (null != keyvalue && keyvalue.trim().equals(value)) {
                result.add(key);
            }
        }
        return result;
    }
}
