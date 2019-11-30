package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaFactoryDprocExample {
    /**
     * orderByClausenull .
     *   oa_factory_dproc
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_factory_dproc
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_factory_dproc
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaFactoryDprocExample
     *   oa_factory_dproc
     *
     * @ET
     */
    public OaFactoryDprocExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_factory_dproc
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_factory_dproc
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_factory_dproc
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_factory_dproc
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_factory_dproc
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_factory_dproc
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_factory_dproc
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
     *   oa_factory_dproc
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
     *   oa_factory_dproc
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_factory_dproc
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
     *  oa_factory_dproc
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

        public Criteria andDprocIdIsNull() {
            addCriterion("dproc_id is null");
            return (Criteria) this;
        }

        public Criteria andDprocIdIsNotNull() {
            addCriterion("dproc_id is not null");
            return (Criteria) this;
        }

        public Criteria andDprocIdEqualTo(Integer value) {
            addCriterion("dproc_id =", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdNotEqualTo(Integer value) {
            addCriterion("dproc_id <>", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdGreaterThan(Integer value) {
            addCriterion("dproc_id >", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dproc_id >=", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdLessThan(Integer value) {
            addCriterion("dproc_id <", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdLessThanOrEqualTo(Integer value) {
            addCriterion("dproc_id <=", value, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdIn(List<Integer> values) {
            addCriterion("dproc_id in", values, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdNotIn(List<Integer> values) {
            addCriterion("dproc_id not in", values, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdBetween(Integer value1, Integer value2) {
            addCriterion("dproc_id between", value1, value2, "dprocId");
            return (Criteria) this;
        }

        public Criteria andDprocIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dproc_id not between", value1, value2, "dprocId");
            return (Criteria) this;
        }

        public Criteria andFactIdIsNull() {
            addCriterion("fact_id is null");
            return (Criteria) this;
        }

        public Criteria andFactIdIsNotNull() {
            addCriterion("fact_id is not null");
            return (Criteria) this;
        }

        public Criteria andFactIdEqualTo(Integer value) {
            addCriterion("fact_id =", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdNotEqualTo(Integer value) {
            addCriterion("fact_id <>", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdGreaterThan(Integer value) {
            addCriterion("fact_id >", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fact_id >=", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdLessThan(Integer value) {
            addCriterion("fact_id <", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdLessThanOrEqualTo(Integer value) {
            addCriterion("fact_id <=", value, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdIn(List<Integer> values) {
            addCriterion("fact_id in", values, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdNotIn(List<Integer> values) {
            addCriterion("fact_id not in", values, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdBetween(Integer value1, Integer value2) {
            addCriterion("fact_id between", value1, value2, "factId");
            return (Criteria) this;
        }

        public Criteria andFactIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fact_id not between", value1, value2, "factId");
            return (Criteria) this;
        }

        public Criteria andSortNumIsNull() {
            addCriterion("sort_num is null");
            return (Criteria) this;
        }

        public Criteria andSortNumIsNotNull() {
            addCriterion("sort_num is not null");
            return (Criteria) this;
        }

        public Criteria andSortNumEqualTo(Integer value) {
            addCriterion("sort_num =", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotEqualTo(Integer value) {
            addCriterion("sort_num <>", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumGreaterThan(Integer value) {
            addCriterion("sort_num >", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort_num >=", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumLessThan(Integer value) {
            addCriterion("sort_num <", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumLessThanOrEqualTo(Integer value) {
            addCriterion("sort_num <=", value, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumIn(List<Integer> values) {
            addCriterion("sort_num in", values, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotIn(List<Integer> values) {
            addCriterion("sort_num not in", values, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumBetween(Integer value1, Integer value2) {
            addCriterion("sort_num between", value1, value2, "sortNum");
            return (Criteria) this;
        }

        public Criteria andSortNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sort_num not between", value1, value2, "sortNum");
            return (Criteria) this;
        }

        public Criteria andDprocNameIsNull() {
            addCriterion("dproc_name is null");
            return (Criteria) this;
        }

        public Criteria andDprocNameIsNotNull() {
            addCriterion("dproc_name is not null");
            return (Criteria) this;
        }

        public Criteria andDprocNameEqualTo(String value) {
            addCriterion("dproc_name =", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameNotEqualTo(String value) {
            addCriterion("dproc_name <>", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameGreaterThan(String value) {
            addCriterion("dproc_name >", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameGreaterThanOrEqualTo(String value) {
            addCriterion("dproc_name >=", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameLessThan(String value) {
            addCriterion("dproc_name <", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameLessThanOrEqualTo(String value) {
            addCriterion("dproc_name <=", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameLike(String value) {
            addCriterion("dproc_name like", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameNotLike(String value) {
            addCriterion("dproc_name not like", value, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameIn(List<String> values) {
            addCriterion("dproc_name in", values, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameNotIn(List<String> values) {
            addCriterion("dproc_name not in", values, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameBetween(String value1, String value2) {
            addCriterion("dproc_name between", value1, value2, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocNameNotBetween(String value1, String value2) {
            addCriterion("dproc_name not between", value1, value2, "dprocName");
            return (Criteria) this;
        }

        public Criteria andDprocDescIsNull() {
            addCriterion("dproc_desc is null");
            return (Criteria) this;
        }

        public Criteria andDprocDescIsNotNull() {
            addCriterion("dproc_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDprocDescEqualTo(String value) {
            addCriterion("dproc_desc =", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescNotEqualTo(String value) {
            addCriterion("dproc_desc <>", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescGreaterThan(String value) {
            addCriterion("dproc_desc >", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescGreaterThanOrEqualTo(String value) {
            addCriterion("dproc_desc >=", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescLessThan(String value) {
            addCriterion("dproc_desc <", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescLessThanOrEqualTo(String value) {
            addCriterion("dproc_desc <=", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescLike(String value) {
            addCriterion("dproc_desc like", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescNotLike(String value) {
            addCriterion("dproc_desc not like", value, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescIn(List<String> values) {
            addCriterion("dproc_desc in", values, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescNotIn(List<String> values) {
            addCriterion("dproc_desc not in", values, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescBetween(String value1, String value2) {
            addCriterion("dproc_desc between", value1, value2, "dprocDesc");
            return (Criteria) this;
        }

        public Criteria andDprocDescNotBetween(String value1, String value2) {
            addCriterion("dproc_desc not between", value1, value2, "dprocDesc");
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
     *  oa_factory_dproc
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
     *  oa_factory_dproc
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