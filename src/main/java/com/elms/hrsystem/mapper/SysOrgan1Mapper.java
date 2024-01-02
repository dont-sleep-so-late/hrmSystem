package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SysOrgan1;
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
public interface SysOrgan1Mapper extends BaseMapper<SysOrgan1> {
    @Select("select * " +
            "from sys_organ1")
    List<Map<String,String>> queryOrgan1();
}