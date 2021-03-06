package com.joyveb.cashmanage.entity;

import java.util.ArrayList;
import java.util.List;

public class PrizerExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    protected int offset;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    protected int limit;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    protected String sumCol;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    protected String groupSelClause;

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    protected String groupByClause;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public PrizerExample() {
        oredCriteria = new ArrayList<Criteria>();
        offset = 0;
        limit = Integer.MAX_VALUE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
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
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
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
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    public void setOffset(int offset) {
         this.offset = offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    public int getOffset() {
          return offset;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    public void setLimit(int limit) {
         this.limit = limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    *
    * @generated Mon Aug 34 20:00:28 CST 2017
    */
    public int getLimit() {
          return limit;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public void setSumCol(String sumCol) {
         this.sumCol = sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public String getSumCol() {
          return sumCol;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public void setGroupSelClause(String groupSelClause) {
         this.groupSelClause = groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public String getGroupSelClause() {
          return groupSelClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public void setGroupByClause(String groupByClause) {
         this.groupByClause = groupByClause;
    }

    /**
    * This method was generated by MyBatis Generator.
    * This method corresponds to the database table t_core_prizer
    */
    public String getGroupByClause() {
          return groupByClause;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
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

        public Criteria andMessageidIsNull() {
            addCriterion("MESSAGEID is null");
            return (Criteria) this;
        }

        public Criteria andMessageidIsNotNull() {
            addCriterion("MESSAGEID is not null");
            return (Criteria) this;
        }

        public Criteria andMessageidEqualTo(String value) {
            addCriterion("MESSAGEID =", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotEqualTo(String value) {
            addCriterion("MESSAGEID <>", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThan(String value) {
            addCriterion("MESSAGEID >", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGEID >=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThan(String value) {
            addCriterion("MESSAGEID <", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLessThanOrEqualTo(String value) {
            addCriterion("MESSAGEID <=", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidLike(String value) {
            addCriterion("MESSAGEID like", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotLike(String value) {
            addCriterion("MESSAGEID not like", value, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidIn(List<String> values) {
            addCriterion("MESSAGEID in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotIn(List<String> values) {
            addCriterion("MESSAGEID not in", values, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidBetween(String value1, String value2) {
            addCriterion("MESSAGEID between", value1, value2, "messageid");
            return (Criteria) this;
        }

        public Criteria andMessageidNotBetween(String value1, String value2) {
            addCriterion("MESSAGEID not between", value1, value2, "messageid");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("USERID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("USERID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("USERID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("USERID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("USERID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("USERID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("USERID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("USERID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("USERID like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("USERID not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("USERID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("USERID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("USERID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("USERID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNull() {
            addCriterion("PARENTNAME is null");
            return (Criteria) this;
        }

        public Criteria andParentnameIsNotNull() {
            addCriterion("PARENTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andParentnameEqualTo(String value) {
            addCriterion("PARENTNAME =", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotEqualTo(String value) {
            addCriterion("PARENTNAME <>", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThan(String value) {
            addCriterion("PARENTNAME >", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTNAME >=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThan(String value) {
            addCriterion("PARENTNAME <", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLessThanOrEqualTo(String value) {
            addCriterion("PARENTNAME <=", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameLike(String value) {
            addCriterion("PARENTNAME like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotLike(String value) {
            addCriterion("PARENTNAME not like", value, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameIn(List<String> values) {
            addCriterion("PARENTNAME in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotIn(List<String> values) {
            addCriterion("PARENTNAME not in", values, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameBetween(String value1, String value2) {
            addCriterion("PARENTNAME between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andParentnameNotBetween(String value1, String value2) {
            addCriterion("PARENTNAME not between", value1, value2, "parentname");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNull() {
            addCriterion("DEVICEID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("DEVICEID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(String value) {
            addCriterion("DEVICEID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(String value) {
            addCriterion("DEVICEID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(String value) {
            addCriterion("DEVICEID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICEID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(String value) {
            addCriterion("DEVICEID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(String value) {
            addCriterion("DEVICEID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLike(String value) {
            addCriterion("DEVICEID like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotLike(String value) {
            addCriterion("DEVICEID not like", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<String> values) {
            addCriterion("DEVICEID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<String> values) {
            addCriterion("DEVICEID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(String value1, String value2) {
            addCriterion("DEVICEID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(String value1, String value2) {
            addCriterion("DEVICEID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNull() {
            addCriterion("BARCODE is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeIsNotNull() {
            addCriterion("BARCODE is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeEqualTo(String value) {
            addCriterion("BARCODE =", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotEqualTo(String value) {
            addCriterion("BARCODE <>", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThan(String value) {
            addCriterion("BARCODE >", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("BARCODE >=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThan(String value) {
            addCriterion("BARCODE <", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLessThanOrEqualTo(String value) {
            addCriterion("BARCODE <=", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeLike(String value) {
            addCriterion("BARCODE like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotLike(String value) {
            addCriterion("BARCODE not like", value, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeIn(List<String> values) {
            addCriterion("BARCODE in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotIn(List<String> values) {
            addCriterion("BARCODE not in", values, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeBetween(String value1, String value2) {
            addCriterion("BARCODE between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andBarcodeNotBetween(String value1, String value2) {
            addCriterion("BARCODE not between", value1, value2, "barcode");
            return (Criteria) this;
        }

        public Criteria andAmountsignIsNull() {
            addCriterion("AMOUNTSIGN is null");
            return (Criteria) this;
        }

        public Criteria andAmountsignIsNotNull() {
            addCriterion("AMOUNTSIGN is not null");
            return (Criteria) this;
        }

        public Criteria andAmountsignEqualTo(String value) {
            addCriterion("AMOUNTSIGN =", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignNotEqualTo(String value) {
            addCriterion("AMOUNTSIGN <>", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignGreaterThan(String value) {
            addCriterion("AMOUNTSIGN >", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignGreaterThanOrEqualTo(String value) {
            addCriterion("AMOUNTSIGN >=", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignLessThan(String value) {
            addCriterion("AMOUNTSIGN <", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignLessThanOrEqualTo(String value) {
            addCriterion("AMOUNTSIGN <=", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignLike(String value) {
            addCriterion("AMOUNTSIGN like", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignNotLike(String value) {
            addCriterion("AMOUNTSIGN not like", value, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignIn(List<String> values) {
            addCriterion("AMOUNTSIGN in", values, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignNotIn(List<String> values) {
            addCriterion("AMOUNTSIGN not in", values, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignBetween(String value1, String value2) {
            addCriterion("AMOUNTSIGN between", value1, value2, "amountsign");
            return (Criteria) this;
        }

        public Criteria andAmountsignNotBetween(String value1, String value2) {
            addCriterion("AMOUNTSIGN not between", value1, value2, "amountsign");
            return (Criteria) this;
        }

        public Criteria andTransferstatusIsNull() {
            addCriterion("TRANSFERSTATUS is null");
            return (Criteria) this;
        }

        public Criteria andTransferstatusIsNotNull() {
            addCriterion("TRANSFERSTATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTransferstatusEqualTo(String value) {
            addCriterion("TRANSFERSTATUS =", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusNotEqualTo(String value) {
            addCriterion("TRANSFERSTATUS <>", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusGreaterThan(String value) {
            addCriterion("TRANSFERSTATUS >", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSFERSTATUS >=", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusLessThan(String value) {
            addCriterion("TRANSFERSTATUS <", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusLessThanOrEqualTo(String value) {
            addCriterion("TRANSFERSTATUS <=", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusLike(String value) {
            addCriterion("TRANSFERSTATUS like", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusNotLike(String value) {
            addCriterion("TRANSFERSTATUS not like", value, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusIn(List<String> values) {
            addCriterion("TRANSFERSTATUS in", values, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusNotIn(List<String> values) {
            addCriterion("TRANSFERSTATUS not in", values, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusBetween(String value1, String value2) {
            addCriterion("TRANSFERSTATUS between", value1, value2, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andTransferstatusNotBetween(String value1, String value2) {
            addCriterion("TRANSFERSTATUS not between", value1, value2, "transferstatus");
            return (Criteria) this;
        }

        public Criteria andWinamountIsNull() {
            addCriterion("WINAMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andWinamountIsNotNull() {
            addCriterion("WINAMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andWinamountEqualTo(Integer value) {
            addCriterion("WINAMOUNT =", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountNotEqualTo(Integer value) {
            addCriterion("WINAMOUNT <>", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountGreaterThan(Integer value) {
            addCriterion("WINAMOUNT >", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountGreaterThanOrEqualTo(Integer value) {
            addCriterion("WINAMOUNT >=", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountLessThan(Integer value) {
            addCriterion("WINAMOUNT <", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountLessThanOrEqualTo(Integer value) {
            addCriterion("WINAMOUNT <=", value, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountIn(List<Integer> values) {
            addCriterion("WINAMOUNT in", values, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountNotIn(List<Integer> values) {
            addCriterion("WINAMOUNT not in", values, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountBetween(Integer value1, Integer value2) {
            addCriterion("WINAMOUNT between", value1, value2, "winamount");
            return (Criteria) this;
        }

        public Criteria andWinamountNotBetween(Integer value1, Integer value2) {
            addCriterion("WINAMOUNT not between", value1, value2, "winamount");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIsNull() {
            addCriterion("REQUESTTIME is null");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIsNotNull() {
            addCriterion("REQUESTTIME is not null");
            return (Criteria) this;
        }

        public Criteria andRequesttimeEqualTo(Long value) {
            addCriterion("REQUESTTIME =", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotEqualTo(Long value) {
            addCriterion("REQUESTTIME <>", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeGreaterThan(Long value) {
            addCriterion("REQUESTTIME >", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeGreaterThanOrEqualTo(Long value) {
            addCriterion("REQUESTTIME >=", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeLessThan(Long value) {
            addCriterion("REQUESTTIME <", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeLessThanOrEqualTo(Long value) {
            addCriterion("REQUESTTIME <=", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIn(List<Long> values) {
            addCriterion("REQUESTTIME in", values, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotIn(List<Long> values) {
            addCriterion("REQUESTTIME not in", values, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeBetween(Long value1, Long value2) {
            addCriterion("REQUESTTIME between", value1, value2, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotBetween(Long value1, Long value2) {
            addCriterion("REQUESTTIME not between", value1, value2, "requesttime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeIsNull() {
            addCriterion("TRANSFERTIME is null");
            return (Criteria) this;
        }

        public Criteria andTransfertimeIsNotNull() {
            addCriterion("TRANSFERTIME is not null");
            return (Criteria) this;
        }

        public Criteria andTransfertimeEqualTo(Long value) {
            addCriterion("TRANSFERTIME =", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeNotEqualTo(Long value) {
            addCriterion("TRANSFERTIME <>", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeGreaterThan(Long value) {
            addCriterion("TRANSFERTIME >", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeGreaterThanOrEqualTo(Long value) {
            addCriterion("TRANSFERTIME >=", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeLessThan(Long value) {
            addCriterion("TRANSFERTIME <", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeLessThanOrEqualTo(Long value) {
            addCriterion("TRANSFERTIME <=", value, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeIn(List<Long> values) {
            addCriterion("TRANSFERTIME in", values, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeNotIn(List<Long> values) {
            addCriterion("TRANSFERTIME not in", values, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeBetween(Long value1, Long value2) {
            addCriterion("TRANSFERTIME between", value1, value2, "transfertime");
            return (Criteria) this;
        }

        public Criteria andTransfertimeNotBetween(Long value1, Long value2) {
            addCriterion("TRANSFERTIME not between", value1, value2, "transfertime");
            return (Criteria) this;
        }

        public Criteria andMessageidLikeInsensitive(String value) {
            addCriterion("upper(MESSAGEID) like", value.toUpperCase(), "messageid");
            return (Criteria) this;
        }

        public Criteria andUseridLikeInsensitive(String value) {
            addCriterion("upper(USERID) like", value.toUpperCase(), "userid");
            return (Criteria) this;
        }

        public Criteria andParentnameLikeInsensitive(String value) {
            addCriterion("upper(PARENTNAME) like", value.toUpperCase(), "parentname");
            return (Criteria) this;
        }

        public Criteria andDeviceidLikeInsensitive(String value) {
            addCriterion("upper(DEVICEID) like", value.toUpperCase(), "deviceid");
            return (Criteria) this;
        }

        public Criteria andBarcodeLikeInsensitive(String value) {
            addCriterion("upper(BARCODE) like", value.toUpperCase(), "barcode");
            return (Criteria) this;
        }

        public Criteria andAmountsignLikeInsensitive(String value) {
            addCriterion("upper(AMOUNTSIGN) like", value.toUpperCase(), "amountsign");
            return (Criteria) this;
        }

        public Criteria andTransferstatusLikeInsensitive(String value) {
            addCriterion("upper(TRANSFERSTATUS) like", value.toUpperCase(), "transferstatus");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_core_prizer
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 21 20:00:28 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
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