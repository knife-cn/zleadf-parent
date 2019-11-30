package com.zlead.fplat.dao;

import com.zlead.fplat.entity.BaseWhLoc;
import com.zlead.fplat.entity.BaseWhLocExample;
import java.util.List;

public interface BaseWhLocMapper {
    /**
     * This method:deleteByPrimaryKey
     *   base_wh_loc
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer locId);

    /**
     * This method:insert
     *   base_wh_loc
     *
     * @ET
     */
    int insert(BaseWhLoc record);

    /**
     * This method:insertSelective
     *   base_wh_loc
     *
     * @ET
     */
    int insertSelective(BaseWhLoc record);

    /**
     * This method:selectByExample
     *   base_wh_loc
     *
     * @ET
     */
    List<BaseWhLoc> selectByExample(BaseWhLocExample example);

    /**
     * This method:selectByPrimaryKey
     *   base_wh_loc
     *
     * @ET
     */
    BaseWhLoc selectByPrimaryKey(Integer locId);

    /**
     * This method:updateByPrimaryKeySelective
     *   base_wh_loc
     *
     * @ET
     */
    int updateByPrimaryKeySelective(BaseWhLoc record);

    /**
     * This method:updateByPrimaryKey
     *   base_wh_loc
     *
     * @ET
     */
    int updateByPrimaryKey(BaseWhLoc record);
}