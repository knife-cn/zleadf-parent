package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.CrmPrdModelMapper;
import com.zlead.fplat.service.CrmPrdModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CrmPrdModelServiceImpl implements CrmPrdModelService {

    @Autowired
    private CrmPrdModelMapper mapper;

    @Override
    public List<Map<String, Object>> findAllNameList() {
        return mapper.findAllNameList();
    }

    @Override
    public List<Map<String, Object>> findNameListByListIds(Set<Long> listIds) {
        return null;
    }
}
