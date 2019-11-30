package com.zlead.shopmgr.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.AdsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.reception.service.AdsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/zlead/adsmgr")
public class AdsMgrController {
    @Resource
    private AdsService adsService;
    
    @Autowired
    private LoginUtil loginUtil;
 

    @RequestMapping("/querylist")
    @ResponseBody
    public JsonResult querylist(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size,
                           @RequestParam(value="shopId") @Validated @NotNull Integer shopId){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        params.put("shopId", shopId);
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<AdsEntity>  list = adsService.getPage(params, pageBounds);
        jsonResult =  new JsonResult(1,"列表信息",list,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


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
     * 保存
     *
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(HttpServletRequest request,@Validated AdsEntity ads){
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null && member.getOwnShopid()!=null){
        	ads.setShopId(member.getOwnShopid());
        	if(ads.getAdsImg()!=null){
        		System.out.println(" ads save 1 : img: "+ads.getAdsImg());
        		System.out.println(" ads save 1 : id: "+ads.getId());
        		adsService.save(ads);
        	}else
        	{
        		jsonResult =  new JsonResult(3,"广告图片为空!",true);
        	}

        	jsonResult =  new JsonResult(1,"保存成功",ads,true);
        //String result = JsonUtil.getJson(jsonResult);
        }else{
        	jsonResult =  new JsonResult(3,"用户未登陆或用户不是商铺用户!",true);
        }
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated AdsEntity tAds){
        JsonResult jsonResult = null;
        adsService.save(tAds);

        jsonResult =  new JsonResult(1,"修改成功",tAds,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        adsService.delete(id);

        jsonResult =  new JsonResult(1,"删除成功","",true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
   
}
