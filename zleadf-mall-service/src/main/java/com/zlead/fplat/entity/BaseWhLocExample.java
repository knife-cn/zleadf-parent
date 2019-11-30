package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseWhLocExample {
    /**
     * orderByClausenull .
     *   base_wh_loc
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   base_wh_loc
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   base_wh_loc
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:BaseWhLocExample
     *   base_wh_loc
     *
     * @ET
     */
    public BaseWhLocExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   base_wh_loc
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   base_wh_loc
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   base_wh_loc
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   base_wh_loc
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   base_wh_loc
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   base_wh_loc
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   base_wh_loc
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
     *   base_wh_loc
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
     *   base_wh_loc
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   base_wh_loc
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
     *  base_wh_loc
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

        public Criteria andLocNoIsNull() {
            addCriterion("loc_no is null");
            return (Criteria) this;
        }

        public Criteria andLocNoIsNotNull() {
            addCriterion("loc_no is not null");
            return (Criteria) this;
        }

        public Criteria andLocNoEqualTo(String value) {
            addCriterion("loc_no =", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoNotEqualTo(String value) {
            addCriterion("loc_no <>", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoGreaterThan(String value) {
            addCriterion("loc_no >", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoGreaterThanOrEqualTo(String value) {
            addCriterion("loc_no >=", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoLessThan(String value) {
            addCriterion("loc_no <", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoLessThanOrEqualTo(String value) {
            addCriterion("loc_no <=", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoLike(String value) {
            addCriterion("loc_no like", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoNotLike(String value) {
            addCriterion("loc_no not like", value, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoIn(List<String> values) {
            addCriterion("loc_no in", values, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoNotIn(List<String> values) {
            addCriterion("loc_no not in", values, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoBetween(String value1, String value2) {
            addCriterion("loc_no between", value1, value2, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNoNotBetween(String value1, String value2) {
            addCriterion("loc_no not between", value1, value2, "locNo");
            return (Criteria) this;
        }

        public Criteria andLocNameIsNull() {
            addCriterion("loc_name is null");
            return (Criteria) this;
        }

        public Criteria andLocNameIsNotNull() {
            addCriterion("loc_name is not null");
            return (Criteria) this;
        }

        public Criteria andLocNameEqualTo(String value) {
            addCriterion("loc_name =", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameNotEqualTo(String value) {
            addCriterion("loc_name <>", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameGreaterThan(String value) {
            addCriterion("loc_name >", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameGreaterThanOrEqualTo(String value) {
            addCriterion("loc_name >=", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameLessThan(String value) {
            addCriterion("loc_name <", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameLessThanOrEqualTo(String value) {
            addCriterion("loc_name <=", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameLike(String value) {
            addCriterion("loc_name like", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameNotLike(String value) {
            addCriterion("loc_name not like", value, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameIn(List<String> values) {
            addCriterion("loc_name in", values, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameNotIn(List<String> values) {
            addCriterion("loc_name not in", values, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameBetween(String value1, String value2) {
            addCriterion("loc_name between", value1, value2, "locName");
            return (Criteria) this;
        }

        public Criteria andLocNameNotBetween(String value1, String value2) {
            addCriterion("loc_name not between", value1, value2, "locName");
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

        public Criteria andLocStateIsNull() {
            addCriterion("loc_state is null");
            return (Criteria) this;
        }

        public Criteria andLocStateIsNotNull() {
            addCriterion("loc_state is not null");
            return (Criteria) this;
        }

        public Criteria andLocStateEqualTo(String value) {
            addCriterion("loc_state =", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateNotEqualTo(String value) {
            addCriterion("loc_state <>", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateGreaterThan(String value) {
            addCriterion("loc_state >", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateGreaterThanOrEqualTo(String value) {
            addCriterion("loc_state >=", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateLessThan(String value) {
            addCriterion("loc_state <", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateLessThanOrEqualTo(String value) {
            addCriterion("loc_state <=", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateLike(String value) {
            addCriterion("loc_state like", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateNotLike(String value) {
            addCriterion("loc_state not like", value, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateIn(List<String> values) {
            addCriterion("loc_state in", values, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateNotIn(List<String> values) {
            addCriterion("loc_state not in", values, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateBetween(String value1, String value2) {
            addCriterion("loc_state between", value1, value2, "locState");
            return (Criteria) this;
        }

        public Criteria andLocStateNotBetween(String value1, String value2) {
            addCriterion("loc_state not between", value1, value2, "locState");
            return (Criteria) this;
        }

        public Criteria andLocDescIsNull() {
            addCriterion("loc_desc is null");
            return (Criteria) this;
        }

        public Criteria andLocDescIsNotNull() {
            addCriterion("loc_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLocDescEqualTo(String value) {
            addCriterion("loc_desc =", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescNotEqualTo(String value) {
            addCriterion("loc_desc <>", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescGreaterThan(String value) {
            addCriterion("loc_desc >", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescGreaterThanOrEqualTo(String value) {
            addCriterion("loc_desc >=", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescLessThan(String value) {
            addCriterion("loc_desc <", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescLessThanOrEqualTo(String value) {
            addCriterion("loc_desc <=", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescLike(String value) {
            addCriterion("loc_desc like", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescNotLike(String value) {
            addCriterion("loc_desc not like", value, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescIn(List<String> values) {
            addCriterion("loc_desc in", values, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescNotIn(List<String> values) {
            addCriterion("loc_desc not in", values, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescBetween(String value1, String value2) {
            addCriterion("loc_desc between", value1, value2, "locDesc");
            return (Criteria) this;
        }

        public Criteria andLocDescNotBetween(String value1, String value2) {
            addCriterion("loc_desc not between", value1, value2, "locDesc");
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
     *  base_wh_loc
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
     *  base_wh_loc
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