package com.zlead.dao;

import com.zlead.entity.GoodsEntity;
import com.zlead.fplat.entity.vo.KindVo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FastSearchDao {

    /**
     * 根据代理人查询代理人关联的工厂并按关联工厂时间排序
     * @param agentId
     * @param factoryId
     * @return
     */
    List<Map<String,Object>> findFactoryInfoByAgent(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId);
    /**
     * 根据条件查询品牌、系列、分类并排序（根据关联工厂时间顺序，商品上架时间倒序排序）
     * @param agentId
     * @param factoryId
     * @return
     */
    List<KindVo> findSortKindByCondition(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId);

    /**
     * 查询上架商品品牌、系列、分类并排序（根据商品上架时间倒序排序）
     * @return
     */
    List<KindVo> findSortKind();


    /**
     * 根据分类id查询分类树形结构
     * @param catIds
     * @return
     */
    List<Map<String,Object>> findCatTreeByCatId(@Param("catIds") Collection<Integer> catIds);

    /**
     * 根据分类ID查询上每个店铺最新上架的商品
     * @param agentId
     * @param catIds
     * @return
     */
    List<Map<String,Object>> findGoodsByCatIds(@Param("agentId") Long agentId, @Param("factoryId") Long factoryId, @Param("catIds") Collection<Integer> catIds);

    /**
     * 根据商品ID查询商品信息
     * @param goodsIds
     * @return
     */
    List<GoodsEntity> findGoodsByIds(@Param("goodsIds") Collection<Integer> goodsIds);
}
