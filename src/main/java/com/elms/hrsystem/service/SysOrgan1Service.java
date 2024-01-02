package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SysOrgan1;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SysOrgan1Service extends IService<SysOrgan1> {

    /**
     * 返回一级机构
     * @return
     */
    public List<Map<String,String>> queryOrgan1();

    /**
     * 添加一级机构
     * @param sysOrgan1
     */
    public void insertOrgan1(SysOrgan1 sysOrgan1);
}