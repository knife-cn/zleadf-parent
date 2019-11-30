package com.zlead.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 订单
 *
 * @author fqf
 * @date 2018-08-09 15:20:52
 */
public interface OrderBgService {

    PageList<OrderEntity> getPage(Map params, PageBounds rowBounds);

    void save(OrderEntity entity);

    void update(OrderEntity entity);

    void delete(Long id);

    OrderEntity findById(Long id);

    boolean orderovid(@Param("id") long orderId, MemberEntity memberEntity, HttpServletRequest request);
}

