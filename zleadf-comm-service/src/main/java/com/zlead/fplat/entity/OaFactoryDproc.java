package com.zlead.fplat.entity;

import java.util.Date;

public class OaFactoryDproc {
    /**
     * 字段名称: 流程编号 .
     * 字段定义: oa_factory_dproc.dproc_id
     *
     * @ET
     */
    private Integer dprocId;

    /**
     * 字段名称: 工厂id .
     * 字段定义: oa_factory_dproc.fact_id
     *
     * @ET
     */
    private Integer factId;

    /**
     * 字段名称: 序号 .
     * 字段定义: oa_factory_dproc.sort_num
     *
     * @ET
     */
    private Integer sortNum;

    /**
     * 字段名称: 流程名 .
     * 字段定义: oa_factory_dproc.dproc_name
     *
     * @ET
     */
    private String dprocName;

    /**
     * 字段名称: 流程说明 .
     * 字段定义: oa_factory_dproc.dproc_desc
     *
     * @ET
     */
    private String dprocDesc;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: oa_factory_dproc.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: oa_factory_dproc.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getDprocId
     * oa_factory_dproc.dproc_id
     *
     * @return the value of oa_factory_dproc.dproc_id
     *
     * @ET
     */
    public Integer getDprocId() {
        return dprocId;
    }

    /**
     * This method:setDprocId
     *  oa_factory_dproc.dproc_id
     *
     * @param dprocId the value for oa_factory_dproc.dproc_id
     *
     * @ET
     */
    public void setDprocId(Integer dprocId) {
        this.dprocId = dprocId;
    }

    /**
     * This method:getFactId
     * oa_factory_dproc.fact_id
     *
     * @return the value of oa_factory_dproc.fact_id
     *
     * @ET
     */
    public Integer getFactId() {
        return factId;
    }

    /**
     * This method:setFactId
     *  oa_factory_dproc.fact_id
     *
     * @param factId the value for oa_factory_dproc.fact_id
     *
     * @ET
     */
    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    /**
     * This method:getSortNum
     * oa_factory_dproc.sort_num
     *
     * @return the value of oa_factory_dproc.sort_num
     *
     * @ET
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * This method:setSortNum
     *  oa_factory_dproc.sort_num
     *
     * @param sortNum the value for oa_factory_dproc.sort_num
     *
     * @ET
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * This method:getDprocName
     * oa_factory_dproc.dproc_name
     *
     * @return the value of oa_factory_dproc.dproc_name
     *
     * @ET
     */
    public String getDprocName() {
        return dprocName;
    }

    /**
     * This method:setDprocName
     *  oa_factory_dproc.dproc_name
     *
     * @param dprocName the value for oa_factory_dproc.dproc_name
     *
     * @ET
     */
    public void setDprocName(String dprocName) {
        this.dprocName = dprocName == null ? null : dprocName.trim();
    }

    /**
     * This method:getDprocDesc
     * oa_factory_dproc.dproc_desc
     *
     * @return the value of oa_factory_dproc.dproc_desc
     *
     * @ET
     */
    public String getDprocDesc() {
        return dprocDesc;
    }

    /**
     * This method:setDprocDesc
     *  oa_factory_dproc.dproc_desc
     *
     * @param dprocDesc the value for oa_factory_dproc.dproc_desc
     *
     * @ET
     */
    public void setDprocDesc(String dprocDesc) {
        this.dprocDesc = dprocDesc == null ? null : dprocDesc.trim();
    }

    /**
     * This method:getCreateTime
     * oa_factory_dproc.create_time
     *
     * @return the value of oa_factory_dproc.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  oa_factory_dproc.create_time
     *
     * @param createTime the value for oa_factory_dproc.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * oa_factory_dproc.modify_time
     *
     * @return the value of oa_factory_dproc.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  oa_factory_dproc.modify_time
     *
     * @param modifyTime the value for oa_factory_dproc.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}