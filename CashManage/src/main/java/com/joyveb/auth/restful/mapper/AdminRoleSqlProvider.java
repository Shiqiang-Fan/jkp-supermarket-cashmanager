package com.joyveb.auth.restful.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.List;
import java.util.Map;

import com.joyveb.auth.restful.bean.AdminRole;
import com.joyveb.auth.restful.bean.AdminRoleExample;
import com.joyveb.auth.restful.bean.AdminRoleExample.Criteria;
import com.joyveb.auth.restful.bean.AdminRoleExample.Criterion;

public class AdminRoleSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String countByExample(AdminRoleExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_admin_role");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String deleteByExample(AdminRoleExample example) {
        BEGIN();
        DELETE_FROM("t_admin_role");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String insertSelective(AdminRole record) {
        BEGIN();
        INSERT_INTO("t_admin_role");
        
        if (record.getRoleCode() != null) {
            VALUES("ROLE_CODE", "#{roleCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            VALUES("ROLE_NAME", "#{roleName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String selectByExample(AdminRoleExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ROLE_CODE");
        } else {
            SELECT("ROLE_CODE");
        }
        SELECT("ROLE_NAME");
        FROM("t_admin_role");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        if(example != null){
            return SQL().concat(" limit "+example.getOffset()+","+example.getLimit());
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        AdminRole record = (AdminRole) parameter.get("record");
        AdminRoleExample example = (AdminRoleExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_admin_role");
        
        if (record.getRoleCode() != null) {
            SET("ROLE_CODE = #{record.roleCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleName() != null) {
            SET("ROLE_NAME = #{record.roleName,jdbcType=VARCHAR}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_admin_role");
        
        SET("ROLE_CODE = #{record.roleCode,jdbcType=VARCHAR}");
        SET("ROLE_NAME = #{record.roleName,jdbcType=VARCHAR}");
        
        AdminRoleExample example = (AdminRoleExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    public String updateByPrimaryKeySelective(AdminRole record) {
        BEGIN();
        UPDATE("t_admin_role");
        
        if (record.getRoleName() != null) {
            SET("ROLE_NAME = #{roleName,jdbcType=VARCHAR}");
        }
        
        WHERE("ROLE_CODE = #{roleCode,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin_role
     *
     * @mbggenerated Wed Mar 23 11:14:04 CST 2016
     */
    protected void applyWhere(AdminRoleExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}