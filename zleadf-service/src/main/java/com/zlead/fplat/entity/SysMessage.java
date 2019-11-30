package com.zlead.fplat.entity;

import java.util.Date;

/**
 * @ProjectName zlead_parent
 * @Auther:GuoFeng
 * @Date: 2019/1/23.
 * @Desoription TODO
 */
public class SysMessage {

    private int id;

    /**内容 **/
    private String content;

    /**代理商id **/
    private int agentId;

    /**类型 **/
    private int msgtype;

    /**状态 **/
    private int status;

    /** **/
    private Date createTime;

    /** **/
    private Date updateTime;

    /**会员id **/
    private int memberId;

    /**已读时间 **/
    private Date readTime;

    /**
     * 商铺id-SHOP_ID
     */
    private Integer sysId;

    /**
     * 消息标题 -TITLE
     */
    private String title;

    /**
     * 编号类型  1.订单编号 2.关联工厂id -SN_TYPE
     */
    private Integer snType;

    /**
     * 编号 -SN
     */
    private String sn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSnType() {
        return snType;
    }

    public void setSnType(Integer snType) {
        this.snType = snType;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
