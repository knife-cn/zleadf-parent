package com.zlead.member.controller;


import com.zlead.dao.ShopDao;
import com.zlead.entity.MemberEntity;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.transaction.AgentTrans;
import com.zlead.reception.service.MemberService;
import com.zlead.service.RegionService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.utils.DateTool;
import com.zlead.utils.HttpUtil;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 登录注册接口
 */
@RestController
@RequestMapping("/zlead/api")
public class MsgController {
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

    //找回密码 短信验证
    @RequestMapping(value = "msg")
    public JsonResult msg(HttpServletRequest request,@RequestBody MemberEntity memberEntity){
        JsonResult jsonResult = null;
        //查询手机有没有被注册
        MemberEntity member = memberService.findByPhone(memberEntity.getPhone());
        if(member!=null){
            String result =memberService.msg(memberEntity.getPhone());
            jsonResult =  new JsonResult(2,result,"",true);
            return jsonResult;
        }else{
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);

        }
        return jsonResult;
    }



    //找回密码 短信验证
    @RequestMapping(value = "msgcode",method =RequestMethod.POST)
    public JsonResult msgcode(HttpServletRequest request,String phone,String msg){
        JsonResult jsonResult = null;
        MemberEntity member = memberService.findByPhone(phone);
        String result =memberService.msgIs(phone);
        if(member!=null){
        if(result.equals(msg)){
            jsonResult =  new JsonResult(2,"验证完成","",true);
        }else{
            jsonResult =  new JsonResult(2,"验证码不正确","",false);

        }
        }else {
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);
        }
        return jsonResult;
    }
    @RequestMapping(value = "updatepwd",method =RequestMethod.POST)
    public JsonResult updatepwd(HttpServletRequest request,@RequestBody MemberEntity memberEntity){
        JsonResult jsonResult = null;
        MemberEntity member = memberService.findByPhone(memberEntity.getPhone());
        if(member!=null){
            String pwd=memberEntity.getPassword();
            String pawt=MD5Util.toMD5(pwd);
            member.setPassword(pawt);
            int i=memberService.updatepwd(member);
            if (i != 0) {
                jsonResult =  new JsonResult(2,"密码修改成功","",true);
            }else {
                jsonResult =  new JsonResult(2,"密码修改失败","",false);
            }
        }else {
            jsonResult =  new JsonResult(2,"该手机号未注册","",false);
        }
        return jsonResult;
    }

     
}
