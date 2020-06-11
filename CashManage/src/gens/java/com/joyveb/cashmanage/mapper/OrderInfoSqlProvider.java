package com.joyveb.cashmanage.mapper;

import com.joyveb.cashmanage.entity.OrderInfo;
import com.joyveb.cashmanage.entity.OrderInfoExample;
import com.joyveb.cashmanage.entity.OrderInfoExample.Criteria;
import com.joyveb.cashmanage.entity.OrderInfoExample.Criterion;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class OrderInfoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String countByExample(OrderInfoExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("t_order_info");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String deleteByExample(OrderInfoExample example) {
        BEGIN();
        DELETE_FROM("t_order_info");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String insertSelective(OrderInfo record) {
        BEGIN();
        INSERT_INTO("t_order_info");
        
        if (record.getOrderid() != null) {
            VALUES("ORDERID", "#{orderid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderstatus() != null) {
            VALUES("ORDERSTATUS", "#{orderstatus,jdbcType=CHAR}");
        }
        
        if (record.getDelivertime() != null) {
            VALUES("DELIVERTIME", "#{delivertime,jdbcType=VARCHAR}");
        }
        
        if (record.getFailreason() != null) {
            VALUES("FAILREASON", "#{failreason,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsid() != null) {
            VALUES("LOGISTICSID", "#{logisticsid,jdbcType=VARCHAR}");
        }
        
        if (record.getWaybillno() != null) {
            VALUES("WAYBILLNO", "#{waybillno,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsstatus() != null) {
            VALUES("LOGISTICSSTATUS", "#{logisticsstatus,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsremark() != null) {
            VALUES("LOGISTICSREMARK", "#{logisticsremark,jdbcType=VARCHAR}");
        }
        
        if (record.getPaytime() != null) {
            VALUES("PAYTIME", "#{paytime,jdbcType=BIGINT}");
        }
        
        if (record.getUserid() != null) {
            VALUES("USERID", "#{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            VALUES("PARENTNAME", "#{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiverid() != null) {
            VALUES("RECEIVERID", "#{receiverid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrdertime() != null) {
            VALUES("ORDERTIME", "#{ordertime,jdbcType=BIGINT}");
        }
        
        if (record.getTotalamount() != null) {
            VALUES("TOTALAMOUNT", "#{totalamount,jdbcType=DECIMAL}");
        }
        
        if (record.getFreightprice() != null) {
            VALUES("FREIGHTPRICE", "#{freightprice,jdbcType=DECIMAL}");
        }
        
        if (record.getTaketime() != null) {
            VALUES("TAKETIME", "#{taketime,jdbcType=BIGINT}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String selectByExample(OrderInfoExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ORDERID");
        } else {
            SELECT("ORDERID");
        }
        SELECT("ORDERSTATUS");
        SELECT("DELIVERTIME");
        SELECT("FAILREASON");
        SELECT("LOGISTICSID");
        SELECT("WAYBILLNO");
        SELECT("LOGISTICSSTATUS");
        SELECT("LOGISTICSREMARK");
        SELECT("PAYTIME");
        SELECT("USERID");
        SELECT("PARENTNAME");
        SELECT("RECEIVERID");
        SELECT("ORDERTIME");
        SELECT("TOTALAMOUNT");
        SELECT("FREIGHTPRICE");
        SELECT("TAKETIME");
        FROM("t_order_info");
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
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        OrderInfo record = (OrderInfo) parameter.get("record");
        OrderInfoExample example = (OrderInfoExample) parameter.get("example");
        
        BEGIN();
        UPDATE("t_order_info");
        
        if (record!=null && record.getOrderid() != null) {
            SET("ORDERID = #{record.orderid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getOrderstatus() != null) {
            SET("ORDERSTATUS = #{record.orderstatus,jdbcType=CHAR}");
        }
        
        if (record!=null && record.getDelivertime() != null) {
            SET("DELIVERTIME = #{record.delivertime,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getFailreason() != null) {
            SET("FAILREASON = #{record.failreason,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getLogisticsid() != null) {
            SET("LOGISTICSID = #{record.logisticsid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getWaybillno() != null) {
            SET("WAYBILLNO = #{record.waybillno,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getLogisticsstatus() != null) {
            SET("LOGISTICSSTATUS = #{record.logisticsstatus,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getLogisticsremark() != null) {
            SET("LOGISTICSREMARK = #{record.logisticsremark,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getPaytime() != null) {
            SET("PAYTIME = #{record.paytime,jdbcType=BIGINT}");
        }
        
        if (record!=null && record.getUserid() != null) {
            SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getParentname() != null) {
            SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getReceiverid() != null) {
            SET("RECEIVERID = #{record.receiverid,jdbcType=VARCHAR}");
        }
        
        if (record!=null && record.getOrdertime() != null) {
            SET("ORDERTIME = #{record.ordertime,jdbcType=BIGINT}");
        }
        
        if (record!=null && record.getTotalamount() != null) {
            SET("TOTALAMOUNT = #{record.totalamount,jdbcType=DECIMAL}");
        }
        
        if (record!=null && record.getFreightprice() != null) {
            SET("FREIGHTPRICE = #{record.freightprice,jdbcType=DECIMAL}");
        }
        
        if (record!=null && record.getTaketime() != null) {
            SET("TAKETIME = #{record.taketime,jdbcType=BIGINT}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("t_order_info");
        
        SET("ORDERID = #{record.orderid,jdbcType=VARCHAR}");
        SET("ORDERSTATUS = #{record.orderstatus,jdbcType=CHAR}");
        SET("DELIVERTIME = #{record.delivertime,jdbcType=VARCHAR}");
        SET("FAILREASON = #{record.failreason,jdbcType=VARCHAR}");
        SET("LOGISTICSID = #{record.logisticsid,jdbcType=VARCHAR}");
        SET("WAYBILLNO = #{record.waybillno,jdbcType=VARCHAR}");
        SET("LOGISTICSSTATUS = #{record.logisticsstatus,jdbcType=VARCHAR}");
        SET("LOGISTICSREMARK = #{record.logisticsremark,jdbcType=VARCHAR}");
        SET("PAYTIME = #{record.paytime,jdbcType=BIGINT}");
        SET("USERID = #{record.userid,jdbcType=VARCHAR}");
        SET("PARENTNAME = #{record.parentname,jdbcType=VARCHAR}");
        SET("RECEIVERID = #{record.receiverid,jdbcType=VARCHAR}");
        SET("ORDERTIME = #{record.ordertime,jdbcType=BIGINT}");
        SET("TOTALAMOUNT = #{record.totalamount,jdbcType=DECIMAL}");
        SET("FREIGHTPRICE = #{record.freightprice,jdbcType=DECIMAL}");
        SET("TAKETIME = #{record.taketime,jdbcType=BIGINT}");
        
        OrderInfoExample example = (OrderInfoExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    public String updateByPrimaryKeySelective(OrderInfo record) {
        BEGIN();
        UPDATE("t_order_info");
        
        if (record.getOrderstatus() != null) {
            SET("ORDERSTATUS = #{orderstatus,jdbcType=CHAR}");
        }
        
        if (record.getDelivertime() != null) {
            SET("DELIVERTIME = #{delivertime,jdbcType=VARCHAR}");
        }
        
        if (record.getFailreason() != null) {
            SET("FAILREASON = #{failreason,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsid() != null) {
            SET("LOGISTICSID = #{logisticsid,jdbcType=VARCHAR}");
        }
        
        if (record.getWaybillno() != null) {
            SET("WAYBILLNO = #{waybillno,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsstatus() != null) {
            SET("LOGISTICSSTATUS = #{logisticsstatus,jdbcType=VARCHAR}");
        }
        
        if (record.getLogisticsremark() != null) {
            SET("LOGISTICSREMARK = #{logisticsremark,jdbcType=VARCHAR}");
        }
        
        if (record.getPaytime() != null) {
            SET("PAYTIME = #{paytime,jdbcType=BIGINT}");
        }
        
        if (record.getUserid() != null) {
            SET("USERID = #{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getParentname() != null) {
            SET("PARENTNAME = #{parentname,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiverid() != null) {
            SET("RECEIVERID = #{receiverid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrdertime() != null) {
            SET("ORDERTIME = #{ordertime,jdbcType=BIGINT}");
        }
        
        if (record.getTotalamount() != null) {
            SET("TOTALAMOUNT = #{totalamount,jdbcType=DECIMAL}");
        }
        
        if (record.getFreightprice() != null) {
            SET("FREIGHTPRICE = #{freightprice,jdbcType=DECIMAL}");
        }
        
        if (record.getTaketime() != null) {
            SET("TAKETIME = #{taketime,jdbcType=BIGINT}");
        }
        
        WHERE("ORDERID = #{orderid,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order_info
     *
     * @mbggenerated Mon Aug 21 20:00:28 CST 2017
     */
    protected void applyWhere(OrderInfoExample example, boolean includeExamplePhrase) {
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