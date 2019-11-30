package com.zlead.fplat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BaseWarehouseService {

    Set<Integer> getWhIdByUserIds(Set<Integer> userIds, Integer sysId);
}
