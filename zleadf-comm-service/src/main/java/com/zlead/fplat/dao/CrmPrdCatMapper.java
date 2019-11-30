package com.zlead.fplat.dao;

import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdCatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmPrdCatMapper {
    int countByExample(CrmPrdCatExample example);

    int deleteByExample(CrmPrdCatExample example);

    int deleteByPrimaryKey(Integer catId);

    int insert(CrmPrdCat record);

    int insertSelective(CrmPrdCat record);

    List<CrmPrdCat> selectByExample(CrmPrdCatExample example);

    CrmPrdCat selectByPrimaryKey(Integer catId);

    int updateByExampleSelective(@Param("record") CrmPrdCat record, @Param("example") CrmPrdCatExample example);

    int updateByExample(@Param("record") CrmPrdCat record, @Param("example") CrmPrdCatExample example);

    int updateByPrimaryKeySelective(CrmPrdCat record);

    int updateByPrimaryKey(CrmPrdCat record);
}