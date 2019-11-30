package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单节点状态明细
 * @author mengyouming
 */
public class OperatorLog implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8796296479316087525L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 操作人id(后台管理人员/或用户)
     */
    private Integer memberId;

    /**
     * 操作人姓名
     */
    private String userName;

    /**
     * 标题(用作module下级分类)
     */
    private String title;

    /**
     * 内容
     */
    private String remark;

    /**
     * 日志种类 0 系统日志 1 操作日志
     */
    private Integer category;

    /**
     * 业务模块 order:订单 agent:代理申请
     */
    private String module;

    /**
     * 业务单号
     */
    private String sn;

    /**
     * 链接
     */
    private String linkUrl;

    /**
     * 操作状态 200，成功，其他失败
     */
    private Integer operatorStatus;

    /**
     * 参数
     */
    private String operatorParams;

    /**
     * 系统编号 1 电商 2 工厂后台
     */
    private Integer systemId;

    /**
     * 创建/操作时间
     */
    private Date createDate;

    /**
     * ip(服务器地址还是请求地址不明)
     */
    private String addIp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getOperatorStatus() {
        return operatorStatus;
    }

    public void setOperatorStatus(Integer operatorStatus) {
        this.operatorStatus = operatorStatus;
    }

    public String getOperatorParams() {
        return operatorParams;
    }

    public void setOperatorParams(String operatorParams) {
        this.operatorParams = operatorParams;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

}
