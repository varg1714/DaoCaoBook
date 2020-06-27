package com.daocao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReportExample() {
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

        public Criteria andInformerIsNull() {
            addCriterion("informer is null");
            return (Criteria) this;
        }

        public Criteria andInformerIsNotNull() {
            addCriterion("informer is not null");
            return (Criteria) this;
        }

        public Criteria andInformerEqualTo(String value) {
            addCriterion("informer =", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerNotEqualTo(String value) {
            addCriterion("informer <>", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerGreaterThan(String value) {
            addCriterion("informer >", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerGreaterThanOrEqualTo(String value) {
            addCriterion("informer >=", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerLessThan(String value) {
            addCriterion("informer <", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerLessThanOrEqualTo(String value) {
            addCriterion("informer <=", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerLike(String value) {
            addCriterion("informer like", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerNotLike(String value) {
            addCriterion("informer not like", value, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerIn(List<String> values) {
            addCriterion("informer in", values, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerNotIn(List<String> values) {
            addCriterion("informer not in", values, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerBetween(String value1, String value2) {
            addCriterion("informer between", value1, value2, "informer");
            return (Criteria) this;
        }

        public Criteria andInformerNotBetween(String value1, String value2) {
            addCriterion("informer not between", value1, value2, "informer");
            return (Criteria) this;
        }

        public Criteria andBookidIsNull() {
            addCriterion("bookId is null");
            return (Criteria) this;
        }

        public Criteria andBookidIsNotNull() {
            addCriterion("bookId is not null");
            return (Criteria) this;
        }

        public Criteria andBookidEqualTo(Integer value) {
            addCriterion("bookId =", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotEqualTo(Integer value) {
            addCriterion("bookId <>", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThan(Integer value) {
            addCriterion("bookId >", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bookId >=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThan(Integer value) {
            addCriterion("bookId <", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidLessThanOrEqualTo(Integer value) {
            addCriterion("bookId <=", value, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidIn(List<Integer> values) {
            addCriterion("bookId in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotIn(List<Integer> values) {
            addCriterion("bookId not in", values, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidBetween(Integer value1, Integer value2) {
            addCriterion("bookId between", value1, value2, "bookid");
            return (Criteria) this;
        }

        public Criteria andBookidNotBetween(Integer value1, Integer value2) {
            addCriterion("bookId not between", value1, value2, "bookid");
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

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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