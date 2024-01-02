package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SysOrgan3;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SysOrgan3Service extends IService<SysOrgan3> {


    /**
     * 薪酬登记【查询"薪酬标准名称"（下拉框展示）】
     * @return
     */
    public List<Map<String,String>> queryXinZiBiaoZhun();

    /**
     * 根据二级机构返回三级机构
     * @param o2id
     * @return
     */
    List<Map<String,String>> queryOrgan3(String o2id);


    /**
     * 添加三级机构
     * @param sysOrgan3
     */
    public void insertOrgan3(SysOrgan3 sysOrgan3);

    /**
     * 删除三级机构
     * @param o3id
     */
    public void deleteOrgan3(String o3id);



}