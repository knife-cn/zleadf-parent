package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分类接口
 */
public interface ProdcatService {
    /**
     * 获取所有的分类
     */
    List<Map<String, Object>> findAllNameList();

    /**
     * 根据传入的工厂获取分类
     */
    List<Map<String, Object>> findNameListByFactoryIds(Set<Long> factoryIds);
}
