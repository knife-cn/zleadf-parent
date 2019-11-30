package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Goodsattr;
import com.zlead.fplat.entity.GoodsattrExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsattrMapper {
    /**
     * This method:deleteByPrimaryKey
     *   t_goods_attr
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   t_goods_attr
     *
     * @ET
     */
    int insert(Goodsattr record);

    /**
     * This method:insertSelective
     *   t_goods_attr
     *
     * @ET
     */
    int insertSelective(Goodsattr record);

    /**
     * This method:selectByExample
     *   t_goods_attr
     *
     * @ET
     */
    List<Goodsattr> selectByExample(GoodsattrExample example);

    /**
     * This method:selectByPrimaryKey
     *   t_goods_attr
     *
     * @ET
     */
    Goodsattr selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_goods_attr
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Goodsattr record);

    /**
     * This method:updateByPrimaryKey
     *   t_goods_attr
     *
     * @ET
     */
    int updateByPrimaryKey(Goodsattr record);



}