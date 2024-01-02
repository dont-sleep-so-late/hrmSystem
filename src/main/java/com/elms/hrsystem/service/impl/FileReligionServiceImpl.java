package com.elms.hrsystem.service.impl;




import com.elms.hrsystem.entity.FileReligion;
import com.elms.hrsystem.mapper.FileReligionMapper;
import com.elms.hrsystem.service.FileReligionService;
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
public class FileReligionServiceImpl extends ServiceImpl<FileReligionMapper, FileReligion> implements FileReligionService {

    @Autowired
    private FileReligionMapper fileReligionMapper;

    @Override
    public List<Map<String, String>> queryReligion() {
       return fileReligionMapper.queryReligion();
    }
}