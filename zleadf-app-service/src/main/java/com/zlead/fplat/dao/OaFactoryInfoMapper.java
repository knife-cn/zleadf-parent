package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaFactoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OaFactoryInfoMapper {
    /**
     * This method:deleteByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer factId);

    /**
     * This method:insert
     * oa_factory_info
     *
     * @ET
     */
    int insert(OaFactoryInfo record);

    /**
     * This method:insertSelective
     * oa_factory_info
     *
     * @ET
     */
    int insertSelective(OaFactoryInfo record);

    /**
     * This method:selectByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    OaFactoryInfo selectByPrimaryKey(Integer factId);

    /**
     * This method:updateByPrimaryKeySelective
     * oa_factory_info
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaFactoryInfo record);

    int updateShopid(OaFactoryInfo fac);

    /**
     * This method:updateByPrimaryKey
     * oa_factory_info
     *
     * @ET
     */
    int updateByPrimaryKey(OaFactoryInfo record);

    /**
     * 获取未与该代理商关联的工厂
     *
     * @param agentId 代理商id
     * @param key     关键词
     */
    List<Map<String, Object>> unRelationFactoryListByAgentId(@Param("agentId") Long agentId, @Param("key") String key);

    /**
     * @param agentId 代理商id
     * @return 与该代理商已绑定的工厂列表
     */
    List<Map<String, Object>> relationFactoryListByAgentId(Long agentId);

    /*
     * 获取平台现有正常状态下的工厂id*/
    List<Integer> findAllIds();

    //获取平台正常状态的工厂信息
    List<OaFactoryInfo> findAllInfo(List ids);

    /**
     * @param vcode     关联码
     * @param factoryId 工厂id
     * @return 条数
     */
    int checkVcode(@Param("vcode") String vcode, @Param("factoryId") Long factoryId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的商铺信息
     */
    Map<String, Object> findShopByFactoryId(@Param("factoryId") Long factoryId);

    /**
     * @param shopId 商铺id
     * @return 商铺轮播图
     */
    List<Map<String, Object>> findShopAdsImgByFactoryId(@Param("shopId") Integer shopId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findBrandsByFactoryIdAndAgentId(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findAllBrandsByFactoryIdAndAgentId(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌（跟着首页查询数据走）
     */
    List<Map<String, Object>> newFindAllBrandsByFactoryIdAndAgentId(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId);



    /**
     * @param factId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findAllBrandsByFactoryId(@Param("factId") Long factId);

    /**
     * @param factoryId 工厂id
     * @param bids      品牌ids
     * @return 工厂下这些品牌的系列
     */
    List<Map<String, Object>> findAllListsByFactoryIdAndBids(@Param("factoryId") Long factoryId, @Param("bids") Set<Long> bids);

    /**
     * @param factoryId 工厂id
     * @param ids       系列的ids
     * @return 工厂的系列
     */
    List<Map<String, Object>> findListsByFactoryId(@Param("factoryId") Long factoryId, @Param("ids") Set<Long> ids,@Param("agentId")Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的分类
     */
    List<Map<String, Object>> findCatsByFactoryId(@Param("factoryId") Long factoryId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的所有1级分类
     */
    List<Map<String, Object>> findAllCatsByFactoryId(@Param("factoryId") Long factoryId);

    /**
     * @param factoryId 工厂id
     * @param pcatId    1级分类的id
     * @return 工厂的2级分类
     */
    List<Map<String, Object>> findChildCatsByPactId(@Param("factoryId") Long factoryId, @Param("pcatId") Long pcatId);

    /**
     * @param factoryId 工厂id
     * @param pcatId    1级分类的id
     * @return 工厂的2级分类
     */
    List<Map<String, Object>> findAllChildCatsByPactId(@Param("factoryId") Long factoryId, @Param("pcatId") Long pcatId, @Param("listIds") Set<Long> listIds);

    /**
     * @param factoryId 工厂id
     * @param catIds    分类id集合
     * @return 该分类的6个商品
     */
    List<Map<String, Object>> findShopGoodsByCatIds(@Param("factoryId") Long factoryId,
                                                    @Param("agentId") Long agentId,
                                                    @Param("brandIds") Set<Long> brandIds,
                                                    @Param("listIds") Set<Long> listIds,
                                                    @Param("catIds") Set<Long> catIds);


    List<Integer> findShopIdById(List<Integer> facs);

    /**
     * @param shopId
     * @return
     */
    int findByShopId(@Param("shopId")int shopId);


    /**
     * @param shopId
     * @return
     */
    OaFactoryInfo findFacByShopId(@Param("shopId")Integer shopId);


    /**
     * 根据工厂ID 查询工厂
     * @param factId
     * @return
     */
    OaFactoryInfo findFacByFactId(Integer factId);

    /**
     * 关联工厂验证
     * @param vcode
     * @param factoryName
     * @return
     */
    Integer facRelevancy(@Param("vcode") String vcode,@Param("factoryName")String factoryName );

    OaFactoryInfo getFactoryNameByMemberId(Long memberId);
}