package com.joyveb.cashmanage.bean.response;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper=true)
public class RepQueryGameEntity extends AbstractEntity{
	private String gamename;
	private String gamecode;
	private Integer facevalue;
	private Integer booktotal;
	private String previewpath;
	private String detailedpath;
	private String price;
}
