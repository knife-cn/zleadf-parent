package com.zlead.shopmgr.api;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.dto.MarketActDto;
import com.zlead.entity.dto.OrderGoodsDto;
import com.zlead.entity.dto.VoucherDto;
import com.zlead.entity.vo.*;
import com.zlead.fplat.entity.Marketact;
import com.zlead.fplat.entity.Voucher;
import com.zlead.fplat.service.MarketagentService;
import com.zlead.fplat.service.SysMessageService;
import com.zlead.fplat.service.VoucherService;
import com.zlead.reception.service.MemberService;
import com.zlead.shopmgr.service.FactoryAgentService;
import com.zlead.shopmgr.service.FactoryOrderService;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.utils.AppUtil;
import com.zlead.utils.DateTool;
import com.zlead.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/api/shopmgr")
public class FactoryOrderAppController {
    @Resource
    FactoryOrderService orderService;

    @Resource
    FactoryAgentService agentService;

    @Resource
    private LoginUtil loginUtil;

    @Resource
    private VoucherService voucherService;

    @Resource
    private MemberService memberService;

    @Resource
    private MarketagentService marketagentService;

    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 订单列表
     *
     * @param status
     * @param sn
     * @param time
     * @param agentId
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/shopOrderList")
    @ResponseBody
    public JsonResult shopOrderList(@RequestParam(value = "status", required = false) @Validated String status,
                                    @RequestParam(value = "sn", required = false) @Validated String sn,
                                    @RequestParam(value = "time", required = false) @Validated String time,
                                    @RequestParam(value = "agentId", required = false) String agentId,
                                    @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                    @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {

                List<OrderListVo> orderList = orderService.appShopOrderList(member.getId(),status,sn, time, agentId, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
                if (orderList != null && orderList.size() > 0) {
                    jsonResult = new JsonResult(1, "列表信息", orderList, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }

    /**
     * 待办事项
     *
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/shopOrderUpcomingList")
    @ResponseBody
    public JsonResult shopOrderUpcomingList(
                                    @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                    @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                    HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                List<OrderListVo> orderList = orderService.appShopUpcomingOrderList(member.getId(),Integer.parseInt(pageNum), Integer.parseInt(pageSize));
                if (orderList != null && orderList.size() > 0) {
                    jsonResult = new JsonResult(1, "列表信息", orderList, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }


    /**
     * 订单数量统计
     *
     * @param sn
     * @param time
     * @param agentId
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderListCount")
    @ResponseBody
    public JsonResult shopOrderList(
            @RequestParam(value = "sn", required = false) @Validated String sn,
            @RequestParam(value = "time", required = false) @Validated String time,
            @RequestParam(value = "agentId", required = false) @Validated String agentId,
            HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                List<OrderCountVo> list = orderService.findOrderListCount(sn, time, agentId, member.getId());
                if (list != null && list.size() > 0) {
                    jsonResult = new JsonResult(1, "列表信息", list, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }


    /**
     * 订单总数量统计
     * <p>
     * 注意：app调用时无需传参，入参暂时保留
     *
     * @author 喻聪聪
     * @date 2019-02-27
     */
    @RequestMapping(value = "/orderListStatisticsCount")
    @ResponseBody
    public JsonResult orderListStatisticsCount(
            @RequestParam(value = "sn", required = false) @Validated String sn,
            @RequestParam(value = "time", required = false) @Validated String time,
            @RequestParam(value = "agentId", required = false) @Validated String agentId,
            HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                OrderCountStatisticsVo returnVo = orderService.findOrderListStatisticsCount(sn, time, agentId, member.getId());
                jsonResult = new JsonResult(1, "数据信息", returnVo, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }


    /**
     * 订单详情
     *
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderDetail")
    @ResponseBody
    public JsonResult appShopOrderDetail(@RequestParam(value = "orderId") @Validated @NotNull Long orderId,
                                         HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                OrderDetailVo orderGoods = orderService.appShopOrderDetails(orderId, member.getId());
                if (orderGoods != null) {
                    jsonResult = new JsonResult(1, "列表信息", orderGoods, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }

        return jsonResult;
    }

    /**
     * 订单商品详情
     *
     * @param pageNum
     * @param pageSize
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderGoodsDetail")
    @ResponseBody
    public JsonResult appShopOrderGoodsDetailsList(@RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                                   @RequestParam(value = "size") @Validated @NotNull String pageSize,
                                                   @RequestParam(value = "orderId") @Validated @NotNull Long orderId,
                                                   HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                List<OrderGoodsDetailVo> orderGoodsList = orderService.appShopOrderGoodsDetailsList(Integer.parseInt(pageNum), Integer.parseInt(pageSize), orderId);
                if (orderGoodsList != null && orderGoodsList.size() > 0) {
                    jsonResult = new JsonResult(1, "列表信息", orderGoodsList, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(2, "系统异常", false);
        }

        return jsonResult;
    }

    /**
     * 订单下的客户列表（下拉搜索框）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/orderAgentList")
    @ResponseBody
    public JsonResult appOrderAgentList(HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                List<OrderAgentMas> agentList = agentService.getAppOrderAgentList(member.getId());
                if (agentList != null && agentList.size() > 0) {
                    jsonResult = new JsonResult(1, "列表信息", agentList, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(2, "系统异常", false);
        }

        return jsonResult;
    }

    /**
     * 首页展示
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/shopIndex")
    @ResponseBody
    public JsonResult shopIndex(HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member != null) {
                ShopIndexVo indexInfo = orderService.shopIndex(member.getId());
                if (indexInfo != null) {
                    jsonResult = new JsonResult(1, "列表信息", indexInfo, true);
                } else {
                    jsonResult = new JsonResult(1, "无列表信息", null, true);
                }
            } else {
                jsonResult = new JsonResult(3, "未登录", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }

    /**
     * 代理商(客户)交易统计
     *
     * @param agentId
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentTradeSummary")
    @ResponseBody
    public JsonResult agentTradeSummary(@RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                        HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            Map<String, Object> results = orderService.agentTradeSummary(agentId, member.getOwnShopid());
            jsonResult = new JsonResult(1, "客户交易统计成功", results, true);
        }
        return jsonResult;
    }

    /**
     * 查询代理商(客户)代还款订单
     *
     * @param pageNum
     * @param pageSize
     * @param agentId
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentWaitReturnOrder")
    @ResponseBody
    public JsonResult agentWaitReturnOrder(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                                           @RequestParam(value = "size") @Validated @NotNull Integer pageSize,
                                           @RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                           HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            List<OrderListVo> waitReturnOrders = orderService.agentWaitReturnOrder(pageNum, pageSize, agentId, member.getOwnShopid());
            if (waitReturnOrders != null && waitReturnOrders.size() > 0) {
                jsonResult = new JsonResult(1, "待还款订单列表", waitReturnOrders, true);
            } else {
                jsonResult = new JsonResult(1, "无待还款订单", null, true);
            }
        }
        return jsonResult;
    }

    /**
     * 代理商(客户)订单列表
     *
     * @param pageNum
     * @param pageSize
     * @param agentId
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentOrderList")
    @ResponseBody
    public JsonResult agentOrderList(@RequestParam(value = "pageNum") @Validated @NotNull Integer pageNum,
                                     @RequestParam(value = "size") @Validated @NotNull Integer pageSize,
                                     @RequestParam(value = "agentId") @Validated @NotNull Integer agentId,
                                     HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            List<OrderListVo> agentOrderList = orderService.agentOrderList(pageNum, pageSize, agentId, member.getOwnShopid());
            if (agentOrderList != null && agentOrderList.size() > 0) {
                jsonResult = new JsonResult(1, "客户订单列表", agentOrderList, true);
            } else {
                jsonResult = new JsonResult(1, "客户无订单", null, true);
            }
        }
        return jsonResult;
    }


    /**
     * App创建订单
     *
     * @param orderGoodsDto
     * @param request
     * @return
     */
    @RequestMapping(value = "/generateAppOrder")
    @ResponseBody
    public JsonResult generateAppOrder(@RequestBody OrderGoodsDto orderGoodsDto,
                                       HttpServletRequest request) {
        JsonResult jsonResult = null;
        try {
            MemberEntity member = loginUtil.getAppLoginMember(request);
            if (member == null) {
                jsonResult = new JsonResult(3, "未登录", false);
            } else {
                int isSuccess = orderService.generateAppOrder(orderGoodsDto,member, member.getOwnShopid(),request);
                if (isSuccess == 1) {

                    jsonResult = new JsonResult(1, "生成订单成功", true);
                } else {
                    jsonResult = new JsonResult(2, "生成订单失败", false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常", false);
        }
        return jsonResult;
    }


    /**
     * 添加凭证
     */
    @RequestMapping("addVoucher")
    @ResponseBody
    public JsonResult addVoucher(@RequestBody VoucherDto voucherDto, HttpServletRequest request) {

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getAppLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(3, "未登录", "", false);
            return jsonResult;
        }
        try {
            if (voucherDto != null && voucherDto.getAmount() != null && voucherDto.getImg() != null &&
                    voucherDto.getPayer() != null && voucherDto.getPayerPhone() != null && voucherDto.getPayTime() != null
                    && voucherDto.getPayType() != null) {
                int isSuccess = voucherService.insertVoucher(voucherDto, memberEntity);
                if (isSuccess > 0) {
                    sysMessageService.insertVoucherSysMsg(memberEntity, voucherDto.getAmount(),null);
                    jsonResult = new JsonResult(1, "添加凭证成功", true);
                } else {
                    jsonResult = new JsonResult(2, "添加凭证失败", false);
                }
            } else {
                jsonResult = new JsonResult(1, "数据不全,添加凭证失败", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "系统异常，添加凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }

    /**
     * 凭证列表
     */
    @RequestMapping("voucherList")
    @ResponseBody
    public JsonResult voucherList(HttpServletRequest request,
                                  @RequestParam(value = "pageNum") @Validated @NotNull String pageNum,
                                  @RequestParam(value = "size") @Validated @NotNull String pageSize
    ) {

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getAppLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(3, "未登录", "", false);
            return jsonResult;
        }
        try {
            List<VoucherVo> vouchers = voucherService.getVoucherList(memberEntity.getId(), new PageBounds(Integer.parseInt(pageNum), Integer.parseInt(pageSize)));
            if (vouchers != null && vouchers.size() > 0) {
                jsonResult = new JsonResult(1, "获取凭证成功", vouchers, true);
            } else {
                jsonResult = new JsonResult(1, "无凭证", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult(2, "获取凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }

    /**
     * 获取凭证详情
     */
    @RequestMapping("voucherDetail")
    @ResponseBody
    public JsonResult findById(@RequestParam(value = "voucherId") @Validated int voucherId, HttpServletRequest request) {

        JsonResult jsonResult = null;
        MemberEntity memberEntity = loginUtil.getAppLoginMember(request);
        if (memberEntity == null) {
            jsonResult = new JsonResult(3, "未登录", "", false);
            return jsonResult;
        }
        try {
            Voucher voucher = voucherService.findById(voucherId);
            if (voucher != null) {
                jsonResult = new JsonResult(1, "获取凭证成功", voucher, true);
            } else {
                jsonResult = new JsonResult(1, "获取凭证失败", true);
            }
        } catch (Exception e) {
            jsonResult = new JsonResult(2, "获取凭证失败", false);
            return jsonResult;
        }
        return jsonResult;
    }

    /**
     * 查询登陆用户下的客户账单
     *
     * @param levelId
     * @param timeAreaType
     * @param request
     * @return
     */
    @RequestMapping(value = "/agentBills")
    @ResponseBody
    public JsonResult agentBills(@RequestParam(value = "levelId", required = false) Integer levelId,
                                 @RequestParam(value = "timeAreaType") @Validated @NotNull Integer timeAreaType,
                                 HttpServletRequest request) {
        JsonResult jsonResult = null;
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (member == null) {
            jsonResult = new JsonResult(3, "未登录", false);
        } else {
            Map<String, Object> agentBillsMap = orderService.agentBills(member.getId(), levelId, timeAreaType, member.getOwnShopid());
            jsonResult = new JsonResult(1, "客户账单", agentBillsMap, true);
        }
        return jsonResult;
    }

    @RequestMapping("/activityDetail")
    @ResponseBody
    public JsonResult queryMarketAttrDetail(HttpServletRequest request, @RequestParam("actId") int actId) {

        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        Marketact marketact = marketagentService.queryMarketActDetailForApp(actId);
        if (null == marketact) {
            return new JsonResult(2, "活动不存在", "", false);
        }
        MarketActDto marketActDto = new MarketActDto();
        marketActDto.setContName(marketact.getContName());
        marketActDto.setContTitle(marketact.getContTitle());
        marketActDto.setAttrDate(new StringBuffer().append(DateTool.format(marketact.getEffDate())).append("至").
                append(DateTool.format(marketact.getExpDate())).toString());
        marketActDto.setStaticCont(marketact.getStaticCont());
        marketActDto.setContUrl(marketact.getContUrl());
        marketActDto.setAtrPic(marketact.getActPic());
        marketActDto.setActContent(marketact.getTerminal());
        marketActDto.setActTypeName(marketact.getActTypeName());
        return new JsonResult(1, "详细信息", marketActDto, true);
    }

    @RequestMapping("/activityGoods")
    @ResponseBody
    public JsonResult goodsCollectPageList(HttpServletRequest request, @RequestParam("pageNum") int pageCurrent,
                                           @RequestParam("size") int pageSize, @RequestParam("actId") int actId) {
        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        List<ActityGoodsVo> list = marketagentService.findByPageGoods(actId, member.getOwnShopid().intValue(), new PageBounds(pageCurrent, pageSize));
        return new JsonResult(1, "活动商品列表", list, true);
    }


    /**
     * 获取活动列表
     *
     * @author 喻聪聪（仅完善活动的条件搜索子功能）
     * @date 2019-02-28
     */
    @RequestMapping("/activities")
    @ResponseBody
    public JsonResult findByPageAct(HttpServletRequest request, @RequestParam("pageNum") int pageCurrent,
                                    @RequestParam("size") int pageSize,
                                    @RequestParam(value = "contType", required = false) Integer contType,
                                    @RequestParam(value = "contState", required = false) Integer contState,
                                    @RequestParam(value = "effDate", required = false) String effDate,
                                    @RequestParam(value = "expDate", required = false) String expDate) {

        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        List<ActivityVo> list = marketagentService.findShopActivies(
                new PageBounds(pageCurrent, pageSize),
                member.getOwnShopid(), contType, contState, effDate, expDate);
        return new JsonResult(1, "活动列表", list, true);
    }

    /**
     * 获取活动类型列表
     *
     * @author 喻聪聪
     * @date 2019-02-28
     */
    @RequestMapping("/activityTypes")
    @ResponseBody
    public JsonResult findActivityTypes(HttpServletRequest request) {

        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        List<ActivityTypeVo> list = marketagentService.findActivityTypes(member.getOwnShopid());
        return new JsonResult(1, "活动列表", list, true);
    }

    /**
     * 配送方式列表接口
     *
     */
    @RequestMapping("/shippingTypes")
    @ResponseBody
    public JsonResult findShippingTypes(HttpServletRequest request) {

        MemberEntity member = loginUtil.getAppLoginMember(request);
        if (null == member) {
            return new JsonResult(3, "未登录", "", false);
        }
        List<ShippingTypeVo> list = orderService.findShippingTypes(member.getOwnShopid());
        return new JsonResult(1, "配送方式列表", list, true);
    }

}
