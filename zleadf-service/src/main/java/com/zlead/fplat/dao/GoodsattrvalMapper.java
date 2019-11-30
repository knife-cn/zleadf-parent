package com.zlead.fplat.dao;

import com.zlead.fplat.entity.Goodsattrval;
import com.zlead.fplat.entity.GoodsattrvalExample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsattrvalMapper {
    /**
     * This method:deleteByPrimaryKey
     *   t_goods_attr_val
     *
     * @ET
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method:insert
     *   t_goods_attr_val
     *
     * @ET
     */
    int insert(Goodsattrval record);

    /**
     * This method:insertSelective
     *   t_goods_attr_val
     *
     * @ET
     */
    int insertSelective(Goodsattrval record);

    /**
     * This method:selectByExample
     *   t_goods_attr_val
     *
     * @ET
     */
    List<Goodsattrval> selectByExample(GoodsattrvalExample example);

    /**
     * This method:selectByPrimaryKey
     *   t_goods_attr_val
     *
     * @ET
     */
    Goodsattrval selectByPrimaryKey(Long id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_goods_attr_val
     *
     * @ET
     */
    int updateByPrimaryKeySelective(Goodsattrval record);

    /**
     * This method:updateByPrimaryKey
     *   t_goods_attr_val
     *
     * @ET
     */
    int updateByPrimaryKey(Goodsattrval record);

    /**
     *  通过商品ID 获取属性
     *   t_goods_attr
     *
     * @ET
     */

     List<String> getByGoodsId(@Param("goodsId") Long goodsId);

}