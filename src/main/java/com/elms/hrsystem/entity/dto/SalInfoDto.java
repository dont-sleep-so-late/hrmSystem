package com.elms.hrsystem.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.elms.hrsystem.entity.SalItem;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/22 17:56
 **/
@Data
public class SalInfoDto  implements Serializable {

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
     * 职称
     */
    private String pName;
    /**
     * 基本工资
     */
    private Double basic;
    /**
     * 总薪酬
     */
    private Double allmoney;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 制定人姓名
     */
    private String fname;

    /**
     * 是否被驳回【1true】
     */
    private String isReject;

    /**
     * 薪酬项目
     */
    private List<SalItemDto> items;
}
