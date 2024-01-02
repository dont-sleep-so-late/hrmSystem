package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select r_id,role_name " +
            "from sys_role " +
            "where is_deleted='0'")
    List<Map<String,String>> queryAllRole();
}