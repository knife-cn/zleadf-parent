package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.List;

public class ProdattrvalExample {
    /**
     * orderByClausenull .
     *   t_prod_attr_val
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   t_prod_attr_val
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   t_prod_attr_val
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:ProdattrvalExample
     *   t_prod_attr_val
     *
     * @ET
     */
    public ProdattrvalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   t_prod_attr_val
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   t_prod_attr_val
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   t_prod_attr_val
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   t_prod_attr_val
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   t_prod_attr_val
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   t_prod_attr_val
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   t_prod_attr_val
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
     *   t_prod_attr_val
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
     *   t_prod_attr_val
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   t_prod_attr_val
     *
     * @ET
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class:org.mybatis.generator.config.Context@5f5a92bb
     *  t_prod_attr_val
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNull() {
            addCriterion("value_id is null");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNotNull() {
            addCriterion("value_id is not null");
            return (Criteria) this;
        }

        public Criteria andValueIdEqualTo(Integer value) {
            addCriterion("value_id =", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotEqualTo(Integer value) {
            addCriterion("value_id <>", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThan(Integer value) {
            addCriterion("value_id >", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("value_id >=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThan(Integer value) {
            addCriterion("value_id <", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThanOrEqualTo(Integer value) {
            addCriterion("value_id <=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdIn(List<Integer> values) {
            addCriterion("value_id in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotIn(List<Integer> values) {
            addCriterion("value_id not in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdBetween(Integer value1, Integer value2) {
            addCriterion("value_id between", value1, value2, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("value_id not between", value1, value2, "valueId");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNull() {
            addCriterion("prod_id is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(Integer value) {
            addCriterion("prod_id =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(Integer value) {
            addCriterion("prod_id <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(Integer value) {
            addCriterion("prod_id >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_id >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(Integer value) {
            addCriterion("prod_id <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(Integer value) {
            addCriterion("prod_id <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<Integer> values) {
            addCriterion("prod_id in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<Integer> values) {
            addCriterion("prod_id not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(Integer value1, Integer value2) {
            addCriterion("prod_id between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_id not between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNull() {
            addCriterion("attr_id is null");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNotNull() {
            addCriterion("attr_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttrIdEqualTo(Integer value) {
            addCriterion("attr_id =", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotEqualTo(Integer value) {
            addCriterion("attr_id <>", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThan(Integer value) {
            addCriterion("attr_id >", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attr_id >=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThan(Integer value) {
            addCriterion("attr_id <", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThanOrEqualTo(Integer value) {
            addCriterion("attr_id <=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIn(List<Integer> values) {
            addCriterion("attr_id in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotIn(List<Integer> values) {
            addCriterion("attr_id not in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdBetween(Integer value1, Integer value2) {
            addCriterion("attr_id between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attr_id not between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNull() {
            addCriterion("attr_name is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("attr_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("attr_name =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("attr_name <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("attr_name >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("attr_name >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("attr_name <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("attr_name <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("attr_name like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("attr_name not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("attr_name in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("attr_name not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("attr_name between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("attr_name not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andValueNameIsNull() {
            addCriterion("value_name is null");
            return (Criteria) this;
        }

        public Criteria andValueNameIsNotNull() {
            addCriterion("value_name is not null");
            return (Criteria) this;
        }

        public Criteria andValueNameEqualTo(String value) {
            addCriterion("value_name =", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameNotEqualTo(String value) {
            addCriterion("value_name <>", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameGreaterThan(String value) {
            addCriterion("value_name >", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameGreaterThanOrEqualTo(String value) {
            addCriterion("value_name >=", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameLessThan(String value) {
            addCriterion("value_name <", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameLessThanOrEqualTo(String value) {
            addCriterion("value_name <=", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameLike(String value) {
            addCriterion("value_name like", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameNotLike(String value) {
            addCriterion("value_name not like", value, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameIn(List<String> values) {
            addCriterion("value_name in", values, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameNotIn(List<String> values) {
            addCriterion("value_name not in", values, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameBetween(String value1, String value2) {
            addCriterion("value_name between", value1, value2, "valueName");
            return (Criteria) this;
        }

        public Criteria andValueNameNotBetween(String value1, String value2) {
            addCriterion("value_name not between", value1, value2, "valueName");
            return (Criteria) this;
        }

        public Criteria andLineSortIsNull() {
            addCriterion("line_sort is null");
            return (Criteria) this;
        }

        public Criteria andLineSortIsNotNull() {
            addCriterion("line_sort is not null");
            return (Criteria) this;
        }

        public Criteria andLineSortEqualTo(Integer value) {
            addCriterion("line_sort =", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortNotEqualTo(Integer value) {
            addCriterion("line_sort <>", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortGreaterThan(Integer value) {
            addCriterion("line_sort >", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_sort >=", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortLessThan(Integer value) {
            addCriterion("line_sort <", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortLessThanOrEqualTo(Integer value) {
            addCriterion("line_sort <=", value, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortIn(List<Integer> values) {
            addCriterion("line_sort in", values, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortNotIn(List<Integer> values) {
            addCriterion("line_sort not in", values, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortBetween(Integer value1, Integer value2) {
            addCriterion("line_sort between", value1, value2, "lineSort");
            return (Criteria) this;
        }

        public Criteria andLineSortNotBetween(Integer value1, Integer value2) {
            addCriterion("line_sort not between", value1, value2, "lineSort");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@5f5a92bb
     *  t_prod_attr_val
     *
     * @ET do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@5f5a92bb
     *  t_prod_attr_val
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