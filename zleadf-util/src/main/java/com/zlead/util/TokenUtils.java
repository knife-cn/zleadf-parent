package com.zlead.util;

import com.zlead.utils.MD5Util;

import java.util.UUID;

public class TokenUtils {

    public static String getToken(){
        String token = MD5Util.toMD5(UUID.randomUUID().toString()+System.currentTimeMillis());
        System.out.println("token:"+token);
        return token;
    }
}
