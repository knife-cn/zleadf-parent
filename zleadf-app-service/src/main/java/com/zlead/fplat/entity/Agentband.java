package com.zlead.fplat.entity;


import javax.xml.crypto.Data;

public class Agentband {
	/**
     * 字段名称: id .
     * 字段定义: oa_agent_band.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 代理商id .
     * 字段定义: oa_agent_band.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 工厂id .
     * 字段定义: oa_agent_band.fact_id
     *
     * @ET
     */
    private Integer factId;

    /**
     * 字段名称: 品牌id .
     * 字段定义: oa_agent_band.band_id
     *
     * @ET
     */
    private Integer bandId;
    /**
     * 字段名称: 品牌id .
     * 字段定义: oa_agent_band.band_id
     *
     * @ET
     */
    private String bandIds;

    /**
     * 字段名称: 品牌名称 .
     * 字段定义: oa_agent_band.band_name
     *
     * @ET
     */
    private String bandName;

    /**
     * 字段名称: 系列 .
     * 字段定义: oa_agent_band.list_name
     *
     * @ET
     */
    private String listName;

    /**
     * 字段名称:  .
     * 字段定义: oa_agent_band.list_ids
     *
     * @ET
     */
    private String listIds;

    private Data createTime;

    public Data getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Data createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getId
     * oa_agent_band.id
     *
     * @return the value of oa_agent_band.id
     *
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     *  oa_agent_band.id
     *
     * @param id the value for oa_agent_band.id
     *
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getAgentId
     * oa_agent_band.agent_id
     *
     * @return the value of oa_agent_band.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  oa_agent_band.agent_id
     *
     * @param agentId the value for oa_agent_band.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getFactId
     * oa_agent_band.fact_id
     *
     * @return the value of oa_agent_band.fact_id
     *
     * @ET
     */
    public Integer getFactId() {
        return factId;
    }

    /**
     * This method:setFactId
     *  oa_agent_band.fact_id
     *
     * @param factId the value for oa_agent_band.fact_id
     *
     * @ET
     */
    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    /**
     * This method:getBandId
     * oa_agent_band.band_id
     *
     * @return the value of oa_agent_band.band_id
     *
     * @ET
     */
    public Integer getBandId() {
        return bandId;
    }

    /**
     * This method:setBandId
     *  oa_agent_band.band_id
     *
     * @param bandId the value for oa_agent_band.band_id
     *
     * @ET
     */
    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    /**
     * This method:getBandName
     * oa_agent_band.band_name
     *
     * @return the value of oa_agent_band.band_name
     *
     * @ET
     */
    public String getBandName() {
        return bandName;
    }

    /**
     * This method:setBandName
     *  oa_agent_band.band_name
     *
     * @param bandName the value for oa_agent_band.band_name
     *
     * @ET
     */
    public void setBandName(String bandName) {
        this.bandName = bandName == null ? null : bandName.trim();
    }

    /**
     * This method:getListName
     * oa_agent_band.list_name
     *
     * @return the value of oa_agent_band.list_name
     *
     * @ET
     */
    public String getListName() {
        return listName;
    }

    /**
     * This method:setListName
     *  oa_agent_band.list_name
     *
     * @param listName the value for oa_agent_band.list_name
     *
     * @ET
     */
    public void setListName(String listName) {
        this.listName = listName == null ? null : listName.trim();
    }

    /**
     * This method:getListIds
     * oa_agent_band.list_ids
     *
     * @return the value of oa_agent_band.list_ids
     *
     * @ET
     */
    public String getListIds() {
        return listIds;
    }

    /**
     * This method:setListIds
     *  oa_agent_band.list_ids
     *
     * @param listIds the value for oa_agent_band.list_ids
     *
     * @ET
     */
    public void setListIds(String listIds) {
        this.listIds = listIds == null ? null : listIds.trim();
    }
    
    public String toString(){
        return "【品牌="+this.getBandName()+"，序列="+this.getListName()+"】";
    	//return this.getListName();
    }

    public String getBandIds() {
        return bandIds;
    }

    public void setBandIds(String bandIds) {
        this.bandIds = bandIds;
    }
}