package com.joyveb.cashmanage.action;

import com.joyveb.cashmanage.bean.TransMessageEntity;

public interface Actionable {
	public String action(TransMessageEntity msg);

}
