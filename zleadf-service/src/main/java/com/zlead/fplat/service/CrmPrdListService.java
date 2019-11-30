package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系列相关接口
 */
public interface CrmPrdListService {
    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByIdsAndKey(Set<Long> ids, String key);

    List<Map<String, Object>> findNameListByIdsAndKeyAPP(Set<Long> ids, String key);

    List<Map<String, Object>> findByIds(Set<Long> ids);

    List<Map<String, Object>> findListsByShopId(Long ownShopid);
}
