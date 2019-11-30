package com.zlead.entity.dto;

import java.util.*;

public class AgentBillDTO {

    /**
     * 客户ids
     */
    private List<Integer> agentCustIds;

    /**
     * 客户级别
     */
    private Integer levelId;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * userIds
     * @return
     */
    private Set<Integer> userIds;

    public List<Integer> getAgentCustIds() {
        return agentCustIds;
    }

    public void setAgentCustIds(List<Integer> agentCustIds) {
        this.agentCustIds = agentCustIds;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Set<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }
}
