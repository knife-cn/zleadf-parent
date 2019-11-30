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
import com.zlead.fplat.dao.AgentAddressMapper;
import com.zlead.fplat.dao.AgentFacMapper;
import com.zlead.fplat.dao.OaAgentMasMapper;
import com.zlead.fplat.dao.OaAgentUserinfoMapper;
import com.zlead.fplat.entity.*;
import com.zlead.shopmgr.service.FactoryOrderService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import com.zlead.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
        if (shopId != null && isShop(member)) {
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
        Map map = orderDao.getIndexInfo(memberId);
        ShopIndexVo shopIndexVo = new ShopIndexVo();
        shopIndexVo.setCustomerCount(map.get("customerCount") != null ? (Long) map.get("customerCount") : 0L);
        shopIndexVo.setOrderCount(map.get("orderCount") != null ? (Long) map.get("orderCount") : 0L);
        shopIndexVo.setTotalCountMonth(map.get("totalCountMonth") != null ? (Long) map.get("totalCountMonth") : 0L);
        shopIndexVo.setTotalCountSeason(map.get("totalCountSeason") != null ? (Long) map.get("totalCountSeason") : 0L);
        shopIndexVo.setTotalCountWeek(map.get("totalCountWeek") != null ? (Long) map.get("totalCountWeek") : 0L);
        shopIndexVo.setTotalAmountMonth(map.get("totalAmountMonth") != null ? (BigDecimal) map.get("totalAmountMonth") : new BigDecimal(0));
        shopIndexVo.setTotalAmountSeason(map.get("totalAmountSeason") != null ? (BigDecimal) map.get("totalAmountSeason") : new BigDecimal(0));
        BigDecimal bg = new BigDecimal(map.get("finishRateMonth") != null ? (Double) map.get("finishRateMonth") : 0);
        double f1 = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg2 = new BigDecimal(map.get("finishRateSeason") != null ? (Double) map.get("finishRateSeason") : 0);
        double f2 = bg2.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal bg3 = new BigDecimal(map.get("finishRateWeek") != null ? (Double) map.get("finishRateWeek") : 0);
        double f3 = bg3.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        shopIndexVo.setTotalAmountWeek(map.get("totalAmountWeek") != null ? (BigDecimal) map.get("totalAmountWeek") : new BigDecimal(0));
        shopIndexVo.setFinishRateMonth(f1);
        shopIndexVo.setFinishRateSeason(f2);
        shopIndexVo.setFinishRateWeek(f3);
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
    public List<OrderListVo>  appShopOrderList(Long memberId,String status, String sn, String time, String agentId, int pageNum, int pageSize) {
        List<OrderListVo> orderList = null;
        Map<String, Object> params = new HashMap<>();
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        params.put("memberId",memberId);
        params.put("status", status);
        params.put("sn", sn);
        params.put("time", time);
        params.put("agentId", agentId);
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
            orderVo.setPayType(orderListVo.getPayType());
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
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @author leiningbo
     * @date 2019年4月26日15:45:04
     * @return
     */
    @Override
    public List<OrderListVo> appShopUpcomingOrderList(Long memberId, int pageNum, int pageSize) {
        List<OrderListVo> orderList = null;
        Map<String, Object> params = new HashMap<>();
        PageBounds pageBounds = new PageBounds(pageNum, pageSize);
        params.put("memberId",memberId);
        orderList = orderDao.findAppOrderUpComingListByPage(params, pageBounds);
        List<OrderListVo> list = new ArrayList<>();
        for (OrderListVo orderListVo : orderList) {
            Integer goodscnt = 0;
            OrderListVo orderVo = new OrderListVo();
            List<OrderGoodsListVo> orderGoods = orderGoodsDao.findAppOrderGoodsListByPage(orderListVo.getOrderId());
            orderVo.setAgentId(orderListVo.getAgentId());
            orderVo.setAgentName(orderListVo.getAgentName());
            orderVo.setGoodsAmount(orderListVo.getGoodsAmount());
            orderVo.setOrderId(orderListVo.getOrderId());
            orderVo.setPayType(orderListVo.getPayType());
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

    @Override
    public List<ShippingTypeVo> findShippingTypes(Long shopId) {
        return shippingTypeDao.findShippingTypes(shopId);
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

        orderDetailVo.setCreateDate(orderDetail.getCreateDate().substring(0,orderDetail.getCreateDate().indexOf(".")));
        orderDetailVo.setShippingType(orderDetail.getShippingType());

        List<OperatorLog> listOpLogs = operatorlogDao.getListOpLogs(orderId);
        Date date = new Date();
        if (null!=listOpLogs&&listOpLogs.size()>=2){
            for (OperatorLog log:listOpLogs){
                if ("PAY".equals(log.getTitle())){
                    date=log.getCreateDate();
                }
            }
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderDetailVo.setPayDate(sdf.format(date).toString());
        }else {
            orderDetailVo.setPayDate(orderDetail.getPayDate());
        }
        orderDetailVo.setOrderGoodsListVos(orderGoodsListVos);
        for (OrderGoodsListVo orderGood : orderGoodsListVos) {
            goodscnt += orderGood.getCount();
        }
        orderDetailVo.setGoodscnt(goodscnt);
        return orderDetailVo;
    }

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
        params.put("sn", sn);
        params.put("time", time);
        params.put("agentId", agentId);
        params.put("memberId", memberId);
        Integer total = 0;
        List<OrderCountVo> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            params.put("status",i);
            OrderCountVo orderListCount = orderDao.findOrderListCount(params);
            if(orderListCount!=null){
                orderListCount.setStatus(i);
                total+=orderListCount.getOrdercnt();
                list.add(orderListCount);
            }else{
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
        int monthCnt = orderDao.findStatisticsOrderListCount(memberId, "month");
        int totalCnt = orderDao.findStatisticsOrderListCount(memberId, null);

        OrderCountStatisticsVo returnVO = new OrderCountStatisticsVo();
        returnVO.setMonthCnt(monthCnt);
        returnVO.setTotalCnt(totalCnt);
        return returnVO;
    }

    @Override
    public Map<String, Object> agentTradeSummary(Integer agentId, Long ownShopid) {
        Map<String, Object> results = new HashMap<>();
        //统计客户交易总额
        BigDecimal totalAmount = orderDao.countOrderAmountByAgentId(agentId, ownShopid);
        //统计客户交易总单数
        int totalNumber = orderDao.countOrderNumberByAgentId(agentId, ownShopid);
        //统计客户代还款金额
        BigDecimal waitReturnAmount = orderDao.countWaitReturnOrderAmountByAgentId(agentId, ownShopid);
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
            isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public int generateAppOrder(OrderGoodsDto orderGoodsDto,MemberEntity member, Long shopId, HttpServletRequest request) {
        MemberAddressEntity addressEntity = memberAddressDao.findById(orderGoodsDto.getOrderMemberInfoVo().getAddressId());
        String proviceName=memberAddressDao.findRegionName(addressEntity.getProvinceId().toString());
        String cityName=memberAddressDao.findRegionName(addressEntity.getCityId().toString());
        String areaName=memberAddressDao.findRegionName(addressEntity.getRegionId().toString());
        OrderEntity orderEntity = null;
        if (addressEntity != null) {
            OaAgentMas oaAgentMas = oaAgentMasMapper.selectByPrimaryKey(orderGoodsDto.getOrderMemberInfoVo().getAgentId());
            MemberEntity memberEntity = memberDao.findByAgentId(oaAgentMas.getAgentId());
            AclUserEntity aclUserEntity = aclUserDao.findUserByMemberId(member.getId());
            OaAgentUserinfo oaAgentUserinfo = oaAgentUserinfoMapper.findOaAgentUser(orderGoodsDto.getOrderMemberInfoVo().getAgentId(),shopId);
            ShopEntity shopEntity = shopDao.findById(shopId);
            AgentFac agentFac = agentFacMapper.findByAgentIdAndShopId(oaAgentMas.getAgentId(), shopId.intValue());
            AgentAddress agentAddress = agentAddressMapper.findAddressRevByIds(orderGoodsDto.getOrderMemberInfoVo().getAgentId(),shopId);
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
            orderEntity.setAddress(proviceName+cityName+areaName+addressEntity.getAddress());
            orderEntity.setPostCode(addressEntity.getPostcode());
            orderEntity.setPhone(addressEntity.getPhone());
            orderEntity.setEmail(addressEntity.getEmail());
            orderEntity.setCreateId(aclUserEntity.getUserId());
            orderEntity.setNeedInvoide(0);
            orderEntity.setCreateDate(new Date());
            orderEntity.setIsExpire(0);//是否过期
            orderEntity.setIsDelete(0);
            orderEntity.setStatus(0);
            orderEntity.setUserId(agentFac.getUserId());
            orderEntity.setDiscount(agentFac.getAgentDiscount()==null?1f:agentFac.getAgentDiscount().floatValue());
            orderEntity.setContactMan(oaAgentUserinfo.getUserName());
            orderEntity.setContactTel(oaAgentUserinfo.getLinkTel());
            orderEntity.setModifyDate(new Date());
            //添加金额
            BigDecimal shippingCost = BigDecimal.ZERO;
            orderEntity.setShippingCost(shippingCost);
            BigDecimal goodsAmount = BigDecimal.ZERO;
            //保存订单信息
            orderEntity.setMemberId(memberEntity.getId());
            for (GoodsVo goodsVo : orderGoodsDto.getGoodsVos()) {
                GoodsEntity goodsEntity = goodsDao.findById(goodsVo.getGoodsId());
                if (goodsEntity != null) {
                    goodsAmount = goodsAmount.add(goodsEntity.getShowPrice().multiply(new BigDecimal(goodsVo.getCount())));
                    //进行四舍五入
                    goodsAmount = goodsAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
                } else {
                    return 0;
                }
            }
            orderEntity.setGoodsAmount(goodsAmount);
            orderEntity.setPayableAmount(goodsAmount.add(shippingCost));
            orderEntity.setActualPayments(goodsAmount.add(shippingCost));
            orderEntity.setPayType(orderGoodsDto.getOrderMemberInfoVo().getPayType());
            orderEntity.setShippingType(orderGoodsDto.getOrderMemberInfoVo().getShippingType());
            orderEntity.setShippingCost(new BigDecimal(0));
            orderDao.insert(orderEntity);
            for (GoodsVo goodsVo : orderGoodsDto.getGoodsVos()) {
                GoodsEntity goodsEntity = goodsDao.findById(goodsVo.getGoodsId());
                OrderGoodsEntity orderGoodsEntity = new OrderGoodsEntity();
                orderGoodsEntity.setGoodsId(goodsEntity.getId());
                orderGoodsEntity.setGoodsName(goodsEntity.getFullName());
                orderGoodsEntity.setGoodsImg(goodsEntity.getFirstImg());
                orderGoodsEntity.setPrice(goodsEntity.getShowPrice());
                orderGoodsEntity.setDiscount(goodsEntity.getDiscount());
                orderGoodsEntity.setGoodsPoint(goodsEntity.getPoint());
                orderGoodsEntity.setPrice(goodsEntity.getShowPrice());
                orderGoodsEntity.setPrice(goodsEntity.getShowPrice());
                orderGoodsEntity.setCommentStatus(0);
                orderGoodsEntity.setStatus(0);
                orderGoodsEntity.setCreateDate(new Date());
                orderGoodsEntity.setCount(goodsVo.getCount());
                orderGoodsEntity.setOrderId(orderEntity.getId());
                orderGoodsEntity.setGoodsTotalPrice((goodsEntity.getShowPrice().multiply(new BigDecimal(goodsVo.getCount()))).setScale(2,BigDecimal.ROUND_HALF_UP));
                //保存订单商品信息
                orderGoodsDao.insert(orderGoodsEntity);
                //往日志表里插入一条记录
                OperatorLog operatorLog = new OperatorLog();
                operatorLog.setMemberId(memberEntity.getId().intValue());
                operatorLog.setUserName(memberEntity.getUsername());
                operatorLog.setTitle(TOperatorLogConstant.TITLE_ORDER_CREATE);
                operatorLog.setRemark("APP" + memberEntity.getId() + "用户创建订单");
                operatorLog.setCategory(TOperatorLogConstant.CATEGORY_OPERATOR);
                operatorLog.setModule(TOperatorLogConstant.MODULE_ORDER);
                operatorLog.setSn(orderEntity.getId().toString());
                operatorLog.setCreateDate(new Date());
                operatorLog.setOperatorStatus(TOperatorLogConstant.OPERATOR_STATUS_OK);
                operatorLog.setSystemId(TOperatorLogConstant.SOURCE_MGT);
                operatorLog.setLinkUrl(request.getRequestURL().toString());
                operatorLog.setAddIp(request.getRemoteAddr());
                operatorLog.setOperatorParams(JSON.toJSONString(orderGoodsDto));
                operatorlogDao.insertSelectiveOne(operatorLog);
//                //工厂端无减库存操作，可以无限下单
//                goodsDao.addSaleNum(goodsEntity.getId(), goodsVo.getCount(), goodsEntity.getVersion() + 1);
//                goodsDao.minusStock(goodsVo.getCount(), goodsEntity.getId());
//                Integer stock = goodsDao.queryStock(goodsEntity.getId());
//                if (stock < 0) {
//                    throw new StockException("超出库存限制");
//                }
            }
            return 1;
        }
        return 0;
    }


    @Override
    public Map<String, Object> agentBills(Long memberId, Integer levelId, Integer timeAreaType, Long ownShopid) {
        Map<String, Object> agentBillsMap = new HashMap<>();
        //先查询当前登录用户有无客户，无客户返回空结果
        OaAgentMasRequest param = new OaAgentMasRequest();
        param.setMemberId(memberId.toString());
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
            Integer totalNumber = orderDao.countAgentBills(agentBillDTO);
            if (totalNumber > 0) {
                //统计已付清账单总数
                Integer colsedTotalNumber = orderDao.countClosedAgentBills(agentBillDTO);
                //统计欠款账单总数
                Integer notColsedTotalNumber = orderDao.countNotClosedAgentBills(agentBillDTO);
                //查询账单列表
                agentBillDTO.setLevelId(levelId);
                List<AgentBillListVO> agentBills = orderDao.agentBills(agentBillDTO);
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


    private static boolean isShop(MemberEntity member) {
        return member.getMemberType().equals(6);
    }


}
