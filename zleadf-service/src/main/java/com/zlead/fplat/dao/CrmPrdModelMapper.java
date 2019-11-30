package com.zlead.fplat.dao;

import com.zlead.fplat.entity.CrmPrdModel;
import com.zlead.fplat.entity.CrmPrdModelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrmPrdModelMapper {
    /**
     * This method:deleteByPrimaryKey
     * crm_prd_model
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer modelId);

    /**
     * This method:insert
     * crm_prd_model
     *
     * @ET
     */
    int insert(CrmPrdModel record);

    /**
     * This method:insertSelective
     * crm_prd_model
     *
     * @ET
     */
    int insertSelective(CrmPrdModel record);

    /**
     * This method:selectByExample
     * crm_prd_model
     *
     * @ET
     */
    List<CrmPrdModel> selectByExample(CrmPrdModelExample example);

    /**
     * This method:selectByPrimaryKey
     * crm_prd_model
     *
     * @ET
     */
    CrmPrdModel selectByPrimaryKey(Integer modelId);

    /**
     * This method:updateByPrimaryKeySelective
     * crm_prd_model
     *
     * @ET
     */
    int updateByPrimaryKeySelective(CrmPrdModel record);

    /**
     * This method:updateByPrimaryKey
     * crm_prd_model
     *
     * @ET
     */
    int updateByPrimaryKey(CrmPrdModel record);

    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByListIdsAndKey(@Param("listIds") Set<Long> listIds, @Param("key") String key);

    List<Map<String, Object>> findNameListByListIdsAndKeyAPP(@Param("listIds") Set<Long> listIds, @Param("key") String key);

    List<Map<String, Object>> findByIds(@Param("ids") Set<Long> ids);

    List<Map<String, Object>> findById(Long ownShopid);

    List<CrmPrdModel> findExistsGoodsByAgentAndListId(@Param("list") List ids,@Param("facIds")List<Integer> facIds,@Param("agentId")Long agentId);

    List<Map<String,Object>> findByName(@Param("agentId") Long agentId,@Param("factoryId") Long factoryId,@Param("modelName") String modelName);

    List<Map<String,Object>> findNameByIds(@Param("agentId") Long agentId,@Param("ids") Set<Long> ids);
}