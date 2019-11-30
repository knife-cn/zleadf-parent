package com.zlead.entity.vo;

import java.util.Date;

public class SysMsgListVO {

    /**
     * 消息id
     */
    private Integer msgId;

    /**
     * 消息内容
     */
    private String content = "";

    /**
     * 消息创建时间
     */
    private Date createTime;

    /**
     * 编号
     */
    private String sn = "";

    /**
     * 订单id snType=1时不为空
     */
    private Long orderId;

    /**
     * 编号类型 1.订单 2.关联工厂
     */
    private Integer snType= 0;

    /**
     * 消息标题
     */
    private String title = "";

    /**
     * 操作人
     */
    private String operaterName = "";

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getSnType() {
        return snType;
    }

    public void setSnType(Integer snType) {
        this.snType = snType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperaterName() {
        return operaterName;
    }

    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
