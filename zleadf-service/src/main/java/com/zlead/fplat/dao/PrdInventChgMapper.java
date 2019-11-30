package com.zlead.fplat.dao;

import com.zlead.fplat.entity.PrdInventChg;
import com.zlead.fplat.entity.PrdInventChgExample;
import java.util.List;

public interface PrdInventChgMapper {
    /**
     * This method:deleteByPrimaryKey
     *   prd_invent_chg
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer recId);

    /**
     * This method:insert
     *   prd_invent_chg
     *
     * @ET
     */
    int insert(PrdInventChg record);

    /**
     * This method:insertSelective
     *   prd_invent_chg
     *
     * @ET
     */
    int insertSelective(PrdInventChg record);

    /**
     * This method:selectByExample
     *   prd_invent_chg
     *
     * @ET
     */
    List<PrdInventChg> selectByExample(PrdInventChgExample example);

    /**
     * This method:selectByPrimaryKey
     *   prd_invent_chg
     *
     * @ET
     */
    PrdInventChg selectByPrimaryKey(Integer recId);

    /**
     * This method:updateByPrimaryKeySelective
     *   prd_invent_chg
     *
     * @ET
     */
    int updateByPrimaryKeySelective(PrdInventChg record);

    /**
     * This method:updateByPrimaryKey
     *   prd_invent_chg
     *
     * @ET
     */
    int updateByPrimaryKey(PrdInventChg record);
}