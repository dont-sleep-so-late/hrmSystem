package com.elms.hrsystem.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/22 19:05
 **/
@Data
public class SalItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 薪酬项目
     */
    private String name;

    /**
     * 薪酬项目
     */
    private Double value;
}
