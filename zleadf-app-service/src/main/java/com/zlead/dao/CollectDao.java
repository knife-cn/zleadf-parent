package com.zlead.dao;

import com.zlead.entity.CollectEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
@Repository
public interface CollectDao {

    /**
     * 添加商品收藏
     * @param collectEntity
     */
    void saveCollect(CollectEntity collectEntity);

    /**
     * 查询收藏数量
     * @param agentId
     * @param goodsId
     */
     int countByGidAndAid(@Param("agentId") int agentId,@Param("goodsId") int goodsId);

    /**
     * 删除商品收藏
     * @param goodsId
     * @param agentId
     */
    void deleteByGoodsId(@Param("goodsId") int goodsId,@Param("agentId") int agentId);

    void batchDeleteCollectByGoodsId(@Param("goodsIds") List<Long> goodsIds, @Param("agentId") int agentId);

}
