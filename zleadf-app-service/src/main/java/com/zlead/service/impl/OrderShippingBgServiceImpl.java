package com.zlead.service.impl;

import com.zlead.dao.ExpressCompanyDao;
import com.zlead.dao.OrderDao;
import com.zlead.dao.OrderShippingDao;
import com.zlead.entity.ExpressCompanyEntity;
import com.zlead.entity.OrderEntity;
import com.zlead.entity.OrderShippingEntity;
import com.zlead.service.OrderShippingBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Transactional
@Service
public class OrderShippingBgServiceImpl implements OrderShippingBgService {
    @Autowired
    private OrderShippingDao orderShippingDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ExpressCompanyDao expressCompanyDao;

    @Transactional(readOnly = true)
    public PageList<OrderShippingEntity> getPage(Map params, PageBounds rowBounds) {
        PageList list = orderDao.getPage(params, rowBounds);
        return list;
    }

    @Transactional(readOnly = true)
    public OrderShippingEntity findById(Long id) {
        return orderShippingDao.findById(id);
    }

    public void save(OrderShippingEntity entity) {
        orderShippingDao.insert(entity);
    }

    public void update(OrderShippingEntity entity) {
        orderShippingDao.update(entity);
    }

    public void delete(Long id) {
        orderShippingDao.delete(id);
    }

    /**
     * 订单发货
     */
    public void saveOrderShipping(OrderShippingEntity entity,Long userId,Integer sendType) {
        //修改订单的状态
        OrderEntity orderEntity = orderDao.findById(entity.getOrderId());
        if(orderEntity!=null){
            //先查询是否有该订单的物流
            OrderShippingEntity OrderShippingEntity = orderShippingDao.findByOrderId(entity.getOrderId());
            if(OrderShippingEntity == null){
                entity.setCreatorId(userId);
                entity.setCreateDate(new Date());
                entity.setSendType(sendType);
                orderShippingDao.insert(entity);
                //修改订单的状态
                orderEntity.setStatus(2);//已发货，待收货状态
                orderEntity.setShippingCorpno(entity.getExCmpNo());
                //查询物流公司信息
                ExpressCompanyEntity expressCompanyEntity = expressCompanyDao.findByComNo(entity.getExCmpNo());
                if(expressCompanyEntity!=null&&expressCompanyEntity.getComName()!=null){
                    orderEntity.setShippingCorpname(expressCompanyEntity.getComName());
                }
                orderEntity.setDeliveryNo(entity.getDeliveryNumber());
                orderDao.update(orderEntity);
            }
        }
    }

}
