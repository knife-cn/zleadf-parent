package com.zlead.service.impl;

import com.zlead.dao.CollectDao;
import com.zlead.entity.CollectEntity;
import com.zlead.service.CollectService;
import com.zlead.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
@Service
public class CollectServiceImpl implements CollectService{

    private static final Logger log = LoggerFactory.getLogger(CollectServiceImpl.class);

    @Autowired
    private CollectDao collectDao;

    @Override
    public JsonResult saveCollect(CollectEntity collectEntity) {
        log.info("收藏商品入参：{}",collectEntity);
        if(0 == collectEntity.getAgentId() || 0 == collectEntity.getGoodsId()
                || 0 == collectEntity.getMemberId()){
            return new JsonResult(2,"添加收藏失败","",false);
        }
        int count = collectDao.countByGidAndAid(collectEntity.getAgentId(),collectEntity.getGoodsId());
        if(1 <= count){
            return new JsonResult(2,"同一商品不能重复收藏","",false);
        }
        collectDao.saveCollect(collectEntity);
        return new JsonResult(1,"添加收藏成功","",true);
    }

    @Override
    public int queryByGidAndAid(int agentId, int goodsId) {
        log.info("查询收藏商品数量入参：agentId = {},goodsId = {}" ,agentId,goodsId);
        return collectDao.countByGidAndAid(agentId,goodsId);
    }

    @Override
    public JsonResult deleteCollect(int agentId, int goodsId) {
        log.info("删除收藏商品入参：agentId = {} , goodsId = {}",agentId,goodsId);
        if(0 == agentId || 0 == goodsId){
            return new JsonResult(2,"删除收藏商品失败","",false);
        }
        collectDao.deleteByGoodsId(goodsId,agentId);
        return new JsonResult(1,"删除收藏成功","",true);
    }

    @Override
    public JsonResult batchDeleteCollect(int agentId, List<Long> goodsIds) {
        log.info("批量删除收藏商品入参：agentId = {} , goodsIds = {}",agentId,goodsIds);
        if (null!=goodsIds&&goodsIds.size()>0){
            collectDao.batchDeleteCollectByGoodsId(goodsIds,agentId);
            return new JsonResult(1,"",true);
        }else {
            return new JsonResult(2,"删除收藏商品失败","",false);
        }
    }


}
