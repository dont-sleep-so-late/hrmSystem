package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SysOrgan3;
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
public interface SysOrgan3Mapper extends BaseMapper<SysOrgan3> {

    @Select("select * " +
            "from sys_organ3 " +
            "where o2_id=#{o2id}")
    List<Map<String,String>> queryOrgan3(@Param("o2id") String o2id);

    @Select("select o3_id,o.o3_name " +
            "from sys_organ3 o")
    List<Map<String,String>> queryXinZiBiaoZhun();
}