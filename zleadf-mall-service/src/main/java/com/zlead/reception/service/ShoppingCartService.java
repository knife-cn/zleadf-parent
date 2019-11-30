package com.zlead.reception.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShoppingCartEntity;

import java.util.List;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-08-01 11:04:28
 */
public interface ShoppingCartService {

    PageList<ShoppingCartEntity> getPage(Map params, PageBounds rowBounds);

    void save(ShoppingCartEntity entity);

    void update(ShoppingCartEntity entity);

    void delete(Long id);

    ShoppingCartEntity findById(Long id);

    List<Map<String, Object>> getShoppingCart(MemberEntity memberEntity, ShoppingCartEntity tShoppingCartEntity, Integer prodType, HttpServletRequest request);

    String shoppingCartSave(ShoppingCartEntity shoppingCartEntity,MemberEntity memberEntity);

    void delete(String ids);

    void deleteBymemberIdAndGoodsId(Long memberId,Long goodsId);

    Map<String,Object> updateCount(Long cartId,Integer type,Integer count);

    Map<String,Object> updateGoodsId(Long cartId,Long goodsId,List<Long> otherIds);

    ShoppingCartEntity shopingCartInfo(Long memberId, Long goodsId);


}

