package com.zlead.reception.controller;

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
 * 广告信息--老版本-暂不用
 *
 * @author fqf
 * @date 2018-08-03 16:42:39
 */
@Controller
@RequestMapping("/zlead/tads")
public class AdsController {
    @Resource
    private AdsService adsService;


    /*
     * 首页轮播图接口
     */
    @ResponseBody
    @RequestMapping("/adsList")
    public JsonResult AdsList( @RequestParam(name="adstype",required=true) @Validated @NotNull Integer adstype){
        JsonResult jsonResult= null;
        //输出的参数
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            //传入的判断数据
            Map params = new HashMap();
            params.put("adstype", adstype);
            params.put("catagory", 1);
            params.put("status", 1);
            //查询
            List<AdsEntity> adsList = adsService.getAdsList(params);
            if(adsList!=null && adsList.size()>0){
                for (AdsEntity ads : adsList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", ads.getId());
                    map.put("thumbnail", ads.getThumbnail());//缩略图
                    map.put("adsImg", ads.getAdsImg());//大图
                    map.put("title", ads.getTitle());//标题
                    map.put("contentPath", ads.getContentPath());//详情的地址
                    map.put("contentType", ads.getContentPath());//0广告图片1文章2商品3视频4外链
                    list.add(map);
                }
                jsonResult = new JsonResult(1,"返回轮播图数据",list,true);
            }else{
                jsonResult = new JsonResult(2,"没有轮播图数据","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2,"返回轮播图信息失败","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(@RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                           @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
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
    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info(@RequestParam("id") Long id){
        JsonResult jsonResult = null;
		AdsEntity ads = adsService.findById(id);

        jsonResult =  new JsonResult(1,"详细信息",ads,true);
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(@Validated AdsEntity tAds){
        JsonResult jsonResult = null;
        adsService.save(tAds);

        jsonResult =  new JsonResult(1,"保存成功",tAds,true);
        String result = JsonUtil.getJson(jsonResult);
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
