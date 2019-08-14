package com.joyveb.lbos.restful.spring;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

/**
 * Copyright © 2014畅享互联.
 * 
 * @Title: QueryMapperBean.java
 * @Prject: LBOS
 * @Package: com.joyveb.lbos.restful.spring
 * @date: 2014年8月20日 下午2:28:36
 * @author: yangqiju
 */

public class QueryMapperBean {
	private @Getter List<OrBean> ors = new ArrayList<QueryMapperBean.OrBean>();
	private @Getter List<EqualBean> equals = new ArrayList<QueryMapperBean.EqualBean>();
	private @Getter List<ConditionBean> conditions = new ArrayList<QueryMapperBean.ConditionBean>();
	private @Getter List<LikeBean> likes = new ArrayList<QueryMapperBean.LikeBean>();

	public QueryMapperBean addOr(String fieldName, Object value) {
		ors.add(new OrBean(fieldName, value));
		return this;
	}

	public QueryMapperBean addEqual(String fieldName, Object value) {
		equals.add(new EqualBean(fieldName, value));
		return this;
	}

	public QueryMapperBean addLike(String fieldName, String regex,
			String options) {
		likes.add(new LikeBean(fieldName, regex, options));
		return this;
	}

	public QueryMapperBean addCondition(String fieldName, String condi,
			Object value) {
		conditions.add(new ConditionBean(fieldName, condi, value));
		return this;
	}

	@Data
	public static class LikeBean {
		private String fieldName;
		private String regex;
		private String options;

		public LikeBean() {
		}

		public LikeBean(String fieldName, String regex, String options) {
			super();
			this.fieldName = fieldName;
			this.regex = regex;
			this.options = options;
		}
	}

	@Data
	public static class ConditionBean {
		private String fieldName;
		private String condi;
		private Object value;

		public ConditionBean() {
		}

		public ConditionBean(String fieldName, String condi, Object value) {
			super();
			this.fieldName = fieldName;
			this.condi = condi;
			this.value = value;
		}
	}

	@Data
	public static class EqualBean {
		private String fieldName;
		private Object value;

		public EqualBean() {
		}

		public EqualBean(String fieldName, Object value) {
			super();
			this.fieldName = fieldName;
			this.value = value;
		}
	}

	@Data
	public static class OrBean {
		private String fieldName;
		private Object value;

		public OrBean() {
		}

		public OrBean(String fieldName, Object value) {
			super();
			this.fieldName = fieldName;
			this.value = value;
		}
	}

}
