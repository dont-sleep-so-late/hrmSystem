package com.elms.hrsystem.service.impl;




import com.elms.hrsystem.entity.SysRole;
import com.elms.hrsystem.mapper.SysRoleMapper;
import com.elms.hrsystem.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<Map<String, String>> queryAllRole() {
        return sysRoleMapper.queryAllRole();
    }
}