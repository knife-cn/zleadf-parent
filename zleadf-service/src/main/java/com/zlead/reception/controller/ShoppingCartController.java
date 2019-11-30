package com.zlead.reception.controller;

import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShoppingCartEntity;
import com.zlead.reception.service.GoodsService;
import com.zlead.reception.service.ShoppingCartService;
import com.zlead.utils.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;




/**
 * 购物车--老版本-暂不用
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 11:04:28
 */
@Controller
@RequestMapping("/zlead/tshoppingcart")
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private GoodsService tGoodsService;

    @Resource
    private LoginUtil loginUtil;
    /**
     * 查询购物车接口
     */
    @RequestMapping("/shoppingCartGoods")
    @ResponseBody
    public JsonResult shoppingCartGoods( HttpServletRequest request,@Validated ShoppingCartEntity shoppingCartEntity,
                                         @RequestParam(name="prodType",required=false,defaultValue = "0")@Validated @NotNull Integer prodType){
        JsonResult jsonResult = null;
        try {
            //获取当前登录的用户信息
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                List<Map<String, Object>> list = shoppingCartService.getShoppingCart(member,shoppingCartEntity,prodType,request);
                if(list!=null&&list.size()>0){
                    jsonResult =  new JsonResult(1,"返回购物车信息!",list,true);
                }else{
                    jsonResult =  new JsonResult(2,"没有购物车信息!","",false);
                }
            }else{
                jsonResult =  new JsonResult(510,"没有查询到登录用户!","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jsonResult =  new JsonResult(2,"系统异常!","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("获取购物车商品信息:"+result);
        return jsonResult;
    }

    /**
     * 添加购物车接口
     */
    @RequestMapping("/addShoppingCart")
    @ResponseBody
    public JsonResult addShoppingCart( HttpServletRequest request,@Validated ShoppingCartEntity shoppingCartEntity){
        JsonResult jsonResult = null;
        //购物车添加时间
        try {
            //获取当前登录的用户信息
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                //查询当前的库存
                GoodsEntity goods = tGoodsService.findById(shoppingCartEntity.getGoodsId());
                if(goods!=null){
                    if(goods.getStock()!=null&&goods.getStock()>=shoppingCartEntity.getCount()){
                        String successType = shoppingCartService.shoppingCartSave(shoppingCartEntity,member);
                        if(successType.equals("1")){
                            jsonResult =  new JsonResult(1,"添加购物车成功！","",true);
                        }else{
                            jsonResult =  new JsonResult(2,"系统异常！添加购物车失败。","",false);
                        }
                    }else{
                        jsonResult =  new JsonResult(2,"添加购物车失败！商品库存不足。","",false);
                    }
                }else{
                    jsonResult =  new JsonResult(2,"添加购物车失败！没有查询到该商品。","",false);
                }
            }else{
                jsonResult =  new JsonResult(2,"没有查询到登录用户","",false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常！添加购物车失败。","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("添加购物车:"+result);
        return jsonResult;
    }

    /*
     * 删除购物车接口
     */
    @RequestMapping("/deleteShoppingCart")
    @ResponseBody
    public JsonResult deleteShoppingCart( @RequestParam(name="cartIds",required=true)@Validated @NotNull String cartIds){
        JsonResult jsonResult = null;
        //删除购物车
        try {
            shoppingCartService.delete(cartIds);
            jsonResult =  new JsonResult(1,"删除购物车成功","",true);
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常！删除购物车失败","",true);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("删除购物车:"+result);
        return jsonResult;
    }

    /**
     * 修改购物车商品的数量
     */
    @RequestMapping("/updateCount")
    @ResponseBody
    public JsonResult updateCount( @RequestParam(name="cartId",required=true)@Validated @NotNull Long cartId,
                                   @RequestParam(name="type",required=true)@Validated @NotNull Integer type,
                                   @RequestParam(name="count",required=false)Integer count){
        JsonResult jsonResult = null;
        try {
            Map<String,Object> map = shoppingCartService.updateCount(cartId,type,count);
            if(map==null){
                jsonResult =  new JsonResult(2,"操作失败","",false);
                return jsonResult;
            }
            String q =  map.get("success").toString();
            if(map.get("success").toString().equals("false")){
                jsonResult =  new JsonResult(2,map.get("message").toString(),"",false);
            }else{
                jsonResult =  new JsonResult(1,map.get("message").toString(),"",true);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(2,"系统异常！操作失败","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("删除购物车:"+result);
        return jsonResult;
    }

//    /**
//     * 修改购物车商品型号
//     */
//    @RequestMapping("/updateGoodsId")
//    @ResponseBody
//    public JsonResult updateCount( @RequestParam(name="cartId",required=true)@Validated @NotNull Long cartId,
//                                   @RequestParam(name="goodsId",required=true)@Validated @NotNull Long goodsId){
//        JsonResult jsonResult = null;
//        try {
//            Map<String,Object> map = shoppingCartService.updateGoodsId(cartId,goodsId);
//            if(map==null){
//                jsonResult =  new JsonResult(2,"操作失败","",false);
//                return jsonResult;
//            }
//            String q =  map.get("success").toString();
//            if(map.get("success").toString().equals("false")){
//                jsonResult =  new JsonResult(2,map.get("message").toString(),"",false);
//            }else{
//                jsonResult =  new JsonResult(1,map.get("message").toString(),"",true);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            jsonResult =  new JsonResult(2,"系统异常！操作失败","",false);
//        }
//        String result = JsonUtil.getJson(jsonResult);
//        System.out.println("删除购物车:"+result);
//        return jsonResult;
//    }




}
