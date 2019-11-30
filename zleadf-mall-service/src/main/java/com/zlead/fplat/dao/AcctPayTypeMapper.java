package com.zlead.fplat.dao;

import com.zlead.fplat.entity.AcctPayType;
import com.zlead.fplat.entity.AcctPayTypeExample;
import java.util.List;

public interface AcctPayTypeMapper {
    /**
     * This method:deleteByPrimaryKey
     *   acct_pay_type
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer typeId);

    /**
     * This method:insert
     *   acct_pay_type
     *
     * @ET
     */
    int insert(AcctPayType record);

    /**
     * This method:insertSelective
     *   acct_pay_type
     *
     * @ET
     */
    int insertSelective(AcctPayType record);

    /**
     * This method:selectByExample
     *   acct_pay_type
     *
     * @ET
     */
    List<AcctPayType> selectByExample(AcctPayTypeExample example);

    /**
     * This method:selectByPrimaryKey
     *   acct_pay_type
     *
     * @ET
     */
    AcctPayType selectByPrimaryKey(Integer typeId);

    /**
     * This method:updateByPrimaryKeySelective
     *   acct_pay_type
     *
     * @ET
     */
    int updateByPrimaryKeySelective(AcctPayType record);

    /**
     * This method:updateByPrimaryKey
     *   acct_pay_type
     *
     * @ET
     */
    int updateByPrimaryKey(AcctPayType record);
}