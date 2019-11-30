package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.ProdcatMapper;
import com.zlead.fplat.service.ProdcatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class ProdcatServiceImpl implements ProdcatService {
    @Autowired
    private ProdcatMapper mapper;

    @Override
    public List<Map<String, Object>> findAllNameList() {
        return mapper.findAllNameList();
    }

    @Override
    public List<Map<String, Object>> findNameListByFactoryIds(Set<Long> factoryIds) {
        return mapper.findNameListByFactoryIds(factoryIds);
    }
}
