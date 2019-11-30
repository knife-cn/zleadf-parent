package com.zlead.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户定义数据对象
 */
public class AclUserEntity implements Serializable{

    private static final long serialVersionUID = 1533996992653L;

    /**
     * 用户ID-USER_ID, not null
     */
    private Integer userId;
    /**
     * 登录名-LOGIN_NAME, not null
     */
    private String username;
    /**
     * 名称-USER_NAME, not null
     */
    private String userName;
    /**
     * 名称-USER_NAME, not null
     */
    private String roleName;
    /**
     * 名称-USER_NAME, not null
     */
    private String puserName;
    /**
     * 密码-USER_PWD
     */
    private String password;
    /**
     * 指纹-FINGER_PWD
     */
    private String fingerPwd;
    /**
     * 手势-GESTURE_PWD
     */
    private String gesturePwd;
    /**
     * 类型-USER_TYPE
     */
    private String userType;
    /**
     * 工号-USER_NO
     */
    private String userNo;
    /**
     * 邮箱-EMAIL
     */
    private String email;
    /**
     * 微信号-WE_CHAT
     */
    private String weChat;
    /**
     * 系统ID-OPEN_ID
     */
    private String openId;
    /**
     * 手机-MOBILE
     */
    private String mobile;
    /**
     * 公司-ORG_ID
     */
    private Integer orgId;
    /**
     * 公司代码-ORG_NO
     */
    private String orgNo;
    /**
     * 公司名称-ORG_NAME
     */
    private String orgName;
    /**
     * 部门-DEPT_ID
     */
    private Integer deptId;
    /**
     * 部门代码-DEPT_NO
     */
    private String deptNo;
    /**
     * 部门名称-DEPT_NAME
     */
    private String deptName;
    /**
     * 角色-ROLE_ID
     */
    private Integer roleId;
    /**
     * 语言-LANG_CODE
     */
    private String langCode;
    /**
     * 拼音-PINYIN
     */
    private String pinyin;
    /**
     * 简拼-PINYIN_SH
     */
    private String pinyinSh;
    /**
     * 员工ID-EMP_ID
     */
    private Integer empId;
    /**
     * 用户IP-USER_IP
     */
    private String userIp;
    /**
     * 证件类型-ID_TYPE
     */
    private String idType;
    /**
     * 证件号码-ID_NO
     */
    private String idNo;
    /**
     * 工作-WORK_CODE
     */
    private String workCode;
    /**
     * 查询-QUERY_MODE
     */
    private String queryMode;
    /**
     * 代理-AGENT_CD
     */
    private String agentCd;
    /**
     * 签名-SIGNATURE
     */
    private String signature;
    /**
     * 账户类型-ACCOUNT_TYPE
     */
    private String accountType;
    /**
     * 等级-USER_LEVEL
     */
    private String userLevel;
    /**
     * 超级用户-ADMIN_FLAG
     */
    private String adminFlag;
    /**
     * 性别-GENDER
     */
    private String gender;
    /**
     * 入职日期-JOININ_DATE
     */
    private Date joininDate;
    /**
     * 整数1-UINT1
     */
    private Integer uint1;
    /**
     * 整数2-UINT2
     */
    private Integer uint2;
    /**
     * 数值1-UNUM1
     */
    private Double unum1;
    /**
     * 数值2-UNUM2
     */
    private Double unum2;
    /**
     * 字符1-UCHAR1
     */
    private String uchar1;
    /**
     * 字符2-UCHAR2
     */
    private String uchar2;
    /**
     * 字符3-UCHAR3
     */
    private String uchar3;
    /**
     * 字符4-UCHAR4
     */
    private String uchar4;
    /**
     * 日期1-UDATE1
     */
    private Date udate1;
    /**
     * 状态-USER_STATE
     */
    private String userState;
    /**
     * 描述-USER_DESC
     */
    private String userDesc;
    /**
     * 系统-SYS_ID
     */
    private Integer sysId;
    /**
     * 创建人-CREATOR
     */
    private Integer creator;
    /**
     * 修改人-MODIFIER
     */
    private Integer modifier;
    /**
     * 创建时间-CREATE_TIME
     */
    private Date createTime;
    /**
     * 修改时间-MODIFY_TIME
     */
    private Date modifyTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPuserName() {
        return puserName;
    }

    public void setPuserName(String puserName) {
        this.puserName = puserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFingerPwd() {
        return fingerPwd;
    }

    public void setFingerPwd(String fingerPwd) {
        this.fingerPwd = fingerPwd;
    }

    public String getGesturePwd() {
        return gesturePwd;
    }

    public void setGesturePwd(String gesturePwd) {
        this.gesturePwd = gesturePwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyinSh() {
        return pinyinSh;
    }

    public void setPinyinSh(String pinyinSh) {
        this.pinyinSh = pinyinSh;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getQueryMode() {
        return queryMode;
    }

    public void setQueryMode(String queryMode) {
        this.queryMode = queryMode;
    }

    public String getAgentCd() {
        return agentCd;
    }

    public void setAgentCd(String agentCd) {
        this.agentCd = agentCd;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getJoininDate() {
        return joininDate;
    }

    public void setJoininDate(Date joininDate) {
        this.joininDate = joininDate;
    }

    public Integer getUint1() {
        return uint1;
    }

    public void setUint1(Integer uint1) {
        this.uint1 = uint1;
    }

    public Integer getUint2() {
        return uint2;
    }

    public void setUint2(Integer uint2) {
        this.uint2 = uint2;
    }

    public Double getUnum1() {
        return unum1;
    }

    public void setUnum1(Double unum1) {
        this.unum1 = unum1;
    }

    public Double getUnum2() {
        return unum2;
    }

    public void setUnum2(Double unum2) {
        this.unum2 = unum2;
    }

    public String getUchar1() {
        return uchar1;
    }

    public void setUchar1(String uchar1) {
        this.uchar1 = uchar1;
    }

    public String getUchar2() {
        return uchar2;
    }

    public void setUchar2(String uchar2) {
        this.uchar2 = uchar2;
    }

    public String getUchar3() {
        return uchar3;
    }

    public void setUchar3(String uchar3) {
        this.uchar3 = uchar3;
    }

    public String getUchar4() {
        return uchar4;
    }

    public void setUchar4(String uchar4) {
        this.uchar4 = uchar4;
    }

    public Date getUdate1() {
        return udate1;
    }

    public void setUdate1(Date udate1) {
        this.udate1 = udate1;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}

