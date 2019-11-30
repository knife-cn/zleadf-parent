package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Prodattr;
import com.zlead.fplat.entity.ProdattrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProdattrMapper {
    /**
     * This method:deleteByPrimaryKey
     * t_prod_attr
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer attrId);

    /**
     * This method:insert
     * t_prod_attr
     *
     * @ET
     */
    int insert(Prodattr record);

    /**
     * This method:insertSelective
     * t_prod_attr
     *
     * @ET
     */
    int insertSelective(Prodattr record);

    /**
     * This method:selectByExample
     * t_prod_attr
     *
     * @ET
     */
    List<Prodattr> selectByExample(ProdattrExample example);

    /**
     * This method:selectByPrimaryKey
     * t_prod_attr
     *
     * @ET
     */
    Prodattr selectByPrimaryKey(Integer attrId);

    /**
     * This method:updateByPrimaryKeySelective
     * t_prod_attr
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Prodattr record);

    /**
     * This method:updateByPrimaryKey
     * t_prod_attr
     *
     * @ET
     */
    int updateByPrimaryKey(Prodattr record);

    List<Prodattr> selectByProdId(@Param("prodId") Long prodId);
}