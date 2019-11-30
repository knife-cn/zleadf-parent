package com.zlead.fplat.service;

import com.zlead.fplat.entity.vo.CatTree;
import com.zlead.fplat.entity.vo.HomeProductsVo;
import com.zlead.fplat.entity.vo.Screen;

import java.util.Collection;
import java.util.List;


public interface FastSearchService {

    /**
     * 首页品牌、系列、分类展示
     * @param agentId
     * @return
     */
    Screen displayBrandListCat(Long agentId);

    /**
     * 首页商品展示区域
     * @param agentId
     * @return
     */
    List<HomeProductsVo> displayGoodsArea(Long agentId);

    /**
     * 根据分类ID查询分类树形结构
     * @param catIds
     * @param flag 是否构造二级分类标志
     * @return
     */
    List<CatTree> findCatTreeByCatId(Collection<Integer> catIds, boolean flag);
}
