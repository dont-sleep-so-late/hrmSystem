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
@TableName("sys_organ2")
public class SysOrgan2 implements Serializable {

    /**
     * 二级机构id
     */
    @TableId
	private String o2Id;
    /**
     * 上级机构id
     */
	private String o1Id;
    /**
     * 二级机构名称
     */
	private String o2Name;
}