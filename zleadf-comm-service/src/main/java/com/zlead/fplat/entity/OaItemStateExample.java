package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OaItemStateExample {
    /**
     * orderByClausenull .
     *   oa_item_state
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   oa_item_state
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   oa_item_state
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:OaItemStateExample
     *   oa_item_state
     *
     * @ET
     */
    public OaItemStateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   oa_item_state
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   oa_item_state
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   oa_item_state
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   oa_item_state
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   oa_item_state
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   oa_item_state
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   oa_item_state
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
     *   oa_item_state
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
     *   oa_item_state
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   oa_item_state
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
     *  oa_item_state
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

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Double value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Double value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Double value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Double value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Double value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Double> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Double> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Double value1, Double value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Double value1, Double value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceIsNull() {
            addCriterion("show_price is null");
            return (Criteria) this;
        }

        public Criteria andShowPriceIsNotNull() {
            addCriterion("show_price is not null");
            return (Criteria) this;
        }

        public Criteria andShowPriceEqualTo(String value) {
            addCriterion("show_price =", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotEqualTo(String value) {
            addCriterion("show_price <>", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceGreaterThan(String value) {
            addCriterion("show_price >", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceGreaterThanOrEqualTo(String value) {
            addCriterion("show_price >=", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceLessThan(String value) {
            addCriterion("show_price <", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceLessThanOrEqualTo(String value) {
            addCriterion("show_price <=", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceLike(String value) {
            addCriterion("show_price like", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotLike(String value) {
            addCriterion("show_price not like", value, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceIn(List<String> values) {
            addCriterion("show_price in", values, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotIn(List<String> values) {
            addCriterion("show_price not in", values, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceBetween(String value1, String value2) {
            addCriterion("show_price between", value1, value2, "showPrice");
            return (Criteria) this;
        }

        public Criteria andShowPriceNotBetween(String value1, String value2) {
            addCriterion("show_price not between", value1, value2, "showPrice");
            return (Criteria) this;
        }

        public Criteria andCustLevelsIsNull() {
            addCriterion("cust_levels is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelsIsNotNull() {
            addCriterion("cust_levels is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelsEqualTo(String value) {
            addCriterion("cust_levels =", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsNotEqualTo(String value) {
            addCriterion("cust_levels <>", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsGreaterThan(String value) {
            addCriterion("cust_levels >", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsGreaterThanOrEqualTo(String value) {
            addCriterion("cust_levels >=", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsLessThan(String value) {
            addCriterion("cust_levels <", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsLessThanOrEqualTo(String value) {
            addCriterion("cust_levels <=", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsLike(String value) {
            addCriterion("cust_levels like", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsNotLike(String value) {
            addCriterion("cust_levels not like", value, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsIn(List<String> values) {
            addCriterion("cust_levels in", values, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsNotIn(List<String> values) {
            addCriterion("cust_levels not in", values, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsBetween(String value1, String value2) {
            addCriterion("cust_levels between", value1, value2, "custLevels");
            return (Criteria) this;
        }

        public Criteria andCustLevelsNotBetween(String value1, String value2) {
            addCriterion("cust_levels not between", value1, value2, "custLevels");
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

        public Criteria andSaleQtyIsNull() {
            addCriterion("sale_qty is null");
            return (Criteria) this;
        }

        public Criteria andSaleQtyIsNotNull() {
            addCriterion("sale_qty is not null");
            return (Criteria) this;
        }

        public Criteria andSaleQtyEqualTo(Integer value) {
            addCriterion("sale_qty =", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotEqualTo(Integer value) {
            addCriterion("sale_qty <>", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyGreaterThan(Integer value) {
            addCriterion("sale_qty >", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_qty >=", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyLessThan(Integer value) {
            addCriterion("sale_qty <", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyLessThanOrEqualTo(Integer value) {
            addCriterion("sale_qty <=", value, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyIn(List<Integer> values) {
            addCriterion("sale_qty in", values, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotIn(List<Integer> values) {
            addCriterion("sale_qty not in", values, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyBetween(Integer value1, Integer value2) {
            addCriterion("sale_qty between", value1, value2, "saleQty");
            return (Criteria) this;
        }

        public Criteria andSaleQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_qty not between", value1, value2, "saleQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyIsNull() {
            addCriterion("show_qty is null");
            return (Criteria) this;
        }

        public Criteria andShowQtyIsNotNull() {
            addCriterion("show_qty is not null");
            return (Criteria) this;
        }

        public Criteria andShowQtyEqualTo(String value) {
            addCriterion("show_qty =", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyNotEqualTo(String value) {
            addCriterion("show_qty <>", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyGreaterThan(String value) {
            addCriterion("show_qty >", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyGreaterThanOrEqualTo(String value) {
            addCriterion("show_qty >=", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyLessThan(String value) {
            addCriterion("show_qty <", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyLessThanOrEqualTo(String value) {
            addCriterion("show_qty <=", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyLike(String value) {
            addCriterion("show_qty like", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyNotLike(String value) {
            addCriterion("show_qty not like", value, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyIn(List<String> values) {
            addCriterion("show_qty in", values, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyNotIn(List<String> values) {
            addCriterion("show_qty not in", values, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyBetween(String value1, String value2) {
            addCriterion("show_qty between", value1, value2, "showQty");
            return (Criteria) this;
        }

        public Criteria andShowQtyNotBetween(String value1, String value2) {
            addCriterion("show_qty not between", value1, value2, "showQty");
            return (Criteria) this;
        }

        public Criteria andAutoDateIsNull() {
            addCriterion("auto_date is null");
            return (Criteria) this;
        }

        public Criteria andAutoDateIsNotNull() {
            addCriterion("auto_date is not null");
            return (Criteria) this;
        }

        public Criteria andAutoDateEqualTo(Date value) {
            addCriterion("auto_date =", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateNotEqualTo(Date value) {
            addCriterion("auto_date <>", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateGreaterThan(Date value) {
            addCriterion("auto_date >", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateGreaterThanOrEqualTo(Date value) {
            addCriterion("auto_date >=", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateLessThan(Date value) {
            addCriterion("auto_date <", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateLessThanOrEqualTo(Date value) {
            addCriterion("auto_date <=", value, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateIn(List<Date> values) {
            addCriterion("auto_date in", values, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateNotIn(List<Date> values) {
            addCriterion("auto_date not in", values, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateBetween(Date value1, Date value2) {
            addCriterion("auto_date between", value1, value2, "autoDate");
            return (Criteria) this;
        }

        public Criteria andAutoDateNotBetween(Date value1, Date value2) {
            addCriterion("auto_date not between", value1, value2, "autoDate");
            return (Criteria) this;
        }

        public Criteria andOnDateIsNull() {
            addCriterion("on_date is null");
            return (Criteria) this;
        }

        public Criteria andOnDateIsNotNull() {
            addCriterion("on_date is not null");
            return (Criteria) this;
        }

        public Criteria andOnDateEqualTo(Date value) {
            addCriterion("on_date =", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotEqualTo(Date value) {
            addCriterion("on_date <>", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateGreaterThan(Date value) {
            addCriterion("on_date >", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("on_date >=", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateLessThan(Date value) {
            addCriterion("on_date <", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateLessThanOrEqualTo(Date value) {
            addCriterion("on_date <=", value, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateIn(List<Date> values) {
            addCriterion("on_date in", values, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotIn(List<Date> values) {
            addCriterion("on_date not in", values, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateBetween(Date value1, Date value2) {
            addCriterion("on_date between", value1, value2, "onDate");
            return (Criteria) this;
        }

        public Criteria andOnDateNotBetween(Date value1, Date value2) {
            addCriterion("on_date not between", value1, value2, "onDate");
            return (Criteria) this;
        }

        public Criteria andOffDateIsNull() {
            addCriterion("off_date is null");
            return (Criteria) this;
        }

        public Criteria andOffDateIsNotNull() {
            addCriterion("off_date is not null");
            return (Criteria) this;
        }

        public Criteria andOffDateEqualTo(Date value) {
            addCriterion("off_date =", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateNotEqualTo(Date value) {
            addCriterion("off_date <>", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateGreaterThan(Date value) {
            addCriterion("off_date >", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateGreaterThanOrEqualTo(Date value) {
            addCriterion("off_date >=", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateLessThan(Date value) {
            addCriterion("off_date <", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateLessThanOrEqualTo(Date value) {
            addCriterion("off_date <=", value, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateIn(List<Date> values) {
            addCriterion("off_date in", values, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateNotIn(List<Date> values) {
            addCriterion("off_date not in", values, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateBetween(Date value1, Date value2) {
            addCriterion("off_date between", value1, value2, "offDate");
            return (Criteria) this;
        }

        public Criteria andOffDateNotBetween(Date value1, Date value2) {
            addCriterion("off_date not between", value1, value2, "offDate");
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
     *  oa_item_state
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
     *  oa_item_state
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