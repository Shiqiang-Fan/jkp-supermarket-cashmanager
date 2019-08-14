package com.joyveb.cashmanage.entity;

import java.math.BigDecimal;

public class LogCommission extends LogCommissionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private String parentname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.SECONDNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private String secondname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.MONEY
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private BigDecimal money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.ORDERTIME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private Long ordertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.STATUS
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_log_commission.DATE
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    private String date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.PARENTNAME
     *
     * @return the value of t_log_commission.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public String getParentname() {
        return parentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.PARENTNAME
     *
     * @param parentname the value for t_log_commission.PARENTNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.SECONDNAME
     *
     * @return the value of t_log_commission.SECONDNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public String getSecondname() {
        return secondname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.SECONDNAME
     *
     * @param secondname the value for t_log_commission.SECONDNAME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.MONEY
     *
     * @return the value of t_log_commission.MONEY
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.MONEY
     *
     * @param money the value for t_log_commission.MONEY
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.ORDERTIME
     *
     * @return the value of t_log_commission.ORDERTIME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public Long getOrdertime() {
        return ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.ORDERTIME
     *
     * @param ordertime the value for t_log_commission.ORDERTIME
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setOrdertime(Long ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.STATUS
     *
     * @return the value of t_log_commission.STATUS
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.STATUS
     *
     * @param status the value for t_log_commission.STATUS
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_log_commission.DATE
     *
     * @return the value of t_log_commission.DATE
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_log_commission.DATE
     *
     * @param date the value for t_log_commission.DATE
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_commission
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
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
        LogCommission other = (LogCommission) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getParentname() == null ? other.getParentname() == null : this.getParentname().equals(other.getParentname()))
            && (this.getSecondname() == null ? other.getSecondname() == null : this.getSecondname().equals(other.getSecondname()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getOrdertime() == null ? other.getOrdertime() == null : this.getOrdertime().equals(other.getOrdertime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_commission
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getParentname() == null) ? 0 : getParentname().hashCode());
        result = prime * result + ((getSecondname() == null) ? 0 : getSecondname().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getOrdertime() == null) ? 0 : getOrdertime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_log_commission
     *
     * @mbggenerated Sun Aug 20 17:47:46 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentname=").append(parentname);
        sb.append(", secondname=").append(secondname);
        sb.append(", money=").append(money);
        sb.append(", ordertime=").append(ordertime);
        sb.append(", status=").append(status);
        sb.append(", date=").append(date);
        sb.append("]");
        return sb.toString();
    }
}