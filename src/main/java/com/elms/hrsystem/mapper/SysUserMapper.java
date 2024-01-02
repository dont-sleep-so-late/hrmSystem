package com.elms.hrsystem.mapper;

import com.elms.hrsystem.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("select * " +
            "from sys_user " +
            "where username=#{username} and `password`=#{password} and is_deleted='0'")
    List<Map<String,Object>> selectByUser(SysUser sysUser);


    @Select("select distinct p.higher_pid pid " +
            "from sys_user u,sys_role_permission rp,sys_permission p " +
            "where u.username=#{username} and u.`password`=#{password} and u.is_deleted='0' and u.r_id=rp.r_id and rp.p_id=p.p_id")
    List<Map<String,Object>> loginPermissionChildById(@Param("username") String username, @Param("password") String password);


    @Select("select p.p_name title,p.path path " +
            "from sys_user u,sys_role_permission rp,sys_permission p " +
            "where u.username=#{username} and u.`password`=#{password} and u.is_deleted='0' and u.r_id=rp.r_id and rp.p_id=p.p_id and p.higher_pid=#{higherId}")
    List<Map<String,Object>> loginPermissionId(@Param("username") String username,@Param("password") String password,@Param("higherId") String higherId);


    @Select("select p.p_name title,p.path path " +
            "from sys_permission p " +
            "where p.p_id=#{pid}")
    Map<String,Object> loginPermissionInfo(@Param("pid") String pid);

    @Select("select u.u_id,u.username,u.`password`,r.role_name " +
            "from sys_user u,sys_role r " +
            "where u.r_id=r.r_id and u.is_deleted='0'" +
            "limit #{page},#{limit}")
    List<Map<String,String>> showAllUser(@Param("page") int page, @Param("limit") int limit);


    @Select("select u.u_id,u.username,u.`password`,r.role_name\n" +
            "from sys_user u,sys_role r\n" +
            "where u.r_id=r.r_id and u.is_deleted='0' and u.username like concat('%',#{username},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryUserByName(@Param("username") String username,@Param("page") int page, @Param("limit") int limit);


    @Select("select u.u_id,u.username,u.`password`,r.role_name\n" +
            "from sys_user u,sys_role r\n" +
            "where u.r_id=r.r_id and u.is_deleted='0' and r.role_name like concat('%',#{role_name},'%')\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryUserByRole(@Param("role_name") String role_name,@Param("page") int page, @Param("limit") int limit);

    @Update("Update sys_user " +
            "set is_deleted='1' " +
            "where u_id=#{uid}")
    void deleteUserByUid(@Param("uid") String uid);

    @Update("Update sys_user " +
            "set r_id=#{rId} " +
            "where u_id=#{uId}")
    void updateUserRoleByUid(SysUser sysUser);

    @Select("select o3.o3_id id,o1.o1_name,o2.o2_name,o3.o3_name\n" +
            "from sys_organ1 o1,sys_organ2 o2,sys_organ3 o3\n" +
            "where o1.o1_id=o2.o1_id and o2.o2_id=o3.o2_id\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryAllOrganization(@Param("page") int page, @Param("limit") int limit);


    @Select("select o3.o3_id id,o1.o1_name,o2.o2_name,o3.o3_name\n" +
            "from sys_organ1 o1,sys_organ2 o2,sys_organ3 o3\n" +
            "where o1.o1_id=o2.o1_id and o2.o2_id=o3.o2_id \n" +
            "and (o1.o1_name like concat('%',#{organName},'%') or o2.o2_name like concat('%',#{organName},'%') or o3.o3_name like concat('%',#{organName},'%'))\n" +
            "limit #{page},#{limit}")
    List<Map<String,String>> queryOrganizationByName(@Param("organName") String organName,@Param("page") int page, @Param("limit") int limit);
}