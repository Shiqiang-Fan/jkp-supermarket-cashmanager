package com.joyveb.cashmanage.entity;

public class GameOutKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_game_out.UUID
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
     */
    private String uuid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_game_out.UUID
     *
     * @return the value of t_game_out.UUID
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_game_out.UUID
     *
     * @param uuid the value for t_game_out.UUID
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_out
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
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
        GameOutKey other = (GameOutKey) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_game_out
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
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
     * This method corresponds to the database table t_game_out
     *
     * @mbggenerated Mon Mar 13 14:58:20 CST 2017
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