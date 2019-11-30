package com.zlead.fplat.entity;

import java.util.Date;

public class Marketact {
    /**
     * id
     */
    private Integer id;

    /**
     * 活动id
     */
    private Integer actId;

    /**
     * 活动代码
     */
    private String contCode;

    /**
     * 活动名称
     */
    private String contName;

    /**
     * 活动类型
     */
    private Integer contType;

    /**活动图片 **/
    private String actPic;

    /**
     * 活动有效期
     */
    private Date effDate;

    /**
     * 活动有效期
     */
    private Date expDate;

    /**
     * 活动展示
     */
    private String terminal;

    /**
     * 活动标题
     */
    private String contTitle;

    /**
     * 地址
     */
    private String contUrl;

    /**
     * 发布时间
     */
    private Date pushTime;

    /**
     * 发布人
     */
    private Integer pushUser;

    /**
     * 状态
     */
    private String contState;

    /**
     * 商铺编号
     */
    private Integer shopId;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 修改人
     */
    private Integer modifier;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 静态内容
     */
    private String staticCont;


    /*活动类型名称*/
    private String actTypeName;

    public String getActTypeName() {
        return actTypeName;
    }

    public void setActTypeName(String actTypeName) {
        this.actTypeName = actTypeName;
    }



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
        this.contCode = contCode;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public Integer getContType() {
        return contType;
    }

    public String getActPic() {
        return actPic;
    }

    public void setActPic(String actPic) {
        this.actPic = actPic;
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
        this.terminal = terminal;
    }

    public String getContTitle() {
        return contTitle;
    }

    public void setContTitle(String contTitle) {
        this.contTitle = contTitle;
    }

    public String getContUrl() {
        return contUrl;
    }

    public void setContUrl(String contUrl) {
        this.contUrl = contUrl;
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
        this.contState = contState;
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
        this.staticCont = staticCont;
    }
}