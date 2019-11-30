package com.zlead.utils.wx;
import com.zlead.util.ProjectProperties;
import com.zlead.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信业务工具类
 */
public class WxCommonUtil {

   private static  String APPID = ProjectProperties.appId;
   private static  String APPSECRET = ProjectProperties.appSecret;
   public WxCommonUtil(){};


   public WxCommonUtil(String appId,String appsecret){
       APPID  =   appId;
       APPSECRET =  appsecret;
   }
    private static Logger log = LoggerFactory.getLogger(WxCommonUtil.class);

    /**
     * 发送文本消息
     * ADD BY 2015/4/16 14:58
     * */
    private static final String MESSAGE_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    /**
     * 页面授权
     */
    private static final String AUTH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&code=";

    /**
     * 发送模板消息
     */
    private static final String TEMPLATE_MSG_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 获取用户信息
     */
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&access_token=";

    /**
     * 网页授权--获取用户信息
     */
    private static final String PAGE_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&access_token=";





    /**
     * 获取微信用户openId
     * @param code 网页授权code
     * @return
     */
    public  String getOpenId(String code) {
        String openid = "";
        try {
            String url = AUTH_ACCESS_TOKEN + code +"&appid="+APPID+"&secret="+APPSECRET;
            String str = WxUtil.sendHttpGet(url);
            JSONObject obj = JSONObject.fromObject(str);
            openid = obj.get("openid")== null ? "" : (String)obj.get("openid");
        }catch (Exception e){
            e.printStackTrace();
        }
        return openid;
    }

    /**
     * 获取网页授权
     * @param code 网页授权code
     * @return
     */
    public  String getAccess_tokenByPage(String code) {
        String openid = "";
        try {
            String url = AUTH_ACCESS_TOKEN + code +"&appid="+APPID+"&secret="+APPSECRET;
            String str = WxUtil.sendHttpGet(url);
            JSONObject obj = JSONObject.fromObject(str);
            openid = obj.get("access_token")== null ? "" : (String)obj.get("access_token");
        }catch (Exception e){
            e.printStackTrace();
        }
        return openid;
    }


    /**
     * 获取用户信息
     * @param openId 微信用户openId
     * @return
     */
    public  WxUserInfoDto getWxUserInfo(String openId) {
        WxUserInfoDto wxUserInfoDto = null;
        try {
            String url = USER_INFO_URL+URLEncoder.encode(WxUtil.getAccessToken(APPID, APPSECRET), "UTF-8")+"&openid="+openId;
            System.out.println("微信获取用户信息路径----------："+url);
            //发送
            String str = WxUtil.sendHttpGet(url);
            JSONObject obj = JSONObject.fromObject(str);
            System.out.println("微信获取用户信息----------："+str);
            Object errcode = obj.get("errcode");
            if(errcode == null){
                wxUserInfoDto = (WxUserInfoDto)JSONObject.toBean(obj,WxUserInfoDto.class);
            }else{
                int code = obj.getInt("errcode");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return wxUserInfoDto;
    }

    /**
     * 网页授权--获取用户信息
     * @param code 网页码
     * @return
     */
    public  WxUserInfoDto getWxUserInfoByPage(String code) {
        WxUserInfoDto wxUserInfoDto = null;
        try {
            System.out.println("appid===="+APPID);
            System.out.println("secret===="+APPSECRET);
            String access_tokenUrl = AUTH_ACCESS_TOKEN + code +"&appid="+APPID+"&secret="+APPSECRET;

            String access_tokenstr = WxUtil.sendHttpGet(access_tokenUrl);
            JSONObject tokenObj = JSONObject.fromObject(access_tokenstr);
            System.out.println("网页授权-微信获取用户tokenObj:"+tokenObj);
            String openid = tokenObj.get("openid")== null ? "" : (String)tokenObj.get("openid");
            System.out.println("网页授权-微信获取用户openid:"+openid);
            String access_token = tokenObj.get("access_token")== null ? "" : (String)tokenObj.get("access_token");
            System.out.println("网页授权-微信获取用户access_token:"+access_token);
            String url = PAGE_USER_INFO_URL+access_token+"&openid="+openid;
            //发送
            String str = WxUtil.sendHttpGet(url);
            JSONObject obj = JSONObject.fromObject(str);
            System.out.println("网页授权-微信获取用户信息----------："+str);
            Object errcode = obj.get("errcode");
            if(errcode == null){
                wxUserInfoDto = (WxUserInfoDto)JSONObject.toBean(obj,WxUserInfoDto.class);
            }else{
                int returnCode = obj.getInt("errcode");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return wxUserInfoDto;
    }

    /**
     * 网页授权--获取用户openId
     * @param code 网页码
     * @return
     */
    public String getOpenIdByPage(String code) {
        String openId = null;
        try {
            System.out.println("appid===="+APPID);
            System.out.println("secret===="+APPSECRET);
            String access_tokenUrl = AUTH_ACCESS_TOKEN + code +"&appid="+APPID+"&secret="+APPSECRET;
            String access_tokenstr = WxUtil.sendHttpGet(access_tokenUrl);
            JSONObject tokenObj = JSONObject.fromObject(access_tokenstr);
            System.out.println("网页授权-微信获取用户tokenObj:"+tokenObj);
            openId = tokenObj.get("openid")== null ? "" : (String)tokenObj.get("openid");
            System.out.println("网页授权-微信获取用户openid:"+openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return openId;
    }


    /**
     * 发送模板消息
     * @param openId 接收用户openId
     * @param templateId 模板消息id
     * @param url 点击消息跳转页面地址（外网地址）
     * @param msgMap 根据模板消息格式构造，key参数名，value值；
     * @return 0发送成功，10000系统异常，其他为微信错误代码
     */
    public  int sendTemplateMsg(String openId,String templateId,String url,Map<String,String> msgMap) {
        int code = 0;
        try {
            String sendUrl = TEMPLATE_MSG_SEND + URLEncoder.encode(WxUtil.getAccessToken(APPID, APPSECRET), "UTF-8");
            Map<String, Object> map = new HashMap<String, Object>(); //发送格式
            map.put("touser", openId);
            map.put("template_id", templateId);
            map.put("url",url);
            map.put("topcolor","#FF0000");
            Map<String, Object> data = new HashMap<String, Object>();

            for(Map.Entry<String, String> entry:msgMap.entrySet()){
                Map<String, Object> keynote = new HashMap<String, Object>();
                keynote.put("value",entry.getValue());
                keynote.put("color","#173177");
                data.put(entry.getKey(),keynote);
            }
            map.put("data",data);
            String jsonStr = WxUtil.instants().sendHttpPost(sendUrl, JsonUtil.object2Json(map));
            JSONObject obj = JSONObject.fromObject(jsonStr);
            code = obj.getInt("errcode");
        }catch (Exception e){
            e.printStackTrace();
            code = 10000;
        }
        return code;
    }

    /**
     * 发送微信消息； 注：48小时内与公众号有互动的能收到；
     * @param content 消息内容
     * @param openId 用户opendId
     */
    public  void sendWxMsgToUser(String content,String openId) {
        try {
            String url = MESSAGE_SEND + URLEncoder.encode(WxUtil.getAccessToken(APPID, APPSECRET), "UTF-8");
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> text = new HashMap<String, Object>();
            content = dealTextContent(content);
            text.put("content", content);
            map.put("text", text);
            map.put("msgtype", "text");
            map.put("touser",openId);
            String jsonStr = WxUtil.instants().sendHttpPost(url, JsonUtil.object2Json(map));
            JSONObject obj = JSONObject.fromObject(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** 处理文本表情 **/
    private  String dealTextContent(String content) {
        StringBuffer sb = new StringBuffer();
        if (content != null && content.indexOf("<img") != -1) {
            String[] s = content.split("<img");
            for (String str : s) {
                if (str.indexOf("mo-") != -1) {
                    str = str.substring(str.indexOf("mo-")).replace("mo-", "/")
                            .replace("\">", "");
                }
                sb.append(str);
            }
        } else {
            return content;
        }
        return sb.toString();
    }

}
