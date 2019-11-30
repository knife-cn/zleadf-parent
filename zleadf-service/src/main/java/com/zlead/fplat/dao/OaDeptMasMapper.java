package com.zlead.fplat.dao;

import com.zlead.fplat.entity.OaDeptMas;
import com.zlead.fplat.entity.OaDeptMasExample;
import java.util.List;

public interface OaDeptMasMapper {
    /**
     * This method:deleteByPrimaryKey
     *   oa_dept_mas
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * This method:insert
     *   oa_dept_mas
     *
     * @ET
     */
    int insert(OaDeptMas record);

    /**
     * This method:insertSelective
     *   oa_dept_mas
     *
     * @ET
     */
    int insertSelective(OaDeptMas record);

    /**
     * This method:selectByExample
     *   oa_dept_mas
     *
     * @ET
     */
    List<OaDeptMas> selectByExample(OaDeptMasExample example);

    /**
     * This method:selectByPrimaryKey
     *   oa_dept_mas
     *
     * @ET
     */
    OaDeptMas selectByPrimaryKey(Integer deptId);

    /**
     * This method:updateByPrimaryKeySelective
     *   oa_dept_mas
     *
     * @ET
     */
    int updateByPrimaryKeySelective(OaDeptMas record);

    /**
     * This method:updateByPrimaryKey
     *   oa_dept_mas
     *
     * @ET
     */
    int updateByPrimaryKey(OaDeptMas record);
}