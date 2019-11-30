package com.zlead.shopmgr.service.impl;

import com.alibaba.fastjson.JSON;
import com.zlead.constant.Cnst;
import com.zlead.dao.*;
import com.zlead.entity.*;
import com.zlead.entity.constant.TOperatorLogConstant;
import com.zlead.entity.dto.AgentBillDTO;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.dto.OrderGoodsDto;
import com.zlead.entity.vo.*;
import com.zlead.fplat.dao.*;
import com.zlead.fplat.entity.*;
import com.zlead.shopmgr.service.FactoryOrderService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.AppUtil;
import com.zlead.utils.DateTool;
import com.zlead.utils.LoginUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FactoryOrderServiceImpl implements FactoryOrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Autowired
    private GoodsAttrValDao goodsAttrValDao;

    @Autowired
    private MemberAddressDao memberAddressDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private OaAgentMasMapper oaAgentMasMapper;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AgentFacMapper agentFacMapper;

    @Autowired
    private OperatorlogDao operatorlogDao;

    @Autowired
    private AclUserDao aclUserDao;

    @Autowired
    private OaAgentUserinfoMapper oaAgentUserinfoMapper;

    @Autowired
    private AgentAddressMapper agentAddressMapper;

    @Autowired
    private ShippingTypeDao shippingTypeDao;

    @Autowired
    private PayTypeDao payTypeDao;

    @Autowired
    private PrdCustPriceMapper prdCustPriceMapper;

    @Autowired
    private OaAgentRevinfoMapper oaAgentRevinfoMapper;

    @Autowired
    private VoucherMapper voucherMapper;

    /**
     * 分页查询销售订单列表
     *
     * @param status
     * @param pageNum
     * @param pageSize
     * @param member
     * @return
     */
    @Override
    public PageList<OrderEntity> shopOrderList(String status, String sn, int pageNum, int pageSize, MemberEntity member) {
        PageList<OrderEntity> orderList = null;
        Long shopId = member.getOwnShopid();
        Map params = new HashMap<>();
        if (shopId != null && LoginUtil.isShop(member)) {
            //查看订单信息
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            params.put("memberId", member.getId());//登录用户
            if (status != null) {
                Integer intStatus = Integer.parseInt(status);
                if (intStatus != null)
                    params.put("status", status);
            }
            if (sn != null)
                params.put("sn", sn);
            orderList = orderDao.findSellOrderListByPage(params, pageBounds);
        }
        return orderList;
    }

    /**
     * 查询指定订单号下的订单详情
     *
     * @param pageNum
     * @param pageSize
     * @param orderSn
     * @return
     */
    @Override
    public PageList<OrderGoodsEntity> shopOrderDetailsList(int pageNum, int pageSize, String orderSn, Long shopId) {
        PageList<OrderGoodsEntity> orderGoodsList = null;
        if (orderSn != null && shopId != null) {
            PageBounds pageBounds = new PageBounds(pageNum, pageSize);
            orderGoodsList = orderGoodsDao.findOrderDetailsBySn(orderSn, shopId, pageBounds);
        }
        return orderGoodsList;
    }

    @Override
    public BigDecimal shopOrderTotalAmount(Long shopId) {
        return orderDao.getOrderTotalAmount(shopId);
    }

    @Override
    public Long shopOrderTotalCount(Long shopId) {
        return orderDao.getOrderTotalCount(shopId);
    }

    @Override
    public ShopIndexVo shopIndex(Long memberId) {
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        Map map = null;
        map = orderDao.getIndexInfo(memberId, userIds, agentIds);
        ShopIndexVo shopIndexVo = new ShopIndexVo();
        shopIndexVo.setCustomerCount((Long) map.get("customerCount"));
        shopIndexVo.setOrderCount((Long) map.get("orderCount"));
        shopIndexVo.setTotalCountMonth((Long) map.get("totalCountMonth"));
        shopIndexVo.setTotalCountSeason((Long) map.get("totalCountSeason"));
        shopIndexVo.setTotalCountWeek((Long) map.get("totalCountWeek"));
        shopIndexVo.setTotalAmountMonth((BigDecimal) map.get("totalAmountMonth"));
        shopIndexVo.setTotalAmountSeason((BigDecimal) map.get("totalAmountSeason"));
        shopIndexVo.setTotalAmountWeek((BigDecimal) map.get("totalAmountWeek"));
        Set<Integer> saleUsers = voucherMapper.findVoucherByUserIds(memberId, userIds);
        Double finishTotalAmountWeek = 0d;
        Double finishTotalAmountMonth = 0d;
        Double finishTotalAmountSeason = 0d;
        Set<Integer> filterUserIds = new HashSet<>();
        for (Integer saleUser : saleUsers) {
            if (userIds.contains(saleUser)) {
                filterUserIds.add(saleUser);
            }
        }
        finishTotalAmountWeek = voucherMapper.findFinishAmtWeek(memberId, filterUserIds);
        finishTotalAmountMonth = voucherMapper.findFinishAmtMonth(memberId, filterUserIds);
        finishTotalAmountSeason = voucherMapper.findFinishAmtSeason(memberId, filterUserIds);
        MemberEntity memberEntity = memberDao.findById(memberId);
        List<AgentFac> agentFacs = agentFacMapper.findByShopIdByUserIds(memberEntity.getId(), userIds);
        Set<AgentFac> filterAgentFacs = new HashSet<>();
        for (AgentFac agentFac : agentFacs) {
            if (userIds.contains(agentFac.getUserId())) {
                filterAgentFacs.add(agentFac);
            }
        }
        Double totalWeekTaskAmt = 0d;
        Double totalMonthTaskAmt = 0d;
        Double totalSeasonTaskAmt = 0d;
        Double yearTaskAmt = 0d;
        for (AgentFac agentFac : filterAgentFacs) {
            long weekCount = 0;
            long monthCount = 0;
            long seasonCount = 0;
            //查找是否所属业务员
            if ("1".equals(agentFac.getCoopState())) {
                weekCount = 1;
                monthCount = 1;
                seasonCount = 1;
            } else {
                OperatorLog operatorLog = operatorlogDao.findBySn(agentFac.getId());
                if (operatorLog != null) {
                    Date createDate = operatorLog.getCreateDate();
                    Date monday = operatorlogDao.getWeekMonday();
                    Date sunday = operatorlogDao.getWeekSunday();
                    Date monthStart = operatorlogDao.getMonthStart();
                    Date monthStop = operatorlogDao.getMonthStop();
                    Date seasonStart = operatorlogDao.getSeasonStart();
                    Date seasonStop = operatorlogDao.getSeasonStop();
                    if (AppUtil.belongCalendar(createDate, monday, sunday)) {
                        weekCount = 1;
                    } else {
                        if ("agentStart".equals(operatorLog.getTitle())) {
                            weekCount = 1;
                        }
                    }
                    if (AppUtil.belongCalendar(createDate, monthStart, monthStop)) {
                        monthCount = 1;
                    } else {
                        if ("agentStart".equals(operatorLog.getTitle())) {
                            monthCount = 1;
                        }
                    }
                    if (AppUtil.belongCalendar(createDate, seasonStart, seasonStop)) {
                        seasonCount = 1;
                    } else {
                        if ("agentStart".equals(operatorLog.getTitle())) {
                            seasonCount = 1;
                        }
                    }
                } else {
                    weekCount = 1;
                    monthCount = 1;
                    seasonCount = 1;
                }
            }
            yearTaskAmt = agentFac.getTaskAmt() == null ? 0 : agentFac.getTaskAmt();
            Double weekAvg = yearTaskAmt * 10000 / 52;
            Double monthAvg = yearTaskAmt * 10000 / 12;
            Double seasonAvg = yearTaskAmt * 10000 / 4;
            totalWeekTaskAmt += weekCount * weekAvg;
            totalMonthTaskAmt += monthCount * monthAvg;
            totalSeasonTaskAmt += seasonCount * seasonAvg;
        }
        double bg = (agentFacs.size() != 0 && totalWeekTaskAmt != 0) ? finishTotalAmountWeek.doubleValue() / totalWeekTaskAmt.doubleValue() : 0;
        double f1 = AppUtil.formatDouble(bg);
        double bg2 = (agentFacs.size() != 0 && totalMonthTaskAmt != 0) ? finishTotalAmountMonth.doubleValue() / totalMonthTaskAmt.doubleValue() : 0;
        double f2 = AppUtil.formatDouble(bg2);
        double bg3 = (agentFacs.size() != 0 && totalSeasonTaskAmt != 0) ? finishTotalAmountSeason.doubleValue() / totalSeasonTaskAmt.doubleValue() : 0;
        double f3 = AppUtil.formatDouble(bg3);
        shopIndexVo.setFinishRateWeek(f1);
        shopIndexVo.setFinishRateMonth(f2);
        shopIndexVo.setFinishRateSeason(f3);
        return shopIndexVo;
    }

    /**
     * order_goods:[
     * {
     * goods_id:xxx,
     * goods_img:xxx
     * }
     * ]
     *
     * @param status
     * @param sn
     * @param time
     * @param agentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<OrderListVo> appShopOrderList(Long memberId, String status, String sn, String time, String agentId, int pageNum, int pageSize) {
        List<OrderListVo> orderList = null;
        Map<String, Object> params = new HashMap<>();
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //查询userId下的客户
        params.put("memberId", memberId);
        params.put("status", status);
        params.put("sn", sn);
        params.put("time", time);
        params.put("agentId", agentId);
        params.put("userIds", userIds);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        params.put("agentIds", agentIds);
        orderList = orderDao.findAppOrderListByPage(params, pageBounds);
        List<OrderListVo> list = new ArrayList<>();
        for (OrderListVo orderListVo : orderList) {
            Integer goodscnt = 0;
            OrderListVo orderVo = new OrderListVo();
            List<OrderGoodsListVo> orderGoods = orderGoodsDao.findAppOrderGoodsListByPage(orderListVo.getOrderId());
            orderVo.setAgentId(orderListVo.getAgentId());
            orderVo.setAgentName(orderListVo.getAgentName());
            orderVo.setGoodsAmount(orderListVo.getGoodsAmount());
            orderVo.setOrderId(orderListVo.getOrderId());
            orderVo.setShippingType(orderListVo.getShippingType());
            orderVo.setShippingCost(orderListVo.getShippingCost());
            orderVo.setSn(orderListVo.getSn());
            orderVo.setStatus(orderListVo.getStatus());
            orderVo.setFactoryId(orderListVo.getFactoryId());
            orderVo.setOrderGoodsListVos(orderGoods);
            for (OrderGoodsListVo orderGood : orderGoods) {
                goodscnt += orderGood.getCount();
            }
            orderVo.setGoodscnt(goodscnt);
            list.add(orderVo);
        }
        return list;
    }

    /**
     * 待办事项
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     * @author leiningbo
     * @date 2019年4月26日15:45:04
     */
    @Override
    public List<OrderListVo> appShopUpcomingOrderList(Long memberId, int pageNum, int pageSize) {
        List<OrderListVo> orderList = null;
        Map<String, Object> params = new HashMap<>();
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        params.put("memberId", memberId);
        params.put("userIds", userIds);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        params.put("agentIds", agentIds);
        orderList = orderDao.findAppOrderUpComingListByPage(params, pageBounds);
        List<OrderListVo> list = new ArrayList<>();
        for (OrderListVo orderListVo : orderList) {
            Integer goodscnt = 0;
            OrderListVo orderVo = new OrderListVo();
            List<OrderGoodsListVo> orderGoods = new ArrayList<>();
            orderGoods = orderGoodsDao.findAppOrderGoodsListByPage(orderListVo.getOrderId());

            if (1 == orderListVo.getBuyType() || 2 == orderListVo.getBuyType()) {
                orderGoods = orderGoodsDao.findAppOrderGoodsListByPage(orderListVo.getOrderId());
                if (null != orderVo.getOrderGoodsListVos() && orderVo.getOrderGoodsListVos().size() > 1) {
                    orderGoods = orderGoodsDao.newfindAppOrderGoodsList(orderListVo.getOrderId(), memberId);
                }
            }

            orderVo.setAgentId(orderListVo.getAgentId());
            orderVo.setAgentName(orderListVo.getAgentName());
            orderVo.setGoodsAmount(orderListVo.getGoodsAmount());
            orderVo.setOrderId(orderListVo.getOrderId());
            orderVo.setShippingCost(orderListVo.getShippingCost());
            orderVo.setShippingType(orderListVo.getShippingType());
            orderVo.setSn(orderListVo.getSn());
            orderVo.setStatus(orderListVo.getStatus());
            orderVo.setFactoryId(orderListVo.getFactoryId());
            orderVo.setOrderGoodsListVos(orderGoods);
            for (OrderGoodsListVo orderGood : orderGoods) {
                goodscnt += orderGood.getCount();
            }
            orderVo.setGoodscnt(goodscnt);
            list.add(orderVo);
        }
        return list;
    }

    @Override
    public List<ShippingTypeVo> findShippingTypes(Long shopId) {
        return shippingTypeDao.findShippingTypes(shopId);
    }

    @Override
    public List<PayTypeVo> findPayTypes(Long shopId) {
        List<PayTypeVo> dataList = payTypeDao.findPayTypesByShopId(shopId);
        if (CollectionUtils.isEmpty(dataList)) {
            dataList = payTypeDao.findPayTypes();
        }
        return dataList;
    }

    @Override
    public OrderDetailVo appShopOrderDetails(Long orderId, Long memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("memberId", memberId);
        OrderDetailVo orderDetail = orderDao.findAppOrderDetail(params);
        List<OrderGoodsListVo> orderGoodsListVos = orderGoodsDao.findAppOrderGoodsListByPage(orderDetail.getOrderId());
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        Integer goodscnt = 0;
        orderDetailVo.setAgentId(orderDetail.getAgentId());
        orderDetailVo.setAgentName(orderDetail.getAgentName());
        orderDetailVo.setGoodsAmount(orderDetail.getGoodsAmount());
        orderDetailVo.setOrderId(orderDetail.getOrderId());
        orderDetailVo.setPayType(orderDetail.getPayType());
        orderDetailVo.setShippingCost(orderDetail.getShippingCost());

        orderDetailVo.setSn(orderDetail.getSn());
        orderDetailVo.setStatus(orderDetail.getStatus());
        //收货人地址
        orderDetailVo.setAgentAddr(orderDetail.getAddress());
        //收货人姓名
        orderDetailVo.setLinkName(orderDetail.getConsignee());
        //收货人电话
        orderDetailVo.setLinkTel(orderDetail.getPhone());

        orderDetailVo.setCreateDate(orderDetail.getCreateDate().substring(0, orderDetail.getCreateDate().indexOf(".")));
        orderDetailVo.setShippingType(orderDetail.getShippingType());

        List<OperatorLog> listOpLogs = operatorlogDao.getListOpLogs(orderId);
        Date date = new Date();
        if (null != listOpLogs && listOpLogs.size() >= 2) {
            for (OperatorLog log : listOpLogs) {
                if ("PAY".equals(log.getTitle())) {
                    date = log.getCreateDate();
                }
            }
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderDetailVo.setPayDate(sdf.format(date).toString());
        } else {
            orderDetailVo.setPayDate(orderDetail.getPayDate());
        }
        orderDetailVo.setOrderGoodsListVos(orderGoodsListVos);
        for (OrderGoodsListVo orderGood : orderGoodsListVos) {
            goodscnt += orderGood.getCount();
        }
        orderDetailVo.setGoodscnt(goodscnt);
        return orderDetailVo;
    }

    @Override
    public List<OrderGoodsDetailVo> appShopOrderGoodsDetailsList(int pageNum, int pageSize, Long orderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        List<OrderGoodsDetailVo> orderGoodsDetailList = orderGoodsDao.findAppOrderGoodsDetail(params, new PageBounds(pageNum, pageSize));
        List<OrderGoodsDetailVo> list = new ArrayList<>();
        for (OrderGoodsDetailVo orderGoodsDetail : orderGoodsDetailList) {
            OrderGoodsDetailVo orderGoodsDetailVo = new OrderGoodsDetailVo();
            Integer goodsId = orderGoodsDetail.getGoodsId();
            List<GoodsAttrValDto> goodsAttrValDtos = goodsAttrValDao.queryByGdsId(goodsId);
            orderGoodsDetailVo.setGoodsAttrValDtos(goodsAttrValDtos);
            orderGoodsDetailVo.setGoodsId(orderGoodsDetail.getGoodsId());

            GoodsDetailVo goodsDetailVo = goodsDao.findShopGoodsDetail(goodsId);

            orderGoodsDetailVo.setGoodsName(goodsDetailVo.getFullName());
            orderGoodsDetailVo.setGoodsImg(orderGoodsDetail.getGoodsImg());
            orderGoodsDetailVo.setPrice(orderGoodsDetail.getPrice());
            orderGoodsDetailVo.setCount(orderGoodsDetail.getCount());
            list.add(orderGoodsDetailVo);
        }
        return list;
    }

    @Override
    public List<OrderCountVo> findOrderListCount(String sn, String time, String agentId, Long memberId) {
        Map params = new HashMap<>();
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        params.put("sn", sn);
        params.put("time", time);
        params.put("agentId", agentId);
        params.put("memberId", memberId);
        params.put("userIds", userIds);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        params.put("agentIds", agentIds);
        Integer total = 0;
        List<OrderCountVo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            params.put("status", i);
            OrderCountVo orderListCount = orderDao.findOrderListCount(params);
            if (orderListCount != null) {
                orderListCount.setStatus(i);
                total += orderListCount.getOrdercnt();
                list.add(orderListCount);
            } else {
                OrderCountVo orderCountVo = new OrderCountVo();
                orderCountVo.setStatus(i);
                orderCountVo.setOrdercnt(0);
                list.add(orderCountVo);
            }
        }
        OrderCountVo orderCount = new OrderCountVo();
        orderCount.setStatus(-1);
        orderCount.setOrdercnt(total);
        list.add(orderCount);
        return list;
    }

    @Override
    public OrderCountStatisticsVo findOrderListStatisticsCount(String sn, String time, String agentId, Long memberId) {
        /*Map params = new HashMap<>();
        params.put("sn", sn);
        params.put("time", time);
        params.put("agentId", agentId);*/

        //完善monthCnt,totalCnt两个字段值
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //查询userId下的客户
        Set<Integer> agentIds = agentFacMapper.findAgentListByUserIds(userIds);
        int monthCnt = orderDao.findStatisticsOrderListCount(memberId, userIds, agentIds, "month");
        int totalCnt = orderDao.findStatisticsOrderListCount(memberId, userIds, agentIds, null);
        OrderCountStatisticsVo returnVO = new OrderCountStatisticsVo();
        returnVO.setMonthCnt(monthCnt);
        returnVO.setTotalCnt(totalCnt);
        return returnVO;
    }

    @Override
    public Map<String, Object> agentTradeSummary(Integer agentId, MemberEntity memberEntity) {
        Map<String, Object> results = new HashMap<>();
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberEntity.getId());
        //统计客户交易总额
        BigDecimal totalAmount = orderDao.countOrderAmountByAgentId(agentId, memberEntity.getOwnShopid(), userIds);
        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }
        //统计客户已付款总金额
        BigDecimal paidAmount = orderDao.countPaidByAgentAndShop(agentId, memberEntity.getOwnShopid(), userIds);
        if (paidAmount == null) {
            paidAmount = BigDecimal.ZERO;
        }
        BigDecimal waitReturnAmount = totalAmount.subtract(paidAmount);//统计客户代还款金额 = 客户交易总额 - 客户已付款总金额
        if (waitReturnAmount.compareTo(BigDecimal.ZERO) < 0) {
            waitReturnAmount = BigDecimal.ZERO;
        }
       /*
        //修改客户已付款总金额，需要按凭证上传时间和订单创建时间比较
        BigDecimal waitReturnAmount = BigDecimal.ZERO;
        //查询所有的待还款订单列表（包含已关联凭证但是凭证金额小于订单金额的订单和未关联凭证金额的订单）
        Long shopId = memberEntity.getOwnShopid();
        List<PendingPaymentOrder> dataList = orderDao.findPendingPaymentListByAgentAndShop(agentId,shopId,userIds);
        //查询工厂端上传的凭证列表
        List<Voucher> voucherList = voucherMapper.findFactoryVoucherListByAgentAndShop(agentId,shopId);
        if(CollectionUtils.isNotEmpty(dataList)){
            BigDecimal pendingPaymentAmount = null;
            if(CollectionUtils.isEmpty(voucherList)){//如果工厂端没有上传凭证
                for(PendingPaymentOrder data : dataList){
                    pendingPaymentAmount = data.getPendingPaymentAmount();
                    if(pendingPaymentAmount == null){
                        pendingPaymentAmount = BigDecimal.ZERO;
                    }
                    waitReturnAmount = waitReturnAmount.add(pendingPaymentAmount);
                }
            } else{
                Map<String,Object> result = null;
                int startIndex = 0;
                for(Voucher voucher : voucherList){
                    result = this.getPendingPaymentAmount(voucher,dataList,startIndex,waitReturnAmount);
                    waitReturnAmount = (BigDecimal) result.get("amount");
                    startIndex = (int)result.get("index");
                }
                int size = dataList.size();
                if(startIndex < size){
                    PendingPaymentOrder data = null;
                    for(int i = startIndex; i< size; i++){
                        data = dataList.get(i);
                        pendingPaymentAmount = data.getPendingPaymentAmount();
                        if(pendingPaymentAmount == null){
                            pendingPaymentAmount = BigDecimal.ZERO;
                        }
                        waitReturnAmount = waitReturnAmount.add(pendingPaymentAmount);
                    }
                }
            }
        }*/

        //统计客户交易总单数
        int totalNumber = orderDao.countOrderNumberByAgentId(agentId, memberEntity.getOwnShopid(), userIds);
        //判断客户是否有代还款订单
        boolean hasWaitReturnOrder = false;
        if (waitReturnAmount != null && waitReturnAmount.doubleValue() > 0) {
            hasWaitReturnOrder = true;
        }
        results.put("totalAmount", totalAmount == null ? 0 : totalAmount);
        results.put("totalNumber", totalNumber);
        results.put("waitReturnAmount", waitReturnAmount == null ? 0 : waitReturnAmount);
        results.put("hasWaitReturnOrder", hasWaitReturnOrder);
        return results;
    }

    private Map<String,Object> getPendingPaymentAmount(Voucher voucher,List<PendingPaymentOrder> orderList,int startIndex,BigDecimal totalPendingPayment){
        BigDecimal voucherAmount = voucher.getAmount();//凭证金额
        if(voucherAmount == null){
            voucherAmount = BigDecimal.ZERO;
        }
        int index = startIndex;
        //查询该凭证上传之前创建的订单的欠款总金额
        int size = orderList.size();
        Date voucherDate = voucher.getUploadTime();//上传凭证时间
        Date orderCreateDate = null;
        BigDecimal orderPendingPayment = BigDecimal.ZERO;//订单总欠款
        PendingPaymentOrder order;
        for(int i = startIndex; i < size; i++){
            order = orderList.get(i);
            orderCreateDate = order.getCreateDate();
            if(voucherDate.compareTo(orderCreateDate) > 0){
                index = i + 1;
                orderPendingPayment = orderPendingPayment.add(order.getPendingPaymentAmount() == null? BigDecimal.ZERO : order.getPendingPaymentAmount());
            }else{
                index = i;
                break;
            }
        }
        orderPendingPayment = totalPendingPayment.add(orderPendingPayment);
        BigDecimal pendingPaymentAmount = null;
        if(voucherAmount.compareTo(orderPendingPayment) > 0){//上传凭证金额大于订单总欠款金额则欠款为零
            pendingPaymentAmount =  BigDecimal.ZERO;
        }else{
            pendingPaymentAmount = orderPendingPayment.subtract(voucherAmount);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("index",index);
        result.put("amount",pendingPaymentAmount);
        return result;
    }

    @Override
    public List<OrderListVo> agentWaitReturnOrder(Integer pageNum, Integer pageSize, Integer agentId, Long ownShopid) {
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        List<OrderListVo> waitReturnOrders = orderDao.queryWaitReturnOrderByAgentId(agentId, ownShopid, pageBounds);
        return setOrderGoods(waitReturnOrders);
    }

    @Override
    public List<OrderListVo> agentOrderList(Integer pageNum, Integer pageSize, Integer agentId, Long ownShopid) {
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        List<OrderListVo> orderList = orderDao.queryOrderByAgentId(agentId, ownShopid, pageBounds);
        return setOrderGoods(orderList);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class/*, StockException.class*/},
            isolation = Isolation.SERIALIZABLE, propagation = Propagation.SUPPORTS)
    public int generateAppOrder(OrderGoodsDto orderGoodsDto, MemberEntity member, Long shopId, HttpServletRequest request) throws Exception {
        //MemberAddressEntity addressEntity = memberAddressDao.findById(orderGoodsDto.getOrderMemberInfoVo().getAddressId());
        //String proviceName = memberAddressDao.findRegionName(addressEntity.getProvinceId().toString());
        //String cityName = memberAddressDao.findRegionName(addressEntity.getCityId().toString());
        //String areaName = memberAddressDao.findRegionName(addressEntity.getRegionId().toString());
        int signResult = AppUtil.verifySign(request);
        if (signResult == AppUtil.SIGN_OK) {
            OaAgentRevinfo revinfo = oaAgentRevinfoMapper.findRevinfoById(orderGoodsDto.getOrderMemberInfoVo().getAddressId());
            String proviceName = memberAddressDao.findRegionName(revinfo.getRevProvince());
            String cityName = memberAddressDao.findRegionName(revinfo.getRevCity());
            String areaName = memberAddressDao.findRegionName(revinfo.getRevCounty());
            OrderEntity orderEntity = null;
            //if (addressEntity != null) {
            if (revinfo != null) {
                OaAgentMas oaAgentMas = oaAgentMasMapper.selectByPrimaryKey(orderGoodsDto.getOrderMemberInfoVo().getAgentId());
                MemberEntity memberEntity = memberDao.findByAgentId(oaAgentMas.getAgentId());
                if (memberEntity == null) {
                    return -1;
                }
                AclUserEntity aclUserEntity = aclUserDao.findUserByMemberId(member.getId());
                OaAgentUserinfo oaAgentUserinfo = oaAgentUserinfoMapper.findOaAgentUser(orderGoodsDto.getOrderMemberInfoVo().getAgentId(), shopId);
                ShopEntity shopEntity = shopDao.findById(shopId);
                AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(oaAgentMas.getAgentId(), shopId.intValue());
                AgentAddress agentAddress = agentAddressMapper.findAddressRevByIds(orderGoodsDto.getOrderMemberInfoVo().getAgentId(), shopId);
                orderEntity = new OrderEntity();
                orderEntity.setSn(Cnst.SDF_SHORT.format(new Date()) + Cnst.getFixLengthString(4));
                orderEntity.setShopId(shopId);
                orderEntity.setOrderType(0);
                orderEntity.setBuyType(4);
                orderEntity.setPayType(orderGoodsDto.getOrderMemberInfoVo().getPayType());
                orderEntity.setStatus(0);
                orderEntity.setPayPoint(0L);
                orderEntity.setBuyerCorpid(orderGoodsDto.getOrderMemberInfoVo().getAgentId().longValue());
                orderEntity.setShippingType(orderGoodsDto.getOrderMemberInfoVo().getShippingType());
                orderEntity.setShopName(shopEntity.getShopName());
                orderEntity.setMemberName(oaAgentMas.getAgentName());
                orderEntity.setConsignee(agentAddress.getRevName());
                //orderEntity.setAddress(proviceName + cityName + areaName + addressEntity.getAddress());
                //orderEntity.setPostCode(addressEntity.getPostcode());
                //orderEntity.setPhone(addressEntity.getPhone());
                //orderEntity.setEmail(addressEntity.getEmail());
                orderEntity.setAddress(proviceName + cityName + areaName + revinfo.getRevAddr());
                orderEntity.setPostCode(null);
                orderEntity.setPhone(revinfo.getRevTel());
                orderEntity.setEmail(null);
                orderEntity.setCreateId(aclUserEntity.getUserId());
                orderEntity.setNeedInvoide(0);
                orderEntity.setCreateDate(new Date());
                orderEntity.setIsExpire(0);//是否过期
                orderEntity.setIsDelete(0);
                orderEntity.setStatus(0);
                orderEntity.setUserId(agentFac.getUserId());
                orderEntity.setDiscount(agentFac.getAgentDiscount() == null ? 1f : agentFac.getAgentDiscount().floatValue());
                orderEntity.setContactMan(oaAgentUserinfo.getUserName());
                orderEntity.setContactTel(oaAgentUserinfo.getLinkTel());
                orderEntity.setModifyDate(new Date());
                //添加金额
                BigDecimal shippingCost = BigDecimal.ZERO;
                orderEntity.setShippingCost(shippingCost);
                //保存订单信息
                orderEntity.setMemberId(memberEntity.getId());
                BigDecimal actualPay = BigDecimal.ZERO;
                BigDecimal price = BigDecimal.ZERO;
                for (GoodsVo goodsVo : orderGoodsDto.getGoodsVos()) {
                    PrdCustPrice prdCustPrice = prdCustPriceMapper.selectByPrimaryKey(goodsVo.getGoodsId().intValue());
                    price = countPrice(agentFac, prdCustPrice)[1];
                    actualPay = actualPay.add(price.multiply(new BigDecimal(goodsVo.getCount())));

                }
                orderEntity.setPriceType(agentFac.getAgentDiscountType() == null ? "1" : agentFac.getAgentDiscountType());
                orderEntity.setGoodsAmount(actualPay.setScale(2, BigDecimal.ROUND_HALF_UP));
                orderEntity.setActualPayments(actualPay.add(shippingCost).setScale(2, BigDecimal.ROUND_HALF_UP));
                orderEntity.setPayableAmount(actualPay.add(shippingCost).setScale(2, BigDecimal.ROUND_HALF_UP));
                orderDao.insert(orderEntity);
                for (GoodsVo goodsVo : orderGoodsDto.getGoodsVos()) {
                    PrdCustPrice prdCustPrice = prdCustPriceMapper.selectByPrimaryKey(goodsVo.getGoodsId().intValue());
                    price = countPrice(agentFac, prdCustPrice)[1];
                    GoodsEntity goodsEntity = goodsDao.findById(goodsVo.getGoodsId());
                    OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
                    orderGoodsEntity.setGoodsId(goodsEntity.getId());
                    orderGoodsEntity.setGoodsName(goodsEntity.getFullName());
                    orderGoodsEntity.setGoodsImg(goodsEntity.getFirstImg());
                    orderGoodsEntity.setPrice(countPrice(agentFac, prdCustPrice)[1]);
                    orderGoodsEntity.setDiscount(goodsEntity.getDiscount());
                    orderGoodsEntity.setGoodsPoint(goodsEntity.getPoint());
                    orderGoodsEntity.setCommentStatus(0);
                    orderGoodsEntity.setStatus(0);
                    orderGoodsEntity.setCreateDate(new Date());
                    orderGoodsEntity.setCount(goodsVo.getCount());
                    orderGoodsEntity.setSpareQty(goodsVo.getCount());
                    orderGoodsEntity.setOrderId(orderEntity.getId());
                    //商品原价（不打折之前的原价）
                    orderGoodsEntity.setOriginPrice(countPrice(agentFac, prdCustPrice)[0]);
                    orderGoodsEntity.setGoodsTotalPrice(price.multiply(new BigDecimal(goodsVo.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP));
                    //保存订单商品信息
                    orderGoodsDao.insert(orderGoodsEntity);
                }
                String id = orderEntity.getId().toString();
                inserOperatorLog(member, memberEntity, id, orderGoodsDto, request);
                return 1;
            }
            return 0;
        } else {
            return signResult;
        }
    }


    /*把 -插入操作日志-方法抽出来*/
    private void inserOperatorLog(MemberEntity member, MemberEntity memberEntity, String orderId,
                                  OrderGoodsDto orderGoodsDto, HttpServletRequest request) {
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setMemberId(memberEntity.getId().intValue());
        operatorLog.setUserName(aclUserDao.queryByName(member.getUsername()));
        operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_CREATE);
        operatorLog.setRemark("APP:" + memberEntity.getId() + "用户创建订单");
        operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
        operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
        operatorLog.setSn(orderId);
        operatorLog.setCreateDate(new Date());
        operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
        operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MGT);
        operatorLog.setLinkUrl(request.getRequestURL().toString());
        operatorLog.setAddIp(request.getRemoteAddr());
        operatorLog.setOperatorParams(JSON.toJSONString(orderGoodsDto));
        operatorlogDao.insertSelectiveOne(operatorLog);
    }

    /**
     * 计算折扣价格
     *
     * @param agentFac
     * @param prdCustPrice 0是未打折的原价，1是打完折扣的价格
     * @return
     */
    private BigDecimal[] countPrice(AgentFac agentFac, PrdCustPrice prdCustPrice) {
        BigDecimal marketPrice = null;
        BigDecimal originPrice = null;
        String type = agentFac.getAgentDiscountType();
        if (StringUtils.isNotBlank(type)) {
            if (null != prdCustPrice) {
                switch (type) {
                    case "1":
                        if (null == prdCustPrice.getBatchPrice()) {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                        } else {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getBatchPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getBatchPrice()).
                                    multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).
                                    setScale(2, RoundingMode.HALF_UP);
                        }
                        break;
                    case "2":
                        if (null == prdCustPrice.getItemPrice()) {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                        } else {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getItemPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getItemPrice()).multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
                        }
                        break;
                    case "3":
                        if (null == prdCustPrice.getRetailPrice()) {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                        } else {
                            originPrice = BigDecimal.valueOf(prdCustPrice.getRetailPrice());
                            marketPrice = BigDecimal.valueOf(prdCustPrice.getRetailPrice()).multiply(BigDecimal.valueOf(agentFac.getAgentDiscount() == null ? 1D : agentFac.getAgentDiscount())).setScale(2, RoundingMode.HALF_UP);
                        }
                        break;
                    default:
                        originPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                        marketPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
                        break;
                }
            }
        } else {
            originPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
            marketPrice = BigDecimal.valueOf(prdCustPrice.getCostPrice());
        }
        BigDecimal[] res = new BigDecimal[2];
        res[0] = originPrice;
        res[1] = marketPrice;
        return res;

    }


    @Override
    public Map<String, Object> agentBills(Long memberId, Integer levelId, Integer timeAreaType, Long ownShopid) {
        Map<String, Object> agentBillsMap = new HashMap<>();
        Set<Integer> userIds = AppUtil.findSubUserByMember(memberId);
        //先查询当前登录用户有无客户，无客户返回空结果
        OaAgentMasRequest param = new OaAgentMasRequest();
        param.setMemberId(memberId.toString());
        ArrayList<Integer> userIdArr = new ArrayList<>(userIds);
        param.setUserIds(userIdArr);
        PageList<OaAgentMasListVo> agentCustList = oaAgentMasMapper.queryAgentList(param);
        if (agentCustList != null && agentCustList.size() > 0) {
            //获取客户ids
            List<Integer> agentCustIds = new ArrayList<>();
            agentCustList.forEach(agentCust -> {
                agentCustIds.add(agentCust.getAgentId());
            });
            //根据dateType得到起止时间
            Date beginDate = null;
            Date endDate = new Date();
            if (timeAreaType == 1) {//本月
                beginDate = DateTool.getMonthBeginByNowDate();
            } else if (timeAreaType == 2) {//本季
                beginDate = DateTool.getQuarterBeginByNowDate();
            } else if (timeAreaType == 3) {//本周
                beginDate = DateTool.getWeekBeginByNowDate();
            }
            AgentBillDTO agentBillDTO = new AgentBillDTO();
            agentBillDTO.setAgentCustIds(agentCustIds);
            //账单数量统计不按照客户等级
            //agentBillDTO.setLevelId(levelId);
            agentBillDTO.setBeginDate(beginDate);
            agentBillDTO.setEndDate(endDate);
            agentBillDTO.setShopId(ownShopid);
            //先统计账单总数
            agentBillDTO.setUserIds(userIds);
            Integer totalNumber = orderDao.countAgentBills(agentBillDTO);
            if (totalNumber > 0) {
                //统计已付清账单总数
                Integer colsedTotalNumber = orderDao.countClosedAgentBills(agentBillDTO);
                //统计欠款账单总数(账单总数-已付清账单总数)
                //Integer notColsedTotalNumber = orderDao.countNotClosedAgentBills(agentBillDTO);
                Integer notColsedTotalNumber;
                if (colsedTotalNumber == null) {
                    notColsedTotalNumber = totalNumber;
                } else {
                    notColsedTotalNumber = totalNumber - colsedTotalNumber;
                }
                //查询账单列表
                agentBillDTO.setLevelId(levelId);
                List<AgentBillListVO> agentBills = orderDao.agentBills(agentBillDTO);
                //账单状态处理
                if (CollectionUtils.isNotEmpty(agentBills)) {
                    Set<Long> orderIds = new HashSet<>();
                    for (AgentBillListVO vo : agentBills) {
                        if (vo.getStatusStr().contains("2")) {
                            vo.setStatus(2);
                        } else {
                            vo.setStatus(1);
                        }
                        orderIds.add(vo.getOrderId());
                    }
                    List<AgentBillListVO> dataList = orderDao.findOrderStatusByIds(orderIds);
                    if (CollectionUtils.isNotEmpty(dataList)) {
                        for (AgentBillListVO vo : agentBills) {
                            for (AgentBillListVO data : dataList) {
                                if (vo.getOrderId().longValue() == data.getOrderId().longValue()) {
                                    vo.setStatus(data.getStatus());
                                }
                            }
                        }
                    }
                }
                agentBillsMap.put("totalNumber", totalNumber);
                agentBillsMap.put("colsedTotalNumber", colsedTotalNumber);
                agentBillsMap.put("notColsedTotalNumber", notColsedTotalNumber);
                agentBillsMap.put("agentBills", agentBills);
            } else {
                this.putEmptyResultMap(agentBillsMap);
            }
        } else {
            this.putEmptyResultMap(agentBillsMap);
        }
        return agentBillsMap;
    }


    /**
     * 设置客户账单空结果map
     *
     * @param agentBillsMap
     */
    private void putEmptyResultMap(Map<String, Object> agentBillsMap) {
        agentBillsMap.put("totalNumber", 0);
        agentBillsMap.put("colsedTotalNumber", 0);
        agentBillsMap.put("notColsedTotalNumber", 0);
        agentBillsMap.put("agentBills", null);
    }

    /**
     * 设置订单商品信息
     *
     * @param orderList
     * @return
     */
    private List<OrderListVo> setOrderGoods(List<OrderListVo> orderList) {
        if (orderList != null && orderList.size() > 0) {
            for (OrderListVo orderListVo : orderList) {
                Integer goodscnt = 0;
                List<OrderGoodsListVo> orderGoods = orderGoodsDao.findAppOrderGoodsListByPage(orderListVo.getOrderId());
                orderListVo.setOrderGoodsListVos(orderGoods);
                for (OrderGoodsListVo orderGood : orderGoods) {
                    goodscnt += orderGood.getCount();
                }
                orderListVo.setGoodscnt(goodscnt);
            }
        }
        return orderList;
    }

}
