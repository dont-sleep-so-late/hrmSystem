package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SysUser;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * @param sysUser
     * 用户注册
     */
    public void insertUser(SysUser sysUser);


    /**
     * 用户登录信息
     * @param sysUser
     * @return
     */
    public List<Map<String,Object>> login(SysUser sysUser);


    /**
     * 用户登录的权限路径
     * @param sysUser
     * @return
     */
    public List<Map<String,Object>> loginPermission(SysUser sysUser);

    /**
     * 查询所有用户
     * @param page
     * @param limit
     * @return
     */
    public List<Map<String,String>> showAllUser(int page,int limit);

    /**
     * 通过姓名查询用户
     * @param username
     * @param page
     * @param limit
     * @return
     */
    public  List<Map<String,String>> queryUserByName(String username,int page,int limit);

    /**
     * 通过角色身份查询用户(下拉框)
     * @param role_name
     * @param page
     * @param limit
     * @return
     */
    public  List<Map<String,String>> queryUserByRole(String role_name,int page,int limit);

    /**
     * 删除指定用户(通过u_id)
     * @param uid
     */
    public void deleteUserByUid(String uid);

    /**
     * 修改指定用户的角色
     * @param sysUser
     */
    public  void updateUserRoleByUid(SysUser sysUser);


    /**
     * 查询所有机构
     * @param page
     * @param limit
     * @return
     */
    public List<Map<String,String>> queryAllOrganization(int page,int limit);


    /**
     * 通过机构名查询机构
     * @param organName
     * @param page
     * @param limit
     * @return
     */
    public List<Map<String,String>> queryOrganizationByName(String organName,int page,int limit);

}