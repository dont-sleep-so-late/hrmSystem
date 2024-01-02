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
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
     * 用户id
     */
    @TableId
	private String uId;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 角色id
     */
	private String rId;
    /**
     * 逻辑删除【1true】
     */
	private String isDeleted;
}