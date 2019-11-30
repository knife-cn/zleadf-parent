package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaSaleCust;
import com.zlead.fplat.entity.OaSaleCustExample;
import java.util.List;

public interface OaSaleCustMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_sale_cust
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer saleId);

    /**
     * This method:insert
     *   oa_sale_cust
     *
     * @ET
     */
    int insert(OaSaleCust record);

    /**
     * This method:insertSelective
     *   oa_sale_cust
     *
     * @ET
     */
    int insertSelective(OaSaleCust record);

    /**
     * This method:selectByExample
     *   oa_sale_cust
     *
     * @ET
     */
    List<OaSaleCust> selectByExample(OaSaleCustExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_sale_cust
     *
     * @ET
     */
    OaSaleCust selectByPrimaryKey(Integer saleId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_sale_cust
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaSaleCust record);

    /**
     * This method:updateByPrimaryKey
     *   oa_sale_cust
     *
     * @ET
     */
    int updateByPrimaryKey(OaSaleCust record);
}