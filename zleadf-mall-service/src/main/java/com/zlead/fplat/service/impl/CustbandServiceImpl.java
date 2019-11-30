package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.CustbandMapper;
import com.zlead.fplat.entity.Custband;
import com.zlead.fplat.service.CustbandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CustbandServiceImpl implements CustbandService {
    @Autowired
    private CustbandMapper mapper;

    @Override
    public List<Map<String, Object>> findAllNameList() {
        return mapper.findAllNameList();
    }

    @Override
    public List<Map<String, Object>> findByIds(Set<Long> ids) {
        return mapper.findByIds(ids);
    }

    @Override
    public List<Map<String, Object>> findBrandsByShopId(Long ownShopid) {
        return mapper.findByShopId(ownShopid);
    }
}
