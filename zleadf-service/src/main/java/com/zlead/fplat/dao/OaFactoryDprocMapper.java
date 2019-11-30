package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaFactoryDproc;
import com.zlead.fplat.entity.OaFactoryDprocExample;
import java.util.List;

public interface OaFactoryDprocMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_factory_dproc
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer dprocId);

    /**
     * This method:insert
     *   oa_factory_dproc
     *
     * @ET
     */
    int insert(OaFactoryDproc record);

    /**
     * This method:insertSelective
     *   oa_factory_dproc
     *
     * @ET
     */
    int insertSelective(OaFactoryDproc record);

    /**
     * This method:selectByExample
     *   oa_factory_dproc
     *
     * @ET
     */
    List<OaFactoryDproc> selectByExample(OaFactoryDprocExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_factory_dproc
     *
     * @ET
     */
    OaFactoryDproc selectByPrimaryKey(Integer dprocId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_factory_dproc
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaFactoryDproc record);

    /**
     * This method:updateByPrimaryKey
     *   oa_factory_dproc
     *
     * @ET
     */
    int updateByPrimaryKey(OaFactoryDproc record);
}