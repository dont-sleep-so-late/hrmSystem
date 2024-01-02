package com.elms.hrsystem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 薪酬项目关联表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@Data
@TableName("sal_info_item")
public class SalInfoItem  implements Serializable {

    /**
     * id
     */
    @TableId
	private String id;
    /**
     * 薪酬表id
     */
	private String infoId;
    /**
     * 项目表id
     */
	private String itemId;
    /**
     * 薪酬项目
     */
    private Double value;
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
}