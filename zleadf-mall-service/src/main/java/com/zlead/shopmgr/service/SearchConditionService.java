package com.zlead.shopmgr.service;

import com.zlead.entity.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface SearchConditionService {

    /**
     * 获取客户级别列表
     * @return
     */
    List<CrmCustLevelListVO> getCrmCustLevelList(Long memberId);

    /**
     * 获取库存列表筛选条件
     * @param memberId
     * @return
     */
    Map<String, Object> getStockSearchCondition(Long memberId);
}
