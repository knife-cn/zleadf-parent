package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Prodattrval;
import com.zlead.fplat.entity.ProdattrvalExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProdattrvalMapper {
    /**
     * This method:deleteByPrimaryKey
     * t_prod_attr_val
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     * t_prod_attr_val
     *
     * @ET
     */
    int insert(Prodattrval record);

    /**
     * This method:insertSelective
     * t_prod_attr_val
     *
     * @ET
     */
    int insertSelective(Prodattrval record);

    /**
     * This method:selectByExample
     * t_prod_attr_val
     *
     * @ET
     */
    List<Prodattrval> selectByExample(ProdattrvalExample example);

    /**
     * This method:selectByPrimaryKey
     * t_prod_attr_val
     *
     * @ET
     */
    Prodattrval selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     * t_prod_attr_val
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prodattrval record);

    /**
     * This method:updateByPrimaryKey
     * t_prod_attr_val
     *
     * @ET
     */
    int updateByPrimaryKey(Prodattrval record);

    List<Prodattrval> selectByProdId(@Param("prodId") Long prodId);

    List<Map<String, Object>> selectGroupAttrVal(@Param("prodId") Long prodId);
}