package com.joyveb.cashmanage.entity;

public class PrizerKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_core_prizer.MESSAGEID
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    private String messageid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_core_prizer.MESSAGEID
     *
     * @return the value of t_core_prizer.MESSAGEID
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String getMessageid() {
        return messageid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_core_prizer.MESSAGEID
     *
     * @param messageid the value for t_core_prizer.MESSAGEID
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
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
        PrizerKey other = (PrizerKey) that;
        return (this.getMessageid() == null ? other.getMessageid() == null : this.getMessageid().equals(other.getMessageid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessageid() == null) ? 0 : getMessageid().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", messageid=").append(messageid);
        sb.append("]");
        return sb.toString();
    }
}