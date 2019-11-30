package com.zlead.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.entity.vo.OrderVo;
import com.zlead.reception.service.OrderGoodsService;
import com.zlead.reception.service.OrderService;
import com.zlead.service.OperatorlogService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 订单--老版本-暂不用
 *
 * @author fqf
 * @date 2018-08-09 15:20:52
 */
@Controller
@RequestMapping("/zlead/torder")
public class OrderBgController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private OperatorlogService operatorlogService;

    @Resource
    private LoginUtil loginUtil;

    /**
     * 商城订单列表
     *
     * @param isPointOrder:是否积分订单 true/false status:订单状态
     */
    @RequestMapping("shopOrderList")
    @ResponseBody
    public JsonResult shopOrderList(@RequestParam(value = "isPointOrder") @Validated @NotNull String isPointOrder,
                                    @RequestParam(value = "status") @Validated @NotNull String status,
                                    @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                    @RequestParam(value = "size") @Validated @NotNull String size,
                                    @RequestParam(value = "orderType") @Validated @NotNull Integer orderType,
                                    @RequestParam(value = "shopId") Long shopId,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        PageList<OrderVo> list = orderService.shopOrderList(Boolean.valueOf(isPointOrder), status, Integer.parseInt(pageNum),
                Integer.parseInt(size), memberEntity, orderType, shopId);
        if (list != null && list.size() > 0) {
            Page page = new Page<OrderVo>(list, list.getPagination());
            jsonResult = new JsonResult(1, "列表信息", page, true);
        } else {
            jsonResult = new JsonResult(2, "无列表信息", false);
        }
        return jsonResult;
    }

    /**
     * 订单详情
     *
     * @param orderSn:订单号 status:订单状态
     */
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

    }

    /**
     * 立即购买
     */
    @RequestMapping("newConfirmOrder")
    @ResponseBody
    public JsonResult newConfirmOrder(@RequestParam(value = "goodsId") @Validated @NotNull String goodsId,
                                      @RequestParam(value = "buyNum") @Validated @NotNull String buyNum,
                                      HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        Map<String, Object> map = orderService.confirmOrder(goodsId, buyNum, memberEntity);
        String sotckCheck = "";
        if (map != null) sotckCheck = map.get("sotckCheck").toString();
        if (map != null && map.size() > 0 && "true".equals(sotckCheck)) {
            jsonResult = new JsonResult(1, "立即购买成功", map, true);
        } else if ("false".equals(sotckCheck)) {
            String msg = map.get("sotckCheckmsg").toString();
            if (msg == null) msg = "商品库存不足,请更改购买数量后再进行结算";
            jsonResult = new JsonResult(2, msg, false);
        } else {
            jsonResult = new JsonResult(2, "立即购买失败", false);
        }
        return jsonResult;
    }

    /**
     * 提交订单
     */
    @RequestMapping("addOrder")
    @ResponseBody
    public JsonResult addOrder(@Validated OrderEntity orderEntity,
                               @RequestParam(value = "addressId") @Validated @NotNull Long addressId,
                               @RequestParam(value = "buyNum") @Validated @NotNull Integer buyNum,
                               @RequestParam(value = "goodsId") @Validated @NotNull Long goodsId,
                               HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        Map<String, Object> result = orderService.addOrder(orderEntity, addressId, buyNum, goodsId, memberEntity, request
        );
        //Integer integer = operatorlogService.insertOrderOperatorlog(memberEntity.getId().intValue(), memberEntity.getUsername(), orderEntity.getSn(), TOperatorLogConstant.TITLE_ORDER_CREATE);

        if (result != null && result.size() > 0) {
            jsonResult = new JsonResult(1, "提交订单成功", result, true);
        } else {
            jsonResult = new JsonResult(2, "提交订单失败", false);
        }
        return jsonResult;
    }

    /**
     * 购物车立即购买（新）
     */
    @RequestMapping("cartComfirmOrder")
    @ResponseBody
    public JsonResult cartComfirmOrder(
            @RequestParam(value = "cartIds") @Validated @NotNull List<Integer> cartList,
            HttpServletRequest request) {
        //System.out.println("goodsIdList: "+goodsIdList+"  buyNumList: "+buyNumList);
        //Map<String, Object> map = orderService.newConfirmOrder3(pointType,goodsIdList, buyNumList, request);
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        List<Map<String, Object>> list = orderService.cartConfirmOrder(memberEntity, cartList);

        if (list != null && list.size() > 0) {
            jsonResult = new JsonResult(1, "购物车立即购买成功", list, true);
        } else {
            jsonResult = new JsonResult(2, "失败", false);
        }

        return jsonResult;
    }


    /**
     * 购物车提交订单
     */
    @RequestMapping("cartAddOrder")
    @ResponseBody
    public JsonResult cartAddOrder(@Validated OrderEntity orderEntity,
                                   @RequestParam(value = "addressId") @Validated @NotNull Long addressId,
                                   @RequestParam(value = "cartIds") @Validated @NotNull List<Integer> cartIds,
                                   @RequestParam(value = "shopIds") @Validated @NotNull List<Long> shopIds,
                                   HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        Map<String, Map<String, Object>> result = orderService.cartAddOrder(orderEntity, cartIds, shopIds, addressId, memberEntity, request);
        if (result != null && result.size() > 0) {
            jsonResult = new JsonResult(1, "提交订单成功", result, true);
        } else {
            jsonResult = new JsonResult(2, "提交订单失败", false);
        }
        return jsonResult;
    }

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
    public JsonResult sendGoods(HttpServletRequest request, long id) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        OrderEntity orderEntity = orderService.findById(id);
        Boolean b = orderService.sendGoodsOrder(orderEntity);
        if (b) {
            return new JsonResult(1, "发货成功", true);
        } else {
            return new JsonResult(2, "发货失败", true);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/paySuccess")
    public JsonResult paySuccess(HttpServletRequest request, String out_trade_no) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        orderService.paySuccess(out_trade_no);
        jsonResult = new JsonResult(1, "修改成功", "", false);
        return jsonResult;
    }

    /**
     * 订单查询
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfoCus")
    public JsonResult getOrderInfoCus(HttpServletRequest request, int pageSize, int pageNum) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            //List<OrderEntity> orderEntity = orderService.getOrderInfoCus(memberEntity.getId());
            List<OrderEntity> orderEntity = orderService.getPage(params, new PageBounds(pageNum, pageSize));
            int ids = orderEntity.size();
            if (orderEntity != null) {
                return new JsonResult(ids, "查询成功", orderEntity, true);
            } else {
                return new JsonResult(2, "订单为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }

    /**
     * 订单查询
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfoSta")
    public JsonResult getOrderInfoSta(HttpServletRequest request, int status) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            List<OrderEntity> orderEntity = orderService.getOrderInfoSta(memberEntity.getId(), status);
//            List<OrderEntity> orderEntity = orderService.getOrderInfoSta(params,new PageBounds(pageNum, pageSize));
            int ids = orderEntity.size();
            if (orderEntity != null) {
                return new JsonResult(ids, "查询成功", orderEntity, true);
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
     * 无凭证的订单
     *
     * @param
     */
    @RequestMapping("noVoucherOrderList")
    @ResponseBody
    public JsonResult noVoucherOrderList(PageBounds rowBounds, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        PageList<OrderEntity> list = orderService.noVoucherOrderList(memberEntity.getId(), rowBounds);
        if (list != null && list.size() > 0) {
            Page page = new Page<OrderEntity>(list, list.getPagination());
            jsonResult = new JsonResult(1, "列表信息", page, true);
        } else {
            jsonResult = new JsonResult(2, "无列表信息", false);
        }
        return jsonResult;
    }

    /**
     * 订单查询 状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消)
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/findPageZero")
    public JsonResult findPageZero(HttpServletRequest request, int status, int pageNum) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        int pageSize = 10;
        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            //List<OrderEntity> orderEntity = orderService.getOrderInfoCus(memberEntity.getId());
            List<OrderEntity> orderEntity = orderService.findPageSt(status, memberEntity.getId(), params, new PageBounds(pageNum, pageSize));
            int ids = orderEntity.size();
            if (orderEntity != null) {
                return new JsonResult(ids, "查询成功", orderEntity, true);
            } else {
                return new JsonResult(2, "订单为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }


}
