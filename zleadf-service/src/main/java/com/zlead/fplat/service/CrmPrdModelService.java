package com.zlead.fplat.service;

import com.zlead.fplat.entity.CrmPrdModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 型号相关接口
 */
public interface CrmPrdModelService {
    List<Map<String, Object>> findAllNameList();

    List<Map<String, Object>> findNameListByListIdsAndKey(Set<Long> listIds,String key);


    List<Map<String, Object>> findNameListByListIdsAndKeyAPP(Set<Long> listIds,String key);

    List<Map<String, Object>> findByIds(Set<Long> ids);

    /**
     * This method:selectByPrimaryKey
     * crm_prd_model
     *
     * @ET
     */
    CrmPrdModel selectByPrimaryKey(Integer modelId);

    List<Map<String, Object>> findModelsByShopId(Long ownShopid);
}
