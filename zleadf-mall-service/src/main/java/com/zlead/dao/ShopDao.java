package com.zlead.dao;

import com.zlead.entity.ShopEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.util.List;
import java.util.Map;

/**
 * 第三方入驻店铺
 * 
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
public interface ShopDao {

    void insert(ShopEntity entity);

    void update(ShopEntity entity);

    void delete(Long id);

    PageList<ShopEntity> findPage(Map params, PageBounds rowBounds);
    //根据id查询店铺信息
    ShopEntity findById(Long id);
    ShopEntity findByWxUnionid(String WxUnionid);
    //通过后台管理系统的id找到店铺的信息
    ShopEntity findByMgrUserId(Long mgrUserId);
    //通过memberid查询信息
    ShopEntity findByMemeberId(String memeberId);

    void updateEnterprise(ShopEntity shopEntity);

    Map<String,Object> shopdsList(Long memid);

    List<ShopEntity> shopdsLists(Long memid);

    int shopdsListSize(Long memid);

    List<ShopEntity> shopdstop();

    ShopEntity findEnterprise(Long shopId);
}
