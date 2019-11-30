package com.zlead.fplat.entity.vo;

import com.zlead.fplat.entity.CrmPrdCat;

import java.io.Serializable;

/**
 * 分类树形结构，目前系统只支持到三级分类
 */
public class CatTree implements Serializable {
    private Integer catId;//当前分类ID
    private CrmPrdCat crmPrdCat;//当前分类
    private CrmPrdCat levelOne;//当前分类所属一级分类
    private CrmPrdCat levelTwo;//当前分类所属二级分类

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public CrmPrdCat getCrmPrdCat() {
        return crmPrdCat;
    }

    public void setCrmPrdCat(CrmPrdCat crmPrdCat) {
        this.crmPrdCat = crmPrdCat;
    }

    public CrmPrdCat getLevelOne() {
        return levelOne;
    }

    public void setLevelOne(CrmPrdCat levelOne) {
        this.levelOne = levelOne;
    }

    public CrmPrdCat getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(CrmPrdCat levelTwo) {
        this.levelTwo = levelTwo;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("【");
        sb.append("catId=").append(this.getCatId());
        if(this.getCrmPrdCat() != null){
            sb.append(",当前分类=").append("{").append("catId=").append(this.getCrmPrdCat().getCatId());
            sb.append(",catName=").append(this.getCrmPrdCat().getCatName()).append("}");
        }
        if(this.getLevelTwo() != null){
            sb.append(",二级分类=").append("{").append("catId=").append(this.getLevelTwo().getCatId());
            sb.append(",catName=").append(this.getLevelTwo().getCatName()).append("}");
        }
        if(this.getLevelOne() != null){
            sb.append(",一级分类=").append("{").append("catId=").append(this.getLevelOne().getCatId());
            sb.append(",catName=").append(this.getLevelOne().getCatName()).append("}");
        }
        sb.append("】");
        return sb.toString();
    }
}
