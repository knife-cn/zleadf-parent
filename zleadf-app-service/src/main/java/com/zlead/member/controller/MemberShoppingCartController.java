package com.zlead.member.controller;

import com.zlead.common.PromptMsg;
import com.zlead.fplat.entity.AgentbandList;
import com.zlead.fplat.service.AgentBandListService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShoppingCartEntity;
import com.zlead.service.GoodsBgService;
import com.zlead.reception.service.ShoppingCartService;
import com.zlead.util.ObjectUtils;
import com.zlead.utils.LoginUtil;
import com.zlead.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
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
 * 购物车
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 11:04:28
 */
@Controller
@RequestMapping("/zlead/shopcart")
public class MemberShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private GoodsBgService goodsService;

    @Resource
    private LoginUtil loginUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AgentBandListService agentBandListService;
    /**
     * 查询购物车接口
     * 如果 shoppingCartGoods 方法内的代码逻辑更新了，请把此方法shoppingCartGoodsForNavi 同步更新保持一致
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
                if(list==null){
                    jsonResult =  new JsonResult(3,"没有关联工厂!","",false);
                }
                else if(list!=null&&list.size()>0){
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
        //System.out.println("获取购物车商品信息:"+result);
        return jsonResult;
    }


    /**
     * 因为前端渲染页面  首页和点击购物车调用的是同一个接口，所以会把导航redis的值覆盖掉
     * 这里复制一个方法，区别于首页调用，方便实现面包屑导航
     *
     *  redisUtil.saveNaviRedis(request,3,url);
     *
     * 如果 shoppingCartGoodsForNavi 方法内的代码逻辑更新了，请把此方法shoppingCartGoods 同步更新保持一致
     */
    @RequestMapping("/shoppingCartGoodsForNavi")
    @ResponseBody
    public JsonResult shoppingCartGoodsForNavi( HttpServletRequest request,@Validated ShoppingCartEntity shoppingCartEntity,
                                         @RequestParam(name="prodType",required=false,defaultValue = "0")@Validated @NotNull Integer prodType){
        JsonResult jsonResult = null;
        try {
            //获取当前登录的用户信息
            MemberEntity member = loginUtil.getLoginMember(request);
            if(member!=null){
                List<Map<String, Object>> list = shoppingCartService.getShoppingCart(member,shoppingCartEntity,prodType,request);
                if(list==null){
                    jsonResult =  new JsonResult(3,"没有关联工厂!","",false);
                }
                else if(list!=null&&list.size()>0){
                    try {
                        String url = request.getHeader("Referer");
                        redisUtil.saveNaviRedis(request,3,url);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

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
        //System.out.println("获取购物车商品信息:"+result);
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
                GoodsEntity goodsEntity = goodsService.findById(shoppingCartEntity.getGoodsId());
                //当前商品为普通商品
                if (goodsEntity.getProdType() ==0 ){
                    //判断当前商品是否有代理权限
                    AgentbandList agentbandList = agentBandListService.queryAgencyList(member.getAgentId().intValue(), null, goodsEntity.getListId().intValue());
                    if (agentbandList == null ){
                        jsonResult =  new JsonResult(5,PromptMsg.GOODS_AUTHORITY,"",true);
                        return jsonResult;
                    }
                }

                /**
                 * 上架库存:0-空、1-自定义  2-实际库存
                 * 同步商品库存数
                 */
                Integer realStock=0;
                if(null!=goodsEntity && goodsEntity.getId()>0 && goodsEntity.getStockType().equals(1)){
                	realStock=goodsService.queryGoodsNewestStock(goodsEntity);

                	if(null !=realStock)
                		goodsEntity.setStock(realStock);
                }
                
                if(goodsEntity!=null){
                    if(goodsEntity.getIsMarket() != 1){//判断是否下架
                        return new JsonResult(2, PromptMsg.shoppingGOODS_MARKET,"",false);
                    }
                    //当添加购物车的时候判断，当前用户在购物车中是否已存在商品，如果已经存在此商品那么一起进行判断
                    ShoppingCartEntity beforeAddShoppingCart = shoppingCartService.shopingCartInfo(member.getId(), goodsEntity.getId());
                    int count = 0;
                    if (ObjectUtils.isNotEmpty(beforeAddShoppingCart)){
                        count = beforeAddShoppingCart.getCount();
                    }

                    if(goodsEntity.getStock()!=null&&goodsEntity.getStock()>=shoppingCartEntity.getCount()+count){
                        shoppingCartEntity.setShopId(goodsEntity.getShopId());
                        String successType = shoppingCartService.shoppingCartSave(shoppingCartEntity,member);
                        if(successType.equals("1")){
                            jsonResult =  new JsonResult(1,"添加购物车成功！","",true);
                        }else{
                            jsonResult =  new JsonResult(2,"系统异常！添加购物车失败。","",false);
                        }
                    }else{
                        jsonResult =  new JsonResult(2,PromptMsg.shoppingGOODS_STOCK,"",false);
                    }
                }else{
                    jsonResult =  new JsonResult(2,PromptMsg.shoppingGOODS_MARKET,"",false);
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
                                    @RequestParam(name="count",required=false) Integer count){
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

    /**
     * 修改购物车商品型号
     */
    @RequestMapping("/updateGoodsId")
    @ResponseBody
    public JsonResult updateGoodsId( @RequestParam(name="cartId",required=true)@Validated @NotNull Long cartId,
                                   @RequestParam(name="goodsId",required=true)@Validated @NotNull Long goodsId,
                                   @RequestParam(name="otherIds",required=true)@Validated @NotNull List<Long> otherIds){
        JsonResult jsonResult = null;
        try {
            Map<String,Object> map = shoppingCartService.updateGoodsId(cartId,goodsId,otherIds);
            if(map==null){
                jsonResult =  new JsonResult(2,"操作失败","",false);
                return jsonResult;
            }
            String q =  map.get("success").toString();
            if(map.get("success").toString().equals("3")){
                jsonResult =  new JsonResult(3,map.get("message").toString(),"",false);
            }else if (map.get("success").toString().equals("2")){
                jsonResult =  new JsonResult(2,map.get("message").toString(),"",true);
            }else {
                jsonResult =  new JsonResult(1,map.get("message").toString(),"",true);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult =  new JsonResult(3,"系统异常！操作失败","",false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("修改购物车商品:"+result);
        return jsonResult;
    }
}
