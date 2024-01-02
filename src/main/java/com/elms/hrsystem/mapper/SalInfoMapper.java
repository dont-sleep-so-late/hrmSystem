package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SalInfo;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Mapper
public interface SalInfoMapper extends BaseMapper<SalInfo> {



    @Select("select s.s_id,s.f_name,o.o3_name,u.username,s.allmoney,s.create_time,fp.profession\n" +
            "from sal_info s,sys_organ3 o,sys_user u,file_profession fp\n" +
            "where s.u_id=u.u_id and s.o3_id=o.o3_id AND fp.p_id =s.p_id  and s.is_give='1' " +
            "limit #{page},#{limit}")
    List<Map<String,String>> giveSalAllInfo(@Param("page") int page, @Param("limit") int limit);


    @Select("SELECT s.s_id, s.f_name, o.o3_name, u.username, s.allmoney, s.create_time, fp.profession\n" +
            "FROM sal_info s, sys_organ3 o, sys_user u, file_profession fp\n" +
            "WHERE s.u_id = u.u_id\n" +
            "  AND s.o3_id = o.o3_id\n" +
            "  AND fp.p_id = s.p_id\n" +
            "  AND s.is_give = '1'\n" +
            "  AND s.create_time >= #{startTime} AND s.create_time <= #{endTime}\n" +
            "LIMIT #{pageNum}, #{pageSize}")
    List<Map<String,String>> getAllByIsGiveByTime(@Param("pageNum")int pageNum, @Param("pageSize") int pageSize,@Param("startTime") String startTime,@Param("endTime") String endTime);
}