package com.joyveb.auth.restful.bean;

public class AdminPermissionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_ADMIN_PERMISSION.ID
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
     */
    private Integer id;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMIN_PERMISSION.ID
     *
     * @return the value of T_ADMIN_PERMISSION.ID
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMIN_PERMISSION.ID
     *
     * @param id the value for T_ADMIN_PERMISSION.ID
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_PERMISSION
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
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
        AdminPermissionKey other = (AdminPermissionKey) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_PERMISSION
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMIN_PERMISSION
     *
     * @mbggenerated Fri Apr 15 21:03:14 CST 2016
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append("]");
        return sb.toString();
    }
}