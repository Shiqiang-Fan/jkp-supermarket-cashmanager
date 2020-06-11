package com.joyveb.cashmanage.entity;

import java.util.ArrayList;
import java.util.List;

public class WhiteChildExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    protected int offset;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    protected int limit;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    protected String sumCol;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    protected String groupSelClause;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    protected String groupByClause;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public WhiteChildExample() {
        oredCriteria = new ArrayList<Criteria>();
        offset = 0;
        limit = Integer.MAX_VALUE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
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
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
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
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    public void setOffset(int offset) {
         this.offset = offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    public int getOffset() {
          return offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    public void setLimit(int limit) {
         this.limit = limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    *
    * @generated Fri Mar 10 15:34:18 CST 2017
    */
    public int getLimit() {
          return limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public void setSumCol(String sumCol) {
         this.sumCol = sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public String getSumCol() {
          return sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public void setGroupSelClause(String groupSelClause) {
         this.groupSelClause = groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public String getGroupSelClause() {
          return groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public void setGroupByClause(String groupByClause) {
         this.groupByClause = groupByClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_acc_whitechild
    */
    public String getGroupByClause() {
          return groupByClause;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
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

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PASSWORD in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PASSWORD not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(String value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(String value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(String value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(String value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(String value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLike(String value) {
            addCriterion("PARENTID like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotLike(String value) {
            addCriterion("PARENTID not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<String> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<String> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(String value1, String value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(String value1, String value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNull() {
            addCriterion("DEVICETYPE is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNotNull() {
            addCriterion("DEVICETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeEqualTo(String value) {
            addCriterion("DEVICETYPE =", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotEqualTo(String value) {
            addCriterion("DEVICETYPE <>", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThan(String value) {
            addCriterion("DEVICETYPE >", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICETYPE >=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThan(String value) {
            addCriterion("DEVICETYPE <", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThanOrEqualTo(String value) {
            addCriterion("DEVICETYPE <=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLike(String value) {
            addCriterion("DEVICETYPE like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotLike(String value) {
            addCriterion("DEVICETYPE not like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIn(List<String> values) {
            addCriterion("DEVICETYPE in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotIn(List<String> values) {
            addCriterion("DEVICETYPE not in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeBetween(String value1, String value2) {
            addCriterion("DEVICETYPE between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotBetween(String value1, String value2) {
            addCriterion("DEVICETYPE not between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicenumIsNull() {
            addCriterion("DEVICENUM is null");
            return (Criteria) this;
        }

        public Criteria andDevicenumIsNotNull() {
            addCriterion("DEVICENUM is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenumEqualTo(String value) {
            addCriterion("DEVICENUM =", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumNotEqualTo(String value) {
            addCriterion("DEVICENUM <>", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumGreaterThan(String value) {
            addCriterion("DEVICENUM >", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICENUM >=", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumLessThan(String value) {
            addCriterion("DEVICENUM <", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumLessThanOrEqualTo(String value) {
            addCriterion("DEVICENUM <=", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumLike(String value) {
            addCriterion("DEVICENUM like", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumNotLike(String value) {
            addCriterion("DEVICENUM not like", value, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumIn(List<String> values) {
            addCriterion("DEVICENUM in", values, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumNotIn(List<String> values) {
            addCriterion("DEVICENUM not in", values, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumBetween(String value1, String value2) {
            addCriterion("DEVICENUM between", value1, value2, "devicenum");
            return (Criteria) this;
        }

        public Criteria andDevicenumNotBetween(String value1, String value2) {
            addCriterion("DEVICENUM not between", value1, value2, "devicenum");
            return (Criteria) this;
        }

        public Criteria andUuidLikeInsensitive(String value) {
            addCriterion("upper(UUID) like", value.toUpperCase(), "uuid");
            return (Criteria) this;
        }

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(USERNAME) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(PASSWORD) like", value.toUpperCase(), "password");
            return (Criteria) this;
        }

        public Criteria andParentidLikeInsensitive(String value) {
            addCriterion("upper(PARENTID) like", value.toUpperCase(), "parentid");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLikeInsensitive(String value) {
            addCriterion("upper(DEVICETYPE) like", value.toUpperCase(), "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicenumLikeInsensitive(String value) {
            addCriterion("upper(DEVICENUM) like", value.toUpperCase(), "devicenum");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated do_not_delete_during_merge Fri Mar 10 15:34:18 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_acc_whitechild
     *
     * @mbggenerated Fri Mar 10 15:34:18 CST 2017
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