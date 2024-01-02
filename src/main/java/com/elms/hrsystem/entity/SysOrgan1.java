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
@TableName("sys_organ1")
public class SysOrgan1 implements Serializable {

    /**
     * 一级机构id
     */
    @TableId
	private String o1Id;
    /**
     * 一级机构名称
     */
	private String o1Name;
}