package com.joyveb.cashmanage.entity;

public class Terminal extends TerminalKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ips_terminal.ISUSED
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
     */
    private String isused;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ips_terminal.ISUSED
     *
     * @return the value of t_ips_terminal.ISUSED
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
     */
    public String getIsused() {
        return isused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ips_terminal.ISUSED
     *
     * @param isused the value for t_ips_terminal.ISUSED
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
     */
    public void setIsused(String isused) {
        this.isused = isused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ips_terminal
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
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
        Terminal other = (Terminal) that;
        return (this.getSessionid() == null ? other.getSessionid() == null : this.getSessionid().equals(other.getSessionid()))
            && (this.getIsused() == null ? other.getIsused() == null : this.getIsused().equals(other.getIsused()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ips_terminal
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSessionid() == null) ? 0 : getSessionid().hashCode());
        result = prime * result + ((getIsused() == null) ? 0 : getIsused().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_ips_terminal
     *
     * @mbggenerated Wed Nov 30 11:07:10 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", isused=").append(isused);
        sb.append("]");
        return sb.toString();
    }
}