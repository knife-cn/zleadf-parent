package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaAgentMasExample {
    /**
     * orderByClausenull .
     *   oa_agent_mas
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_agent_mas
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_agent_mas
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaAgentMasExample
     *   oa_agent_mas
     *
     * @ET
     */
    public OaAgentMasExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_agent_mas
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_agent_mas
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_agent_mas
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_agent_mas
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_agent_mas
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_agent_mas
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_agent_mas
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
     *   oa_agent_mas
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
     *   oa_agent_mas
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_agent_mas
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
     *  oa_agent_mas
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

        public Criteria andAgentNoIsNull() {
            addCriterion("agent_no is null");
            return (Criteria) this;
        }

        public Criteria andAgentNoIsNotNull() {
            addCriterion("agent_no is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNoEqualTo(String value) {
            addCriterion("agent_no =", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotEqualTo(String value) {
            addCriterion("agent_no <>", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThan(String value) {
            addCriterion("agent_no >", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThanOrEqualTo(String value) {
            addCriterion("agent_no >=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThan(String value) {
            addCriterion("agent_no <", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThanOrEqualTo(String value) {
            addCriterion("agent_no <=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLike(String value) {
            addCriterion("agent_no like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotLike(String value) {
            addCriterion("agent_no not like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoIn(List<String> values) {
            addCriterion("agent_no in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotIn(List<String> values) {
            addCriterion("agent_no not in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoBetween(String value1, String value2) {
            addCriterion("agent_no between", value1, value2, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotBetween(String value1, String value2) {
            addCriterion("agent_no not between", value1, value2, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("agent_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("agent_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("agent_name =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("agent_name <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("agent_name >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_name >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("agent_name <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("agent_name <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("agent_name like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("agent_name not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("agent_name in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("agent_name not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("agent_name between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("agent_name not between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentStateIsNull() {
            addCriterion("agent_state is null");
            return (Criteria) this;
        }

        public Criteria andAgentStateIsNotNull() {
            addCriterion("agent_state is not null");
            return (Criteria) this;
        }

        public Criteria andAgentStateEqualTo(String value) {
            addCriterion("agent_state =", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateNotEqualTo(String value) {
            addCriterion("agent_state <>", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateGreaterThan(String value) {
            addCriterion("agent_state >", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateGreaterThanOrEqualTo(String value) {
            addCriterion("agent_state >=", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateLessThan(String value) {
            addCriterion("agent_state <", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateLessThanOrEqualTo(String value) {
            addCriterion("agent_state <=", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateLike(String value) {
            addCriterion("agent_state like", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateNotLike(String value) {
            addCriterion("agent_state not like", value, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateIn(List<String> values) {
            addCriterion("agent_state in", values, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateNotIn(List<String> values) {
            addCriterion("agent_state not in", values, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateBetween(String value1, String value2) {
            addCriterion("agent_state between", value1, value2, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentStateNotBetween(String value1, String value2) {
            addCriterion("agent_state not between", value1, value2, "agentState");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIsNull() {
            addCriterion("agent_level is null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIsNotNull() {
            addCriterion("agent_level is not null");
            return (Criteria) this;
        }

        public Criteria andAgentLevelEqualTo(String value) {
            addCriterion("agent_level =", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNotEqualTo(String value) {
            addCriterion("agent_level <>", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelGreaterThan(String value) {
            addCriterion("agent_level >", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelGreaterThanOrEqualTo(String value) {
            addCriterion("agent_level >=", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelLessThan(String value) {
            addCriterion("agent_level <", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelLessThanOrEqualTo(String value) {
            addCriterion("agent_level <=", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelLike(String value) {
            addCriterion("agent_level like", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNotLike(String value) {
            addCriterion("agent_level not like", value, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelIn(List<String> values) {
            addCriterion("agent_level in", values, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNotIn(List<String> values) {
            addCriterion("agent_level not in", values, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelBetween(String value1, String value2) {
            addCriterion("agent_level between", value1, value2, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentLevelNotBetween(String value1, String value2) {
            addCriterion("agent_level not between", value1, value2, "agentLevel");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeIsNull() {
            addCriterion("agent_discount_type is null");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeIsNotNull() {
            addCriterion("agent_discount_type is not null");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeEqualTo(String value) {
            addCriterion("agent_discount_type =", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeNotEqualTo(String value) {
            addCriterion("agent_discount_type <>", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeGreaterThan(String value) {
            addCriterion("agent_discount_type >", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("agent_discount_type >=", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeLessThan(String value) {
            addCriterion("agent_discount_type <", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeLessThanOrEqualTo(String value) {
            addCriterion("agent_discount_type <=", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeLike(String value) {
            addCriterion("agent_discount_type like", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeNotLike(String value) {
            addCriterion("agent_discount_type not like", value, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeIn(List<String> values) {
            addCriterion("agent_discount_type in", values, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeNotIn(List<String> values) {
            addCriterion("agent_discount_type not in", values, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeBetween(String value1, String value2) {
            addCriterion("agent_discount_type between", value1, value2, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountTypeNotBetween(String value1, String value2) {
            addCriterion("agent_discount_type not between", value1, value2, "agentDiscountType");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountIsNull() {
            addCriterion("agent_discount is null");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountIsNotNull() {
            addCriterion("agent_discount is not null");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountEqualTo(Integer value) {
            addCriterion("agent_discount =", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountNotEqualTo(Integer value) {
            addCriterion("agent_discount <>", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountGreaterThan(Integer value) {
            addCriterion("agent_discount >", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_discount >=", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountLessThan(Integer value) {
            addCriterion("agent_discount <", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("agent_discount <=", value, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountIn(List<Integer> values) {
            addCriterion("agent_discount in", values, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountNotIn(List<Integer> values) {
            addCriterion("agent_discount not in", values, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountBetween(Integer value1, Integer value2) {
            addCriterion("agent_discount between", value1, value2, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_discount not between", value1, value2, "agentDiscount");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsIsNull() {
            addCriterion("agent_payments is null");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsIsNotNull() {
            addCriterion("agent_payments is not null");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsEqualTo(String value) {
            addCriterion("agent_payments =", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsNotEqualTo(String value) {
            addCriterion("agent_payments <>", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsGreaterThan(String value) {
            addCriterion("agent_payments >", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsGreaterThanOrEqualTo(String value) {
            addCriterion("agent_payments >=", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsLessThan(String value) {
            addCriterion("agent_payments <", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsLessThanOrEqualTo(String value) {
            addCriterion("agent_payments <=", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsLike(String value) {
            addCriterion("agent_payments like", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsNotLike(String value) {
            addCriterion("agent_payments not like", value, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsIn(List<String> values) {
            addCriterion("agent_payments in", values, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsNotIn(List<String> values) {
            addCriterion("agent_payments not in", values, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsBetween(String value1, String value2) {
            addCriterion("agent_payments between", value1, value2, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentPaymentsNotBetween(String value1, String value2) {
            addCriterion("agent_payments not between", value1, value2, "agentPayments");
            return (Criteria) this;
        }

        public Criteria andAgentReturnIsNull() {
            addCriterion("agent_return is null");
            return (Criteria) this;
        }

        public Criteria andAgentReturnIsNotNull() {
            addCriterion("agent_return is not null");
            return (Criteria) this;
        }

        public Criteria andAgentReturnEqualTo(String value) {
            addCriterion("agent_return =", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnNotEqualTo(String value) {
            addCriterion("agent_return <>", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnGreaterThan(String value) {
            addCriterion("agent_return >", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnGreaterThanOrEqualTo(String value) {
            addCriterion("agent_return >=", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnLessThan(String value) {
            addCriterion("agent_return <", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnLessThanOrEqualTo(String value) {
            addCriterion("agent_return <=", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnLike(String value) {
            addCriterion("agent_return like", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnNotLike(String value) {
            addCriterion("agent_return not like", value, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnIn(List<String> values) {
            addCriterion("agent_return in", values, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnNotIn(List<String> values) {
            addCriterion("agent_return not in", values, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnBetween(String value1, String value2) {
            addCriterion("agent_return between", value1, value2, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnNotBetween(String value1, String value2) {
            addCriterion("agent_return not between", value1, value2, "agentReturn");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueIsNull() {
            addCriterion("agent_returnvalue is null");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueIsNotNull() {
            addCriterion("agent_returnvalue is not null");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueEqualTo(Integer value) {
            addCriterion("agent_returnvalue =", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueNotEqualTo(Integer value) {
            addCriterion("agent_returnvalue <>", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueGreaterThan(Integer value) {
            addCriterion("agent_returnvalue >", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_returnvalue >=", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueLessThan(Integer value) {
            addCriterion("agent_returnvalue <", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueLessThanOrEqualTo(Integer value) {
            addCriterion("agent_returnvalue <=", value, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueIn(List<Integer> values) {
            addCriterion("agent_returnvalue in", values, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueNotIn(List<Integer> values) {
            addCriterion("agent_returnvalue not in", values, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueBetween(Integer value1, Integer value2) {
            addCriterion("agent_returnvalue between", value1, value2, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentReturnvalueNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_returnvalue not between", value1, value2, "agentReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateIsNull() {
            addCriterion("agent_fmdate is null");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateIsNotNull() {
            addCriterion("agent_fmdate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateEqualTo(Date value) {
            addCriterion("agent_fmdate =", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateNotEqualTo(Date value) {
            addCriterion("agent_fmdate <>", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateGreaterThan(Date value) {
            addCriterion("agent_fmdate >", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_fmdate >=", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateLessThan(Date value) {
            addCriterion("agent_fmdate <", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateLessThanOrEqualTo(Date value) {
            addCriterion("agent_fmdate <=", value, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateIn(List<Date> values) {
            addCriterion("agent_fmdate in", values, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateNotIn(List<Date> values) {
            addCriterion("agent_fmdate not in", values, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateBetween(Date value1, Date value2) {
            addCriterion("agent_fmdate between", value1, value2, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentFmdateNotBetween(Date value1, Date value2) {
            addCriterion("agent_fmdate not between", value1, value2, "agentFmdate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateIsNull() {
            addCriterion("agent_todate is null");
            return (Criteria) this;
        }

        public Criteria andAgentTodateIsNotNull() {
            addCriterion("agent_todate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentTodateEqualTo(Date value) {
            addCriterion("agent_todate =", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateNotEqualTo(Date value) {
            addCriterion("agent_todate <>", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateGreaterThan(Date value) {
            addCriterion("agent_todate >", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateGreaterThanOrEqualTo(Date value) {
            addCriterion("agent_todate >=", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateLessThan(Date value) {
            addCriterion("agent_todate <", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateLessThanOrEqualTo(Date value) {
            addCriterion("agent_todate <=", value, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateIn(List<Date> values) {
            addCriterion("agent_todate in", values, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateNotIn(List<Date> values) {
            addCriterion("agent_todate not in", values, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateBetween(Date value1, Date value2) {
            addCriterion("agent_todate between", value1, value2, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentTodateNotBetween(Date value1, Date value2) {
            addCriterion("agent_todate not between", value1, value2, "agentTodate");
            return (Criteria) this;
        }

        public Criteria andAgentContractIsNull() {
            addCriterion("agent_contract is null");
            return (Criteria) this;
        }

        public Criteria andAgentContractIsNotNull() {
            addCriterion("agent_contract is not null");
            return (Criteria) this;
        }

        public Criteria andAgentContractEqualTo(String value) {
            addCriterion("agent_contract =", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractNotEqualTo(String value) {
            addCriterion("agent_contract <>", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractGreaterThan(String value) {
            addCriterion("agent_contract >", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractGreaterThanOrEqualTo(String value) {
            addCriterion("agent_contract >=", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractLessThan(String value) {
            addCriterion("agent_contract <", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractLessThanOrEqualTo(String value) {
            addCriterion("agent_contract <=", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractLike(String value) {
            addCriterion("agent_contract like", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractNotLike(String value) {
            addCriterion("agent_contract not like", value, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractIn(List<String> values) {
            addCriterion("agent_contract in", values, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractNotIn(List<String> values) {
            addCriterion("agent_contract not in", values, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractBetween(String value1, String value2) {
            addCriterion("agent_contract between", value1, value2, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentContractNotBetween(String value1, String value2) {
            addCriterion("agent_contract not between", value1, value2, "agentContract");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyIsNull() {
            addCriterion("agent_certify is null");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyIsNotNull() {
            addCriterion("agent_certify is not null");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyEqualTo(String value) {
            addCriterion("agent_certify =", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyNotEqualTo(String value) {
            addCriterion("agent_certify <>", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyGreaterThan(String value) {
            addCriterion("agent_certify >", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyGreaterThanOrEqualTo(String value) {
            addCriterion("agent_certify >=", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyLessThan(String value) {
            addCriterion("agent_certify <", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyLessThanOrEqualTo(String value) {
            addCriterion("agent_certify <=", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyLike(String value) {
            addCriterion("agent_certify like", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyNotLike(String value) {
            addCriterion("agent_certify not like", value, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyIn(List<String> values) {
            addCriterion("agent_certify in", values, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyNotIn(List<String> values) {
            addCriterion("agent_certify not in", values, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyBetween(String value1, String value2) {
            addCriterion("agent_certify between", value1, value2, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andAgentCertifyNotBetween(String value1, String value2) {
            addCriterion("agent_certify not between", value1, value2, "agentCertify");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNull() {
            addCriterion("link_name is null");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNotNull() {
            addCriterion("link_name is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNameEqualTo(String value) {
            addCriterion("link_name =", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotEqualTo(String value) {
            addCriterion("link_name <>", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThan(String value) {
            addCriterion("link_name >", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThanOrEqualTo(String value) {
            addCriterion("link_name >=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThan(String value) {
            addCriterion("link_name <", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThanOrEqualTo(String value) {
            addCriterion("link_name <=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLike(String value) {
            addCriterion("link_name like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotLike(String value) {
            addCriterion("link_name not like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameIn(List<String> values) {
            addCriterion("link_name in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotIn(List<String> values) {
            addCriterion("link_name not in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameBetween(String value1, String value2) {
            addCriterion("link_name between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotBetween(String value1, String value2) {
            addCriterion("link_name not between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkTelIsNull() {
            addCriterion("link_tel is null");
            return (Criteria) this;
        }

        public Criteria andLinkTelIsNotNull() {
            addCriterion("link_tel is not null");
            return (Criteria) this;
        }

        public Criteria andLinkTelEqualTo(String value) {
            addCriterion("link_tel =", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelNotEqualTo(String value) {
            addCriterion("link_tel <>", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelGreaterThan(String value) {
            addCriterion("link_tel >", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelGreaterThanOrEqualTo(String value) {
            addCriterion("link_tel >=", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelLessThan(String value) {
            addCriterion("link_tel <", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelLessThanOrEqualTo(String value) {
            addCriterion("link_tel <=", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelLike(String value) {
            addCriterion("link_tel like", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelNotLike(String value) {
            addCriterion("link_tel not like", value, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelIn(List<String> values) {
            addCriterion("link_tel in", values, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelNotIn(List<String> values) {
            addCriterion("link_tel not in", values, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelBetween(String value1, String value2) {
            addCriterion("link_tel between", value1, value2, "linkTel");
            return (Criteria) this;
        }

        public Criteria andLinkTelNotBetween(String value1, String value2) {
            addCriterion("link_tel not between", value1, value2, "linkTel");
            return (Criteria) this;
        }

        public Criteria andRevProvinceIsNull() {
            addCriterion("rev_province is null");
            return (Criteria) this;
        }

        public Criteria andRevProvinceIsNotNull() {
            addCriterion("rev_province is not null");
            return (Criteria) this;
        }

        public Criteria andRevProvinceEqualTo(String value) {
            addCriterion("rev_province =", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceNotEqualTo(String value) {
            addCriterion("rev_province <>", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceGreaterThan(String value) {
            addCriterion("rev_province >", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("rev_province >=", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceLessThan(String value) {
            addCriterion("rev_province <", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceLessThanOrEqualTo(String value) {
            addCriterion("rev_province <=", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceLike(String value) {
            addCriterion("rev_province like", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceNotLike(String value) {
            addCriterion("rev_province not like", value, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceIn(List<String> values) {
            addCriterion("rev_province in", values, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceNotIn(List<String> values) {
            addCriterion("rev_province not in", values, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceBetween(String value1, String value2) {
            addCriterion("rev_province between", value1, value2, "revProvince");
            return (Criteria) this;
        }

        public Criteria andRevProvinceNotBetween(String value1, String value2) {
            addCriterion("rev_province not between", value1, value2, "revProvince");
            return (Criteria) this;
        }

        public Criteria andAgentDescIsNull() {
            addCriterion("agent_desc is null");
            return (Criteria) this;
        }

        public Criteria andAgentDescIsNotNull() {
            addCriterion("agent_desc is not null");
            return (Criteria) this;
        }

        public Criteria andAgentDescEqualTo(String value) {
            addCriterion("agent_desc =", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescNotEqualTo(String value) {
            addCriterion("agent_desc <>", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescGreaterThan(String value) {
            addCriterion("agent_desc >", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescGreaterThanOrEqualTo(String value) {
            addCriterion("agent_desc >=", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescLessThan(String value) {
            addCriterion("agent_desc <", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescLessThanOrEqualTo(String value) {
            addCriterion("agent_desc <=", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescLike(String value) {
            addCriterion("agent_desc like", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescNotLike(String value) {
            addCriterion("agent_desc not like", value, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescIn(List<String> values) {
            addCriterion("agent_desc in", values, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescNotIn(List<String> values) {
            addCriterion("agent_desc not in", values, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescBetween(String value1, String value2) {
            addCriterion("agent_desc between", value1, value2, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andAgentDescNotBetween(String value1, String value2) {
            addCriterion("agent_desc not between", value1, value2, "agentDesc");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("org_id is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("org_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Integer value) {
            addCriterion("org_id =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Integer value) {
            addCriterion("org_id <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Integer value) {
            addCriterion("org_id >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("org_id >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Integer value) {
            addCriterion("org_id <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Integer value) {
            addCriterion("org_id <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Integer> values) {
            addCriterion("org_id in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Integer> values) {
            addCriterion("org_id not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Integer value1, Integer value2) {
            addCriterion("org_id between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Integer value1, Integer value2) {
            addCriterion("org_id not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andSysIdIsNull() {
            addCriterion("sys_id is null");
            return (Criteria) this;
        }

        public Criteria andSysIdIsNotNull() {
            addCriterion("sys_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysIdEqualTo(Integer value) {
            addCriterion("sys_id =", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotEqualTo(Integer value) {
            addCriterion("sys_id <>", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdGreaterThan(Integer value) {
            addCriterion("sys_id >", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_id >=", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdLessThan(Integer value) {
            addCriterion("sys_id <", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdLessThanOrEqualTo(Integer value) {
            addCriterion("sys_id <=", value, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdIn(List<Integer> values) {
            addCriterion("sys_id in", values, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotIn(List<Integer> values) {
            addCriterion("sys_id not in", values, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdBetween(Integer value1, Integer value2) {
            addCriterion("sys_id between", value1, value2, "sysId");
            return (Criteria) this;
        }

        public Criteria andSysIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_id not between", value1, value2, "sysId");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Integer value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Integer value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Integer value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Integer value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Integer value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Integer> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Integer> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Integer value1, Integer value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(Integer value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(Integer value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(Integer value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(Integer value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(Integer value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(Integer value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<Integer> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<Integer> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(Integer value1, Integer value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(Integer value1, Integer value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andAgentBrandIsNull() {
            addCriterion("agent_brand is null");
            return (Criteria) this;
        }

        public Criteria andAgentBrandIsNotNull() {
            addCriterion("agent_brand is not null");
            return (Criteria) this;
        }

        public Criteria andAgentBrandEqualTo(String value) {
            addCriterion("agent_brand =", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandNotEqualTo(String value) {
            addCriterion("agent_brand <>", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandGreaterThan(String value) {
            addCriterion("agent_brand >", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandGreaterThanOrEqualTo(String value) {
            addCriterion("agent_brand >=", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandLessThan(String value) {
            addCriterion("agent_brand <", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandLessThanOrEqualTo(String value) {
            addCriterion("agent_brand <=", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandLike(String value) {
            addCriterion("agent_brand like", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandNotLike(String value) {
            addCriterion("agent_brand not like", value, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandIn(List<String> values) {
            addCriterion("agent_brand in", values, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandNotIn(List<String> values) {
            addCriterion("agent_brand not in", values, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandBetween(String value1, String value2) {
            addCriterion("agent_brand between", value1, value2, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andAgentBrandNotBetween(String value1, String value2) {
            addCriterion("agent_brand not between", value1, value2, "agentBrand");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("order_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("order_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(Date value) {
            addCriterion("order_date =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(Date value) {
            addCriterion("order_date <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(Date value) {
            addCriterion("order_date >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_date >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(Date value) {
            addCriterion("order_date <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(Date value) {
            addCriterion("order_date <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<Date> values) {
            addCriterion("order_date in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<Date> values) {
            addCriterion("order_date not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(Date value1, Date value2) {
            addCriterion("order_date between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(Date value1, Date value2) {
            addCriterion("order_date not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andCoopStateIsNull() {
            addCriterion("coop_state is null");
            return (Criteria) this;
        }

        public Criteria andCoopStateIsNotNull() {
            addCriterion("coop_state is not null");
            return (Criteria) this;
        }

        public Criteria andCoopStateEqualTo(String value) {
            addCriterion("coop_state =", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateNotEqualTo(String value) {
            addCriterion("coop_state <>", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateGreaterThan(String value) {
            addCriterion("coop_state >", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateGreaterThanOrEqualTo(String value) {
            addCriterion("coop_state >=", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateLessThan(String value) {
            addCriterion("coop_state <", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateLessThanOrEqualTo(String value) {
            addCriterion("coop_state <=", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateLike(String value) {
            addCriterion("coop_state like", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateNotLike(String value) {
            addCriterion("coop_state not like", value, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateIn(List<String> values) {
            addCriterion("coop_state in", values, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateNotIn(List<String> values) {
            addCriterion("coop_state not in", values, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateBetween(String value1, String value2) {
            addCriterion("coop_state between", value1, value2, "coopState");
            return (Criteria) this;
        }

        public Criteria andCoopStateNotBetween(String value1, String value2) {
            addCriterion("coop_state not between", value1, value2, "coopState");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueIsNull() {
            addCriterion("agenet_returnvalue is null");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueIsNotNull() {
            addCriterion("agenet_returnvalue is not null");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueEqualTo(Integer value) {
            addCriterion("agenet_returnvalue =", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueNotEqualTo(Integer value) {
            addCriterion("agenet_returnvalue <>", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueGreaterThan(Integer value) {
            addCriterion("agenet_returnvalue >", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueGreaterThanOrEqualTo(Integer value) {
            addCriterion("agenet_returnvalue >=", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueLessThan(Integer value) {
            addCriterion("agenet_returnvalue <", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueLessThanOrEqualTo(Integer value) {
            addCriterion("agenet_returnvalue <=", value, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueIn(List<Integer> values) {
            addCriterion("agenet_returnvalue in", values, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueNotIn(List<Integer> values) {
            addCriterion("agenet_returnvalue not in", values, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueBetween(Integer value1, Integer value2) {
            addCriterion("agenet_returnvalue between", value1, value2, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgenetReturnvalueNotBetween(Integer value1, Integer value2) {
            addCriterion("agenet_returnvalue not between", value1, value2, "agenetReturnvalue");
            return (Criteria) this;
        }

        public Criteria andAgentAddrIsNull() {
            addCriterion("agent_addr is null");
            return (Criteria) this;
        }

        public Criteria andAgentAddrIsNotNull() {
            addCriterion("agent_addr is not null");
            return (Criteria) this;
        }

        public Criteria andAgentAddrEqualTo(String value) {
            addCriterion("agent_addr =", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrNotEqualTo(String value) {
            addCriterion("agent_addr <>", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrGreaterThan(String value) {
            addCriterion("agent_addr >", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrGreaterThanOrEqualTo(String value) {
            addCriterion("agent_addr >=", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrLessThan(String value) {
            addCriterion("agent_addr <", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrLessThanOrEqualTo(String value) {
            addCriterion("agent_addr <=", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrLike(String value) {
            addCriterion("agent_addr like", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrNotLike(String value) {
            addCriterion("agent_addr not like", value, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrIn(List<String> values) {
            addCriterion("agent_addr in", values, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrNotIn(List<String> values) {
            addCriterion("agent_addr not in", values, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrBetween(String value1, String value2) {
            addCriterion("agent_addr between", value1, value2, "agentAddr");
            return (Criteria) this;
        }

        public Criteria andAgentAddrNotBetween(String value1, String value2) {
            addCriterion("agent_addr not between", value1, value2, "agentAddr");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_agent_mas
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
     *  oa_agent_mas
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