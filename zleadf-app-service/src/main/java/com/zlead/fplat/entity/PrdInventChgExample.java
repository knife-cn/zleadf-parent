package com.zlead.fplat.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrdInventChgExample {
    /**
     * orderByClausenull .
     *   prd_invent_chg
     *
     * @ET
     */
    protected String orderByClause;

    /**
     * distinctnull .
     *   prd_invent_chg
     *
     * @ET
     */
    protected boolean distinct;

    /**
     * oredCriterianull .
     *   prd_invent_chg
     *
     * @ET
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method:PrdInventChgExample
     *   prd_invent_chg
     *
     * @ET
     */
    public PrdInventChgExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method:setOrderByClause
     *   prd_invent_chg
     *
     * @ET
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method:getOrderByClause
     *   prd_invent_chg
     *
     * @ET
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method:setDistinct
     *   prd_invent_chg
     *
     * @ET
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method:isDistinct
     *   prd_invent_chg
     *
     * @ET
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method:getOredCriteria
     *   prd_invent_chg
     *
     * @ET
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method:or
     *   prd_invent_chg
     *
     * @ET
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method:or
     *   prd_invent_chg
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
     *   prd_invent_chg
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
     *   prd_invent_chg
     *
     * @ET
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method:clear
     *   prd_invent_chg
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
     *  prd_invent_chg
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

        public Criteria andRecIdIsNull() {
            addCriterion("rec_id is null");
            return (Criteria) this;
        }

        public Criteria andRecIdIsNotNull() {
            addCriterion("rec_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecIdEqualTo(Integer value) {
            addCriterion("rec_id =", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdNotEqualTo(Integer value) {
            addCriterion("rec_id <>", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdGreaterThan(Integer value) {
            addCriterion("rec_id >", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rec_id >=", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdLessThan(Integer value) {
            addCriterion("rec_id <", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdLessThanOrEqualTo(Integer value) {
            addCriterion("rec_id <=", value, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdIn(List<Integer> values) {
            addCriterion("rec_id in", values, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdNotIn(List<Integer> values) {
            addCriterion("rec_id not in", values, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdBetween(Integer value1, Integer value2) {
            addCriterion("rec_id between", value1, value2, "recId");
            return (Criteria) this;
        }

        public Criteria andRecIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rec_id not between", value1, value2, "recId");
            return (Criteria) this;
        }

        public Criteria andImIdIsNull() {
            addCriterion("im_id is null");
            return (Criteria) this;
        }

        public Criteria andImIdIsNotNull() {
            addCriterion("im_id is not null");
            return (Criteria) this;
        }

        public Criteria andImIdEqualTo(Integer value) {
            addCriterion("im_id =", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdNotEqualTo(Integer value) {
            addCriterion("im_id <>", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdGreaterThan(Integer value) {
            addCriterion("im_id >", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("im_id >=", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdLessThan(Integer value) {
            addCriterion("im_id <", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdLessThanOrEqualTo(Integer value) {
            addCriterion("im_id <=", value, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdIn(List<Integer> values) {
            addCriterion("im_id in", values, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdNotIn(List<Integer> values) {
            addCriterion("im_id not in", values, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdBetween(Integer value1, Integer value2) {
            addCriterion("im_id between", value1, value2, "imId");
            return (Criteria) this;
        }

        public Criteria andImIdNotBetween(Integer value1, Integer value2) {
            addCriterion("im_id not between", value1, value2, "imId");
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

        public Criteria andWhIdIsNull() {
            addCriterion("wh_id is null");
            return (Criteria) this;
        }

        public Criteria andWhIdIsNotNull() {
            addCriterion("wh_id is not null");
            return (Criteria) this;
        }

        public Criteria andWhIdEqualTo(Integer value) {
            addCriterion("wh_id =", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotEqualTo(Integer value) {
            addCriterion("wh_id <>", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdGreaterThan(Integer value) {
            addCriterion("wh_id >", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("wh_id >=", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdLessThan(Integer value) {
            addCriterion("wh_id <", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdLessThanOrEqualTo(Integer value) {
            addCriterion("wh_id <=", value, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdIn(List<Integer> values) {
            addCriterion("wh_id in", values, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotIn(List<Integer> values) {
            addCriterion("wh_id not in", values, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdBetween(Integer value1, Integer value2) {
            addCriterion("wh_id between", value1, value2, "whId");
            return (Criteria) this;
        }

        public Criteria andWhIdNotBetween(Integer value1, Integer value2) {
            addCriterion("wh_id not between", value1, value2, "whId");
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

        public Criteria andPreQtyIsNull() {
            addCriterion("pre_qty is null");
            return (Criteria) this;
        }

        public Criteria andPreQtyIsNotNull() {
            addCriterion("pre_qty is not null");
            return (Criteria) this;
        }

        public Criteria andPreQtyEqualTo(Integer value) {
            addCriterion("pre_qty =", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyNotEqualTo(Integer value) {
            addCriterion("pre_qty <>", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyGreaterThan(Integer value) {
            addCriterion("pre_qty >", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_qty >=", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyLessThan(Integer value) {
            addCriterion("pre_qty <", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyLessThanOrEqualTo(Integer value) {
            addCriterion("pre_qty <=", value, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyIn(List<Integer> values) {
            addCriterion("pre_qty in", values, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyNotIn(List<Integer> values) {
            addCriterion("pre_qty not in", values, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyBetween(Integer value1, Integer value2) {
            addCriterion("pre_qty between", value1, value2, "preQty");
            return (Criteria) this;
        }

        public Criteria andPreQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_qty not between", value1, value2, "preQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyIsNull() {
            addCriterion("io_qty is null");
            return (Criteria) this;
        }

        public Criteria andIoQtyIsNotNull() {
            addCriterion("io_qty is not null");
            return (Criteria) this;
        }

        public Criteria andIoQtyEqualTo(Integer value) {
            addCriterion("io_qty =", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyNotEqualTo(Integer value) {
            addCriterion("io_qty <>", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyGreaterThan(Integer value) {
            addCriterion("io_qty >", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("io_qty >=", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyLessThan(Integer value) {
            addCriterion("io_qty <", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyLessThanOrEqualTo(Integer value) {
            addCriterion("io_qty <=", value, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyIn(List<Integer> values) {
            addCriterion("io_qty in", values, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyNotIn(List<Integer> values) {
            addCriterion("io_qty not in", values, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyBetween(Integer value1, Integer value2) {
            addCriterion("io_qty between", value1, value2, "ioQty");
            return (Criteria) this;
        }

        public Criteria andIoQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("io_qty not between", value1, value2, "ioQty");
            return (Criteria) this;
        }

        public Criteria andImQtyIsNull() {
            addCriterion("im_qty is null");
            return (Criteria) this;
        }

        public Criteria andImQtyIsNotNull() {
            addCriterion("im_qty is not null");
            return (Criteria) this;
        }

        public Criteria andImQtyEqualTo(Integer value) {
            addCriterion("im_qty =", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyNotEqualTo(Integer value) {
            addCriterion("im_qty <>", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyGreaterThan(Integer value) {
            addCriterion("im_qty >", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("im_qty >=", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyLessThan(Integer value) {
            addCriterion("im_qty <", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyLessThanOrEqualTo(Integer value) {
            addCriterion("im_qty <=", value, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyIn(List<Integer> values) {
            addCriterion("im_qty in", values, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyNotIn(List<Integer> values) {
            addCriterion("im_qty not in", values, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyBetween(Integer value1, Integer value2) {
            addCriterion("im_qty between", value1, value2, "imQty");
            return (Criteria) this;
        }

        public Criteria andImQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("im_qty not between", value1, value2, "imQty");
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

        public Criteria andPreAmtIsNull() {
            addCriterion("pre_amt is null");
            return (Criteria) this;
        }

        public Criteria andPreAmtIsNotNull() {
            addCriterion("pre_amt is not null");
            return (Criteria) this;
        }

        public Criteria andPreAmtEqualTo(Double value) {
            addCriterion("pre_amt =", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtNotEqualTo(Double value) {
            addCriterion("pre_amt <>", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtGreaterThan(Double value) {
            addCriterion("pre_amt >", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("pre_amt >=", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtLessThan(Double value) {
            addCriterion("pre_amt <", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtLessThanOrEqualTo(Double value) {
            addCriterion("pre_amt <=", value, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtIn(List<Double> values) {
            addCriterion("pre_amt in", values, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtNotIn(List<Double> values) {
            addCriterion("pre_amt not in", values, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtBetween(Double value1, Double value2) {
            addCriterion("pre_amt between", value1, value2, "preAmt");
            return (Criteria) this;
        }

        public Criteria andPreAmtNotBetween(Double value1, Double value2) {
            addCriterion("pre_amt not between", value1, value2, "preAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtIsNull() {
            addCriterion("io_amt is null");
            return (Criteria) this;
        }

        public Criteria andIoAmtIsNotNull() {
            addCriterion("io_amt is not null");
            return (Criteria) this;
        }

        public Criteria andIoAmtEqualTo(Double value) {
            addCriterion("io_amt =", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtNotEqualTo(Double value) {
            addCriterion("io_amt <>", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtGreaterThan(Double value) {
            addCriterion("io_amt >", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("io_amt >=", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtLessThan(Double value) {
            addCriterion("io_amt <", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtLessThanOrEqualTo(Double value) {
            addCriterion("io_amt <=", value, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtIn(List<Double> values) {
            addCriterion("io_amt in", values, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtNotIn(List<Double> values) {
            addCriterion("io_amt not in", values, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtBetween(Double value1, Double value2) {
            addCriterion("io_amt between", value1, value2, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andIoAmtNotBetween(Double value1, Double value2) {
            addCriterion("io_amt not between", value1, value2, "ioAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtIsNull() {
            addCriterion("im_amt is null");
            return (Criteria) this;
        }

        public Criteria andImAmtIsNotNull() {
            addCriterion("im_amt is not null");
            return (Criteria) this;
        }

        public Criteria andImAmtEqualTo(Double value) {
            addCriterion("im_amt =", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtNotEqualTo(Double value) {
            addCriterion("im_amt <>", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtGreaterThan(Double value) {
            addCriterion("im_amt >", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtGreaterThanOrEqualTo(Double value) {
            addCriterion("im_amt >=", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtLessThan(Double value) {
            addCriterion("im_amt <", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtLessThanOrEqualTo(Double value) {
            addCriterion("im_amt <=", value, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtIn(List<Double> values) {
            addCriterion("im_amt in", values, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtNotIn(List<Double> values) {
            addCriterion("im_amt not in", values, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtBetween(Double value1, Double value2) {
            addCriterion("im_amt between", value1, value2, "imAmt");
            return (Criteria) this;
        }

        public Criteria andImAmtNotBetween(Double value1, Double value2) {
            addCriterion("im_amt not between", value1, value2, "imAmt");
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

        public Criteria andBillNoIsNull() {
            addCriterion("bill_no is null");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNotNull() {
            addCriterion("bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andBillNoEqualTo(String value) {
            addCriterion("bill_no =", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotEqualTo(String value) {
            addCriterion("bill_no <>", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThan(String value) {
            addCriterion("bill_no >", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("bill_no >=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThan(String value) {
            addCriterion("bill_no <", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThanOrEqualTo(String value) {
            addCriterion("bill_no <=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLike(String value) {
            addCriterion("bill_no like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotLike(String value) {
            addCriterion("bill_no not like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoIn(List<String> values) {
            addCriterion("bill_no in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotIn(List<String> values) {
            addCriterion("bill_no not in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoBetween(String value1, String value2) {
            addCriterion("bill_no between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotBetween(String value1, String value2) {
            addCriterion("bill_no not between", value1, value2, "billNo");
            return (Criteria) this;
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

        public Criteria andIoFlagIsNull() {
            addCriterion("io_flag is null");
            return (Criteria) this;
        }

        public Criteria andIoFlagIsNotNull() {
            addCriterion("io_flag is not null");
            return (Criteria) this;
        }

        public Criteria andIoFlagEqualTo(String value) {
            addCriterion("io_flag =", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagNotEqualTo(String value) {
            addCriterion("io_flag <>", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagGreaterThan(String value) {
            addCriterion("io_flag >", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagGreaterThanOrEqualTo(String value) {
            addCriterion("io_flag >=", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagLessThan(String value) {
            addCriterion("io_flag <", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagLessThanOrEqualTo(String value) {
            addCriterion("io_flag <=", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagLike(String value) {
            addCriterion("io_flag like", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagNotLike(String value) {
            addCriterion("io_flag not like", value, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagIn(List<String> values) {
            addCriterion("io_flag in", values, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagNotIn(List<String> values) {
            addCriterion("io_flag not in", values, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagBetween(String value1, String value2) {
            addCriterion("io_flag between", value1, value2, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andIoFlagNotBetween(String value1, String value2) {
            addCriterion("io_flag not between", value1, value2, "ioFlag");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNull() {
            addCriterion("bill_type is null");
            return (Criteria) this;
        }

        public Criteria andBillTypeIsNotNull() {
            addCriterion("bill_type is not null");
            return (Criteria) this;
        }

        public Criteria andBillTypeEqualTo(String value) {
            addCriterion("bill_type =", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotEqualTo(String value) {
            addCriterion("bill_type <>", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThan(String value) {
            addCriterion("bill_type >", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bill_type >=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThan(String value) {
            addCriterion("bill_type <", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLessThanOrEqualTo(String value) {
            addCriterion("bill_type <=", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeLike(String value) {
            addCriterion("bill_type like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotLike(String value) {
            addCriterion("bill_type not like", value, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeIn(List<String> values) {
            addCriterion("bill_type in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotIn(List<String> values) {
            addCriterion("bill_type not in", values, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeBetween(String value1, String value2) {
            addCriterion("bill_type between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillTypeNotBetween(String value1, String value2) {
            addCriterion("bill_type not between", value1, value2, "billType");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNull() {
            addCriterion("bill_date is null");
            return (Criteria) this;
        }

        public Criteria andBillDateIsNotNull() {
            addCriterion("bill_date is not null");
            return (Criteria) this;
        }

        public Criteria andBillDateEqualTo(Date value) {
            addCriterion("bill_date =", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotEqualTo(Date value) {
            addCriterion("bill_date <>", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThan(Date value) {
            addCriterion("bill_date >", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateGreaterThanOrEqualTo(Date value) {
            addCriterion("bill_date >=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThan(Date value) {
            addCriterion("bill_date <", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateLessThanOrEqualTo(Date value) {
            addCriterion("bill_date <=", value, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateIn(List<Date> values) {
            addCriterion("bill_date in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotIn(List<Date> values) {
            addCriterion("bill_date not in", values, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateBetween(Date value1, Date value2) {
            addCriterion("bill_date between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andBillDateNotBetween(Date value1, Date value2) {
            addCriterion("bill_date not between", value1, value2, "billDate");
            return (Criteria) this;
        }

        public Criteria andPicsPathIsNull() {
            addCriterion("pics_path is null");
            return (Criteria) this;
        }

        public Criteria andPicsPathIsNotNull() {
            addCriterion("pics_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicsPathEqualTo(String value) {
            addCriterion("pics_path =", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotEqualTo(String value) {
            addCriterion("pics_path <>", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathGreaterThan(String value) {
            addCriterion("pics_path >", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathGreaterThanOrEqualTo(String value) {
            addCriterion("pics_path >=", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLessThan(String value) {
            addCriterion("pics_path <", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLessThanOrEqualTo(String value) {
            addCriterion("pics_path <=", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathLike(String value) {
            addCriterion("pics_path like", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotLike(String value) {
            addCriterion("pics_path not like", value, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathIn(List<String> values) {
            addCriterion("pics_path in", values, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotIn(List<String> values) {
            addCriterion("pics_path not in", values, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathBetween(String value1, String value2) {
            addCriterion("pics_path between", value1, value2, "picsPath");
            return (Criteria) this;
        }

        public Criteria andPicsPathNotBetween(String value1, String value2) {
            addCriterion("pics_path not between", value1, value2, "picsPath");
            return (Criteria) this;
        }

        public Criteria andImStateIsNull() {
            addCriterion("im_state is null");
            return (Criteria) this;
        }

        public Criteria andImStateIsNotNull() {
            addCriterion("im_state is not null");
            return (Criteria) this;
        }

        public Criteria andImStateEqualTo(String value) {
            addCriterion("im_state =", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateNotEqualTo(String value) {
            addCriterion("im_state <>", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateGreaterThan(String value) {
            addCriterion("im_state >", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateGreaterThanOrEqualTo(String value) {
            addCriterion("im_state >=", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateLessThan(String value) {
            addCriterion("im_state <", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateLessThanOrEqualTo(String value) {
            addCriterion("im_state <=", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateLike(String value) {
            addCriterion("im_state like", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateNotLike(String value) {
            addCriterion("im_state not like", value, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateIn(List<String> values) {
            addCriterion("im_state in", values, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateNotIn(List<String> values) {
            addCriterion("im_state not in", values, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateBetween(String value1, String value2) {
            addCriterion("im_state between", value1, value2, "imState");
            return (Criteria) this;
        }

        public Criteria andImStateNotBetween(String value1, String value2) {
            addCriterion("im_state not between", value1, value2, "imState");
            return (Criteria) this;
        }

        public Criteria andMasDescIsNull() {
            addCriterion("mas_desc is null");
            return (Criteria) this;
        }

        public Criteria andMasDescIsNotNull() {
            addCriterion("mas_desc is not null");
            return (Criteria) this;
        }

        public Criteria andMasDescEqualTo(String value) {
            addCriterion("mas_desc =", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotEqualTo(String value) {
            addCriterion("mas_desc <>", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescGreaterThan(String value) {
            addCriterion("mas_desc >", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescGreaterThanOrEqualTo(String value) {
            addCriterion("mas_desc >=", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLessThan(String value) {
            addCriterion("mas_desc <", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLessThanOrEqualTo(String value) {
            addCriterion("mas_desc <=", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescLike(String value) {
            addCriterion("mas_desc like", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotLike(String value) {
            addCriterion("mas_desc not like", value, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescIn(List<String> values) {
            addCriterion("mas_desc in", values, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotIn(List<String> values) {
            addCriterion("mas_desc not in", values, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescBetween(String value1, String value2) {
            addCriterion("mas_desc between", value1, value2, "masDesc");
            return (Criteria) this;
        }

        public Criteria andMasDescNotBetween(String value1, String value2) {
            addCriterion("mas_desc not between", value1, value2, "masDesc");
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
     * This class:org.mybatis.generator.config.Context@28f67ac7
     *  prd_invent_chg
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
     *  prd_invent_chg
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