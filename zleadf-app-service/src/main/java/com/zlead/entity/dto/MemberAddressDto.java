package com.zlead.entity.dto;

import java.io.Serializable;

public class MemberAddressDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 会员ID
     */
    private Long memberId;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否是默认地址(0-否，1-是)
     */
    private Integer isDefault;


    /**
     * 是否是工厂创建的地址  1：是 2：不是
     */
    private Integer isFact;

    /**
     * 工厂主键
     */
    private Integer factId;

    /**
     * 工厂名称
     */
    private String factName;

    /**
     * 省
     * @return
     */
    private String provinceName;
    /**
     * 市
     * @return
     */
    private String cityName;

    /**
     * 县
     * @return
     */
    private String regionName;

    public String getFactName() {
        return factName;
    }

    public void setFactName(String factName) {
        this.factName = factName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getFactId() {
        return factId;
    }

    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    public Integer getIsFact() {
        return isFact;
    }

    public void setIsFact(Integer isFact) {
        this.isFact = isFact;
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
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    /**
     * 获取：会员ID
     */
    public Long getMemberId() {
        return memberId;
    }
    /**
     * 设置：会员姓名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    /**
     * 获取：会员姓名
     */
    public String getMemberName() {
        return memberName;
    }
    /**
     * 设置：联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * 获取：联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 获取：详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：是否是默认地址(0-否，1-是)
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
    /**
     * 获取：是否是默认地址(0-否，1-是)
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    @Override
    public String toString() {
        return "MemberAddressDto{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                ", isFact=" + isFact +
                ", factId=" + factId +
                ", factName='" + factName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                '}';
    }
}
