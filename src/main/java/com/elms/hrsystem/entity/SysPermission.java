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
@TableName("sys_permission")
public class SysPermission  implements Serializable {

    /**
     * 权限id
     */
    @TableId
	private String pId;
    /**
     * 权限名称
     */
	private String pName;
    /**
     * 权限路径
     */
	private String path;
    /**
     * 上级权限id
     */
	private String higherPid;
    /**
     * 逻辑删除【1true】
     */
	private String isDeleted;
}