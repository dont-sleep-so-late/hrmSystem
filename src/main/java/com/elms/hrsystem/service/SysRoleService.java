package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SysRole;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 返回全部角色
     * @return
     */
    List<Map<String,String>> queryAllRole();
}