package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrmPrdListExample {
    /**
     * orderByClausenull .
     *   crm_prd_list
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   crm_prd_list
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   crm_prd_list
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:CrmPrdListExample
     *   crm_prd_list
     *
     * @ET
     */
    public CrmPrdListExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   crm_prd_list
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   crm_prd_list
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   crm_prd_list
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   crm_prd_list
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   crm_prd_list
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   crm_prd_list
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   crm_prd_list
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
     *   crm_prd_list
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
     *   crm_prd_list
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   crm_prd_list
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
     *  crm_prd_list
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

        public Criteria andListIdIsNull() {
            addCriterion("list_id is null");
            return (Criteria) this;
        }

        public Criteria andListIdIsNotNull() {
            addCriterion("list_id is not null");
            return (Criteria) this;
        }

        public Criteria andListIdEqualTo(Integer value) {
            addCriterion("list_id =", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotEqualTo(Integer value) {
            addCriterion("list_id <>", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThan(Integer value) {
            addCriterion("list_id >", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("list_id >=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThan(Integer value) {
            addCriterion("list_id <", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdLessThanOrEqualTo(Integer value) {
            addCriterion("list_id <=", value, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdIn(List<Integer> values) {
            addCriterion("list_id in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotIn(List<Integer> values) {
            addCriterion("list_id not in", values, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdBetween(Integer value1, Integer value2) {
            addCriterion("list_id between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andListIdNotBetween(Integer value1, Integer value2) {
            addCriterion("list_id not between", value1, value2, "listId");
            return (Criteria) this;
        }

        public Criteria andListNoIsNull() {
            addCriterion("list_no is null");
            return (Criteria) this;
        }

        public Criteria andListNoIsNotNull() {
            addCriterion("list_no is not null");
            return (Criteria) this;
        }

        public Criteria andListNoEqualTo(String value) {
            addCriterion("list_no =", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoNotEqualTo(String value) {
            addCriterion("list_no <>", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoGreaterThan(String value) {
            addCriterion("list_no >", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoGreaterThanOrEqualTo(String value) {
            addCriterion("list_no >=", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoLessThan(String value) {
            addCriterion("list_no <", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoLessThanOrEqualTo(String value) {
            addCriterion("list_no <=", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoLike(String value) {
            addCriterion("list_no like", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoNotLike(String value) {
            addCriterion("list_no not like", value, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoIn(List<String> values) {
            addCriterion("list_no in", values, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoNotIn(List<String> values) {
            addCriterion("list_no not in", values, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoBetween(String value1, String value2) {
            addCriterion("list_no between", value1, value2, "listNo");
            return (Criteria) this;
        }

        public Criteria andListNoNotBetween(String value1, String value2) {
            addCriterion("list_no not between", value1, value2, "listNo");
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

        public Criteria andCatIdsIsNull() {
            addCriterion("cat_ids is null");
            return (Criteria) this;
        }

        public Criteria andCatIdsIsNotNull() {
            addCriterion("cat_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdsEqualTo(String value) {
            addCriterion("cat_ids =", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotEqualTo(String value) {
            addCriterion("cat_ids <>", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThan(String value) {
            addCriterion("cat_ids >", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThanOrEqualTo(String value) {
            addCriterion("cat_ids >=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThan(String value) {
            addCriterion("cat_ids <", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThanOrEqualTo(String value) {
            addCriterion("cat_ids <=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLike(String value) {
            addCriterion("cat_ids like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotLike(String value) {
            addCriterion("cat_ids not like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsIn(List<String> values) {
            addCriterion("cat_ids in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotIn(List<String> values) {
            addCriterion("cat_ids not in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsBetween(String value1, String value2) {
            addCriterion("cat_ids between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotBetween(String value1, String value2) {
            addCriterion("cat_ids not between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatNamesIsNull() {
            addCriterion("cat_names is null");
            return (Criteria) this;
        }

        public Criteria andCatNamesIsNotNull() {
            addCriterion("cat_names is not null");
            return (Criteria) this;
        }

        public Criteria andCatNamesEqualTo(String value) {
            addCriterion("cat_names =", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesNotEqualTo(String value) {
            addCriterion("cat_names <>", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesGreaterThan(String value) {
            addCriterion("cat_names >", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesGreaterThanOrEqualTo(String value) {
            addCriterion("cat_names >=", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesLessThan(String value) {
            addCriterion("cat_names <", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesLessThanOrEqualTo(String value) {
            addCriterion("cat_names <=", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesLike(String value) {
            addCriterion("cat_names like", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesNotLike(String value) {
            addCriterion("cat_names not like", value, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesIn(List<String> values) {
            addCriterion("cat_names in", values, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesNotIn(List<String> values) {
            addCriterion("cat_names not in", values, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesBetween(String value1, String value2) {
            addCriterion("cat_names between", value1, value2, "catNames");
            return (Criteria) this;
        }

        public Criteria andCatNamesNotBetween(String value1, String value2) {
            addCriterion("cat_names not between", value1, value2, "catNames");
            return (Criteria) this;
        }

        public Criteria andPinyinIsNull() {
            addCriterion("pinyin is null");
            return (Criteria) this;
        }

        public Criteria andPinyinIsNotNull() {
            addCriterion("pinyin is not null");
            return (Criteria) this;
        }

        public Criteria andPinyinEqualTo(String value) {
            addCriterion("pinyin =", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotEqualTo(String value) {
            addCriterion("pinyin <>", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinGreaterThan(String value) {
            addCriterion("pinyin >", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinGreaterThanOrEqualTo(String value) {
            addCriterion("pinyin >=", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLessThan(String value) {
            addCriterion("pinyin <", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLessThanOrEqualTo(String value) {
            addCriterion("pinyin <=", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinLike(String value) {
            addCriterion("pinyin like", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotLike(String value) {
            addCriterion("pinyin not like", value, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinIn(List<String> values) {
            addCriterion("pinyin in", values, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotIn(List<String> values) {
            addCriterion("pinyin not in", values, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinBetween(String value1, String value2) {
            addCriterion("pinyin between", value1, value2, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinNotBetween(String value1, String value2) {
            addCriterion("pinyin not between", value1, value2, "pinyin");
            return (Criteria) this;
        }

        public Criteria andPinyinShIsNull() {
            addCriterion("pinyin_sh is null");
            return (Criteria) this;
        }

        public Criteria andPinyinShIsNotNull() {
            addCriterion("pinyin_sh is not null");
            return (Criteria) this;
        }

        public Criteria andPinyinShEqualTo(String value) {
            addCriterion("pinyin_sh =", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShNotEqualTo(String value) {
            addCriterion("pinyin_sh <>", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShGreaterThan(String value) {
            addCriterion("pinyin_sh >", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShGreaterThanOrEqualTo(String value) {
            addCriterion("pinyin_sh >=", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShLessThan(String value) {
            addCriterion("pinyin_sh <", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShLessThanOrEqualTo(String value) {
            addCriterion("pinyin_sh <=", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShLike(String value) {
            addCriterion("pinyin_sh like", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShNotLike(String value) {
            addCriterion("pinyin_sh not like", value, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShIn(List<String> values) {
            addCriterion("pinyin_sh in", values, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShNotIn(List<String> values) {
            addCriterion("pinyin_sh not in", values, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShBetween(String value1, String value2) {
            addCriterion("pinyin_sh between", value1, value2, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPinyinShNotBetween(String value1, String value2) {
            addCriterion("pinyin_sh not between", value1, value2, "pinyinSh");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNull() {
            addCriterion("pic_path is null");
            return (Criteria) this;
        }

        public Criteria andPicPathIsNotNull() {
            addCriterion("pic_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicPathEqualTo(String value) {
            addCriterion("pic_path =", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotEqualTo(String value) {
            addCriterion("pic_path <>", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThan(String value) {
            addCriterion("pic_path >", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathGreaterThanOrEqualTo(String value) {
            addCriterion("pic_path >=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThan(String value) {
            addCriterion("pic_path <", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLessThanOrEqualTo(String value) {
            addCriterion("pic_path <=", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathLike(String value) {
            addCriterion("pic_path like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotLike(String value) {
            addCriterion("pic_path not like", value, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathIn(List<String> values) {
            addCriterion("pic_path in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotIn(List<String> values) {
            addCriterion("pic_path not in", values, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathBetween(String value1, String value2) {
            addCriterion("pic_path between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andPicPathNotBetween(String value1, String value2) {
            addCriterion("pic_path not between", value1, value2, "picPath");
            return (Criteria) this;
        }

        public Criteria andListStateIsNull() {
            addCriterion("list_state is null");
            return (Criteria) this;
        }

        public Criteria andListStateIsNotNull() {
            addCriterion("list_state is not null");
            return (Criteria) this;
        }

        public Criteria andListStateEqualTo(String value) {
            addCriterion("list_state =", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateNotEqualTo(String value) {
            addCriterion("list_state <>", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateGreaterThan(String value) {
            addCriterion("list_state >", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateGreaterThanOrEqualTo(String value) {
            addCriterion("list_state >=", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateLessThan(String value) {
            addCriterion("list_state <", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateLessThanOrEqualTo(String value) {
            addCriterion("list_state <=", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateLike(String value) {
            addCriterion("list_state like", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateNotLike(String value) {
            addCriterion("list_state not like", value, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateIn(List<String> values) {
            addCriterion("list_state in", values, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateNotIn(List<String> values) {
            addCriterion("list_state not in", values, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateBetween(String value1, String value2) {
            addCriterion("list_state between", value1, value2, "listState");
            return (Criteria) this;
        }

        public Criteria andListStateNotBetween(String value1, String value2) {
            addCriterion("list_state not between", value1, value2, "listState");
            return (Criteria) this;
        }

        public Criteria andListDescIsNull() {
            addCriterion("list_desc is null");
            return (Criteria) this;
        }

        public Criteria andListDescIsNotNull() {
            addCriterion("list_desc is not null");
            return (Criteria) this;
        }

        public Criteria andListDescEqualTo(String value) {
            addCriterion("list_desc =", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescNotEqualTo(String value) {
            addCriterion("list_desc <>", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescGreaterThan(String value) {
            addCriterion("list_desc >", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescGreaterThanOrEqualTo(String value) {
            addCriterion("list_desc >=", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescLessThan(String value) {
            addCriterion("list_desc <", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescLessThanOrEqualTo(String value) {
            addCriterion("list_desc <=", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescLike(String value) {
            addCriterion("list_desc like", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescNotLike(String value) {
            addCriterion("list_desc not like", value, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescIn(List<String> values) {
            addCriterion("list_desc in", values, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescNotIn(List<String> values) {
            addCriterion("list_desc not in", values, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescBetween(String value1, String value2) {
            addCriterion("list_desc between", value1, value2, "listDesc");
            return (Criteria) this;
        }

        public Criteria andListDescNotBetween(String value1, String value2) {
            addCriterion("list_desc not between", value1, value2, "listDesc");
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
     *  crm_prd_list
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
     *  crm_prd_list
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