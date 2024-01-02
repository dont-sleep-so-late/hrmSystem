package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.FileReligion;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface FileReligionService extends IService<FileReligion> {


    /**
     * 查询特定信息
     * @return
     */
    public List<Map<String,String>> queryReligion();
}