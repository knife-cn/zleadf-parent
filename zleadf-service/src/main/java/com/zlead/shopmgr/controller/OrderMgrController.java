package com.zlead.shopmgr.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.reception.service.OrderGoodsService;
import com.zlead.reception.service.OrderService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.util.page.Page;
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

import java.util.List;
import java.util.Map;


/**
 * 订单
 *
 * @author fqf
 * @date 2018-08-01 10:30:49
 */
@Controller
@RequestMapping("/zlead/shopmgr")
public class OrderMgrController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private LoginUtil loginUtil;

    /**
     * 指定工厂商铺下的订单列表
     *
     * @param status    订单状态
     * @param pageNum   分页（第几页）
     * @param size      分页（每页显示条数）
     * @param orderType 订单类型
     * @param shopId    工厂商铺Id
     * @param request
     * @return
     */
   /* @RequestMapping(value = "/shopOrderList", method= RequestMethod.POST)
    @ResponseBody
    public JsonResult shopOrderList(@RequestParam(value = "status") @Validated @NotNull String status,
                                    @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                    @RequestParam(value = "size") @Validated @NotNull String size,
                                    @RequestParam(value = "orderType") @Validated @NotNull Integer orderType,
                                    @RequestParam(value = "shopId") Long shopId,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }
        PageList<OrderVo> orderVos = orderService.commonGoodsOrder(status, Integer.parseInt(pageNum), Integer.parseInt(size), memberEntity, orderType, shopId);
        if (orderVos != null && orderVos.size() > 0 && memberEntity.getOwnShopid().equals(shopId) && loginUtil.isShop(memberEntity)) {
            Page<OrderVo> page = new Page<>(orderVos, orderVos.getPagination());
            jsonResult = new JsonResult(1, "列表信息", page, true);
        } else {
            jsonResult = new JsonResult(2, "无列表信息", false);
        }
        return jsonResult;
    }
*/
    /**
     * 订单详情
     *
     * @param orderSn:订单号 status:订单状态
     *//*
    @RequestMapping("orderInfo")
    @ResponseBody
    public JsonResult orderInfo(@RequestParam(value = "orderSn", required = false) @Validated @NotNull String orderSn,
                                HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        OrderEntity order = orderService.findBySn(orderSn);
        if (order != null) {
            jsonResult = new JsonResult(1, "订单信息", order, true);
        } else {
            jsonResult = new JsonResult(2, "无订单信息", false);
        }
        return jsonResult;

    }*/

//    /**
//     * 立即购买
//     * */
//    @RequestMapping("newConfirmOrder")
//    @ResponseBody
//    public JsonResult newConfirmOrder(@RequestParam(value="goodsId") @Validated @NotNull String goodsId,
//                                      @RequestParam(value="buyNum") @Validated @NotNull String buyNum,
//                                      HttpServletRequest request){
//        JsonResult jsonResult = null;
//        MemberEntity memberEntity = loginUtil.getLoginMember(request);
//        if(memberEntity==null){
//            jsonResult = new JsonResult(2, "未登录", false);
//            return jsonResult;
//        }
//        Map<String, Object> map = orderService.confirmOrder(goodsId, buyNum, memberEntity);
//        String sotckCheck = "";
//        if(map!=null)sotckCheck = map.get("sotckCheck").toString();
//        if(map!=null && map.size()>0 && "true".equals(sotckCheck)){
//            jsonResult = new JsonResult(1, "立即购买成功", map, true);
//        }else if("false".equals(sotckCheck)){
//            jsonResult = new JsonResult(2, "库存不足", false);
//        }else{
//            jsonResult = new JsonResult(2, "立即购买失败", false);
//        }
//        return jsonResult;
//    }
//
//    /**
//     * 提交订单
//     * */
//    @RequestMapping("addOrder")
//    @ResponseBody
//    public JsonResult addOrder(@Validated OrderEntity orderEntity,
//                                                    @RequestParam(value="addressId") @Validated @NotNull Long addressId,
//                                                    @RequestParam(value="buyNum") @Validated @NotNull Integer buyNum,
//                                                    @RequestParam(value="goodsId") @Validated @NotNull Long goodsId,
//                                                    HttpServletRequest request){
//        JsonResult jsonResult = null;
//        MemberEntity memberEntity = loginUtil.getLoginMember(request);
//        if(memberEntity==null){
//            jsonResult = new JsonResult(2, "用户未登录", "", false);
//            return jsonResult;
//        }
//        Map<String, Object> result = orderService.addOrder(orderEntity,addressId,buyNum,goodsId,memberEntity);
//        if(result!=null && result.size()>0){
//            jsonResult = new JsonResult(1, "提交订单成功", result, true);
//        }else{
//            jsonResult = new JsonResult(2, "提交订单失败", false);
//        }
//        return jsonResult;
//    }
//
//    /**
//     * 购物车立即购买（新）
//     * */
//    @RequestMapping("cartComfirmOrder")
//    @ResponseBody
//    public JsonResult cartComfirmOrder(
//                                       @RequestParam(value="id") @Validated @NotNull List<Integer> cartList,
//                                       HttpServletRequest request){
//        //System.out.println("goodsIdList: "+goodsIdList+"  buyNumList: "+buyNumList);
//        //Map<String, Object> map = orderService.newConfirmOrder3(pointType,goodsIdList, buyNumList, request);
//        JsonResult jsonResult = null;
//        MemberEntity memberEntity = loginUtil.getLoginMember(request);
//        if(memberEntity==null){
//            jsonResult = new JsonResult(2, "用户未登录", "", false);
//            return jsonResult;
//        }
//        List<Map<String, Object>> list = orderService.cartConfirmOrder(memberEntity,cartList);
//        if(list!=null && list.size()>0){
//            jsonResult = new JsonResult(1, "购物车立即购买成功", list, true);
//        }else{
//            jsonResult = new JsonResult(2, "失败", false);
//        }
//        return jsonResult;
//    }
//

//    /**
//     * 购物车提交订单
//     * */
//    @RequestMapping("cartAddOrder")
//    @ResponseBody
//    public JsonResult cartAddOrder(@Validated OrderEntity orderEntity,
//                               @RequestParam(value="addressId") @Validated @NotNull Long addressId,
//                               @RequestParam(value="cartIds") @Validated @NotNull List<Integer> cartIds,
//                               HttpServletRequest request){
//        JsonResult jsonResult = null;
//        MemberEntity memberEntity = loginUtil.getLoginMember(request);
//        if(memberEntity==null){
//            jsonResult = new JsonResult(2, "用户未登录", "", false);
//            return jsonResult;
//        }
//        Map<String, Object> result = orderService.cartAddOrder(orderEntity,cartIds,addressId,memberEntity);
//        if(result!=null && result.size()>0){
//            jsonResult = new JsonResult(1, "提交订单成功", result, true);
//        }else{
//            jsonResult = new JsonResult(2, "提交订单失败", false);
//        }
//        return jsonResult;
//    }

    /**
     * 取消订单
     */
    @RequestMapping("cancelOrder")
    @ResponseBody
    public JsonResult cancelOrder(@RequestParam(value = "orderId") @Validated @NotNull Long orderId, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            boolean b = orderService.cancelOrder(orderId, memberEntity);
            if (b) {
                jsonResult = new JsonResult(1, "取消订单成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "取消订单失败", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "系统异常", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("取消订单:" + result);
        return jsonResult;
    }

    /**
     * 销售订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sellOrder")
    public JsonResult sellOrder(HttpServletRequest request, @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                @RequestParam(value = "size") @Validated @NotNull String size, @Validated @NotNull String status) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        PageList<OrderEntity> list = orderService.sellOrderList(memberEntity.getId(), Integer.parseInt(pageNum), Integer.parseInt(size), Integer.parseInt(status));

        if (list != null && list.size() > 0) {

            Page page = new Page<OrderEntity>(list, list.getPagination());
            return new JsonResult(1, "列表信息", page, true);

        } else {
            return new JsonResult(2, "无列表信息", false);
        }
    }

    /**
     * 订单发货
     */

    @ResponseBody
    @RequestMapping(value = "/sendGoods")
    public JsonResult sendGoods(HttpServletRequest request, @RequestParam(value = "orderId") @Validated @NotNull Long orderId) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        OrderEntity orderEntity = orderService.findById(orderId);
        Boolean b = orderService.sendGoodsOrder(orderEntity);
        if (b) {
            return new JsonResult(1, "发货成功", true);
        } else {
            return new JsonResult(2, "发货失败", true);
        }
    }
//    @ResponseBody
//    @RequestMapping(value = "/paySuccess")
//    public JsonResult paySuccess(HttpServletRequest request,String out_trade_no){
//        JsonResult jsonResult = null;
//        MemberEntity memberEntity = loginUtil.getLoginMember(request);
//        if(memberEntity==null){
//            jsonResult = new JsonResult(2, "用户未登录", "", false);
//            return jsonResult;
//        }
//       orderService.paySuccess(out_trade_no);
//         jsonResult = new JsonResult(1, "修改成功", "", false);
//        return jsonResult;
//    }

    /**
     * 订单查询
     * 
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfoCus")
    public JsonResult getOrderInfoCus(HttpServletRequest request, @RequestParam(value = "pageNum") @Validated  Integer pageNum,
            @RequestParam(value = "size") @Validated Integer size, @RequestParam(value = "status") @Validated Integer status) {
        JsonResult jsonResult = null;
        if(size==null || size<1) size=10;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        PageBounds pageBounds=new PageBounds(pageNum,size);
        if (memberEntity != null) {
        	// 卖家根据shop_id
        	Integer intstatus=null;
        	if(status!=null && status>=0)
        	intstatus=Integer.valueOf(status);
        	if(memberEntity.getOwnShopid()==null){
        		System.out.println(" getShopOrderlist: shopid is null ,memberId: "+memberEntity.getId());
        		return new JsonResult(2, "您所属的店铺信息有误", false);
        	}
            List<OrderEntity> orderEntity = orderService.getShopOrderList(memberEntity.getOwnShopid(),pageBounds,intstatus);
            if (orderEntity != null) {
                return new JsonResult(1, "查询成功", orderEntity, true);
            } else {
                return new JsonResult(2, "订单为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }

    /**
     * 删除订单 逻辑删除
     * ytchen
     * 2019-1-17
     */
    @RequestMapping("updateorder")
    @ResponseBody
    public JsonResult updateorder(@RequestParam(value = "orderId") @Validated @NotNull Long orderId, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            int b = orderService.deleteorder(orderId);
            if (b != 0) {
                jsonResult = new JsonResult(1, "删除订单成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "删除订单失败", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "系统异常", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        System.out.println("删除订单:" + result);
        return jsonResult;
    }

    /**
     * 订单详情查询
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfodetails")
    public JsonResult getOrderInfodetails(HttpServletRequest request, Long orderId) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            Map<String, Object> map = orderService.getOrderInfoId(request,orderId);
            if (map != null) {
                return new JsonResult(1, "查询成功", map, true);
            } else {
                return new JsonResult(2, "订单为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }

    /**
     * 立即付款，选择支付方式,默认为1，线下支付
     */
    @ResponseBody
    @RequestMapping(value = "/payType")
    public JsonResult payType(HttpServletRequest request, @RequestParam(value = "orderId") Long orderId,
                              @RequestParam(value = "payType",required = false,defaultValue = "1") Integer payType) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            try {
                orderService.payType(orderId,payType);
                return new JsonResult(1, "成功", true);
            }catch (Exception e){
                return new JsonResult(2, "失败", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }


}
