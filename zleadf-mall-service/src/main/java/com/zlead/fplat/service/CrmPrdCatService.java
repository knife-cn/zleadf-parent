package com.zlead.fplat.service;

import com.zlead.fplat.entity.CrmPrdCat;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrmPrdCatService {

    /**
     * 获取所有的分类
     */
    List<Map<String, Object>> findAllNameList();

    /**
     * 根据传入的工厂获取一级分类
     */
    List<Map<String, Object>> findNameListByFactoryIdsAndKey(Set<Long> factoryIds, String key);

    /**
     * 根据传入的工厂获取所有分类
     */
    Set<Long> findAllCatidsByfactoryIdsSet(Set<Long> factoryIds);

    /**
     * 根据传入的分类ids查找下级id
     */
    Set<Long> findChildCatids(Set<Long> catids);

    /**
     * 根据传入的分类ids查找下级id
     */
    Set<Long> findChildCatidsAPP(Set<Long> catids);
    
    /**
     * 查询当前分类的上级分类
     */
    CrmPrdCat getParentCat(Integer catId);

    /**
     * 根据系列id 和关键字 查找分类
     */
    List<Map<String, Object>> findNameListByListIdsAndKey(Set<Long> listIds, String key);

    List<Map<String, Object>> findNameListByListIdsAndKeyAPP(Set<Long> listIds, String key);


    /**
     * 查询所有的分类
     *
     * @param
     * @return
     * @author 廖巨会
     * @date 需要分类id集合
     */
    List<CrmPrdCat> selectByCIds(List<Integer> selectByIds);

    List<CrmPrdCat> findListByIds(@Param("ids") Set<Long> ids);

    CrmPrdCat findOneById(Integer id);

    List<Map<String, Object>> findCatsByShopId(Long ownShopid);

    /**
     * 通过上级分类查询下级分类
     * @param agentId
     * @param catIds
     * @return
     */
    List<CrmPrdCat> upCatIdQueryConTent(@Param("agentId")Long agentId,@Param("catIds")List<Long> catIds, @Param("factId") Long factId);

    /**
     * 经过后期迭代，查询三级分类
     * @param agentId
     * @param catIds
     * @param factoryId
     * @return
     */
    List<CrmPrdCat> upCatIdQueryConTent3(@Param("agentId")Long agentId,@Param("catIds")Set<Long> catIds,@Param("factId")Long factoryId);

    /**
     * 获取工厂分类
     * @param agentId
     * @param factId
     * @return
     */
    List<Map<String, Object>> newFindNameListByListIdsAndKey(@Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 递归查询子分类
     * @param catId
     * @param acceptResultList
     * @return
     */
    List<CrmPrdCat> findChildByCatId(Integer catId,List<CrmPrdCat> acceptResultList);

    /**
     * 根据代理人ID、工厂ID、分类名称查询自己和子分类
     * @param agentId
     * @param factoryId
     * @param catName
     * @return
     */
    List<CrmPrdCat> findChildAndOwnByCatName(Long agentId,Long factoryId,String catName);

    /**
     * 根据代理人ID、工厂ID、分类id查询自己和子分类
     * @param agentId
     * @param factoryId
     * @param catId
     * @return
     */
    List<CrmPrdCat> findChildAndOwnByCatId(Long agentId,Long factoryId,Long catId);

    /**
     * 根据代理人ID、工厂ID、分类id查询自己和子分类
     * @param agentId
     * @param factoryId
     * @param catId
     * @return
     */
    Set<Long> findChildAndOwnById(Long agentId,Long factoryId,Long catId);

    /**
     * 优化获取工厂分类 快速查
     * @param agentId
     * @param factId
     * @return
     */
    List<Map<String, Object>> fastFindNameListByListIdsAndKey(@Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 工厂分类商品展示(附带优化)
     * @return
     */
    List<CrmPrdCat> selectFirstFacCatByAgentId(@Param("agentId") Long agentId,@Param("factId")Long factoryId);


    /**
     * 根据代理商和工厂查询所有的一级二级三级四级......分类的id
     * @param agentId
     * @param factId
     * @return
     */
    List<Integer> getAllCatByAgentAndFacId(@Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 查询不到三级分类做出的方案
     * 迭代原先的方法：getAllCatByAgentAndFacId
     * 2019/5/15测试没问题可以删除上面的方法
     * @param agentId
     * @param factId
     * @return
     */
    List<Integer> findLevelOneCatByAgentAndFac(@Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 根据代理人ID和工厂ID查询一级和二级分类
     * @param agentId
     * @param factoryId
     * @return
     */
    Map<String,Object> findLevelCatByCondition(Long agentId, Long factoryId);

}
