package com.zlead.controller;


import com.zlead.entity.Region;
import com.zlead.service.RegionService;
import com.zlead.service.ShopBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地址--老版本-暂不用
 */
@Controller
@RequestMapping("/zlead/region")
public class RegionController {
    @Resource
    private RegionService regionService;

    @Resource
    private ShopBgService shopBgService;
    /**
     * 根据地区code查找地区名称
     * @param region_code
     * @return
     */
    @ResponseBody
    @RequestMapping("/lookupRegion")
    public JsonResult lookupRegion(@RequestParam("region_code") String region_code){
        JsonResult jsonResult=null;
        String name=regionService.getRegionName(region_code);
        jsonResult=new JsonResult(1,"地址信息",name,true);
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("res"+result);
        return jsonResult;
    }

    /**
     * 三级联动
     * ytchen
     * 19-1-22
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/findprovince")
    public JsonResult findprovince(){
        JsonResult jsonResult=null;
        List<Region> List = regionService.findprovince();
        jsonResult=new JsonResult(1,"地址信息省",List,true);
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("res"+result);
        return jsonResult;
    }

    /**
     * 三级联动 市/县/区
     * ytchen
     * 19-1-22
     * @param parentId
     * @return jsonResult
     */
    @ResponseBody
    @RequestMapping("/findid")
    public JsonResult findid(int parentId){
        JsonResult jsonResult=null;
        List<Region> List = regionService.findid(parentId);
        jsonResult=new JsonResult(1,"地址信息市/县/区",List,true);
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("res"+result);
        return jsonResult;
    }



//    /**
//     * 根据memberid查找商铺id
//     * @param memberId
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/findMemberId")
//    public JsonResult findMemberId(@RequestParam("memberId") String memberId){
//        JsonResult jsonResult=null;
//      ShopEntity shopEntity= shopBgService.findByMemeberId(memberId);
//        jsonResult=new JsonResult(1,"地址信息",shopEntity,true);
//return jsonResult;
//    }


}
