package com.zlead.service;

import com.zlead.entity.*;
import com.zlead.util.page.PageBounds;
import com.zlead.util.page.PageList;

import java.util.Map;

public interface EnterpriseBgService {
    /**
     * 企业用户添加商品
     * @param goodsEntity
     * @param memberEntity
     * @return
     */
    boolean enterpriseGoodsSave(GoodsEntity goodsEntity, MemberEntity memberEntity);

    /**
     * 查询商品信息
     * @param params
     * @param rowBounds
     * @param memberEntity
     * @return
     */
    PageList<GoodsEntity> getPage(Map params, PageBounds rowBounds, MemberEntity memberEntity);

    /**
     * 企业的店铺信息
     * @param memberEntity
     * @return
     */
    ShopEntity enterpriseShopInfo(MemberEntity memberEntity);

    /**
     * 查询企业订单
     * @param params
     * @param rowBounds
     * @param memberEntity
     * @return
     */
    PageList<OrderEntity> enterpriseOrderList(Map params, PageBounds rowBounds, MemberEntity memberEntity);

 
    /**
     * 企业发布banner
     * @param tAdsEntity
     * @param memberEntity
     * @return
     */
    boolean enterpriseSaveAds(AdsEntity adsEntity, MemberEntity memberEntity);

    /**
     * 企业发货
     * @param tOrderShippingEntity
     * @param memberEntity
     * @return
     */
    boolean enterpriseShipments(OrderShippingEntity orderShippingEntity, MemberEntity memberEntity);

    /**
     * 企业banner的列表
     * @param params
     * @param rowBounds
     * @param memberEntity
     * @return
     */
    PageList<AdsEntity> getAdsList(Map params, PageBounds rowBounds, MemberEntity memberEntity);


    PageList<AdsEntity> getAllAds(PageBounds rowBounds);
}
