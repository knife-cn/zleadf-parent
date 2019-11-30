package com.zlead.service;

import com.zlead.entity.SysUserEntity;
import com.zlead.entity.ProductEntity;

import java.util.Map;

import com.zlead.entity.ShopEntity;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import javax.servlet.http.HttpServletRequest;

/**
 * 产品
 *
 * @author fqf
 * @date 2018-07-25 11:41:32
 */
public interface ProductBgService {

    PageList<ProductEntity> getPage(Map params, PageBounds rowBounds);

    void save(ProductEntity entity);

    void update(ProductEntity entity);

    void delete(Long id);

    ProductEntity findById(Long id);

    /**
     * 保存商品
     *
     * @param productEntity
     * @return
     */
    boolean productSave(ProductEntity productEntity, Long userId, ShopEntity shopEntity, HttpServletRequest request);

    /**
     * 产品审核
     */
    boolean productAudit(Long id, SysUserEntity userEntity);

    /**
     * 产品删除
     */
    boolean productDelete(Long id);

    /**
     * 修改产品
     *
     * @param productEntity
     * @param request
     * @return
     */
    boolean productUpdate(ProductEntity productEntity, HttpServletRequest request);

    /**
     * 管理员保存产品
     */
    boolean sysProductSave(ProductEntity productEntity, SysUserEntity userEntity, HttpServletRequest request);

    /**
     * 2019年1月22日 15:29:28
     * 保存产品
     */
    void insert(ProductEntity productEntity);

    /**
     * 根据产品id生成商品
     */
    boolean createGoods(Long prodId);
}

