package com.zlead.member.controller;


import com.alibaba.fastjson.JSON;
import com.zlead.common.PromptMsg;
import com.zlead.entity.*;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.fplat.entity.OaFactoryInfo;
import com.zlead.fplat.service.OaFactoryInfoService;
import com.zlead.reception.service.GoodsService;
import com.zlead.reception.service.MemberService;
import com.zlead.reception.service.OrderGoodsService;
import com.zlead.service.GoodsAttrValService;
import com.zlead.service.GoodsBgService;
import com.zlead.service.OperatorlogService;
import com.zlead.service.OrderBgService;
import com.zlead.util.JsonResult;
import com.zlead.util.JsonUtil;
import com.zlead.entity.vo.OrderVo;
import com.zlead.reception.service.OrderService;
import com.zlead.util.StrTools;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.LoginUtil;
import com.zlead.util.page.Page;
import com.zlead.util.page.PageList;

import com.zlead.utils.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.*;


/**
 * 订单
 *
 * @author ytchen
 * @date 2019-01-23 14:30:49
 */
@Controller
@RequestMapping("/zlead/order")
public class MemberOrderMgrController {
    @Resource
    private OrderService orderService;
    @Resource
    private OrderGoodsService orderGoodsService;
    @Resource
    private OrderBgService orderbgservice;
    @Resource
    private GoodsAttrValService goodsAttrValService;
    @Autowired
    private MemberService memberService;
    @Resource
    private LoginUtil loginUtil;
    @Resource
    private GoodsBgService goodsBgService;
    @Resource
    private OaFactoryInfoService oaFactoryInfoService;  //工厂接口

    @Autowired
    private RedisUtil redisUtil;

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
    public JsonResult newConfirmOrder(@RequestParam(value = "goodsId", required = false) @Validated @NotNull String goodsId,
                                      @RequestParam(value = "buyNum") @Validated @NotNull String buyNum,
                                      HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        if (goodsId == null) {
            jsonResult = new JsonResult(2, PromptMsg.GOODS_MARKET, false);
            return jsonResult;
        }
        Map<String, Object> result = orderService.confirmOrder(goodsId, buyNum, memberEntity);
        if (result != null && result.size() > 0) {
            boolean isNotMarket = false;
            if (result.get("isNotMarket") != null && (boolean) result.get("isNotMarket") == false) {
                isNotMarket = true;
            }
            if (isNotMarket) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (result.get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            } else if (result.get("agentcyScope") != null){
                jsonResult = new JsonResult(5, PromptMsg.GOODS_AUTHORITY, "", false);
            } else {
                jsonResult = new JsonResult(1, "提交订单成功", result, true);
            }
        } else {
            jsonResult = new JsonResult(2, "提交订单失败", false);
        }
        return jsonResult;
    }

    /**
     * 立即购买
     */
    @RequestMapping("newConfirmActOrder")
    @ResponseBody
    public JsonResult newConfirmActOrder(@RequestParam(value = "goodsId", required = false) @Validated @NotNull String goodsId,
                                         @RequestParam(value = "buyNum") @Validated @NotNull String buyNum,
                                         @RequestParam(value = "actId") @Validated @NotNull Long actId,
                                         HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        if (goodsId == null) {
            jsonResult = new JsonResult(2, "此商品参数为空!请联系管理员查询问题", false);
            return jsonResult;
        }//actId
        Map<String, Object> result = orderService.confirmActOrder(actId, goodsId, buyNum, memberEntity);
        if (result != null && result.size() > 0) {
            if (result.get("isNotMarket") != null) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (result.get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            } else {
                jsonResult = new JsonResult(1, "提交订单成功", result, true);
            }
        } else {
            jsonResult = new JsonResult(2, "提交订单失败", false);
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
        //查询用户是否有此商品的权限
        Map<String, Object> result = orderService.addOrder(orderEntity, addressId, buyNum, goodsId, memberEntity, request);
        if (result != null && result.size() > 0) {
            if (result.get("isNotMarket") != null) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (result.get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            }else if (result.get("agentcyScope") != null){
                jsonResult = new JsonResult(5, PromptMsg.GOODS_AUTHORITY, "", false);
            }else {
                jsonResult = new JsonResult(1, "提交订单成功", result, true);
            }
        } else {
            jsonResult = new JsonResult(2, "", false);
        }
        return jsonResult;
    }


    /**
     * 提交活动订单
     */
    @RequestMapping("addActOrder")
    @ResponseBody
    public JsonResult addActOrder(@Validated OrderEntity orderEntity,
                                  @RequestParam(value = "addressId") @Validated @NotNull Long addressId,
                                  @RequestParam(value = "buyNum") @Validated @NotNull Integer buyNum,
                                  @RequestParam(value = "goodsId") @Validated @NotNull Long goodsId,
                                  @RequestParam(value = "actId") @Validated @NotNull Long actId,
                                  HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        Map<String, Object> result = orderService.addActOrder(actId, orderEntity, addressId, buyNum, goodsId, memberEntity, request);
        if (result != null && result.size() > 0) {
            if (result.get("isNotMarket") != null) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (result.get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            } else {
                jsonResult = new JsonResult(1, "提交订单成功", result, true);
            }
        } else {
            jsonResult = new JsonResult(2, "", false);
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
            if (list.get(list.size() - 1).get("isNotMarket") != null) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (list.get(list.size() - 1).get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            }else if (list.get(list.size() - 1).get("agentcyScope") != null){
                jsonResult = new JsonResult(5, PromptMsg.GOODS_AUTHORITY, "", false);
            } else{
                jsonResult = new JsonResult(1, "购物车立即购买成功", list, true);
            }
        } else {
            jsonResult = new JsonResult(2, "购物车立即购买失败", false);
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
            if (result.get("isNotMarket") != null) {
                jsonResult = new JsonResult(3, PromptMsg.GOODS_MARKET, "", false);
            } else if (result.get("sotckCheck") != null) {
                jsonResult = new JsonResult(4, PromptMsg.GOODS_STOCK, "", false);
            }else if (result.get("agentcyScope") != null){
                jsonResult = new JsonResult(5, PromptMsg.GOODS_AUTHORITY, "", false);
            } else{
                jsonResult = new JsonResult(1, "提交订单成功", result, true);
            }
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
        return jsonResult;
    }

    /**
     * 订单支付
     */
    @RequestMapping("payOrder")
    @ResponseBody
    public JsonResult payOrder(@RequestParam(value = "orderId") @Validated @NotNull Long orderId, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            boolean b = orderService.payOrder(orderId, request);
            if (b) {
                jsonResult = new JsonResult(1, "订单线下付款成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "订单线下付款失败", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "系统异常", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
        return jsonResult;
    }

    /**
     * 订单收货
     */
    @RequestMapping("recevOrder")
    @ResponseBody
    public JsonResult recevOrder(@RequestParam(value = "orderId") @Validated @NotNull Long orderId, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }
        try {
            boolean b = orderService.recevOrder(orderId, memberEntity);
            if (b) {
                jsonResult = new JsonResult(1, "订单收货成功", "", true);
            } else {
                jsonResult = new JsonResult(2, "订单收货失败", "", false);
            }
        } catch (Exception e) {
            // TODO: handle exception
            jsonResult = new JsonResult(2, "系统异常", "", false);
        }
        String result = JsonUtil.getJson(jsonResult);
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
    public JsonResult getOrderInfoCus(HttpServletRequest request, int pageNum, @RequestParam(value = "status") @Validated Integer status) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        Map<String, Object> map = new HashMap<String, Object>();
        int pageSize = 10;

        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            //List<OrderEntity> orderEntity = orderService.getOrderInfoCus(memberEntity.getId());
            int ids = orderService.findSize(memberEntity.getId(), status);
            List<OrderEntity> orderEntity = orderService.getOrderInfoCus(memberEntity.getId(), new PageBounds(pageNum, pageSize), status);

            //List<OrderEntity> orderEntity = orderService.findPage(memberEntity.getId(),params,new PageBounds(pageNum,pageSize));
            System.out.println(ids);
            map.put("ids", ids);
            map.put("orderEntity", orderEntity);
            if (orderEntity != null) {
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
     * 订单查询
     * ytchen
     * 2019-1-17
     */
    @ResponseBody
    @RequestMapping(value = "/getOrderInfoSta")
    public JsonResult getOrderInfoSta(HttpServletRequest request, @RequestParam(value = "pageNum") @Validated Integer pageNum,
                                      @RequestParam(value = "pageSize") @Validated Integer pageSize, @RequestParam(value = "status") @Validated Integer status) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            List<OrderEntity> orderEntity = orderService.getOrderInfoCus(memberEntity.getId(), new PageBounds(pageNum, pageSize), status);//orderService.findOrderList(memberEntity.getId());
//            List<OrderEntity> orderEntity = orderService.getOrderInfoSta(memberEntity.getId(),status);
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
            if (orderId != null) {
                Map<String, Object> map = orderService.getOrderInfoId(request,orderId);
                if (map != null) {
                    return new JsonResult(1, "查询成功", map, true);
                } else {
                    return new JsonResult(2, "订单为空", false);
                }
            } else {
                return new JsonResult(2, "订单为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }


    /**
     * 订单购买详情
     * ytchen
     * 2019-1-29
     */
    @ResponseBody
    @RequestMapping(value = "/getorderchase")
    public JsonResult getorderchase(HttpServletRequest request, Long orderId) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            Map<String, Object> map = orderService.getOrderInfoId(request,orderId);
            if (map != null) {
                return new JsonResult(1, "查询订单购买详情成功", map, true);
            } else {
                return new JsonResult(2, "查询订单购买详情为空", false);
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }

    /**
     * 查询会员的无凭证的订单
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
     * 查询代理商所有无凭证的订单
     *
     * @param
     */
    @RequestMapping("getAllnoVoucherOrderList")
    @ResponseBody
    public JsonResult getAllnoVoucherOrderList(PageBounds rowBounds, HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(2, "未登录", false);
            return jsonResult;
        }
        Long agentId = memberEntity.getAgentId();
        if (agentId == null) {
            jsonResult = new JsonResult(2, "用户是否代理用户?请联系管理员", false);
            return jsonResult;
        }
        List<Long> memberList = memberService.getByAgentId(agentId.intValue());
        String memberIds = "";
        for (int i = 0; i < memberList.size(); i++) {
            memberIds = memberIds + memberList.get(i) + ",";
        }
        memberIds = memberIds.substring(0, memberIds.length() - 1);
        PageList<OrderEntity> list = orderService.getAllnoVoucherOrderList(memberIds, rowBounds);
        if (list != null && list.size() > 0) {
            Page page = new Page<OrderEntity>(list, list.getPagination());
            jsonResult = new JsonResult(1, "列表信息", page, true);
        } else {
            jsonResult = new JsonResult(2, "无列表信息", false);
        }
        return jsonResult;
    }


    /**
     * 订单查询  状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消)
     * ytchen
     * 2019-01-23
     */
    @ResponseBody
    @RequestMapping(value = "/findPageZero")
    public JsonResult findPageZero(HttpServletRequest request, Integer pageNum, @RequestParam(name = "status", required = false) @Validated Integer status) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        Map<String, Object> map = new HashMap<String, Object>();
        int pageSize = 10;
        if (status == null || status > 5) {
            status = null;
            System.out.println("findPageZero 1  status: " + status);
        }
        if (memberEntity != null) {
            HashMap params = new HashMap();
            params.put("memberId", memberEntity.getId());
            System.out.println("findPageZero 2  status: " + status);
            if (pageNum == null) pageNum = 1;
            List<OrderEntity> orderlist = orderService.findPageSt2(status, memberEntity.getId(), params, new PageBounds(pageNum, pageSize));
            if (CollectionUtils.isNotEmpty(orderlist)){
                for (OrderEntity order : orderlist) {
                    //得到工厂名字
                    OaFactoryInfo facByShopId = oaFactoryInfoService.findFacByShopId(order.getShopId().intValue());
                    //订单商品
                    List<OrderGoodsEntity> orderGoods = order.getOrderGoods();
                    if (null != orderGoods && orderGoods.size() > 0) {
                        try {
                            String url = request.getHeader("Referer");
                            redisUtil.saveNaviRedis(request, 1,url);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        for (OrderGoodsEntity orderGood : orderGoods) {
                            StringBuffer stringBuffer = new StringBuffer();
                            Long goodsId = orderGood.getGoodsId();
                            Long agentId = memberEntity.getAgentId();
                            if (goodsId != null && goodsId != 0) {
                                //获取品牌名，商品名，商品型号，商品系列 2426 804
                                Map<String, Object> maps = goodsBgService.goodsFull(goodsId, agentId);
                                String bandName = "";
                                String listDesc = "";
                                String modelName = "";
                                if (null != maps) {
                                    if (maps.get("bandName") != null)
                                        bandName = maps.get("bandName").toString();//品牌名
                                    if (maps.get("listDesc") != null)
                                        listDesc = maps.get("listDesc").toString();//系列名
                                    if (maps.get("modelName") != null)
                                        modelName = maps.get("modelName").toString();//型号名
                                }
                                //拼接商品参数
                                List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValService.queryByGdsId(goodsId.intValue());
                                for (GoodsAttrValDto byGdsId : goodsAttrValDtos) {
                                    String attrValue = byGdsId.getAttrValue();
                                    stringBuffer.append(attrValue + " ");
                                }//商品名，品牌名，系列名，型号名，参数名
                                orderGood.setGoodsName(bandName + " " + listDesc + " " + modelName + " " + orderGood.getGoodsName() + " " + stringBuffer.toString());
                                //设置属性值
                                orderGood.setGoodsAttriValue(stringBuffer.toString());
                            }
                            if (orderGood.getGoodsImg() == null || "".equals(orderGood.getGoodsImg())){
                                orderGood.setGoodsImg(StrTools.localImagesPath(request,"/shopping/img/index/sl3.png"));
                            }
                        }
                    }
                    //设置工厂名字
                    order.setShopName(facByShopId.getFactName());
                }
            }
            int ids = orderService.findSize(memberEntity.getId(), status);
            int total = orderService.findTotalOrderSize(memberEntity.getId());
            int count0 = orderService.findSize(memberEntity.getId(), 0);
            int count1 = orderService.findSize(memberEntity.getId(), 1);
            int count2 = orderService.findSize(memberEntity.getId(), 2);
            int count3 = orderService.findSize(memberEntity.getId(), 3);
            System.out.println(ids);
            List<Integer> statusList = new ArrayList<>();
            statusList.add(total);
            statusList.add(count0);
            statusList.add(count1);
            statusList.add(count2);
            statusList.add(count3);
            map.put("ids", ids);
            map.put("status", statusList);
            map.put("OrderEntity", orderlist);
            if (orderlist != null) {
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
     * 确认收货
     * ytchen
     * 2019-1-29
     */
    @ResponseBody
    @RequestMapping(value = "/orderovid", method = RequestMethod.POST)
    public JsonResult orderovid(HttpServletRequest request, Long orderId) {
        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getLoginMember(request);
        if (memberEntity != null) {
            OrderEntity order = orderService.findById(orderId);
            //判断商品是部分出库还是完全出库    部分出库 0 ，完全出库 1
           // order.setIsExpire(order.getIsExpire() == null ? 0 : order.getIsExpire());
            if (null!=order && 1 == order.getIsExpire()) {
                boolean bule = orderbgservice.orderovid(orderId, memberEntity, request);
                if (bule) {
                    return new JsonResult(1, "订单已完成", bule, true);
                } else {
                    return new JsonResult(2, "订单收货失败", false);
                }
            } else {
                jsonResult = new JsonResult(2, "商品没有完全出库", "", false);
                return jsonResult;
            }
        } else {
            jsonResult = new JsonResult(2, "用户未登录", "", false);
            return jsonResult;
        }

    }


}


