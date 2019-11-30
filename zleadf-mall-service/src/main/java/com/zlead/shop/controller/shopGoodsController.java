package com.zlead.shop.controller;

import com.zlead.common.PageResponse;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.GoodsDto;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.reception.service.GoodsService;
import com.zlead.reception.service.MemberService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *前台商品的展示
 * @author fqf
 * @date 2018-07-31 09:55:22
 */
@Controller
@RequestMapping("/zlead/shop")
public class shopGoodsController {
    @Resource
    private GoodsService goodsService;

    @Resource
    private LoginUtil loginUtil;

    @Autowired
    private MemberService memberService;
//
//    /**
//     * 按照传入的商品个数值查询推荐商品
//     * 商铺首页商品
//     */
//    @RequestMapping("/homeGoodsList")
//    @ResponseBody
//    public JsonResult homeGoodsList(@RequestParam(name="showNum",required=true)@Validated @NotNull Integer showNum){
//        JsonResult jsonResult = null;
//        //输出的参数
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        //查询最新的六个商品
//        List<GoodsEntity> goodsList = goodsService.gethomeGoodsList(showNum);
//        if(goodsList!=null&&goodsList.size()>0){
//            for (GoodsEntity goodsEntity : goodsList) {
//                //返回的信息
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("id", goodsEntity.getId());
//                map.put("fullName", goodsEntity.getFullName());
//                map.put("sales", goodsEntity.getSalesNum());
//                map.put("price", goodsEntity.getPrice());
//                map.put("pointPrice", goodsEntity.getPoint());
//                map.put("img", goodsEntity.getFirstImg());
//                map.put("supplyPrice",goodsEntity.getSupplyPrice());
//                map.put("agentPrice",goodsEntity.getAgentPrice());
//                map.put("marketPrice",goodsEntity.getMarketPrice());
//                // map.put("pointType", goodsEntity.getPointType());//0-积分购 1-积分换 2-共享商城3-代理商城
//                map.put("stock", goodsEntity.getStock());//库存
//                list.add(map);
//            }
//            jsonResult =  new JsonResult(1,"返回推荐商品",list,true);
//        }else{
//            jsonResult =  new JsonResult(2,"没有查询到商品","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        System.out.println("推荐商品:"+result);
//        return jsonResult;
//    }

   /* *//**
     * 查询商品列表
     * @param
     * @param page
     * @return
     *//*
    @RequestMapping("/queryAgentgoodsList")
    @ResponseBody
    public JsonResult queryAgentgoodsList(@RequestParam(name="page",required=true)@Validated @NotNull Integer page){
        JsonResult jsonResult = null;
        try {
            //获取信息
            PageList<GoodsEntity> goodslist = goodsService.queryAgentGoodsList(page);
            if(goodslist!=null&&goodslist.size()>0){

                jsonResult =  new JsonResult(1,"返回商品列表!",goodslist,true);
            }else{
                jsonResult =  new JsonResult(2,"没有查询到商品列表!","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"返回数据失败!",e.getMessage(),false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }*/


//    /**
//     * 查询商品列表
//     * @param prodType
//     * @param page
//     * @return
//     */
//    @RequestMapping("/goodsList")
//    @ResponseBody
//    public JsonResult goodsList( @RequestParam(name="prodType") Integer prodType,
//                                 @RequestParam(name="page",required=true)@Validated @NotNull Integer page,
//                                 @RequestParam(name="shopId") Integer shopId){
//        JsonResult jsonResult = null;
//        try {
//            //获取信息
//            PageList<GoodsEntity> goodslist = goodsService.getGoodsList(prodType, page,shopId);
//            if(goodslist!=null&&goodslist.size()>0){
//
//                jsonResult =  new JsonResult(1,"返回商品列表!",goodslist,true);
//            }else{
//                jsonResult =  new JsonResult(2,"没有查询到商品列表!","",false);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            jsonResult =  new JsonResult(2,"返回数据失败!",e.getMessage(),false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        return jsonResult;
//    }

  /*  *//**
     * 商品详情接口
     *//*
    @RequestMapping("/goodsDetail")
    @ResponseBody
    public JsonResult goodsDetail(@RequestParam(name="goodsId",required=true)@Validated @NotNull Long goodsId){
        //获取商品的ID
        JsonResult jsonResult;
        try {
            if(goodsId != 0){
                //查询商品详情
                GoodsDto map = goodsService.getGoodsDetail(goodsId);
                jsonResult =  new JsonResult(1,"返回商品详情!",map,true);
            }else{
                jsonResult =  new JsonResult(2,"传入的商品Id为空!","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"返回数据失败!","",false);
        }

        String result = JsonUtil.getJson(jsonResult);
        System.out.println("商品详情:"+result);
        return jsonResult;
    }

    @RequestMapping("/goodsCollect/{pageCurrent}/{pageSize}")
    @ResponseBody
    public JsonResult goodsCollectPageList(HttpServletRequest request, @PathVariable("pageCurrent") int pageCurrent,
                                           @PathVariable("pageSize") int pageSize){
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult = new JsonResult(2,"找不到用户","",false);
            return jsonResult;
        }
        try {
            PageResponse pageResponse = goodsService.pageListCollent(member.getAgentId().intValue(),
                    new PageBounds(pageCurrent,pageSize));
            jsonResult =  new JsonResult(1,"返回商品详情!",pageResponse,true);
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"返回数据失败!","",false);
        }

        String result = JsonUtil.getJson(jsonResult);
        System.out.println("商品详情:"+result);
        return jsonResult;
    }

    @RequestMapping("/getGoods")
    @ResponseBody
    public JsonResult getGoodsBy(@RequestBody  List<GoodsAttrValueReponse> goodsAttrValueResponse,HttpServletRequest request){
        JsonResult jsonResult;
        //获取当前登录的用户信息
        MemberEntity member = loginUtil.getLoginMember(request);
        if(member==null){
            jsonResult = new JsonResult(2,"找不到用户","",false);
            return jsonResult;
        }
        try {
            if(goodsAttrValueResponse.size() == 0){
                return  new JsonResult(2,"返回数据失败!","",false);
            }
            GoodsDto goodsDto = goodsService.getByGoodsId(goodsAttrValueResponse);
            jsonResult =  new JsonResult(1,"返回商品详情!",goodsDto,true);
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"返回数据失败!","",false);
        }
        return jsonResult;
    }*/
}
