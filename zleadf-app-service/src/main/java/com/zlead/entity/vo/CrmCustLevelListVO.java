package com.zlead.entity.vo;

public class CrmCustLevelListVO {

    /**
     * 客户级别id
     */
    private Integer levelId;

    /**
     * 客户级别名称
     */
    private String agentLevel;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
    }
}
