package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdcatExample {
    /**
     * orderByClausenull .
     *   crm_prd_cat
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   crm_prd_cat
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   crm_prd_cat
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:ProdcatExample
     *   crm_prd_cat
     *
     * @ET
     */
    public ProdcatExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   crm_prd_cat
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   crm_prd_cat
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   crm_prd_cat
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   crm_prd_cat
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   crm_prd_cat
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   crm_prd_cat
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   crm_prd_cat
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
     *   crm_prd_cat
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
     *   crm_prd_cat
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   crm_prd_cat
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
     *  crm_prd_cat
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

        public Criteria andCatIdIsNull() {
            addCriterion("cat_id is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("cat_id =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("cat_id <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("cat_id >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_id >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("cat_id <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("cat_id <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("cat_id in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("cat_id not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("cat_id between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_id not between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatNoIsNull() {
            addCriterion("cat_no is null");
            return (Criteria) this;
        }

        public Criteria andCatNoIsNotNull() {
            addCriterion("cat_no is not null");
            return (Criteria) this;
        }

        public Criteria andCatNoEqualTo(String value) {
            addCriterion("cat_no =", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotEqualTo(String value) {
            addCriterion("cat_no <>", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoGreaterThan(String value) {
            addCriterion("cat_no >", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoGreaterThanOrEqualTo(String value) {
            addCriterion("cat_no >=", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLessThan(String value) {
            addCriterion("cat_no <", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLessThanOrEqualTo(String value) {
            addCriterion("cat_no <=", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoLike(String value) {
            addCriterion("cat_no like", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotLike(String value) {
            addCriterion("cat_no not like", value, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoIn(List<String> values) {
            addCriterion("cat_no in", values, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotIn(List<String> values) {
            addCriterion("cat_no not in", values, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoBetween(String value1, String value2) {
            addCriterion("cat_no between", value1, value2, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNoNotBetween(String value1, String value2) {
            addCriterion("cat_no not between", value1, value2, "catNo");
            return (Criteria) this;
        }

        public Criteria andCatNameIsNull() {
            addCriterion("cat_name is null");
            return (Criteria) this;
        }

        public Criteria andCatNameIsNotNull() {
            addCriterion("cat_name is not null");
            return (Criteria) this;
        }

        public Criteria andCatNameEqualTo(String value) {
            addCriterion("cat_name =", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotEqualTo(String value) {
            addCriterion("cat_name <>", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThan(String value) {
            addCriterion("cat_name >", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThanOrEqualTo(String value) {
            addCriterion("cat_name >=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThan(String value) {
            addCriterion("cat_name <", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThanOrEqualTo(String value) {
            addCriterion("cat_name <=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLike(String value) {
            addCriterion("cat_name like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotLike(String value) {
            addCriterion("cat_name not like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameIn(List<String> values) {
            addCriterion("cat_name in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotIn(List<String> values) {
            addCriterion("cat_name not in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameBetween(String value1, String value2) {
            addCriterion("cat_name between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotBetween(String value1, String value2) {
            addCriterion("cat_name not between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andPcatIdIsNull() {
            addCriterion("pcat_id is null");
            return (Criteria) this;
        }

        public Criteria andPcatIdIsNotNull() {
            addCriterion("pcat_id is not null");
            return (Criteria) this;
        }

        public Criteria andPcatIdEqualTo(Integer value) {
            addCriterion("pcat_id =", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdNotEqualTo(Integer value) {
            addCriterion("pcat_id <>", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdGreaterThan(Integer value) {
            addCriterion("pcat_id >", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pcat_id >=", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdLessThan(Integer value) {
            addCriterion("pcat_id <", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdLessThanOrEqualTo(Integer value) {
            addCriterion("pcat_id <=", value, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdIn(List<Integer> values) {
            addCriterion("pcat_id in", values, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdNotIn(List<Integer> values) {
            addCriterion("pcat_id not in", values, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdBetween(Integer value1, Integer value2) {
            addCriterion("pcat_id between", value1, value2, "pcatId");
            return (Criteria) this;
        }

        public Criteria andPcatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pcat_id not between", value1, value2, "pcatId");
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

        public Criteria andCatStateIsNull() {
            addCriterion("cat_state is null");
            return (Criteria) this;
        }

        public Criteria andCatStateIsNotNull() {
            addCriterion("cat_state is not null");
            return (Criteria) this;
        }

        public Criteria andCatStateEqualTo(String value) {
            addCriterion("cat_state =", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateNotEqualTo(String value) {
            addCriterion("cat_state <>", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateGreaterThan(String value) {
            addCriterion("cat_state >", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateGreaterThanOrEqualTo(String value) {
            addCriterion("cat_state >=", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateLessThan(String value) {
            addCriterion("cat_state <", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateLessThanOrEqualTo(String value) {
            addCriterion("cat_state <=", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateLike(String value) {
            addCriterion("cat_state like", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateNotLike(String value) {
            addCriterion("cat_state not like", value, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateIn(List<String> values) {
            addCriterion("cat_state in", values, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateNotIn(List<String> values) {
            addCriterion("cat_state not in", values, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateBetween(String value1, String value2) {
            addCriterion("cat_state between", value1, value2, "catState");
            return (Criteria) this;
        }

        public Criteria andCatStateNotBetween(String value1, String value2) {
            addCriterion("cat_state not between", value1, value2, "catState");
            return (Criteria) this;
        }

        public Criteria andBcatIdIsNull() {
            addCriterion("bcat_id is null");
            return (Criteria) this;
        }

        public Criteria andBcatIdIsNotNull() {
            addCriterion("bcat_id is not null");
            return (Criteria) this;
        }

        public Criteria andBcatIdEqualTo(Integer value) {
            addCriterion("bcat_id =", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdNotEqualTo(Integer value) {
            addCriterion("bcat_id <>", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdGreaterThan(Integer value) {
            addCriterion("bcat_id >", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bcat_id >=", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdLessThan(Integer value) {
            addCriterion("bcat_id <", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdLessThanOrEqualTo(Integer value) {
            addCriterion("bcat_id <=", value, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdIn(List<Integer> values) {
            addCriterion("bcat_id in", values, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdNotIn(List<Integer> values) {
            addCriterion("bcat_id not in", values, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdBetween(Integer value1, Integer value2) {
            addCriterion("bcat_id between", value1, value2, "bcatId");
            return (Criteria) this;
        }

        public Criteria andBcatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bcat_id not between", value1, value2, "bcatId");
            return (Criteria) this;
        }

        public Criteria andCatDescIsNull() {
            addCriterion("cat_desc is null");
            return (Criteria) this;
        }

        public Criteria andCatDescIsNotNull() {
            addCriterion("cat_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCatDescEqualTo(String value) {
            addCriterion("cat_desc =", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescNotEqualTo(String value) {
            addCriterion("cat_desc <>", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescGreaterThan(String value) {
            addCriterion("cat_desc >", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescGreaterThanOrEqualTo(String value) {
            addCriterion("cat_desc >=", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescLessThan(String value) {
            addCriterion("cat_desc <", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescLessThanOrEqualTo(String value) {
            addCriterion("cat_desc <=", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescLike(String value) {
            addCriterion("cat_desc like", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescNotLike(String value) {
            addCriterion("cat_desc not like", value, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescIn(List<String> values) {
            addCriterion("cat_desc in", values, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescNotIn(List<String> values) {
            addCriterion("cat_desc not in", values, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescBetween(String value1, String value2) {
            addCriterion("cat_desc between", value1, value2, "catDesc");
            return (Criteria) this;
        }

        public Criteria andCatDescNotBetween(String value1, String value2) {
            addCriterion("cat_desc not between", value1, value2, "catDesc");
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
     *  crm_prd_cat
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
     *  crm_prd_cat
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