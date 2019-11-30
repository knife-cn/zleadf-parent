package com.zlead.fplat.entity;

import java.util.Date;

/**
 * ϵ��
 */
public class CrmPrdList {
    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.list_id
     *
     * @ET
     */
    private Integer listId;
    /**
     * 在查询系列的时候对系列名进行分组操作，然后不同的listId进行拼接
     * DB无此字段
     * @ET
     */
    private String listIds;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.list_no
     *
     * @ET
     */
    private String listNo;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.list_name
     *
     * @ET
     */
    private String listName;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.band_id
     *
     * @ET
     */
    private Integer bandId;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.cat_ids
     *
     * @ET
     */
    private String catIds;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.cat_names
     *
     * @ET
     */
    private String catNames;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.pinyin
     *
     * @ET
     */
    private String pinyin;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.pinyin_sh
     *
     * @ET
     */
    private String pinyinSh;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.pic_path
     *
     * @ET
     */
    private String picPath;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.list_state
     *
     * @ET
     */
    private String listState;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.list_desc
     *
     * @ET
     */
    private String listDesc;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.sys_id
     *
     * @ET
     */
    private Integer shopId;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * �ֶ�����:  .
     * �ֶζ���: crm_prd_list.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getListId
     * crm_prd_list.list_id
     *
     * @return the value of crm_prd_list.list_id
     *
     * @ET
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * This method:setListId
     *  crm_prd_list.list_id
     *
     * @param listId the value for crm_prd_list.list_id
     *
     * @ET
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * This method:getListNo
     * crm_prd_list.list_no
     *
     * @return the value of crm_prd_list.list_no
     *
     * @ET
     */
    public String getListNo() {
        return listNo;
    }

    /**
     * This method:setListNo
     *  crm_prd_list.list_no
     *
     * @param listNo the value for crm_prd_list.list_no
     *
     * @ET
     */
    public void setListNo(String listNo) {
        this.listNo = listNo == null ? null : listNo.trim();
    }

    /**
     * This method:getListName
     * crm_prd_list.list_name
     *
     * @return the value of crm_prd_list.list_name
     *
     * @ET
     */
    public String getListName() {
        return listName;
    }

    /**
     * This method:setListName
     *  crm_prd_list.list_name
     *
     * @param listName the value for crm_prd_list.list_name
     *
     * @ET
     */
    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    /**
     * This method:getBandId
     * crm_prd_list.band_id
     *
     * @return the value of crm_prd_list.band_id
     *
     * @ET
     */
    public Integer getBandId() {
        return bandId;
    }

    /**
     * This method:setBandId
     *  crm_prd_list.band_id
     *
     * @param bandId the value for crm_prd_list.band_id
     *
     * @ET
     */
    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    /**
     * This method:getCatIds
     * crm_prd_list.cat_ids
     *
     * @return the value of crm_prd_list.cat_ids
     *
     * @ET
     */
    public String getCatIds() {
        return catIds;
    }

    /**
     * This method:setCatIds
     *  crm_prd_list.cat_ids
     *
     * @param catIds the value for crm_prd_list.cat_ids
     *
     * @ET
     */
    public void setCatIds(String catIds) {
        this.catIds = catIds == null ? null : catIds.trim();
    }

    /**
     * This method:getCatNames
     * crm_prd_list.cat_names
     *
     * @return the value of crm_prd_list.cat_names
     *
     * @ET
     */
    public String getCatNames() {
        return catNames;
    }

    /**
     * This method:setCatNames
     *  crm_prd_list.cat_names
     *
     * @param catNames the value for crm_prd_list.cat_names
     *
     * @ET
     */
    public void setCatNames(String catNames) {
        this.catNames = catNames == null ? null : catNames.trim();
    }

    /**
     * This method:getPinyin
     * crm_prd_list.pinyin
     *
     * @return the value of crm_prd_list.pinyin
     *
     * @ET
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * This method:setPinyin
     *  crm_prd_list.pinyin
     *
     * @param pinyin the value for crm_prd_list.pinyin
     *
     * @ET
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * This method:getPinyinSh
     * crm_prd_list.pinyin_sh
     *
     * @return the value of crm_prd_list.pinyin_sh
     *
     * @ET
     */
    public String getPinyinSh() {
        return pinyinSh;
    }

    /**
     * This method:setPinyinSh
     *  crm_prd_list.pinyin_sh
     *
     * @param pinyinSh the value for crm_prd_list.pinyin_sh
     *
     * @ET
     */
    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh == null ? null : pinyinSh.trim();
    }

    /**
     * This method:getPicPath
     * crm_prd_list.pic_path
     *
     * @return the value of crm_prd_list.pic_path
     *
     * @ET
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method:setPicPath
     *  crm_prd_list.pic_path
     *
     * @param picPath the value for crm_prd_list.pic_path
     *
     * @ET
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method:getListState
     * crm_prd_list.list_state
     *
     * @return the value of crm_prd_list.list_state
     *
     * @ET
     */
    public String getListState() {
        return listState;
    }

    /**
     * This method:setListState
     *  crm_prd_list.list_state
     *
     * @param listState the value for crm_prd_list.list_state
     *
     * @ET
     */
    public void setListState(String listState) {
        this.listState = listState == null ? null : listState.trim();
    }

    /**
     * This method:getListDesc
     * crm_prd_list.list_desc
     *
     * @return the value of crm_prd_list.list_desc
     *
     * @ET
     */
    public String getListDesc() {
        return listDesc;
    }

    /**
     * This method:setListDesc
     *  crm_prd_list.list_desc
     *
     * @param listDesc the value for crm_prd_list.list_desc
     *
     * @ET
     */
    public void setListDesc(String listDesc) {
        this.listDesc = listDesc == null ? null : listDesc.trim();
    }

    /**
     * This method:getSysId
     * crm_prd_list.sys_id
     *
     * @return the value of crm_prd_list.sys_id
     *
     * @ET
     */
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getCreator
     * crm_prd_list.creator
     *
     * @return the value of crm_prd_list.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  crm_prd_list.creator
     *
     * @param creator the value for crm_prd_list.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * crm_prd_list.modifier
     *
     * @return the value of crm_prd_list.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  crm_prd_list.modifier
     *
     * @param modifier the value for crm_prd_list.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * crm_prd_list.create_time
     *
     * @return the value of crm_prd_list.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  crm_prd_list.create_time
     *
     * @param createTime the value for crm_prd_list.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * crm_prd_list.modify_time
     *
     * @return the value of crm_prd_list.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  crm_prd_list.modify_time
     *
     * @param modifyTime the value for crm_prd_list.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getListIds() {
        return listIds;
    }

    public void setListIds(String listIds) {
        this.listIds = listIds;
    }
}