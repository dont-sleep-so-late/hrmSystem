package com.elms.hrsystem.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/25 1:27
 **/
@Data
public class OrganDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sid;

    private String o3id;

    private String o1name;

    private String o2name;

    private String o3name;

    private Integer count;

    private Double totalMoney;
}
