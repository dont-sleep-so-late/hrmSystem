package com.elms.hrsystem.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: elms
 * @Description: HRSystem
 * @DateTime: 2023/12/26 1:07
 **/
@Data
public class ItemDto implements Serializable {

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
    private Double customize;
}
