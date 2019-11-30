package com.zlead.dao;

import com.zlead.entity.vo.ShippingTypeVo;

import java.util.List;

public interface ShippingTypeDao {

    List<ShippingTypeVo> findShippingTypes(Long shopId);

}
