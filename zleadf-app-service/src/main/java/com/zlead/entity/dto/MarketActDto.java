package com.zlead.entity.dto;

import com.zlead.common.PageResponse;
import com.zlead.util.page.Page;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName zleadf-parent
 * @Auther:GuoFeng
 * @Date: 2019/1/19.
 * @Desoription TODO
 */
public class MarketActDto {
    /**
     * 字段名称: 活动名称
     */
    private String contName;

    /**
     * 字段名称: 活动有效期 .
     */
    private String attrDate;
    /**
     * 字段名称: 活动标题 .
     */
    private String contTitle;

    /**
     * 字段名称: 静态内容 .
     */
    private String staticCont;

    /**
     * 活动图片
     */
    private String atrPic;

    private String actContent;

    private String contUrl;


    /*活动类型名称*/
    private String actTypeName;

    public String getActTypeName() {
        return actTypeName;
    }

    public void setActTypeName(String actTypeName) {
        this.actTypeName = actTypeName;
    }

    public String getAtrPic() {
        return atrPic;
    }

    public void setAtrPic(String atrPic) {
        this.atrPic = atrPic;
    }

    public String getActContent() {
        return actContent;
    }

    public void setActContent(String actContent) {
        this.actContent = actContent;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getAttrDate() {
        return attrDate;
    }

    public void setAttrDate(String attrDate) {
        this.attrDate = attrDate;
    }

    public String getContTitle() {
        return contTitle;
    }

    public void setContTitle(String contTitle) {
        this.contTitle = contTitle;
    }

    public String getStaticCont() {
        return staticCont;
    }

    public void setStaticCont(String staticCont) {
        this.staticCont = staticCont;
    }

    public String getContUrl() {
        return contUrl;
    }

    public void setContUrl(String contUrl) {
        this.contUrl = contUrl;
    }
}
