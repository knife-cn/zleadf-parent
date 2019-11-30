package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系列相关接口
 */
public interface CrmPrdListService {
    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByIds(Set<Long> ids);
}
