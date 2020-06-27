package com.daocao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andPostageIsNull() {
            addCriterion("postage is null");
            return (Criteria) this;
        }

        public Criteria andPostageIsNotNull() {
            addCriterion("postage is not null");
            return (Criteria) this;
        }

        public Criteria andPostageEqualTo(BigDecimal value) {
            addCriterion("postage =", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotEqualTo(BigDecimal value) {
            addCriterion("postage <>", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageGreaterThan(BigDecimal value) {
            addCriterion("postage >", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("postage >=", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageLessThan(BigDecimal value) {
            addCriterion("postage <", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("postage <=", value, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageIn(List<BigDecimal> values) {
            addCriterion("postage in", values, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotIn(List<BigDecimal> values) {
            addCriterion("postage not in", values, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("postage between", value1, value2, "postage");
            return (Criteria) this;
        }

        public Criteria andPostageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("postage not between", value1, value2, "postage");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterionForJDBCDate("createDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("createDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("createDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterionForJDBCDate("createDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterionForJDBCDate("createDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("createDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("updateDate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updateDate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("updateDate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterionForJDBCDate("updateDate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterionForJDBCDate("updateDate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("updateDate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateDate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateDate not between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andPaydateIsNull() {
            addCriterion("payDate is null");
            return (Criteria) this;
        }

        public Criteria andPaydateIsNotNull() {
            addCriterion("payDate is not null");
            return (Criteria) this;
        }

        public Criteria andPaydateEqualTo(Date value) {
            addCriterionForJDBCDate("payDate =", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotEqualTo(Date value) {
            addCriterionForJDBCDate("payDate <>", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateGreaterThan(Date value) {
            addCriterionForJDBCDate("payDate >", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payDate >=", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateLessThan(Date value) {
            addCriterionForJDBCDate("payDate <", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("payDate <=", value, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateIn(List<Date> values) {
            addCriterionForJDBCDate("payDate in", values, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotIn(List<Date> values) {
            addCriterionForJDBCDate("payDate not in", values, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payDate between", value1, value2, "paydate");
            return (Criteria) this;
        }

        public Criteria andPaydateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("payDate not between", value1, value2, "paydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateIsNull() {
            addCriterion("deliveryDate is null");
            return (Criteria) this;
        }

        public Criteria andDeliverydateIsNotNull() {
            addCriterion("deliveryDate is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverydateEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate =", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateNotEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate <>", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateGreaterThan(Date value) {
            addCriterionForJDBCDate("deliveryDate >", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate >=", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateLessThan(Date value) {
            addCriterionForJDBCDate("deliveryDate <", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("deliveryDate <=", value, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateIn(List<Date> values) {
            addCriterionForJDBCDate("deliveryDate in", values, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateNotIn(List<Date> values) {
            addCriterionForJDBCDate("deliveryDate not in", values, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliveryDate between", value1, value2, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andDeliverydateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("deliveryDate not between", value1, value2, "deliverydate");
            return (Criteria) this;
        }

        public Criteria andOverdateIsNull() {
            addCriterion("overDate is null");
            return (Criteria) this;
        }

        public Criteria andOverdateIsNotNull() {
            addCriterion("overDate is not null");
            return (Criteria) this;
        }

        public Criteria andOverdateEqualTo(Date value) {
            addCriterionForJDBCDate("overDate =", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("overDate <>", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateGreaterThan(Date value) {
            addCriterionForJDBCDate("overDate >", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("overDate >=", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateLessThan(Date value) {
            addCriterionForJDBCDate("overDate <", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("overDate <=", value, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateIn(List<Date> values) {
            addCriterionForJDBCDate("overDate in", values, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("overDate not in", values, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("overDate between", value1, value2, "overdate");
            return (Criteria) this;
        }

        public Criteria andOverdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("overDate not between", value1, value2, "overdate");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNull() {
            addCriterion("logisticsCompany is null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIsNotNull() {
            addCriterion("logisticsCompany is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyEqualTo(String value) {
            addCriterion("logisticsCompany =", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotEqualTo(String value) {
            addCriterion("logisticsCompany <>", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThan(String value) {
            addCriterion("logisticsCompany >", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyGreaterThanOrEqualTo(String value) {
            addCriterion("logisticsCompany >=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThan(String value) {
            addCriterion("logisticsCompany <", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLessThanOrEqualTo(String value) {
            addCriterion("logisticsCompany <=", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyLike(String value) {
            addCriterion("logisticsCompany like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotLike(String value) {
            addCriterion("logisticsCompany not like", value, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyIn(List<String> values) {
            addCriterion("logisticsCompany in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotIn(List<String> values) {
            addCriterion("logisticsCompany not in", values, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyBetween(String value1, String value2) {
            addCriterion("logisticsCompany between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andLogisticscompanyNotBetween(String value1, String value2) {
            addCriterion("logisticsCompany not between", value1, value2, "logisticscompany");
            return (Criteria) this;
        }

        public Criteria andTracknumberIsNull() {
            addCriterion("trackNumber is null");
            return (Criteria) this;
        }

        public Criteria andTracknumberIsNotNull() {
            addCriterion("trackNumber is not null");
            return (Criteria) this;
        }

        public Criteria andTracknumberEqualTo(String value) {
            addCriterion("trackNumber =", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberNotEqualTo(String value) {
            addCriterion("trackNumber <>", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberGreaterThan(String value) {
            addCriterion("trackNumber >", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberGreaterThanOrEqualTo(String value) {
            addCriterion("trackNumber >=", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberLessThan(String value) {
            addCriterion("trackNumber <", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberLessThanOrEqualTo(String value) {
            addCriterion("trackNumber <=", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberLike(String value) {
            addCriterion("trackNumber like", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberNotLike(String value) {
            addCriterion("trackNumber not like", value, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberIn(List<String> values) {
            addCriterion("trackNumber in", values, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberNotIn(List<String> values) {
            addCriterion("trackNumber not in", values, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberBetween(String value1, String value2) {
            addCriterion("trackNumber between", value1, value2, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andTracknumberNotBetween(String value1, String value2) {
            addCriterion("trackNumber not between", value1, value2, "tracknumber");
            return (Criteria) this;
        }

        public Criteria andUsernoteIsNull() {
            addCriterion("userNote is null");
            return (Criteria) this;
        }

        public Criteria andUsernoteIsNotNull() {
            addCriterion("userNote is not null");
            return (Criteria) this;
        }

        public Criteria andUsernoteEqualTo(String value) {
            addCriterion("userNote =", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteNotEqualTo(String value) {
            addCriterion("userNote <>", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteGreaterThan(String value) {
            addCriterion("userNote >", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteGreaterThanOrEqualTo(String value) {
            addCriterion("userNote >=", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteLessThan(String value) {
            addCriterion("userNote <", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteLessThanOrEqualTo(String value) {
            addCriterion("userNote <=", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteLike(String value) {
            addCriterion("userNote like", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteNotLike(String value) {
            addCriterion("userNote not like", value, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteIn(List<String> values) {
            addCriterion("userNote in", values, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteNotIn(List<String> values) {
            addCriterion("userNote not in", values, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteBetween(String value1, String value2) {
            addCriterion("userNote between", value1, value2, "usernote");
            return (Criteria) this;
        }

        public Criteria andUsernoteNotBetween(String value1, String value2) {
            addCriterion("userNote not between", value1, value2, "usernote");
            return (Criteria) this;
        }

        public Criteria andUserevaluationIsNull() {
            addCriterion("userEvaluation is null");
            return (Criteria) this;
        }

        public Criteria andUserevaluationIsNotNull() {
            addCriterion("userEvaluation is not null");
            return (Criteria) this;
        }

        public Criteria andUserevaluationEqualTo(String value) {
            addCriterion("userEvaluation =", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationNotEqualTo(String value) {
            addCriterion("userEvaluation <>", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationGreaterThan(String value) {
            addCriterion("userEvaluation >", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationGreaterThanOrEqualTo(String value) {
            addCriterion("userEvaluation >=", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationLessThan(String value) {
            addCriterion("userEvaluation <", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationLessThanOrEqualTo(String value) {
            addCriterion("userEvaluation <=", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationLike(String value) {
            addCriterion("userEvaluation like", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationNotLike(String value) {
            addCriterion("userEvaluation not like", value, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationIn(List<String> values) {
            addCriterion("userEvaluation in", values, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationNotIn(List<String> values) {
            addCriterion("userEvaluation not in", values, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationBetween(String value1, String value2) {
            addCriterion("userEvaluation between", value1, value2, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andUserevaluationNotBetween(String value1, String value2) {
            addCriterion("userEvaluation not between", value1, value2, "userevaluation");
            return (Criteria) this;
        }

        public Criteria andEvltypeIsNull() {
            addCriterion("evlType is null");
            return (Criteria) this;
        }

        public Criteria andEvltypeIsNotNull() {
            addCriterion("evlType is not null");
            return (Criteria) this;
        }

        public Criteria andEvltypeEqualTo(String value) {
            addCriterion("evlType =", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeNotEqualTo(String value) {
            addCriterion("evlType <>", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeGreaterThan(String value) {
            addCriterion("evlType >", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeGreaterThanOrEqualTo(String value) {
            addCriterion("evlType >=", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeLessThan(String value) {
            addCriterion("evlType <", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeLessThanOrEqualTo(String value) {
            addCriterion("evlType <=", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeLike(String value) {
            addCriterion("evlType like", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeNotLike(String value) {
            addCriterion("evlType not like", value, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeIn(List<String> values) {
            addCriterion("evlType in", values, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeNotIn(List<String> values) {
            addCriterion("evlType not in", values, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeBetween(String value1, String value2) {
            addCriterion("evlType between", value1, value2, "evltype");
            return (Criteria) this;
        }

        public Criteria andEvltypeNotBetween(String value1, String value2) {
            addCriterion("evlType not between", value1, value2, "evltype");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNull() {
            addCriterion("sellerId is null");
            return (Criteria) this;
        }

        public Criteria andSelleridIsNotNull() {
            addCriterion("sellerId is not null");
            return (Criteria) this;
        }

        public Criteria andSelleridEqualTo(String value) {
            addCriterion("sellerId =", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotEqualTo(String value) {
            addCriterion("sellerId <>", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThan(String value) {
            addCriterion("sellerId >", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridGreaterThanOrEqualTo(String value) {
            addCriterion("sellerId >=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThan(String value) {
            addCriterion("sellerId <", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLessThanOrEqualTo(String value) {
            addCriterion("sellerId <=", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridLike(String value) {
            addCriterion("sellerId like", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotLike(String value) {
            addCriterion("sellerId not like", value, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridIn(List<String> values) {
            addCriterion("sellerId in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotIn(List<String> values) {
            addCriterion("sellerId not in", values, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridBetween(String value1, String value2) {
            addCriterion("sellerId between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andSelleridNotBetween(String value1, String value2) {
            addCriterion("sellerId not between", value1, value2, "sellerid");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("orderType is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("orderType is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(String value) {
            addCriterion("orderType =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(String value) {
            addCriterion("orderType <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(String value) {
            addCriterion("orderType >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(String value) {
            addCriterion("orderType >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(String value) {
            addCriterion("orderType <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(String value) {
            addCriterion("orderType <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLike(String value) {
            addCriterion("orderType like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotLike(String value) {
            addCriterion("orderType not like", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<String> values) {
            addCriterion("orderType in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<String> values) {
            addCriterion("orderType not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(String value1, String value2) {
            addCriterion("orderType between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(String value1, String value2) {
            addCriterion("orderType not between", value1, value2, "ordertype");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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