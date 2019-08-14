package com.joyveb.cashmanage.xml.bean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseMessage1010Body extends ResponseMessageBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1666370183398024267L;
//	private String isFinish;
	private List<ResponseRecord> records;
}
