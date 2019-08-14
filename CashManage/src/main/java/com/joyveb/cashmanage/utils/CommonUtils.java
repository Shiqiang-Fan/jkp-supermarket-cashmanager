package com.joyveb.cashmanage.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;
@Slf4j
public class CommonUtils {
	public static final String SEPARATOR = "_";
	public SimpleDateFormat yymmdd = new SimpleDateFormat("yyyyMMdd");
	public SimpleDateFormat yyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmmss");
	public static boolean isNotEmpty(List<?> records) {
		if (records != null && records.size() > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(List<?> records){
		return !CommonUtils.isNotEmpty(records);
	}

	public static String append(String... key) {
		StringBuilder s = new StringBuilder();
		for (String tmp : key) {
			s.append(tmp).append(SEPARATOR);
		}
		return s.toString().substring(0, s.length() - 1);
	}

	//将字符串中的数据进行排序
	public static String sortString(String str){
		if(StringUtils.isNotBlank(str)){
			String[] strArray = str.split("|");
			Arrays.sort(strArray);
			return strArray[0]+strArray[1]+strArray[2]+"";
		}
		return null;
	}
	public static String returnYYMMDD(){
		CommonUtils commonUtils = new CommonUtils();
		String format = commonUtils.yymmdd.format(currentTimeMillis());
		return format;
	}
	public static String returnYYMMDDHHMM(){
		CommonUtils commonUtils = new CommonUtils();
		String format = commonUtils.yyMMddHHmm.format(currentTimeMillis());
		return format;
	}
	public static String returnYesterdayDate(){
		try {
			CommonUtils commonUtils = new CommonUtils();
			String format = commonUtils.yymmdd.format(currentTimeMillis());
			Date parse = commonUtils.yymmdd.parse(format);
			Date date = DateUtils.addDays(parse, -1);
			String yesterday = commonUtils.yymmdd.format(date);
			return yesterday;
		} catch (ParseException e) {
			log.debug("日期格式转换错误",e);
		}
		return null;
	}
}
