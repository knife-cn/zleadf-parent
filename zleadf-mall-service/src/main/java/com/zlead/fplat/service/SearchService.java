package com.zlead.fplat.service;

import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.vo.Screen;

import java.util.List;


public interface SearchService {

    /**
     * 根据代理人ID和工厂ID查询首页展示的品牌、序列、分类
     * @param agentId
     * @param factoryId
     * @return
     */
    Screen findBrandListCatByAgent(Long agentId, Long factoryId);

    /**
     * 递归查询一级分类
     * @param catId
     * @return
     */
    CrmPrdCat getParentCat(Integer catId);

    /**
     * 根据代理人ID和工厂ID查询首页展示的一级分类
     * @param agentId
     * @param factoryId
     * @return
     */
    List<CrmPrdCat> findLevelOneCat(Long agentId, Long factoryId);
}
