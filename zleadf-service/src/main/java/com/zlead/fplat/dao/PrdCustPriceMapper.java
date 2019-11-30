package com.zlead.fplat.dao;

import com.zlead.fplat.entity.PrdCustPrice;
import java.util.List;

public interface PrdCustPriceMapper {
    /**
     * This method:deleteByPrimaryKey
     *   prd_cust_price
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer itemId);

    /**
     * This method:insert
     *   prd_cust_price
     *
     * @ET
     */
    int insert(PrdCustPrice record);

    /**
     * This method:insertSelective
     *   prd_cust_price
     *
     * @ET
     */
    int insertSelective(PrdCustPrice record);

    /**
     * This method:selectByPrimaryKey
     *   prd_cust_price
     *
     * @ET
     */
    PrdCustPrice selectByPrimaryKey(Integer itemId);

    /**
     * This method:updateByPrimaryKeySelective
     *   prd_cust_price
     *
     * @ET
     */
    int updateByPrimaryKeySelective(PrdCustPrice record);

    /**
     * This method:updateByPrimaryKey
     *   prd_cust_price
     *
     * @ET
     */
    int updateByPrimaryKey(PrdCustPrice record);
}