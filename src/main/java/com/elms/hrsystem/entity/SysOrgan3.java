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
@TableName("sys_organ3")
public class SysOrgan3  implements Serializable {

    /**
     * 三级机构id
     */
    @TableId
	private String o3Id;
    /**
     * 上级机构id
     */
	private String o2Id;
    /**
     * 三级机构名称
     */
	private String o3Name;
}