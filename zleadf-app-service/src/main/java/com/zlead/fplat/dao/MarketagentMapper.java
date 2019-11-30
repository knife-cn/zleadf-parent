package com.zlead.fplat.dao;

import com.zlead.entity.dto.GoodsPageDto;
import com.zlead.entity.vo.ActityGoodsVo;
import com.zlead.util.page.PageBounds;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketagentMapper {

    /**
     * 分页查询活动商品
     * @param agentId
     * @return
     */
    List<GoodsPageDto> findByPageAttrGoods(@Param("actId") int actId,@Param("agentId") Long agentId, PageBounds pageBounds);

    List<ActityGoodsVo> findByPageGoods(@Param("actId")int actId,@Param("shopId")int shopId, PageBounds pageBounds);

    int findTotalCount(@Param("actId") int actId,@Param("agentId") Long agentId);

    GoodsPageDto queryVaildActivity(@Param("agentId")Long agentId,@Param("goodsId")Long goodsId);
}