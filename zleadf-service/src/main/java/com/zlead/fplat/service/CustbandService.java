package com.zlead.fplat.service;

import com.zlead.fplat.entity.Custband;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustbandService {
    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findByIds(Set<Long> ids);

    List<Map<String, Object>> findBrandsByShopId(Long ownShopid);
}
