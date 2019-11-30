package com.zlead.fplat.entity;

public class OaSaleCust {
    /**
     * 字段名称: 促销id .
     * 字段定义: oa_sale_cust.sale_id
     *
     * @ET
     */
    private Integer saleId;

    /**
     * 字段名称: 活动id .
     * 字段定义: oa_sale_cust.cont_id
     *
     * @ET
     */
    private Integer contId;

    /**
     * 字段名称: 代理商 .
     * 字段定义: oa_sale_cust.agent_id
     *
     * @ET
     */
    private Integer agentId;

    /**
     * 字段名称: 排序 .
     * 字段定义: oa_sale_cust.sale_sort
     *
     * @ET
     */
    private Integer saleSort;

    /**
     * This method:getSaleId
     * oa_sale_cust.sale_id
     *
     * @return the value of oa_sale_cust.sale_id
     *
     * @ET
     */
    public Integer getSaleId() {
        return saleId;
    }

    /**
     * This method:setSaleId
     *  oa_sale_cust.sale_id
     *
     * @param saleId the value for oa_sale_cust.sale_id
     *
     * @ET
     */
    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    /**
     * This method:getContId
     * oa_sale_cust.cont_id
     *
     * @return the value of oa_sale_cust.cont_id
     *
     * @ET
     */
    public Integer getContId() {
        return contId;
    }

    /**
     * This method:setContId
     *  oa_sale_cust.cont_id
     *
     * @param contId the value for oa_sale_cust.cont_id
     *
     * @ET
     */
    public void setContId(Integer contId) {
        this.contId = contId;
    }

    /**
     * This method:getAgentId
     * oa_sale_cust.agent_id
     *
     * @return the value of oa_sale_cust.agent_id
     *
     * @ET
     */
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * This method:setAgentId
     *  oa_sale_cust.agent_id
     *
     * @param agentId the value for oa_sale_cust.agent_id
     *
     * @ET
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * This method:getSaleSort
     * oa_sale_cust.sale_sort
     *
     * @return the value of oa_sale_cust.sale_sort
     *
     * @ET
     */
    public Integer getSaleSort() {
        return saleSort;
    }

    /**
     * This method:setSaleSort
     *  oa_sale_cust.sale_sort
     *
     * @param saleSort the value for oa_sale_cust.sale_sort
     *
     * @ET
     */
    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }
}