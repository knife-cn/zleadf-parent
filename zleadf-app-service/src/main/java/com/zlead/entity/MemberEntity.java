package com.zlead.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 *
 * @author fqf
 * @date 2018-07-23 14:34:20
 */
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    private Long id;
    /**
     * 会员ID
     */
    private String memberId;
    /**
     * 上级用户会员ID
     */
    private String parentId;

    /**
     * 字段名称: 商铺分享者的id .
     * 字段定义: t_member.shop_id
     *
     * @ET
     */
    private Long shopId;

    /**
     * 字段名称: 职员所属店铺的ID .
     * 字段定义: t_member.own_shopid
     *
     * @ET
     */
    private Long ownShopid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 微信等第三方登录openID
     */
    private String openId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别(0未知，1男，2女)
     */
    private Integer gender;
    /**
     * 生日
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
    @JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date birthday;
    /**
     * 省ID
     */
    private Long provinceId;
    /**
     * 市ID
     */
    private Long cityId;
    /**
     *区/县ID
     */
    private Long regionId;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 是否绑定手机(0-否，1-是)
     */
    private Integer ifPhone;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 实名状态(0未实名，1实名审核中，2实名审核通过)
     */
    private Integer ifRealName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 银行名称
     */
    private String bankname;
    /**
     * 银行代号
     */
    private String bankcode;
    /**
     * 开户行地址
     */
    private String openaddr;
    /**
     * 银行卡号
     */
    private String bankcard;
    /**
     * 账户金额
     */
    private BigDecimal account;
    /**
     * 账户积分
     */
    private Long point;
    /**
     * 登录凭证
     */
    private String userToken;

    /**
     * 微信授权信息
     */
    private String wxOpenId;
    private String wxNickName;
    private String wxUnionId;
    /**
     * 个人二维码图片
     */
    private String qrcode;
    /**
     * 付款码
     */
    private String payQrcode;
    /**
     * 用户星级
     */
    private Integer starLevel;
    /**
     * 用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
     */
    private Integer memberType;
    /**
     * VIP会员类型：0:普通会员 1:VIP
     */
    private Integer isVip;
    /**
     * 是否员工
     */
    private Integer isStaff;
    /**
     * 是否销售人员
     */
    private Integer isSalesmen;
    /**
     * 如果是销售人员，所属代理商的id
     */
    private Long agentMemberId;
    /**
     *
     */
    private String systemId;
    /**
     * 是否禁用
     */
    private Integer isDisable;
    /**
     * 是否删除(0-否，1-是)
     */
    private Integer isDelete;
    /**
     * 0 未激活 1 激活
     */
    private Integer status;
    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 代理商id
     */
    private Long agentId;

    // 赋值用 新密码
    private String npassword;

    // 赋值用 新密码
    private String cpassword;

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：会员ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取：会员ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置：上级用户会员ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级用户会员ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method:getShopId
     * t_member.shop_id
     *
     * @return the value of t_member.shop_id
     * @ET
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method:setShopId
     * t_member.shop_id
     *
     * @param shopId the value for t_member.shop_id
     * @ET
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method:getOwnShopid
     * t_member.own_shopid
     *
     * @return the value of t_member.own_shopid
     * @ET
     */
    public Long getOwnShopid() {
        return ownShopid;
    }

    /**
     * This method:setOwnShopid
     * t_member.own_shopid
     *
     * @param ownShopid the value for t_member.own_shopid
     * @ET
     */
    public void setOwnShopid(Long ownShopid) {
        this.ownShopid = ownShopid;
    }

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：微信等第三方登录openID
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取：微信等第三方登录openID
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置：昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取：昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置：性别(0未知，1男，2女)
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取：性别(0未知，1男，2女)
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置：省ID
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取：省ID
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置：市ID
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取：市ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 设置：头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取：头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置：是否绑定手机(0-否，1-是)
     */
    public void setIfPhone(Integer ifPhone) {
        this.ifPhone = ifPhone;
    }

    /**
     * 获取：是否绑定手机(0-否，1-是)
     */
    public Integer getIfPhone() {
        return ifPhone;
    }

    /**
     * 设置：手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：实名状态(0未实名，1实名审核中，2实名审核通过)
     */
    public void setIfRealName(Integer ifRealName) {
        this.ifRealName = ifRealName;
    }

    /**
     * 获取：实名状态(0未实名，1实名审核中，2实名审核通过)
     */
    public Integer getIfRealName() {
        return ifRealName;
    }

    /**
     * 设置：真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取：真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置：身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取：身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置：银行名称
     */
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    /**
     * 获取：银行名称
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * 设置：银行代号
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * 获取：银行代号
     */
    public String getBankcode() {
        return bankcode;
    }

    /**
     * 设置：开户行地址
     */
    public void setOpenaddr(String openaddr) {
        this.openaddr = openaddr;
    }

    /**
     * 获取：开户行地址
     */
    public String getOpenaddr() {
        return openaddr;
    }

    /**
     * 设置：银行卡号
     */
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    /**
     * 获取：银行卡号
     */
    public String getBankcard() {
        return bankcard;
    }

    /**
     * 设置：账户金额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * 获取：账户金额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * 设置：账户积分
     */
    public void setPoint(Long point) {
        this.point = point;
    }

    /**
     * 获取：账户积分
     */
    public Long getPoint() {
        return point;
    }

    /**
     * 设置：登录凭证
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * 获取：登录凭证
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置：个人二维码图片
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * 获取：个人二维码图片
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * 设置：付款码
     */
    public void setPayQrcode(String payQrcode) {
        this.payQrcode = payQrcode;
    }

    /**
     * 获取：付款码
     */
    public String getPayQrcode() {
        return payQrcode;
    }

    /**
     * 设置：用户星级
     */
    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }

    /**
     * 获取：用户星级
     */
    public Integer getStarLevel() {
        return starLevel;
    }

    /**
     * 设置：用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
     */
    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    /**
     * 获取：用户类型 0.个人会员 1.厂家供应商 2.平台自营店 3.代理商   4品牌旗舰店 5品牌自营店 6一般店铺  7个人店铺)
     */
    public Integer getMemberType() {
        return memberType;
    }

    /**
     * 设置：VIP会员类型：0:普通会员 1:VIP
     */
    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    /**
     * 获取：VIP会员类型：0:普通会员 1:VIP
     */
    public Integer getIsVip() {
        return isVip;
    }

    /**
     * 设置：是否员工
     */
    public void setIsStaff(Integer isStaff) {
        this.isStaff = isStaff;
    }

    /**
     * 获取：是否员工
     */
    public Integer getIsStaff() {
        return isStaff;
    }

    /**
     * 设置：是否销售人员
     */
    public void setIsSalesmen(Integer isSalesmen) {
        this.isSalesmen = isSalesmen;
    }

    /**
     * 获取：是否销售人员
     */
    public Integer getIsSalesmen() {
        return isSalesmen;
    }

    /**
     * 设置：如果是销售人员，所属代理商的id
     */
    public void setAgentMemberId(Long agentMemberId) {
        this.agentMemberId = agentMemberId;
    }

    /**
     * 获取：如果是销售人员，所属代理商的id
     */
    public Long getAgentMemberId() {
        return agentMemberId;
    }

    /**
     * 设置：
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * 获取：
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * 设置：是否禁用
     */
    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    /**
     * 获取：是否禁用
     */
    public Integer getIsDisable() {
        return isDisable;
    }

    /**
     * 设置：是否删除(0-否，1-是)
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取：是否删除(0-否，1-是)
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置：0 未激活 1 激活
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：0 未激活 1 激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：注册时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxNickName() {
        return wxNickName;
    }

    public void setWxNickName(String wxNickName) {
        this.wxNickName = wxNickName;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public MemberEntity(){

    }

    public MemberEntity(Long id, String memberId, String parentId, Long shopId, Long ownShopid, String username, String password, String openId, String nickName, Integer gender, Date birthday, Long provinceId, Long cityId, Long regionId, String address, String headImg, Integer ifPhone, String phone, Integer ifRealName, String realName, String idCard, String bankname, String bankcode, String openaddr, String bankcard, BigDecimal account, Long point, String userToken, String wxOpenId, String wxNickName, String wxUnionId, String qrcode, String payQrcode, Integer starLevel, Integer memberType, Integer isVip, Integer isStaff, Integer isSalesmen, Long agentMemberId, String systemId, Integer isDisable, Integer isDelete, Integer status, Date createTime, Long agentId, String npassword, String cpassword) {
        this.id = id;
        this.memberId = memberId;
        this.parentId = parentId;
        this.shopId = shopId;
        this.ownShopid = ownShopid;
        this.username = username;
        this.password = password;
        this.openId = openId;
        this.nickName = nickName;
        this.gender = gender;
        this.birthday = birthday;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.regionId = regionId;
        this.address = address;
        this.headImg = headImg;
        this.ifPhone = ifPhone;
        this.phone = phone;
        this.ifRealName = ifRealName;
        this.realName = realName;
        this.idCard = idCard;
        this.bankname = bankname;
        this.bankcode = bankcode;
        this.openaddr = openaddr;
        this.bankcard = bankcard;
        this.account = account;
        this.point = point;
        this.userToken = userToken;
        this.wxOpenId = wxOpenId;
        this.wxNickName = wxNickName;
        this.wxUnionId = wxUnionId;
        this.qrcode = qrcode;
        this.payQrcode = payQrcode;
        this.starLevel = starLevel;
        this.memberType = memberType;
        this.isVip = isVip;
        this.isStaff = isStaff;
        this.isSalesmen = isSalesmen;
        this.agentMemberId = agentMemberId;
        this.systemId = systemId;
        this.isDisable = isDisable;
        this.isDelete = isDelete;
        this.status = status;
        this.createTime = createTime;
        this.agentId = agentId;
        this.npassword = npassword;
        this.cpassword = cpassword;
    }
}
