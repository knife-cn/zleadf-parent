package com.zlead.fplat.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/22.
 * 二级分类查询商品的条件
 */
public class GoodsQueryRequest implements Serializable {

    //商品的是否上架状态(0=库存商品,未上架 1=已上架, 2=已下架, 3=停止销售 )
    private  Integer isMarket;

    //二级分类的ids
    private List<Integer> ids;

    public Integer getIsMarket() {
        return isMarket;
    }

    public void setIsMarket(Integer isMarket) {
        this.isMarket = isMarket;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
