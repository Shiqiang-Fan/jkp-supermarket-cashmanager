package com.joyveb.cashmanage.bean.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepGameEntity extends AbstractEntity{
	private Long ordernum;
	private Integer orderamount;
	private String gamecode;
	private String gamename;
	private String previewpath;
	private String detailedpath;
}
