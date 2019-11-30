package com.zlead.dao;

import com.zlead.entity.LogisticsInformation;

/**
* 物流信息数据库接口方法
* @Author: fqf
**/
public interface LogisticsInformationDao {
    int deleteByPrimaryKey(Long logisticsInformationId);

    int insert(LogisticsInformation record);

    int insertSelective(LogisticsInformation record);

    LogisticsInformation selectByPrimaryKey(Long logisticsInformationId);

    int updateByPrimaryKeySelective(LogisticsInformation record);

    int updateByPrimaryKey(LogisticsInformation record);

    /**
     * 通过订单ID查询物流的信息
     * @param orderId 订单id
     * @return
     * @author fqf
     */
    LogisticsInformation selectByOrderId(Long orderId);
}