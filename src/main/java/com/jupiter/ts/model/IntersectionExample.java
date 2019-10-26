package com.jupiter.ts.model;

import java.util.ArrayList;
import java.util.List;

public class IntersectionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public IntersectionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIsDdIsNull() {
            addCriterion("is_dd is null");
            return (Criteria) this;
        }

        public Criteria andIsDdIsNotNull() {
            addCriterion("is_dd is not null");
            return (Criteria) this;
        }

        public Criteria andIsDdEqualTo(String value) {
            addCriterion("is_dd =", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdNotEqualTo(String value) {
            addCriterion("is_dd <>", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdGreaterThan(String value) {
            addCriterion("is_dd >", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdGreaterThanOrEqualTo(String value) {
            addCriterion("is_dd >=", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdLessThan(String value) {
            addCriterion("is_dd <", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdLessThanOrEqualTo(String value) {
            addCriterion("is_dd <=", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdLike(String value) {
            addCriterion("is_dd like", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdNotLike(String value) {
            addCriterion("is_dd not like", value, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdIn(List<String> values) {
            addCriterion("is_dd in", values, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdNotIn(List<String> values) {
            addCriterion("is_dd not in", values, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdBetween(String value1, String value2) {
            addCriterion("is_dd between", value1, value2, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDdNotBetween(String value1, String value2) {
            addCriterion("is_dd not between", value1, value2, "isDd");
            return (Criteria) this;
        }

        public Criteria andIsDlIsNull() {
            addCriterion("is_dl is null");
            return (Criteria) this;
        }

        public Criteria andIsDlIsNotNull() {
            addCriterion("is_dl is not null");
            return (Criteria) this;
        }

        public Criteria andIsDlEqualTo(String value) {
            addCriterion("is_dl =", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlNotEqualTo(String value) {
            addCriterion("is_dl <>", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlGreaterThan(String value) {
            addCriterion("is_dl >", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlGreaterThanOrEqualTo(String value) {
            addCriterion("is_dl >=", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlLessThan(String value) {
            addCriterion("is_dl <", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlLessThanOrEqualTo(String value) {
            addCriterion("is_dl <=", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlLike(String value) {
            addCriterion("is_dl like", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlNotLike(String value) {
            addCriterion("is_dl not like", value, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlIn(List<String> values) {
            addCriterion("is_dl in", values, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlNotIn(List<String> values) {
            addCriterion("is_dl not in", values, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlBetween(String value1, String value2) {
            addCriterion("is_dl between", value1, value2, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsDlNotBetween(String value1, String value2) {
            addCriterion("is_dl not between", value1, value2, "isDl");
            return (Criteria) this;
        }

        public Criteria andIsXhIsNull() {
            addCriterion("is_xh is null");
            return (Criteria) this;
        }

        public Criteria andIsXhIsNotNull() {
            addCriterion("is_xh is not null");
            return (Criteria) this;
        }

        public Criteria andIsXhEqualTo(String value) {
            addCriterion("is_xh =", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhNotEqualTo(String value) {
            addCriterion("is_xh <>", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhGreaterThan(String value) {
            addCriterion("is_xh >", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhGreaterThanOrEqualTo(String value) {
            addCriterion("is_xh >=", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhLessThan(String value) {
            addCriterion("is_xh <", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhLessThanOrEqualTo(String value) {
            addCriterion("is_xh <=", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhLike(String value) {
            addCriterion("is_xh like", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhNotLike(String value) {
            addCriterion("is_xh not like", value, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhIn(List<String> values) {
            addCriterion("is_xh in", values, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhNotIn(List<String> values) {
            addCriterion("is_xh not in", values, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhBetween(String value1, String value2) {
            addCriterion("is_xh between", value1, value2, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsXhNotBetween(String value1, String value2) {
            addCriterion("is_xh not between", value1, value2, "isXh");
            return (Criteria) this;
        }

        public Criteria andIsSdIsNull() {
            addCriterion("is_sd is null");
            return (Criteria) this;
        }

        public Criteria andIsSdIsNotNull() {
            addCriterion("is_sd is not null");
            return (Criteria) this;
        }

        public Criteria andIsSdEqualTo(Integer value) {
            addCriterion("is_sd =", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdNotEqualTo(Integer value) {
            addCriterion("is_sd <>", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdGreaterThan(Integer value) {
            addCriterion("is_sd >", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_sd >=", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdLessThan(Integer value) {
            addCriterion("is_sd <", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdLessThanOrEqualTo(Integer value) {
            addCriterion("is_sd <=", value, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdIn(List<Integer> values) {
            addCriterion("is_sd in", values, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdNotIn(List<Integer> values) {
            addCriterion("is_sd not in", values, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdBetween(Integer value1, Integer value2) {
            addCriterion("is_sd between", value1, value2, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsSdNotBetween(Integer value1, Integer value2) {
            addCriterion("is_sd not between", value1, value2, "isSd");
            return (Criteria) this;
        }

        public Criteria andIsXwIsNull() {
            addCriterion("is_xw is null");
            return (Criteria) this;
        }

        public Criteria andIsXwIsNotNull() {
            addCriterion("is_xw is not null");
            return (Criteria) this;
        }

        public Criteria andIsXwEqualTo(Integer value) {
            addCriterion("is_xw =", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwNotEqualTo(Integer value) {
            addCriterion("is_xw <>", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwGreaterThan(Integer value) {
            addCriterion("is_xw >", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_xw >=", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwLessThan(Integer value) {
            addCriterion("is_xw <", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwLessThanOrEqualTo(Integer value) {
            addCriterion("is_xw <=", value, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwIn(List<Integer> values) {
            addCriterion("is_xw in", values, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwNotIn(List<Integer> values) {
            addCriterion("is_xw not in", values, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwBetween(Integer value1, Integer value2) {
            addCriterion("is_xw between", value1, value2, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsXwNotBetween(Integer value1, Integer value2) {
            addCriterion("is_xw not between", value1, value2, "isXw");
            return (Criteria) this;
        }

        public Criteria andIsWhIsNull() {
            addCriterion("is_wh is null");
            return (Criteria) this;
        }

        public Criteria andIsWhIsNotNull() {
            addCriterion("is_wh is not null");
            return (Criteria) this;
        }

        public Criteria andIsWhEqualTo(String value) {
            addCriterion("is_wh =", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhNotEqualTo(String value) {
            addCriterion("is_wh <>", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhGreaterThan(String value) {
            addCriterion("is_wh >", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhGreaterThanOrEqualTo(String value) {
            addCriterion("is_wh >=", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhLessThan(String value) {
            addCriterion("is_wh <", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhLessThanOrEqualTo(String value) {
            addCriterion("is_wh <=", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhLike(String value) {
            addCriterion("is_wh like", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhNotLike(String value) {
            addCriterion("is_wh not like", value, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhIn(List<String> values) {
            addCriterion("is_wh in", values, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhNotIn(List<String> values) {
            addCriterion("is_wh not in", values, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhBetween(String value1, String value2) {
            addCriterion("is_wh between", value1, value2, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsWhNotBetween(String value1, String value2) {
            addCriterion("is_wh not between", value1, value2, "isWh");
            return (Criteria) this;
        }

        public Criteria andIsLbIsNull() {
            addCriterion("is_lb is null");
            return (Criteria) this;
        }

        public Criteria andIsLbIsNotNull() {
            addCriterion("is_lb is not null");
            return (Criteria) this;
        }

        public Criteria andIsLbEqualTo(String value) {
            addCriterion("is_lb =", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbNotEqualTo(String value) {
            addCriterion("is_lb <>", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbGreaterThan(String value) {
            addCriterion("is_lb >", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbGreaterThanOrEqualTo(String value) {
            addCriterion("is_lb >=", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbLessThan(String value) {
            addCriterion("is_lb <", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbLessThanOrEqualTo(String value) {
            addCriterion("is_lb <=", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbLike(String value) {
            addCriterion("is_lb like", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbNotLike(String value) {
            addCriterion("is_lb not like", value, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbIn(List<String> values) {
            addCriterion("is_lb in", values, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbNotIn(List<String> values) {
            addCriterion("is_lb not in", values, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbBetween(String value1, String value2) {
            addCriterion("is_lb between", value1, value2, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsLbNotBetween(String value1, String value2) {
            addCriterion("is_lb not between", value1, value2, "isLb");
            return (Criteria) this;
        }

        public Criteria andIsCreateIsNull() {
            addCriterion("is_create is null");
            return (Criteria) this;
        }

        public Criteria andIsCreateIsNotNull() {
            addCriterion("is_create is not null");
            return (Criteria) this;
        }

        public Criteria andIsCreateEqualTo(Long value) {
            addCriterion("is_create =", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateNotEqualTo(Long value) {
            addCriterion("is_create <>", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateGreaterThan(Long value) {
            addCriterion("is_create >", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateGreaterThanOrEqualTo(Long value) {
            addCriterion("is_create >=", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateLessThan(Long value) {
            addCriterion("is_create <", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateLessThanOrEqualTo(Long value) {
            addCriterion("is_create <=", value, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateIn(List<Long> values) {
            addCriterion("is_create in", values, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateNotIn(List<Long> values) {
            addCriterion("is_create not in", values, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateBetween(Long value1, Long value2) {
            addCriterion("is_create between", value1, value2, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsCreateNotBetween(Long value1, Long value2) {
            addCriterion("is_create not between", value1, value2, "isCreate");
            return (Criteria) this;
        }

        public Criteria andIsModifiedIsNull() {
            addCriterion("is_modified is null");
            return (Criteria) this;
        }

        public Criteria andIsModifiedIsNotNull() {
            addCriterion("is_modified is not null");
            return (Criteria) this;
        }

        public Criteria andIsModifiedEqualTo(Long value) {
            addCriterion("is_modified =", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedNotEqualTo(Long value) {
            addCriterion("is_modified <>", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedGreaterThan(Long value) {
            addCriterion("is_modified >", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedGreaterThanOrEqualTo(Long value) {
            addCriterion("is_modified >=", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedLessThan(Long value) {
            addCriterion("is_modified <", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedLessThanOrEqualTo(Long value) {
            addCriterion("is_modified <=", value, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedIn(List<Long> values) {
            addCriterion("is_modified in", values, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedNotIn(List<Long> values) {
            addCriterion("is_modified not in", values, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedBetween(Long value1, Long value2) {
            addCriterion("is_modified between", value1, value2, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsModifiedNotBetween(Long value1, Long value2) {
            addCriterion("is_modified not between", value1, value2, "isModified");
            return (Criteria) this;
        }

        public Criteria andIsNameIsNull() {
            addCriterion("is_name is null");
            return (Criteria) this;
        }

        public Criteria andIsNameIsNotNull() {
            addCriterion("is_name is not null");
            return (Criteria) this;
        }

        public Criteria andIsNameEqualTo(String value) {
            addCriterion("is_name =", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameNotEqualTo(String value) {
            addCriterion("is_name <>", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameGreaterThan(String value) {
            addCriterion("is_name >", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameGreaterThanOrEqualTo(String value) {
            addCriterion("is_name >=", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameLessThan(String value) {
            addCriterion("is_name <", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameLessThanOrEqualTo(String value) {
            addCriterion("is_name <=", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameLike(String value) {
            addCriterion("is_name like", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameNotLike(String value) {
            addCriterion("is_name not like", value, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameIn(List<String> values) {
            addCriterion("is_name in", values, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameNotIn(List<String> values) {
            addCriterion("is_name not in", values, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameBetween(String value1, String value2) {
            addCriterion("is_name between", value1, value2, "isName");
            return (Criteria) this;
        }

        public Criteria andIsNameNotBetween(String value1, String value2) {
            addCriterion("is_name not between", value1, value2, "isName");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ts_is
     *
     * @mbg.generated do_not_delete_during_merge Sat Oct 26 15:51:37 GMT+08:00 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ts_is
     *
     * @mbg.generated Sat Oct 26 15:51:37 GMT+08:00 2019
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