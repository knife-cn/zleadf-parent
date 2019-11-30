package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaMacketContExample {
    /**
     * orderByClausenull .
     *   oa_macket_cont
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_macket_cont
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_macket_cont
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaMacketContExample
     *   oa_macket_cont
     *
     * @ET
     */
    public OaMacketContExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_macket_cont
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_macket_cont
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_macket_cont
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_macket_cont
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_macket_cont
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_macket_cont
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_macket_cont
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
     *   oa_macket_cont
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
     *   oa_macket_cont
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_macket_cont
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
     *  oa_macket_cont
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

        public Criteria andContCodeIsNull() {
            addCriterion("cont_code is null");
            return (Criteria) this;
        }

        public Criteria andContCodeIsNotNull() {
            addCriterion("cont_code is not null");
            return (Criteria) this;
        }

        public Criteria andContCodeEqualTo(String value) {
            addCriterion("cont_code =", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeNotEqualTo(String value) {
            addCriterion("cont_code <>", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeGreaterThan(String value) {
            addCriterion("cont_code >", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cont_code >=", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeLessThan(String value) {
            addCriterion("cont_code <", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeLessThanOrEqualTo(String value) {
            addCriterion("cont_code <=", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeLike(String value) {
            addCriterion("cont_code like", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeNotLike(String value) {
            addCriterion("cont_code not like", value, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeIn(List<String> values) {
            addCriterion("cont_code in", values, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeNotIn(List<String> values) {
            addCriterion("cont_code not in", values, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeBetween(String value1, String value2) {
            addCriterion("cont_code between", value1, value2, "contCode");
            return (Criteria) this;
        }

        public Criteria andContCodeNotBetween(String value1, String value2) {
            addCriterion("cont_code not between", value1, value2, "contCode");
            return (Criteria) this;
        }

        public Criteria andContNameIsNull() {
            addCriterion("cont_name is null");
            return (Criteria) this;
        }

        public Criteria andContNameIsNotNull() {
            addCriterion("cont_name is not null");
            return (Criteria) this;
        }

        public Criteria andContNameEqualTo(String value) {
            addCriterion("cont_name =", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameNotEqualTo(String value) {
            addCriterion("cont_name <>", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameGreaterThan(String value) {
            addCriterion("cont_name >", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameGreaterThanOrEqualTo(String value) {
            addCriterion("cont_name >=", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameLessThan(String value) {
            addCriterion("cont_name <", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameLessThanOrEqualTo(String value) {
            addCriterion("cont_name <=", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameLike(String value) {
            addCriterion("cont_name like", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameNotLike(String value) {
            addCriterion("cont_name not like", value, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameIn(List<String> values) {
            addCriterion("cont_name in", values, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameNotIn(List<String> values) {
            addCriterion("cont_name not in", values, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameBetween(String value1, String value2) {
            addCriterion("cont_name between", value1, value2, "contName");
            return (Criteria) this;
        }

        public Criteria andContNameNotBetween(String value1, String value2) {
            addCriterion("cont_name not between", value1, value2, "contName");
            return (Criteria) this;
        }

        public Criteria andContTypeIsNull() {
            addCriterion("cont_type is null");
            return (Criteria) this;
        }

        public Criteria andContTypeIsNotNull() {
            addCriterion("cont_type is not null");
            return (Criteria) this;
        }

        public Criteria andContTypeEqualTo(String value) {
            addCriterion("cont_type =", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeNotEqualTo(String value) {
            addCriterion("cont_type <>", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeGreaterThan(String value) {
            addCriterion("cont_type >", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cont_type >=", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeLessThan(String value) {
            addCriterion("cont_type <", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeLessThanOrEqualTo(String value) {
            addCriterion("cont_type <=", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeLike(String value) {
            addCriterion("cont_type like", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeNotLike(String value) {
            addCriterion("cont_type not like", value, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeIn(List<String> values) {
            addCriterion("cont_type in", values, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeNotIn(List<String> values) {
            addCriterion("cont_type not in", values, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeBetween(String value1, String value2) {
            addCriterion("cont_type between", value1, value2, "contType");
            return (Criteria) this;
        }

        public Criteria andContTypeNotBetween(String value1, String value2) {
            addCriterion("cont_type not between", value1, value2, "contType");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNull() {
            addCriterion("eff_date is null");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNotNull() {
            addCriterion("eff_date is not null");
            return (Criteria) this;
        }

        public Criteria andEffDateEqualTo(Date value) {
            addCriterion("eff_date =", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotEqualTo(Date value) {
            addCriterion("eff_date <>", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThan(Date value) {
            addCriterion("eff_date >", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eff_date >=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThan(Date value) {
            addCriterion("eff_date <", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThanOrEqualTo(Date value) {
            addCriterion("eff_date <=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateIn(List<Date> values) {
            addCriterion("eff_date in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotIn(List<Date> values) {
            addCriterion("eff_date not in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateBetween(Date value1, Date value2) {
            addCriterion("eff_date between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotBetween(Date value1, Date value2) {
            addCriterion("eff_date not between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNull() {
            addCriterion("exp_date is null");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNotNull() {
            addCriterion("exp_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpDateEqualTo(Date value) {
            addCriterion("exp_date =", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotEqualTo(Date value) {
            addCriterion("exp_date <>", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThan(Date value) {
            addCriterion("exp_date >", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("exp_date >=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThan(Date value) {
            addCriterion("exp_date <", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThanOrEqualTo(Date value) {
            addCriterion("exp_date <=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIn(List<Date> values) {
            addCriterion("exp_date in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotIn(List<Date> values) {
            addCriterion("exp_date not in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateBetween(Date value1, Date value2) {
            addCriterion("exp_date between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotBetween(Date value1, Date value2) {
            addCriterion("exp_date not between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andTerminalIsNull() {
            addCriterion("terminal is null");
            return (Criteria) this;
        }

        public Criteria andTerminalIsNotNull() {
            addCriterion("terminal is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalEqualTo(String value) {
            addCriterion("terminal =", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotEqualTo(String value) {
            addCriterion("terminal <>", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalGreaterThan(String value) {
            addCriterion("terminal >", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalGreaterThanOrEqualTo(String value) {
            addCriterion("terminal >=", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLessThan(String value) {
            addCriterion("terminal <", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLessThanOrEqualTo(String value) {
            addCriterion("terminal <=", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalLike(String value) {
            addCriterion("terminal like", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotLike(String value) {
            addCriterion("terminal not like", value, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalIn(List<String> values) {
            addCriterion("terminal in", values, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotIn(List<String> values) {
            addCriterion("terminal not in", values, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalBetween(String value1, String value2) {
            addCriterion("terminal between", value1, value2, "terminal");
            return (Criteria) this;
        }

        public Criteria andTerminalNotBetween(String value1, String value2) {
            addCriterion("terminal not between", value1, value2, "terminal");
            return (Criteria) this;
        }

        public Criteria andContTitleIsNull() {
            addCriterion("cont_title is null");
            return (Criteria) this;
        }

        public Criteria andContTitleIsNotNull() {
            addCriterion("cont_title is not null");
            return (Criteria) this;
        }

        public Criteria andContTitleEqualTo(String value) {
            addCriterion("cont_title =", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleNotEqualTo(String value) {
            addCriterion("cont_title <>", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleGreaterThan(String value) {
            addCriterion("cont_title >", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleGreaterThanOrEqualTo(String value) {
            addCriterion("cont_title >=", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleLessThan(String value) {
            addCriterion("cont_title <", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleLessThanOrEqualTo(String value) {
            addCriterion("cont_title <=", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleLike(String value) {
            addCriterion("cont_title like", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleNotLike(String value) {
            addCriterion("cont_title not like", value, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleIn(List<String> values) {
            addCriterion("cont_title in", values, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleNotIn(List<String> values) {
            addCriterion("cont_title not in", values, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleBetween(String value1, String value2) {
            addCriterion("cont_title between", value1, value2, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContTitleNotBetween(String value1, String value2) {
            addCriterion("cont_title not between", value1, value2, "contTitle");
            return (Criteria) this;
        }

        public Criteria andContUrlIsNull() {
            addCriterion("cont_url is null");
            return (Criteria) this;
        }

        public Criteria andContUrlIsNotNull() {
            addCriterion("cont_url is not null");
            return (Criteria) this;
        }

        public Criteria andContUrlEqualTo(String value) {
            addCriterion("cont_url =", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlNotEqualTo(String value) {
            addCriterion("cont_url <>", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlGreaterThan(String value) {
            addCriterion("cont_url >", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlGreaterThanOrEqualTo(String value) {
            addCriterion("cont_url >=", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlLessThan(String value) {
            addCriterion("cont_url <", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlLessThanOrEqualTo(String value) {
            addCriterion("cont_url <=", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlLike(String value) {
            addCriterion("cont_url like", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlNotLike(String value) {
            addCriterion("cont_url not like", value, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlIn(List<String> values) {
            addCriterion("cont_url in", values, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlNotIn(List<String> values) {
            addCriterion("cont_url not in", values, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlBetween(String value1, String value2) {
            addCriterion("cont_url between", value1, value2, "contUrl");
            return (Criteria) this;
        }

        public Criteria andContUrlNotBetween(String value1, String value2) {
            addCriterion("cont_url not between", value1, value2, "contUrl");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushUserIsNull() {
            addCriterion("push_user is null");
            return (Criteria) this;
        }

        public Criteria andPushUserIsNotNull() {
            addCriterion("push_user is not null");
            return (Criteria) this;
        }

        public Criteria andPushUserEqualTo(Integer value) {
            addCriterion("push_user =", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserNotEqualTo(Integer value) {
            addCriterion("push_user <>", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserGreaterThan(Integer value) {
            addCriterion("push_user >", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_user >=", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserLessThan(Integer value) {
            addCriterion("push_user <", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserLessThanOrEqualTo(Integer value) {
            addCriterion("push_user <=", value, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserIn(List<Integer> values) {
            addCriterion("push_user in", values, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserNotIn(List<Integer> values) {
            addCriterion("push_user not in", values, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserBetween(Integer value1, Integer value2) {
            addCriterion("push_user between", value1, value2, "pushUser");
            return (Criteria) this;
        }

        public Criteria andPushUserNotBetween(Integer value1, Integer value2) {
            addCriterion("push_user not between", value1, value2, "pushUser");
            return (Criteria) this;
        }

        public Criteria andContStateIsNull() {
            addCriterion("cont_state is null");
            return (Criteria) this;
        }

        public Criteria andContStateIsNotNull() {
            addCriterion("cont_state is not null");
            return (Criteria) this;
        }

        public Criteria andContStateEqualTo(String value) {
            addCriterion("cont_state =", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateNotEqualTo(String value) {
            addCriterion("cont_state <>", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateGreaterThan(String value) {
            addCriterion("cont_state >", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateGreaterThanOrEqualTo(String value) {
            addCriterion("cont_state >=", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateLessThan(String value) {
            addCriterion("cont_state <", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateLessThanOrEqualTo(String value) {
            addCriterion("cont_state <=", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateLike(String value) {
            addCriterion("cont_state like", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateNotLike(String value) {
            addCriterion("cont_state not like", value, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateIn(List<String> values) {
            addCriterion("cont_state in", values, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateNotIn(List<String> values) {
            addCriterion("cont_state not in", values, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateBetween(String value1, String value2) {
            addCriterion("cont_state between", value1, value2, "contState");
            return (Criteria) this;
        }

        public Criteria andContStateNotBetween(String value1, String value2) {
            addCriterion("cont_state not between", value1, value2, "contState");
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
     *  oa_macket_cont
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
     *  oa_macket_cont
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