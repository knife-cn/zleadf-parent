package com.zlead.controller;

import com.alibaba.fastjson.JSONObject;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.Region;
import com.zlead.reception.service.MemberService;
import com.zlead.reception.service.OrderService;
import com.zlead.service.RegionService;
import com.zlead.utils.*;


import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhb
 */

@RequestMapping("/cert")
@Controller
public class WxController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    //获取相关的参数,在application.properties文件中



    private String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private String apiTicketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi&offset_type=1";

    //微信参数
    private String accessToken;
    private String jsApiTicket;
    //获取参数的时刻
    private Long getTiketTime = 0L;
    private Long getTokenTime = 0L;
    //参数的有效时间,单位是秒(s)
    private Long tokenExpireTime = 0L;
    private Long ticketExpireTime = 0L;

    @Resource
    private MemberService memberService;
//    @Resource
//    private ShopService shopService;
    @Resource
    private RegionService regionService;

    @Resource
    private OrderService orderService;

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private HttpUtil httpUtil;

    private String weburl;

    @ModelAttribute
    public void getWebUrl() {
        weburl = httpUtil.getWebUrl();
    }

    //获取微信参数
    @RequestMapping("/wechatParam")
    @ResponseBody
    public Map<String, String> getWechatParam(String url){
        //当前时间
        long now = System.currentTimeMillis();
        log.info("currentTime====>"+now+"ms");

        //判断accessToken是否已经存在或者token是否过期
        if(StringUtils.isBlank(accessToken)||(now - getTokenTime > tokenExpireTime*1000)){
            JSONObject tokenInfo = getAccessToken();
            if(tokenInfo != null){
                log.info("tokenInfo====>"+tokenInfo.toJSONString());
                accessToken = tokenInfo.getString("access_token");
                tokenExpireTime = tokenInfo.getLongValue("expires_in");
                //获取token的时间
                getTokenTime = System.currentTimeMillis();
                log.info("accessToken====>"+accessToken);
                log.info("tokenExpireTime====>"+tokenExpireTime+"s");
                log.info("getTokenTime====>"+getTokenTime+"ms");
            }else{
                log.info("====>tokenInfo is null~");
                log.info("====>failure of getting tokenInfo,please do some check~");
            }

        }

        //判断jsApiTicket是否已经存在或者是否过期
        if(StringUtils.isBlank(jsApiTicket)||(now - getTiketTime > ticketExpireTime*1000)){
            JSONObject ticketInfo = getJsApiTicket();
            if(ticketInfo!=null){
                log.info("ticketInfo====>"+ticketInfo.toJSONString());
                jsApiTicket = ticketInfo.getString("ticket");
                ticketExpireTime = ticketInfo.getLongValue("expires_in");
                getTiketTime = System.currentTimeMillis();
                log.info("jsApiTicket====>"+jsApiTicket);
                log.info("ticketExpireTime====>"+ticketExpireTime+"s");
                log.info("getTiketTime====>"+getTiketTime+"ms");
            }else{
                log.info("====>ticketInfo is null~");
                log.info("====>failure of getting tokenInfo,please do some check~");
            }
        }

        //生成微信权限验证的参数
        Map<String, String> wechatParam= makeWXTicket(jsApiTicket,url);
        return wechatParam;
    }

    //获取accessToken
    private JSONObject getAccessToken(){
        //String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = accessTokenUrl.replace("APPID",AuthUtil.WXAPPID).replace("APPSECRET",AuthUtil.WXAPPID);
        log.info("getAccessToken.requestUrl====>"+requestUrl);
        JSONObject result = HttpUtil.doGet(requestUrl);
        return result ;
    }

    //获取ticket
    private JSONObject getJsApiTicket(){
        //String apiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi&offset_type=1";
        String requestUrl = apiTicketUrl.replace("ACCESS_TOKEN", accessToken);
        log.info("getJsApiTicket.requestUrl====>"+requestUrl);
        JSONObject result = HttpUtil.doGet(requestUrl);
        return result;
    }

    //生成微信权限验证的参数
    public Map<String, String> makeWXTicket(String jsApiTicket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonceStr = createNonceStr();
        String timestamp = createTimestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        log.info("String1=====>"+string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
            log.info("signature=====>"+signature);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error("WeChatController.makeWXTicket=====Start");
            log.error(e.getMessage(),e);
            log.error("WeChatController.makeWXTicket=====End");
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("WeChatController.makeWXTicket=====Start");
            log.error(e.getMessage(),e);
            log.error("WeChatController.makeWXTicket=====End");
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsApiTicket);
        ret.put("nonceStr", nonceStr);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appid", AuthUtil.WXAPPID);

        return ret;
    }
    //字节数组转换为十六进制字符串
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @RequestMapping("/wxlogin")
    @ResponseBody
    public Map<String, String> wechatLogin(@RequestParam(value="projectPath") @Validated String projectPath,
                                           @RequestParam(value="shopId") Long shopId){
        Map<String, String> result =new HashMap<String, String>();
        String wxBackUrl = weburl+"/cert/wxcallback";
        String qqBackUrl = weburl+"/cert/qqcallback";
        String wbBackUrl = weburl+"/cert/wbcallback";
        String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtil.WXAPPID
                + "&redirect_uri="+URLEncoder.encode(wxBackUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state="+shopId;
        String qqUrl = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="
                +AuthUtil.QQAPPID+"&redirect_uri="+qqBackUrl+"&state="+shopId;
        String wbUrl = "https://api.weibo.com/oauth2/authorize?client_id="+AuthUtil.WBAPPID+"&redirect_uri="+wbBackUrl+"&state="+shopId;
        String testUrl = weburl+"/cert/qqcallback?code=111&state=10";
        result.put("wxUrl",wxUrl);
        result.put("qqUrl",testUrl);
        result.put("wbUrl",wbUrl);
        return result;
    }

    @RequestMapping("/wxcallback")
    public String wechatcallback(@RequestParam(value="code") @Validated String code,@RequestParam(value="state") String shopId,HttpServletRequest request){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtil.WXAPPID
                + "&secret="+AuthUtil.WXAPPSECRET
                + "&code="+code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = HttpUtil.doGet(url);
        String openid = jsonObject.getString("openid");
        System.out.println(openid);
        String token = jsonObject.getString("access_token");
        System.out.println(token);
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+token
                + "&openid="+openid
                + "&lang=zh_CN";
        JSONObject userInfo = HttpUtil.doGet(infoUrl);
//        String unionid=userInfo.getString("unionid");
//        System.out.println(unionid);
//        MemberEntity member=memberService.findByUnionId(unionid);
        MemberEntity member=memberService.findByOpenId(openid);
        if(member==null){
            //新增会员信息
            System.out.println("微信新增会员");
            String nickname=PinyinUtil.getPingYinAll( userInfo.getString("nickname"));
            System.out.println(nickname);
            String headimgurl =userInfo.getString("headimgurl");
            System.out.println(headimgurl);
            String wxProvince = userInfo.getString("province")+"省";
            System.out.println(wxProvince);
            String wxCity = userInfo.getString("city")+"市";
            System.out.println(wxCity);
            String provinceId = "";
            String cityId = "";
            Region province = regionService.getRegion(1, wxProvince);
            if(province!=null){
                    provinceId = province.getRegionCode();
                    Region city = regionService.getRegion(province.getId(), wxCity);
                    System.out.println(provinceId);
                    if(city!=null){
                        cityId = city.getRegionCode();
                        System.out.println(cityId);
                    }
                }
            String headImg ="";
            try {
                ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
                headImg = FastDfsUtil.download2UploadImg(headimgurl);
                System.out.println(headImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String unionid=userInfo.getString("unionid");
            boolean result = memberService.saveMemberByNickName(nickname, nickname, openid, unionid, headImg, provinceId, cityId);
            System.out.println(result);
//            MemberEntity wxMember=memberService.findByUnionId(unionid);
            MemberEntity wxMember=memberService.findByOpenId(openid);
            loginUtil.setLoginMember(request, wxMember);
            Long wxMemberId = wxMember.getId();
            System.out.println(wxMemberId);
            return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+wxMemberId+"&type=1";
        }
        System.out.println("微信老会员");
        Long memberId = member.getId();
        loginUtil.setLoginMember(request, member);
        System.out.println(memberId);
            return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+memberId+"&type=0";
    }

    @RequestMapping("/qqcallback")
    public String qqcallback(@RequestParam(value="code") @Validated String code,@RequestParam(value="state") String shopId,HttpServletRequest request) {

        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="+AuthUtil.QQAPPID+"&client_secret="+AuthUtil.QQAPPIKEY +
                "&code="+code+"&redirect_uri="+weburl+"/cert/qqcallback";
        JSONObject jsonObject1 = HttpUtil.doGet(url);
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put("access_token","access_token");
        String access_token = jsonObject1.getString("access_token");
        System.out.println(access_token);
        String url1 = "https://graph.qq.com/oauth2.0/me/?access_token="+access_token;
        JSONObject jsonObject = HttpUtil.doGet(url1);
//        JSONObject jsonObject = new JSONObject();
//        UUID uuid = UUID.randomUUID();
//        String str = uuid.toString();
//        String temp = str.substring(0, 8);
//        jsonObject.put("openid",temp);
        String openid = jsonObject.getString("openid");
        System.out.println(openid);
        String infoUrl = "https://graph.qq.com/user/get_user_info?access_token="+access_token+"&oauth_consumer_key="+AuthUtil.QQAPPID+"&openid="+openid;
        JSONObject userInfo = HttpUtil.doGet(infoUrl);
//        JSONObject userInfo = new JSONObject();
//        userInfo.put("nickname",temp);
//        userInfo.put("figureurl","https://ss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/wisegame/wh%3D68%2C68/sign=c09cf70cdb88d43ff0fc99f44532e423/810a19d8bc3eb1350f65e7c2ab1ea8d3fd1f442c.jpg");
//        userInfo.put("province","辽宁省");
//        userInfo.put("city","沈阳市");
        MemberEntity member=memberService.findByOpenId(openid);
        if(member==null){
            //新增会员信息
              String nickname=PinyinUtil.getPingYinAll(userInfo.getString("nickname"));
            String figureurl =userInfo.getString("figureurl");
            String qqProvince = userInfo.getString("province")+"省";
            String qqCity = userInfo.getString("city")+"市";
            String provinceId = "";
            String cityId = "";
            Region province = regionService.getRegion(1, qqProvince);
            if(province!=null){
                provinceId = province.getRegionCode();
                Region city = regionService.getRegion(province.getId(), qqCity);
                if(city!=null){
                    cityId = city.getRegionCode();
                }
            }
            String headImg ="";
            try {
                ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
                headImg = FastDfsUtil.download2UploadImg(figureurl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean b = memberService.saveMemberByNickName(nickname,nickname,openid,"",headImg,provinceId,cityId);
            MemberEntity qqMember=memberService.findByOpenId(openid);
            loginUtil.setLoginMember(request, qqMember);
            Long qqMemberId = qqMember.getId();
            return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+qqMemberId+"&type=1";
        }
        loginUtil.setLoginMember(request, member);
        Long memberId = member.getId();
        return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+memberId+"&type=0";

    }

    @RequestMapping("/wbcallback")
    public String wbcallback(@RequestParam(value="code") @Validated String code,@RequestParam(value="state") String shopId,HttpServletRequest request) {
        String url = "https://api.weibo.com/oauth2/access_token?grant_type=authorization_code&client_id="+AuthUtil.WBAPPID+"&client_secret="+AuthUtil.WBAPPIKEY +
                "&code="+code+"&redirect_uri="+weburl+"/cert/wbcallback";
        JSONObject jsonObject1 = HttpUtil.doGet(url);
        String access_token = jsonObject1.getString("access_token");
        String url1 = "https://api.weibo.com/oauth2/get_token_info?access_token="+access_token;
        JSONObject jsonObject = HttpUtil.doGet(url1);
        String uid = jsonObject.getString("uid");
        String infoUrl = "https://api.weibo.com/2/users/show.json?access_token="+access_token+"&uid="+uid;
        JSONObject userInfo = HttpUtil.doGet(infoUrl);
        MemberEntity member=memberService.findByOpenId(uid);
        if(member==null){
            //新增会员信息
            String nickname=PinyinUtil.getPingYinAll(userInfo.getString("name"));
            String profile_img_url =userInfo.getString("profile_img_url");
            String wbProvince = userInfo.getString("province");
            String wbCity = userInfo.getString("city");
            String provinceId = "";
            String cityId = "";
            Region province = regionService.getRegion(1, wbProvince);
            if(province!=null){
                provinceId = province.getRegionCode();
                Region city = regionService.getRegion(province.getId(), wbCity);
                if(city!=null){
                    cityId = city.getRegionCode();
                }
            }
            String headImg ="";
            try {
                ClientGlobal.init(this.getClass().getResource("/fdfs_client.properties").toURI().toURL().getPath());
                headImg = FastDfsUtil.download2UploadImg(profile_img_url);
            }catch(Exception e){
                e.printStackTrace();
            }
            boolean b = memberService.saveMemberByNickName(nickname,nickname,uid,"",headImg,provinceId,cityId);
            MemberEntity wbMember=memberService.findByOpenId(uid);
            loginUtil.setLoginMember(request, wbMember);
            Long wbMemberId = wbMember.getId();
            return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+wbMemberId+"&type=1";
        }
        Long memberId = member.getId();
        loginUtil.setLoginMember(request, member);
        return "redirect:"+weburl+"/company/ssoIndex.action?shopId="+shopId+"&memberId="+memberId+"&type=0";
    }

    @RequestMapping("/wxbinding")
    public String wxbinding(@RequestParam(value="code") @Validated String code,@RequestParam(value="state") String orderNo,HttpServletRequest request){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtil.WXAPPID
                + "&secret="+AuthUtil.WXAPPSECRET
                + "&code="+code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = HttpUtil.doGet(url);
        String openid = jsonObject.getString("openid");
        System.out.println(openid);
        OrderEntity orderEntity = orderService.findBySn(orderNo);
        Long memberId = orderEntity.getMemberId();
        Long shopId = orderEntity.getShopId();
        MemberEntity memberEntity = memberService.findById(memberId);
        memberEntity.setOpenId(openid);
        memberEntity.setWxOpenId(openid);
        return "redirect:"+weburl+"/member/agentOrderPay?orderNo="+orderNo;
    }

    //生成随机字符串
    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }
    //生成时间戳
    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}


