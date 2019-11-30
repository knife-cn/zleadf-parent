package com.zlead.fplat.dao;

import com.zlead.fplat.entity.AcctSaleType;
import com.zlead.fplat.entity.AcctSaleTypeExample;
import java.util.List;

public interface AcctSaleTypeMapper {
    /**
     * This method:deleteByPrimaryKey
     *   acct_sale_type
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer typeId);

    /**
     * This method:insert
     *   acct_sale_type
     *
     * @ET
     */
    int insert(AcctSaleType record);

    /**
     * This method:insertSelective
     *   acct_sale_type
     *
     * @ET
     */
    int insertSelective(AcctSaleType record);

    /**
     * This method:selectByExample
     *   acct_sale_type
     *
     * @ET
     */
    List<AcctSaleType> selectByExample(AcctSaleTypeExample example);

    /**
     * This method:selectByPrimaryKey
     *   acct_sale_type
     *
     * @ET
     */
    AcctSaleType selectByPrimaryKey(Integer typeId);

    /**
     * This method:updateByPrimaryKeySelective
     *   acct_sale_type
     *
     * @ET
     */
    int updateByPrimaryKeySelective(AcctSaleType record);

    /**
     * This method:updateByPrimaryKey
     *   acct_sale_type
     *
     * @ET
     */
    int updateByPrimaryKey(AcctSaleType record);
}