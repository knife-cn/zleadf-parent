package com.zlead.entity.vo;

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
    private String createTime;

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
     * 代理商Id
     */
    private Integer agentId;
    /**
     * 店铺Id
     */
    private Long shopId;
    /**
     * 操作人
     */
    private String operaterName = "";
    /**会员id **/
    private String memberId;
    /**来源**/
    private String msgType;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
