package com.joyveb.cashmanage.entity;

import java.util.ArrayList;
import java.util.List;

public class StockManageExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    protected int offset;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    protected int limit;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    protected String sumCol;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    protected String groupSelClause;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    protected String groupByClause;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public StockManageExample() {
        oredCriteria = new ArrayList<Criteria>();
        offset = 0;
        limit = Integer.MAX_VALUE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
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
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        this.offset= 0;
        this.limit= Integer.MAX_VALUE;
        this.sumCol=null;
        this.groupSelClause=null;
        this.groupByClause=null;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    public void setOffset(int offset) {
         this.offset = offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    public int getOffset() {
          return offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    public void setLimit(int limit) {
         this.limit = limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    *
    * @generated Mon Mar 11 15:04:20 CST 2017
    */
    public int getLimit() {
          return limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public void setSumCol(String sumCol) {
         this.sumCol = sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public String getSumCol() {
          return sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public void setGroupSelClause(String groupSelClause) {
         this.groupSelClause = groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public String getGroupSelClause() {
          return groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public void setGroupByClause(String groupByClause) {
         this.groupByClause = groupByClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_stock_manage
    */
    public String getGroupByClause() {
          return groupByClause;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
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

        public Criteria andGamecodeIsNull() {
            addCriterion("GAMECODE is null");
            return (Criteria) this;
        }

        public Criteria andGamecodeIsNotNull() {
            addCriterion("GAMECODE is not null");
            return (Criteria) this;
        }

        public Criteria andGamecodeEqualTo(String value) {
            addCriterion("GAMECODE =", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeNotEqualTo(String value) {
            addCriterion("GAMECODE <>", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeGreaterThan(String value) {
            addCriterion("GAMECODE >", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeGreaterThanOrEqualTo(String value) {
            addCriterion("GAMECODE >=", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeLessThan(String value) {
            addCriterion("GAMECODE <", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeLessThanOrEqualTo(String value) {
            addCriterion("GAMECODE <=", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeLike(String value) {
            addCriterion("GAMECODE like", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeNotLike(String value) {
            addCriterion("GAMECODE not like", value, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeIn(List<String> values) {
            addCriterion("GAMECODE in", values, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeNotIn(List<String> values) {
            addCriterion("GAMECODE not in", values, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeBetween(String value1, String value2) {
            addCriterion("GAMECODE between", value1, value2, "gamecode");
            return (Criteria) this;
        }

        public Criteria andGamecodeNotBetween(String value1, String value2) {
            addCriterion("GAMECODE not between", value1, value2, "gamecode");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Long value) {
            addCriterion("TOTAL =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Long value) {
            addCriterion("TOTAL <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Long value) {
            addCriterion("TOTAL >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Long value) {
            addCriterion("TOTAL <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Long> values) {
            addCriterion("TOTAL in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Long> values) {
            addCriterion("TOTAL not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Long value1, Long value2) {
            addCriterion("TOTAL between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("UPDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("UPDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Long value) {
            addCriterion("UPDATETIME =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Long value) {
            addCriterion("UPDATETIME <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Long value) {
            addCriterion("UPDATETIME >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATETIME >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Long value) {
            addCriterion("UPDATETIME <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Long value) {
            addCriterion("UPDATETIME <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Long> values) {
            addCriterion("UPDATETIME in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Long> values) {
            addCriterion("UPDATETIME not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Long value1, Long value2) {
            addCriterion("UPDATETIME between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Long value1, Long value2) {
            addCriterion("UPDATETIME not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andGamecodeLikeInsensitive(String value) {
            addCriterion("upper(GAMECODE) like", value.toUpperCase(), "gamecode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_stock_manage
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 13 15:04:20 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_stock_manage
     *
     * @mbggenerated Mon Mar 13 15:04:20 CST 2017
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