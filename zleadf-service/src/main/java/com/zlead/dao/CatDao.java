package com.zlead.dao;

import com.zlead.entity.CatEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.util.List;
import java.util.Map;

public interface CatDao {
    /**
     * This method:deleteByPrimaryKey
     *   t_cat
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   t_cat
     *
     * @ET
     */
    int insert(CatEntity record);

    /**
     * This method:insertSelective
     *   t_cat
     *
     * @ET
     */
    int insertSelective(CatEntity record);

  
   // List<CatEntity> selectByExample(CatEntityExample example);
    PageList<CatEntity> findPage(Map params,PageBounds rowBounds);
    
    List<CatEntity> getList(Map params);
    
    /**
     * This method:selectByPrimaryKey
     *   t_cat
     *
     * @ET
     */
    CatEntity selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_cat
     *
     * @ET
     */
    int updateByPrimaryKeySelective(CatEntity record);

    /**
     * This method:updateByPrimaryKey
     *   t_cat
     *
     * @ET
     */
    int updateByPrimaryKey(CatEntity record);
}