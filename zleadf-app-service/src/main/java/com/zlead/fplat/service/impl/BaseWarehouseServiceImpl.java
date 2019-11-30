package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.BaseWarehouseMapper;
import com.zlead.fplat.dao.CustbandMapper;
import com.zlead.fplat.entity.BaseWarehouse;
import com.zlead.fplat.service.BaseWarehouseService;
import com.zlead.fplat.service.CustbandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class BaseWarehouseServiceImpl implements BaseWarehouseService {
    @Autowired
    private BaseWarehouseMapper baseWarehouseMapper;

    @Override
    public Set<Integer> getWhIdByUserIds(Set<Integer> userIds, Integer sysId) {
        Set<Integer> whIds = new HashSet<>();
        List<BaseWarehouse> list = baseWarehouseMapper.findMgrUserIdByUserId(sysId);
        if (list == null || list.size() == 0) {
            return whIds;
        }
        for (BaseWarehouse item : list) {
            if (StringUtils.isBlank(item.getMgrUserid())) {
                continue;
            }
            String[] userIdArray = item.getMgrUserid().split(",");
            for (String userId : userIdArray) {
                if (userIds.contains(Integer.parseInt(userId))) {
                    whIds.add(item.getWhId());
                    break;
                }
            }
        }
        return whIds;
    }
}
