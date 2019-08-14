package com.joyveb.cashmanage.exception;

/**
 * DES解密异常
 * 
 * 
 * @Company: 北京畅享互联有限公司
 * @Copyright: Copyright (c) 2011
 * @Author: 章志伟
 * @Date: 2011-10-19 下午01:09:46
 * @version: 1.0
 */
public class DesException extends Exception {

	private static final long serialVersionUID = 1581334538194710140L;

	public DesException() {
		super();
	}

	public DesException(String message) {
		super(message);
	}

	public DesException(String message, Throwable cause) {
		super(message, cause);
	}

	public DesException(Throwable cause) {
		super(cause);
	}
}
