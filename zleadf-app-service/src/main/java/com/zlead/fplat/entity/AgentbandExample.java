package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.List;

public class AgentbandExample {
    /**
     * orderByClausenull .
     *   oa_agent_band
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_agent_band
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_agent_band
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:AgentbandExample
     *   oa_agent_band
     *
     * @ET
     */
    public AgentbandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_agent_band
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_agent_band
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_agent_band
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_agent_band
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_agent_band
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_agent_band
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_agent_band
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
     *   oa_agent_band
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
     *   oa_agent_band
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_agent_band
     *
     * @ET
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class:org.mybatis.generator.config.Context@b065c63
     *  oa_agent_band
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andBandIdIsNull() {
            addCriterion("band_id is null");
            return (Criteria) this;
        }

        public Criteria andBandIdIsNotNull() {
            addCriterion("band_id is not null");
            return (Criteria) this;
        }

        public Criteria andBandIdEqualTo(Integer value) {
            addCriterion("band_id =", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdNotEqualTo(Integer value) {
            addCriterion("band_id <>", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdGreaterThan(Integer value) {
            addCriterion("band_id >", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("band_id >=", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdLessThan(Integer value) {
            addCriterion("band_id <", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdLessThanOrEqualTo(Integer value) {
            addCriterion("band_id <=", value, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdIn(List<Integer> values) {
            addCriterion("band_id in", values, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdNotIn(List<Integer> values) {
            addCriterion("band_id not in", values, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdBetween(Integer value1, Integer value2) {
            addCriterion("band_id between", value1, value2, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("band_id not between", value1, value2, "bandId");
            return (Criteria) this;
        }

        public Criteria andBandNameIsNull() {
            addCriterion("band_name is null");
            return (Criteria) this;
        }

        public Criteria andBandNameIsNotNull() {
            addCriterion("band_name is not null");
            return (Criteria) this;
        }

        public Criteria andBandNameEqualTo(String value) {
            addCriterion("band_name =", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameNotEqualTo(String value) {
            addCriterion("band_name <>", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameGreaterThan(String value) {
            addCriterion("band_name >", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameGreaterThanOrEqualTo(String value) {
            addCriterion("band_name >=", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameLessThan(String value) {
            addCriterion("band_name <", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameLessThanOrEqualTo(String value) {
            addCriterion("band_name <=", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameLike(String value) {
            addCriterion("band_name like", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameNotLike(String value) {
            addCriterion("band_name not like", value, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameIn(List<String> values) {
            addCriterion("band_name in", values, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameNotIn(List<String> values) {
            addCriterion("band_name not in", values, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameBetween(String value1, String value2) {
            addCriterion("band_name between", value1, value2, "bandName");
            return (Criteria) this;
        }

        public Criteria andBandNameNotBetween(String value1, String value2) {
            addCriterion("band_name not between", value1, value2, "bandName");
            return (Criteria) this;
        }

        public Criteria andListNameIsNull() {
            addCriterion("list_name is null");
            return (Criteria) this;
        }

        public Criteria andListNameIsNotNull() {
            addCriterion("list_name is not null");
            return (Criteria) this;
        }

        public Criteria andListNameEqualTo(String value) {
            addCriterion("list_name =", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotEqualTo(String value) {
            addCriterion("list_name <>", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThan(String value) {
            addCriterion("list_name >", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameGreaterThanOrEqualTo(String value) {
            addCriterion("list_name >=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThan(String value) {
            addCriterion("list_name <", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLessThanOrEqualTo(String value) {
            addCriterion("list_name <=", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameLike(String value) {
            addCriterion("list_name like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotLike(String value) {
            addCriterion("list_name not like", value, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameIn(List<String> values) {
            addCriterion("list_name in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotIn(List<String> values) {
            addCriterion("list_name not in", values, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameBetween(String value1, String value2) {
            addCriterion("list_name between", value1, value2, "listName");
            return (Criteria) this;
        }

        public Criteria andListNameNotBetween(String value1, String value2) {
            addCriterion("list_name not between", value1, value2, "listName");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@b065c63
     *  oa_agent_band
     *
     * @ET do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@b065c63
     *  oa_agent_band
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