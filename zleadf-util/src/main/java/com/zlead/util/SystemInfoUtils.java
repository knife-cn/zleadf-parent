package com.zlead.util;

import java.net.InetAddress;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class SystemInfoUtils {
//    public static ThreadLocal<HashMap<Long, String>> serialNo=new ThreadLocal<HashMap<Long, String>>(){
//        @Override
//        protected HashMap<Long, String> initialValue() {
//            return new HashMap<>();
//        }
//    };
    public static String getLocalIp(){
        InetAddress ia=null;
        String localip = "";
        try {
            ia=ia.getLocalHost();
            String localname=ia.getHostName();
            localip=ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localip;
    }
    
	public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("Cdn-Src-Ip");    // 网宿cdn的真实ip
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");   // 蓝讯cdn的真实ip
        }
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");  // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP"); // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 获取代理ip
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr(); // 获取真实ip
        }
        return ip;
	}
}
