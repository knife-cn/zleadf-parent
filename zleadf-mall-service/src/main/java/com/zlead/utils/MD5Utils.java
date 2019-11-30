/**
 * @program: zleadf-parent
 * @description:
 * @author: ytchen
 * @create: 2019-03-06 10:10
 **/
package com.zlead.utils;
import java.security.MessageDigest;
import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
//import com.sun.javaws.Main;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

@Component
public class MD5Utils {

    public static String getMD5(String pwd, boolean isUpper, Integer bit) {
        String md5 = new String();
        try {
            // 创建加密对象
            MessageDigest md = MessageDigest.getInstance("md5");
            if (bit == 64) {
                BASE64Encoder bw = new BASE64Encoder();
                String bsB64 = bw.encode(md.digest(pwd.getBytes("utf-8")));
                md5 = bsB64;
            } else {
                // 计算MD5函数
                md.update(pwd.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer sb = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        sb.append("0");
                    sb.append(Integer.toHexString(i));
                }
                md5 = sb.toString();
                if (bit == 16) {
                    //截取32位md5为16位
                    String md16 = md5.substring(8, 24).toString();
                    md5 = md16;
                    if (isUpper)
                        md5 = md5.toUpperCase();
                    return md5;
                }
            }
            //转换成大写
            if (isUpper)
                md5 = md5.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("md5加密抛出异常！");
        }

        return md5;
    }

}