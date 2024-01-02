package com.elms.hrsystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Data
@TableName("sal_info")
public class SalInfo  implements Serializable {

    /**
     * 薪酬id
     */
    @TableId
	private String sId;
    /**
     * 登记人id
     */
	private String uId;
    /**
     * 薪酬标准id
     */
	private String o3Id;

    /**
     * 职称id
     */
    private String pId;

    /**
     * 制定人姓名
     */
	private String fName;
    /**
     * 总薪酬
     */
	private Double allmoney;
    /**
     * 基本工资
     */
	private Double basic;

    /**
     * 复核意见
     */
	private String review;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 复核情况【1true】
     */
	private String isCheck;
    /**
     * 是否被驳回【1true】
     */
	private String isReject;
    /**
     * 发放情况【1true】
     */
	private String isGive;
    /**
     * 逻辑删除【1true】
     */
	private String isDeleted;
}