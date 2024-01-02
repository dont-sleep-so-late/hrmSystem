package com.elms.hrsystem.service.impl;




import com.elms.hrsystem.entity.FileNation;
import com.elms.hrsystem.mapper.FileNationMapper;
import com.elms.hrsystem.service.FileNationService;
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
public class FileNationServiceImpl extends ServiceImpl<FileNationMapper, FileNation> implements FileNationService {

    @Autowired
    private FileNationMapper fileNationMapper;

    @Override
    public List<Map<String, String>> queryNation() {
        return fileNationMapper.queryNation();
    }
}