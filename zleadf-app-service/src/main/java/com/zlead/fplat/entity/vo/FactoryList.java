package com.zlead.fplat.entity.vo;

import com.zlead.fplat.entity.OaFactoryInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by admin on 2019/1/16.
 * 工厂列表
 */
public class FactoryList implements Serializable {

    //工厂的列表
    private HashMap<Character,List<OaFactoryInfo>> map = new LinkedHashMap<>();

    public HashMap<Character, List<OaFactoryInfo>> getMap() {
        return map;
    }

    public void setMap(HashMap<Character, List<OaFactoryInfo>> map) {
        this.map = map;
    }
}
