package com.elms.hrsystem.service.impl;




import com.elms.hrsystem.entity.SysOrgan1;
import com.elms.hrsystem.mapper.SysOrgan1Mapper;
import com.elms.hrsystem.service.SysOrgan1Service;
import com.elms.hrsystem.util.SnowflakeIdWorker;
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
public class SysOrgan1ServiceImpl extends ServiceImpl<SysOrgan1Mapper, SysOrgan1> implements SysOrgan1Service {

    @Autowired
    private SysOrgan1Mapper sysOrgan1Mapper;

    @Override
    public List<Map<String, String>> queryOrgan1() {
        return sysOrgan1Mapper.queryOrgan1();
    }

    @Override
    public void insertOrgan1(SysOrgan1 sysOrgan1) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan1.setO1Id(String.valueOf(id));
        this.save(sysOrgan1);
    }
}