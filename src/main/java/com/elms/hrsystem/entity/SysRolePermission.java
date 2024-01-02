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
@TableName("sys_role_permission")
public class SysRolePermission implements Serializable {

    /**
     * 角色id
     */
    @TableId
	private String rId;
    /**
     * 权限id
     */
	private String pId;
    /**
     * 逻辑删除【1true】
     */
	private String isDeleted;
}