package com.joyveb.auth.restful.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.joyveb.auth.restful.bean.AdminRole;
import com.joyveb.auth.restful.bean.AdminRoleExample;
import com.joyveb.auth.restful.bean.AdminRoleKey;

public interface AdminRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @SelectProvider(type=AdminRoleSqlProvider.class, method="countByExample")
    int countByExample(AdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @DeleteProvider(type=AdminRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(AdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @Delete({
        "delete from t_admin_role",
        "where ROLE_CODE = #{roleCode,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(AdminRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @Insert({
        "insert into t_admin_role (ROLE_CODE, ROLE_NAME)",
        "values (#{roleCode,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR})"
    })
    int insert(AdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @InsertProvider(type=AdminRoleSqlProvider.class, method="insertSelective")
    int insertSelective(AdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @SelectProvider(type=AdminRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ROLE_CODE", property="roleCode", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    List<AdminRole> selectByExample(AdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @Select({
        "select",
        "ROLE_CODE, ROLE_NAME",
        "from t_admin_role",
        "where ROLE_CODE = #{roleCode,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ROLE_CODE", property="roleCode", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR)
    })
    AdminRole selectByPrimaryKey(AdminRoleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @UpdateProvider(type=AdminRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @UpdateProvider(type=AdminRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AdminRole record, @Param("example") AdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @UpdateProvider(type=AdminRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    @Update({
        "update t_admin_role",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR}",
        "where ROLE_CODE = #{roleCode,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AdminRole record);
}