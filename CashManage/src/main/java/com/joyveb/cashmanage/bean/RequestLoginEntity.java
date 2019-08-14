package com.joyveb.cashmanage.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RequestLoginEntity extends HeadEntityHolder{

	private AbstractEntity body;

}
