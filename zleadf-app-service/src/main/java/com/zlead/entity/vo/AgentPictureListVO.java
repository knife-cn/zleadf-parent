package com.zlead.entity.vo;

public class AgentPictureListVO {

    /**
     * 图片id
     */
    private Integer picId;

    /**
     * 图片url地址
     */
    private String picUrl = "";

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
