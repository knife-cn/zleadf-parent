/**
 * @program: zleadf-parent
 * @description:验签
 * @author: ytchen
 * @create: 2019-03-08 11:22
 **/
package com.zlead.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SignCheckUtilst {

    private static final String appid = "factory";
    private static final String appsecret = "qzed+suj4+hzudl9zrqw9q==";


    /**
     * 验签
     *
     * @param mobile
     * @return authinfo
     */
    public static String msgAPIS(String mobile) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonce = RandNumIs.createRandNum();
        JSONObject json = new JSONObject();
        JSONObject jsonT = new JSONObject();
        json.put("mobile", mobile);
        String signature = "";
        //拼接
        signature = timestamp + nonce + appsecret + json.toJSONString();
        char[] arrayCh = signature.toCharArray();
        Arrays.sort(arrayCh);//数组排序
        signature = new String(arrayCh);
        signature = MD5Util.interfaceToMD5(signature).toUpperCase();//MD5加密
        String authinfo = "";
        jsonT.put("timestamp", timestamp);
        jsonT.put("nonce", nonce);
        jsonT.put("signature", signature);
        jsonT.put("appid", appid);
        //base64编码
        authinfo = Base64Utils.encode(jsonT.toJSONString().getBytes());
        return authinfo;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(msgAPIS("18954152839"));


    }
}
