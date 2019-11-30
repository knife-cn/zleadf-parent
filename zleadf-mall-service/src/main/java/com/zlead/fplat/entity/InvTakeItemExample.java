package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.List;

public class InvTakeItemExample {
    /**
     * orderByClausenull .
     *   inv_take_item
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   inv_take_item
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   inv_take_item
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:InvTakeItemExample
     *   inv_take_item
     *
     * @ET
     */
    public InvTakeItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   inv_take_item
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   inv_take_item
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   inv_take_item
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   inv_take_item
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   inv_take_item
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   inv_take_item
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   inv_take_item
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
     *   inv_take_item
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
     *   inv_take_item
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   inv_take_item
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
     *  inv_take_item
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

        public Criteria andItemUomIsNull() {
            addCriterion("item_uom is null");
            return (Criteria) this;
        }

        public Criteria andItemUomIsNotNull() {
            addCriterion("item_uom is not null");
            return (Criteria) this;
        }

        public Criteria andItemUomEqualTo(String value) {
            addCriterion("item_uom =", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomNotEqualTo(String value) {
            addCriterion("item_uom <>", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomGreaterThan(String value) {
            addCriterion("item_uom >", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomGreaterThanOrEqualTo(String value) {
            addCriterion("item_uom >=", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomLessThan(String value) {
            addCriterion("item_uom <", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomLessThanOrEqualTo(String value) {
            addCriterion("item_uom <=", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomLike(String value) {
            addCriterion("item_uom like", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomNotLike(String value) {
            addCriterion("item_uom not like", value, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomIn(List<String> values) {
            addCriterion("item_uom in", values, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomNotIn(List<String> values) {
            addCriterion("item_uom not in", values, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomBetween(String value1, String value2) {
            addCriterion("item_uom between", value1, value2, "itemUom");
            return (Criteria) this;
        }

        public Criteria andItemUomNotBetween(String value1, String value2) {
            addCriterion("item_uom not between", value1, value2, "itemUom");
            return (Criteria) this;
        }

        public Criteria andInvQtyIsNull() {
            addCriterion("inv_qty is null");
            return (Criteria) this;
        }

        public Criteria andInvQtyIsNotNull() {
            addCriterion("inv_qty is not null");
            return (Criteria) this;
        }

        public Criteria andInvQtyEqualTo(Integer value) {
            addCriterion("inv_qty =", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyNotEqualTo(Integer value) {
            addCriterion("inv_qty <>", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyGreaterThan(Integer value) {
            addCriterion("inv_qty >", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("inv_qty >=", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyLessThan(Integer value) {
            addCriterion("inv_qty <", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyLessThanOrEqualTo(Integer value) {
            addCriterion("inv_qty <=", value, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyIn(List<Integer> values) {
            addCriterion("inv_qty in", values, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyNotIn(List<Integer> values) {
            addCriterion("inv_qty not in", values, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyBetween(Integer value1, Integer value2) {
            addCriterion("inv_qty between", value1, value2, "invQty");
            return (Criteria) this;
        }

        public Criteria andInvQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("inv_qty not between", value1, value2, "invQty");
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

        public Criteria andLdefQtyIsNull() {
            addCriterion("ldef_qty is null");
            return (Criteria) this;
        }

        public Criteria andLdefQtyIsNotNull() {
            addCriterion("ldef_qty is not null");
            return (Criteria) this;
        }

        public Criteria andLdefQtyEqualTo(Integer value) {
            addCriterion("ldef_qty =", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyNotEqualTo(Integer value) {
            addCriterion("ldef_qty <>", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyGreaterThan(Integer value) {
            addCriterion("ldef_qty >", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("ldef_qty >=", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyLessThan(Integer value) {
            addCriterion("ldef_qty <", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyLessThanOrEqualTo(Integer value) {
            addCriterion("ldef_qty <=", value, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyIn(List<Integer> values) {
            addCriterion("ldef_qty in", values, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyNotIn(List<Integer> values) {
            addCriterion("ldef_qty not in", values, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyBetween(Integer value1, Integer value2) {
            addCriterion("ldef_qty between", value1, value2, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andLdefQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("ldef_qty not between", value1, value2, "ldefQty");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(Double value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(Double value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(Double value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(Double value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(Double value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<Double> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<Double> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(Double value1, Double value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(Double value1, Double value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
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

        public Criteria andLineAmtEqualTo(Double value) {
            addCriterion("line_amt =", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotEqualTo(Double value) {
            addCriterion("line_amt <>", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtGreaterThan(Double value) {
            addCriterion("line_amt >", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("line_amt >=", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtLessThan(Double value) {
            addCriterion("line_amt <", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtLessThanOrEqualTo(Double value) {
            addCriterion("line_amt <=", value, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtIn(List<Double> values) {
            addCriterion("line_amt in", values, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotIn(List<Double> values) {
            addCriterion("line_amt not in", values, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtBetween(Double value1, Double value2) {
            addCriterion("line_amt between", value1, value2, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLineAmtNotBetween(Double value1, Double value2) {
            addCriterion("line_amt not between", value1, value2, "lineAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtIsNull() {
            addCriterion("linc_amt is null");
            return (Criteria) this;
        }

        public Criteria andLincAmtIsNotNull() {
            addCriterion("linc_amt is not null");
            return (Criteria) this;
        }

        public Criteria andLincAmtEqualTo(Double value) {
            addCriterion("linc_amt =", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtNotEqualTo(Double value) {
            addCriterion("linc_amt <>", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtGreaterThan(Double value) {
            addCriterion("linc_amt >", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("linc_amt >=", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtLessThan(Double value) {
            addCriterion("linc_amt <", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtLessThanOrEqualTo(Double value) {
            addCriterion("linc_amt <=", value, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtIn(List<Double> values) {
            addCriterion("linc_amt in", values, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtNotIn(List<Double> values) {
            addCriterion("linc_amt not in", values, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtBetween(Double value1, Double value2) {
            addCriterion("linc_amt between", value1, value2, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLincAmtNotBetween(Double value1, Double value2) {
            addCriterion("linc_amt not between", value1, value2, "lincAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtIsNull() {
            addCriterion("ldef_amt is null");
            return (Criteria) this;
        }

        public Criteria andLdefAmtIsNotNull() {
            addCriterion("ldef_amt is not null");
            return (Criteria) this;
        }

        public Criteria andLdefAmtEqualTo(Double value) {
            addCriterion("ldef_amt =", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtNotEqualTo(Double value) {
            addCriterion("ldef_amt <>", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtGreaterThan(Double value) {
            addCriterion("ldef_amt >", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("ldef_amt >=", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtLessThan(Double value) {
            addCriterion("ldef_amt <", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtLessThanOrEqualTo(Double value) {
            addCriterion("ldef_amt <=", value, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtIn(List<Double> values) {
            addCriterion("ldef_amt in", values, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtNotIn(List<Double> values) {
            addCriterion("ldef_amt not in", values, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtBetween(Double value1, Double value2) {
            addCriterion("ldef_amt between", value1, value2, "ldefAmt");
            return (Criteria) this;
        }

        public Criteria andLdefAmtNotBetween(Double value1, Double value2) {
            addCriterion("ldef_amt not between", value1, value2, "ldefAmt");
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
     *  inv_take_item
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
     *  inv_take_item
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