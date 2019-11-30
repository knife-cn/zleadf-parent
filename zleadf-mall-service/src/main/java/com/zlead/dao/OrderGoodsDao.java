package com.zlead.dao;

import com.zlead.entity.OrderGoodsEntity;
import com.zlead.entity.vo.OrderGoodsDetailVo;
import com.zlead.entity.vo.OrderGoodsListVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单商品
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
public interface OrderGoodsDao {

    Integer insert(OrderGoodsEntity entity);

    void update(OrderGoodsEntity entity);

    void delete(Long id);

    PageList<OrderGoodsEntity> findPage(Map params, PageBounds rowBounds);

    OrderGoodsEntity findById(Long id);

    List<OrderGoodsEntity> findListByOrderId(@Param(value = "orderId") Long orderId);

    PageList<OrderGoodsEntity> findOrderDetailsBySn(@Param(value="sn") String sn,@Param(value="shopId") Long shopId, PageBounds rowBounds);

    List<OrderGoodsListVo> findAppOrderGoodsListByPage(@Param(value="orderId") Long orderId);

    List<OrderGoodsDetailVo> findAppOrderGoodsDetail(Map params,PageBounds rowBounds);

}
