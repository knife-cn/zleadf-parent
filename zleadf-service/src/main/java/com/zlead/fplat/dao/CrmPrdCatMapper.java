package com.zlead.fplat.dao;

import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdCatExample;
import com.zlead.fplat.entity.vo.CatSecondQueryRequest;
import com.zlead.fplat.entity.vo.SecondCatRequest;
import org.apache.ibatis.annotations.Param;
import org.jgroups.util.OneTimeAddressGenerator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrmPrdCatMapper {
    int countByExample(CrmPrdCatExample example);

    int deleteByExample(CrmPrdCatExample example);

    int deleteByPrimaryKey(Integer catId);

    int insert(CrmPrdCat record);

    int insertSelective(CrmPrdCat record);

    List<CrmPrdCat> selectByExample(CrmPrdCatExample example);

    CrmPrdCat selectByPrimaryKey(Integer catId);

    int updateByExampleSelective(@Param("record") CrmPrdCat record, @Param("example") CrmPrdCatExample example);

    int updateByExample(@Param("record") CrmPrdCat record, @Param("example") CrmPrdCatExample example);

    int updateByPrimaryKeySelective(CrmPrdCat record);

    int updateByPrimaryKey(CrmPrdCat record);

    List<CrmPrdCat> findOwnCat(@Param("ids")List ids);

    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByFactoryIdsAndKey(@Param("factoryIds") Set<Long> factoryIds, @Param("key") String key);

    //平台工厂下所有分类
    List<CrmPrdCat> selectCatAll();

    //关联工厂下的分类
    List<CrmPrdCat> selectCatByAgentId(Long id);

    //根据一级分类获取工厂下的二级分类名称
    List<CrmPrdCat> findSecondCat(SecondCatRequest request);

    //根据二级名称和工厂Id获取二级二级（名称有重复）
    List<CrmPrdCat> selectBycatNameAndFacIds(CatSecondQueryRequest request);

    //根据分类名字获取所有工厂的一级分类
    List<CrmPrdCat> selectByFirstCatName(@Param("catName") String catName);

    List<CrmPrdCat> selectByIds(@Param("lists") List<Integer> lists);
    /**查询所有的分类
     * @author
     * @date 需要分类id集合
     * @param 
     * @return 
     */
    List<CrmPrdCat> selectByCIds(@Param("lists")List<Integer> selectByIds);
    
    List<CrmPrdCat> selectByCId(@Param("catid") Integer  catId);

    List<CrmPrdCat> selectAllBycatIds(@Param("catIds") List catIds, @Param("facIds") List facIds);

    List<CrmPrdCat> selectFirstCatByIds(@Param("catIds") List catIds);

    List<CrmPrdCat> selectSecByAllcatIds(@Param("catlist") List catlist,@Param("shopIds") List<Integer> shopIds,@Param("agentId") Long agentId);

    List<CrmPrdCat> selectSecByAllcatId(@Param("catId") Integer catId,@Param("shopIds")List<Integer> shopIds);

    List<CrmPrdCat> selectFir(@Param("catName") String catName, @Param("allCatIds") List<Integer> allCatIds,@Param("shopIds")List<Integer> shopIds);

    List<CrmPrdCat> selectSecByCatId(@Param("catId") Integer catId);

    Set<Long> findAllCatidsByfactoryIdsSet(@Param("factoryIds") Set<Long> factoryIds);

    /**
     * 根据传入的分类ids查找下级id
     */
    Set<Long> findChildCatids(@Param("catids") Set<Long> catids);

    Set<Long> findChildCatidsAPP(@Param("catids") Set<Long> catids);

    /**
     * 根据系列id 和关键字 查找分类
     */
    List<Map<String, Object>> findNameListByListIdsAndKey(@Param("listIds") Set<Long> listIds, @Param("key") String key);

    /**
     * 获取工厂分类
     * @param agentId
     * @param factId
     * @return
     */
    List<Map<String, Object>> newFindNameListByListIdsAndKey(@Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 优化查询
     * @param catIds
     * @return
     */
    List<Map<String, Object>> fastFindNameListByListIdsAndKey(@Param("catIds") List<Integer> catIds);



    List<Map<String, Object>> findNameListByListIdsAndKeyAPP(@Param("listIds") Set<Long> listIds, @Param("key") String key);

    List<CrmPrdCat> findListByIds(@Param("ids") Set<Long> ids);

    CrmPrdCat findOneById(@Param("id") Integer id);

    /**
     *
     * @param agentId
     * @return
     */
    List<Integer> queryCatIdsList(@Param("agentId") Long agentId);

    /**
     * 查询当前代理商的分类，需要当前代理商代理了品牌的情况下，并且有上架的商品，商品不允许为活动商品
     * @param agentId
     * @return
     */
    List<CrmPrdCat> selectFirstCatByAgentId(@Param("agentId") Long agentId,@Param("factId")Long factoryId);

    /**
     * 工厂端查询分类商品
     * @param catIds
     * @return
     */
    List<CrmPrdCat> selectFirstFacCatByAgentId(@Param("catIds") List<Integer> catIds);


    List<Map<String, Object>> findCatsByShopId(Long ownShopid);

    /**
     * 通过上级分类查询下级分类
     * @param agentId
     * @param catIds
     * @return
     */
    List<CrmPrdCat> upCatIdQueryConTent(@Param("agentId")Long agentId,@Param("catIds")List<Long> catIds,@Param("factId")Long factoryId);
    // List<CrmPrdCat> upCatIdQueryConTent(@Param("agentId")Long agentId,@Param("catIds")List<Long> catIds);



    /**
     * 根据品牌名称查询分类
     */
    CrmPrdCat  brandQueryCat(@Param("agentId")Long agentId,@Param("bkey")String bkey);



    /**
     * 查询当前分类ID下的子分类
     * @param catId
     * @return
     */
    List<CrmPrdCat> findChildByCatId(@Param("catId") Integer catId);

    /***
     * 根据代理人ID或分工厂ID或分类描述查询一级分类
     * @param factoryId
     * @param agentId
     * @param catName
     * @return
     */
    List<CrmPrdCat> findLevelOneByAgentOrName(@Param("factoryId") Long factoryId,@Param("agentId") Long agentId,@Param("catName") String catName);


    CrmPrdCat findOneByCondition(@Param("factoryId") Long factoryId,@Param("agentId") Long agentId,@Param("catId") Long catId);

    /**
     * 根据代理人ID和分类ID查询分类名称并且排序（根据关联工厂时间和创建分类时间排序）
     * @param agentId
     * @param ids
     * @return
     */
    List<Map<String,Object>> findNameByIds(@Param("agentId") Long agentId,@Param("ids") Set<Long> ids);

    /**
     * 优化 单独拿出所有catIds
     * @param agentId
     * @param factId
     * @return
     */
    Set<Long> getAllCatIds(@Param("agentId") Long agentId,@Param("factId") Long factId);

    /**
     * 优化 单独拿出所有 PcatIds
     * @param agentId
     * @param factId
     * @return
     */
    Set<Long> getAllPcatIds(@Param("agentId") Long agentId,@Param("factId") Long factId);

    /**
     * 查询根据等级代理工厂分类
     * @param agentId
     * @param factId
     * @param pcatId
     * @return
     */
    List<Map<String,Integer>> getAllCatIdsWithGoods(@Param("agentId") Long agentId,@Param("factId")Long factId);

}

