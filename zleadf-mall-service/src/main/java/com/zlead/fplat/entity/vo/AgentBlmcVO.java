package com.zlead.fplat.entity.vo;

import java.io.Serializable;

/**
 * 代理商所关联的品牌、系列、型号、分类
 */
public class AgentBlmcVO implements Serializable {
    private static final long serialVersionUID = 1345018117777938258L;
    /**
     * 工厂id
     */
    private Long factoryId;
    /**
     * 品牌id
     */
    private Long bandId;
    /**
     * 品牌拼接
     */
    private String bandIds;
    /**
     * 品牌名称
     */
    private String bandName;
    /**
     * 品牌图片地址
     */
    private String bandPicPath;
    /**
     * 系列id
     */
    private Long listId;
    /**
     * 系列名称
     */
    private String listName;
    /**
     * 系列图片地址
     */
    private String listPicPath;
    /**
     * 型号id
     */
    private Long modelId;
    /**
     * 型号名称
     */
    private String modelName;
    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */
    private String catName;
    /**
     * 分类备注名称
     */
    private String catDesc;
    /**
     * 分类图片地址
     */
    private String catPicPath;
    /**
     * 分类上级id
     */
    private Long pcatId;
    /**
     * 分类所属平台 1：平台 2：工厂自定义
     */
    private Integer isFac;

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getBandId() {
        return bandId;
    }

    public void setBandId(Long bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getPcatId() {
        return pcatId;
    }

    public void setPcatId(Long pcatId) {
        this.pcatId = pcatId;
    }

    public Integer getIsFac() {
        return isFac;
    }

    public void setIsFac(Integer isFac) {
        this.isFac = isFac;
    }

    public String getBandPicPath() {
        return bandPicPath;
    }

    public void setBandPicPath(String bandPicPath) {
        this.bandPicPath = bandPicPath;
    }

    public String getListPicPath() {
        return listPicPath;
    }

    public void setListPicPath(String listPicPath) {
        this.listPicPath = listPicPath;
    }

    public String getCatPicPath() {
        return catPicPath;
    }

    public void setCatPicPath(String catPicPath) {
        this.catPicPath = catPicPath;
    }

    public String getCatDesc() {
    	if(catDesc==null){
    		if(this.catName!=null)
    			catDesc=this.catName;
    		else
    			catDesc=" ";
    	}
    	else if(catDesc.length()<1)
    		{
    		if(this.catName!=null)
    			catDesc=this.catName;
    		else
    			catDesc=" ";
    		}
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getBandIds() {
        return bandIds;
    }

    public void setBandIds(String bandIds) {
        this.bandIds = bandIds;
    }
}
