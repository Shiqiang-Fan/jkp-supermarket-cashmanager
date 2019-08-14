package com.joyveb.cashmanage.mapper;

import com.joyveb.cashmanage.entity.WhiteList;
import com.joyveb.cashmanage.entity.WhiteListExample;
import com.joyveb.cashmanage.entity.WhiteListExample.Criteria;
import com.joyveb.cashmanage.entity.WhiteListExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class WhiteListSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String countByExample(WhiteListExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_acc_whitelist");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String deleteByExample(WhiteListExample example) {
        BEGIN();
        DELETE_FROM("t_acc_whitelist");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String insertSelective(WhiteList record) {
        BEGIN();
        INSERT_INTO("t_acc_whitelist");
        
        if (record.getUuid() != null) {
            VALUES("UUID", "#{uuid,jdbcType=VARCHAR}");
        }
        
        if (record.getFullname() != null) {
            VALUES("FULLNAME", "#{fullname,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcard() != null) {
            VALUES("IDCARD", "#{idcard,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            VALUES("MOBILE", "#{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            VALUES("ADDRESS", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getTransfercycle() != null) {
            VALUES("TRANSFERCYCLE", "#{transfercycle,jdbcType=INTEGER}");
        }
        
        if (record.getUserid() != null) {
            VALUES("USERID", "#{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            VALUES("STATUS", "#{status,jdbcType=CHAR}");
        }
        
        if (record.getUserkey() != null) {
            VALUES("USERKEY", "#{userkey,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            VALUES("COMMENT", "#{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUsetime() != null) {
            VALUES("USETIME", "#{usetime,jdbcType=BIGINT}");
        }
        
        if (record.getDeposit() != null) {
            VALUES("DEPOSIT", "#{deposit,jdbcType=BIGINT}");
        }
        
        if (record.getDevicetype() != null) {
            VALUES("DEVICETYPE", "#{devicetype,jdbcType=VARCHAR}");
        }
        
        if (record.getDevicenum() != null) {
            VALUES("DEVICENUM", "#{devicenum,jdbcType=VARCHAR}");
        }
        
        if (record.getCommission() != null) {
            VALUES("COMMISSION", "#{commission,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            VALUES("PARENTNAME", "#{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            VALUES("USERNAME", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            VALUES("PASSWORD", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipaynum() != null) {
            VALUES("ALIPAYNUM", "#{alipaynum,jdbcType=VARCHAR}");
        }
        
        if (record.getLogintime() != null) {
            VALUES("LOGINTIME", "#{logintime,jdbcType=BIGINT}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String selectByExample(WhiteListExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("UUID");
        } else {
            SELECT("UUID");
        }
        SELECT("FULLNAME");
        SELECT("IDCARD");
        SELECT("MOBILE");
        SELECT("ADDRESS");
        SELECT("TRANSFERCYCLE");
        SELECT("USERID");
        SELECT("STATUS");
        SELECT("USERKEY");
        SELECT("COMMENT");
        SELECT("USETIME");
        SELECT("DEPOSIT");
        SELECT("DEVICETYPE");
        SELECT("DEVICENUM");
        SELECT("COMMISSION");
        SELECT("PARENTNAME");
        SELECT("USERNAME");
        SELECT("PASSWORD");
        SELECT("ALIPAYNUM");
        SELECT("LOGINTIME");
        FROM("t_acc_whitelist");
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
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        WhiteList record = (WhiteList) parameter.get("record");
        WhiteListExample example = (WhiteListExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_acc_whitelist");
        
        if (record!=null && record.getUuid() != null) {
            SET("UUID = #{record.uuid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getFullname() != null) {
            SET("FULLNAME = #{record.fullname,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getIdcard() != null) {
            SET("IDCARD = #{record.idcard,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getMobile() != null) {
            SET("MOBILE = #{record.mobile,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getAddress() != null) {
            SET("ADDRESS = #{record.address,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getTransfercycle() != null) {
            SET("TRANSFERCYCLE = #{record.transfercycle,jdbcType=INTEGER}");
        }
        
        if (record!=null && record.getUserid() != null) {
            SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getStatus() != null) {
            SET("STATUS = #{record.status,jdbcType=CHAR}");
        }
        
        if (record!=null && record.getUserkey() != null) {
            SET("USERKEY = #{record.userkey,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getComment() != null) {
            SET("COMMENT = #{record.comment,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getUsetime() != null) {
            SET("USETIME = #{record.usetime,jdbcType=BIGINT}");
        }
        
        if (record!=null && record.getDeposit() != null) {
            SET("DEPOSIT = #{record.deposit,jdbcType=BIGINT}");
        }
        
        if (record!=null && record.getDevicetype() != null) {
            SET("DEVICETYPE = #{record.devicetype,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getDevicenum() != null) {
            SET("DEVICENUM = #{record.devicenum,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getCommission() != null) {
            SET("COMMISSION = #{record.commission,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getParentname() != null) {
            SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getUsername() != null) {
            SET("USERNAME = #{record.username,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getPassword() != null) {
            SET("PASSWORD = #{record.password,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getAlipaynum() != null) {
            SET("ALIPAYNUM = #{record.alipaynum,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getLogintime() != null) {
            SET("LOGINTIME = #{record.logintime,jdbcType=BIGINT}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_acc_whitelist");
        
        SET("UUID = #{record.uuid,jdbcType=VARCHAR}");
        SET("FULLNAME = #{record.fullname,jdbcType=VARCHAR}");
        SET("IDCARD = #{record.idcard,jdbcType=VARCHAR}");
        SET("MOBILE = #{record.mobile,jdbcType=VARCHAR}");
        SET("ADDRESS = #{record.address,jdbcType=VARCHAR}");
        SET("TRANSFERCYCLE = #{record.transfercycle,jdbcType=INTEGER}");
        SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        SET("STATUS = #{record.status,jdbcType=CHAR}");
        SET("USERKEY = #{record.userkey,jdbcType=VARCHAR}");
        SET("COMMENT = #{record.comment,jdbcType=VARCHAR}");
        SET("USETIME = #{record.usetime,jdbcType=BIGINT}");
        SET("DEPOSIT = #{record.deposit,jdbcType=BIGINT}");
        SET("DEVICETYPE = #{record.devicetype,jdbcType=VARCHAR}");
        SET("DEVICENUM = #{record.devicenum,jdbcType=VARCHAR}");
        SET("COMMISSION = #{record.commission,jdbcType=VARCHAR}");
        SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        SET("USERNAME = #{record.username,jdbcType=VARCHAR}");
        SET("PASSWORD = #{record.password,jdbcType=VARCHAR}");
        SET("ALIPAYNUM = #{record.alipaynum,jdbcType=VARCHAR}");
        SET("LOGINTIME = #{record.logintime,jdbcType=BIGINT}");
        
        WhiteListExample example = (WhiteListExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    public String updateByPrimaryKeySelective(WhiteList record) {
        BEGIN();
        UPDATE("t_acc_whitelist");
        
        if (record.getFullname() != null) {
            SET("FULLNAME = #{fullname,jdbcType=VARCHAR}");
        }
        
        if (record.getIdcard() != null) {
            SET("IDCARD = #{idcard,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            SET("MOBILE = #{mobile,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            SET("ADDRESS = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getTransfercycle() != null) {
            SET("TRANSFERCYCLE = #{transfercycle,jdbcType=INTEGER}");
        }
        
        if (record.getUserid() != null) {
            SET("USERID = #{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{status,jdbcType=CHAR}");
        }
        
        if (record.getUserkey() != null) {
            SET("USERKEY = #{userkey,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            SET("COMMENT = #{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getUsetime() != null) {
            SET("USETIME = #{usetime,jdbcType=BIGINT}");
        }
        
        if (record.getDeposit() != null) {
            SET("DEPOSIT = #{deposit,jdbcType=BIGINT}");
        }
        
        if (record.getDevicetype() != null) {
            SET("DEVICETYPE = #{devicetype,jdbcType=VARCHAR}");
        }
        
        if (record.getDevicenum() != null) {
            SET("DEVICENUM = #{devicenum,jdbcType=VARCHAR}");
        }
        
        if (record.getCommission() != null) {
            SET("COMMISSION = #{commission,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            SET("PARENTNAME = #{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            SET("USERNAME = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("PASSWORD = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getAlipaynum() != null) {
            SET("ALIPAYNUM = #{alipaynum,jdbcType=VARCHAR}");
        }
        
        if (record.getLogintime() != null) {
            SET("LOGINTIME = #{logintime,jdbcType=BIGINT}");
        }
        
        WHERE("UUID = #{uuid,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_acc_whitelist
     *
     * @mbggenerated Thu Aug 17 17:56:38 CST 2017
     */
    protected void applyWhere(WhiteListExample example, boolean includeExamplePhrase) {
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