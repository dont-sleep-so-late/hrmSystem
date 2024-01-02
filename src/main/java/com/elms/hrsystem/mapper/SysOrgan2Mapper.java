package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SysOrgan2;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface SysOrgan2Mapper extends BaseMapper<SysOrgan2> {

    @Select("select * " +
            "from sys_organ2 " +
            "where o1_id=#{o1id}")
    List<Map<String,String>> queryOrgan2(@Param("o1id") String o1id);
}