package com.elms.hrsystem.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/26 21:15
 **/
@Data
public class SalGiveDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 薪酬id
     */
    private String sid;

    /**
     * 受理人
     */
    private String username;

    /**
     * 薪酬标准
     */
    private String o3Name;

    /**
     * 薪酬标准
     */
    private String o1Name;
    /**
     * 薪酬标准
     */
    private String o2Name;


    /**
     * 薪酬标准id
     */
    private String o3id;

    /**
     * 职称
     */
    private String pName;
    /**
     * 职称id
     */
    private String pid;

    /**
     * 基本工资
     */
    private Double basic;

    /**
     * 基本工资总额
     */
    private Double total;

    /**
     * 该项目组下相同职位的人数
     */
    private Integer count;

}
