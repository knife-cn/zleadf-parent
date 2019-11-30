package com.zlead.reception.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.vo.OrderVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */

@Transactional
@Service
public interface OrderService {

    PageList<OrderEntity> getPage(Map params, PageBounds rowBounds);

    PageList<OrderEntity> findPage(@Param("memberId") Long memberId, Map params, PageBounds rowBounds);

    int findSize(@Param("memberId") Long memberId, @Param("status") Integer status);

    void save(OrderEntity entity);

    void update(OrderEntity entity);

    void delete(Long id);

    OrderEntity findById(Long id);

    OrderEntity findBySn(String sn);

    Map<String, Object> confirmOrder(String goodsId, String buyNum, MemberEntity memberEntity);

    Map<String, Object> confirmActOrder(Long actId, String goodsId, String buyNum, MemberEntity memberEntity);

    Map<String, Object> addOrder(OrderEntity orderEntity, Long addressId, Integer buyNum, Long goodsId, MemberEntity memberEntity, HttpServletRequest request);

    Map<String, Object> addActOrder(Long actId, OrderEntity orderEntity, Long addressId, Integer buyNum, Long goodsId, MemberEntity memberEntity, HttpServletRequest request);

    List<Map<String, Object>> cartConfirmOrder(
            MemberEntity memberEntity, List<Integer> cartIds);

    Map<String, Map<String, Object>> cartAddOrder(OrderEntity orderEntity, List<Integer> cartIds, List<Long> shopIds, Long addressId, MemberEntity memberEntity, HttpServletRequest request);

    void payCallBack(String orderSn, String paySn, int payChannel, String systemId, String total_amount);

    PageList<OrderVo> shopOrderList(boolean isPointOrder, String status, int pageNum, int size, MemberEntity member, Integer orderType, Long shopId);

    PageList<OrderEntity> noVoucherOrderList(Long memberId, PageBounds rowBounds);

    PageList<OrderEntity> getAllnoVoucherOrderList(String memberIds, PageBounds rowBounds);

    PageList<OrderVo> commonGoodsOrder(String status, int pageNum, int size, MemberEntity member, Integer orderType, Long shopId);

    /**
     * 取消订单
     *
     * @param orderId
     * @param memberEntity
     * @return
     */
    boolean cancelOrder(Long orderId, MemberEntity memberEntity);

    /**
     * 订单付款
     *
     * @param orderId
     * @return
     */
    boolean payOrder(Long orderId, HttpServletRequest request);

    /**
     * 订单收货
     *
     * @param orderId
     * @param memberEntity
     * @return
     */
    public boolean recevOrder(Long orderId, MemberEntity memberEntity);

    /**
     * 订单发货
     *
     * @param orderId
     * @return
     */
    public boolean sendOrder(Long orderId);


    /**
     * 恢复冻结库存
     *
     * @param buyNum
     * @param goodsId
     */
    void releaseFreezeStock(int buyNum, Long goodsId);

    /**
     * 销售订单查询
     *
     * @param memberId
     * @param pageSize
     * @param size
     * @param status
     * @return
     */
    PageList<OrderEntity> sellOrderList(Long memberId, Integer pageSize, Integer size, Integer status);

    /**
     * 根据订单id发货
     */
    public Boolean sendGoodsOrder(OrderEntity order);

    public void paySuccess(String sn);

    /**
     * 查询订单
     */
    List<OrderEntity> getOrderInfoCus(@Param("memberId") Long memberId, PageBounds pageBounds, Integer status);

//    /**
//     * 查询订单
//     */
//    List<OrderEntity> findOrderList(@Param("memberId") Long memberId);

    /**
     * 查询商家订单
     */
    public List<OrderEntity> getShopOrderList(Long shopId, PageBounds pageBounds, Integer status);


    /**
     * 订单状态 逻辑更改 删除
     * ytchen
     * 2019-1-18
     *
     * @param orderId
     * @return
     */
    int deleteorder(Long orderId);

    /**
     * 查询订单详情
     */
    Map<String,Object> getOrderInfoId(HttpServletRequest request,@Param("id") Long orderId);


    /**
     * 订单凭证
     * @param orderId
     * @return
     */
//    List<Voucher> selectByOrderi(@Param(value = "orgId") int orderId);

    /**
     * 查询订单 -状态
     * 状态(0-待付款，1-待发货，2-待收货，3-已完成 , 4已删除）
     */
    List<OrderEntity> getOrderInfoSta(@Param("memberId") Long memberId, @Param("status") int status);

    //状态(0-待付款，1-待发货，2-待收货，3-已完成,4已取消)
    PageList<OrderEntity> findPageSt(@Param("status") Integer status, @Param("memberId") Long memberId, Map params, PageBounds rowBounds);

    /*查询用户订单数*/
    PageList<OrderEntity> findPageSt2(@Param("status") Integer status, @Param("memberId") Long memberId, Map params, PageBounds rowBounds);


    /**
     * 立即支付，选择支付方式
     */
    void payType(Long orderId, Integer payType);

    /**
     * 查询订单编号是否重复，大于0表示重复
     *
     * @param
     * @return
     * @author 廖巨会
     * @date
     */
    Integer queryOrderNumber(@Param("sn") String sn);

    /*查询订单总数*/
    int findTotalOrderSize(Long memberId);

    /**
     * 封装更新订单联系人名称和电话方法
     * @param agentId
     * @param shopId
     * @return
     */
    OrderEntity updateOrderNameAndTel(Long agentId,Long shopId);
}

