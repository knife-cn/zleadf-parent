package com.zlead.fplat.dao;

import com.zlead.fplat.entity.BaseShipType;
import com.zlead.fplat.entity.BaseShipTypeExample;
import java.util.List;

public interface BaseShipTypeMapper {
    /**
     * This method:deleteByPrimaryKey
     *   base_ship_type
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer typeId);

    /**
     * This method:insert
     *   base_ship_type
     *
     * @ET
     */
    int insert(BaseShipType record);

    /**
     * This method:insertSelective
     *   base_ship_type
     *
     * @ET
     */
    int insertSelective(BaseShipType record);

    /**
     * This method:selectByExample
     *   base_ship_type
     *
     * @ET
     */
    List<BaseShipType> selectByExample(BaseShipTypeExample example);

    /**
     * This method:selectByPrimaryKey
     *   base_ship_type
     *
     * @ET
     */
    BaseShipType selectByPrimaryKey(Integer typeId);

    /**
     * This method:updateByPrimaryKeySelective
     *   base_ship_type
     *
     * @ET
     */
    int updateByPrimaryKeySelective(BaseShipType record);

    /**
     * This method:updateByPrimaryKey
     *   base_ship_type
     *
     * @ET
     */
    int updateByPrimaryKey(BaseShipType record);
}