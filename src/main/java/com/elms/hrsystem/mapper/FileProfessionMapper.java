package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.FileProfession;
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
public interface FileProfessionMapper extends BaseMapper<FileProfession> {

    @Select("select * " +
            "from file_profession")
    List<Map<String,String>> queryProfession();

    @Select("select * \n" +
            "from file_profession\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryAllProfession(@Param("page") int page, @Param("limit") int limit);


    @Select("select * \n" +
            "from file_profession\n" +
            "where profession like concat('%',#{profession},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryProfessionByProfession(@Param("profession") String profession,@Param("page") int page, @Param("limit") int limit);

}