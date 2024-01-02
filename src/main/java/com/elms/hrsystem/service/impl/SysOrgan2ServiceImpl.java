package com.elms.hrsystem.service.impl;




import com.elms.hrsystem.entity.SysOrgan2;
import com.elms.hrsystem.mapper.SysOrgan2Mapper;
import com.elms.hrsystem.service.SysOrgan2Service;
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
public class SysOrgan2ServiceImpl extends ServiceImpl<SysOrgan2Mapper, SysOrgan2> implements SysOrgan2Service {

    @Autowired
    private SysOrgan2Mapper sysOrgan2Mapper;

    @Override
    public List<Map<String, String>> queryOrgan2(String o1id) {
        return sysOrgan2Mapper.queryOrgan2(o1id);
    }

    @Override
    public void insertOrgan2(SysOrgan2 sysOrgan2) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        sysOrgan2.setO2Id(String.valueOf(id));
        this.save(sysOrgan2);
    }
}