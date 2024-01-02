package com.elms.hrsystem.entity.dto;

import com.elms.hrsystem.entity.SalItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/22 15:54
 **/
@Data
public class SalDto implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 薪酬标准登记id
     */
    private String sid;

    /**
     * 登记人id
     */
    private String uid;
    /**
     * 薪酬标准id
     */
    private String o3Id;

    /**
     * 职称id
     */
    private String pid;

    /**
     * 制定人姓名
     */
    private String fname;

    /**
     * 基本工资
     */
    private Double basic;

    /**
     * 薪酬项目id
     */
    private List<SalItem> items;

}
