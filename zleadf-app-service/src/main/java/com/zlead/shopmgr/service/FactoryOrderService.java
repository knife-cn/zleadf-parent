package com.zlead.shopmgr.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.OrderGoodsEntity;
import com.zlead.entity.dto.OrderGoodsDto;
import com.zlead.entity.vo.*;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface FactoryOrderService {
    /**
     * 分页查询销售订单列表
     * @param status
     * @param pageNum
     * @param pageSize
     * @param member
     * @return
     */
    PageList<OrderEntity> shopOrderList(String status, String sn, int pageNum, int pageSize, MemberEntity member);

    /**
     * 订单详情列表
     * @param pageNum
     * @param pageSize
     * @param orderSn
     * @param memberId
     * @return
     */
    PageList<OrderGoodsEntity> shopOrderDetailsList(int pageNum,int pageSize,String orderSn,Long memberId);

    BigDecimal shopOrderTotalAmount(Long shopId);

    Long shopOrderTotalCount(Long shopId);

    ShopIndexVo shopIndex(Long memberId);

    List<OrderListVo> appShopOrderList(Long memberId,String status, String sn, String time, String agentId, int pageNum, int pageSize);

    OrderDetailVo appShopOrderDetails(Long orderId, Long memberId);

    List<OrderGoodsDetailVo> appShopOrderGoodsDetailsList(int pageNum, int pageSize,Long orderId);

    List<OrderCountVo> findOrderListCount(String sn, String time, String agentId,Long memberId);

    OrderCountStatisticsVo findOrderListStatisticsCount(String sn, String time, String agentId,Long memberId);

    /**
     * 代理商(客户)交易统计
     * @param agentId
     * @param memberEntity
     * @return
     */
    Map<String, Object> agentTradeSummary(Integer agentId, MemberEntity memberEntity);

    /**
     * 查询代理商(客户)待还款订单
     * @param pageNum
     * @param pageSize
     * @param agentId
     * @param ownShopid
     * @return
     */
    List<OrderListVo> agentWaitReturnOrder(Integer pageNum, Integer pageSize, Integer agentId, Long ownShopid);

    /**
     * 查询代理商(客户)订单
     * @param pageNum
     * @param pageSize
     * @param agentId
     * @param ownShopid
     * @return
     */
    List<OrderListVo> agentOrderList(Integer pageNum, Integer pageSize, Integer agentId, Long ownShopid);

    int generateAppOrder(OrderGoodsDto orderGoodsDto,MemberEntity memberEntity, Long shopId, HttpServletRequest request) throws Exception;


    /**
     * 客户账单列表
     * @param memberId
     * @param levelId
     * @param timeAreaType
     * @param ownShopid
     * @return
     */
    Map<String, Object> agentBills(Long memberId, Integer levelId, Integer timeAreaType, Long ownShopid);

    /**
     * 待办事项
     * @param memberId

     * @param pageNum
     * @param pageSize
     * @return
     */
    List<OrderListVo> appShopUpcomingOrderList(Long memberId, int pageNum, int pageSize);

    /**
     * 配送方式列表
     * @param shopId
     * @return
     */
    List<ShippingTypeVo> findShippingTypes(Long shopId);

    /**
     * 支付方式列表
     * @param shopId
     * @return
     */
    List<PayTypeVo> findPayTypes(Long shopId);
}
