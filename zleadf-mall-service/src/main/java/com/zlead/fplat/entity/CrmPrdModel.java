package com.zlead.fplat.entity;

import java.util.Date;

/**
 * 型号
 */
public class CrmPrdModel {
    /**
     * 字段名称: id .
     * 字段定义: crm_prd_model.model_id
     *
     * @ET
     */
    private Integer modelId;

    /**
     * 字段名称: 型号代码 .
     * 字段定义: crm_prd_model.model_no
     *
     * @ET
     */
    private String modelNo;

    /**
     * 字段名称: 型号名称 .
     * 字段定义: crm_prd_model.model_name
     *
     * @ET
     */
    private String modelName;

    /**
     * 字段名称: 所属品牌 .
     * 字段定义: crm_prd_model.band_id
     *
     * @ET
     */
    private Integer bandId;

    /**
     * 字段名称: 所属系列 .
     * 字段定义: crm_prd_model.list_id
     *
     * @ET
     */
    private Integer listId;

    /**
     * 字段名称: 拼音 .
     * 字段定义: crm_prd_model.pinyin
     *
     * @ET
     */
    private String pinyin;

    /**
     * 字段名称: 简拼 .
     * 字段定义: crm_prd_model.pinyin_sh
     *
     * @ET
     */
    private String pinyinSh;

    /**
     * 字段名称: 图片 .
     * 字段定义: crm_prd_model.pic_path
     *
     * @ET
     */
    private String picPath;

    /**
     * 字段名称: 状态 .
     * 字段定义: crm_prd_model.model_state
     *
     * @ET
     */
    private String modelState;

    /**
     * 字段名称: 备注 .
     * 字段定义: crm_prd_model.model_desc
     *
     * @ET
     */
    private String modelDesc;

    /**
     * 字段名称: 系统 .
     * 字段定义: crm_prd_model.sys_id
     *
     * @ET
     */
    private Integer shopId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: crm_prd_model.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: crm_prd_model.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: crm_prd_model.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: crm_prd_model.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    private String modelIds;

    /**
     * This method:getModelId
     * crm_prd_model.model_id
     *
     * @return the value of crm_prd_model.model_id
     *
     * @ET
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * This method:setModelId
     *  crm_prd_model.model_id
     *
     * @param modelId the value for crm_prd_model.model_id
     *
     * @ET
     */
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    /**
     * This method:getModelNo
     * crm_prd_model.model_no
     *
     * @return the value of crm_prd_model.model_no
     *
     * @ET
     */
    public String getModelNo() {
        return modelNo;
    }

    /**
     * This method:setModelNo
     *  crm_prd_model.model_no
     *
     * @param modelNo the value for crm_prd_model.model_no
     *
     * @ET
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo == null ? null : modelNo.trim();
    }

    /**
     * This method:getModelName
     * crm_prd_model.model_name
     *
     * @return the value of crm_prd_model.model_name
     *
     * @ET
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * This method:setModelName
     *  crm_prd_model.model_name
     *
     * @param modelName the value for crm_prd_model.model_name
     *
     * @ET
     */
    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    /**
     * This method:getBandId
     * crm_prd_model.band_id
     *
     * @return the value of crm_prd_model.band_id
     *
     * @ET
     */
    public Integer getBandId() {
        return bandId;
    }

    /**
     * This method:setBandId
     *  crm_prd_model.band_id
     *
     * @param bandId the value for crm_prd_model.band_id
     *
     * @ET
     */
    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    /**
     * This method:getListId
     * crm_prd_model.list_id
     *
     * @return the value of crm_prd_model.list_id
     *
     * @ET
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * This method:setListId
     *  crm_prd_model.list_id
     *
     * @param listId the value for crm_prd_model.list_id
     *
     * @ET
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * This method:getPinyin
     * crm_prd_model.pinyin
     *
     * @return the value of crm_prd_model.pinyin
     *
     * @ET
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * This method:setPinyin
     *  crm_prd_model.pinyin
     *
     * @param pinyin the value for crm_prd_model.pinyin
     *
     * @ET
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * This method:getPinyinSh
     * crm_prd_model.pinyin_sh
     *
     * @return the value of crm_prd_model.pinyin_sh
     *
     * @ET
     */
    public String getPinyinSh() {
        return pinyinSh;
    }

    /**
     * This method:setPinyinSh
     *  crm_prd_model.pinyin_sh
     *
     * @param pinyinSh the value for crm_prd_model.pinyin_sh
     *
     * @ET
     */
    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh == null ? null : pinyinSh.trim();
    }

    /**
     * This method:getPicPath
     * crm_prd_model.pic_path
     *
     * @return the value of crm_prd_model.pic_path
     *
     * @ET
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method:setPicPath
     *  crm_prd_model.pic_path
     *
     * @param picPath the value for crm_prd_model.pic_path
     *
     * @ET
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method:getModelState
     * crm_prd_model.model_state
     *
     * @return the value of crm_prd_model.model_state
     *
     * @ET
     */
    public String getModelState() {
        return modelState;
    }

    /**
     * This method:setModelState
     *  crm_prd_model.model_state
     *
     * @param modelState the value for crm_prd_model.model_state
     *
     * @ET
     */
    public void setModelState(String modelState) {
        this.modelState = modelState == null ? null : modelState.trim();
    }

    /**
     * This method:getModelDesc
     * crm_prd_model.model_desc
     *
     * @return the value of crm_prd_model.model_desc
     *
     * @ET
     */
    public String getModelDesc() {
        return modelDesc;
    }

    /**
     * This method:setModelDesc
     *  crm_prd_model.model_desc
     *
     * @param modelDesc the value for crm_prd_model.model_desc
     *
     * @ET
     */
    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc == null ? null : modelDesc.trim();
    }

    /**
     * This method:getSysId
     * crm_prd_model.sys_id
     *
     * @return the value of crm_prd_model.sys_id
     *
     * @ET
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method:setSysId
     *  crm_prd_model.sys_id
     *
     * @param shopId the value for crm_prd_model.sys_id
     *
     * @ET
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getCreator
     * crm_prd_model.creator
     *
     * @return the value of crm_prd_model.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  crm_prd_model.creator
     *
     * @param creator the value for crm_prd_model.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * crm_prd_model.modifier
     *
     * @return the value of crm_prd_model.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  crm_prd_model.modifier
     *
     * @param modifier the value for crm_prd_model.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * crm_prd_model.create_time
     *
     * @return the value of crm_prd_model.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  crm_prd_model.create_time
     *
     * @param createTime the value for crm_prd_model.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * crm_prd_model.modify_time
     *
     * @return the value of crm_prd_model.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  crm_prd_model.modify_time
     *
     * @param modifyTime the value for crm_prd_model.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModelIds() {
        return modelIds;
    }

    public void setModelIds(String modelIds) {
        this.modelIds = modelIds;
    }
}