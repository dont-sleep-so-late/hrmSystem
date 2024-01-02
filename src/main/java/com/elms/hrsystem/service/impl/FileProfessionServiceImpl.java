package com.elms.hrsystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elms.hrsystem.entity.FileProfession;
import com.elms.hrsystem.mapper.FileProfessionMapper;
import com.elms.hrsystem.service.FileProfessionService;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Service
public class FileProfessionServiceImpl extends ServiceImpl<FileProfessionMapper, FileProfession> implements FileProfessionService {

    @Autowired
    private FileProfessionMapper fileProfessionMapper;

    @Override
    public List<Map<String, String>> queryProfession() {
      return fileProfessionMapper.queryProfession();
    }

    @Override
    public List<Map<String, String>> queryAllProfession(int page, int limit) {
        return fileProfessionMapper.queryAllProfession(page,limit);
    }

    @Override
    public List<Map<String, String>> queryProfessionByProfession(String profession, int page, int limit) {
        return fileProfessionMapper.queryProfessionByProfession(profession,page,limit);
    }

    @Override
    public void insertProfession(FileProfession fileProfession) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        fileProfession.setPId(String.valueOf(id));
        this.save(fileProfession);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProfessionByPid(String profession, String pId) {
        FileProfession fileProfession = fileProfessionMapper.selectById(pId);
        fileProfession.setProfession(profession);
        this.updateById(fileProfession);
    }

    @Override
    public void deleteProfessionByPid(String profession, String pId) {
        QueryWrapper<FileProfession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id",pId).eq("profession",profession);
        this.remove(queryWrapper);
    }
}