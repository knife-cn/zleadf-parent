package com.zlead.reception.service;

import com.zlead.entity.MemberEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 第三方入驻店铺
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
public interface ShopService {

    PageList<ShopEntity> getPage(Map params, PageBounds rowBounds);

    void save(ShopEntity entity);

    void update(ShopEntity entity);

    void delete(Long id);

    ShopEntity findById(Long id);

    /**
     * 供应商入驻
     * @param shopEntity
     * @param member
     * @return
     */
    boolean saveShop(ShopEntity shopEntity,MemberEntity member,HttpServletRequest request);
    boolean saveShopFromSSO(ShopEntity shopEntity,HttpServletRequest request);

    ShopEntity findByMemberId(String memberId);

    boolean updateShop(ShopEntity shopEntity,HttpServletRequest request);
    ShopEntity findByWxUnionid(String WxUnionid);

    List<ShopEntity> shopdsLists(Long memid);

    Map<String,Object> shopdsList(Long memid);

    int shopdsListSize(Long memid);

    List<ShopEntity> shopdstop();
}

