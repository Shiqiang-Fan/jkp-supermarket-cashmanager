package com.joyveb.cashmanage.entity;

import java.util.ArrayList;
import java.util.List;

public class GameInputExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    protected int offset;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    protected int limit;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    protected String sumCol;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    protected String groupSelClause;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    protected String groupByClause;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public GameInputExample() {
        oredCriteria = new ArrayList<Criteria>();
        offset = 0;
        limit = Integer.MAX_VALUE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
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
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
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
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    public void setOffset(int offset) {
         this.offset = offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    public int getOffset() {
          return offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    public void setLimit(int limit) {
         this.limit = limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    *
    * @generated Mon Mar 11 11:43:43 CST 2017
    */
    public int getLimit() {
          return limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public void setSumCol(String sumCol) {
         this.sumCol = sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public String getSumCol() {
          return sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public void setGroupSelClause(String groupSelClause) {
         this.groupSelClause = groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public String getGroupSelClause() {
          return groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public void setGroupByClause(String groupByClause) {
         this.groupByClause = groupByClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_game_input
    */
    public String getGroupByClause() {
          return groupByClause;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
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

        public Criteria andProductbatchIsNull() {
            addCriterion("PRODUCTBATCH is null");
            return (Criteria) this;
        }

        public Criteria andProductbatchIsNotNull() {
            addCriterion("PRODUCTBATCH is not null");
            return (Criteria) this;
        }

        public Criteria andProductbatchEqualTo(String value) {
            addCriterion("PRODUCTBATCH =", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchNotEqualTo(String value) {
            addCriterion("PRODUCTBATCH <>", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchGreaterThan(String value) {
            addCriterion("PRODUCTBATCH >", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCTBATCH >=", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchLessThan(String value) {
            addCriterion("PRODUCTBATCH <", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchLessThanOrEqualTo(String value) {
            addCriterion("PRODUCTBATCH <=", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchLike(String value) {
            addCriterion("PRODUCTBATCH like", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchNotLike(String value) {
            addCriterion("PRODUCTBATCH not like", value, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchIn(List<String> values) {
            addCriterion("PRODUCTBATCH in", values, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchNotIn(List<String> values) {
            addCriterion("PRODUCTBATCH not in", values, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchBetween(String value1, String value2) {
            addCriterion("PRODUCTBATCH between", value1, value2, "productbatch");
            return (Criteria) this;
        }

        public Criteria andProductbatchNotBetween(String value1, String value2) {
            addCriterion("PRODUCTBATCH not between", value1, value2, "productbatch");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNull() {
            addCriterion("BOOKNUM is null");
            return (Criteria) this;
        }

        public Criteria andBooknumIsNotNull() {
            addCriterion("BOOKNUM is not null");
            return (Criteria) this;
        }

        public Criteria andBooknumEqualTo(String value) {
            addCriterion("BOOKNUM =", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotEqualTo(String value) {
            addCriterion("BOOKNUM <>", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThan(String value) {
            addCriterion("BOOKNUM >", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumGreaterThanOrEqualTo(String value) {
            addCriterion("BOOKNUM >=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThan(String value) {
            addCriterion("BOOKNUM <", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLessThanOrEqualTo(String value) {
            addCriterion("BOOKNUM <=", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumLike(String value) {
            addCriterion("BOOKNUM like", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotLike(String value) {
            addCriterion("BOOKNUM not like", value, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumIn(List<String> values) {
            addCriterion("BOOKNUM in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotIn(List<String> values) {
            addCriterion("BOOKNUM not in", values, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumBetween(String value1, String value2) {
            addCriterion("BOOKNUM between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andBooknumNotBetween(String value1, String value2) {
            addCriterion("BOOKNUM not between", value1, value2, "booknum");
            return (Criteria) this;
        }

        public Criteria andInputtimeIsNull() {
            addCriterion("INPUTTIME is null");
            return (Criteria) this;
        }

        public Criteria andInputtimeIsNotNull() {
            addCriterion("INPUTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andInputtimeEqualTo(Long value) {
            addCriterion("INPUTTIME =", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeNotEqualTo(Long value) {
            addCriterion("INPUTTIME <>", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeGreaterThan(Long value) {
            addCriterion("INPUTTIME >", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeGreaterThanOrEqualTo(Long value) {
            addCriterion("INPUTTIME >=", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeLessThan(Long value) {
            addCriterion("INPUTTIME <", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeLessThanOrEqualTo(Long value) {
            addCriterion("INPUTTIME <=", value, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeIn(List<Long> values) {
            addCriterion("INPUTTIME in", values, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeNotIn(List<Long> values) {
            addCriterion("INPUTTIME not in", values, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeBetween(Long value1, Long value2) {
            addCriterion("INPUTTIME between", value1, value2, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputtimeNotBetween(Long value1, Long value2) {
            addCriterion("INPUTTIME not between", value1, value2, "inputtime");
            return (Criteria) this;
        }

        public Criteria andInputpersonIsNull() {
            addCriterion("INPUTPERSON is null");
            return (Criteria) this;
        }

        public Criteria andInputpersonIsNotNull() {
            addCriterion("INPUTPERSON is not null");
            return (Criteria) this;
        }

        public Criteria andInputpersonEqualTo(String value) {
            addCriterion("INPUTPERSON =", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonNotEqualTo(String value) {
            addCriterion("INPUTPERSON <>", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonGreaterThan(String value) {
            addCriterion("INPUTPERSON >", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonGreaterThanOrEqualTo(String value) {
            addCriterion("INPUTPERSON >=", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonLessThan(String value) {
            addCriterion("INPUTPERSON <", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonLessThanOrEqualTo(String value) {
            addCriterion("INPUTPERSON <=", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonLike(String value) {
            addCriterion("INPUTPERSON like", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonNotLike(String value) {
            addCriterion("INPUTPERSON not like", value, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonIn(List<String> values) {
            addCriterion("INPUTPERSON in", values, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonNotIn(List<String> values) {
            addCriterion("INPUTPERSON not in", values, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonBetween(String value1, String value2) {
            addCriterion("INPUTPERSON between", value1, value2, "inputperson");
            return (Criteria) this;
        }

        public Criteria andInputpersonNotBetween(String value1, String value2) {
            addCriterion("INPUTPERSON not between", value1, value2, "inputperson");
            return (Criteria) this;
        }

        public Criteria andIsonsaleIsNull() {
            addCriterion("ISONSALE is null");
            return (Criteria) this;
        }

        public Criteria andIsonsaleIsNotNull() {
            addCriterion("ISONSALE is not null");
            return (Criteria) this;
        }

        public Criteria andIsonsaleEqualTo(String value) {
            addCriterion("ISONSALE =", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleNotEqualTo(String value) {
            addCriterion("ISONSALE <>", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleGreaterThan(String value) {
            addCriterion("ISONSALE >", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleGreaterThanOrEqualTo(String value) {
            addCriterion("ISONSALE >=", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleLessThan(String value) {
            addCriterion("ISONSALE <", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleLessThanOrEqualTo(String value) {
            addCriterion("ISONSALE <=", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleLike(String value) {
            addCriterion("ISONSALE like", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleNotLike(String value) {
            addCriterion("ISONSALE not like", value, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleIn(List<String> values) {
            addCriterion("ISONSALE in", values, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleNotIn(List<String> values) {
            addCriterion("ISONSALE not in", values, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleBetween(String value1, String value2) {
            addCriterion("ISONSALE between", value1, value2, "isonsale");
            return (Criteria) this;
        }

        public Criteria andIsonsaleNotBetween(String value1, String value2) {
            addCriterion("ISONSALE not between", value1, value2, "isonsale");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIsNull() {
            addCriterion("ONSALETIME is null");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIsNotNull() {
            addCriterion("ONSALETIME is not null");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeEqualTo(Long value) {
            addCriterion("ONSALETIME =", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotEqualTo(Long value) {
            addCriterion("ONSALETIME <>", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeGreaterThan(Long value) {
            addCriterion("ONSALETIME >", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeGreaterThanOrEqualTo(Long value) {
            addCriterion("ONSALETIME >=", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeLessThan(Long value) {
            addCriterion("ONSALETIME <", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeLessThanOrEqualTo(Long value) {
            addCriterion("ONSALETIME <=", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIn(List<Long> values) {
            addCriterion("ONSALETIME in", values, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotIn(List<Long> values) {
            addCriterion("ONSALETIME not in", values, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeBetween(Long value1, Long value2) {
            addCriterion("ONSALETIME between", value1, value2, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotBetween(Long value1, Long value2) {
            addCriterion("ONSALETIME not between", value1, value2, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andUuidLikeInsensitive(String value) {
            addCriterion("upper(UUID) like", value.toUpperCase(), "uuid");
            return (Criteria) this;
        }

        public Criteria andGamecodeLikeInsensitive(String value) {
            addCriterion("upper(GAMECODE) like", value.toUpperCase(), "gamecode");
            return (Criteria) this;
        }

        public Criteria andProductbatchLikeInsensitive(String value) {
            addCriterion("upper(PRODUCTBATCH) like", value.toUpperCase(), "productbatch");
            return (Criteria) this;
        }

        public Criteria andBooknumLikeInsensitive(String value) {
            addCriterion("upper(BOOKNUM) like", value.toUpperCase(), "booknum");
            return (Criteria) this;
        }

        public Criteria andInputpersonLikeInsensitive(String value) {
            addCriterion("upper(INPUTPERSON) like", value.toUpperCase(), "inputperson");
            return (Criteria) this;
        }

        public Criteria andIsonsaleLikeInsensitive(String value) {
            addCriterion("upper(ISONSALE) like", value.toUpperCase(), "isonsale");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_game_input
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 13 11:43:43 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_game_input
     *
     * @mbggenerated Mon Mar 13 11:43:43 CST 2017
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