package com.zlead.reception.service.impl;

import com.zlead.dao.OrderGoodsDao;
import com.zlead.entity.OrderGoodsEntity;
import com.zlead.reception.service.OrderGoodsService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
    @Autowired
    private OrderGoodsDao orderGoodsDao;

    @Transactional(readOnly = true)
    public PageList<OrderGoodsEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = orderGoodsDao.findPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public OrderGoodsEntity findById(Long id) {
        return orderGoodsDao.findById(id);
    }

    public void save(OrderGoodsEntity entity) {
        orderGoodsDao.insert(entity);
    }

    public void update(OrderGoodsEntity entity) {
        orderGoodsDao.update(entity);
    }

    public void delete(Long id) {
        orderGoodsDao.delete(id);
    }

    public List<OrderGoodsEntity> findListByOrderId(Long orderId){

        return  orderGoodsDao.findListByOrderId(orderId);
    }

}
