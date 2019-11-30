package com.zlead.fplat.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvInItemExample {
    /**
     * orderByClausenull .
     *   inv_in_item
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   inv_in_item
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   inv_in_item
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:InvInItemExample
     *   inv_in_item
     *
     * @ET
     */
    public InvInItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   inv_in_item
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   inv_in_item
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   inv_in_item
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   inv_in_item
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   inv_in_item
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   inv_in_item
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   inv_in_item
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
     *   inv_in_item
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
     *   inv_in_item
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   inv_in_item
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
     *  inv_in_item
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

        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(Integer value) {
            addCriterion("detail_id =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(Integer value) {
            addCriterion("detail_id <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(Integer value) {
            addCriterion("detail_id >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("detail_id >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(Integer value) {
            addCriterion("detail_id <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<Integer> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<Integer> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(Integer value1, Integer value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNull() {
            addCriterion("bill_id is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(Integer value) {
            addCriterion("bill_id =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(Integer value) {
            addCriterion("bill_id <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(Integer value) {
            addCriterion("bill_id >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bill_id >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(Integer value) {
            addCriterion("bill_id <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(Integer value) {
            addCriterion("bill_id <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<Integer> values) {
            addCriterion("bill_id in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<Integer> values) {
            addCriterion("bill_id not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(Integer value1, Integer value2) {
            addCriterion("bill_id between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bill_id not between", value1, value2, "billId");
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

        public Criteria andLocIdIsNull() {
            addCriterion("loc_id is null");
            return (Criteria) this;
        }

        public Criteria andLocIdIsNotNull() {
            addCriterion("loc_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocIdEqualTo(Integer value) {
            addCriterion("loc_id =", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotEqualTo(Integer value) {
            addCriterion("loc_id <>", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdGreaterThan(Integer value) {
            addCriterion("loc_id >", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loc_id >=", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdLessThan(Integer value) {
            addCriterion("loc_id <", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdLessThanOrEqualTo(Integer value) {
            addCriterion("loc_id <=", value, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdIn(List<Integer> values) {
            addCriterion("loc_id in", values, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotIn(List<Integer> values) {
            addCriterion("loc_id not in", values, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdBetween(Integer value1, Integer value2) {
            addCriterion("loc_id between", value1, value2, "locId");
            return (Criteria) this;
        }

        public Criteria andLocIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loc_id not between", value1, value2, "locId");
            return (Criteria) this;
        }

        public Criteria andLineQtyIsNull() {
            addCriterion("line_qty is null");
            return (Criteria) this;
        }

        public Criteria andLineQtyIsNotNull() {
            addCriterion("line_qty is not null");
            return (Criteria) this;
        }

        public Criteria andLineQtyEqualTo(Integer value) {
            addCriterion("line_qty =", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyNotEqualTo(Integer value) {
            addCriterion("line_qty <>", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyGreaterThan(Integer value) {
            addCriterion("line_qty >", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("line_qty >=", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyLessThan(Integer value) {
            addCriterion("line_qty <", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyLessThanOrEqualTo(Integer value) {
            addCriterion("line_qty <=", value, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyIn(List<Integer> values) {
            addCriterion("line_qty in", values, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyNotIn(List<Integer> values) {
            addCriterion("line_qty not in", values, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyBetween(Integer value1, Integer value2) {
            addCriterion("line_qty between", value1, value2, "lineQty");
            return (Criteria) this;
        }

        public Criteria andLineQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("line_qty not between", value1, value2, "lineQty");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNull() {
            addCriterion("item_price is null");
            return (Criteria) this;
        }

        public Criteria andItemPriceIsNotNull() {
            addCriterion("item_price is not null");
            return (Criteria) this;
        }

        public Criteria andItemPriceEqualTo(BigDecimal value) {
            addCriterion("item_price =", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotEqualTo(BigDecimal value) {
            addCriterion("item_price <>", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThan(BigDecimal value) {
            addCriterion("item_price >", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price >=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThan(BigDecimal value) {
            addCriterion("item_price <", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("item_price <=", value, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceIn(List<BigDecimal> values) {
            addCriterion("item_price in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotIn(List<BigDecimal> values) {
            addCriterion("item_price not in", values, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andItemPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("item_price not between", value1, value2, "itemPrice");
            return (Criteria) this;
        }

        public Criteria andLineAmtIsNull() {
            addCriterion("line_amt is null");
            return (Criteria) this;
        }

        public Criteria andLineAmtIsNotNull() {
            addCriterion("line_amt is not null");
            return (Criteria) this;
        }

        public Criteria andLineAmtEqualTo(BigDecimal value) {
            addCriterion("line_amt =", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotEqualTo(BigDecimal value) {
            addCriterion("line_amt <>", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtGreaterThan(BigDecimal value) {
            addCriterion("line_amt >", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("line_amt >=", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtLessThan(BigDecimal value) {
            addCriterion("line_amt <", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("line_amt <=", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtIn(List<BigDecimal> values) {
            addCriterion("line_amt in", values, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotIn(List<BigDecimal> values) {
            addCriterion("line_amt not in", values, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("line_amt between", value1, value2, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("line_amt not between", value1, value2, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLkdIdIsNull() {
            addCriterion("lkd_id is null");
            return (Criteria) this;
        }

        public Criteria andLkdIdIsNotNull() {
            addCriterion("lkd_id is not null");
            return (Criteria) this;
        }

        public Criteria andLkdIdEqualTo(Integer value) {
            addCriterion("lkd_id =", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdNotEqualTo(Integer value) {
            addCriterion("lkd_id <>", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdGreaterThan(Integer value) {
            addCriterion("lkd_id >", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lkd_id >=", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdLessThan(Integer value) {
            addCriterion("lkd_id <", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdLessThanOrEqualTo(Integer value) {
            addCriterion("lkd_id <=", value, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdIn(List<Integer> values) {
            addCriterion("lkd_id in", values, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdNotIn(List<Integer> values) {
            addCriterion("lkd_id not in", values, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdBetween(Integer value1, Integer value2) {
            addCriterion("lkd_id between", value1, value2, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLkdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lkd_id not between", value1, value2, "lkdId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNull() {
            addCriterion("link_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkIdIsNotNull() {
            addCriterion("link_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkIdEqualTo(Integer value) {
            addCriterion("link_id =", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotEqualTo(Integer value) {
            addCriterion("link_id <>", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThan(Integer value) {
            addCriterion("link_id >", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_id >=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThan(Integer value) {
            addCriterion("link_id <", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdLessThanOrEqualTo(Integer value) {
            addCriterion("link_id <=", value, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdIn(List<Integer> values) {
            addCriterion("link_id in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotIn(List<Integer> values) {
            addCriterion("link_id not in", values, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdBetween(Integer value1, Integer value2) {
            addCriterion("link_id between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("link_id not between", value1, value2, "linkId");
            return (Criteria) this;
        }

        public Criteria andLinkQtyIsNull() {
            addCriterion("link_qty is null");
            return (Criteria) this;
        }

        public Criteria andLinkQtyIsNotNull() {
            addCriterion("link_qty is not null");
            return (Criteria) this;
        }

        public Criteria andLinkQtyEqualTo(Integer value) {
            addCriterion("link_qty =", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyNotEqualTo(Integer value) {
            addCriterion("link_qty <>", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyGreaterThan(Integer value) {
            addCriterion("link_qty >", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_qty >=", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyLessThan(Integer value) {
            addCriterion("link_qty <", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyLessThanOrEqualTo(Integer value) {
            addCriterion("link_qty <=", value, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyIn(List<Integer> values) {
            addCriterion("link_qty in", values, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyNotIn(List<Integer> values) {
            addCriterion("link_qty not in", values, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyBetween(Integer value1, Integer value2) {
            addCriterion("link_qty between", value1, value2, "linkQty");
            return (Criteria) this;
        }

        public Criteria andLinkQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("link_qty not between", value1, value2, "linkQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyIsNull() {
            addCriterion("spare_qty is null");
            return (Criteria) this;
        }

        public Criteria andSpareQtyIsNotNull() {
            addCriterion("spare_qty is not null");
            return (Criteria) this;
        }

        public Criteria andSpareQtyEqualTo(Integer value) {
            addCriterion("spare_qty =", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyNotEqualTo(Integer value) {
            addCriterion("spare_qty <>", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyGreaterThan(Integer value) {
            addCriterion("spare_qty >", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("spare_qty >=", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyLessThan(Integer value) {
            addCriterion("spare_qty <", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyLessThanOrEqualTo(Integer value) {
            addCriterion("spare_qty <=", value, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyIn(List<Integer> values) {
            addCriterion("spare_qty in", values, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyNotIn(List<Integer> values) {
            addCriterion("spare_qty not in", values, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyBetween(Integer value1, Integer value2) {
            addCriterion("spare_qty between", value1, value2, "spareQty");
            return (Criteria) this;
        }

        public Criteria andSpareQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("spare_qty not between", value1, value2, "spareQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyIsNull() {
            addCriterion("used_qty is null");
            return (Criteria) this;
        }

        public Criteria andUsedQtyIsNotNull() {
            addCriterion("used_qty is not null");
            return (Criteria) this;
        }

        public Criteria andUsedQtyEqualTo(Integer value) {
            addCriterion("used_qty =", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyNotEqualTo(Integer value) {
            addCriterion("used_qty <>", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyGreaterThan(Integer value) {
            addCriterion("used_qty >", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("used_qty >=", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyLessThan(Integer value) {
            addCriterion("used_qty <", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyLessThanOrEqualTo(Integer value) {
            addCriterion("used_qty <=", value, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyIn(List<Integer> values) {
            addCriterion("used_qty in", values, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyNotIn(List<Integer> values) {
            addCriterion("used_qty not in", values, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyBetween(Integer value1, Integer value2) {
            addCriterion("used_qty between", value1, value2, "usedQty");
            return (Criteria) this;
        }

        public Criteria andUsedQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("used_qty not between", value1, value2, "usedQty");
            return (Criteria) this;
        }

        public Criteria andLineDescIsNull() {
            addCriterion("line_desc is null");
            return (Criteria) this;
        }

        public Criteria andLineDescIsNotNull() {
            addCriterion("line_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLineDescEqualTo(String value) {
            addCriterion("line_desc =", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescNotEqualTo(String value) {
            addCriterion("line_desc <>", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescGreaterThan(String value) {
            addCriterion("line_desc >", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescGreaterThanOrEqualTo(String value) {
            addCriterion("line_desc >=", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescLessThan(String value) {
            addCriterion("line_desc <", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescLessThanOrEqualTo(String value) {
            addCriterion("line_desc <=", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescLike(String value) {
            addCriterion("line_desc like", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescNotLike(String value) {
            addCriterion("line_desc not like", value, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescIn(List<String> values) {
            addCriterion("line_desc in", values, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescNotIn(List<String> values) {
            addCriterion("line_desc not in", values, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescBetween(String value1, String value2) {
            addCriterion("line_desc between", value1, value2, "lineDesc");
            return (Criteria) this;
        }

        public Criteria andLineDescNotBetween(String value1, String value2) {
            addCriterion("line_desc not between", value1, value2, "lineDesc");
            return (Criteria) this;
        }
    }

    /**
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  inv_in_item
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
     *  inv_in_item
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