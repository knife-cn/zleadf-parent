package com.zlead.dao;

import com.zlead.entity.vo.PayTypeVo;

import java.util.List;

public interface PayTypeDao {

    /**
     * 根据店铺ID查询
     * @param shopId
     * @return
     */
    List<PayTypeVo> findPayTypesByShopId(Long shopId);

    /**
     * 查询平台支付方式
     * @return
     */
    List<PayTypeVo> findPayTypes();
}
