package com.zlead.shopmgr.controller;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.OrderGoodsEntity;
import com.zlead.shopmgr.service.FactoryOrderService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.Page;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zlead/shopmgr")
public class FactoryOrderMgrController {
    @Resource
    FactoryOrderService orderService;

    @Resource
    private LoginUtil loginUtil;

    @RequestMapping(value = "/shopOrderList")
    @ResponseBody
    public JsonResult shopOrderList(@RequestParam(value = "status",required = false) @Validated @NotNull String status,
                                    @RequestParam(value = "sn",required = false) @Validated @NotNull String sn,
                                    @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                    @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }else{
            PageList<OrderEntity> orderList = orderService.shopOrderList(status,sn, Integer.parseInt(pageNum), Integer.parseInt(pageSize), member);
            if (orderList != null && orderList.size() > 0) {
                Page<OrderEntity> page = new Page<>(orderList, orderList.getPagination());
                jsonResult = new JsonResult(1, "列表信息", page, true);
            } else {
                jsonResult = new JsonResult(2, "无列表信息", false);
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/orderInfo")
    @ResponseBody
    public JsonResult shopOrderDetailsList(@RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                           @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                           @RequestParam(value = "orderSn") @Validated @NotNull String orderSn,
                                           HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(2, "未登录", false);
        }else{
            PageList<OrderGoodsEntity> orderGoodsList = orderService.shopOrderDetailsList(Integer.parseInt(pageNum), Integer.parseInt(pageSize), orderSn,member.getOwnShopid());
            if (orderGoodsList != null && orderGoodsList.size() > 0) {
                Page<OrderGoodsEntity> page = new Page<>(orderGoodsList, orderGoodsList.getPagination());
                jsonResult = new JsonResult(1, "列表信息", page, true);
            } else {
                jsonResult = new JsonResult(2, "无列表信息", false);
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/orderTotalAmount")
    @ResponseBody
    public JsonResult shopOrderTotalAmount(HttpServletRequest request) {
        JsonResult jsonResult = null;
        Map<String,Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            BigDecimal totalAmount = orderService.shopOrderTotalAmount(member.getOwnShopid());
            params.put("totalAmount", totalAmount);
            jsonResult = new JsonResult(1, "列表信息", params, true);
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/orderTotalCount")
    @ResponseBody
    public JsonResult shopOrderTotalCount(HttpServletRequest request) {
        JsonResult jsonResult = null;
        Map<String,Object> params = new HashMap<>();
        MemberEntity member = loginUtil.getLoginMember(request);
        if (member != null && member.getOwnShopid() != null && loginUtil.isShop(member)) {
            Long orderTotalCount = orderService.shopOrderTotalCount(member.getOwnShopid());
            params.put("orderTotalCount", orderTotalCount);
            jsonResult = new JsonResult(1, "列表信息", params, true);
        } else {
            jsonResult = new JsonResult(2, "信息获取失败", false);
        }
        return jsonResult;
    }
}
