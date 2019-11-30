package com.zlead.utils.wx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puqian.util.holder.ApplicationContextHolder;
import com.puqian.util.RedisCacheClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wxliu on 15-4-17.
 * 微信系统工具类
 */
public class WxUtil {


    //access_token
    private static String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=@APPID@&secret=@APPSECRET@";
    private static Map<String,Map<String,String>> accessTokenInfoMap = new HashMap<String,Map<String,String>>();


    //js-sdk 使用
    private static String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=@ACCESS_TOKEN@&type=jsapi";
    private static Map<String,Map<String,String>> jsapiTicketInfoMap = new HashMap<String,Map<String,String>>();


    /**
     * 单子模式实例
     */
    private static WxUtil wxUtil;

    private WxUtil(){};

    /**
     * 单子模式实例方法
     *
     * @return
     */
    public static WxUtil instants(){
        if(wxUtil == null){
            wxUtil = new WxUtil();
        }
        return wxUtil;
    }


    /**
     * 获取微信 accesstoken
     * @param appId 公众号 appid
     * @param appSecret 公众号 appSecret
     * @return
     */
    public static String getAccessToken(String appId,String appSecret){
        RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        Map<String,String> accessTokenInfo = redis.get(appId);
        if((accessTokenInfo != null)
                && (null!=accessTokenInfo.get("accessToken"))
                && (null!=accessTokenInfo.get("time"))
                && (((System.currentTimeMillis()-Long.parseLong(accessTokenInfo.get("time")))/1000) < 60) ){
            return accessTokenInfo.get("accessToken");
        }
        if(accessTokenInfo == null){
            accessTokenInfo = new HashMap<String,String>();
        }

        String newUrl = ACCESS_TOKEN.replaceAll("@APPID@",appId);
        newUrl = newUrl.replaceAll("@APPSECRET@",appSecret);
        RestTemplate restTemplate = new RestTemplate();

        Map<String,String> parms = new HashMap<String,String>();
        parms.put("grant_type","client_credential");
        parms.put("appid",appId);
        parms.put("secret",appSecret);
        try {
            String ret = restTemplate.getForObject(newUrl,
                    String.class);
            if(null!=ret){
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> maps = objectMapper.readValue(ret, Map.class);
                if(maps.containsKey("access_token")){
                    accessTokenInfo.put("accessToken",String.valueOf(maps.get("access_token")));
                    accessTokenInfo.put("time",String.valueOf(System.currentTimeMillis()));
                    redis.set(appId,60*60*2);
                    return String.valueOf(maps.get("access_token"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于调用微信JS接口的临时票据
     * @param appId 公众号 appid
     * @param appSecret 公众号 appSecret
     * @return
     */
    public static String getJsapiTicket(String appId,String appSecret){
        RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        Map<String,String> jsapiTicketInfo = redis.get(appId);
        if((jsapiTicketInfo != null)
                && (null!=jsapiTicketInfo.get("ticket"))
                && (null!=jsapiTicketInfo.get("time"))
                && (((System.currentTimeMillis()-Long.parseLong(jsapiTicketInfo.get("time")))/1000) < 600 ) ){
            return jsapiTicketInfo.get("ticket");
        }
        if(jsapiTicketInfo == null){
            jsapiTicketInfo = new HashMap<String,String>();
        }

        String accessToken = getAccessToken(appId,appSecret);

        String newUrl = JSAPI_TICKET.replaceAll("@ACCESS_TOKEN@",accessToken);
        RestTemplate restTemplate = new RestTemplate();

        try {
            String ret = restTemplate.getForObject(newUrl,
                    String.class);
            if(null!=ret){
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> maps = objectMapper.readValue(ret, Map.class);
                if(null!=maps&&maps.containsKey("ticket")){
                    jsapiTicketInfo.put("ticket",String.valueOf(maps.get("ticket")));
                    jsapiTicketInfo.put("time",String.valueOf(System.currentTimeMillis()));
                    redis.set(appId,60*60*2);
                    return String.valueOf(maps.get("ticket"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送http post请求
     * @param url
     * @param appid
     * @param secret
     * @param requestBody
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @SuppressWarnings("all")
    public static synchronized String sendHttpPost(String url, String requestBody) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.setRequestBody(requestBody);
        post.getParams().setContentCharset("UTF-8");
        // 发送http请求
        client.executeMethod(post);
        StringBuilder result = new StringBuilder();
        InputStream in = null;
        try {
            in = post.getResponseBodyAsStream();
            byte[] buff = new byte[1024];
            int flag = -1;

            while ((flag = in.read(buff)) != -1) {
                result.append(new String(buff, 0, flag, "UTF-8"));
            }
            in.close();
        } catch (IOException e) {
            throw new HttpException("post请求异常");
        } finally {
            close(in);
        }
        return result.toString();
    }

    /**
     * 发送http get请求
     * @param url
     * @param appid
     * @param secret
     * @return
     * @throws HttpException
     * @throws IOException
     */
    @SuppressWarnings("all")
    public static synchronized String sendHttpGet(String url) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        client.executeMethod(get);
        StringBuilder result = new StringBuilder();
        InputStream in = null;
        try {
            in = get.getResponseBodyAsStream();
            byte[] buff = new byte[1024];
            int flag = -1;

            while ((flag = in.read(buff)) != -1) {
                result.append(new String(buff, 0, flag, "UTF-8"));
            }
            in.close();
        } catch (IOException e) {
            throw new HttpException("get请求异常");
        } finally {
            close(in);
        }
        return result.toString();
    }

    private static void close(InputStream in) {
        if (null != in) {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }

    public static String md5(String source) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(source.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /*
       *获取指定长度位的随机数
       */
    public static String codeNum(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }


    //处理微信昵称 特殊字符
    public static final String SPECIAL_CHAR = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";

    public static String StringFilter(String srcString) throws PatternSyntaxException {
        return Pattern.compile(SPECIAL_CHAR).matcher(srcString).replaceAll("").replaceAll("[url=]\\\\[/url]", "").trim();
    }


}
