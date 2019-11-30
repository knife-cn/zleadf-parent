package com.zlead.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.fplat.entity.OaAgentMas;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.transaction.AgentTrans;
import com.zlead.fplat.transaction.FactoryTrans;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller

@RequestMapping("/zlead/factory")

public class FactoryTransController {
    @Resource
    private FactoryTrans factoryTrans;
    @Resource
    private AgentTrans agentTrans;
    @Resource
    private LoginUtil loginUtil;


    @RequestMapping("/addFactory")
    @ResponseBody
public JsonResult addFactory(OaFactoryInfo fac, HttpServletRequest request){
        JsonResult result = null;
        MemberEntity user = loginUtil.getLoginMember(request);
        if (user == null) {
            result = new JsonResult(2, "未登录", false);
        }else {
            int i = factoryTrans.insertFactory(fac);
            if(i>0){
                result = new JsonResult(1, "创建工厂成功", true);
            }else {
                result = new JsonResult(2, "创建工厂失败", false);
            }
        }


        return result;
}
    @RequestMapping("/addAgent")
    @ResponseBody
    public JsonResult addAgent(OaAgentMas agentMas,HttpServletRequest request){
        JsonResult result = null;
        MemberEntity user = loginUtil.getLoginMember(request);
        if (user == null) {
            result = new JsonResult(2, "未登录", false);
        }else{
            int i = agentTrans.insertAgent(agentMas);
            if(i>0){
                result = new JsonResult(1, "创建代理商成功", true);
            }else {
                result = new JsonResult(2, "创建代理商失败", false);
            }}



        return result;
    }
}
