package com.zlead.fplat.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/23.
 * 查询二级分类重名条件
 */
public class CatSecondQueryRequest implements Serializable {
    //不重名的二级分类集合
    private List<String> seCat;

    //工厂ids
    private List<Integer> facIds;

    public List<String> getSeCat() {
        return seCat;
    }

    public void setSeCat(List<String> seCat) {
        this.seCat = seCat;
    }

    public List<Integer> getFacIds() {
        return facIds;
    }

    public void setFacIds(List<Integer> facIds) {
        this.facIds = facIds;
    }
}
