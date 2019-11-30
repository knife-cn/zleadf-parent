package com.zlead.utils;


import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;

/**
 * @author ZHB
 */
public class AuthUtil {
    public static final String WXAPPID = "wx355cc0a957775f54";
    public static final String WXAPPSECRET = "1511386ba58b6c09515d22a5bf7b4abe";
    public static final String QQAPPID = "1107898579";
    public static final String QQAPPIKEY = "jfui4tsmQcaGXTZ2";
    public static final String WBAPPID = "101181022";
    public static final String WBAPPIKEY = "b9f40957cbcb58dd37dd9b5f286274cc";
    public static final String MCH_ID = "1518963341";
    public static final String MCHKEY = "1511386ba58b6c09515d22a5bf7b4abe";
    public static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }
}
