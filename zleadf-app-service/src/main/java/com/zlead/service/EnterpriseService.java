package com.zlead.service;

import com.zlead.entity.AdsEntity;
//import com.zlead.entity.ArticleEntity;
import com.zlead.entity.GoodsEntity;
import com.zlead.entity.ShopEntity;
import com.zlead.util.page.PageList;

import java.util.List;
import java.util.Map;

public interface EnterpriseService {
    /**
     * 查询企业banner
     */
    List<AdsEntity> getAdsList(String memberId);

    /**
     * 判断企业信息
     * @param shopId
     * @return
     */
    Map shopInfo(Long shopId);

   
    /**
     * 查询企业的商品
     * @param prodType
     * @param page
     * @param shopId
     * @return
     */
    PageList<GoodsEntity> getGoodsList(Integer prodType, Integer page, Long shopId);

    /**
     * 查询企业的首页商品
     * @param showNum
     * @param shopId
     * @return
     */
    List<GoodsEntity> gethomeGoodsList(Integer showNum,Long shopId);

    /**
     * 修改企业信息
     * @param shop
     * @return
     */
   Boolean updateEnterprise(ShopEntity shop);

    /**
     * 查询企业的首页商品
     * @param showNum
     * @param shopId
     * @return
     */
    List<GoodsEntity> getGoodsList(Integer showNum);
}
