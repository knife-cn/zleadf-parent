package com.zlead.fplat.dao;

import com.zlead.entity.vo.*;

import java.util.List;

public interface SearchConditionMapper {

    /**
     *获取仓库列表
     * @return
     */
    List<SearchConditionListVO> getWarehouseList(Long memberId);

    /**
     *获取商品品牌列表
     * @return
     */
    List<SearchConditionListVO> getCrmCustBrandList(Long memberId);

    /**
     *获取商品系列列表
     * @return
     */
    List<SearchConditionListVO> getCrmPrdLists(Long memberId);

    /**
     *获取商品类型列表
     * @return
     */
    List<SearchConditionListVO> getCrmPrdModelList(Long memberId);

    /**
     *获取商品分类列表
     * @return
     */
    List<SearchConditionListVO> getCrmPrdCatList(Long memberId);

    /**
     *获取客户级别列表
     * @return
     */
    List<CrmCustLevelListVO> getCrmCustLevelList(Long memberId);

}
