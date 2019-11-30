package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.CrmPrdListMapper;
import com.zlead.fplat.service.CrmPrdListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CrmPrdListServiceImpl implements CrmPrdListService {
    @Autowired
    private CrmPrdListMapper mapper;

    @Override
    public List<Map<String, Object>> findAllNameList() {
        return mapper.findAllNameList();
    }

    @Override
    public List<Map<String, Object>> findNameListByIdsAndKey(Set<Long> ids, String key) {
        return mapper.findNameListByIdsAndKey(ids, key);
    }

    @Override
    public List<Map<String, Object>> findNameListByIdsAndKeyAPP(Set<Long> ids, String key) {
        return mapper.findNameListByIdsAndKeyAPP(ids, key);
    }

    @Override
    public List<Map<String, Object>> findByIds(Set<Long> ids) {
        return mapper.findByIds(ids);
    }

    @Override
    public List<Map<String, Object>> findListsByShopId(Long ownShopid) {
        return mapper.findById(ownShopid);
    }
}
