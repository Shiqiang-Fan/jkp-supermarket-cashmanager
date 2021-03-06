package com.joyveb.cashmanage.entity;

import java.math.BigDecimal;

public class TransferRecord extends TransferRecordKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRANSFERTYPE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String transfertype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRANSFEROUT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String transferout;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRANSFERIN
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String transferin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRANSFERAMOUNT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private BigDecimal transferamount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.FACTORAGE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private BigDecimal factorage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.STATUS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRADENUM
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String tradenum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.MESSAGEIDS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String messageids;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.TRANSFERTIME
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private Long transfertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_transferrecord.FAILREASON
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    private String failreason;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRANSFERTYPE
     *
     * @return the value of t_acc_transferrecord.TRANSFERTYPE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getTransfertype() {
        return transfertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRANSFERTYPE
     *
     * @param transfertype the value for t_acc_transferrecord.TRANSFERTYPE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRANSFEROUT
     *
     * @return the value of t_acc_transferrecord.TRANSFEROUT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getTransferout() {
        return transferout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRANSFEROUT
     *
     * @param transferout the value for t_acc_transferrecord.TRANSFEROUT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTransferout(String transferout) {
        this.transferout = transferout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRANSFERIN
     *
     * @return the value of t_acc_transferrecord.TRANSFERIN
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getTransferin() {
        return transferin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRANSFERIN
     *
     * @param transferin the value for t_acc_transferrecord.TRANSFERIN
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTransferin(String transferin) {
        this.transferin = transferin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRANSFERAMOUNT
     *
     * @return the value of t_acc_transferrecord.TRANSFERAMOUNT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public BigDecimal getTransferamount() {
        return transferamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRANSFERAMOUNT
     *
     * @param transferamount the value for t_acc_transferrecord.TRANSFERAMOUNT
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTransferamount(BigDecimal transferamount) {
        this.transferamount = transferamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.FACTORAGE
     *
     * @return the value of t_acc_transferrecord.FACTORAGE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public BigDecimal getFactorage() {
        return factorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.FACTORAGE
     *
     * @param factorage the value for t_acc_transferrecord.FACTORAGE
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setFactorage(BigDecimal factorage) {
        this.factorage = factorage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.STATUS
     *
     * @return the value of t_acc_transferrecord.STATUS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.STATUS
     *
     * @param status the value for t_acc_transferrecord.STATUS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRADENUM
     *
     * @return the value of t_acc_transferrecord.TRADENUM
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getTradenum() {
        return tradenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRADENUM
     *
     * @param tradenum the value for t_acc_transferrecord.TRADENUM
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTradenum(String tradenum) {
        this.tradenum = tradenum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.MESSAGEIDS
     *
     * @return the value of t_acc_transferrecord.MESSAGEIDS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getMessageids() {
        return messageids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.MESSAGEIDS
     *
     * @param messageids the value for t_acc_transferrecord.MESSAGEIDS
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setMessageids(String messageids) {
        this.messageids = messageids;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.TRANSFERTIME
     *
     * @return the value of t_acc_transferrecord.TRANSFERTIME
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public Long getTransfertime() {
        return transfertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.TRANSFERTIME
     *
     * @param transfertime the value for t_acc_transferrecord.TRANSFERTIME
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setTransfertime(Long transfertime) {
        this.transfertime = transfertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_transferrecord.FAILREASON
     *
     * @return the value of t_acc_transferrecord.FAILREASON
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public String getFailreason() {
        return failreason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_transferrecord.FAILREASON
     *
     * @param failreason the value for t_acc_transferrecord.FAILREASON
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_transferrecord
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
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
        TransferRecord other = (TransferRecord) that;
        return (this.getOuttradenum() == null ? other.getOuttradenum() == null : this.getOuttradenum().equals(other.getOuttradenum()))
            && (this.getTransfertype() == null ? other.getTransfertype() == null : this.getTransfertype().equals(other.getTransfertype()))
            && (this.getTransferout() == null ? other.getTransferout() == null : this.getTransferout().equals(other.getTransferout()))
            && (this.getTransferin() == null ? other.getTransferin() == null : this.getTransferin().equals(other.getTransferin()))
            && (this.getTransferamount() == null ? other.getTransferamount() == null : this.getTransferamount().equals(other.getTransferamount()))
            && (this.getFactorage() == null ? other.getFactorage() == null : this.getFactorage().equals(other.getFactorage()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTradenum() == null ? other.getTradenum() == null : this.getTradenum().equals(other.getTradenum()))
            && (this.getMessageids() == null ? other.getMessageids() == null : this.getMessageids().equals(other.getMessageids()))
            && (this.getTransfertime() == null ? other.getTransfertime() == null : this.getTransfertime().equals(other.getTransfertime()))
            && (this.getFailreason() == null ? other.getFailreason() == null : this.getFailreason().equals(other.getFailreason()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_transferrecord
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOuttradenum() == null) ? 0 : getOuttradenum().hashCode());
        result = prime * result + ((getTransfertype() == null) ? 0 : getTransfertype().hashCode());
        result = prime * result + ((getTransferout() == null) ? 0 : getTransferout().hashCode());
        result = prime * result + ((getTransferin() == null) ? 0 : getTransferin().hashCode());
        result = prime * result + ((getTransferamount() == null) ? 0 : getTransferamount().hashCode());
        result = prime * result + ((getFactorage() == null) ? 0 : getFactorage().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTradenum() == null) ? 0 : getTradenum().hashCode());
        result = prime * result + ((getMessageids() == null) ? 0 : getMessageids().hashCode());
        result = prime * result + ((getTransfertime() == null) ? 0 : getTransfertime().hashCode());
        result = prime * result + ((getFailreason() == null) ? 0 : getFailreason().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_transferrecord
     *
     * @mbggenerated Fri Mar 10 15:45:26 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", transfertype=").append(transfertype);
        sb.append(", transferout=").append(transferout);
        sb.append(", transferin=").append(transferin);
        sb.append(", transferamount=").append(transferamount);
        sb.append(", factorage=").append(factorage);
        sb.append(", status=").append(status);
        sb.append(", tradenum=").append(tradenum);
        sb.append(", messageids=").append(messageids);
        sb.append(", transfertime=").append(transfertime);
        sb.append(", failreason=").append(failreason);
        sb.append("]");
        return sb.toString();
    }
}