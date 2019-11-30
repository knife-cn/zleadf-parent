package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaFactoryInfoExample {
    /**
     * orderByClausenull .
     *   oa_factory_info
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_factory_info
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_factory_info
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaFactoryInfoExample
     *   oa_factory_info
     *
     * @ET
     */
    public OaFactoryInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_factory_info
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_factory_info
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_factory_info
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_factory_info
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_factory_info
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_factory_info
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_factory_info
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
     *   oa_factory_info
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
     *   oa_factory_info
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_factory_info
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
     *  oa_factory_info
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

        public Criteria andFactNoIsNull() {
            addCriterion("fact_no is null");
            return (Criteria) this;
        }

        public Criteria andFactNoIsNotNull() {
            addCriterion("fact_no is not null");
            return (Criteria) this;
        }

        public Criteria andFactNoEqualTo(String value) {
            addCriterion("fact_no =", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoNotEqualTo(String value) {
            addCriterion("fact_no <>", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoGreaterThan(String value) {
            addCriterion("fact_no >", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoGreaterThanOrEqualTo(String value) {
            addCriterion("fact_no >=", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoLessThan(String value) {
            addCriterion("fact_no <", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoLessThanOrEqualTo(String value) {
            addCriterion("fact_no <=", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoLike(String value) {
            addCriterion("fact_no like", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoNotLike(String value) {
            addCriterion("fact_no not like", value, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoIn(List<String> values) {
            addCriterion("fact_no in", values, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoNotIn(List<String> values) {
            addCriterion("fact_no not in", values, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoBetween(String value1, String value2) {
            addCriterion("fact_no between", value1, value2, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNoNotBetween(String value1, String value2) {
            addCriterion("fact_no not between", value1, value2, "factNo");
            return (Criteria) this;
        }

        public Criteria andFactNameIsNull() {
            addCriterion("fact_name is null");
            return (Criteria) this;
        }

        public Criteria andFactNameIsNotNull() {
            addCriterion("fact_name is not null");
            return (Criteria) this;
        }

        public Criteria andFactNameEqualTo(String value) {
            addCriterion("fact_name =", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameNotEqualTo(String value) {
            addCriterion("fact_name <>", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameGreaterThan(String value) {
            addCriterion("fact_name >", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameGreaterThanOrEqualTo(String value) {
            addCriterion("fact_name >=", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameLessThan(String value) {
            addCriterion("fact_name <", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameLessThanOrEqualTo(String value) {
            addCriterion("fact_name <=", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameLike(String value) {
            addCriterion("fact_name like", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameNotLike(String value) {
            addCriterion("fact_name not like", value, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameIn(List<String> values) {
            addCriterion("fact_name in", values, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameNotIn(List<String> values) {
            addCriterion("fact_name not in", values, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameBetween(String value1, String value2) {
            addCriterion("fact_name between", value1, value2, "factName");
            return (Criteria) this;
        }

        public Criteria andFactNameNotBetween(String value1, String value2) {
            addCriterion("fact_name not between", value1, value2, "factName");
            return (Criteria) this;
        }

        public Criteria andFactStateIsNull() {
            addCriterion("fact_state is null");
            return (Criteria) this;
        }

        public Criteria andFactStateIsNotNull() {
            addCriterion("fact_state is not null");
            return (Criteria) this;
        }

        public Criteria andFactStateEqualTo(String value) {
            addCriterion("fact_state =", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateNotEqualTo(String value) {
            addCriterion("fact_state <>", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateGreaterThan(String value) {
            addCriterion("fact_state >", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateGreaterThanOrEqualTo(String value) {
            addCriterion("fact_state >=", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateLessThan(String value) {
            addCriterion("fact_state <", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateLessThanOrEqualTo(String value) {
            addCriterion("fact_state <=", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateLike(String value) {
            addCriterion("fact_state like", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateNotLike(String value) {
            addCriterion("fact_state not like", value, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateIn(List<String> values) {
            addCriterion("fact_state in", values, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateNotIn(List<String> values) {
            addCriterion("fact_state not in", values, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateBetween(String value1, String value2) {
            addCriterion("fact_state between", value1, value2, "factState");
            return (Criteria) this;
        }

        public Criteria andFactStateNotBetween(String value1, String value2) {
            addCriterion("fact_state not between", value1, value2, "factState");
            return (Criteria) this;
        }

        public Criteria andContactNoIsNull() {
            addCriterion("contact_no is null");
            return (Criteria) this;
        }

        public Criteria andContactNoIsNotNull() {
            addCriterion("contact_no is not null");
            return (Criteria) this;
        }

        public Criteria andContactNoEqualTo(String value) {
            addCriterion("contact_no =", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotEqualTo(String value) {
            addCriterion("contact_no <>", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoGreaterThan(String value) {
            addCriterion("contact_no >", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoGreaterThanOrEqualTo(String value) {
            addCriterion("contact_no >=", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLessThan(String value) {
            addCriterion("contact_no <", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLessThanOrEqualTo(String value) {
            addCriterion("contact_no <=", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoLike(String value) {
            addCriterion("contact_no like", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotLike(String value) {
            addCriterion("contact_no not like", value, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoIn(List<String> values) {
            addCriterion("contact_no in", values, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotIn(List<String> values) {
            addCriterion("contact_no not in", values, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoBetween(String value1, String value2) {
            addCriterion("contact_no between", value1, value2, "contactNo");
            return (Criteria) this;
        }

        public Criteria andContactNoNotBetween(String value1, String value2) {
            addCriterion("contact_no not between", value1, value2, "contactNo");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andContactMan1IsNull() {
            addCriterion("contact_man1 is null");
            return (Criteria) this;
        }

        public Criteria andContactMan1IsNotNull() {
            addCriterion("contact_man1 is not null");
            return (Criteria) this;
        }

        public Criteria andContactMan1EqualTo(String value) {
            addCriterion("contact_man1 =", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1NotEqualTo(String value) {
            addCriterion("contact_man1 <>", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1GreaterThan(String value) {
            addCriterion("contact_man1 >", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1GreaterThanOrEqualTo(String value) {
            addCriterion("contact_man1 >=", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1LessThan(String value) {
            addCriterion("contact_man1 <", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1LessThanOrEqualTo(String value) {
            addCriterion("contact_man1 <=", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1Like(String value) {
            addCriterion("contact_man1 like", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1NotLike(String value) {
            addCriterion("contact_man1 not like", value, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1In(List<String> values) {
            addCriterion("contact_man1 in", values, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1NotIn(List<String> values) {
            addCriterion("contact_man1 not in", values, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1Between(String value1, String value2) {
            addCriterion("contact_man1 between", value1, value2, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan1NotBetween(String value1, String value2) {
            addCriterion("contact_man1 not between", value1, value2, "contactMan1");
            return (Criteria) this;
        }

        public Criteria andContactMan2IsNull() {
            addCriterion("contact_man2 is null");
            return (Criteria) this;
        }

        public Criteria andContactMan2IsNotNull() {
            addCriterion("contact_man2 is not null");
            return (Criteria) this;
        }

        public Criteria andContactMan2EqualTo(String value) {
            addCriterion("contact_man2 =", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2NotEqualTo(String value) {
            addCriterion("contact_man2 <>", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2GreaterThan(String value) {
            addCriterion("contact_man2 >", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2GreaterThanOrEqualTo(String value) {
            addCriterion("contact_man2 >=", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2LessThan(String value) {
            addCriterion("contact_man2 <", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2LessThanOrEqualTo(String value) {
            addCriterion("contact_man2 <=", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2Like(String value) {
            addCriterion("contact_man2 like", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2NotLike(String value) {
            addCriterion("contact_man2 not like", value, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2In(List<String> values) {
            addCriterion("contact_man2 in", values, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2NotIn(List<String> values) {
            addCriterion("contact_man2 not in", values, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2Between(String value1, String value2) {
            addCriterion("contact_man2 between", value1, value2, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andContactMan2NotBetween(String value1, String value2) {
            addCriterion("contact_man2 not between", value1, value2, "contactMan2");
            return (Criteria) this;
        }

        public Criteria andFactDescIsNull() {
            addCriterion("fact_desc is null");
            return (Criteria) this;
        }

        public Criteria andFactDescIsNotNull() {
            addCriterion("fact_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFactDescEqualTo(String value) {
            addCriterion("fact_desc =", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescNotEqualTo(String value) {
            addCriterion("fact_desc <>", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescGreaterThan(String value) {
            addCriterion("fact_desc >", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescGreaterThanOrEqualTo(String value) {
            addCriterion("fact_desc >=", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescLessThan(String value) {
            addCriterion("fact_desc <", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescLessThanOrEqualTo(String value) {
            addCriterion("fact_desc <=", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescLike(String value) {
            addCriterion("fact_desc like", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescNotLike(String value) {
            addCriterion("fact_desc not like", value, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescIn(List<String> values) {
            addCriterion("fact_desc in", values, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescNotIn(List<String> values) {
            addCriterion("fact_desc not in", values, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescBetween(String value1, String value2) {
            addCriterion("fact_desc between", value1, value2, "factDesc");
            return (Criteria) this;
        }

        public Criteria andFactDescNotBetween(String value1, String value2) {
            addCriterion("fact_desc not between", value1, value2, "factDesc");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathIsNull() {
            addCriterion("powerpic_path is null");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathIsNotNull() {
            addCriterion("powerpic_path is not null");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathEqualTo(String value) {
            addCriterion("powerpic_path =", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathNotEqualTo(String value) {
            addCriterion("powerpic_path <>", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathGreaterThan(String value) {
            addCriterion("powerpic_path >", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathGreaterThanOrEqualTo(String value) {
            addCriterion("powerpic_path >=", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathLessThan(String value) {
            addCriterion("powerpic_path <", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathLessThanOrEqualTo(String value) {
            addCriterion("powerpic_path <=", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathLike(String value) {
            addCriterion("powerpic_path like", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathNotLike(String value) {
            addCriterion("powerpic_path not like", value, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathIn(List<String> values) {
            addCriterion("powerpic_path in", values, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathNotIn(List<String> values) {
            addCriterion("powerpic_path not in", values, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathBetween(String value1, String value2) {
            addCriterion("powerpic_path between", value1, value2, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andPowerpicPathNotBetween(String value1, String value2) {
            addCriterion("powerpic_path not between", value1, value2, "powerpicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathIsNull() {
            addCriterion("apapic_path is null");
            return (Criteria) this;
        }

        public Criteria andApapicPathIsNotNull() {
            addCriterion("apapic_path is not null");
            return (Criteria) this;
        }

        public Criteria andApapicPathEqualTo(String value) {
            addCriterion("apapic_path =", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathNotEqualTo(String value) {
            addCriterion("apapic_path <>", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathGreaterThan(String value) {
            addCriterion("apapic_path >", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathGreaterThanOrEqualTo(String value) {
            addCriterion("apapic_path >=", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathLessThan(String value) {
            addCriterion("apapic_path <", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathLessThanOrEqualTo(String value) {
            addCriterion("apapic_path <=", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathLike(String value) {
            addCriterion("apapic_path like", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathNotLike(String value) {
            addCriterion("apapic_path not like", value, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathIn(List<String> values) {
            addCriterion("apapic_path in", values, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathNotIn(List<String> values) {
            addCriterion("apapic_path not in", values, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathBetween(String value1, String value2) {
            addCriterion("apapic_path between", value1, value2, "apapicPath");
            return (Criteria) this;
        }

        public Criteria andApapicPathNotBetween(String value1, String value2) {
            addCriterion("apapic_path not between", value1, value2, "apapicPath");
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
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  oa_factory_info
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
     *  oa_factory_info
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