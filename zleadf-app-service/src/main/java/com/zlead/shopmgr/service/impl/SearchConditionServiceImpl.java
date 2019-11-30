package com.zlead.shopmgr.service.impl;

import com.zlead.entity.vo.*;
import com.zlead.fplat.dao.SearchConditionMapper;
import com.zlead.shopmgr.service.SearchConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchConditionServiceImpl implements SearchConditionService {

    @Autowired
    private SearchConditionMapper searchConditionMapper;

    private List<SearchConditionListVO> getWarehouseList(Long memberId)  {
        return searchConditionMapper.getWarehouseList(memberId);
    }

    private List<SearchConditionListVO> getCrmCustBrandList(Long memberId) {
        return searchConditionMapper.getCrmCustBrandList(memberId);
    }

    private List<SearchConditionListVO> getCrmPrdLists(Long memberId) {
        return searchConditionMapper.getCrmPrdLists(memberId);
    }

    private List<SearchConditionListVO> getCrmPrdModelList(Long memberId) {
        return searchConditionMapper.getCrmPrdModelList(memberId);
    }

    private List<SearchConditionListVO> getCrmPrdCatList(Long memberId) {
        return searchConditionMapper.getCrmPrdCatList(memberId);
    }

    @Override
    public List<CrmCustLevelListVO> getCrmCustLevelList(Long memberId) {
        return searchConditionMapper.getCrmCustLevelList(memberId);
    }

    @Override
    public Map<String, Object> getStockSearchCondition(Long memberId) {
        Map<String, Object> stockSearchCondition = new HashMap<>();
        stockSearchCondition.put("warehouseList", getWarehouseList(memberId));
        stockSearchCondition.put("brandList", getCrmCustBrandList(memberId));
        stockSearchCondition.put("lists", getCrmPrdLists(memberId));
        stockSearchCondition.put("modelList", getCrmPrdModelList(memberId));
        stockSearchCondition.put("catList", getCrmPrdCatList(memberId));
        return stockSearchCondition;
    }
}
