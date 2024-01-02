package com.elms.hrsystem.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elms.hrsystem.entity.SysUser;
import com.elms.hrsystem.mapper.SysUserMapper;
import com.elms.hrsystem.service.SysUserService;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void insertUser(SysUser sysUser) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",sysUser.getUsername());
        if(Objects.nonNull(this.getOne(queryWrapper))){
            throw new RuntimeException("用户名已存在");
        }
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        String uid=id+"";
        sysUser.setUId(uid);
        sysUser.setIsDeleted("0");
        this.save(sysUser);
    }

    @Override
    public List<Map<String, Object>> login(SysUser sysUser) {
        return sysUserMapper.selectByUser(sysUser);
    }

    @Override
    public List<Map<String, Object>> loginPermission(SysUser sysUser) {
        //大集合
        List<Map<String, Object>> mapList = new ArrayList<>();

        //得到权限id
        List<Map<String, Object>> maps = sysUserMapper.loginPermissionChildById(sysUser.getUsername(),sysUser.getPassword());

        //迭代遍历
        Iterator<Map<String, Object>> iterator = maps.iterator();
        int index=0;
        while (iterator.hasNext()) {
            Map<String, Object> result = iterator.next();

            Map<String,Object> onePath = new HashMap<>();
            List<Map<String, Object>> child = sysUserMapper.loginPermissionId(sysUser.getUsername(),sysUser.getPassword(),(String) result.get("pid"));
            Map<String, Object> permissionInfo = sysUserMapper.loginPermissionInfo((String) result.get("pid"));

            onePath.put("path",permissionInfo.get("path"));
            onePath.put("title",permissionInfo.get("title"));
            onePath.put("children",child);

            mapList.add(index++,onePath);
        }
        return mapList;
    }

    @Override
    public List<Map<String, String>> showAllUser(int page, int limit) {
        return sysUserMapper.showAllUser(page,limit);
    }

    @Override
    public List<Map<String, String>> queryUserByName(String username, int page, int limit) {
        return sysUserMapper.queryUserByName(username,page,limit);
    }

    @Override
    public List<Map<String, String>> queryUserByRole(String role_name, int page, int limit) {
        return sysUserMapper.queryUserByRole(role_name,page,limit);
    }

    @Override
    public void deleteUserByUid(String uid) {
        sysUserMapper.deleteUserByUid(uid);
    }

    @Override
    public void updateUserRoleByUid(SysUser sysUser) {
        sysUserMapper.updateUserRoleByUid(sysUser);
    }

    @Override
    public List<Map<String, String>> queryAllOrganization(int page, int limit) {
        return sysUserMapper.queryAllOrganization(page,limit);
    }

    @Override
    public List<Map<String, String>> queryOrganizationByName(String organName, int page, int limit) {
        return sysUserMapper.queryOrganizationByName(organName,page,limit);
    }
}