package com.zlead.fplat.entity.vo;

import com.zlead.fplat.entity.CrmPrdCat;
import com.zlead.fplat.entity.CrmPrdList;
import com.zlead.fplat.entity.Custband;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/16.
 * 筛选导航数据
 */
public class Screen implements Serializable {

    //品牌的集合
    private List<Custband> custbandList;

    //系列的集合
    private List<CrmPrdList> crmPrdListList;

    //分类的集合
    private List<CrmPrdCat> crmPrdCatList;

    public List<Custband> getCustbandList() {
        return custbandList;
    }

    public void setCustbandList(List<Custband> custbandList) {
        this.custbandList = custbandList;
    }

    public List<CrmPrdList> getCrmPrdListList() {
        return crmPrdListList;
    }

    public void setCrmPrdListList(List<CrmPrdList> crmPrdListList) {
        this.crmPrdListList = crmPrdListList;
    }

    public List<CrmPrdCat> getCrmPrdCatList() {
        return crmPrdCatList;
    }

    public void setCrmPrdCatList(List<CrmPrdCat> crmPrdCatList) {
        this.crmPrdCatList = crmPrdCatList;
    }
}
