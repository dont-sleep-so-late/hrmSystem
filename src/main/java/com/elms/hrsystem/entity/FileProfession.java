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
@TableName("file_profession")
public class FileProfession  implements Serializable {

    /**
     * 职称id
     */
    @TableId
	private String pId;
    /**
     * 职称
     */
	private String profession;
}