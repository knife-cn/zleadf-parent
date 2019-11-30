package com.zlead.fplat.entity.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/19.
 */
public class SecondCatRequest implements Serializable {
    //一级分类名称
    private String firstCatName;

    //工厂ids

    private List<Integer> facIds;
    
    private List<Integer> shopIds;

    public List<Integer> getShopIds() {
		return shopIds;
	}

	public void setShopIds(List<Integer> shopIds) {
		this.shopIds = shopIds;
	}

	public String getFirstCatName() {
        return firstCatName;
    }

    public void setFirstCatName(String firstCatName) {
        this.firstCatName = firstCatName;
    }

    public List<Integer> getFacIds() {
        return facIds;
    }

    public void setFacIds(List<Integer> facIds) {
        this.facIds = facIds;
    }
}
