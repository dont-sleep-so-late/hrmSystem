package com.elms.hrsystem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 薪酬项目表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@Data
@TableName("sal_item")
public class SalItem implements Serializable {

    private static final long serialVersionUID = -1242493306307174690L;

    /**
     * id
     */
    @TableId
	private String id;
    /**
     * 薪酬项目
     */
	private String name;

    /**
     * 公式
     */
    private Double formula;

    /**
     * 追加
     */
    private Double supplement;

    /**
     * 创建者
     */
	private String createdBy;
    /**
     * 创建时间
     */
	private Date createdTime;
    /**
     * 删除标志 true/false 删除/未删除
     */
	private Boolean deleteFlag;
    /**
     * 更新者
     */
	private String updateBy;
    /**
     * 更新时间
     */
	private Date updateTime;

    /**
     * 自定义
     */
    @TableField(exist = false)
    private Double customize;
}