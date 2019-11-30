package com.zlead.member.controller;


import com.alibaba.fastjson.JSONObject;
import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.transaction.AgentTrans;
import com.zlead.reception.service.MemberService;
import com.zlead.service.RegionService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.ProjectProperties;
import com.zlead.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录注册接口
 */
@Controller
@RequestMapping("/zlead/member")
public class MemberMgrController {
    @Resource
    private MemberService memberService;

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private HttpUtil httpUtil;

    @Resource
    private RegionService regionService;

    private String weburl;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private AgentTrans agentTrans;

    @ModelAttribute
    public void getWebUrl() {
        weburl = httpUtil.getWebUrl();
    }


    //用户注册接口
    @RequestMapping("registered2")
    @ResponseBody
    public JsonResult userRegist2(HttpServletRequest request, MemberEntity memberEntity) {
        JsonResult jsonResult = null;
//        String result=null;
        String phone = request.getParameter("phone");
        String passWord = request.getParameter("passWord");
        String companyName = request.getParameter("companyName");
        String verifyCode = request.getParameter("verifyCode");
//        String provinceName= request.getParameter("provinceName");
//        String cityName= request.getParameter("cityName");
//        String countyName = request.getParameter("countyName");
//        result = JsonUtil.getJson(jsonResult);
        //  JsonUtil.render(response, result);
        //校验短信验证码
        /*String preVerifyCode = request.getSession().getAttribute("verifyCode").toString();*/
        String preVerifyCode = loginUtil.getMsg(phone);
        if (!verifyCode.equals(preVerifyCode)) {
            jsonResult = new JsonResult(2, "验证码校验失败", "", false);
            return jsonResult;
        }
        //查询电话有没有被占用
        MemberEntity member = memberService.findByPhone(phone);
        if (member != null) {
            jsonResult = new JsonResult(2, "该手机号已被占用", "", false);
            return jsonResult;
        }
        //boolean b = memberService.saveMember(phone,passWord);
        OaAgentMas agentMas = new OaAgentMas();
        agentMas.setAgentState("1");
        agentMas.setLinkTel(phone);
        agentMas.setAgentName(companyName);
        int b = agentTrans.insertAgentWithPass(agentMas, passWord);
        if (b > 0) {
            jsonResult = new JsonResult(1, "注册成功", "", true);
            //调用b端注册供货商接口
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("Mobile", phone));
            nvps.add(new BasicNameValuePair("Password", passWord));
            nvps.add(new BasicNameValuePair("CompanyName", companyName));
//            httpUtil.Post(ProjectProperties.bServerUrl + "api/FactoryDetail/RegisterAgentByF", nvps);
        } else {
            jsonResult = new JsonResult(2, "注册失败", "", false);
        }
        return jsonResult;
    }

    /**
     * 对接B端的注册
     */
    @RequestMapping("registered")
    @ResponseBody
    public JsonResult userRegist(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        try {
            String phone = request.getParameter("phone");
            String passWord = request.getParameter("passWord");
            String companyName = request.getParameter("companyName");
            String verifyCode = request.getParameter("verifyCode");
            //校验短信验证码
            String preVerifyCode = loginUtil.getMsg(phone);
            if (StringUtils.isBlank(preVerifyCode) || !verifyCode.equals(preVerifyCode)) {
                jsonResult = new JsonResult(2, "验证码校验失败", "", false);
                return jsonResult;
            }
            Map<String, String> params = new HashMap<>();
            params.put("Mobile", phone);
            params.put("Password", passWord);
            params.put("CompanyName", companyName);
            JSONObject jsonObject = BClientHttp.post(ProjectProperties.bServerUrl + "api/FactoryDetail/RegisterAgentByF", params);
            if (jsonObject != null) {
                if (jsonObject.get("StatusCode") != null && jsonObject.getIntValue("StatusCode") == 200) {
                    //查询F端是否已经注册
                    MemberEntity member = memberService.findByPhone(phone);
                    if (member != null) {
                        jsonResult = new JsonResult(1, "注册成功", "", true);
                        return jsonResult;
                    }
                    OaAgentMas agentMas = new OaAgentMas();
                    agentMas.setAgentState("1");
                    agentMas.setLinkTel(phone);
                    agentMas.setAgentName(companyName);
                    int b = agentTrans.insertAgentWithPass(agentMas, passWord);
                    if (b > 0) {
                        jsonResult = new JsonResult(1, "注册成功", "", true);
                    } else {
                        jsonResult = new JsonResult(2, "注册失败", "", false);
                    }
                } else {
                    jsonResult = new JsonResult(2, jsonObject.getString("Info"), "", false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    
    /**
     * 用户登录
     */
    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(@RequestParam(value = "account") @Validated @NotNull String account,
                            @RequestParam(value = "password") @Validated @NotNull String password,
                            HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            System.out.println("memberService.login 1: " + account);
            Map map = memberService.login(account, password, request);
            if (map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
//                String returnurl= map.get("returnurl").toString();
                if ("true".equals(result) && map.get("data") != null) {
//                	data.put("returnurl", returnurl);
//                	System.out.println("returnurl 3:"+returnurl);
                    jsonResult = new JsonResult(1, message, map.get("data"), true);
                } else if ("false".equals(result)) {
                    jsonResult = new JsonResult(2, message, false);
                }
            } else {
                jsonResult = new JsonResult(2, "没有该用户", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 用户登录
     */
    @RequestMapping("login2")
    @ResponseBody
    public JsonResult login2(@RequestParam(value="account") @Validated @NotNull String account,
                             @RequestParam(value="password") @Validated @NotNull String password,
                             HttpServletRequest request){
        JsonResult jsonResult = null;
        try{
            Map map = memberService.loginGn(account, password, request);
            if (map != null && map.get("success").toString().equals("true")) {
                String result = map.get("success").toString();
                if ("false".equals(result)) {
                }else if ("true".equals(result) && map.get("data")!=null){
                    Map maptx = (Map)map.get("data");
                    if (maptx.get("memberType").toString().equals("6")) {
                        //                        Integer sc =(Integer)maptx.get("memberType");
//                        System.out.println(sc);
//                        Map mames =(Map)map.get("data");
//                        MemberEntity mem=(MemberEntity)mames.get("zxMember");
//                        loginUtil.setLoginMember(request, mem);
                        jsonResult =  new JsonResult(6,"工厂端账号 权限不足","",false);
                    }else{
                        Map<String, String> maps = new HashMap<String, String>();
                        maps.put("Mobile",account);
                        maps.put("Password",password);
                        JSONObject com =BClientHttp.post(ProjectProperties.bServerUrl+"api/FactoryDetail/AccountLoginByF",maps);
                        System.out.println(com);
                        Map mapb = (Map)com.get("Data");
                        if (com != null) {
                            if (mapb!=null && com.get("StatusCode")!=null && com.getIntValue("StatusCode")==200){
                                Map mapip = memberService.loginGn(account, password, request);
                                Map mamem =(Map)mapip.get("data");
                                MemberEntity mem=(MemberEntity)mamem.get("zxMember");
                                loginUtil.setLoginMember(request, mem);
                                jsonResult =  new JsonResult(1,"验证成功 登录成功",mapip,true);
                            }else {
                                jsonResult =  new JsonResult(2,"验证失败1",com,false);
                            }
                        }else {
                            jsonResult =  new JsonResult(2,"验证失败2",com,false);
                        }

                    }

                }

            }else {
                jsonResult =  new JsonResult(2,"账号不存在",map,false);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 用户登录
     */
    @RequestMapping("memberlogin")
    //@ResponseBody
    public String memberlogin(@RequestParam(value = "telNumber") @Validated @NotNull String account,
                              @RequestParam(value = "password") @Validated @NotNull String password,
                              Model model, HttpServletRequest request) {
        JsonResult jsonResult = null;
        String returnurl = request.getParameter("returnurl");
        Object obShopId = request.getSession().getAttribute("shopId");
        String shopId = null;
        try {
            Map map = memberService.login(account, password, request);
            if (map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                //returnurl= map.get("returnurl").toString();
                if ("true".equals(result) && map.get("data") != null) {
                	 /* HashMap data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    data.put("returnurl", returnurl);
                    System.out.println("returnurl 3:"+returnurl);
                    jsonResult = new JsonResult(1, message, data, true);

                    if(obShopId==null){
                        ShopEntity shop = shopDao.findByMemeberId(data.get("memberId").toString());
                        if(shop!=null && shop.getId()!=null)
                            shopId=shop.getId().toString();
                        System.out.println("returnurl shopid 3:"+shopId);
                    }
                    if(shopId==null) shopId="10";*/
                    if (returnurl == null || returnurl.trim() == "") returnurl = "/member/agentAccount.jsp";
                    else {
                        model.addAttribute("msg", message);
                    }
                    System.out.println("returnurl shopid 31:" + weburl + returnurl);

                    System.out.println("returnurl shopid 32 return :/member/agentAccount");
                    return "redirect:" + weburl + returnurl;
                    //return "/member/agentAccount";
                } else if ("false".equals(result)) {
                    jsonResult = new JsonResult(2, message, false);
                    model.addAttribute("msg", message);
                    return "redirect:" + weburl + "/member/memberlogin.action";
                }
            } else {
                jsonResult = new JsonResult(2, "没有该用户", false);
                return "redirect:" + weburl + "/member/memberlogin.action";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/member/login";
    }

    /**
     * 会员/代理商个人详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("memberInfo")
    @ResponseBody
    public JsonResult memberInfo(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String result = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);

//        MemberEntity member =new MemberEntity();
//        long i=1;
//        member.setId(i);
        if (member != null) {
            Map<String, Object> map = memberService.memebrInfo(member);
            System.out.println(map);
            jsonResult = new JsonResult(1, "获取个人信息成功", map, true);
        } else {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
        }
//        result = JsonUtil.getJson(jsonResult);
//        JsonUtil.render(response, result);
        return jsonResult;
    }

//    /**
//     * 用户(注册/修改密码)发送验证码
//     * */
//    @RequestMapping("sendMsg")
//    @ResponseBody
//    public void sendMsg(HttpServletRequest request, HttpServletResponse response){
//
//        JsonResult jsonResult = null;
//        String result=null;
//        String code = (((int) (Math.random() * 899999)) + 100000) + "";
//        String phone=request.getParameter("phone");
//        if(phone!=null){
//            Map<String, String> paramMap = new LinkedHashMap<String, String>();
//            paramMap.put("验证码",code);
//            boolean sendSuccess = SmsUtilForChuanglan.sendMessage(phone, "SMS_27495257", paramMap);
//            if(sendSuccess){
//                jsonResult =  new JsonResult(1,"验证码发送成功",code,true);
//            }else{
//                jsonResult =  new JsonResult(2,"发送失败","",false);
//            }
//        }else{
//            jsonResult =  new JsonResult(2,"手机号不能为空","",false);
//        }
//
//
//        result = JsonUtil.getJson(jsonResult);
//        JsonUtil.render(response, result);
//    }

    //忘记密码接口
    @RequestMapping("forgetPass")
    @ResponseBody
    public String forgetPass(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String result = null;
        String phone = request.getParameter("phone");
        String newPass = request.getParameter("newPass");
        MemberEntity member = memberService.findByPhone(phone);
        if (member != null) {
            member.setPassword(MD5Util.toMD5(newPass));
            memberService.updateById(member);
            if (MD5Util.toMD5(newPass).equals(member.getPassword())) {
                jsonResult = new JsonResult(1, "密码修改成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "密码修改失败", "", false);
            }
        } else {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
        }

        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
    }

    /**
     * 已知密码的情况下 修改密码接口
     *
     * @param request
     * @param response
     * @param em
     * @return
     */
    @RequestMapping("updatepaw")
    @ResponseBody
    public String updatepaw(HttpServletRequest request, HttpServletResponse response, MemberEntity em) {
        JsonResult jsonResult = null;
        String result = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        //新密码
//        em.setPassword("123456");
        //原密码
//        em.setNpassword("111111");
        MemberEntity sc = memberService.findById(member.getId());
        //原密码
        String paw = em.getNpassword();
        //新密码
        String pawred = em.getPassword();
        //原密码 转md5
        String pawt = MD5Util.toMD5(paw);
        if (member != null) {
            //判断原密码与数据库是否相同 md5的情况
            if (sc.getPassword().equals(pawt)) {
                em.setPassword(MD5Util.toMD5(pawred));
                em.setId(member.getId());
                em.setMemberId(member.getMemberId());
                em.setUsername(member.getUsername());
                em.setNickName(member.getNickName());
                em.setIfPhone(member.getIfPhone());
                em.setAccount(member.getAccount());
                em.setIsDisable(member.getIsDisable());
                em.setIsDelete(member.getIsDelete());
                em.setIsStaff(member.getIsStaff());
                em.setIsVip(member.getIsVip());
                em.setCreateTime(member.getCreateTime());
                em.setIsSalesmen(member.getIsSalesmen());
                em.setAgentId(member.getAgentId());
                int a = memberService.updatepwd(em);
                System.out.println(a);
                jsonResult = new JsonResult(1, "密码修改成功", "", true);
                //同时登出前台跳转登录页面
                memberService.logout(member.getPhone(), request);
            } else {
                jsonResult = new JsonResult(2, "密码修改失败", "", false);
            }
        } else {
            jsonResult = new JsonResult(2, "找不到用户", "", false);
        }

        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
    }

    /**
     * 修改用户
     *
     * @return
     */
    @RequestMapping("updateVipe")
    @ResponseBody
    public String updatevipe(MemberEntity em, HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = null;
        String result = null;
        //获取已登录用户信息
//        em.setNickName("测试");
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null) {
            member.setNickName(em.getNickName());
            member.setGender(em.getGender());
            member.setProvinceId(em.getProvinceId());
            member.setCityId(em.getCityId());
            member.setRegionId(em.getRegionId());
            member.setAddress(em.getAddress());
            member.setBirthday(em.getBirthday());
            int in = memberService.updatevipe(member);
            if (in != 0) {
                jsonResult = new JsonResult(1, "用户修改成功", in, true);
            } else {
                jsonResult = new JsonResult(1, "用户修改失败", in, false);
            }
        } else {
            jsonResult = new JsonResult(1, "用户不存在", false);
        }
        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);

        return null;
    }

    /**
     * 登陆接口
     *
     * @param account: 账号 password: 密码  urlBack: 返回url
     */
    @RequestMapping("logout")
    @ResponseBody
    public JsonResult logout(@RequestParam(value = "account") @Validated @NotNull String account,
                             HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            Map map = memberService.logout(account, request);
            if (map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                if ("true".equals(result)) {
                    Map data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    jsonResult = new JsonResult(1, message, data, true);
                } else if ("false".equals(result)) {
                    jsonResult = new JsonResult(2, message, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 发送短信
     *
     * @param phone: 账号 password: 密码  urlBack: 返回url
     */
    @RequestMapping("sendvcode")
    @ResponseBody
    public JsonResult sendvcode(@RequestParam(value = "phone") @Validated @NotNull String phone,
                                HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = memberService.findByPhone(phone);
        if (member == null) {
            jsonResult = new JsonResult(2, "您的手机号码未注册过", false);
        } else if (member != null) {
            try {
                //String requestUrl="http://prenewapi.wujinyunshang.com/api/FactoryCommon/SendSms";
                String vcode = RandNumIs.create4RandNum();
                request.getSession().setAttribute("vcode", vcode);
                HttpUtil.sendSms(phone, "100", "您的验证码是:" + vcode);
                jsonResult = new JsonResult(1, "验证码发送成功", true);
                //Map map = memberService.logout(account,  request);
        	/*
            if(map != null){
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                if("true".equals(result)){
                    Map data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    jsonResult = new JsonResult(1, message, data, true);
                }else if("false".equals(result)){
                    jsonResult = new JsonResult(2, message, false);
                }
            } **/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonResult;
    }

    /**
     * 验证码校验
     * 账号 password: 密码  urlBack: 返回url
     */
    @RequestMapping("validcode")
    @ResponseBody
    public JsonResult validcode(@RequestParam(value = "phone") @Validated @NotNull String phone, @RequestParam(value = "vcode") @Validated @NotNull String vcode,
                                HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
//        	String requestUrl="http://prenewapi.wujinyunshang.com/api/FactoryCommon/SendSms";
            String prevcode = request.getSession().getAttribute("vcode").toString();
            if (vcode != null && vcode == prevcode) {
                jsonResult = new JsonResult(1, "验证码校验通过", true);
            }
//        	httpUtil.sendSms(phone, "100", msg);
            //Map map = memberService.logout(account,  request);
        	/*
            if(map != null){
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                if("true".equals(result)){
                    Map data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    jsonResult = new JsonResult(1, message, data, true);
                }else if("false".equals(result)){
                    jsonResult = new JsonResult(2, message, false);
                }
            } **/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 发送4位短信验证码(用于注册)
     *
     * @param phone
     * @param request
     * @return
     */
    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JsonResult sendVerifyCode4registe(@RequestParam(value = "phone") @Validated @NotNull String phone,
                                             HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            String verifyCode = RandNumIs.create4RandNum();
            /*request.getSession().setAttribute("verifyCode", verifyCode);*/
            SendTelMsgUtils.sendMsgMobile(phone, verifyCode);
            loginUtil.setMsg(phone, verifyCode);
            jsonResult = new JsonResult(1, "验证码发送成功", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
}
