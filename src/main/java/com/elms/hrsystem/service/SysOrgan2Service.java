package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SysOrgan2;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SysOrgan2Service extends IService<SysOrgan2> {

    /**
     * 根据一级机构返回二级机构
     * @param o1id
     * @return
     */
    public List<Map<String,String>> queryOrgan2(String o1id);

    /**
     * 添加二级机构
     * @param sysOrgan2
     */
    public void insertOrgan2(SysOrgan2 sysOrgan2);
}