package com.zlead.fplat.dao;

import com.zlead.fplat.entity.ShopGoodsrela; 
import java.util.List;

public interface ShopGoodsrelaMapper {
    /**
     * This method:deleteByPrimaryKey
     *   t_shop_goods_rela
     *
     * @ET
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method:insert
     *   t_shop_goods_rela
     *
     * @ET
     */
    int insert(ShopGoodsrela record);

    /**
     * This method:insertSelective
     *   t_shop_goods_rela
     *
     * @ET
     */
    int insertSelective(ShopGoodsrela record);

    /**
     * This method:selectByExample
     *   t_shop_goods_rela
     *
     * @ET
     */
//    List<ShopGoodsrela> selectByExample(ShopGoodsrelaExample example);

    /**
     * This method:selectByPrimaryKey
     *   t_shop_goods_rela
     *
     * @ET
     */
    ShopGoodsrela selectByPrimaryKey(Integer id);

    /**
     * This method:updateByPrimaryKeySelective
     *   t_shop_goods_rela
     *
     * @ET
     */
    int updateByPrimaryKeySelective(ShopGoodsrela record);

    /**
     * This method:updateByPrimaryKey
     *   t_shop_goods_rela
     *
     * @ET
     */
    int updateByPrimaryKey(ShopGoodsrela record);
}