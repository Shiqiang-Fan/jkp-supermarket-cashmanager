package com.joyveb.cashmanage.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by liuyangkly on 15/6/27.
 * 杂物工具类
 */
@Slf4j
public class Utils {

    private Utils() {
        // No instances.
    }

    public static String toAmount(long amount) {
        return new BigDecimal(amount).divide(new BigDecimal(100)).toString();
    }

    public static String toDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static boolean isEmpty(Object object) {
        if (object instanceof String) {
            return StringUtils.isEmpty((String) object);
        }
        return object == null;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static <T> boolean isListNotEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }

    public static <T> boolean isListEmpty(List<T> list) {
        return !isListNotEmpty(list);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.warn("Interrupted异常",e);
            Thread.currentThread().interrupt();
        }
    }
    public static boolean isdiff(long time1){
    	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String nowt = sim.format(System.currentTimeMillis());
		String casht = sim.format(time1);
		Calendar cdnow = Calendar.getInstance();
		Calendar cdcash = Calendar.getInstance();
		try {
			cdnow.setTime(sim.parse(nowt));
			cdcash.setTime(sim.parse(casht));
			int nowdays = cdnow.get(Calendar.DAY_OF_WEEK);
			int cashdays = cdcash.get(Calendar.DAY_OF_WEEK);
			if(nowdays==cashdays){
				return true;
			}
		} catch (ParseException e) {
            log.warn("日期转换异常",e);
		}
		return false;
    }
}
