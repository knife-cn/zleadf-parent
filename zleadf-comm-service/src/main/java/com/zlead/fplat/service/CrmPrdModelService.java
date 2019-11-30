package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 型号相关接口
 */
public interface CrmPrdModelService {
    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByListIds(Set<Long> listIds);
}
