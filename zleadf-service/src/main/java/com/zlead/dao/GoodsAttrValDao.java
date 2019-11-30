package com.zlead.dao;

import com.zlead.entity.GoodsAttrValEntity;
import com.zlead.entity.dto.GoodsAttrValDto;
import com.zlead.entity.httpResponse.GoodsAttrValueReponse;
import com.zlead.entity.vo.GoodsAttrValueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/16.
 * @Desoription TODO
 */
public interface GoodsAttrValDao {

    /**
     * 产品参数详情
     *查询商品为平台商品获取全部的商品（2,3）且以上架的商品
     * @param pid
     * @return
     */
    List<GoodsAttrValDto> queryByPid(@Param("pid") int pid, @Param("agentId") int agentId);

    List<GoodsAttrValDto> queryByGdsId(Integer goodsId);

    List<GoodsAttrValEntity> findByGdsId(int prodId);

    List<Map<String, Object>> findGoodsAttrVal(@Param("pid") int pid, @Param("agentId") int agentId);

    GoodsAttrValueVo selectGoods(@Param("list") List<GoodsAttrValueReponse> goodsAttrValueReponses, @Param("prdId") Long prdId);

    Integer updateByPrimaryKeySelective(GoodsAttrValEntity goodsAttrValEntity);

    List<GoodsAttrValDto> findActGoodsAttrs(@Param("actId") Long actId, @Param("prodId") Long prodId);

    List<Map<String, Object>> findActGoodsAttrVal(@Param("actId") Long actId, @Param("prodId") Integer prodId);

    /**
     * 根据产品ID查询该产品所有的上架商品属性
     * @param prodId
     * @return
     */
    List<GoodsAttrValEntity> findGoodsAttrByProdId(int prodId);
}
