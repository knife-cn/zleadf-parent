package com.zlead.shopmgr.service;

import com.zlead.entity.GoodsEntity;
import com.zlead.entity.vo.GoodsDetailVo;
import com.zlead.entity.vo.GoodsListVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface FactoryGoodsService {

    /**
     * @param factoryId 工厂id
     * @return 工厂的所有1级分类
     */
    List<Map<String, Object>> findAllCatsByFactoryId(Long factoryId);

    /**
     * @param factoryId 工厂id
     * @param pcatId    1级分类的id
     * @return 工厂的2级分类
     */
    List<Map<String, Object>> findChildCatsByPactId(Long factoryId, Long pcatId);

    /**
     * @param factoryId 工厂id
     * @return 工厂的品牌
     */
    List<Map<String, Object>> findBrandsByFactoryIdAndAgentId(Long factoryId, Long agentId);

    /**
     * @param factoryId 工厂id
     * @param catIds    分类id集合
     * @return 该分类的6个商品
     */
    List<Map<String, Object>> findShopGoodsByCatIds(Long factoryId, Long agentId, Set<Long> brandIds, Set<Long> listIds, Set<Long> catIds);

    /**
     * 查询库存
     *
     * @param shopId
     * @return
     */
    Integer findShopStock(Long shopId);

    /**
     * 查询库存价格
     *
     * @param shopId
     * @return
     */
    BigDecimal findShopGoodsTotalPrice(Long shopId);

    /**
     * 查询工厂下的商品信息
     *
     * @param shopId
     * @return
     */
    PageList<GoodsEntity> findShopGoodsInfo(Long shopId, PageBounds rowBounds);

    /**
     * 查询商品详情
     *
     * @param shopId
     * @param goodsId
     * @return
     */
    GoodsEntity findGoodsDetail(Long shopId, Long goodsId);

    /**
     * 商品上下架
     *
     * @param stocknum
     * @param goodsId
     * @param isMarket
     * @return
     */
    int setMarket(Long goodsId, Integer isMarket, Integer stocknum);


    List<GoodsListVo> findGoodsList(Long memberId, String fullName,Integer brandId,Integer listId,Integer modelId,Integer catId, PageBounds rowBounds);


    GoodsDetailVo findShopGoodsDetail(Integer goodsId);

    /**
     * 商品库存统计筛选
     * @param memberId
     * @param warehouseId
     * @param brandId
     * @param modelId
     * @param listId
     * @param catagoryId
     * @param keyword
     * @return
     */
    Map<String, Object> goodsStockSummary(Long memberId, Integer warehouseId, Integer brandId, Integer modelId, Integer listId, Integer catagoryId, String keyword);

}


