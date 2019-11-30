package com.zlead.fplat.entity.vo;

import com.zlead.fplat.entity.CrmPrdCat;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatTreeDisplay implements Serializable {
    private Integer shopId;//归属店铺
    private Integer id;//分类ID
    private String name;//分类展示名称（一级分类名称）
    private List<CrmPrdCat> levelOneList = new ArrayList<>();//该分类名称包含的所有一级分类（不同工厂分类名称可能相同）
    private List<CrmPrdCat> levelTwoList = new ArrayList<>();//该分类名称包含的所有二级分类
    private List<CrmPrdCat> allList = new ArrayList<>();//该分类名称包含的所有子分类（一级分类、二级分类、三级分类）

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CrmPrdCat> getLevelOneList() {
        return levelOneList;
    }

    public List<CrmPrdCat> getLevelTwoList() {
        return levelTwoList;
    }

    public List<CrmPrdCat> getAllList() {
        return allList;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("【");
        sb.append("name=").append(this.getName()).append(",");
        if(CollectionUtils.isNotEmpty(this.getLevelOneList())){
            sb.append("一级=【");
            for(CrmPrdCat crmPrdCat : this.getLevelOneList()){
                sb.append("{shopId=").append(crmPrdCat.getShopId()).append(",");
                sb.append("catId=").append(crmPrdCat.getCatId()).append(",");
                sb.append("catName=").append(crmPrdCat.getCatName()).append("}");
            }
            sb.append("】");
        }
        if(CollectionUtils.isNotEmpty(this.getLevelTwoList())){
            sb.append("二级=【");
            for(CrmPrdCat crmPrdCat : this.getLevelTwoList()){
                sb.append("{shopId=").append(crmPrdCat.getShopId()).append(",");
                sb.append("catId=").append(crmPrdCat.getCatId()).append(",");
                sb.append("catName=").append(crmPrdCat.getCatName()).append("}");
            }
            sb.append("】");
        }
        if(CollectionUtils.isNotEmpty(this.getAllList())){
            sb.append("全部=【");
            for(CrmPrdCat crmPrdCat : this.getAllList()){
                sb.append("{shopId=").append(crmPrdCat.getShopId()).append(",");
                sb.append("catId=").append(crmPrdCat.getCatId()).append(",");
                sb.append("catName=").append(crmPrdCat.getCatName()).append("}");
            }
            sb.append("】");
        }
        sb.append("】");
        return sb.toString();
    }
}
