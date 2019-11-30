package com.zlead.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Arrays;
import java.util.Map;

public class BClientHttp {
    private static final String appid = "factory";
    private static final String appsecret = "qzed+suj4+hzudl9zrqw9q==";

    public static JSONObject post(String url, Map<String, String> map) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
            if (MapUtils.isNotEmpty(map)) {
                if (map.containsKey("Mobile")) {
                    httpPost.addHeader("authinfo", msgAPIS(map));
                }
            }
            System.out.println("data:" + JSONObject.toJSONString(map));
            StringEntity entity = new StringEntity(JSONObject.toJSONString(map), "utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                if (responseEntity != null && responseEntity.getContentEncoding() != null
                        && responseEntity.getContentEncoding().toString().equalsIgnoreCase("Content-Encoding: gzip")) {
                    response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                    responseEntity = response.getEntity();
                }
                String responseContent = EntityUtils.toString(responseEntity);
                return JSONObject.parseObject(responseContent);
            } else {
                System.out.println(EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验签
     */
    private static String msgAPIS(Map<String, String> map) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String nonce = RandNumIs.createRandNum();
        JSONObject jsonT = new JSONObject();
        String signature = "";
        //拼接
        signature = timestamp + nonce + appsecret + JSONObject.toJSONString(map);
        System.out.println("1" + signature);
        char[] arrayCh = signature.toCharArray();
        Arrays.sort(arrayCh);//数组排序
        signature = new String(arrayCh);
        System.out.println("2" + signature);
        signature = MD5Util.interfaceToMD5(signature).toUpperCase();//MD5加密
        System.out.println("3" + signature);
        String authinfo = "";
        jsonT.put("timestamp", timestamp);
        jsonT.put("nonce", nonce);
        jsonT.put("signature", signature);
        jsonT.put("appid", appid);
        if (map.containsKey("Password")){
            jsonT.put("passWord",map.get("Password"));
        }
        //base64编码
        System.out.println(jsonT.toJSONString());
        authinfo = Base64.encodeBase64String(jsonT.toJSONString().getBytes());
        System.out.println(authinfo);
        return authinfo;
    }
}
