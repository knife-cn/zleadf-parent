package com.zlead.fplat.entity;

import java.util.Date;

public class OaDeptMas {
    /**
     * 字段名称: 部门 .
     * 字段定义: oa_dept_mas.dept_id
     *
     * @ET
     */
    private Integer deptId;

    /**
     * 字段名称: 部门编号 .
     * 字段定义: oa_dept_mas.dept_no
     *
     * @ET
     */
    private String deptNo;

    /**
     * 字段名称: 部门名称 .
     * 字段定义: oa_dept_mas.dept_name
     *
     * @ET
     */
    private String deptName;

    /**
     * 字段名称: 上级部门 .
     * 字段定义: oa_dept_mas.pdept_id
     *
     * @ET
     */
    private Integer pdeptId;

    /**
     * 字段名称: 拼音 .
     * 字段定义: oa_dept_mas.pinyin
     *
     * @ET
     */
    private String pinyin;

    /**
     * 字段名称: 首字母 .
     * 字段定义: oa_dept_mas.pinyin_sh
     *
     * @ET
     */
    private String pinyinSh;

    /**
     * 字段名称: 部门类型 .
     * 字段定义: oa_dept_mas.dept_type
     *
     * @ET
     */
    private String deptType;

    /**
     * 字段名称: 状态 .
     * 字段定义: oa_dept_mas.dept_state
     *
     * @ET
     */
    private String deptState;

    /**
     * 字段名称: 备注 .
     * 字段定义: oa_dept_mas.dept_desc
     *
     * @ET
     */
    private String deptDesc;

    /**
     * 字段名称: 机构 .
     * 字段定义: oa_dept_mas.org_id
     *
     * @ET
     */
    private Integer orgId;

    /**
     * 字段名称: 系统 .
     * 字段定义: oa_dept_mas.sys_id
     *
     * @ET
     */
    private Integer sysId;

    /**
     * 字段名称: 创建人 .
     * 字段定义: oa_dept_mas.creator
     *
     * @ET
     */
    private Integer creator;

    /**
     * 字段名称: 修改人 .
     * 字段定义: oa_dept_mas.modifier
     *
     * @ET
     */
    private Integer modifier;

    /**
     * 字段名称: 创建日期 .
     * 字段定义: oa_dept_mas.create_time
     *
     * @ET
     */
    private Date createTime;

    /**
     * 字段名称: 修改时间 .
     * 字段定义: oa_dept_mas.modify_time
     *
     * @ET
     */
    private Date modifyTime;

    /**
     * This method:getDeptId
     * oa_dept_mas.dept_id
     *
     * @return the value of oa_dept_mas.dept_id
     *
     * @ET
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * This method:setDeptId
     *  oa_dept_mas.dept_id
     *
     * @param deptId the value for oa_dept_mas.dept_id
     *
     * @ET
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * This method:getDeptNo
     * oa_dept_mas.dept_no
     *
     * @return the value of oa_dept_mas.dept_no
     *
     * @ET
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * This method:setDeptNo
     *  oa_dept_mas.dept_no
     *
     * @param deptNo the value for oa_dept_mas.dept_no
     *
     * @ET
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    /**
     * This method:getDeptName
     * oa_dept_mas.dept_name
     *
     * @return the value of oa_dept_mas.dept_name
     *
     * @ET
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * This method:setDeptName
     *  oa_dept_mas.dept_name
     *
     * @param deptName the value for oa_dept_mas.dept_name
     *
     * @ET
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * This method:getPdeptId
     * oa_dept_mas.pdept_id
     *
     * @return the value of oa_dept_mas.pdept_id
     *
     * @ET
     */
    public Integer getPdeptId() {
        return pdeptId;
    }

    /**
     * This method:setPdeptId
     *  oa_dept_mas.pdept_id
     *
     * @param pdeptId the value for oa_dept_mas.pdept_id
     *
     * @ET
     */
    public void setPdeptId(Integer pdeptId) {
        this.pdeptId = pdeptId;
    }

    /**
     * This method:getPinyin
     * oa_dept_mas.pinyin
     *
     * @return the value of oa_dept_mas.pinyin
     *
     * @ET
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * This method:setPinyin
     *  oa_dept_mas.pinyin
     *
     * @param pinyin the value for oa_dept_mas.pinyin
     *
     * @ET
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * This method:getPinyinSh
     * oa_dept_mas.pinyin_sh
     *
     * @return the value of oa_dept_mas.pinyin_sh
     *
     * @ET
     */
    public String getPinyinSh() {
        return pinyinSh;
    }

    /**
     * This method:setPinyinSh
     *  oa_dept_mas.pinyin_sh
     *
     * @param pinyinSh the value for oa_dept_mas.pinyin_sh
     *
     * @ET
     */
    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh == null ? null : pinyinSh.trim();
    }

    /**
     * This method:getDeptType
     * oa_dept_mas.dept_type
     *
     * @return the value of oa_dept_mas.dept_type
     *
     * @ET
     */
    public String getDeptType() {
        return deptType;
    }

    /**
     * This method:setDeptType
     *  oa_dept_mas.dept_type
     *
     * @param deptType the value for oa_dept_mas.dept_type
     *
     * @ET
     */
    public void setDeptType(String deptType) {
        this.deptType = deptType == null ? null : deptType.trim();
    }

    /**
     * This method:getDeptState
     * oa_dept_mas.dept_state
     *
     * @return the value of oa_dept_mas.dept_state
     *
     * @ET
     */
    public String getDeptState() {
        return deptState;
    }

    /**
     * This method:setDeptState
     *  oa_dept_mas.dept_state
     *
     * @param deptState the value for oa_dept_mas.dept_state
     *
     * @ET
     */
    public void setDeptState(String deptState) {
        this.deptState = deptState == null ? null : deptState.trim();
    }

    /**
     * This method:getDeptDesc
     * oa_dept_mas.dept_desc
     *
     * @return the value of oa_dept_mas.dept_desc
     *
     * @ET
     */
    public String getDeptDesc() {
        return deptDesc;
    }

    /**
     * This method:setDeptDesc
     *  oa_dept_mas.dept_desc
     *
     * @param deptDesc the value for oa_dept_mas.dept_desc
     *
     * @ET
     */
    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc == null ? null : deptDesc.trim();
    }

    /**
     * This method:getOrgId
     * oa_dept_mas.org_id
     *
     * @return the value of oa_dept_mas.org_id
     *
     * @ET
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * This method:setOrgId
     *  oa_dept_mas.org_id
     *
     * @param orgId the value for oa_dept_mas.org_id
     *
     * @ET
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * This method:getSysId
     * oa_dept_mas.sys_id
     *
     * @return the value of oa_dept_mas.sys_id
     *
     * @ET
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * This method:setSysId
     *  oa_dept_mas.sys_id
     *
     * @param sysId the value for oa_dept_mas.sys_id
     *
     * @ET
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * This method:getCreator
     * oa_dept_mas.creator
     *
     * @return the value of oa_dept_mas.creator
     *
     * @ET
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * This method:setCreator
     *  oa_dept_mas.creator
     *
     * @param creator the value for oa_dept_mas.creator
     *
     * @ET
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * This method:getModifier
     * oa_dept_mas.modifier
     *
     * @return the value of oa_dept_mas.modifier
     *
     * @ET
     */
    public Integer getModifier() {
        return modifier;
    }

    /**
     * This method:setModifier
     *  oa_dept_mas.modifier
     *
     * @param modifier the value for oa_dept_mas.modifier
     *
     * @ET
     */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /**
     * This method:getCreateTime
     * oa_dept_mas.create_time
     *
     * @return the value of oa_dept_mas.create_time
     *
     * @ET
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method:setCreateTime
     *  oa_dept_mas.create_time
     *
     * @param createTime the value for oa_dept_mas.create_time
     *
     * @ET
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method:getModifyTime
     * oa_dept_mas.modify_time
     *
     * @return the value of oa_dept_mas.modify_time
     *
     * @ET
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method:setModifyTime
     *  oa_dept_mas.modify_time
     *
     * @param modifyTime the value for oa_dept_mas.modify_time
     *
     * @ET
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}