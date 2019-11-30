package com.zlead.fplat.entity;

import java.util.Date;

/**
 * 分类
 */
public class Prodcat {
    /**
     * 字段名称: id .
     * 字段定义: crm_prd_cat.cat_id
     *
     * @ET
     */
    private Integer catId;

    /**
     * 字段名称: 分类代码 .
     * 字段定义: crm_prd_cat.cat_no
     *
     * @ET
     */
    private String catNo;

    /**
     * 字段名称: 分类名称 .
     * 字段定义: crm_prd_cat.cat_name
     *
     * @ET
     */
    private String catName;

    /**
     * 字段名称: 上级分类 .
     * 字段定义: crm_prd_cat.pcat_id
     *
     * @ET
     */
    private Integer pcatId;

    /**
     * 字段名称: 拼音 .
     * 字段定义: crm_prd_cat.pinyin
     *
     * @ET
     */
    private String pinyin;

    /**
     * 字段名称: 简拼 .
     * 字段定义: crm_prd_cat.pinyin_sh
     *
     * @ET
     */
    private String pinyinSh;

    /**
     * 字段名称: 图片 .
     * 字段定义: crm_prd_cat.pic_path
     *
     * @ET
     */
    private String picPath;

    /**
     * 字段名称: 状态 .
     * 字段定义: crm_prd_cat.cat_state
     *
     * @ET
     */
    private String catState;

    /**
     * 字段名称: 预定义分类 .
     * 字段定义: crm_prd_cat.bcat_id
     *
     * @ET
     */
    private Integer bcatId;

    /**
     * 字段名称: 备注 .
     * 字段定义: crm_prd_cat.cat_desc
     *
     * @ET
     */
    private String catDesc;

    /**
     * 字段名称: 工厂/店铺 .
     * 字段定义: crm_prd_cat.shop_id
     *
     * @ET
     */
    private Integer shopId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: crm_prd_cat.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: crm_prd_cat.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: crm_prd_cat.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: crm_prd_cat.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * isFac 是否是工厂自定义 1:平台 2：工厂
     */
    private Integer isFac;

    /**
     * This method:getCatId
     * crm_prd_cat.cat_id
     *
     * @return the value of crm_prd_cat.cat_id
     * @ET
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * This method:setCatId
     * crm_prd_cat.cat_id
     *
     * @param catId the value for crm_prd_cat.cat_id
     * @ET
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * This method:getCatNo
     * crm_prd_cat.cat_no
     *
     * @return the value of crm_prd_cat.cat_no
     * @ET
     */
    public String getCatNo() {
        return catNo;
    }

    /**
     * This method:setCatNo
     * crm_prd_cat.cat_no
     *
     * @param catNo the value for crm_prd_cat.cat_no
     * @ET
     */
    public void setCatNo(String catNo) {
        this.catNo = catNo == null ? null : catNo.trim();
    }

    /**
     * This method:getCatName
     * crm_prd_cat.cat_name
     *
     * @return the value of crm_prd_cat.cat_name
     * @ET
     */
    public String getCatName() {
        return catName;
    }

    /**
     * This method:setCatName
     * crm_prd_cat.cat_name
     *
     * @param catName the value for crm_prd_cat.cat_name
     * @ET
     */
    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    /**
     * This method:getPcatId
     * crm_prd_cat.pcat_id
     *
     * @return the value of crm_prd_cat.pcat_id
     * @ET
     */
    public Integer getPcatId() {
        return pcatId;
    }

    /**
     * This method:setPcatId
     * crm_prd_cat.pcat_id
     *
     * @param pcatId the value for crm_prd_cat.pcat_id
     * @ET
     */
    public void setPcatId(Integer pcatId) {
        this.pcatId = pcatId;
    }

    /**
     * This method:getPinyin
     * crm_prd_cat.pinyin
     *
     * @return the value of crm_prd_cat.pinyin
     * @ET
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * This method:setPinyin
     * crm_prd_cat.pinyin
     *
     * @param pinyin the value for crm_prd_cat.pinyin
     * @ET
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * This method:getPinyinSh
     * crm_prd_cat.pinyin_sh
     *
     * @return the value of crm_prd_cat.pinyin_sh
     * @ET
     */
    public String getPinyinSh() {
        return pinyinSh;
    }

    /**
     * This method:setPinyinSh
     * crm_prd_cat.pinyin_sh
     *
     * @param pinyinSh the value for crm_prd_cat.pinyin_sh
     * @ET
     */
    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh == null ? null : pinyinSh.trim();
    }

    /**
     * This method:getPicPath
     * crm_prd_cat.pic_path
     *
     * @return the value of crm_prd_cat.pic_path
     * @ET
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method:setPicPath
     * crm_prd_cat.pic_path
     *
     * @param picPath the value for crm_prd_cat.pic_path
     * @ET
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method:getCatState
     * crm_prd_cat.cat_state
     *
     * @return the value of crm_prd_cat.cat_state
     * @ET
     */
    public String getCatState() {
        return catState;
    }

    /**
     * This method:setCatState
     * crm_prd_cat.cat_state
     *
     * @param catState the value for crm_prd_cat.cat_state
     * @ET
     */
    public void setCatState(String catState) {
        this.catState = catState == null ? null : catState.trim();
    }

    /**
     * This method:getBcatId
     * crm_prd_cat.bcat_id
     *
     * @return the value of crm_prd_cat.bcat_id
     * @ET
     */
    public Integer getBcatId() {
        return bcatId;
    }

    /**
     * This method:setBcatId
     * crm_prd_cat.bcat_id
     *
     * @param bcatId the value for crm_prd_cat.bcat_id
     * @ET
     */
    public void setBcatId(Integer bcatId) {
        this.bcatId = bcatId;
    }

    /**
     * This method:getCatDesc
     * crm_prd_cat.cat_desc
     *
     * @return the value of crm_prd_cat.cat_desc
     * @ET
     */
    public String getCatDesc() {
        return catDesc;
    }

    /**
     * This method:setCatDesc
     * crm_prd_cat.cat_desc
     *
     * @param catDesc the value for crm_prd_cat.cat_desc
     * @ET
     */
    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc == null ? null : catDesc.trim();
    }

    /**
     * This method:getShopId
     * crm_prd_cat.shop_id
     *
     * @return the value of crm_prd_cat.shop_id
     * @ET
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method:setShopId
     * crm_prd_cat.shop_id
     *
     * @param shopId the value for crm_prd_cat.shop_id
     * @ET
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getCreator
     * crm_prd_cat.creator
     *
     * @return the value of crm_prd_cat.creator
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     * crm_prd_cat.creator
     *
     * @param creator the value for crm_prd_cat.creator
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * crm_prd_cat.modifier
     *
     * @return the value of crm_prd_cat.modifier
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     * crm_prd_cat.modifier
     *
     * @param modifier the value for crm_prd_cat.modifier
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * crm_prd_cat.create_time
     *
     * @return the value of crm_prd_cat.create_time
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     * crm_prd_cat.create_time
     *
     * @param createTime the value for crm_prd_cat.create_time
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * crm_prd_cat.modify_time
     *
     * @return the value of crm_prd_cat.modify_time
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     * crm_prd_cat.modify_time
     *
     * @param modifyTime the value for crm_prd_cat.modify_time
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}