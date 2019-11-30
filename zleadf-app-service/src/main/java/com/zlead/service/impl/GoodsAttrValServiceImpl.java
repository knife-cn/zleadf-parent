package com.zlead.service.impl;

import com.zlead.dao.GoodsAttrValDao;
import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsAttrValueVo;
import com.zlead.service.GoodsAttrValService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsAttrValServiceImpl implements GoodsAttrValService {

    @Autowired
    private GoodsAttrValDao goodsAttrValDao;
    @Override
    public List<GoodsAttrValDto> queryByPid(int pid, int agentId) {
        return goodsAttrValDao.queryByPid(pid,agentId);
    }

    @Override
    public List<GoodsAttrValDto> queryByGdsId(Integer goodsId) {
        return goodsAttrValDao.queryByGdsId(goodsId);
    }

    @Override
    public List<GoodsAttrValEntity> findByGdsId(int prodId) {
        return goodsAttrValDao.findByGdsId(prodId);
    }

    @Override
    public List<Map<String, Object>> findGoodsAttrVal(int pid, int agentId) {
        return goodsAttrValDao.findGoodsAttrVal(pid,  agentId);
    }

    @Override
    public GoodsAttrValueVo selectGoods(List<GoodsAttrValueReponse> goodsAttrValueReponses, Long prdId) {
        return goodsAttrValDao.selectGoods(goodsAttrValueReponses,prdId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(GoodsAttrValEntity goodsAttrValEntity) {
        return goodsAttrValDao.updateByPrimaryKeySelective(goodsAttrValEntity);
    }
}
