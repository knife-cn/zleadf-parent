package com.zlead.utils;

import com.alibaba.fastjson.JSON;
import com.puqian.util.RedisCacheClient;
import com.zlead.util.ProjectProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zhb
 */
@Component
public class HttpUtil {
    public static Log logger = LogFactory.getLog(HttpUtil.class);
    
    @Autowired
	private RedisCacheClient redisCacheClient;
    
    //get请求
    public static com.alibaba.fastjson.JSONObject doGet(String requestUrl) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
        com.alibaba.fastjson.JSONObject result = null;
        try {
            //创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            //执行Get请求，
            response = httpClient.execute(httpGet);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            responseContent  = EntityUtils.toString(entity,"UTF-8");
            //转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
        return result;
    }

    public static String doPostXml(String requestUrl,String xmlData) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent  = null;
//        com.alibaba.fastjson.JSONObject result = null;
        String result = null;
        try {
            //创建post请求，
            HttpPost httpPost = new HttpPost(requestUrl);
            //添加post数据
            httpPost.setEntity(new StringEntity(xmlData,"utf-8"));
            //添加响应头
            httpPost.addHeader("Content-Type","text/xml");
            //执行post请求，
            response = httpClient.execute(httpPost);
            //得到响应体
            HttpEntity entity = response.getEntity();
            //获取响应内容
            result  = EntityUtils.toString(entity,"UTF-8");
//            //转换为map
//            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
            logger.error("HttpUtil=====Start");
            logger.error(e.getMessage(),e);
            logger.error("HttpUtil=====End");
        }
        return result;
    }
 
    public static void sendSms(String Mobile,String Type,String Msg){
    	HttpClient httpClient = new DefaultHttpClient(); 
    	String url="http://prenewapi.wujinyunshang.com/api/FactoryCommon/SendSms";
        HttpPost httpPost = new HttpPost(url);
    //  httpPost.setHeader("Accept-Encoding", "gzip,deflate");//表示返回的数据是压缩的zip格式
//        String postParam = "";//请求的参数内容          
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("Mobile", Mobile));
        nvps.add(new BasicNameValuePair("Type", Type));
        nvps.add(new BasicNameValuePair("Msg", Msg));  
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			HttpResponse response = httpClient.execute(httpPost);    
		    HttpEntity entity = response.getEntity();
		    if (response!=null && response.getStatusLine().getStatusCode() == 200) {
		    	if (entity!=null && entity.getContentEncoding()!=null && entity.getContentEncoding().toString().equalsIgnoreCase("Content-Encoding: gzip")) 
		    	{
		                    response.setEntity(new GzipDecompressingEntity(response.getEntity())); //对zip进行解压
		                    entity = response.getEntity();
		        } 
		               String responseContent = EntityUtils.toString(entity);
		               System.out.println("responseContent: \n" + responseContent); 
		    }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
    }

    public void Post(String url,List<NameValuePair> nvps){
    	HttpClient httpClient = new DefaultHttpClient(); 
    	//String url="http://prenewapi.wujinyunshang.com/api/FactoryCommon/SendSms";
        //  httpPost.setHeader("Accept-Encoding", "gzip,deflate");//表示返回的数据是压缩的zip格式
//        String postParam = "";//请求的参数内容          
        if(nvps==null)
        	nvps = new ArrayList<NameValuePair>();  
        if(url==null)
        	url="http://172.25.17.12:8080/zlead/member/memberlogin";
        HttpPost httpPost = new HttpPost(url);
        nvps.add(new BasicNameValuePair("telNumber", "13211111111"));
        nvps.add(new BasicNameValuePair("password", "11111111"));  
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			HttpResponse response = httpClient.execute(httpPost);    
		    HttpEntity entity = response.getEntity();
		    if (response!=null && response.getStatusLine().getStatusCode() == 200) {
		    	if (entity!=null && entity.getContentEncoding()!=null && entity.getContentEncoding().toString().equalsIgnoreCase("Content-Encoding: gzip")) 
		    	{
		                    response.setEntity(new GzipDecompressingEntity(response.getEntity())); //对zip进行解压
		                    entity = response.getEntity();
		        } 
		               String responseContent = EntityUtils.toString(entity);
		               System.out.println("responseContent: \n" + responseContent); 
		    }
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
       
    }
    public void setWebUrl() {
        //RedisCacheClient redis = (RedisCacheClient) ApplicationContextHolder.getBean("redisCacheClient");
        String key = "weburl" ;
        String weburl="http://f.zlead.com/shopping/";
//        if( ProjectProperties.urlPrefix==null){
//        	ProjectProperties.loadProperty();
        	weburl= ProjectProperties.urlPrefix;
//        }
        redisCacheClient.set(key, weburl); // 1天后过期
    }
    
    public String getWebUrl(){
    	 String key = "weburl" ;
    	 String weburl=redisCacheClient.get(key); // 1天后过期
    	 if(weburl==null){
//    		 if( ProjectProperties.urlPrefix==null){
//    	        	ProjectProperties.loadProperty();
    	        	weburl=ProjectProperties.urlPrefix;
//    	        }
    	 }
    	 return weburl;
    }
}