package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaFactoryPlaces;
import com.zlead.fplat.entity.OaFactoryPlacesExample;
import java.util.List;

public interface OaFactoryPlacesMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_factory_places
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer placeId);

    /**
     * This method:insert
     *   oa_factory_places
     *
     * @ET
     */
    int insert(OaFactoryPlaces record);

    /**
     * This method:insertSelective
     *   oa_factory_places
     *
     * @ET
     */
    int insertSelective(OaFactoryPlaces record);

    /**
     * This method:selectByExample
     *   oa_factory_places
     *
     * @ET
     */
    List<OaFactoryPlaces> selectByExample(OaFactoryPlacesExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_factory_places
     *
     * @ET
     */
    OaFactoryPlaces selectByPrimaryKey(Integer placeId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_factory_places
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaFactoryPlaces record);

    /**
     * This method:updateByPrimaryKey
     *   oa_factory_places
     *
     * @ET
     */
    int updateByPrimaryKey(OaFactoryPlaces record);
}