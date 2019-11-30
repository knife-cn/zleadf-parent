package com.zlead.fplat.dao;

import com.zlead.fplat.entity.BaseWarehouse;
import com.zlead.fplat.entity.BaseWarehouseExample;
import java.util.List;

public interface BaseWarehouseMapper {
    /**
     * This method:deleteByPrimaryKey
     *   base_warehouse
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer whId);

    /**
     * This method:insert
     *   base_warehouse
     *
     * @ET
     */
    int insert(BaseWarehouse record);

    /**
     * This method:insertSelective
     *   base_warehouse
     *
     * @ET
     */
    int insertSelective(BaseWarehouse record);

    /**
     * This method:selectByExample
     *   base_warehouse
     *
     * @ET
     */
    List<BaseWarehouse> selectByExample(BaseWarehouseExample example);

    /**
     * This method:selectByPrimaryKey
     *   base_warehouse
     *
     * @ET
     */
    BaseWarehouse selectByPrimaryKey(Integer whId);

    /**
     * This method:updateByPrimaryKeySelective
     *   base_warehouse
     *
     * @ET
     */
    int updateByPrimaryKeySelective(BaseWarehouse record);

    /**
     * This method:updateByPrimaryKey
     *   base_warehouse
     *
     * @ET
     */
    int updateByPrimaryKey(BaseWarehouse record);
}