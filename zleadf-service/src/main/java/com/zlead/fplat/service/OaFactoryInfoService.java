package com.zlead.fplat.service;

import com.zlead.fplat.entity.OaFactoryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface OaFactoryInfoService {
    /**
     * @param agentId 代理商id
     * @return 未和该代理商绑定的工厂列表
     */
    List<Map<String, Object>> unRelationFactoryListByAgentId(Long agentId, String key);

    /**
     * @param agentId 代理商id
     * @return 与该代理商已绑定的工厂列表
     */
    List<Map<String, Object>> relationFactoryListByAgentId(Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的商铺信息
     */
    Map<String, Object> findShopByFactoryId(Long factoryId);

    /**
     * @param shopId 商铺id
     * @return 商铺轮播图
     */
    List<Map<String, Object>> findShopAdsImgByFactoryId(Integer shopId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findAllBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌（跟着首页查询数据走）
     */
    List<Map<String, Object>> newFindAllBrandsByFactoryIdAndAgentId(@Param("factoryId") Long factoryId, @Param("agentId") Long agentId);

    /**
     * @param factId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findAllBrandsByFactoryId(Long factId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的系列
     */
    List<Map<String, Object>> findAllListsByFactoryIdAndBids(Long factoryId, Set<Long> bids);

    /**
     * @param factoryId 工厂id
     * @return 工厂的系列
     */
    List<Map<String, Object>> findListsByFactoryId(Long factoryId, Set<Long> ids,Long agentId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的分类
     */
    List<Map<String, Object>> findCatsByFactoryId(Long factoryId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的所有1级分类
     */
    List<Map<String, Object>> findAllCatsByFactoryId(Long factoryId);

    /**
     * @param factoryId 工厂id
     * @param pcatId    1级分类的id
     * @return 工厂的2级分类 6个
     */
    List<Map<String, Object>> findChildCatsByPactId(Long factoryId, Long pcatId);

    /**
     * @param factoryId 工厂id
     * @param pcatId    1级分类的id
     * @return 工厂的2级分类 所有
     */
    List<Map<String, Object>> findAllChildCatsByPactId(Long factoryId, Long pcatId, Set<Long> listIds);

    /**
     * @param factoryId 工厂id
     * @param catIds    分类id集合
     * @return 该分类的6个商品
     */
    List<Map<String, Object>> findShopGoodsByCatIds(Long factoryId, Long agentId, Set<Long> brandIds, Set<Long> listIds, Set<Long> catIds);

    /**
     * @param vcode     关联码
     * @param factoryId 工厂id
     * @return 条数
     */
    int checkVcode(String vcode, Long factoryId);


    OaFactoryInfo findFacByShopId(Integer shopId);

    /**
     * 根据工厂ID 查询工厂
     * @param factId
     * @return
     */
    OaFactoryInfo findFacByFactId(Integer factId);

    /**
     * 关联工厂验证
     * @param vcode
     * @param factoryId
     * @return
     */
    Integer facRelevancy(@Param("vcode") String vcode,@Param("factoryName")String factoryName );
}
