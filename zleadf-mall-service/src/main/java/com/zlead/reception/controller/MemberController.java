package com.zlead.reception.controller;


import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.reception.service.MemberService;
import com.zlead.service.RegionService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.utils.HttpUtil;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.MD5Util;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 登录注册接口--老版本-暂不用
 */
@Controller
@RequestMapping("/zlead/login")
public class MemberController {
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

    @ModelAttribute
    public void getWebUrl() {
        weburl = httpUtil.getWebUrl();
    }

    //用户注册接口
    @RequestMapping("registered")
    @ResponseBody
    public JsonResult userRegist(HttpServletRequest request, MemberEntity memberEntity){
        JsonResult jsonResult = null;
        String result=null;
        String phone= request.getParameter("phone");
        String passWord= request.getParameter("passWord");
        String provinceName= request.getParameter("provinceName");
        String cityName= request.getParameter("cityName");
        String countyName = request.getParameter("countyName");
        result = JsonUtil.getJson(jsonResult);
        //  JsonUtil.render(response, result);
        //查询电话有没有被占用
        MemberEntity member = memberService.findByPhone(phone);
        if(member!=null){
            jsonResult =  new JsonResult(2,"该手机号已被占用","",false);
            return jsonResult;
        }
        boolean b = memberService.saveMember(phone,passWord);
        if(b){
            jsonResult =  new JsonResult(1,"注册成功","",true);
        }else{
            jsonResult =  new JsonResult(2,"注册失败","",false);
        }
        return jsonResult;
    }

    /**
     * 用户登录
     */
    @RequestMapping("login")
    @ResponseBody
    public JsonResult login(@RequestParam(value="account") @Validated @NotNull String account,
                            @RequestParam(value="password") @Validated @NotNull String password,
                            HttpServletRequest request){
        JsonResult jsonResult = null;
        try{
            Map map = memberService.login(account, password, request);
            if(map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
//                String returnurl= map.get("returnurl").toString();
                if("true".equals(result) && map.get("data")!=null){
//                    HashMap data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
//                	data.put("returnurl", returnurl);
//                	System.out.println("returnurl 3:"+returnurl);
                    jsonResult = new JsonResult(1, message, map.get("data"), true);
                }else if("false".equals(result)){
                    jsonResult = new JsonResult(2, message, false);
                }
            }else{
                jsonResult = new JsonResult(2, "没有该用户", false);
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
    public String memberlogin(@RequestParam(value="telNumber") @Validated @NotNull String account,
                              @RequestParam(value="password") @Validated @NotNull String password,
                              Model model,HttpServletRequest request){
        JsonResult jsonResult = null;
        String returnurl=request.getParameter("returnurl");
        Object obShopId=request.getSession().getAttribute("shopId");
        String shopId=null;
        try{
            Map map = memberService.login(account, password, request);
            if(map != null) {
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                //returnurl= map.get("returnurl").toString();
                if("true".equals(result) && map.get("data")!=null){
                    HashMap data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    /**
                    data.put("returnurl", returnurl);
                    System.out.println("returnurl 3:"+returnurl);
                    jsonResult = new JsonResult(1, message, data, true);

                    if(obShopId==null){
                        ShopEntity shop = shopDao.findByMemeberId(data.get("memberId").toString());
                        if(shop!=null && shop.getId()!=null)
                            shopId=shop.getId().toString();
                        System.out.println("returnurl shopid 3:"+shopId);
                    }
                    if(shopId==null) shopId="10";
                    **/
                    if(returnurl==null || returnurl.trim()=="") returnurl="/member/agentAccount.jsp";
                    else{	model.addAttribute("msg", message);	}
                    System.out.println("returnurl shopid 31:"+weburl+returnurl);

                    System.out.println("returnurl shopid 32 return :/member/agentAccount");
                    return "redirect:"+weburl+returnurl;
                    //return "/member/agentAccount";
                }else if("false".equals(result)){
                    jsonResult = new JsonResult(2, message, false);
                    model.addAttribute("msg", message);
                    return "redirect:"+weburl+"/member/memberlogin.action";
                }
            }else{
                jsonResult = new JsonResult(2, "没有该用户", false);
                return "redirect:"+weburl+"/member/memberlogin.action";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "/member/login";
    }

    /**
     * 会员个人详细信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("memberInfo")
    @ResponseBody
    public String memberInfo(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = null;
        String result=null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
//        MemberEntity member =new MemberEntity();
//        long i=1;
//        member.setId(i);
        if(member!=null){
            Map<String,Object> map = memberService.memebrInfo(member);
            System.out.println(map);
            jsonResult =  new JsonResult(1,"获取个人信息成功",map,true);
        }else{
            jsonResult =  new JsonResult(2,"找不到用户","",false);
        }

        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
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
    public String forgetPass(HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = null;
        String result=null;
        String phone= request.getParameter("phone");
        String newPass= request.getParameter("newPass");

        MemberEntity member= memberService.findByPhone(phone);
        if(member!=null){
            member.setPassword(MD5Util.toMD5(newPass));
            memberService.updateById(member);
            if(MD5Util.toMD5(newPass).equals(member.getPassword())){
                jsonResult =  new JsonResult(1,"密码修改成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"密码修改失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"找不到用户","",false);
        }

        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
    }

    /**
     * 已知密码的情况下 修改密码接口
     * @param request
     * @param response
     * @param em
     * @return
     */
    @RequestMapping("updatepaw")
    @ResponseBody
    public String updatepaw(HttpServletRequest request, HttpServletResponse response,MemberEntity em){
        JsonResult jsonResult = null;
        String result=null;
        MemberEntity member = loginUtil.getLoginMember(request);
        //新密码
//        em.setPassword("123456");
        //原密码
//        em.setNpassword("111111");
        MemberEntity sc= memberService.findById(member.getId());
        //原密码
        String paw =em.getNpassword();
        //新密码
        String pawred= em.getPassword();
        //原密码 转md5
        String pawt=MD5Util.toMD5(paw);
        String paws =MD5Util.toMD5(member.getPassword());

        if(member!=null) {
            //判断原密码与数据库是否相同 md5的情况
            if (paws != pawt) {
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
            } else {
                jsonResult = new JsonResult(2, "密码修改失败", "", false);
            }
        }else {
                jsonResult =  new JsonResult(2,"修改密码不能与原密码一致","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"找不到用户","",false);
        }
        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);
        return null;
    }

    /**
     * 修改用户
     * @return
     */
    @RequestMapping("updateVipe")
    @ResponseBody
    public String updatevipe(MemberEntity em,HttpServletRequest request, HttpServletResponse response){
        JsonResult jsonResult = null;
        String result=null;
        //获取已登录用户信息
        em.setNickName("测试1.23");
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
            if (in!=0){
                jsonResult =  new JsonResult(1,"用户修改成功", in,true);
            }else {
                jsonResult =  new JsonResult(1,"用户修改失败", in,false);
            }
        }else {
            jsonResult =  new JsonResult(1,"用户不存在",false);
        }
        result = JsonUtil.getJson(jsonResult);
        JsonUtil.render(response, result);

        return null;
    }

    /**
     * 登陆接口
     * @param account: 账号 password: 密码  urlBack: 返回url
     * */
    @RequestMapping("logout")
    @ResponseBody
    public JsonResult logout(@RequestParam(value="account") @Validated @NotNull String account,
                             HttpServletRequest request){
        JsonResult jsonResult = null;
        try{
            Map map = memberService.logout(account,  request);
            if(map != null){
                String result = map.get("success").toString();
                String message = map.get("message").toString();
                if("true".equals(result)){
                    Map data = JsonUtil.readJsonToMap(map.get("data").toString(), 0);
                    jsonResult = new JsonResult(1, message, data, true);
                }else if("false".equals(result)){
                    jsonResult = new JsonResult(2, message, false);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonResult;
    }
}
