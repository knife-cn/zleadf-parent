package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaMacketCont;
import com.zlead.fplat.entity.OaMacketContExample;
import java.util.List;

public interface OaMacketContMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_macket_cont
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer contId);

    /**
     * This method:insert
     *   oa_macket_cont
     *
     * @ET
     */
    int insert(OaMacketCont record);

    /**
     * This method:insertSelective
     *   oa_macket_cont
     *
     * @ET
     */
    int insertSelective(OaMacketCont record);

    /**
     * This method:selectByExampleWithBLOBs
     *   oa_macket_cont
     *
     * @ET
     */
    List<OaMacketCont> selectByExampleWithBLOBs(OaMacketContExample example);

    /**
     * This method:selectByExample
     *   oa_macket_cont
     *
     * @ET
     */
    List<OaMacketCont> selectByExample(OaMacketContExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_macket_cont
     *
     * @ET
     */
    OaMacketCont selectByPrimaryKey(Integer contId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_macket_cont
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaMacketCont record);

    /**
     * This method:updateByPrimaryKeyWithBLOBs
     *   oa_macket_cont
     *
     * @ET
     */
    int updateByPrimaryKeyWithBLOBs(OaMacketCont record);

    /**
     * This method:updateByPrimaryKey
     *   oa_macket_cont
     *
     * @ET
     */
    int updateByPrimaryKey(OaMacketCont record);
}