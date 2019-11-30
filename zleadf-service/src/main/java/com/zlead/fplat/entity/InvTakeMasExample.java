package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvTakeMasExample {
    /**
     * orderByClausenull .
     *   inv_take_mas
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   inv_take_mas
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   inv_take_mas
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:InvTakeMasExample
     *   inv_take_mas
     *
     * @ET
     */
    public InvTakeMasExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   inv_take_mas
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   inv_take_mas
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   inv_take_mas
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   inv_take_mas
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   inv_take_mas
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   inv_take_mas
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   inv_take_mas
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
     *   inv_take_mas
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
     *   inv_take_mas
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   inv_take_mas
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
     *  inv_take_mas
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

        public Criteria andBillIdIsNull() {
            addCriterion("bill_id is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(Integer value) {
            addCriterion("bill_id =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(Integer value) {
            addCriterion("bill_id <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(Integer value) {
            addCriterion("bill_id >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_id >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(Integer value) {
            addCriterion("bill_id <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(Integer value) {
            addCriterion("bill_id <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<Integer> values) {
            addCriterion("bill_id in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<Integer> values) {
            addCriterion("bill_id not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(Integer value1, Integer value2) {
            addCriterion("bill_id between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_id not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNull() {
            addCriterion("bill_no is null");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNotNull() {
            addCriterion("bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andBillNoEqualTo(String value) {
            addCriterion("bill_no =", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotEqualTo(String value) {
            addCriterion("bill_no <>", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThan(String value) {
            addCriterion("bill_no >", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("bill_no >=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThan(String value) {
            addCriterion("bill_no <", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThanOrEqualTo(String value) {
            addCriterion("bill_no <=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLike(String value) {
            addCriterion("bill_no like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotLike(String value) {
            addCriterion("bill_no not like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoIn(List<String> values) {
            addCriterion("bill_no in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotIn(List<String> values) {
            addCriterion("bill_no not in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoBetween(String value1, String value2) {
            addCriterion("bill_no between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotBetween(String value1, String value2) {
            addCriterion("bill_no not between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNull() {
            addCriterion("bill_type is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("bill_type is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("bill_type =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("bill_type <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("bill_type >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bill_type >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("bill_type <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("bill_type <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("bill_type like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("bill_type not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("bill_type in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("bill_type not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("bill_type between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("bill_type not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andWhIdIsNull() {
            addCriterion("wh_id is null");
            return (Criteria) this;
        }

        public Criteria andWhIdIsNotNull() {
            addCriterion("wh_id is not null");
            return (Criteria) this;
        }

        public Criteria andWhIdEqualTo(Integer value) {
            addCriterion("wh_id =", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotEqualTo(Integer value) {
            addCriterion("wh_id <>", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdGreaterThan(Integer value) {
            addCriterion("wh_id >", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wh_id >=", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdLessThan(Integer value) {
            addCriterion("wh_id <", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdLessThanOrEqualTo(Integer value) {
            addCriterion("wh_id <=", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdIn(List<Integer> values) {
            addCriterion("wh_id in", values, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotIn(List<Integer> values) {
            addCriterion("wh_id not in", values, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdBetween(Integer value1, Integer value2) {
            addCriterion("wh_id between", value1, value2, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wh_id not between", value1, value2, "whId");
            return (Criteria) this;
        }

        public Criteria andLocIdIsNull() {
            addCriterion("loc_id is null");
            return (Criteria) this;
        }

        public Criteria andLocIdIsNotNull() {
            addCriterion("loc_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocIdEqualTo(Integer value) {
            addCriterion("loc_id =", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotEqualTo(Integer value) {
            addCriterion("loc_id <>", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdGreaterThan(Integer value) {
            addCriterion("loc_id >", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loc_id >=", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdLessThan(Integer value) {
            addCriterion("loc_id <", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdLessThanOrEqualTo(Integer value) {
            addCriterion("loc_id <=", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdIn(List<Integer> values) {
            addCriterion("loc_id in", values, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotIn(List<Integer> values) {
            addCriterion("loc_id not in", values, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdBetween(Integer value1, Integer value2) {
            addCriterion("loc_id between", value1, value2, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loc_id not between", value1, value2, "locId");
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

        public Criteria andBillDateIsNull() {
            addCriterion("bill_date is null");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNotNull() {
            addCriterion("bill_date is not null");
            return (Criteria) this;
        }

        public Criteria andBillDateEqualTo(Date value) {
            addCriterion("bill_date =", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotEqualTo(Date value) {
            addCriterion("bill_date <>", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThan(Date value) {
            addCriterion("bill_date >", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThanOrEqualTo(Date value) {
            addCriterion("bill_date >=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThan(Date value) {
            addCriterion("bill_date <", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThanOrEqualTo(Date value) {
            addCriterion("bill_date <=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateIn(List<Date> values) {
            addCriterion("bill_date in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotIn(List<Date> values) {
            addCriterion("bill_date not in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateBetween(Date value1, Date value2) {
            addCriterion("bill_date between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotBetween(Date value1, Date value2) {
            addCriterion("bill_date not between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillFlagIsNull() {
            addCriterion("bill_flag is null");
            return (Criteria) this;
        }

        public Criteria andBillFlagIsNotNull() {
            addCriterion("bill_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBillFlagEqualTo(String value) {
            addCriterion("bill_flag =", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotEqualTo(String value) {
            addCriterion("bill_flag <>", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagGreaterThan(String value) {
            addCriterion("bill_flag >", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagGreaterThanOrEqualTo(String value) {
            addCriterion("bill_flag >=", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagLessThan(String value) {
            addCriterion("bill_flag <", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagLessThanOrEqualTo(String value) {
            addCriterion("bill_flag <=", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagLike(String value) {
            addCriterion("bill_flag like", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotLike(String value) {
            addCriterion("bill_flag not like", value, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagIn(List<String> values) {
            addCriterion("bill_flag in", values, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotIn(List<String> values) {
            addCriterion("bill_flag not in", values, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagBetween(String value1, String value2) {
            addCriterion("bill_flag between", value1, value2, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillFlagNotBetween(String value1, String value2) {
            addCriterion("bill_flag not between", value1, value2, "billFlag");
            return (Criteria) this;
        }

        public Criteria andBillAmtIsNull() {
            addCriterion("bill_amt is null");
            return (Criteria) this;
        }

        public Criteria andBillAmtIsNotNull() {
            addCriterion("bill_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBillAmtEqualTo(Double value) {
            addCriterion("bill_amt =", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtNotEqualTo(Double value) {
            addCriterion("bill_amt <>", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtGreaterThan(Double value) {
            addCriterion("bill_amt >", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("bill_amt >=", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtLessThan(Double value) {
            addCriterion("bill_amt <", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtLessThanOrEqualTo(Double value) {
            addCriterion("bill_amt <=", value, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtIn(List<Double> values) {
            addCriterion("bill_amt in", values, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtNotIn(List<Double> values) {
            addCriterion("bill_amt not in", values, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtBetween(Double value1, Double value2) {
            addCriterion("bill_amt between", value1, value2, "billAmt");
            return (Criteria) this;
        }

        public Criteria andBillAmtNotBetween(Double value1, Double value2) {
            addCriterion("bill_amt not between", value1, value2, "billAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtIsNull() {
            addCriterion("mas_amt is null");
            return (Criteria) this;
        }

        public Criteria andMasAmtIsNotNull() {
            addCriterion("mas_amt is not null");
            return (Criteria) this;
        }

        public Criteria andMasAmtEqualTo(Double value) {
            addCriterion("mas_amt =", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtNotEqualTo(Double value) {
            addCriterion("mas_amt <>", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtGreaterThan(Double value) {
            addCriterion("mas_amt >", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("mas_amt >=", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtLessThan(Double value) {
            addCriterion("mas_amt <", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtLessThanOrEqualTo(Double value) {
            addCriterion("mas_amt <=", value, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtIn(List<Double> values) {
            addCriterion("mas_amt in", values, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtNotIn(List<Double> values) {
            addCriterion("mas_amt not in", values, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtBetween(Double value1, Double value2) {
            addCriterion("mas_amt between", value1, value2, "masAmt");
            return (Criteria) this;
        }

        public Criteria andMasAmtNotBetween(Double value1, Double value2) {
            addCriterion("mas_amt not between", value1, value2, "masAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtIsNull() {
            addCriterion("def_amt is null");
            return (Criteria) this;
        }

        public Criteria andDefAmtIsNotNull() {
            addCriterion("def_amt is not null");
            return (Criteria) this;
        }

        public Criteria andDefAmtEqualTo(Double value) {
            addCriterion("def_amt =", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtNotEqualTo(Double value) {
            addCriterion("def_amt <>", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtGreaterThan(Double value) {
            addCriterion("def_amt >", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("def_amt >=", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtLessThan(Double value) {
            addCriterion("def_amt <", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtLessThanOrEqualTo(Double value) {
            addCriterion("def_amt <=", value, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtIn(List<Double> values) {
            addCriterion("def_amt in", values, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtNotIn(List<Double> values) {
            addCriterion("def_amt not in", values, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtBetween(Double value1, Double value2) {
            addCriterion("def_amt between", value1, value2, "defAmt");
            return (Criteria) this;
        }

        public Criteria andDefAmtNotBetween(Double value1, Double value2) {
            addCriterion("def_amt not between", value1, value2, "defAmt");
            return (Criteria) this;
        }

        public Criteria andPicsPathIsNull() {
            addCriterion("pics_path is null");
            return (Criteria) this;
        }

        public Criteria andPicsPathIsNotNull() {
            addCriterion("pics_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicsPathEqualTo(String value) {
            addCriterion("pics_path =", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotEqualTo(String value) {
            addCriterion("pics_path <>", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathGreaterThan(String value) {
            addCriterion("pics_path >", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathGreaterThanOrEqualTo(String value) {
            addCriterion("pics_path >=", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLessThan(String value) {
            addCriterion("pics_path <", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLessThanOrEqualTo(String value) {
            addCriterion("pics_path <=", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLike(String value) {
            addCriterion("pics_path like", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotLike(String value) {
            addCriterion("pics_path not like", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathIn(List<String> values) {
            addCriterion("pics_path in", values, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotIn(List<String> values) {
            addCriterion("pics_path not in", values, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathBetween(String value1, String value2) {
            addCriterion("pics_path between", value1, value2, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotBetween(String value1, String value2) {
            addCriterion("pics_path not between", value1, value2, "picsPath");
            return (Criteria) this;
        }

        public Criteria andBillStateIsNull() {
            addCriterion("bill_state is null");
            return (Criteria) this;
        }

        public Criteria andBillStateIsNotNull() {
            addCriterion("bill_state is not null");
            return (Criteria) this;
        }

        public Criteria andBillStateEqualTo(String value) {
            addCriterion("bill_state =", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateNotEqualTo(String value) {
            addCriterion("bill_state <>", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateGreaterThan(String value) {
            addCriterion("bill_state >", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateGreaterThanOrEqualTo(String value) {
            addCriterion("bill_state >=", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateLessThan(String value) {
            addCriterion("bill_state <", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateLessThanOrEqualTo(String value) {
            addCriterion("bill_state <=", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateLike(String value) {
            addCriterion("bill_state like", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateNotLike(String value) {
            addCriterion("bill_state not like", value, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateIn(List<String> values) {
            addCriterion("bill_state in", values, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateNotIn(List<String> values) {
            addCriterion("bill_state not in", values, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateBetween(String value1, String value2) {
            addCriterion("bill_state between", value1, value2, "billState");
            return (Criteria) this;
        }

        public Criteria andBillStateNotBetween(String value1, String value2) {
            addCriterion("bill_state not between", value1, value2, "billState");
            return (Criteria) this;
        }

        public Criteria andMasDescIsNull() {
            addCriterion("mas_desc is null");
            return (Criteria) this;
        }

        public Criteria andMasDescIsNotNull() {
            addCriterion("mas_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMasDescEqualTo(String value) {
            addCriterion("mas_desc =", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotEqualTo(String value) {
            addCriterion("mas_desc <>", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescGreaterThan(String value) {
            addCriterion("mas_desc >", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescGreaterThanOrEqualTo(String value) {
            addCriterion("mas_desc >=", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLessThan(String value) {
            addCriterion("mas_desc <", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLessThanOrEqualTo(String value) {
            addCriterion("mas_desc <=", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLike(String value) {
            addCriterion("mas_desc like", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotLike(String value) {
            addCriterion("mas_desc not like", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescIn(List<String> values) {
            addCriterion("mas_desc in", values, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotIn(List<String> values) {
            addCriterion("mas_desc not in", values, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescBetween(String value1, String value2) {
            addCriterion("mas_desc between", value1, value2, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotBetween(String value1, String value2) {
            addCriterion("mas_desc not between", value1, value2, "masDesc");
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
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  inv_take_mas
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
     *  inv_take_mas
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