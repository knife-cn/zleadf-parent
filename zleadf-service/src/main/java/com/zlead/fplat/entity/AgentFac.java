package com.zlead.fplat.entity;

import java.util.Date;
import java.util.List;

public class AgentFac {
    /**
     * 字段名称: 主键 .
     * 字段定义: t_agent_fac.id
     *
     * @ET
     */
    private Integer id;

    /**
     * 字段名称: 代理商 .
     * 字段定义: t_agent_fac.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 申请人 .
     * 字段定义: t_agent_fac.member_id
     *
     * @ET
     */
    private String memberId;

    /**
     * 字段名称: 申请时间 .
     * 字段定义: t_agent_fac.apply_date
     *
     * @ET
     */
    private Date applyDate;

    /**
     * 字段名称: 代理商状态（1:已审核 0:待审核 2:拒绝） .
     * 字段定义: t_agent_fac.status
     *
     * @ET
     */
    private String status;

    /**
     * 字段名称: 代理商级别id .
     * 字段定义: t_agent_fac.level_id
     *
     * @ET
     */
    private Integer levelId;

    /**
     * 字段名称: 代理品牌备用 .
     * 字段定义: t_agent_fac.agent_brand
     *
     * @ET
     */
    private String agentBrand;

    /**
     * 字段名称: 代理商折扣类型 代理商折扣类型(1:经销价;2:批发价;3:零售价) .
     * 字段定义: t_agent_fac.agent_discount_type
     *
     * @ET
     */
    private String agentDiscountType;

    /**
     * 字段名称: 代理商折扣 .
     * 字段定义: t_agent_fac.agent_discount
     *
     * @ET
     */
    private Double agentDiscount;

    /**
     * 字段名称: 代理商结算方式 代理商结算方式（多个id） .
     * 字段定义: t_agent_fac.agent_payments
     *
     * @ET
     */
    private String agentPayments;

    /**
     * 字段名称: 1 有返点，0：无返点 .
     * 字段定义: t_agent_fac.agent_return
     *
     * @ET
     */
    private String agentReturn;

    /**
     * 字段名称: 代理商返点值 .
     * 字段定义: t_agent_fac.agent_returnvalue
     *
     * @ET
     */
    private Double agentReturnvalue;

    /**
     * 字段名称: 合同开始时间 .
     * 字段定义: t_agent_fac.agent_fmdate
     *
     * @ET
     */
    private Date agentFmdate;

    /**
     * 字段名称: 合同结束时间 .
     * 字段定义: t_agent_fac.agent_todate
     *
     * @ET
     */
    private Date agentTodate;

    /**
     * 字段名称: 代理商合同 .
     * 字段定义: t_agent_fac.agent_contract
     *
     * @ET
     */
    private String agentContract;

    /**
     * 字段名称: 最后一次下单日期 .
     * 字段定义: t_agent_fac.order_date
     *
     * @ET
     */
    private Date orderDate;

    /**
     * 字段名称: 业务员 .
     * 字段定义: t_agent_fac.user_id
     *
     * @ET
     */
    private Integer userId;

    /**
     * 字段名称: 工厂关联码 .
     * 字段定义: t_agent_fac.vcode
     *
     * @ET
     */
    private String vcode;

    /**
     * 字段名称: 合作状态 合作状态(1:正常，0:结束) .
     * 字段定义: t_agent_fac.coop_state
     *
     * @ET
     */
    private String coopState;

    /**
     * 字段名称: 代理商任务额 .
     * 字段定义: t_agent_fac.task_amt
     *
     * @ET
     */
    private Double taskAmt;

    /**
     * 字段名称: 工厂id .
     * 字段定义: t_agent_fac.factory_id
     *
     * @ET
     */
    private Integer factoryId;

    /**
     * 字段名称: 机构 .
     * 字段定义: t_agent_fac.ogr_id
     *
     * @ET
     */
    private Integer ogrId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: t_agent_fac.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 创建时间 .
     * 字段定义: t_agent_fac.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改人 .
     * 字段定义: t_agent_fac.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: t_agent_fac.modify_time
     *
     * @ET
     */
    private Date modifyTime;
    List<Agentband> agentbands;
    /**
     * 工厂名字
     */
    private String agentName;

    /**
     * 工厂ID
     */
    private Integer sysId;

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getId
     * t_agent_fac.id
     *
     * @return the value of t_agent_fac.id
     * @ET
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method:setId
     * t_agent_fac.id
     *
     * @param id the value for t_agent_fac.id
     * @ET
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method:getAgentId
     * t_agent_fac.agent_id
     *
     * @return the value of t_agent_fac.agent_id
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     * t_agent_fac.agent_id
     *
     * @param agentId the value for t_agent_fac.agent_id
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getMemberId
     * t_agent_fac.member_id
     *
     * @return the value of t_agent_fac.member_id
     * @ET
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * This method:setMemberId
     * t_agent_fac.member_id
     *
     * @param memberId the value for t_agent_fac.member_id
     * @ET
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * This method:getApplyDate
     * t_agent_fac.apply_date
     *
     * @return the value of t_agent_fac.apply_date
     * @ET
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * This method:setApplyDate
     * t_agent_fac.apply_date
     *
     * @param applyDate the value for t_agent_fac.apply_date
     * @ET
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * This method:getStatus
     * t_agent_fac.status
     *
     * @return the value of t_agent_fac.status
     * @ET
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method:setStatus
     * t_agent_fac.status
     *
     * @param status the value for t_agent_fac.status
     * @ET
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method:getLevelId
     * t_agent_fac.level_id
     *
     * @return the value of t_agent_fac.level_id
     * @ET
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * This method:setLevelId
     * t_agent_fac.level_id
     *
     * @param levelId the value for t_agent_fac.level_id
     * @ET
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * This method:getAgentBrand
     * t_agent_fac.agent_brand
     *
     * @return the value of t_agent_fac.agent_brand
     * @ET
     */
    public String getAgentBrand() {
        return agentBrand;
    }

    /**
     * This method:setAgentBrand
     * t_agent_fac.agent_brand
     *
     * @param agentBrand the value for t_agent_fac.agent_brand
     * @ET
     */
    public void setAgentBrand(String agentBrand) {
        this.agentBrand = agentBrand == null ? null : agentBrand.trim();
    }

    /**
     * This method:getAgentDiscountType
     * t_agent_fac.agent_discount_type
     *
     * @return the value of t_agent_fac.agent_discount_type
     * @ET
     */
    public String getAgentDiscountType() {
        return agentDiscountType;
    }

    /**
     * This method:setAgentDiscountType
     * t_agent_fac.agent_discount_type
     *
     * @param agentDiscountType the value for t_agent_fac.agent_discount_type
     * @ET
     */
    public void setAgentDiscountType(String agentDiscountType) {
        this.agentDiscountType = agentDiscountType == null ? null : agentDiscountType.trim();
    }

    /**
     * This method:getAgentDiscount
     * t_agent_fac.agent_discount
     *
     * @return the value of t_agent_fac.agent_discount
     * @ET
     */
    public Double getAgentDiscount() {
        return agentDiscount;
    }

    /**
     * This method:setAgentDiscount
     * t_agent_fac.agent_discount
     *
     * @param agentDiscount the value for t_agent_fac.agent_discount
     * @ET
     */
    public void setAgentDiscount(Double agentDiscount) {
        this.agentDiscount = agentDiscount;
    }

    /**
     * This method:getAgentPayments
     * t_agent_fac.agent_payments
     *
     * @return the value of t_agent_fac.agent_payments
     * @ET
     */
    public String getAgentPayments() {
        return agentPayments;
    }

    /**
     * This method:setAgentPayments
     * t_agent_fac.agent_payments
     *
     * @param agentPayments the value for t_agent_fac.agent_payments
     * @ET
     */
    public void setAgentPayments(String agentPayments) {
        this.agentPayments = agentPayments == null ? null : agentPayments.trim();
    }

    /**
     * This method:getAgentReturn
     * t_agent_fac.agent_return
     *
     * @return the value of t_agent_fac.agent_return
     * @ET
     */
    public String getAgentReturn() {
        return agentReturn;
    }

    /**
     * This method:setAgentReturn
     * t_agent_fac.agent_return
     *
     * @param agentReturn the value for t_agent_fac.agent_return
     * @ET
     */
    public void setAgentReturn(String agentReturn) {
        this.agentReturn = agentReturn == null ? null : agentReturn.trim();
    }

    /**
     * This method:getAgentReturnvalue
     * t_agent_fac.agent_returnvalue
     *
     * @return the value of t_agent_fac.agent_returnvalue
     * @ET
     */
    public Double getAgentReturnvalue() {
        return agentReturnvalue;
    }

    /**
     * This method:setAgentReturnvalue
     * t_agent_fac.agent_returnvalue
     *
     * @param agentReturnvalue the value for t_agent_fac.agent_returnvalue
     * @ET
     */
    public void setAgentReturnvalue(Double agentReturnvalue) {
        this.agentReturnvalue = agentReturnvalue;
    }

    /**
     * This method:getAgentFmdate
     * t_agent_fac.agent_fmdate
     *
     * @return the value of t_agent_fac.agent_fmdate
     * @ET
     */
    public Date getAgentFmdate() {
        return agentFmdate;
    }

    /**
     * This method:setAgentFmdate
     * t_agent_fac.agent_fmdate
     *
     * @param agentFmdate the value for t_agent_fac.agent_fmdate
     * @ET
     */
    public void setAgentFmdate(Date agentFmdate) {
        this.agentFmdate = agentFmdate;
    }

    /**
     * This method:getAgentTodate
     * t_agent_fac.agent_todate
     *
     * @return the value of t_agent_fac.agent_todate
     * @ET
     */
    public Date getAgentTodate() {
        return agentTodate;
    }

    /**
     * This method:setAgentTodate
     * t_agent_fac.agent_todate
     *
     * @param agentTodate the value for t_agent_fac.agent_todate
     * @ET
     */
    public void setAgentTodate(Date agentTodate) {
        this.agentTodate = agentTodate;
    }

    /**
     * This method:getAgentContract
     * t_agent_fac.agent_contract
     *
     * @return the value of t_agent_fac.agent_contract
     * @ET
     */
    public String getAgentContract() {
        return agentContract;
    }

    /**
     * This method:setAgentContract
     * t_agent_fac.agent_contract
     *
     * @param agentContract the value for t_agent_fac.agent_contract
     * @ET
     */
    public void setAgentContract(String agentContract) {
        this.agentContract = agentContract == null ? null : agentContract.trim();
    }

    /**
     * This method:getOrderDate
     * t_agent_fac.order_date
     *
     * @return the value of t_agent_fac.order_date
     * @ET
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * This method:setOrderDate
     * t_agent_fac.order_date
     *
     * @param orderDate the value for t_agent_fac.order_date
     * @ET
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * This method:getUserId
     * t_agent_fac.user_id
     *
     * @return the value of t_agent_fac.user_id
     * @ET
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method:setUserId
     * t_agent_fac.user_id
     *
     * @param userId the value for t_agent_fac.user_id
     * @ET
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method:getVcode
     * t_agent_fac.vcode
     *
     * @return the value of t_agent_fac.vcode
     * @ET
     */
    public String getVcode() {
        return vcode;
    }

    /**
     * This method:setVcode
     * t_agent_fac.vcode
     *
     * @param vcode the value for t_agent_fac.vcode
     * @ET
     */
    public void setVcode(String vcode) {
        this.vcode = vcode == null ? null : vcode.trim();
    }

    /**
     * This method:getCoopState
     * t_agent_fac.coop_state
     *
     * @return the value of t_agent_fac.coop_state
     * @ET
     */
    public String getCoopState() {
        return coopState;
    }

    /**
     * This method:setCoopState
     * t_agent_fac.coop_state
     *
     * @param coopState the value for t_agent_fac.coop_state
     * @ET
     */
    public void setCoopState(String coopState) {
        this.coopState = coopState == null ? null : coopState.trim();
    }

    /**
     * This method:getTaskAmt
     * t_agent_fac.task_amt
     *
     * @return the value of t_agent_fac.task_amt
     * @ET
     */
    public Double getTaskAmt() {
        return taskAmt;
    }

    /**
     * This method:setTaskAmt
     * t_agent_fac.task_amt
     *
     * @param taskAmt the value for t_agent_fac.task_amt
     * @ET
     */
    public void setTaskAmt(Double taskAmt) {
        this.taskAmt = taskAmt;
    }

    /**
     * This method:getFactoryId
     * t_agent_fac.factory_id
     *
     * @return the value of t_agent_fac.factory_id
     * @ET
     */
    public Integer getFactoryId() {
        return factoryId;
    }

    /**
     * This method:setFactoryId
     * t_agent_fac.factory_id
     *
     * @param factoryId the value for t_agent_fac.factory_id
     * @ET
     */
    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * This method:getOgrId
     * t_agent_fac.ogr_id
     *
     * @return the value of t_agent_fac.ogr_id
     * @ET
     */
    public Integer getOgrId() {
        return ogrId;
    }

    /**
     * This method:setOgrId
     * t_agent_fac.ogr_id
     *
     * @param ogrId the value for t_agent_fac.ogr_id
     * @ET
     */
    public void setOgrId(Integer ogrId) {
        this.ogrId = ogrId;
    }

    /**
     * This method:getCreator
     * t_agent_fac.creator
     *
     * @return the value of t_agent_fac.creator
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     * t_agent_fac.creator
     *
     * @param creator the value for t_agent_fac.creator
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getCreateTime
     * t_agent_fac.create_time
     *
     * @return the value of t_agent_fac.create_time
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     * t_agent_fac.create_time
     *
     * @param createTime the value for t_agent_fac.create_time
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifier
     * t_agent_fac.modifier
     *
     * @return the value of t_agent_fac.modifier
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     * t_agent_fac.modifier
     *
     * @param modifier the value for t_agent_fac.modifier
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getModifyTime
     * t_agent_fac.modify_time
     *
     * @return the value of t_agent_fac.modify_time
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     * t_agent_fac.modify_time
     *
     * @param modifyTime the value for t_agent_fac.modify_time
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<Agentband> getAgentbands() {
        return agentbands;
    }

    public void setAgentbands(List<Agentband> agentbands) {
        this.agentbands = agentbands;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}