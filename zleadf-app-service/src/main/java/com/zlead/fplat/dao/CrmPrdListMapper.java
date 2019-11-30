package com.zlead.fplat.dao;

import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.Prdlistcats;
import com.zlead.fplat.entity.CrmPrdListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrmPrdListMapper {
    /**
     * This method:deleteByPrimaryKey
     * crm_prd_list
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer listId);

    /**
     * This method:insert
     * crm_prd_list
     *
     * @ET
     */
    int insert(CrmPrdList record);

    /**
     * This method:insertSelective
     * crm_prd_list
     *
     * @ET
     */
    int insertSelective(CrmPrdList record);

    /**
     * This method:selectByExample
     * crm_prd_list
     *
     * @ET
     */
    List<CrmPrdList> selectByExample(CrmPrdListExample example);

    /**
     * This method:selectByPrimaryKey
     * crm_prd_list
     *
     * @ET
     */
    CrmPrdList selectByPrimaryKey(Integer listId);

    /**
     * This method:updateByPrimaryKeySelective
     * crm_prd_list
     *
     * @ET
     */
    int updateByPrimaryKeySelective(CrmPrdList record);

    /**
     * This method:updateByPrimaryKey
     * crm_prd_list
     *
     * @ET
     */
    int updateByPrimaryKey(CrmPrdList record);

    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByIdsAndKey(@Param("ids") Set<Long> ids, @Param("key") String key);


    List<Map<String, Object>> findNameListByIdsAndKeyAPP(@Param("ids") Set<Long> ids, @Param("key") String key);

    List<CrmPrdList> findListByshopIds(List ids);

    List<CrmPrdList> findListByBands(List ids);

    List<CrmPrdList> selectByListIds(@Param("list") List ids,@Param("facIds")List<Integer> facIds,@Param("agentId")Long agentId);

    List<Map<String, Object>> findByIds(@Param("ids") Set<Long> ids);

    List<Map<String, Object>> findById(Long ownShopid);

    List<Map<String,Object>> findByName(@Param("agentId") Long agentId,@Param("factoryId") Long factoryId,@Param("listName") String listName);

    List<Map<String,Object>> findNameByIds(@Param("agentId") Long agentId,@Param("ids") Set<Long> ids);

}