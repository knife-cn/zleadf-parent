package com.zlead.service;

import com.zlead.entity.OrderShippingEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 发货管理
 *
 * @author fqf
 * @date 2018-08-14 10:13:01
 */
public interface OrderShippingBgService {

    PageList<OrderShippingEntity> getPage(Map params, PageBounds rowBounds);

    void save(OrderShippingEntity entity);

    void update(OrderShippingEntity entity);

    void delete(Long id);

    OrderShippingEntity findById(Long id);

    void saveOrderShipping(OrderShippingEntity entity,Long userId,Integer sendType);
}

