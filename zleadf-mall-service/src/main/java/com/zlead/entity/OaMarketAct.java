package com.zlead.entity;

import java.util.Date;

public class OaMarketAct {
    private Integer id;

    private Integer actId;

    private String contCode;

    private String contName;

    private Integer contType;

    private Date effDate;

    private Date expDate;

    private String terminal;

    private String contTitle;

    private String actPic;

    private String contUrl;

    private Date pushTime;

    private Integer pushUser;

    private String contState;

    private Integer shopId;

    private Integer creator;

    private Integer modifier;

    private Date createTime;

    private Date modifyTime;

    private String staticCont;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getContCode() {
        return contCode;
    }

    public void setContCode(String contCode) {
        this.contCode = contCode == null ? null : contCode.trim();
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName == null ? null : contName.trim();
    }

    public Integer getContType() {
        return contType;
    }

    public void setContType(Integer contType) {
        this.contType = contType;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getContTitle() {
        return contTitle;
    }

    public void setContTitle(String contTitle) {
        this.contTitle = contTitle == null ? null : contTitle.trim();
    }

    public String getActPic() {
        return actPic;
    }

    public void setActPic(String actPic) {
        this.actPic = actPic == null ? null : actPic.trim();
    }

    public String getContUrl() {
        return contUrl;
    }

    public void setContUrl(String contUrl) {
        this.contUrl = contUrl == null ? null : contUrl.trim();
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPushUser() {
        return pushUser;
    }

    public void setPushUser(Integer pushUser) {
        this.pushUser = pushUser;
    }

    public String getContState() {
        return contState;
    }

    public void setContState(String contState) {
        this.contState = contState == null ? null : contState.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public String getStaticCont() {
        return staticCont;
    }

    public void setStaticCont(String staticCont) {
        this.staticCont = staticCont == null ? null : staticCont.trim();
    }
}