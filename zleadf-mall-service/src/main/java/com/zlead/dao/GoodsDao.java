package com.zlead.dao;

import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.dto.GoodsDetailDto;
import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.dto.GoodsStockSummaryDTO;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsDetailVo;
import com.zlead.entity.vo.GoodsListVo;
import com.zlead.entity.vo.GoodsStockSummaryListVO;
import com.zlead.fplat.entity.vo.GoodsQueryRequest;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.apache.ibatis.annotations.Param;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品
 *
 * @author fqf
 * @date 2018-07-25 11:41:37
 */
public interface GoodsDao {

    void insert(GoodsEntity entity);

    int update(GoodsEntity entity);


    void delete(Long id);

    PageList<GoodsEntity> findPage(Map params, PageBounds rowBounds);

    /**
     * 收藏列表
     *
     * @param params
     * @param rowBounds
     * @return
     */
    PageList<GoodsPageDto> findByPageByCollect(Map params, PageBounds rowBounds);

    /**
     * 查询商品
     *
     * @param list
     * @return
     */
    List<Map> getByGoodsId(@Param("list") List<GoodsAttrValueReponse> list, @Param("prdId") int prdId);

    /**
     * 根据商品类型查询商品
     *
     * @param prodType
     * @return
     */
    PageList<GoodsPageDto> findByProdTypePage(int prodType);

    GoodsEntity findById(Long id);

    GoodsDetailDto queryDetailById(Integer id);

    GoodsEntity findByPordId(Long prodId);

    //首页查询方法（取最新的6个商品）
    List<GoodsEntity> findHomeGoods(Map params);

    //修改商品库存
    int freezeStock(@Param(value = "id") Long id, @Param(value = "buyNum") Integer buyNum, @Param(value = "version") Integer version);

    //修改商品销量
    int addSaleNum(@Param(value = "id") Long id, @Param(value = "buyNum") Integer buyNum, @Param(value = "version") Integer version);

    //修改商品库存
    int minusStock(@Param(value = "buyNum") Integer buyNum, @Param(value = "id") Long id);

    Integer queryStock(Long id);

    int queryCountByTerm(@Param("bids") Set<Long> bids,
                         @Param("lids") Set<Long> lids,
                         @Param("mids") Set<Long> mids,
                         @Param("cids") Set<Long> cids,
                         @Param("key") String key);

    List<Map<String, Object>> queryAllListByTerm(@Param("factoryId") Long factoryId,
                                                 @Param("agentId") Long agentId,
                                                 @Param("bids") Set<Long> bids,
                                                 @Param("lids") Set<Long> lids,
                                                 @Param("mids") Set<Long> mids,
                                                 @Param("cids") Set<Long> cids,
                                                 @Param("key") String key);


    List<Map<String, Object>> queryAllListByTermAPP(@Param("factoryId") Long factoryId,
                                                    @Param("bids") Set<Long> bids,
                                                    @Param("lids") Set<Long> lids,
                                                    @Param("mids") Set<Long> mids,
                                                    @Param("cids") Set<Long> cids,
                                                    @Param("key") String key);

    List<Map<String, Object>> queryListByTerm(@Param("factoryId") Long factoryId,
                                              @Param("agentId") Long agentId,
                                              @Param("bids") Set<Long> bids,
                                              @Param("lids") Set<Long> lids,
                                              @Param("mids") Set<Long> mids,
                                              @Param("cids") Set<Long> cids,
                                              @Param("key") String key,
                                              @Param("start") Integer start,
                                              @Param("end") Integer end);


    List<Map<String, Object>> queryListByTermAPP(@Param("factoryId") Long factoryId,
                                                 @Param("bids") Set<Long> bids,
                                                 @Param("lids") Set<Long> lids,
                                                 @Param("mids") Set<Long> mids,
                                                 @Param("cids") Set<Long> cids,
                                                 @Param("key") String key,
                                                 @Param("start") Integer start,
                                                 @Param("end") Integer end);


    //查询二级分类下  展示给商城的上架商品
    List<GoodsEntity> getGoods(GoodsQueryRequest request);

    //查询二级分类下  展示给商城的上架商品
    List<GoodsEntity> getGoodsByCatId(@Param("catId") Integer catId);

    //getGoodsByCatIdWithAgent
    List<GoodsEntity> getGoodsByCatIdWithAgent(@Param("catId") Integer catId, @Param("agentId") Integer agentId);

    /**
     * @param catIds
     * @param agentId
     * @return
     */
    List<GoodsEntity> newGetGoodsByCatIdWithAgent(@Param("catIds") List<Long> catIds, @Param("agentId") Integer agentId, @Param("factoryId") Long factoryId);

    /**
     * 工厂端分类商品查询
     *
     * @param catIds
     * @param agentId
     * @param factoryId
     * @return
     */
    List<GoodsEntity> newFacGetGoodsByCatIdWithAgent(@Param("catIds") List<Long> catIds, @Param("agentId") Integer agentId, @Param("factoryId") Long factoryId, PageBounds rowBounds);

    List<GoodsEntity> newFacGetGoodsByCatIdWithAgent2(@Param("catIds") Set<Long> catIds, @Param("agentId") Integer agentId, @Param("factoryId") Long factoryId, PageBounds rowBounds);


    //查询二级分类下  展示给商城的上架商品
    List<GoodsEntity> getGoodsByCatIds(@Param("catIds") List<Integer> catIds);


    //H5商品展示
    List<GoodsEntity> Hgoods();

    /**
     * 查询商铺库存
     *
     * @param shopId
     * @return
     */
    Integer findShopStock(Long shopId);

    /**
     * 查询商铺库存对应市场价
     *
     * @param shopId
     * @return
     */
    BigDecimal findShopGoodsTotalPrice(Long shopId);

    PageList<GoodsEntity> findShopGoodsInfo(Long shopId, PageBounds rowBounds);

    GoodsEntity findGoodsDetail(@Param("shopId") Long shopId, @Param("goodsId") Long goodsId);


    List<GoodsAttrValEntity> goodsCanvs(@Param("List") List<Integer> goodsId);

    List<GoodsEntity> queryGoodsListByagentId(@Param("agentId") Long agentId, @Param("catId") Integer catId);

    List<GoodsEntity> querySecGoodsListByagentId(@Param("agentId") Long agentId, @Param("catIds") Set<Long> catIds, @Param("shopIds") List<Integer> shopIds);

    int setMarket(@Param("isMarket") Integer isMarket, @Param("goodsId") Long goodsId, @Param("stocknum") Integer stocknum);

    List<GoodsListVo> findGoodsList(@Param("memberId") Long memberId, @Param("fullName") String fullName,
                                    @Param("brandId") Integer brandId,
                                    @Param("listId") Integer listId,
                                    @Param("modelId") Integer modelId,
                                    @Param("catIds") Collection<Integer> catIds,
                                    PageBounds rowBounds);

    GoodsDetailVo findShopGoodsDetail(Integer goodsId);

    Integer findProdCntWithBrand(Integer goodsId);

    List<GoodsEntity> selectGoodsListByIds(Set<Long> goodsIds);

    /**
     * 库存商品种类总数统计
     *
     * @param goodsStockSummaryDTO
     * @return
     */
    Integer countTotalNumber(GoodsStockSummaryDTO goodsStockSummaryDTO);

    /**
     * 库存商品总成本统计
     *
     * @param goodsStockSummaryDTO
     * @return
     */
    BigDecimal sumGoodsSupplyPrice(GoodsStockSummaryDTO goodsStockSummaryDTO);

    /**
     * 库存商品列表信息
     *
     * @param goodsStockSummaryDTO
     * @return
     */
    List<GoodsStockSummaryListVO> getGoodsStockSummaryList(GoodsStockSummaryDTO goodsStockSummaryDTO);

    Integer findProdId(Integer goodsId);

    void setMarketByStock(@Param("goodsId") Long goodsId, @Param("isMarket") int isMarket);

    void deleteItemState(Long goodsId);


    List<Map<String, Object>> findAllBLMC(@Param("agentId") Long agentId,
                                          @Param("bids") Set<Long> bids,
                                          @Param("lids") Set<Long> lids,
                                          @Param("mids") Set<Long> mids,
                                          @Param("cids") Set<Long> cids,
                                          @Param("key") String key,
                                          @Param("catlf") boolean catlf);

    List<Map<String, Object>> findList(@Param("agentId") Long agentId,
                                       @Param("bids") Set<Long> bids,
                                       @Param("lids") Set<Long> lids,
                                       @Param("mids") Set<Long> mids,
                                       @Param("cids") Set<Long> cids,
                                       @Param("key") String key,
                                       @Param("catlf") boolean catlf,
                                       @Param("start") Integer start,
                                       @Param("end") Integer end);

    Map<String, Object> goodsFull(@Param("goodsId") Long goodsId, @Param("agentId") Long agentId);

    List<String> queryNameKey(@Param("agentId") Long agentId,
                              @Param("bids") Set<Long> bids,
                              @Param("lids") Set<Long> lids,
                              @Param("mids") Set<Long> mids,
                              @Param("cids") Set<Long> cids,
                              @Param("key") String key);

    GoodsDetailDto findActGoods(@Param("actId") Long actId, @Param("goodId") Long goodId);


    Map<String, Object> findFactoryId(@Param("shopId") Long shopId);


    //add by ykf 二级页面搜索需要搜索到活动商品
    List<Map<String, Object>> findAllAndActivityBLMC(@Param("agentId") Long agentId,
                                                     @Param("bids") Set<Long> bids,
                                                     @Param("lids") Set<Long> lids,
                                                     @Param("mids") Set<Long> mids,
                                                     @Param("cids") Set<Long> cids,
                                                     @Param("key") String key,
                                                     @Param("catlf") boolean catlf,
                                                     @Param("bActIds") Set<Long> bActIds,
                                                     @Param("lActIds") Set<Long> lActIds,
                                                     @Param("mActIds") Set<Long> mActIds,
                                                     @Param("cActIds") Set<Long> cActIds,
                                                     @Param("queryFlag") String queryFlag);

    //add by ykf 二级页面搜索需要搜索到活动商品
    List<Map<String, Object>> findAllList(@Param("agentId") Long agentId,
                                          @Param("bids") Set<Long> bids,
                                          @Param("lids") Set<Long> lids,
                                          @Param("mids") Set<Long> mids,
                                          @Param("cids") Set<Long> cids,
                                          @Param("key") String key,
                                          @Param("catlf") boolean catlf,
                                          @Param("bActIds") Set<Long> bActIds,
                                          @Param("lActIds") Set<Long> lActIds,
                                          @Param("mActIds") Set<Long> mActIds,
                                          @Param("cActIds") Set<Long> cActIds,
                                          @Param("queryFlag") String queryFlag,
                                          @Param("start") Integer start,
                                          @Param("end") Integer end);

    PageList<Map<String, Object>> findAllListByPage(@Param("agentId") Long agentId,
                                                    @Param("bids") Set<Long> bids,
                                                    @Param("lids") Set<Long> lids,
                                                    @Param("mids") Set<Long> mids,
                                                    @Param("cids") Set<Long> cids,
                                                    @Param("key") String key,
                                                    @Param("catlf") boolean catlf,
                                                    @Param("bActIds") Set<Long> bActIds,
                                                    @Param("lActIds") Set<Long> lActIds,
                                                    @Param("mActIds") Set<Long> mActIds,
                                                    @Param("cActIds") Set<Long> cActIds,
                                                    @Param("queryFlag") String queryFlag,
                                                    PageBounds rowBounds);

    int findAllListCount(@Param("agentId") Long agentId,
                         @Param("bids") Set<Long> bids,
                         @Param("lids") Set<Long> lids,
                         @Param("mids") Set<Long> mids,
                         @Param("cids") Set<Long> cids,
                         @Param("key") String key,
                         @Param("catlf") boolean catlf,
                         @Param("bActIds") Set<Long> bActIds,
                         @Param("lActIds") Set<Long> lActIds,
                         @Param("mActIds") Set<Long> mActIds,
                         @Param("cActIds") Set<Long> cActIds,
                         @Param("queryFlag") String queryFlag);

    /**
     * 二级页面查询，通过点击分类查询商品
     *
     * @return
     */
    List<Map<String, Object>> QueryGoodsByCats(@Param("catIds") List<Long> catIds, @Param("agentId") Long agentId, PageBounds rowBounds);

    Integer QueryCountGoodsByCats(@Param("catIds") List<Long> catIds, @Param("agentId") Long agentId);

    /**
     * 二级搜索通过listIds搜索商品
     *
     * @param listIds
     * @param agentId
     * @return
     */
    List<Map<String, Object>> QueryGoodsBylist(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId, PageBounds rowBounds);

    Integer QueryCountGoodsBylist(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId);

    /**
     * 二级搜索通过brandIds搜索商品
     *
     * @param brandIds
     * @param agentId
     * @return
     */
    List<Map<String, Object>> QueryGoodsBybrand(@Param("brandIds") List<Long> brandIds, @Param("agentId") Long agentId, PageBounds rowBounds);

    Integer QueryCountGoodsBybrand(@Param("brandIds") List<Long> brandIds, @Param("agentId") Long agentId);

    /**
     * 工厂端根据品牌查询
     */
    List<Map<String, Object>> facQueryGoodsBybrand(@Param("brandIds") List<Long> brandIds, @Param("agentId") Long agentId, @Param("factId") Long factId, PageBounds rowBounds);

    Integer facQueryCountGoodsBybrand(@Param("brandIds") List<Long> brandIds, @Param("agentId") Long agentId, @Param("factId") Long factId);

    /**
     * 工厂端根据系列查询
     */
    List<Map<String, Object>> facQueryGoodsBylist(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId, @Param("factId") Long factId, PageBounds rowBounds);

    Integer facQueryCountGoodsBylist(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId, @Param("factId") Long factId);


    /**
     * 工厂端 店铺首页 根据系列查询商品
     */
    List<Map<String, Object>> facQueryGoodsBylist2(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId, @Param("factId") Long factId, PageBounds rowBounds);

    Integer facQueryCountGoodsBylist2(@Param("listIds") List<Long> listIds, @Param("agentId") Long agentId, @Param("factId") Long factId);


    /**
     * 工厂端根据分类查询
     */
    List<Map<String, Object>> facQueryGoodsByCats(@Param("catIds") List<Long> catIds, @Param("agentId") Long agentId, @Param("factId") Long factId, PageBounds rowBounds);

    Integer facQueryCountGoodsByCats(@Param("catIds") List<Long> catIds, @Param("agentId") Long agentId, @Param("factId") Long factId);


    /**
     * 根据条件分页查询商品列表（包括活动商品）
     *
     * @param agentId
     * @param factoryId
     * @param brandIds
     * @param listIds
     * @param modelIds
     * @param catIds
     * @param key
     * @param rowBounds
     * @return
     */
    PageList<Map<String, Object>> findGoodsListByCondition(@Param("agentId") Long agentId,
                                                           @Param("factoryId") Long factoryId,
                                                           @Param("brandIds") Set<Long> brandIds,
                                                           @Param("listIds") Set<Long> listIds,
                                                           @Param("modelIds") Set<Long> modelIds,
                                                           @Param("catIds") Set<Long> catIds,
                                                           @Param("key") String key,
                                                           PageBounds rowBounds);

    /**
     * 根据条件查询商品总数
     *
     * @param agentId
     * @param factoryId
     * @param brandIds
     * @param listIds
     * @param modelIds
     * @param catIds
     * @param key
     * @return
     */
    int findGoodsTotalByCondition(@Param("agentId") Long agentId,
                                  @Param("factoryId") Long factoryId,
                                  @Param("brandIds") Set<Long> brandIds,
                                  @Param("listIds") Set<Long> listIds,
                                  @Param("modelIds") Set<Long> modelIds,
                                  @Param("catIds") Set<Long> catIds,
                                  @Param("key") String key);

    /**
     * 根据条件查询商品分类（品牌、系列、型号、分类）
     *
     * @param agentId
     * @param factoryId
     * @param brandIds
     * @param listIds
     * @param modelIds
     * @param catIds
     * @param key
     * @return
     */
    List<Map<String, Object>> findGoodsKindByCondition(@Param("agentId") Long agentId,
                                                       @Param("factoryId") Long factoryId,
                                                       @Param("brandIds") Set<Long> brandIds,
                                                       @Param("listIds") Set<Long> listIds,
                                                       @Param("modelIds") Set<Long> modelIds,
                                                       @Param("catIds") Set<Long> catIds,
                                                       @Param("key") String key);

    /**
     * 查询代理人有权限的商品（包括活动商品）
     *
     * @param agentId   代理人ID
     * @param factoryId 工厂ID
     * @return
     */
    List<Map<String, Object>> findGoodsBandListCatByAgent(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId);

    int updateIsMarketByActIds(@Param("actIds") List<Integer> actIds);


    /**
     * @param catIds
     * @param agentId
     * @return
     */
    List<GoodsEntity> newTowGetGoodsByCatIdWithAgent(@Param("catIds") List<Long> catIds, @Param("agentId") Integer agentId, @Param("factoryId") Long factoryId,@Param("limtCount") int limtCount);

    /**
     * 通过商品Id查询有效的商品
     * @param goodsId
     * @return
     */
    GoodsEntity queryGoods(Long goodsId);
    
    /**
     * --查询普通商品 的数据--
     * @param goodsId
     * @return
     */
    GoodsDetailDto queryGoodsWithAgent(@Param("goodsId")  Long goodsId, @Param("agentId") Integer agentId);
}
