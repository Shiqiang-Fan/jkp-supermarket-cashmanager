package com.joyveb.cashmanage.mapper;

import com.joyveb.cashmanage.entity.AccWhitelistParent;
import com.joyveb.cashmanage.entity.AccWhitelistParentExample;
import com.joyveb.cashmanage.entity.AccWhitelistParentKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AccWhitelistParentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @SelectProvider(type=AccWhitelistParentSqlProvider.class, method="countByExample")
    int countByExample(AccWhitelistParentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @DeleteProvider(type=AccWhitelistParentSqlProvider.class, method="deleteByExample")
    int deleteByExample(AccWhitelistParentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @Delete({
        "delete from t_acc_whitelist_parent",
        "where USERNAME = #{username,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(AccWhitelistParentKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @Insert({
        "insert into t_acc_whitelist_parent (USERNAME, FULLNAME, ",
        "IDCARD, MOBILE, ",
        "ADDRESS, STATUS, COMMISSION, ",
        "ALIPAYNUM, COMMENT, ",
        "CREATETIME)",
        "values (#{username,jdbcType=VARCHAR}, #{fullname,jdbcType=VARCHAR}, ",
        "#{idcard,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, #{commission,jdbcType=VARCHAR}, ",
        "#{alipaynum,jdbcType=VARCHAR}, #{comment,jdbcType=VARCHAR}, ",
        "#{createtime,jdbcType=BIGINT})"
    })
    int insert(AccWhitelistParent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @InsertProvider(type=AccWhitelistParentSqlProvider.class, method="insertSelective")
    int insertSelective(AccWhitelistParent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @SelectProvider(type=AccWhitelistParentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="USERNAME", property="username", jdbcType= JdbcType.VARCHAR, id=true),
        @Result(column="FULLNAME", property="fullname", jdbcType= JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType= JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType= JdbcType.VARCHAR),
        @Result(column="ADDRESS", property="address", jdbcType= JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType= JdbcType.CHAR),
        @Result(column="COMMISSION", property="commission", jdbcType= JdbcType.VARCHAR),
        @Result(column="ALIPAYNUM", property="alipaynum", jdbcType= JdbcType.VARCHAR),
        @Result(column="COMMENT", property="comment", jdbcType= JdbcType.VARCHAR),
        @Result(column="CREATETIME", property="createtime", jdbcType= JdbcType.BIGINT)
    })
    List<AccWhitelistParent> selectByExample(AccWhitelistParentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @Select({
        "select",
        "USERNAME, FULLNAME, IDCARD, MOBILE, ADDRESS, STATUS, COMMISSION, ALIPAYNUM, ",
        "COMMENT, CREATETIME",
        "from t_acc_whitelist_parent",
        "where USERNAME = #{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="USERNAME", property="username", jdbcType= JdbcType.VARCHAR, id=true),
        @Result(column="FULLNAME", property="fullname", jdbcType= JdbcType.VARCHAR),
        @Result(column="IDCARD", property="idcard", jdbcType= JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType= JdbcType.VARCHAR),
        @Result(column="ADDRESS", property="address", jdbcType= JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType= JdbcType.CHAR),
        @Result(column="COMMISSION", property="commission", jdbcType= JdbcType.VARCHAR),
        @Result(column="ALIPAYNUM", property="alipaynum", jdbcType= JdbcType.VARCHAR),
        @Result(column="COMMENT", property="comment", jdbcType= JdbcType.VARCHAR),
        @Result(column="CREATETIME", property="createtime", jdbcType= JdbcType.BIGINT)
    })
    AccWhitelistParent selectByPrimaryKey(AccWhitelistParentKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @UpdateProvider(type=AccWhitelistParentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AccWhitelistParent record, @Param("example") AccWhitelistParentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @UpdateProvider(type=AccWhitelistParentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AccWhitelistParent record, @Param("example") AccWhitelistParentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @UpdateProvider(type=AccWhitelistParentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AccWhitelistParent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist_parent
     *
     * @mbggenerated Thu Aug 17 20:06:15 CST 2017
     */
    @Update({
        "update t_acc_whitelist_parent",
        "set FULLNAME = #{fullname,jdbcType=VARCHAR},",
          "IDCARD = #{idcard,jdbcType=VARCHAR},",
          "MOBILE = #{mobile,jdbcType=VARCHAR},",
          "ADDRESS = #{address,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=CHAR},",
          "COMMISSION = #{commission,jdbcType=VARCHAR},",
          "ALIPAYNUM = #{alipaynum,jdbcType=VARCHAR},",
          "COMMENT = #{comment,jdbcType=VARCHAR},",
          "CREATETIME = #{createtime,jdbcType=BIGINT}",
        "where USERNAME = #{username,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccWhitelistParent record);
}