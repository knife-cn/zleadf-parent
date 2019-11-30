package com.zlead.dao;


import com.zlead.entity.ExpressSubscription;

public interface ExpressSubscriptionDao {
    int deleteByPrimaryKey(Long expressSubscriptionId);

    int insert(ExpressSubscription record);

    int insertSelective(ExpressSubscription record);

    ExpressSubscription selectByPrimaryKey(Long expressSubscriptionId);

    int updateByPrimaryKeySelective(ExpressSubscription record);

    int updateByPrimaryKey(ExpressSubscription record);
}