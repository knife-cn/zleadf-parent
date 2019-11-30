package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.List;

public class OaSaleCustExample {
    /**
     * orderByClausenull .
     *   oa_sale_cust
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_sale_cust
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_sale_cust
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaSaleCustExample
     *   oa_sale_cust
     *
     * @ET
     */
    public OaSaleCustExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_sale_cust
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_sale_cust
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_sale_cust
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_sale_cust
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_sale_cust
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_sale_cust
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_sale_cust
     *
     * @ET
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method:createCriteria
     *   oa_sale_cust
     *
     * @ET
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method:createCriteriaInternal
     *   oa_sale_cust
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_sale_cust
     *
     * @ET
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_sale_cust
     *
     * @ET
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSaleIdIsNull() {
            addCriterion("sale_id is null");
            return (Criteria) this;
        }

        public Criteria andSaleIdIsNotNull() {
            addCriterion("sale_id is not null");
            return (Criteria) this;
        }

        public Criteria andSaleIdEqualTo(Integer value) {
            addCriterion("sale_id =", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotEqualTo(Integer value) {
            addCriterion("sale_id <>", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdGreaterThan(Integer value) {
            addCriterion("sale_id >", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_id >=", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdLessThan(Integer value) {
            addCriterion("sale_id <", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdLessThanOrEqualTo(Integer value) {
            addCriterion("sale_id <=", value, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdIn(List<Integer> values) {
            addCriterion("sale_id in", values, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotIn(List<Integer> values) {
            addCriterion("sale_id not in", values, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdBetween(Integer value1, Integer value2) {
            addCriterion("sale_id between", value1, value2, "saleId");
            return (Criteria) this;
        }

        public Criteria andSaleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_id not between", value1, value2, "saleId");
            return (Criteria) this;
        }

        public Criteria andContIdIsNull() {
            addCriterion("cont_id is null");
            return (Criteria) this;
        }

        public Criteria andContIdIsNotNull() {
            addCriterion("cont_id is not null");
            return (Criteria) this;
        }

        public Criteria andContIdEqualTo(Integer value) {
            addCriterion("cont_id =", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdNotEqualTo(Integer value) {
            addCriterion("cont_id <>", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdGreaterThan(Integer value) {
            addCriterion("cont_id >", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cont_id >=", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdLessThan(Integer value) {
            addCriterion("cont_id <", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdLessThanOrEqualTo(Integer value) {
            addCriterion("cont_id <=", value, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdIn(List<Integer> values) {
            addCriterion("cont_id in", values, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdNotIn(List<Integer> values) {
            addCriterion("cont_id not in", values, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdBetween(Integer value1, Integer value2) {
            addCriterion("cont_id between", value1, value2, "contId");
            return (Criteria) this;
        }

        public Criteria andContIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cont_id not between", value1, value2, "contId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Integer value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Integer value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Integer value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Integer value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Integer> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Integer> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andSaleSortIsNull() {
            addCriterion("sale_sort is null");
            return (Criteria) this;
        }

        public Criteria andSaleSortIsNotNull() {
            addCriterion("sale_sort is not null");
            return (Criteria) this;
        }

        public Criteria andSaleSortEqualTo(Integer value) {
            addCriterion("sale_sort =", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortNotEqualTo(Integer value) {
            addCriterion("sale_sort <>", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortGreaterThan(Integer value) {
            addCriterion("sale_sort >", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_sort >=", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortLessThan(Integer value) {
            addCriterion("sale_sort <", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortLessThanOrEqualTo(Integer value) {
            addCriterion("sale_sort <=", value, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortIn(List<Integer> values) {
            addCriterion("sale_sort in", values, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortNotIn(List<Integer> values) {
            addCriterion("sale_sort not in", values, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortBetween(Integer value1, Integer value2) {
            addCriterion("sale_sort between", value1, value2, "saleSort");
            return (Criteria) this;
        }

        public Criteria andSaleSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_sort not between", value1, value2, "saleSort");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_sale_cust
     *
     * @ET do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_sale_cust
     *
     * @ET
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}