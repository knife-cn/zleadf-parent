package com.zlead.entity.dto;

public class AclModuUserDto {
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 功能模块Code
     */
    private String moduCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getModuCode() {
        return moduCode;
    }

    public void setModuCode(String moduCode) {
        this.moduCode = moduCode;
    }
}
