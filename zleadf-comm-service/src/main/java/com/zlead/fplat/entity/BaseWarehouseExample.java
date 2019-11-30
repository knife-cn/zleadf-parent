package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseWarehouseExample {
    /**
     * orderByClausenull .
     *   base_warehouse
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   base_warehouse
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   base_warehouse
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:BaseWarehouseExample
     *   base_warehouse
     *
     * @ET
     */
    public BaseWarehouseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   base_warehouse
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   base_warehouse
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   base_warehouse
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   base_warehouse
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   base_warehouse
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   base_warehouse
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   base_warehouse
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
     *   base_warehouse
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
     *   base_warehouse
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   base_warehouse
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
     *  base_warehouse
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

        public Criteria andWhNoIsNull() {
            addCriterion("wh_no is null");
            return (Criteria) this;
        }

        public Criteria andWhNoIsNotNull() {
            addCriterion("wh_no is not null");
            return (Criteria) this;
        }

        public Criteria andWhNoEqualTo(String value) {
            addCriterion("wh_no =", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoNotEqualTo(String value) {
            addCriterion("wh_no <>", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoGreaterThan(String value) {
            addCriterion("wh_no >", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoGreaterThanOrEqualTo(String value) {
            addCriterion("wh_no >=", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoLessThan(String value) {
            addCriterion("wh_no <", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoLessThanOrEqualTo(String value) {
            addCriterion("wh_no <=", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoLike(String value) {
            addCriterion("wh_no like", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoNotLike(String value) {
            addCriterion("wh_no not like", value, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoIn(List<String> values) {
            addCriterion("wh_no in", values, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoNotIn(List<String> values) {
            addCriterion("wh_no not in", values, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoBetween(String value1, String value2) {
            addCriterion("wh_no between", value1, value2, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNoNotBetween(String value1, String value2) {
            addCriterion("wh_no not between", value1, value2, "whNo");
            return (Criteria) this;
        }

        public Criteria andWhNameIsNull() {
            addCriterion("wh_name is null");
            return (Criteria) this;
        }

        public Criteria andWhNameIsNotNull() {
            addCriterion("wh_name is not null");
            return (Criteria) this;
        }

        public Criteria andWhNameEqualTo(String value) {
            addCriterion("wh_name =", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameNotEqualTo(String value) {
            addCriterion("wh_name <>", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameGreaterThan(String value) {
            addCriterion("wh_name >", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameGreaterThanOrEqualTo(String value) {
            addCriterion("wh_name >=", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameLessThan(String value) {
            addCriterion("wh_name <", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameLessThanOrEqualTo(String value) {
            addCriterion("wh_name <=", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameLike(String value) {
            addCriterion("wh_name like", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameNotLike(String value) {
            addCriterion("wh_name not like", value, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameIn(List<String> values) {
            addCriterion("wh_name in", values, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameNotIn(List<String> values) {
            addCriterion("wh_name not in", values, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameBetween(String value1, String value2) {
            addCriterion("wh_name between", value1, value2, "whName");
            return (Criteria) this;
        }

        public Criteria andWhNameNotBetween(String value1, String value2) {
            addCriterion("wh_name not between", value1, value2, "whName");
            return (Criteria) this;
        }

        public Criteria andLocFlagIsNull() {
            addCriterion("loc_flag is null");
            return (Criteria) this;
        }

        public Criteria andLocFlagIsNotNull() {
            addCriterion("loc_flag is not null");
            return (Criteria) this;
        }

        public Criteria andLocFlagEqualTo(String value) {
            addCriterion("loc_flag =", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagNotEqualTo(String value) {
            addCriterion("loc_flag <>", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagGreaterThan(String value) {
            addCriterion("loc_flag >", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagGreaterThanOrEqualTo(String value) {
            addCriterion("loc_flag >=", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagLessThan(String value) {
            addCriterion("loc_flag <", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagLessThanOrEqualTo(String value) {
            addCriterion("loc_flag <=", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagLike(String value) {
            addCriterion("loc_flag like", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagNotLike(String value) {
            addCriterion("loc_flag not like", value, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagIn(List<String> values) {
            addCriterion("loc_flag in", values, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagNotIn(List<String> values) {
            addCriterion("loc_flag not in", values, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagBetween(String value1, String value2) {
            addCriterion("loc_flag between", value1, value2, "locFlag");
            return (Criteria) this;
        }

        public Criteria andLocFlagNotBetween(String value1, String value2) {
            addCriterion("loc_flag not between", value1, value2, "locFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagIsNull() {
            addCriterion("box_flag is null");
            return (Criteria) this;
        }

        public Criteria andBoxFlagIsNotNull() {
            addCriterion("box_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBoxFlagEqualTo(String value) {
            addCriterion("box_flag =", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagNotEqualTo(String value) {
            addCriterion("box_flag <>", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagGreaterThan(String value) {
            addCriterion("box_flag >", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagGreaterThanOrEqualTo(String value) {
            addCriterion("box_flag >=", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagLessThan(String value) {
            addCriterion("box_flag <", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagLessThanOrEqualTo(String value) {
            addCriterion("box_flag <=", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagLike(String value) {
            addCriterion("box_flag like", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagNotLike(String value) {
            addCriterion("box_flag not like", value, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagIn(List<String> values) {
            addCriterion("box_flag in", values, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagNotIn(List<String> values) {
            addCriterion("box_flag not in", values, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagBetween(String value1, String value2) {
            addCriterion("box_flag between", value1, value2, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBoxFlagNotBetween(String value1, String value2) {
            addCriterion("box_flag not between", value1, value2, "boxFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagIsNull() {
            addCriterion("batch_flag is null");
            return (Criteria) this;
        }

        public Criteria andBatchFlagIsNotNull() {
            addCriterion("batch_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBatchFlagEqualTo(String value) {
            addCriterion("batch_flag =", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagNotEqualTo(String value) {
            addCriterion("batch_flag <>", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagGreaterThan(String value) {
            addCriterion("batch_flag >", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagGreaterThanOrEqualTo(String value) {
            addCriterion("batch_flag >=", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagLessThan(String value) {
            addCriterion("batch_flag <", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagLessThanOrEqualTo(String value) {
            addCriterion("batch_flag <=", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagLike(String value) {
            addCriterion("batch_flag like", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagNotLike(String value) {
            addCriterion("batch_flag not like", value, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagIn(List<String> values) {
            addCriterion("batch_flag in", values, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagNotIn(List<String> values) {
            addCriterion("batch_flag not in", values, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagBetween(String value1, String value2) {
            addCriterion("batch_flag between", value1, value2, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andBatchFlagNotBetween(String value1, String value2) {
            addCriterion("batch_flag not between", value1, value2, "batchFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagIsNull() {
            addCriterion("sn_flag is null");
            return (Criteria) this;
        }

        public Criteria andSnFlagIsNotNull() {
            addCriterion("sn_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSnFlagEqualTo(String value) {
            addCriterion("sn_flag =", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagNotEqualTo(String value) {
            addCriterion("sn_flag <>", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagGreaterThan(String value) {
            addCriterion("sn_flag >", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagGreaterThanOrEqualTo(String value) {
            addCriterion("sn_flag >=", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagLessThan(String value) {
            addCriterion("sn_flag <", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagLessThanOrEqualTo(String value) {
            addCriterion("sn_flag <=", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagLike(String value) {
            addCriterion("sn_flag like", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagNotLike(String value) {
            addCriterion("sn_flag not like", value, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagIn(List<String> values) {
            addCriterion("sn_flag in", values, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagNotIn(List<String> values) {
            addCriterion("sn_flag not in", values, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagBetween(String value1, String value2) {
            addCriterion("sn_flag between", value1, value2, "snFlag");
            return (Criteria) this;
        }

        public Criteria andSnFlagNotBetween(String value1, String value2) {
            addCriterion("sn_flag not between", value1, value2, "snFlag");
            return (Criteria) this;
        }

        public Criteria andMgrUserIsNull() {
            addCriterion("mgr_user is null");
            return (Criteria) this;
        }

        public Criteria andMgrUserIsNotNull() {
            addCriterion("mgr_user is not null");
            return (Criteria) this;
        }

        public Criteria andMgrUserEqualTo(String value) {
            addCriterion("mgr_user =", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserNotEqualTo(String value) {
            addCriterion("mgr_user <>", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserGreaterThan(String value) {
            addCriterion("mgr_user >", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserGreaterThanOrEqualTo(String value) {
            addCriterion("mgr_user >=", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserLessThan(String value) {
            addCriterion("mgr_user <", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserLessThanOrEqualTo(String value) {
            addCriterion("mgr_user <=", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserLike(String value) {
            addCriterion("mgr_user like", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserNotLike(String value) {
            addCriterion("mgr_user not like", value, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserIn(List<String> values) {
            addCriterion("mgr_user in", values, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserNotIn(List<String> values) {
            addCriterion("mgr_user not in", values, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserBetween(String value1, String value2) {
            addCriterion("mgr_user between", value1, value2, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andMgrUserNotBetween(String value1, String value2) {
            addCriterion("mgr_user not between", value1, value2, "mgrUser");
            return (Criteria) this;
        }

        public Criteria andWhStateIsNull() {
            addCriterion("wh_state is null");
            return (Criteria) this;
        }

        public Criteria andWhStateIsNotNull() {
            addCriterion("wh_state is not null");
            return (Criteria) this;
        }

        public Criteria andWhStateEqualTo(String value) {
            addCriterion("wh_state =", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateNotEqualTo(String value) {
            addCriterion("wh_state <>", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateGreaterThan(String value) {
            addCriterion("wh_state >", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateGreaterThanOrEqualTo(String value) {
            addCriterion("wh_state >=", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateLessThan(String value) {
            addCriterion("wh_state <", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateLessThanOrEqualTo(String value) {
            addCriterion("wh_state <=", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateLike(String value) {
            addCriterion("wh_state like", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateNotLike(String value) {
            addCriterion("wh_state not like", value, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateIn(List<String> values) {
            addCriterion("wh_state in", values, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateNotIn(List<String> values) {
            addCriterion("wh_state not in", values, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateBetween(String value1, String value2) {
            addCriterion("wh_state between", value1, value2, "whState");
            return (Criteria) this;
        }

        public Criteria andWhStateNotBetween(String value1, String value2) {
            addCriterion("wh_state not between", value1, value2, "whState");
            return (Criteria) this;
        }

        public Criteria andWhDescIsNull() {
            addCriterion("wh_desc is null");
            return (Criteria) this;
        }

        public Criteria andWhDescIsNotNull() {
            addCriterion("wh_desc is not null");
            return (Criteria) this;
        }

        public Criteria andWhDescEqualTo(String value) {
            addCriterion("wh_desc =", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescNotEqualTo(String value) {
            addCriterion("wh_desc <>", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescGreaterThan(String value) {
            addCriterion("wh_desc >", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescGreaterThanOrEqualTo(String value) {
            addCriterion("wh_desc >=", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescLessThan(String value) {
            addCriterion("wh_desc <", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescLessThanOrEqualTo(String value) {
            addCriterion("wh_desc <=", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescLike(String value) {
            addCriterion("wh_desc like", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescNotLike(String value) {
            addCriterion("wh_desc not like", value, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescIn(List<String> values) {
            addCriterion("wh_desc in", values, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescNotIn(List<String> values) {
            addCriterion("wh_desc not in", values, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescBetween(String value1, String value2) {
            addCriterion("wh_desc between", value1, value2, "whDesc");
            return (Criteria) this;
        }

        public Criteria andWhDescNotBetween(String value1, String value2) {
            addCriterion("wh_desc not between", value1, value2, "whDesc");
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
     *  base_warehouse
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
     *  base_warehouse
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