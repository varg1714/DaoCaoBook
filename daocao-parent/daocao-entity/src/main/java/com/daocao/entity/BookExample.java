package com.daocao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BookExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BookExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPublishdateIsNull() {
            addCriterion("publishDate is null");
            return (Criteria) this;
        }

        public Criteria andPublishdateIsNotNull() {
            addCriterion("publishDate is not null");
            return (Criteria) this;
        }

        public Criteria andPublishdateEqualTo(Date value) {
            addCriterionForJDBCDate("publishDate =", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("publishDate <>", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateGreaterThan(Date value) {
            addCriterionForJDBCDate("publishDate >", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("publishDate >=", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateLessThan(Date value) {
            addCriterionForJDBCDate("publishDate <", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("publishDate <=", value, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateIn(List<Date> values) {
            addCriterionForJDBCDate("publishDate in", values, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("publishDate not in", values, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("publishDate between", value1, value2, "publishdate");
            return (Criteria) this;
        }

        public Criteria andPublishdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("publishDate not between", value1, value2, "publishdate");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("publisher not between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNull() {
            addCriterion("isbn is null");
            return (Criteria) this;
        }

        public Criteria andIsbnIsNotNull() {
            addCriterion("isbn is not null");
            return (Criteria) this;
        }

        public Criteria andIsbnEqualTo(String value) {
            addCriterion("isbn =", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotEqualTo(String value) {
            addCriterion("isbn <>", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThan(String value) {
            addCriterion("isbn >", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnGreaterThanOrEqualTo(String value) {
            addCriterion("isbn >=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThan(String value) {
            addCriterion("isbn <", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLessThanOrEqualTo(String value) {
            addCriterion("isbn <=", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnLike(String value) {
            addCriterion("isbn like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotLike(String value) {
            addCriterion("isbn not like", value, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnIn(List<String> values) {
            addCriterion("isbn in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotIn(List<String> values) {
            addCriterion("isbn not in", values, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnBetween(String value1, String value2) {
            addCriterion("isbn between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andIsbnNotBetween(String value1, String value2) {
            addCriterion("isbn not between", value1, value2, "isbn");
            return (Criteria) this;
        }

        public Criteria andPublishpriceIsNull() {
            addCriterion("publishPrice is null");
            return (Criteria) this;
        }

        public Criteria andPublishpriceIsNotNull() {
            addCriterion("publishPrice is not null");
            return (Criteria) this;
        }

        public Criteria andPublishpriceEqualTo(BigDecimal value) {
            addCriterion("publishPrice =", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceNotEqualTo(BigDecimal value) {
            addCriterion("publishPrice <>", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceGreaterThan(BigDecimal value) {
            addCriterion("publishPrice >", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("publishPrice >=", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceLessThan(BigDecimal value) {
            addCriterion("publishPrice <", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("publishPrice <=", value, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceIn(List<BigDecimal> values) {
            addCriterion("publishPrice in", values, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceNotIn(List<BigDecimal> values) {
            addCriterion("publishPrice not in", values, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("publishPrice between", value1, value2, "publishprice");
            return (Criteria) this;
        }

        public Criteria andPublishpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("publishPrice not between", value1, value2, "publishprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNull() {
            addCriterion("sellPrice is null");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNotNull() {
            addCriterion("sellPrice is not null");
            return (Criteria) this;
        }

        public Criteria andSellpriceEqualTo(BigDecimal value) {
            addCriterion("sellPrice =", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotEqualTo(BigDecimal value) {
            addCriterion("sellPrice <>", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThan(BigDecimal value) {
            addCriterion("sellPrice >", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellPrice >=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThan(BigDecimal value) {
            addCriterion("sellPrice <", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellPrice <=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIn(List<BigDecimal> values) {
            addCriterion("sellPrice in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotIn(List<BigDecimal> values) {
            addCriterion("sellPrice not in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellPrice between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellPrice not between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andBindtypeIsNull() {
            addCriterion("bindType is null");
            return (Criteria) this;
        }

        public Criteria andBindtypeIsNotNull() {
            addCriterion("bindType is not null");
            return (Criteria) this;
        }

        public Criteria andBindtypeEqualTo(String value) {
            addCriterion("bindType =", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeNotEqualTo(String value) {
            addCriterion("bindType <>", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeGreaterThan(String value) {
            addCriterion("bindType >", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeGreaterThanOrEqualTo(String value) {
            addCriterion("bindType >=", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeLessThan(String value) {
            addCriterion("bindType <", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeLessThanOrEqualTo(String value) {
            addCriterion("bindType <=", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeLike(String value) {
            addCriterion("bindType like", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeNotLike(String value) {
            addCriterion("bindType not like", value, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeIn(List<String> values) {
            addCriterion("bindType in", values, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeNotIn(List<String> values) {
            addCriterion("bindType not in", values, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeBetween(String value1, String value2) {
            addCriterion("bindType between", value1, value2, "bindtype");
            return (Criteria) this;
        }

        public Criteria andBindtypeNotBetween(String value1, String value2) {
            addCriterion("bindType not between", value1, value2, "bindtype");
            return (Criteria) this;
        }

        public Criteria andPapersizeIsNull() {
            addCriterion("paperSize is null");
            return (Criteria) this;
        }

        public Criteria andPapersizeIsNotNull() {
            addCriterion("paperSize is not null");
            return (Criteria) this;
        }

        public Criteria andPapersizeEqualTo(String value) {
            addCriterion("paperSize =", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeNotEqualTo(String value) {
            addCriterion("paperSize <>", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeGreaterThan(String value) {
            addCriterion("paperSize >", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeGreaterThanOrEqualTo(String value) {
            addCriterion("paperSize >=", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeLessThan(String value) {
            addCriterion("paperSize <", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeLessThanOrEqualTo(String value) {
            addCriterion("paperSize <=", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeLike(String value) {
            addCriterion("paperSize like", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeNotLike(String value) {
            addCriterion("paperSize not like", value, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeIn(List<String> values) {
            addCriterion("paperSize in", values, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeNotIn(List<String> values) {
            addCriterion("paperSize not in", values, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeBetween(String value1, String value2) {
            addCriterion("paperSize between", value1, value2, "papersize");
            return (Criteria) this;
        }

        public Criteria andPapersizeNotBetween(String value1, String value2) {
            addCriterion("paperSize not between", value1, value2, "papersize");
            return (Criteria) this;
        }

        public Criteria andPagenumberIsNull() {
            addCriterion("pageNumber is null");
            return (Criteria) this;
        }

        public Criteria andPagenumberIsNotNull() {
            addCriterion("pageNumber is not null");
            return (Criteria) this;
        }

        public Criteria andPagenumberEqualTo(Integer value) {
            addCriterion("pageNumber =", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberNotEqualTo(Integer value) {
            addCriterion("pageNumber <>", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberGreaterThan(Integer value) {
            addCriterion("pageNumber >", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("pageNumber >=", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberLessThan(Integer value) {
            addCriterion("pageNumber <", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberLessThanOrEqualTo(Integer value) {
            addCriterion("pageNumber <=", value, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberIn(List<Integer> values) {
            addCriterion("pageNumber in", values, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberNotIn(List<Integer> values) {
            addCriterion("pageNumber not in", values, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberBetween(Integer value1, Integer value2) {
            addCriterion("pageNumber between", value1, value2, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andPagenumberNotBetween(Integer value1, Integer value2) {
            addCriterion("pageNumber not between", value1, value2, "pagenumber");
            return (Criteria) this;
        }

        public Criteria andBooktype1IsNull() {
            addCriterion("bookType1 is null");
            return (Criteria) this;
        }

        public Criteria andBooktype1IsNotNull() {
            addCriterion("bookType1 is not null");
            return (Criteria) this;
        }

        public Criteria andBooktype1EqualTo(Integer value) {
            addCriterion("bookType1 =", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1NotEqualTo(Integer value) {
            addCriterion("bookType1 <>", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1GreaterThan(Integer value) {
            addCriterion("bookType1 >", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1GreaterThanOrEqualTo(Integer value) {
            addCriterion("bookType1 >=", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1LessThan(Integer value) {
            addCriterion("bookType1 <", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1LessThanOrEqualTo(Integer value) {
            addCriterion("bookType1 <=", value, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1In(List<Integer> values) {
            addCriterion("bookType1 in", values, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1NotIn(List<Integer> values) {
            addCriterion("bookType1 not in", values, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1Between(Integer value1, Integer value2) {
            addCriterion("bookType1 between", value1, value2, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype1NotBetween(Integer value1, Integer value2) {
            addCriterion("bookType1 not between", value1, value2, "booktype1");
            return (Criteria) this;
        }

        public Criteria andBooktype2IsNull() {
            addCriterion("bookType2 is null");
            return (Criteria) this;
        }

        public Criteria andBooktype2IsNotNull() {
            addCriterion("bookType2 is not null");
            return (Criteria) this;
        }

        public Criteria andBooktype2EqualTo(Integer value) {
            addCriterion("bookType2 =", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2NotEqualTo(Integer value) {
            addCriterion("bookType2 <>", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2GreaterThan(Integer value) {
            addCriterion("bookType2 >", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2GreaterThanOrEqualTo(Integer value) {
            addCriterion("bookType2 >=", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2LessThan(Integer value) {
            addCriterion("bookType2 <", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2LessThanOrEqualTo(Integer value) {
            addCriterion("bookType2 <=", value, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2In(List<Integer> values) {
            addCriterion("bookType2 in", values, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2NotIn(List<Integer> values) {
            addCriterion("bookType2 not in", values, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2Between(Integer value1, Integer value2) {
            addCriterion("bookType2 between", value1, value2, "booktype2");
            return (Criteria) this;
        }

        public Criteria andBooktype2NotBetween(Integer value1, Integer value2) {
            addCriterion("bookType2 not between", value1, value2, "booktype2");
            return (Criteria) this;
        }

        public Criteria andSellerIsNull() {
            addCriterion("seller is null");
            return (Criteria) this;
        }

        public Criteria andSellerIsNotNull() {
            addCriterion("seller is not null");
            return (Criteria) this;
        }

        public Criteria andSellerEqualTo(String value) {
            addCriterion("seller =", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotEqualTo(String value) {
            addCriterion("seller <>", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerGreaterThan(String value) {
            addCriterion("seller >", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerGreaterThanOrEqualTo(String value) {
            addCriterion("seller >=", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLessThan(String value) {
            addCriterion("seller <", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLessThanOrEqualTo(String value) {
            addCriterion("seller <=", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerLike(String value) {
            addCriterion("seller like", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotLike(String value) {
            addCriterion("seller not like", value, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerIn(List<String> values) {
            addCriterion("seller in", values, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotIn(List<String> values) {
            addCriterion("seller not in", values, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerBetween(String value1, String value2) {
            addCriterion("seller between", value1, value2, "seller");
            return (Criteria) this;
        }

        public Criteria andSellerNotBetween(String value1, String value2) {
            addCriterion("seller not between", value1, value2, "seller");
            return (Criteria) this;
        }

        public Criteria andIssellIsNull() {
            addCriterion("isSell is null");
            return (Criteria) this;
        }

        public Criteria andIssellIsNotNull() {
            addCriterion("isSell is not null");
            return (Criteria) this;
        }

        public Criteria andIssellEqualTo(String value) {
            addCriterion("isSell =", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellNotEqualTo(String value) {
            addCriterion("isSell <>", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellGreaterThan(String value) {
            addCriterion("isSell >", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellGreaterThanOrEqualTo(String value) {
            addCriterion("isSell >=", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellLessThan(String value) {
            addCriterion("isSell <", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellLessThanOrEqualTo(String value) {
            addCriterion("isSell <=", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellLike(String value) {
            addCriterion("isSell like", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellNotLike(String value) {
            addCriterion("isSell not like", value, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellIn(List<String> values) {
            addCriterion("isSell in", values, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellNotIn(List<String> values) {
            addCriterion("isSell not in", values, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellBetween(String value1, String value2) {
            addCriterion("isSell between", value1, value2, "issell");
            return (Criteria) this;
        }

        public Criteria andIssellNotBetween(String value1, String value2) {
            addCriterion("isSell not between", value1, value2, "issell");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andBookimagesIsNull() {
            addCriterion("bookImages is null");
            return (Criteria) this;
        }

        public Criteria andBookimagesIsNotNull() {
            addCriterion("bookImages is not null");
            return (Criteria) this;
        }

        public Criteria andBookimagesEqualTo(String value) {
            addCriterion("bookImages =", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesNotEqualTo(String value) {
            addCriterion("bookImages <>", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesGreaterThan(String value) {
            addCriterion("bookImages >", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesGreaterThanOrEqualTo(String value) {
            addCriterion("bookImages >=", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesLessThan(String value) {
            addCriterion("bookImages <", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesLessThanOrEqualTo(String value) {
            addCriterion("bookImages <=", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesLike(String value) {
            addCriterion("bookImages like", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesNotLike(String value) {
            addCriterion("bookImages not like", value, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesIn(List<String> values) {
            addCriterion("bookImages in", values, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesNotIn(List<String> values) {
            addCriterion("bookImages not in", values, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesBetween(String value1, String value2) {
            addCriterion("bookImages between", value1, value2, "bookimages");
            return (Criteria) this;
        }

        public Criteria andBookimagesNotBetween(String value1, String value2) {
            addCriterion("bookImages not between", value1, value2, "bookimages");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andIsauditIsNull() {
            addCriterion("isAudit is null");
            return (Criteria) this;
        }

        public Criteria andIsauditIsNotNull() {
            addCriterion("isAudit is not null");
            return (Criteria) this;
        }

        public Criteria andIsauditEqualTo(String value) {
            addCriterion("isAudit =", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditNotEqualTo(String value) {
            addCriterion("isAudit <>", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditGreaterThan(String value) {
            addCriterion("isAudit >", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditGreaterThanOrEqualTo(String value) {
            addCriterion("isAudit >=", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditLessThan(String value) {
            addCriterion("isAudit <", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditLessThanOrEqualTo(String value) {
            addCriterion("isAudit <=", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditLike(String value) {
            addCriterion("isAudit like", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditNotLike(String value) {
            addCriterion("isAudit not like", value, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditIn(List<String> values) {
            addCriterion("isAudit in", values, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditNotIn(List<String> values) {
            addCriterion("isAudit not in", values, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditBetween(String value1, String value2) {
            addCriterion("isAudit between", value1, value2, "isaudit");
            return (Criteria) this;
        }

        public Criteria andIsauditNotBetween(String value1, String value2) {
            addCriterion("isAudit not between", value1, value2, "isaudit");
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