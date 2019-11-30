package com.zlead.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.*;
import com.zlead.entity.SysUserEntity;
import com.zlead.service.*;
import com.zlead.dao.ShopDao;
import com.zlead.utils.LoginUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商铺后台管理--老版本-暂不用
 */
@Controller
@RequestMapping("/zlead/enterpriseBg")
public class EnterpriseBgController {
    @Resource
    private EnterpriseBgService enterpriseService;
    @Resource
    private GoodsBgService goodsBgService;
//    @Resource
//    private ArticleBgService articleBgService;
    @Resource
    private AdsBgService adsBgService;
    @Resource
    private QRCodeService qrCodeService;
    
    @Resource
    private ShopDao shopDao;
    
    @Resource
    private LoginUtil loginUtil;

    @Resource
    private RegionService regionService;

    /**
     * 查询企业的商品
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public JsonResult goodsList(HttpServletRequest request,
                                      @RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                                      @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"未登录","",false);
            return jsonResult;
        }
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<GoodsEntity> list = enterpriseService.getPage(params, pageBounds,member);
        if(list!=null&&list.size()>0){
            Page page =  new Page<GoodsEntity>(list, list.getPagination());
            jsonResult =  new JsonResult(1,"列表信息",page,true);
        }else{
            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 删除商品
     */
    @RequestMapping("/goodsDelete")
    @ResponseBody
    public JsonResult goodsDelete(HttpServletRequest request,@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  goodsBgService.goodsDelete(id);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    
    /**
     * 保存商品
     */
    @RequestMapping("/enterpriseGoodsSave")
    @ResponseBody
    public JsonResult enterpriseGoodsSave(HttpServletRequest request, @Validated GoodsEntity GoodsEntity){
        JsonResult jsonResult = null;
        GoodsEntity.setProdId((long) 0);
        System.out.println(GoodsEntity+"GoodsEntity");
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  enterpriseService.enterpriseGoodsSave(GoodsEntity,member);
            if(b){
                jsonResult =  new JsonResult(1,"保存成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"保存失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改商品
     */
    @RequestMapping("/enterpriseGoodsUpdate")
    @ResponseBody
    public JsonResult enterpriseGoodsUpdate(HttpServletRequest request,@Validated GoodsEntity GoodsEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  goodsBgService.goodsUpdate(GoodsEntity);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 修改商品
     */
    @RequestMapping("/enterpriseGoodUpdate")
    @ResponseBody
    public JsonResult enterpriseGoodUpdate(HttpServletRequest request,@Validated GoodsEntity GoodsEntity){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  goodsBgService.goodUpdate(GoodsEntity);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 企业店铺的信息
     */
    @RequestMapping("/enterpriseShopInfo")
    @ResponseBody
    public JsonResult enterpriseShopInfo(HttpServletRequest request){
        JsonResult jsonResult = null;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            Map<String, Object> map = new HashMap<String, Object>();
            ShopEntity shop = enterpriseService.enterpriseShopInfo(member);
            //查询省市区的名称
            String provinceName = "";
            String cityName = "";
            String countyName = "";
            //查询省的名称
            if( shop.getProvinceId()!=null){
                provinceName = regionService.getRegionName(String.valueOf(shop.getProvinceId()));
                //查询市的名称
                if(shop.getCityId()!=null){
                    cityName = regionService.getRegionName(String.valueOf(shop.getCityId()));
                    //查询县的名称
                    if(shop.getRegionId()!=null){
                        countyName = regionService.getRegionName(String.valueOf(shop.getRegionId()));
                    }
                }
            }
            map.put("provinceName", provinceName);//省的名称
            map.put("cityName", cityName);//市的名称
            map.put("countyName", countyName);//县的名称
            map.put("shop",shop);
            jsonResult =  new JsonResult(1,"供应商信息",map,true);
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 查询企业的订单
     */
    @RequestMapping("/enterpriseOrderList")
    @ResponseBody
    public JsonResult enterpriseOrderList(HttpServletRequest request,
                                          @RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                                          @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"未登录","",false);
            return jsonResult;
        }
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
        PageList<OrderEntity> list = enterpriseService.enterpriseOrderList(params, pageBounds,member);
        if(list!=null&&list.size()>0){
            Page page =  new Page<OrderEntity>(list, list.getPagination());
            jsonResult =  new JsonResult(1,"列表信息",page,true);
        }else{
            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 订单发货
     */
    @RequestMapping("/enterpriseShipments")
    @ResponseBody
    public JsonResult enterpriseShipments(HttpServletRequest request,
                                          @Validated OrderShippingEntity OrderShippingEntity){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult =  new JsonResult(2,"未登录","",false);
            return jsonResult;
        }
        boolean b = enterpriseService.enterpriseShipments(OrderShippingEntity,member);
        if(b){
            jsonResult =  new JsonResult(1,"发货成功","",true);
        }else{
            jsonResult =  new JsonResult(2,"发货失败","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

//    /**
//     * 查询企业新闻列表
//     */
//    @RequestMapping("/enterpriseArticleList")
//    @ResponseBody
//    public JsonResult enterpriseArticleList(HttpServletRequest request,
//                                          @RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
//                                          @RequestParam(value="size") @Validated @NotNull Integer size){
//        JsonResult jsonResult = null;
//        Map params = new HashMap();
//        //企业使用member表信息登录
//        MemberEntity member = loginUtil.getLoginMember(request);
//        if(member==null){
//            jsonResult =  new JsonResult(2,"未登录","",false);
//            return jsonResult;
//        }
//        //页数
//        PageBounds pageBounds = new PageBounds(pageNum,size);
//        PageList<ArticleEntity> list = enterpriseService.getArticleList(params, pageBounds,member);
//        if(list!=null&&list.size()>0){
//            Page page =  new Page<ArticleEntity>(list, list.getPagination());
//            jsonResult =  new JsonResult(1,"列表信息",page,true);
//        }else{
//            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }
//
//    /**
//     * 企业发布新闻
//     */
//    @RequestMapping("/enterpriseSaveArticle")
//    @ResponseBody
//    public JsonResult enterpriseSaveArticle(HttpServletRequest request,@Validated ArticleEntity ArticleEntity){
//        JsonResult jsonResult = null;
//        //企业使用member表信息登录
//        MemberEntity member = loginUtil.getLoginMember(request);
//        if(member!=null){
//        	ArticleEntity.setPublishType(1);
//            boolean b =  enterpriseService.enterpriseSaveArticle(ArticleEntity,member);
//            if(b){
//                jsonResult =  new JsonResult(1,"操作成功","",true);
//            }else{
//                jsonResult =  new JsonResult(2,"操作失败","",false);
//            }
//        }else{
//            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }
//
//    /**
//     * 企业发布新闻
//     */
//    @RequestMapping("/enterpriseArticle")
//    @ResponseBody
//    public JsonResult enterpriseArticle(HttpServletRequest request,@Validated ArticleEntity ArticleEntity,
//                                        @RequestParam(value="shopId") @Validated @NotNull Long shopId){
//        JsonResult jsonResult = null;
//        //企业使用member表信息登录
//
//        MemberEntity member = loginUtil.getLoginMember(request);
//        if(member!=null){
//            boolean b =  enterpriseService.enterprisArticle(ArticleEntity,member,shopId);
//            if(b){
//                jsonResult =  new JsonResult(1,"操作成功","",true);
//            }else{
//                jsonResult =  new JsonResult(2,"操作失败","",false);
//            }
//        }else{
//            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }
//
//
//    /**
//     * 企业修改新闻
//     */
//    @RequestMapping("/enterpriseUpdateArticle")
//    @ResponseBody
//    public JsonResult enterpriseUpdateArticle(HttpServletRequest request,@Validated ArticleEntity articleEntity){
//        JsonResult jsonResult = null;
//        //企业使用member表信息登录
//        MemberEntity member = loginUtil.getLoginMember(request);
//        if(member!=null){
//            boolean b =  articleBgService.updateArticle(articleEntity);
//            if(b){
//                jsonResult =  new JsonResult(1,"操作成功","",true);
//            }else{
//                jsonResult =  new JsonResult(2,"操作失败","",false);
//            }
//        }else{
//            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }
//
//       /**
//     * 企业删除新闻
//     */
//    @RequestMapping("/enterpriseDeleteArticle")
//    @ResponseBody
//    public JsonResult enterpriseDeleteArticle(HttpServletRequest request,@RequestParam("id") Long id){
//        JsonResult jsonResult = null;
//        //企业使用member表信息登录
//        MemberEntity member = loginUtil.getLoginMember(request);
//        if(member!=null){
//            boolean b =  articleBgService.articleDelete(id);
//            if(b){
//                jsonResult =  new JsonResult(1,"操作成功","",true);
//            }else{
//                jsonResult =  new JsonResult(2,"操作失败","",false);
//            }
//        }else{
//            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }

    /**
     * 查询企业banner列表
     */
    @RequestMapping("/enterpriseAdsList")
    @ResponseBody
    public JsonResult enterpriseAdsList(HttpServletRequest request,
                                            @RequestParam(value="pageNum") @Validated @NotNull Integer pageNum,
                                            @RequestParam(value="size") @Validated @NotNull Integer size){
        JsonResult jsonResult = null;
        Map params = new HashMap();
        //企业使用member表信息登录
        //SysUserEntity member = loginUtil.getLoginSysUser(request);
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null || member.getId()==null){
            jsonResult =  new JsonResult(2,"未登录","",false);
            return jsonResult;
        }
        //页数
        PageBounds pageBounds = new PageBounds(pageNum,size);
//        if(member.getId()!=null){
        	ShopEntity shop=shopDao.findByMemeberId(member.getId().toString());
        	if(shop.getId()!=null)
        		params.put("shopId", shop.getId());
//        }
        PageList<AdsEntity> list = enterpriseService.getAdsList(params, pageBounds,member);
//        PageList<AdsEntity> list =adsBgService.g(shop.getId());
//        PageList<AdsEntity> list = enterpriseService.getAllAds(pageBounds);
        if(list!=null&&list.size()>0){
            Page page =  new Page<AdsEntity>(list, list.getPagination());
            jsonResult =  new JsonResult(1,"列表信息",page,true);
        }else{
            jsonResult =  new JsonResult(2,"暂无数据信息","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 企业发布banner图
     */
    @RequestMapping("/enterpriseSaveAds")
    @ResponseBody
    public JsonResult enterpriseSaveAds(HttpServletRequest request,@Validated AdsEntity AdsEntity){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  enterpriseService.enterpriseSaveAds(AdsEntity,member);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }


    /**
     * 企业修改banner图
     */
    @RequestMapping("/enterpriseUpdateAds")
    @ResponseBody
    public JsonResult enterpriseUpdateAds(HttpServletRequest request,@Validated AdsEntity AdsEntity){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  adsBgService.updateAds(AdsEntity);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 企业删除banner图
     */
    @RequestMapping("/enterpriseDeleteAds")
    @ResponseBody
    public JsonResult enterpriseDeleteAds(HttpServletRequest request,@RequestParam("id") Long id){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            boolean b =  adsBgService.deleteAds(id);
            if(b){
                jsonResult =  new JsonResult(1,"操作成功","",true);
            }else{
                jsonResult =  new JsonResult(2,"操作失败","",false);
            }
        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 生成企业的二维码
     */
    @RequestMapping("/enterpriseMallQR")
    @ResponseBody
    public JsonResult enterpriseMallQR(HttpServletRequest request){
        JsonResult jsonResult = null;
        //企业使用member表信息登录
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member!=null){
            String path = qrCodeService.getTwoBarCode(request,member);
            jsonResult =  new JsonResult(1,"返回二维码信息",path,true);

        }else{
            jsonResult =  new JsonResult(2,"后台用户未登录","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }
}
