package com.zlead.fplat.service.impl;

import com.zlead.fplat.dao.CrmPrdModelMapper;
import com.zlead.fplat.entity.CrmPrdModel;
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
    public List<Map<String, Object>> findNameListByListIdsAndKey(Set<Long> listIds, String key) {
        return mapper.findNameListByListIdsAndKey(listIds, key);
    }

    @Override
    public List<Map<String, Object>> findNameListByListIdsAndKeyAPP(Set<Long> listIds, String key) {
        return mapper.findNameListByListIdsAndKeyAPP(listIds, key);
    }

    @Override
    public List<Map<String, Object>> findByIds(Set<Long> ids) {
        return mapper.findByIds(ids);
    }

    /**
     * This method:selectByPrimaryKey
     * crm_prd_model
     *
     * @ET
     */
    public CrmPrdModel selectByPrimaryKey(Integer modelId){
        return mapper.selectByPrimaryKey(modelId);
    }

    @Override
    public List<Map<String, Object>> findModelsByShopId(Long ownShopid) {
        return mapper.findById(ownShopid);
    }
}
