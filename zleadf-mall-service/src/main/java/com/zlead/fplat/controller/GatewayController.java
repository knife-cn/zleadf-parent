package com.zlead.fplat.controller;

import com.zlead.fplat.service.GatewayService;
import com.zlead.util.JsonResult;
import com.zlead.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by admin on 2019/1/16.
 */

@RestController
@RequestMapping("/gateWay")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private RedisUtil redisUtil;

    //导航条
    @GetMapping("/navigation")
    public JsonResult navigation(HttpServletRequest request){
    	try{
        /*进入首页先清空redis中面包屑导航数据*/
    		redisUtil.clearNaviRedis(request);
    		//return gatewayService.navigation(request);
            return gatewayService.newNavigation(request);
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }


    //限时活动
    @GetMapping("/actives")
    public JsonResult actives(HttpServletRequest request){
        return gatewayService.actives(request);

    }


    //锚点数据
    @GetMapping("/anchorPoint")
    public JsonResult anchorPoint(HttpServletRequest request){
        return gatewayService.getCat(request);
    }

    //首页商品数据区域
    @GetMapping("/prdArea")
    public JsonResult prdArea(HttpServletRequest request){
        try {
            //return gatewayService.getPrdArea(request);
            return gatewayService.newGetPrdArea(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
