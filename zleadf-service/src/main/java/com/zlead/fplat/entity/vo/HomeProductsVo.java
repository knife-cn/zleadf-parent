package com.zlead.fplat.entity.vo;

import com.zlead.entity.GoodsEntity;
import com.zlead.fplat.entity.CrmPrdCat;

import java.util.List;

/**
 * Created by admin on 2019/1/19.
 * 首页商品展示
 */
public class HomeProductsVo {
     //一级分类
    private CrmPrdCat FirstCrmPrdCat;

    //一级分类下的二级分类
    private List<CrmPrdCat> secondCrmPrdCat;

    //一级分类下的商品
    private List<GoodsEntity> goodsEntityList;

    public CrmPrdCat getFirstCrmPrdCat() {
        return FirstCrmPrdCat;
    }

    public void setFirstCrmPrdCat(CrmPrdCat firstCrmPrdCat) {
        FirstCrmPrdCat = firstCrmPrdCat;
    }

    public List<CrmPrdCat> getSecondCrmPrdCat() {
        return secondCrmPrdCat;
    }

    public void setSecondCrmPrdCat(List<CrmPrdCat> secondCrmPrdCat) {
        this.secondCrmPrdCat = secondCrmPrdCat;
    }

    public List<GoodsEntity> getGoodsEntityList() {
        return goodsEntityList;
    }

    public void setGoodsEntityList(List<GoodsEntity> goodsEntityList) {
        this.goodsEntityList = goodsEntityList;
    }
}
