package com.zlead.service.impl;

import com.zlead.dao.*;
import com.zlead.entity.*;
import com.zlead.service.*;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业后台管理
 */
@Transactional
@Service
public class EnterpriseBgServiceImpl implements EnterpriseBgService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private GoodsBgService goodsBgService;

    @Autowired
    private OrderDao orderDao;

//    @Autowired
//    private ArticleBgService articleBgService;
//
//    @Autowired
//    private ArticleDao articleDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private AdsBgService adsBgService;

    @Autowired
    private OrderShippingBgService orderShippingBgService;

    @Autowired
    private AdsDao adsDao;

    /**
     * 查询商品信息(获取该企业的商品)
     */
    @Transactional(readOnly = true)
    public PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds, MemberEntity memberEntity) {
        //查询该企业的店铺信息
        ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
        if(shopEntity==null){
            return  null;
        }
        if(shopEntity.getShopType()!=null&&shopEntity.getShopType()!=8){
            return null;
        }
        params.put("supplierShopId",shopEntity.getId());
        params.put("shopId",shopEntity.getId());
        PageList list = goodsDao.findPage(params, rowBounds);
        return list;
    }

    /**
     * 企业用户添加商品
     */
    public boolean enterpriseGoodsSave(GoodsEntity goodsEntity,MemberEntity memberEntity){
        boolean b = true;
        //查询该登录的供应商店铺信息
        try{
            ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
            if(shopEntity==null){
                return false;
            }
            goodsBgService.goodSave(goodsEntity,memberEntity.getId(),shopEntity);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    /**
     * 企业的信息
     */
    public ShopEntity enterpriseShopInfo(MemberEntity memberEntity){
        ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
        return shopEntity;

    }

    /**
     * 查询企业的订单
     */
    public PageList<OrderEntity> enterpriseOrderList(Map params, PageBounds rowBounds, MemberEntity memberEntity) {
        //查询该企业的店铺信息
        ShopEntity shopEntity = shopDao.findByMemeberId(memberEntity.getMemberId());
        if(shopEntity==null){
            return  null;
        }
        if(shopEntity.getShopType()!=null&&shopEntity.getShopType()!=8){
            return null;
        }
        params.put("shopId",shopEntity.getId());
        params.put("orderType",11);
        PageList list = orderDao.getPage(params, rowBounds);
        return list;
    }

    /**
     * 订单发货
     */
    public boolean enterpriseShipments(OrderShippingEntity OrderShippingEntity, MemberEntity memberEntity){
        boolean b = true;
        try{
            orderShippingBgService.saveOrderShipping(OrderShippingEntity,memberEntity.getId(),1);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    


    /**
     * 查询banner列表（企业的benner）
     */
    @Transactional(readOnly = true)
    public PageList<AdsEntity> getAdsList(Map params, PageBounds rowBounds, MemberEntity memberEntity) {
        //params.put("addUserId",memberEntity.getId());
    	params.put("shopId",params.get("shopId"));
//        params.put("adstype",1);
        params.put("showStatus",1);
        PageList list = adsDao.findPage(params, rowBounds);
        return list;
    }

    /**
     * 查询banner列表（全部）
     */
    @Override
    @Transactional(readOnly = true)
    public PageList<AdsEntity> getAllAds( PageBounds rowBounds) {

        PageList list = adsDao.findAllPage(rowBounds);
        return list;
    }

    /**
     * 企业用户发布banner
     * @param
     * @param
     * @return
     */
    public boolean enterpriseSaveAds(AdsEntity adsEntity, MemberEntity memberEntity){
        boolean b = true;
        try{
        	if(adsEntity.getId()!=null && adsEntity.getId()>0){
        		adsBgService.update(adsEntity);
        	}else{
        		adsBgService.saveAds(adsEntity,memberEntity.getId(),1L);
        	}
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }
}
