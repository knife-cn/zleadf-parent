package com.zlead.service;

import com.zlead.entity.CollectEntity;
import com.zlead.util.JsonResult;

import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
public interface CollectService {

    /**
     * 添加收藏商品
     * @param collectEntity
     */
    JsonResult saveCollect(CollectEntity collectEntity);

    /**
     * 查询商品收藏数量
     * @param agentId
     * @param goodsId
     * @return
     */
    int queryByGidAndAid(int agentId,int goodsId);

    /**
     * 删除收藏商品
     * @param agentId
     * @param goodsId
     * @return
     */
    JsonResult deleteCollect(int agentId,int goodsId);

    JsonResult batchDeleteCollect(int agentId,List<Long> goodsIds);
}
