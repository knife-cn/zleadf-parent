package com.zlead.shop.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.AdsEntity;
import com.zlead.reception.service.AdsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * 广告信息
 *
 * @author fqf
 * @date 2018-08-03 16:42:39
 */
@Controller
@RequestMapping("/zlead/shop")
public class shopAdsController {
    @Resource
    private AdsService adsService;

 
    /**
     * 信息
     */
    @RequestMapping("/adsinfo")
    @ResponseBody
    public JsonResult adsinfo(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
		AdsEntity ads = adsService.findById(id);

        jsonResult =  new JsonResult(1,"详细信息",ads,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
 
    /**
     * 查找全部轮播图
     */
    @RequestMapping("/QueryAdsList")
    @ResponseBody
    public JsonResult QueryAdsList(@RequestParam("shopid") Long shopid){
        JsonResult jsonResult = null;
//        AdsEntity adsEntity=new AdsEntity();
//        adsEntity.setShopId(shopid);
        List<AdsEntity> adslist= adsService.QueryAdsList(shopid);
        jsonResult= new JsonResult(1,"获取成功",adslist,true);

        String result = JsonUtil.getJson(jsonResult);
        System.out.println("返回的集合"+result);
        return jsonResult;
    }
}
