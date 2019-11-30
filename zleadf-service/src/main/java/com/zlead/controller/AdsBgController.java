package com.zlead.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.SysUserEntity;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.AdsEntity;
import com.zlead.service.AdsBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * 广告信息后台--老版本-暂不用
 *
 * @author fqf
 * @date 2018-08-13 14:02:16
 */
@Controller
@RequestMapping("/zlead/tadsBg")
public class AdsBgController {
    @Resource
    private AdsBgService adsBgService;


    @Resource
    private LoginUtil loginUtil;

    @RequestMapping("/adsList")
    @ResponseBody
    public JsonResult list(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value = "size") @Validated @NotNull Integer size,
                           @RequestParam(value = "shopId") Integer shopId) {
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //页数
        if (shopId!=null){
            params.put("shopId",shopId);
        }
        PageBounds pageBounds = new PageBounds(pageNum, size);
        PageList<AdsEntity> list = adsBgService.getPage(params, pageBounds);
        jsonResult = new JsonResult(1, "列表信息", list, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("id") Long id) {
        JsonResult jsonResult = null;
        AdsEntity tAds = adsBgService.findById(id);
        jsonResult = new JsonResult(1, "详细信息", tAds, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(@Validated AdsEntity tAds,HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"用户未登录","",false);
            return jsonResult;
        }
        adsBgService.save(tAds,member.getId());
        jsonResult = new JsonResult(1, "保存成功", tAds, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@Validated AdsEntity tAds,HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"用户未登录","",false);
            return jsonResult;
        }
        if(tAds.getShopId() == null){
        	System.out.println(" ads shop id is null ");
        	tAds.setShopId(0L);
        }
        adsBgService.update(tAds);
        jsonResult = new JsonResult(1, "修改成功", tAds, true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(@RequestParam("id") Long id,HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"用户未登录","",false);
            return jsonResult;
        }
        adsBgService.delete(id);
        jsonResult = new JsonResult(1, "删除成功", "", true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

}
