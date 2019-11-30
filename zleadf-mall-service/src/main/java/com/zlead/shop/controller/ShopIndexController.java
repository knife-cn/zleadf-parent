package com.zlead.shop.controller;

import com.zlead.dao.ShopDao;
import com.zlead.entity.*;
import com.zlead.service.RegionService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.service.EnterpriseService;
import com.zlead.reception.service.AdsService;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商铺首页
 */
@Controller
@RequestMapping("/zlead/shopindex")
public class ShopIndexController {
    @Resource
    private EnterpriseService enterpriseService;

    @Resource
    private LoginUtil loginUtil;


    @Resource
    private RegionService regionService;

    @Autowired
    private ShopDao shopDao;
    
    @Autowired
    private AdsService adsService;


    /**
     * 查询商铺banner信息
     */
    @RequestMapping("/shopAdsInfo")
    @ResponseBody
    public JsonResult shopAdsInfo(@RequestParam(value = "shopId") @Validated @NotNull Long shopId) {
        JsonResult jsonResult = null;
        if (shopId == null) {

        } else {

//            Map map = enterpriseService.shopInfo(shopId);
//            String success = map.get("success").toString();
//            String message = map.get("message").toString();
//            System.out.println(success);
//            if ("true".equals(success)) {
//                String memberId = map.get("memberId").toString();
//                System.out.println("memberid:" + memberId);
                
                List<AdsEntity> list = adsService.QueryAdsList(shopId);
                if (list != null && list.size() > 0) {
                    jsonResult = new JsonResult(1, "banner信息", list, true);
                } else {
                    jsonResult = new JsonResult(2, "没有banner信息", "", false);
                }
//            } else if ("false".equals(success)) {
//                jsonResult = new JsonResult(2, message, "", false);
//            }
            String result = JsonUtil.getJson(jsonResult);
            System.out.println("11111111111111111111++++++++++++" + result);
        }

        return jsonResult;
    }
    

    /**
     * 查询商铺商品列表
     *
     * @param prodType
     * @param page
     * @return
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public JsonResult goodsList(@RequestParam(name = "prodType") Integer prodType,
                                @RequestParam(name = "page", required = true) @Validated @NotNull Integer page,
                                @RequestParam(name = "shopId", required = true) @Validated @NotNull Long shopId) {
        JsonResult jsonResult = null;
        try {
            //获取信息
            PageList<GoodsEntity> goodslist = enterpriseService.getGoodsList(prodType, page, shopId);
            if (goodslist != null && goodslist.size() > 0) {

                jsonResult = new JsonResult(1, "返回商品列表!", goodslist, true);
            } else {
                jsonResult = new JsonResult(2, "没有查询到商品列表!", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "返回数据失败!", e.getMessage(), false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 查询企业首页商品
     */
    @RequestMapping("/homeGoodsList")
    @ResponseBody
    public JsonResult homeGoodsList(@RequestParam(name = "showNum", required = true) @Validated @NotNull Integer showNum,
                                    @RequestParam(name = "shopId", required = true) @Validated @NotNull Long shopId) {
        JsonResult jsonResult = null;
        //查询首页商品
        List<GoodsEntity> goodsList = enterpriseService.gethomeGoodsList(showNum, shopId);
        if (goodsList != null && goodsList.size() > 0) {
            jsonResult = new JsonResult(1, "返回推荐商品", goodsList, true);
        } else {
            jsonResult = new JsonResult(2, "没有查询到商品", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("推荐商品:" + result);
        return jsonResult;
    }

  

    @RequestMapping("/shopInfo")
    @ResponseBody
    public JsonResult shopInfo(@RequestParam(value = "shopId") @Validated @NotNull Long shopId) {
        JsonResult jsonResult = null;
        Map<String, Object> map = new HashMap<String, Object>();
        // ShopEntity shop = enterpriseService.shopInfo(shopId);
        ShopEntity shop = shopDao.findById(shopId);
        // enterpriseService.enterpriseShopInfo(member);
        //查询省市区的名称=
        String provinceName = "";
        String cityName = "";
        String countyName = "";
        if (shop == null) {
            jsonResult = new JsonResult(2, "商家信息为空", "", false);
        } else {
            //查询省的名称
            if (shop.getProvinceId() != null) {
                provinceName = regionService.getRegionName(String.valueOf(shop.getProvinceId()));
                //查询市的名称
                if (shop.getCityId() != null) {
                    cityName = regionService.getRegionName(String.valueOf(shop.getCityId()));
                    //查询县的名称
                    if (shop.getRegionId() != null) {
                        countyName = regionService.getRegionName(String.valueOf(shop.getRegionId()));
                    }
                }
            }
            map.put("provinceName", provinceName);//省的名称
            map.put("cityName", cityName);//市的名称
            map.put("countyName", countyName);//县的名称
            map.put("imgServ", "http://116.62.124.171/group1");
            map.put("shop", shop);
            jsonResult = new JsonResult(1, "商家信息", map, true);
        }
        // String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


}
