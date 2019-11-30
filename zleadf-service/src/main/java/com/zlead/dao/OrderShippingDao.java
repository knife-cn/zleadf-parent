package com.zlead.dao;

import com.zlead.entity.OrderShippingEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.util.Map;

/**
 * 发货管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-14 10:13:01
 */
public interface OrderShippingDao {

    void insert(OrderShippingEntity entity);

    void update(OrderShippingEntity entity);

    void delete(Long id);

    PageList<OrderShippingEntity> findPage(Map params, PageBounds rowBounds);

    OrderShippingEntity findById(Long id);

    OrderShippingEntity findByOrderId(Long orderId);

    /**
     * 通过快递单号查询信息
     * @param deliveryNumber 快递单号
     * @return
     */
    OrderShippingEntity findByDeliveryNumber(String deliveryNumber);
	
}
