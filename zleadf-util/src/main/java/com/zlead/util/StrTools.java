package com.zlead.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class StrTools {
    /**
     * 用于字符串去重 ： 比如 "特步专柜运动鞋2019款 红色 红色 35 35"
     * 结果为：特步专柜运动鞋2019款红色35
     * @param str
     * @return
     */
    public static String strDistinct(String str){
        if (str == null || str.equals("")){
            return str;
        }
        String[] s = str.split(" ");
        if (null == s || s.length == 0){
            return str;
        }
        List<String> strings = Arrays.asList(s);
        List<String> collect = strings.stream().distinct().collect(Collectors.toList());
        StringBuffer sb = new StringBuffer();
        for (String s2 :collect ) {
            sb.append(s2+"");
        }
        return sb.toString();
    }

    /**
     * 用于字符串去重 ： 比如 "特步专柜运动鞋2019款 红色 红色 35 35"
     * 结果为：特步专柜运动鞋2019款_红色_35
     * @param str
     * @return
     */
    public static String strDistinctJoinWire(String str){
        if (str == null){
            return str;
        }else if ("".equals(str.trim())){
            return str;
        }
        str = str.trim();
        String[] s = str.split(" ");
        if (null == s || s.length == 0){
            return str;
        }
        List<String> strings = Arrays.asList(s);
        List<String> collect = strings.stream().filter(blank -> !"".equals(blank)).distinct().collect(Collectors.toList());
        StringBuffer sb = new StringBuffer();
        for (String s2 :collect ) {
            sb.append(s2+"_");
        }
        if (sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        String substring = sb.toString();
        return substring;
    }

    /**
     * 根据逗号切割,并且去重
     * 比如：12,12,12,12,12,12,12,12，13,14
     * 结果：12,13,14
     */
    public static String strIncise(String str){
        StringBuffer stringBuffer = new StringBuffer();
        if ("" == str && null == str) {
            return  str;
        }
        String[] split = str.split(",");
        if (split.length > 0) {
            TreeSet<String> hset = new TreeSet<String>(Arrays.asList(split));
            Iterator i = hset.iterator();
            while (i.hasNext()){
                stringBuffer.append(i.next()+",");
            }
        }else{
            return  str;
        }
        String substring = stringBuffer.toString().substring(0, stringBuffer.length() - 1);
        return substring;
    }

    /**
     * 根据逗号切割,返回List<Long>
     */
    public static List<Long> strSpilt(String str){
        List<Long> hset = null;
        StringBuffer stringBuffer = new StringBuffer();
        if ("" == str && null == str) {
            return  hset;
        }
        String[] split = str.split(",");
        hset = new ArrayList<Long>(split.length);
        if (split.length > 0) {
            for (String s: split) {
                hset.add(Long.parseLong(s));
            }
        }else{
            return  hset;
        }
        return hset;
    }

    /**
     * 获取本地默认图片路径
     */
    public  static String localImagesPath(HttpServletRequest request,String imageUrl){
        String scheme = request.getScheme();//http
        String serverName = request.getServerName();//localhost
        int serverPort = request.getServerPort();//8080
        String contextPath = "";
        if (imageUrl != null && !"".equals(imageUrl)){
            contextPath = imageUrl;//项目名
        }
        String url = scheme+"://"+serverName+":"+serverPort+contextPath;
        return url;
    }
}
