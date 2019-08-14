package com.joyveb.cashmanage.mapper;

import com.joyveb.cashmanage.entity.Prizer;
import com.joyveb.cashmanage.entity.PrizerExample;
import com.joyveb.cashmanage.entity.PrizerExample.Criteria;
import com.joyveb.cashmanage.entity.PrizerExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class PrizerSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String countByExample(PrizerExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_core_prizer");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String deleteByExample(PrizerExample example) {
        BEGIN();
        DELETE_FROM("t_core_prizer");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String insertSelective(Prizer record) {
        BEGIN();
        INSERT_INTO("t_core_prizer");
        
        if (record.getMessageid() != null) {
            VALUES("MESSAGEID", "#{messageid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            VALUES("USERID", "#{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            VALUES("PARENTNAME", "#{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getDeviceid() != null) {
            VALUES("DEVICEID", "#{deviceid,jdbcType=VARCHAR}");
        }
        
        if (record.getBarcode() != null) {
            VALUES("BARCODE", "#{barcode,jdbcType=VARCHAR}");
        }
        
        if (record.getAmountsign() != null) {
            VALUES("AMOUNTSIGN", "#{amountsign,jdbcType=CHAR}");
        }
        
        if (record.getTransferstatus() != null) {
            VALUES("TRANSFERSTATUS", "#{transferstatus,jdbcType=CHAR}");
        }
        
        if (record.getWinamount() != null) {
            VALUES("WINAMOUNT", "#{winamount,jdbcType=DECIMAL}");
        }
        
        if (record.getRequesttime() != null) {
            VALUES("REQUESTTIME", "#{requesttime,jdbcType=BIGINT}");
        }
        
        if (record.getTransfertime() != null) {
            VALUES("TRANSFERTIME", "#{transfertime,jdbcType=BIGINT}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String selectByExample(PrizerExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("MESSAGEID");
        } else {
            SELECT("MESSAGEID");
        }
        SELECT("USERID");
        SELECT("PARENTNAME");
        SELECT("DEVICEID");
        SELECT("BARCODE");
        SELECT("AMOUNTSIGN");
        SELECT("TRANSFERSTATUS");
        SELECT("WINAMOUNT");
        SELECT("REQUESTTIME");
        SELECT("TRANSFERTIME");
        FROM("t_core_prizer");
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
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        Prizer record = (Prizer) parameter.get("record");
        PrizerExample example = (PrizerExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_core_prizer");
        
        if (record!=null && record.getMessageid() != null) {
            SET("MESSAGEID = #{record.messageid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getUserid() != null) {
            SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getParentname() != null) {
            SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getDeviceid() != null) {
            SET("DEVICEID = #{record.deviceid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getBarcode() != null) {
            SET("BARCODE = #{record.barcode,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getAmountsign() != null) {
            SET("AMOUNTSIGN = #{record.amountsign,jdbcType=CHAR}");
        }
        
        if (record!=null && record.getTransferstatus() != null) {
            SET("TRANSFERSTATUS = #{record.transferstatus,jdbcType=CHAR}");
        }
        
        if (record!=null && record.getWinamount() != null) {
            SET("WINAMOUNT = #{record.winamount,jdbcType=DECIMAL}");
        }
        
        if (record!=null && record.getRequesttime() != null) {
            SET("REQUESTTIME = #{record.requesttime,jdbcType=BIGINT}");
        }
        
        if (record!=null && record.getTransfertime() != null) {
            SET("TRANSFERTIME = #{record.transfertime,jdbcType=BIGINT}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_core_prizer");
        
        SET("MESSAGEID = #{record.messageid,jdbcType=VARCHAR}");
        SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        SET("DEVICEID = #{record.deviceid,jdbcType=VARCHAR}");
        SET("BARCODE = #{record.barcode,jdbcType=VARCHAR}");
        SET("AMOUNTSIGN = #{record.amountsign,jdbcType=CHAR}");
        SET("TRANSFERSTATUS = #{record.transferstatus,jdbcType=CHAR}");
        SET("WINAMOUNT = #{record.winamount,jdbcType=DECIMAL}");
        SET("REQUESTTIME = #{record.requesttime,jdbcType=BIGINT}");
        SET("TRANSFERTIME = #{record.transfertime,jdbcType=BIGINT}");
        
        PrizerExample example = (PrizerExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByPrimaryKeySelective(Prizer record) {
        BEGIN();
        UPDATE("t_core_prizer");
        
        if (record.getUserid() != null) {
            SET("USERID = #{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            SET("PARENTNAME = #{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getDeviceid() != null) {
            SET("DEVICEID = #{deviceid,jdbcType=VARCHAR}");
        }
        
        if (record.getBarcode() != null) {
            SET("BARCODE = #{barcode,jdbcType=VARCHAR}");
        }
        
        if (record.getAmountsign() != null) {
            SET("AMOUNTSIGN = #{amountsign,jdbcType=CHAR}");
        }
        
        if (record.getTransferstatus() != null) {
            SET("TRANSFERSTATUS = #{transferstatus,jdbcType=CHAR}");
        }
        
        if (record.getWinamount() != null) {
            SET("WINAMOUNT = #{winamount,jdbcType=DECIMAL}");
        }
        
        if (record.getRequesttime() != null) {
            SET("REQUESTTIME = #{requesttime,jdbcType=BIGINT}");
        }
        
        if (record.getTransfertime() != null) {
            SET("TRANSFERTIME = #{transfertime,jdbcType=BIGINT}");
        }
        
        WHERE("MESSAGEID = #{messageid,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_core_prizer
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected void applyWhere(PrizerExample example, boolean includeExamplePhrase) {
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