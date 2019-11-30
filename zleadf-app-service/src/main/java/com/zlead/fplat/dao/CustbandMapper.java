package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Custband;
import com.zlead.fplat.entity.CustbandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustbandMapper {
    /**
     * This method:deleteByPrimaryKey
     * crm_cust_band
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer bandId);

    /**
     * This method:insert
     * crm_cust_band
     *
     * @ET
     */
    int insert(Custband record);

    /**
     * This method:insertSelective
     * crm_cust_band
     *
     * @ET
     */
    int insertSelective(Custband record);

    /**
     * This method:selectByExample
     * crm_cust_band
     *
     * @ET
     */
    List<Custband> selectByExample(CustbandExample example);

    /**
     * This method:selectByPrimaryKey
     * crm_cust_band
     *
     * @ET
     */
    Custband selectByPrimaryKey(Integer bandId);

    /**
     * This method:updateByPrimaryKeySelective
     * crm_cust_band
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Custband record);

    /**
     * This method:updateByPrimaryKey
     * crm_cust_band
     *
     * @ET
     */
    int updateByPrimaryKey(Custband record);

    List<Map<String, Object>> findAllNameList();


    List<Custband> findAllBand(List ids);

    List<Map<String, Object>> findByIds(@Param("ids") Set<Long> ids);

    List<Map<String, Object>> findByShopId(Long ownShopid);

    List<Map<String,Object>> findByName(@Param("agentId") Long agentId,@Param("factoryId") Long factoryId,@Param("brandName") String brandName);

    List<Map<String,Object>> findNameByIds(@Param("agentId") Long agentId,@Param("ids") Set<Long> ids);
}