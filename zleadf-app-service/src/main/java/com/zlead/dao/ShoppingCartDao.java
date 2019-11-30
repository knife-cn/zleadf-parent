package com.zlead.dao;

import com.zlead.entity.ShoppingCartEntity;
import com.zlead.entity.vo.ShoppingCartVo;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
public interface ShoppingCartDao {

    void insert(ShoppingCartEntity entity);

    void update(ShoppingCartEntity entity);

    void delete(Long id);

    PageList<ShoppingCartEntity> findPage(Map params, PageBounds rowBounds);

    ShoppingCartEntity findById(Long id);

    //查询购物车的店铺
    List<ShoppingCartEntity> shopList(Map params);

    //查询购物车的店铺
    List<Long> shopCartIdList(@Param(value = "shopId") Long shopId,@Param(value = "goodsId") Long goodsId,@Param(value = "memberId") Long memberId);

    //根据店铺去查询购物车的信息
    List<ShoppingCartVo> findShoppingCart(Map params);

    ShoppingCartEntity findByUnique(@Param(value = "memberId") Long memberId, @Param(value = "goodsId") Long goodsId,
                                     @Param(value = "type") int type, @Param(value = "shopId") Long shopId);

    //删除多个购物车
    void deleteIds(@Param(value = "ids")String ids);

    ShoppingCartEntity shopingCartInfo(@Param(value = "memberId") Long memberId, @Param(value = "goodsId") Long goodsId);

    List<ShoppingCartEntity> shopListByids(@Param(value = "ids")List<Integer> ids);

    //通过店铺和ID查询信息
    List<ShoppingCartVo> shoppingCartByids(@Param(value = "ids")List<Integer> ids,@Param(value = "shopId") Long shopId);
    List<ShoppingCartEntity> cartByids(@Param(value = "ids")List<Integer> ids);
    Long getBygoodsId(@Param(value = "goodsId")Long goodsId,@Param(value = "memberId")Long memberId);

}
