package com.zlead.service.impl;

import com.zlead.dao.GoodsDao;
import com.zlead.dao.ProdImgDao;
import com.zlead.dao.ProductDao;
import com.zlead.dao.ShopDao;
import com.zlead.entity.*;
import com.zlead.service.SupplierService;
import com.zlead.service.ProductBgService;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 供应商信息
 */
@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private ProdImgDao prodImgDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ProductBgService productBgService;

    //查询该供应商商品列表
    @Transactional(readOnly = true)
    public PageList<ProductEntity> getPage(Map params, PageBounds rowBounds, MemberEntity memberEntity) {
        //查询该供应商的店铺信息
        ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
        if(shopEntity==null){
            return  null;
        }
        if(shopEntity.getShopType()!=null&&shopEntity.getShopType()!=1){
            return null;
        }
        params.put("supplierShopId",shopEntity.getId());
        PageList list = productDao.findPage(params, rowBounds);
        return list;
    }

    /**
     * 保存产品
     */
    public boolean supplierProductSave(ProductEntity productEntity, MemberEntity memberEntity, HttpServletRequest request) {
        boolean b = true;
        //查询该登录的供应商店铺信息
        try{
            ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
            if(shopEntity==null){
                return false;
            }
            productBgService.productSave(productEntity,memberEntity.getId(),shopEntity,request);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    /**
     * 供应商的信息
     */
    public ShopEntity supplierShopInfo(MemberEntity memberEntity){
        ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
        return shopEntity;

    }

}
