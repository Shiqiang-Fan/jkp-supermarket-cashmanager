package com.joyveb.cashmanage.bean.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepOrderEntity extends AbstractEntity{
	private List<RepXqOrderEntity> repXqOrderEntities; 
	private String rcode;
	private String des;
}
