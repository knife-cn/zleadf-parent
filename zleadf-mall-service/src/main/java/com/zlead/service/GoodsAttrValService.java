package com.zlead.service;

import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsAttrValueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsAttrValService {

    /**
     * 产品参数详情
     *
     * @param pid
     * @return
     */
    List<GoodsAttrValDto> queryByPid(@Param("pid") int pid, @Param("agentId") int agentId);

    List<GoodsAttrValDto> queryByGdsId(Integer goodsId);

    List<GoodsAttrValEntity> findByGdsId(int prodId);

    List<Map<String, Object>> findGoodsAttrVal(@Param("pid") int pid, @Param("agentId") int agentId);

    GoodsAttrValueVo selectGoods(@Param("list") List<GoodsAttrValueReponse> goodsAttrValueReponses, @Param("prdId") Long prdId);

    Integer updateByPrimaryKeySelective(GoodsAttrValEntity goodsAttrValEntity);
}
