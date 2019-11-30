package com.zlead.reception.controller;

import com.zlead.util.JsonUtil;
import com.zlead.util.JsonResult;
import com.zlead.entity.MemberEntity;
import com.zlead.reception.service.LogisticsService;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
* 物流信息接口--老版本-暂不用
* @Author: fqf
**/
@Controller
@RequestMapping("/zlead/logistics")
public class LogisticsController {
    @Resource
    private LogisticsService logisticsService;
    
    @Resource
    private LoginUtil loginUtil;

    /**
     * 物流信息实时接口（直接去快递100查询）
     * @param request
     * @param orderSn 订单编号
     * @return
     * @author fqf
     */
    @ResponseBody
    @RequestMapping("/realTimeLogisticsInfo")
    public JsonResult realTimeLogisticsInfo(HttpServletRequest request, @RequestParam(name="orderSn",required=true) @Validated @NotNull String orderSn){
        JsonResult jsonResult= null;
        try {
            //查询登录用户
            MemberEntity member = loginUtil.getLoginMember(request);
            //输出的参数
            Map<String, Object> map = logisticsService.getLogisticsInfoRealTime(orderSn);
            if(map!=null){
                jsonResult =  new JsonResult(1,"返回物流信息",map,true);
            }else{
                jsonResult =  new JsonResult(2,"没有物流信息","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 快递100物流消息推送回调接口
     * @param request
     * @author fqf
     */
 /*   @ResponseBody
    @RequestMapping("/expressBackUrl")
    public void expressBackUrl(HttpServletRequest request){
        try {
            //反向代理的地址：http://fqf.tunnel.echomod.cn
            //获取返回的数据
            String param = request.getParameter("param");
            logisticsService.getExpressPushInfo(param);
        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    /**
     * 查询保存的最新的物流信息
     * @param request
     * @param orderSn 订单编号
     * @return
     * @author fqf
     */
    @ResponseBody
    @RequestMapping("/logisticsInfo")
    public JsonResult logisticsInfo(HttpServletRequest request, @RequestParam(name="orderSn",required=true) @Validated @NotNull String orderSn){
        JsonResult jsonResult= null;
        try {
            //查询登录用户
            MemberEntity member = loginUtil.getLoginMember(request);
            //输出的参数
            Map<String, Object> map = logisticsService.getLogisticsInfo(orderSn);
            if(map!=null){
                jsonResult =  new JsonResult(1,"返回物流信息",map,true);
            }else{
                jsonResult =  new JsonResult(2,"没有物流信息","",false);
            }
        }catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonResult =  new JsonResult(2,"系统异常","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
}
