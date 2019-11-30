package com.zlead.dao;

import com.zlead.entity.OrderEntity;
import com.zlead.entity.OrderGoodsEntity;
import com.zlead.entity.dto.AgentBillDTO;
import com.zlead.entity.vo.*;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 订单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
 
public interface OrderDao {

    void insert(OrderEntity entity);

    boolean update(OrderEntity entity);

    int updateorder(OrderEntity entity);

    void delete(Long id);
//订单查询所有
    PageList<OrderEntity> findPage(@Param("memberId") Long memberId,Map params, PageBounds rowBounds);
    PageList<OrderEntity> getPage(Map params, PageBounds rowBounds);
//    总条数
    int findSize(@Param("memberId") Long memberId,@Param("status")Integer status);

    /**订单总条数*/
    int findTotalSize(@Param("memberId") Long memberId);



    //状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消)
    PageList<OrderEntity> findPageSt(@Param("status") Integer status,@Param("memberId")Long memberId,Map params, PageBounds rowBounds);

    PageList<OrderEntity> findPageSt2(@Param("status") Integer status,@Param("memberId")Long memberId,Map params, PageBounds rowBounds);

    PageList<OrderEntity> findOderListByStatus(@Param("status") Integer status,@Param("memberId")Long memberId,Map params, PageBounds rowBounds);


    List<OrderGoodsEntity> findOrderGoods( @Param("orderIdList") List<Long> orderIdList);

    PageList<OrderEntity> findNoVoucherOrder(@Param("memberId") Long memberId, PageBounds rowBounds);

    PageList<OrderEntity> getAllnoVoucherOrderList(@Param("memberIds") String memberIds, PageBounds rowBounds);

    OrderEntity findById(Long id);

    OrderEntity findBySn(String sn);

    PageList<OrderVo> findPageOrderVo(Map params, PageBounds rowBounds);

    /**
     * 查询待支付七天以上的订单
     * */
    List<OrderEntity> findOrderSeven();

    /**
     * 查询订单信息
     */
    List<OrderEntity> findOrderList(Map params, PageBounds pageBounds);

    /**
     * 查询销售订单信息
     */
    PageList<OrderEntity> findSellOrderList(Map params);

    /**
     * 分页查询订单信息
     * @param params
     * @param rowBounds
     * @return
     */
    PageList<OrderEntity> findSellOrderListByPage(Map params,PageBounds rowBounds);

    /**
     * 查询订单
     */
    List<OrderEntity> getOrderInfoCus(@Param("memberId") Long memberId);

    /**
     * 查询订单详情
     */
    OrderEntity getOrderInfoId(@Param("id") Long orderId);

    /**
     * 查询订单 -状态
     * 状态(0-待付款，1-待发货，2-待收货，3-已完成）
     */
    List<OrderEntity> getOrderInfoSta(@Param("memberId") Long memberId,@Param("status") int status);


    /**
     * 关联凭证
     * 传入order和凭证id
     */
    void updateVoucher(@Param("orderIds")List<Integer> orderIds,@Param("voucherId")int voucherId);

    BigDecimal getOrderTotalAmount(Long shopId);

    Long getOrderTotalCount(Long shopId);

    Map getIndexInfo(@Param("memberId")Long memberId,@Param("userIds")Set<Integer> userIds,@Param("agentIds")Set<Integer> agentIds);

    Map getIndexInfoAuth(@Param("id")Long id, @Param("memberIds")List<Long> memberIds);

    List<OrderListVo> findAppOrderListByPage(Map params, PageBounds rowBounds);

    /**
     * 只查询待办事项
     * @param params
     * @param rowBounds
     * @return
     */
    List<OrderListVo> findAppOrderUpComingListByPage(Map params, PageBounds rowBounds);

    OrderDetailVo findAppOrderDetail(Map params);

    OrderCountVo findOrderListCount(Map params);

    /**
     * 统计订单总数量
     *
     * @param timeType  取值为month时，获取的是月订单总量，否则获取的是总订单数量
     */
    Integer findStatisticsOrderListCount(@Param("memberId") Long memberId, @Param("userIds") Set<Integer> userIds, @Param("agentIds") Set<Integer> agentIds,@Param("timeType")String timeType);

    /**
     * 统计代理商(客户)交易总金额
     * @param agentId
     * @param ownShopid
     * @return
     */
    BigDecimal countOrderAmountByAgentId(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid,@Param("userIds") Set<Integer> userIds);

    /**
     * 统计代理商(客户)交易总单数
     * @param agentId
     * @param ownShopid
     * @return
     */
    Integer countOrderNumberByAgentId(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid,@Param("userIds") Set<Integer> userIds);

    /**
     * 统计代理商(客户)代还款总金额
     * @param agentId
     * @param ownShopid
     * @return
     */
    BigDecimal countWaitReturnOrderAmountByAgentId(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid);

    /**
     * 查询代理商(客户)待还款订单
     * @param agentId
     * @param pageBounds
     * @return
     */
    List<OrderListVo> queryWaitReturnOrderByAgentId(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid, PageBounds pageBounds);

    /**
     * 查询代理商(客户)订单列表
     * @param agentId
     * @param pageBounds
     * @return
     */
    List<OrderListVo> queryOrderByAgentId(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid, PageBounds pageBounds);

    /**
     *统计账单总数
     * @param agentBillDTO
     * @return
     */
    Integer countAgentBills(AgentBillDTO agentBillDTO);

    /**
     *统计已付清账单总数
     * @param agentBillDTO
     * @return
     */
    Integer countClosedAgentBills(AgentBillDTO agentBillDTO);

    /**
     *统计欠款账单总数
     * @param agentBillDTO
     * @return
     */
    Integer countNotClosedAgentBills(AgentBillDTO agentBillDTO);

    /**
     * 账单列表
     * @param agentBillDTO
     * @return
     */
    List<AgentBillListVO> agentBills(AgentBillDTO agentBillDTO);


    boolean orderovid(@Param("id")long orderId);

    /** 查询订单编号是否重复，大于0表示重复
     * @author 廖巨会
     * @date
     * @param
     * @return
     */
    Integer queryOrderNumber(@Param("sn")String sn);

    /**
     * App查询下级订单接口（权限）
     * @param params
     * @param pageBounds
     * @return
     */
    List<OrderListVo> findAppOrderListByPageAuth(Map<String, Object> params, PageBounds pageBounds);


    OrderCountVo findOrderListCountAuth(Map params);

    Integer findStatisticsOrderListCountAuth(@Param("id") Long id,@Param("memberIds") List<Long> memberIds,@Param("timeType")String timeType);

    /**
     * 根据订单ID查询订单是否已付清欠款
     * @param orderIds
     * @return
     */
    List<AgentBillListVO> findOrderStatusByIds(@Param("orderIds")Collection<Long> orderIds);

    /**
     * 统计代理商(客户)已付款总金额
     * @param agentId
     * @param ownShopid
     * @return
     */
    BigDecimal countPaidByAgentAndShop(@Param("agentId") Integer agentId, @Param("shopId")Long ownShopid,@Param("userIds") Set<Integer> userIds);

    OrderEntity findOrderByVoucherId(@Param("id")int id,@Param("memberId")Long memberId);

    BigDecimal findWeekAmt(Long memberId);

    BigDecimal findMonthAmt(Long memberId);

    BigDecimal findSeasonAmt(Long memberId);

    /**
     * 根据代理人和店铺查询待还款订单列表
     * @param agentId
     * @param shopId
     * @param userIds
     * @return
     */
    List<PendingPaymentOrder> findPendingPaymentListByAgentAndShop(@Param("agentId") Integer agentId, @Param("shopId")Long shopId,@Param("userIds") Set<Integer> userIds);

}
