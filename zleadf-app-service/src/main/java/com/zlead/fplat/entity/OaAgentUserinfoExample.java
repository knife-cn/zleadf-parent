package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.List;

public class OaAgentUserinfoExample {
    /**
     * orderByClausenull .
     *   oa_agent_userinfo
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_agent_userinfo
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_agent_userinfo
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaAgentUserinfoExample
     *   oa_agent_userinfo
     *
     * @ET
     */
    public OaAgentUserinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_agent_userinfo
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_agent_userinfo
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_agent_userinfo
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_agent_userinfo
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_agent_userinfo
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_agent_userinfo
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_agent_userinfo
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
     *   oa_agent_userinfo
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
     *   oa_agent_userinfo
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_agent_userinfo
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
     *  oa_agent_userinfo
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andMainUserIsNull() {
            addCriterion("main_user is null");
            return (Criteria) this;
        }

        public Criteria andMainUserIsNotNull() {
            addCriterion("main_user is not null");
            return (Criteria) this;
        }

        public Criteria andMainUserEqualTo(String value) {
            addCriterion("main_user =", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserNotEqualTo(String value) {
            addCriterion("main_user <>", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserGreaterThan(String value) {
            addCriterion("main_user >", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserGreaterThanOrEqualTo(String value) {
            addCriterion("main_user >=", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserLessThan(String value) {
            addCriterion("main_user <", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserLessThanOrEqualTo(String value) {
            addCriterion("main_user <=", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserLike(String value) {
            addCriterion("main_user like", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserNotLike(String value) {
            addCriterion("main_user not like", value, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserIn(List<String> values) {
            addCriterion("main_user in", values, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserNotIn(List<String> values) {
            addCriterion("main_user not in", values, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserBetween(String value1, String value2) {
            addCriterion("main_user between", value1, value2, "mainUser");
            return (Criteria) this;
        }

        public Criteria andMainUserNotBetween(String value1, String value2) {
            addCriterion("main_user not between", value1, value2, "mainUser");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_agent_userinfo
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
     *  oa_agent_userinfo
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