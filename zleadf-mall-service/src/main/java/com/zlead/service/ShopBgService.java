package com.zlead.service;

import com.zlead.entity.SysUserEntity;
import com.zlead.entity.ShopEntity;
import java.util.Map;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

/**
 * 第三方入驻店铺
 *
 * @author fqf
 * @date 2018-07-23 17:10:08
 */
public interface ShopBgService {

    PageList<ShopEntity> getPage(Map params, PageBounds rowBounds);

    void save(ShopEntity entity);

    void update(ShopEntity entity);

    void delete(Long id);

    ShopEntity findById(Long id);
    //通过memberid查询信息
    ShopEntity findByMemeberId(String memeberId);
    /**
     * 供应商审核
     * @param shopEntity
     * @return
     */
    boolean auditShop(ShopEntity shopEntity, SysUserEntity userEntity);
}

