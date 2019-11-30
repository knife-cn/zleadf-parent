package com.zlead.fplat.entity.vo;

import java.io.Serializable;

/**
 * Created by admin on 2019/1/16.
 * 首页导航栏信息
 */
public class NavigationVo implements Serializable {

    //筛选
    private Screen screen;

    //工厂列表
    private FactoryList factoryList;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public FactoryList getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(FactoryList factoryList) {
        this.factoryList = factoryList;
    }
}
