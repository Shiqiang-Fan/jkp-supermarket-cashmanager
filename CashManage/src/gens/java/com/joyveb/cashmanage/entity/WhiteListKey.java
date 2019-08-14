package com.joyveb.cashmanage.entity;

public class WhiteListKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_acc_whitelist.UUID
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    private String uuid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_acc_whitelist.UUID
     *
     * @return the value of t_acc_whitelist.UUID
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_acc_whitelist.UUID
     *
     * @param uuid the value for t_acc_whitelist.UUID
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
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
        WhiteListKey other = (WhiteListKey) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append("]");
        return sb.toString();
    }
}