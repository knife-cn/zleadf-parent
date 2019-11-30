package com.zlead.reception.service;

import com.zlead.entity.OrderGoodsEntity;

import java.util.List;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

/**
 * 订单商品
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 10:30:49
 */
public interface OrderGoodsService {

    PageList<OrderGoodsEntity> getPage(Map params, PageBounds rowBounds);

    void save(OrderGoodsEntity entity);

    void update(OrderGoodsEntity entity);

    void delete(Long id);

    OrderGoodsEntity findById(Long id);

    List<OrderGoodsEntity> findListByOrderId(@Param(value = "orderId") Long orderId);
}

