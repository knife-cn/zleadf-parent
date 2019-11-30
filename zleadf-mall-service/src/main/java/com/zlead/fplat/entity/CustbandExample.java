package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustbandExample {
    /**
     * orderByClausenull .
     *   crm_cust_band
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   crm_cust_band
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   crm_cust_band
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:CustbandExample
     *   crm_cust_band
     *
     * @ET
     */
    public CustbandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   crm_cust_band
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   crm_cust_band
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   crm_cust_band
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   crm_cust_band
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   crm_cust_band
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   crm_cust_band
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   crm_cust_band
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
     *   crm_cust_band
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
     *   crm_cust_band
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   crm_cust_band
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
     *  crm_cust_band
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

        public Criteria andBandNoIsNull() {
            addCriterion("band_no is null");
            return (Criteria) this;
        }

        public Criteria andBandNoIsNotNull() {
            addCriterion("band_no is not null");
            return (Criteria) this;
        }

        public Criteria andBandNoEqualTo(String value) {
            addCriterion("band_no =", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoNotEqualTo(String value) {
            addCriterion("band_no <>", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoGreaterThan(String value) {
            addCriterion("band_no >", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoGreaterThanOrEqualTo(String value) {
            addCriterion("band_no >=", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoLessThan(String value) {
            addCriterion("band_no <", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoLessThanOrEqualTo(String value) {
            addCriterion("band_no <=", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoLike(String value) {
            addCriterion("band_no like", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoNotLike(String value) {
            addCriterion("band_no not like", value, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoIn(List<String> values) {
            addCriterion("band_no in", values, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoNotIn(List<String> values) {
            addCriterion("band_no not in", values, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoBetween(String value1, String value2) {
            addCriterion("band_no between", value1, value2, "bandNo");
            return (Criteria) this;
        }

        public Criteria andBandNoNotBetween(String value1, String value2) {
            addCriterion("band_no not between", value1, value2, "bandNo");
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

        public Criteria andBshortNameIsNull() {
            addCriterion("bshort_name is null");
            return (Criteria) this;
        }

        public Criteria andBshortNameIsNotNull() {
            addCriterion("bshort_name is not null");
            return (Criteria) this;
        }

        public Criteria andBshortNameEqualTo(String value) {
            addCriterion("bshort_name =", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameNotEqualTo(String value) {
            addCriterion("bshort_name <>", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameGreaterThan(String value) {
            addCriterion("bshort_name >", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameGreaterThanOrEqualTo(String value) {
            addCriterion("bshort_name >=", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameLessThan(String value) {
            addCriterion("bshort_name <", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameLessThanOrEqualTo(String value) {
            addCriterion("bshort_name <=", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameLike(String value) {
            addCriterion("bshort_name like", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameNotLike(String value) {
            addCriterion("bshort_name not like", value, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameIn(List<String> values) {
            addCriterion("bshort_name in", values, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameNotIn(List<String> values) {
            addCriterion("bshort_name not in", values, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameBetween(String value1, String value2) {
            addCriterion("bshort_name between", value1, value2, "bshortName");
            return (Criteria) this;
        }

        public Criteria andBshortNameNotBetween(String value1, String value2) {
            addCriterion("bshort_name not between", value1, value2, "bshortName");
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

        public Criteria andBandStateIsNull() {
            addCriterion("band_state is null");
            return (Criteria) this;
        }

        public Criteria andBandStateIsNotNull() {
            addCriterion("band_state is not null");
            return (Criteria) this;
        }

        public Criteria andBandStateEqualTo(String value) {
            addCriterion("band_state =", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateNotEqualTo(String value) {
            addCriterion("band_state <>", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateGreaterThan(String value) {
            addCriterion("band_state >", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateGreaterThanOrEqualTo(String value) {
            addCriterion("band_state >=", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateLessThan(String value) {
            addCriterion("band_state <", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateLessThanOrEqualTo(String value) {
            addCriterion("band_state <=", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateLike(String value) {
            addCriterion("band_state like", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateNotLike(String value) {
            addCriterion("band_state not like", value, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateIn(List<String> values) {
            addCriterion("band_state in", values, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateNotIn(List<String> values) {
            addCriterion("band_state not in", values, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateBetween(String value1, String value2) {
            addCriterion("band_state between", value1, value2, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandStateNotBetween(String value1, String value2) {
            addCriterion("band_state not between", value1, value2, "bandState");
            return (Criteria) this;
        }

        public Criteria andBandDescIsNull() {
            addCriterion("band_desc is null");
            return (Criteria) this;
        }

        public Criteria andBandDescIsNotNull() {
            addCriterion("band_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBandDescEqualTo(String value) {
            addCriterion("band_desc =", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescNotEqualTo(String value) {
            addCriterion("band_desc <>", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescGreaterThan(String value) {
            addCriterion("band_desc >", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescGreaterThanOrEqualTo(String value) {
            addCriterion("band_desc >=", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescLessThan(String value) {
            addCriterion("band_desc <", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescLessThanOrEqualTo(String value) {
            addCriterion("band_desc <=", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescLike(String value) {
            addCriterion("band_desc like", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescNotLike(String value) {
            addCriterion("band_desc not like", value, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescIn(List<String> values) {
            addCriterion("band_desc in", values, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescNotIn(List<String> values) {
            addCriterion("band_desc not in", values, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescBetween(String value1, String value2) {
            addCriterion("band_desc between", value1, value2, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andBandDescNotBetween(String value1, String value2) {
            addCriterion("band_desc not between", value1, value2, "bandDesc");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
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
     *  crm_cust_band
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
     *  crm_cust_band
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