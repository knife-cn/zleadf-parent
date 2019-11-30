package com.zlead.fplat.entity;

import java.util.Date;

public class CrmPrdCat {
    /**
     * id
     */
    private Integer catId;
    /**
     *DB不存在此字段，用户查询分类的时候进行catId拼接
     */
    private String catIds;

    /**
     * 分类代码
     */
    private String catNo;

    /**
     * 分类名称
     */
    private String catName;

    /**
     * 上级分类
     */
    private Integer pcatId;


    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 简拼
     */
    private String pinyinSh;

    /**
     * 图片
     */
    private String picPath;

    /**
     * 状态
     */
    private String catState;

    /**
     * 预定义分类
     */
    private Integer bcatId;

    /**
     * 备注
     */
    private String catDesc;

    /**
     * 工厂/店铺
     */
    private Integer shopId;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 修改人
     */
    private Integer modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 是否是工厂自定义 1:平台 2：工厂
     */
    private Integer isFac;

    /**
     * 数据库不存在此字段
     * @return
     */
    private Integer prodType;

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatNo() {
        return catNo;
    }

    public void setCatNo(String catNo) {
        this.catNo = catNo;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getPcatId() {
        return pcatId;
    }

    public void setPcatId(Integer pcatId) {
        this.pcatId = pcatId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyinSh() {
        return pinyinSh;
    }

    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getCatState() {
        return catState;
    }

    public void setCatState(String catState) {
        this.catState = catState;
    }

    public Integer getBcatId() {
        return bcatId;
    }

    public void setBcatId(Integer bcatId) {
        this.bcatId = bcatId;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getIsFac() {
        return isFac;
    }

    public void setIsFac(Integer isFac) {
        this.isFac = isFac;
    }
    
    public String toString(){
    	return this.catName;
    }

    public String getCatIds() {
        return catIds;
    }

    public void setCatIds(String catIds) {
        this.catIds = catIds;
    }
}