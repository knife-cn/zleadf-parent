package com.zlead.fplat.entity;

public class AgentbandList {
    /**
     * 字段名称: id .
     * 字段定义: oa_agent_band_lists.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 代理商id .
     * 字段定义: oa_agent_band_lists.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 工厂id .
     * 字段定义: oa_agent_band_lists.fact_id
     *
     * @ET
     */
    private Integer factId;

    /**
     * 字段名称: 品牌id .
     * 字段定义: oa_agent_band_lists.band_id
     *
     * @ET
     */
    private Integer bandId;

    /**
     * 字段名称: 品牌名称 .
     * 字段定义: oa_agent_band_lists.band_name
     *
     * @ET
     */
    private String bandName;

    /**
     * 字段名称:  .
     * 字段定义: oa_agent_band_lists.list_id
     *
     * @ET
     */
    private Integer listId;

    /**
     * This method:getId
     * oa_agent_band_lists.id
     *
     * @return the value of oa_agent_band_lists.id
     *
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     *  oa_agent_band_lists.id
     *
     * @param id the value for oa_agent_band_lists.id
     *
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getAgentId
     * oa_agent_band_lists.agent_id
     *
     * @return the value of oa_agent_band_lists.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  oa_agent_band_lists.agent_id
     *
     * @param agentId the value for oa_agent_band_lists.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getFactId
     * oa_agent_band_lists.fact_id
     *
     * @return the value of oa_agent_band_lists.fact_id
     *
     * @ET
     */
    public Integer getFactId() {
        return factId;
    }

    /**
     * This method:setFactId
     *  oa_agent_band_lists.fact_id
     *
     * @param factId the value for oa_agent_band_lists.fact_id
     *
     * @ET
     */
    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    /**
     * This method:getBandId
     * oa_agent_band_lists.band_id
     *
     * @return the value of oa_agent_band_lists.band_id
     *
     * @ET
     */
    public Integer getBandId() {
        return bandId;
    }

    /**
     * This method:setBandId
     *  oa_agent_band_lists.band_id
     *
     * @param bandId the value for oa_agent_band_lists.band_id
     *
     * @ET
     */
    public void setBandId(Integer bandId) {
        this.bandId = bandId;
    }

    /**
     * This method:getBandName
     * oa_agent_band_lists.band_name
     *
     * @return the value of oa_agent_band_lists.band_name
     *
     * @ET
     */
    public String getBandName() {
        return bandName;
    }

    /**
     * This method:setBandName
     *  oa_agent_band_lists.band_name
     *
     * @param bandName the value for oa_agent_band_lists.band_name
     *
     * @ET
     */
    public void setBandName(String bandName) {
        this.bandName = bandName == null ? null : bandName.trim();
    }

    /**
     * This method:getListId
     * oa_agent_band_lists.list_id
     *
     * @return the value of oa_agent_band_lists.list_id
     *
     * @ET
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * This method:setListId
     *  oa_agent_band_lists.list_id
     *
     * @param listId the value for oa_agent_band_lists.list_id
     *
     * @ET
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }
}