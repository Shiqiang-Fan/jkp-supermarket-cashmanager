package com.joyveb.cashmanage.entity;

import java.math.BigDecimal;

public class LogMoney extends LogMoneyKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private String parentname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.ALIPAYNUM
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private String alipaynum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.EXTRACTMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private BigDecimal extractmoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.RESIDUEMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private BigDecimal residuemoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.STATUS
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.FAILREASON
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private String failreason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.EXTRACTTIME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private Long extracttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_money.DATE
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    private String date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.PARENTNAME
     *
     * @return the value of t_log_money.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public String getParentname() {
        return parentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.PARENTNAME
     *
     * @param parentname the value for t_log_money.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.ALIPAYNUM
     *
     * @return the value of t_log_money.ALIPAYNUM
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public String getAlipaynum() {
        return alipaynum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.ALIPAYNUM
     *
     * @param alipaynum the value for t_log_money.ALIPAYNUM
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setAlipaynum(String alipaynum) {
        this.alipaynum = alipaynum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.EXTRACTMONEY
     *
     * @return the value of t_log_money.EXTRACTMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public BigDecimal getExtractmoney() {
        return extractmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.EXTRACTMONEY
     *
     * @param extractmoney the value for t_log_money.EXTRACTMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setExtractmoney(BigDecimal extractmoney) {
        this.extractmoney = extractmoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.RESIDUEMONEY
     *
     * @return the value of t_log_money.RESIDUEMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public BigDecimal getResiduemoney() {
        return residuemoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.RESIDUEMONEY
     *
     * @param residuemoney the value for t_log_money.RESIDUEMONEY
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setResiduemoney(BigDecimal residuemoney) {
        this.residuemoney = residuemoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.STATUS
     *
     * @return the value of t_log_money.STATUS
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.STATUS
     *
     * @param status the value for t_log_money.STATUS
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.FAILREASON
     *
     * @return the value of t_log_money.FAILREASON
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public String getFailreason() {
        return failreason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.FAILREASON
     *
     * @param failreason the value for t_log_money.FAILREASON
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.EXTRACTTIME
     *
     * @return the value of t_log_money.EXTRACTTIME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public Long getExtracttime() {
        return extracttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.EXTRACTTIME
     *
     * @param extracttime the value for t_log_money.EXTRACTTIME
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setExtracttime(Long extracttime) {
        this.extracttime = extracttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_money.DATE
     *
     * @return the value of t_log_money.DATE
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_money.DATE
     *
     * @param date the value for t_log_money.DATE
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_money
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LogMoney other = (LogMoney) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getParentname() == null ? other.getParentname() == null : this.getParentname().equals(other.getParentname()))
            && (this.getAlipaynum() == null ? other.getAlipaynum() == null : this.getAlipaynum().equals(other.getAlipaynum()))
            && (this.getExtractmoney() == null ? other.getExtractmoney() == null : this.getExtractmoney().equals(other.getExtractmoney()))
            && (this.getResiduemoney() == null ? other.getResiduemoney() == null : this.getResiduemoney().equals(other.getResiduemoney()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getFailreason() == null ? other.getFailreason() == null : this.getFailreason().equals(other.getFailreason()))
            && (this.getExtracttime() == null ? other.getExtracttime() == null : this.getExtracttime().equals(other.getExtracttime()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_money
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getParentname() == null) ? 0 : getParentname().hashCode());
        result = prime * result + ((getAlipaynum() == null) ? 0 : getAlipaynum().hashCode());
        result = prime * result + ((getExtractmoney() == null) ? 0 : getExtractmoney().hashCode());
        result = prime * result + ((getResiduemoney() == null) ? 0 : getResiduemoney().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getFailreason() == null) ? 0 : getFailreason().hashCode());
        result = prime * result + ((getExtracttime() == null) ? 0 : getExtracttime().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_money
     *
     * @mbggenerated Sun Aug 20 17:51:59 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentname=").append(parentname);
        sb.append(", alipaynum=").append(alipaynum);
        sb.append(", extractmoney=").append(extractmoney);
        sb.append(", residuemoney=").append(residuemoney);
        sb.append(", status=").append(status);
        sb.append(", failreason=").append(failreason);
        sb.append(", extracttime=").append(extracttime);
        sb.append(", date=").append(date);
        sb.append("]");
        return sb.toString();
    }
}