package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.FileNation;
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
public interface FileNationMapper extends BaseMapper<FileNation> {

    @Select("select * " +
            "from file_nation")
    List<Map<String,String>> queryNation();
}