package com.zlead.service;

import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.entity.SysUserEntity;
import com.zlead.fplat.entity.PrdCustPrice;
import com.zlead.util.JsonResult;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品
 *
 * @author fqf
 * @date 2018-07-25 11:41:37
 */
public interface GoodsBgService {

    PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds);

    void save(GoodsEntity entity);

    void update(GoodsEntity entity);


    void delete(Long id);

    GoodsEntity findById(Long id);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    boolean goodsDelete(Long id);

    /**
     * 商品修改
     *
     * @param goodsEntity
     * @param
     * @return
     */
    boolean goodsUpdate(GoodsEntity goodsEntity);


    /**
     * 商品修改
     *
     * @param goodsEntity
     * @param
     * @return
     */
    boolean goodUpdate(GoodsEntity goodsEntity);

    /**
     * 商品添加(后台)
     *
     * @param goodsEntity
     * @param
     * @return
     */
    boolean sysGoodsSave(GoodsEntity goodsEntity, SysUserEntity userEntity);

    /**
     * 商品添加
     *
     * @param goodsEntity
     * @param userId
     * @param shopEntity
     * @return
     */
    boolean goodsSave(GoodsEntity goodsEntity, Long userId, ShopEntity shopEntity);

    /**
     * 商品添加
     *
     * @param goodsEntity
     * @param userId
     * @param shopEntity
     * @return
     */
    boolean goodSave(GoodsEntity goodsEntity, Long userId, ShopEntity shopEntity);

    /**
     * @param bids 品牌
     * @param lids 系列
     * @param mids 型号
     * @param cids 分类
     * @param key  关键词
     * @return 检索出来的总条数
     */
    int queryCountByTerm(Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key);

    /**
     * @param bids  品牌
     * @param lids  系列
     * @param mids  型号
     * @param cids  分类
     * @param key   关键词
     * @param start 开始
     * @param end   结束
     * @return 检索出来的商品
     */
    List<Map<String, Object>> queryListByTerm(Long factoryId, Long agentId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key, Integer start, Integer end);

    List<Map<String, Object>> queryListByTermAPP(Long factoryId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key, Integer start, Integer end);

    /**
     * @param bids 品牌
     * @param lids 系列
     * @param mids 型号
     * @param cids 分类
     * @param key  关键词
     * @return 检索出来的商品关联的品牌、系列、型号、分类
     */
    List<Map<String, Object>> queryAllListByTerm(Long factoryId, Long agentId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key);

    List<Map<String, Object>> queryAllListByTermAPP(Long factoryId, Set<Long> bids, Set<Long> lids, Set<Long> mids, Set<Long> cids, String key);

    void insert(GoodsEntity goodsEntity);


    //H5商品展示
    List<GoodsEntity> Hgoods();

    List<GoodsAttrValEntity> goodsCanvs(@Param("List") List<Integer> goodsId);

    /**
     * @param factoryId 工厂id 可以为null
     * @param agentId   代理商id 不能为null
     * @param bKey      品牌关键字
     * @param lKey      系列关键字
     * @param mKey      型号关键字
     * @param cKey      分类关键字
     * @param key       商品名称关键字
     * @param page      当前页
     * @param paseSize  每页多少条
     * @return
     */
    JsonResult query(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize);


    JsonResult queryGoods(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize);

    /**
     * @param factoryId 工厂id 可以为null
     * @param agentId   代理商id 不能为null
     * @param bKey      品牌关键字
     * @param lKey      系列关键字
     * @param mKey      型号关键字
     * @param cKey      分类关键字
     * @param key       商品名称关键字
     * @param page      当前页
     * @param paseSize  每页多少条
     * @return
     */
    JsonResult queryGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize);

    //JsonResult queryGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize, HttpServletRequest request);
    //JsonResult queryGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize, HttpServletRequest request,String brandids,String listids,String catids);


    JsonResult queryGoodsByPageNew(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize);

    JsonResult queryAgentGoodsByPage(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer pageNum, Integer pageSize);
    
    List<Map<String, Object>> showGoodsByBand(Long factoryId, Long agentId);

    List<Map<String, Object>> showGoodsByList(Long factoryId, Long agentId);

   // List<Map<String, Object>> showGoodsByCat(Long factoryId, Long agentId);
    List<Map<String, Object>> newShowGoodsByCat(Long factoryId, Long agentId);

    Map<String, Object> goodsFull(Long goodsId, @Param("agentId") Long agentId);

    JsonResult queryNameKey(Long agentId, Long factId, String key);

    Map<String, Object> findFactoryId(@Param("shopId") Long shopId);
    /**
     *
     * @param catIds
     * @param agentId
     * @return
     */
    List<GoodsEntity> newGetGoodsByCatIdWithAgent(@Param("catIds") List<Long> catIds,@Param("agentId")Integer agentId);

    JsonResult queryGoodsByPageByCondition(Long factoryId, Long agentId, String bKey, String lKey, String mKey, String cKey, String key, String x, Integer page, Integer paseSize,HttpServletRequest request);

    int updateIsMarketByActIds(@Param("actIds") List<Integer> actIds);
    
    Integer queryGoodsNewestStock(GoodsEntity goodsEntity);
    
    /**
     * 
     * @param id 商品数
     * @param stockNum 增减的库存数
     * @param plus 0 加 1 减
     * @return
     */
    GoodsEntity updateGoodsRealStock(Integer stockNum,Integer plus,GoodsEntity goodsEntity);

}

