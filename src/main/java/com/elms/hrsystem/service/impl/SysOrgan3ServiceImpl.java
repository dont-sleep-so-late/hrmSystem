package com.elms.hrsystem.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.elms.hrsystem.entity.SysOrgan3;
import com.elms.hrsystem.mapper.SysOrgan3Mapper;
import com.elms.hrsystem.service.SysOrgan3Service;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Service
public class SysOrgan3ServiceImpl extends ServiceImpl<SysOrgan3Mapper, SysOrgan3> implements SysOrgan3Service {

    @Autowired
    private SysOrgan3Mapper sysOrgan3Mapper;


    @Override
    public List<Map<String, String>> queryXinZiBiaoZhun() {
        return sysOrgan3Mapper.queryXinZiBiaoZhun();
    }

    @Override
    public List<Map<String, String>> queryOrgan3(String o2id) {
        return sysOrgan3Mapper.queryOrgan3(o2id);
    }

    @Override
    public void insertOrgan3(SysOrgan3 sysOrgan3) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan3.setO3Id(String.valueOf(id));
        this.save(sysOrgan3);
    }

    @Override
    public void deleteOrgan3(String o3id) {
        this.removeById(o3id);
    }
}