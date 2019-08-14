package com.joyveb.cashmanage.bean.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.joyveb.cashmanage.bean.AbstractEntity;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepVersionEntity extends AbstractEntity {
    private Long pubtime;

    private String comment;

    private String url;

    private Integer vol;
    
    private String version;
}