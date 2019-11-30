package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustlevelExample {
    /**
     * orderByClausenull .
     *   crm_cust_level
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   crm_cust_level
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   crm_cust_level
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:CustlevelExample
     *   crm_cust_level
     *
     * @ET
     */
    public CustlevelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   crm_cust_level
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   crm_cust_level
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   crm_cust_level
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   crm_cust_level
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   crm_cust_level
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   crm_cust_level
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   crm_cust_level
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
     *   crm_cust_level
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
     *   crm_cust_level
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   crm_cust_level
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
     *  crm_cust_level
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

        public Criteria andLevelIdIsNull() {
            addCriterion("level_id is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("level_id is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("level_id =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("level_id <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("level_id >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_id >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("level_id <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("level_id <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("level_id in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("level_id not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("level_id between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("level_id not between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelNoIsNull() {
            addCriterion("level_no is null");
            return (Criteria) this;
        }

        public Criteria andLevelNoIsNotNull() {
            addCriterion("level_no is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNoEqualTo(String value) {
            addCriterion("level_no =", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoNotEqualTo(String value) {
            addCriterion("level_no <>", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoGreaterThan(String value) {
            addCriterion("level_no >", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoGreaterThanOrEqualTo(String value) {
            addCriterion("level_no >=", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoLessThan(String value) {
            addCriterion("level_no <", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoLessThanOrEqualTo(String value) {
            addCriterion("level_no <=", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoLike(String value) {
            addCriterion("level_no like", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoNotLike(String value) {
            addCriterion("level_no not like", value, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoIn(List<String> values) {
            addCriterion("level_no in", values, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoNotIn(List<String> values) {
            addCriterion("level_no not in", values, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoBetween(String value1, String value2) {
            addCriterion("level_no between", value1, value2, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNoNotBetween(String value1, String value2) {
            addCriterion("level_no not between", value1, value2, "levelNo");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andShowSortIsNull() {
            addCriterion("show_sort is null");
            return (Criteria) this;
        }

        public Criteria andShowSortIsNotNull() {
            addCriterion("show_sort is not null");
            return (Criteria) this;
        }

        public Criteria andShowSortEqualTo(Integer value) {
            addCriterion("show_sort =", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotEqualTo(Integer value) {
            addCriterion("show_sort <>", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortGreaterThan(Integer value) {
            addCriterion("show_sort >", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_sort >=", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortLessThan(Integer value) {
            addCriterion("show_sort <", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortLessThanOrEqualTo(Integer value) {
            addCriterion("show_sort <=", value, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortIn(List<Integer> values) {
            addCriterion("show_sort in", values, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotIn(List<Integer> values) {
            addCriterion("show_sort not in", values, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortBetween(Integer value1, Integer value2) {
            addCriterion("show_sort between", value1, value2, "showSort");
            return (Criteria) this;
        }

        public Criteria andShowSortNotBetween(Integer value1, Integer value2) {
            addCriterion("show_sort not between", value1, value2, "showSort");
            return (Criteria) this;
        }

        public Criteria andMinDisIsNull() {
            addCriterion("min_dis is null");
            return (Criteria) this;
        }

        public Criteria andMinDisIsNotNull() {
            addCriterion("min_dis is not null");
            return (Criteria) this;
        }

        public Criteria andMinDisEqualTo(Double value) {
            addCriterion("min_dis =", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisNotEqualTo(Double value) {
            addCriterion("min_dis <>", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisGreaterThan(Double value) {
            addCriterion("min_dis >", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisGreaterThanOrEqualTo(Double value) {
            addCriterion("min_dis >=", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisLessThan(Double value) {
            addCriterion("min_dis <", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisLessThanOrEqualTo(Double value) {
            addCriterion("min_dis <=", value, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisIn(List<Double> values) {
            addCriterion("min_dis in", values, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisNotIn(List<Double> values) {
            addCriterion("min_dis not in", values, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisBetween(Double value1, Double value2) {
            addCriterion("min_dis between", value1, value2, "minDis");
            return (Criteria) this;
        }

        public Criteria andMinDisNotBetween(Double value1, Double value2) {
            addCriterion("min_dis not between", value1, value2, "minDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisIsNull() {
            addCriterion("max_dis is null");
            return (Criteria) this;
        }

        public Criteria andMaxDisIsNotNull() {
            addCriterion("max_dis is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDisEqualTo(Double value) {
            addCriterion("max_dis =", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisNotEqualTo(Double value) {
            addCriterion("max_dis <>", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisGreaterThan(Double value) {
            addCriterion("max_dis >", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisGreaterThanOrEqualTo(Double value) {
            addCriterion("max_dis >=", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisLessThan(Double value) {
            addCriterion("max_dis <", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisLessThanOrEqualTo(Double value) {
            addCriterion("max_dis <=", value, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisIn(List<Double> values) {
            addCriterion("max_dis in", values, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisNotIn(List<Double> values) {
            addCriterion("max_dis not in", values, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisBetween(Double value1, Double value2) {
            addCriterion("max_dis between", value1, value2, "maxDis");
            return (Criteria) this;
        }

        public Criteria andMaxDisNotBetween(Double value1, Double value2) {
            addCriterion("max_dis not between", value1, value2, "maxDis");
            return (Criteria) this;
        }

        public Criteria andBackFlagIsNull() {
            addCriterion("back_flag is null");
            return (Criteria) this;
        }

        public Criteria andBackFlagIsNotNull() {
            addCriterion("back_flag is not null");
            return (Criteria) this;
        }

        public Criteria andBackFlagEqualTo(String value) {
            addCriterion("back_flag =", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotEqualTo(String value) {
            addCriterion("back_flag <>", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagGreaterThan(String value) {
            addCriterion("back_flag >", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagGreaterThanOrEqualTo(String value) {
            addCriterion("back_flag >=", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLessThan(String value) {
            addCriterion("back_flag <", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLessThanOrEqualTo(String value) {
            addCriterion("back_flag <=", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagLike(String value) {
            addCriterion("back_flag like", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotLike(String value) {
            addCriterion("back_flag not like", value, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagIn(List<String> values) {
            addCriterion("back_flag in", values, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotIn(List<String> values) {
            addCriterion("back_flag not in", values, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagBetween(String value1, String value2) {
            addCriterion("back_flag between", value1, value2, "backFlag");
            return (Criteria) this;
        }

        public Criteria andBackFlagNotBetween(String value1, String value2) {
            addCriterion("back_flag not between", value1, value2, "backFlag");
            return (Criteria) this;
        }

        public Criteria andMinBackIsNull() {
            addCriterion("min_back is null");
            return (Criteria) this;
        }

        public Criteria andMinBackIsNotNull() {
            addCriterion("min_back is not null");
            return (Criteria) this;
        }

        public Criteria andMinBackEqualTo(Double value) {
            addCriterion("min_back =", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackNotEqualTo(Double value) {
            addCriterion("min_back <>", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackGreaterThan(Double value) {
            addCriterion("min_back >", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackGreaterThanOrEqualTo(Double value) {
            addCriterion("min_back >=", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackLessThan(Double value) {
            addCriterion("min_back <", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackLessThanOrEqualTo(Double value) {
            addCriterion("min_back <=", value, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackIn(List<Double> values) {
            addCriterion("min_back in", values, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackNotIn(List<Double> values) {
            addCriterion("min_back not in", values, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackBetween(Double value1, Double value2) {
            addCriterion("min_back between", value1, value2, "minBack");
            return (Criteria) this;
        }

        public Criteria andMinBackNotBetween(Double value1, Double value2) {
            addCriterion("min_back not between", value1, value2, "minBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackIsNull() {
            addCriterion("max_back is null");
            return (Criteria) this;
        }

        public Criteria andMaxBackIsNotNull() {
            addCriterion("max_back is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBackEqualTo(Double value) {
            addCriterion("max_back =", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackNotEqualTo(Double value) {
            addCriterion("max_back <>", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackGreaterThan(Double value) {
            addCriterion("max_back >", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackGreaterThanOrEqualTo(Double value) {
            addCriterion("max_back >=", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackLessThan(Double value) {
            addCriterion("max_back <", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackLessThanOrEqualTo(Double value) {
            addCriterion("max_back <=", value, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackIn(List<Double> values) {
            addCriterion("max_back in", values, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackNotIn(List<Double> values) {
            addCriterion("max_back not in", values, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackBetween(Double value1, Double value2) {
            addCriterion("max_back between", value1, value2, "maxBack");
            return (Criteria) this;
        }

        public Criteria andMaxBackNotBetween(Double value1, Double value2) {
            addCriterion("max_back not between", value1, value2, "maxBack");
            return (Criteria) this;
        }

        public Criteria andLevelStateIsNull() {
            addCriterion("level_state is null");
            return (Criteria) this;
        }

        public Criteria andLevelStateIsNotNull() {
            addCriterion("level_state is not null");
            return (Criteria) this;
        }

        public Criteria andLevelStateEqualTo(String value) {
            addCriterion("level_state =", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateNotEqualTo(String value) {
            addCriterion("level_state <>", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateGreaterThan(String value) {
            addCriterion("level_state >", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateGreaterThanOrEqualTo(String value) {
            addCriterion("level_state >=", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateLessThan(String value) {
            addCriterion("level_state <", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateLessThanOrEqualTo(String value) {
            addCriterion("level_state <=", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateLike(String value) {
            addCriterion("level_state like", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateNotLike(String value) {
            addCriterion("level_state not like", value, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateIn(List<String> values) {
            addCriterion("level_state in", values, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateNotIn(List<String> values) {
            addCriterion("level_state not in", values, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateBetween(String value1, String value2) {
            addCriterion("level_state between", value1, value2, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelStateNotBetween(String value1, String value2) {
            addCriterion("level_state not between", value1, value2, "levelState");
            return (Criteria) this;
        }

        public Criteria andLevelDescIsNull() {
            addCriterion("level_desc is null");
            return (Criteria) this;
        }

        public Criteria andLevelDescIsNotNull() {
            addCriterion("level_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLevelDescEqualTo(String value) {
            addCriterion("level_desc =", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescNotEqualTo(String value) {
            addCriterion("level_desc <>", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescGreaterThan(String value) {
            addCriterion("level_desc >", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescGreaterThanOrEqualTo(String value) {
            addCriterion("level_desc >=", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescLessThan(String value) {
            addCriterion("level_desc <", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescLessThanOrEqualTo(String value) {
            addCriterion("level_desc <=", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescLike(String value) {
            addCriterion("level_desc like", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescNotLike(String value) {
            addCriterion("level_desc not like", value, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescIn(List<String> values) {
            addCriterion("level_desc in", values, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescNotIn(List<String> values) {
            addCriterion("level_desc not in", values, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescBetween(String value1, String value2) {
            addCriterion("level_desc between", value1, value2, "levelDesc");
            return (Criteria) this;
        }

        public Criteria andLevelDescNotBetween(String value1, String value2) {
            addCriterion("level_desc not between", value1, value2, "levelDesc");
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
     * This class:org.mybatis.generator.config.Context@b065c63
     *  crm_cust_level
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
     *  crm_cust_level
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