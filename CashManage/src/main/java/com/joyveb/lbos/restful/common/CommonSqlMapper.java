package com.joyveb.lbos.restful.common;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

public interface CommonSqlMapper {

	@SelectProvider(type=CommonSqlProvider.class,method="executeSql")
	public List<HashMap<String,Object>> executeSql(String sql);
	
	public static class CommonSqlProvider {

		public String executeSql(String sql){
			return sql;
		}
	}
}
