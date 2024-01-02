package com.elms.hrsystem.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elms.hrsystem.entity.SalInfo;
import com.elms.hrsystem.entity.SalInfoItem;
import com.elms.hrsystem.entity.SalItem;
import com.elms.hrsystem.entity.dto.ItemDto;
import com.elms.hrsystem.mapper.SalInfoItemMapper;
import com.elms.hrsystem.mapper.SalInfoMapper;
import com.elms.hrsystem.mapper.SalItemMapper;
import com.elms.hrsystem.service.SalInfoItemService;
import com.elms.hrsystem.service.SalItemService;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * 薪酬项目表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@Service
public class SalItemServiceImpl extends ServiceImpl<SalItemMapper, SalItem> implements SalItemService {

    @Autowired
    private SalItemMapper mapper;

    @Autowired
    private SalInfoItemMapper salInfoItemMapper;

    @Autowired
    private SalInfoItemService salInfoItemService;

    @Autowired
    private SalInfoMapper salInfoMapper;

    @Override
    public SalItem insertSalItem(ItemDto salItem) {

        SalItem item=new SalItem();

        item.setName(salItem.getName());
        item.setFormula(salItem.getFormula());
        item.setSupplement(salItem.getSupplement());
        item.setCreatedBy(salItem.getCreatedBy());


        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(currentDateTime);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            item.setCreatedTime(df.parse(format));
            item.setUpdateTime(df.parse(format));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        item.setId(String.valueOf(id));
        boolean save = this.save(item);
        if (save) {
            return item;
        }
        return null;
    }

    @Override
    public List<SalItem> list(int pageSize, int pageNum) {
        Page<SalItem> page = new Page<>(pageNum, pageSize);
        IPage<SalItem> salItemIPage = mapper.selectPage(page, null);
        return salItemIPage.getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        QueryWrapper<SalInfoItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("item_id", id);
        salInfoItemMapper.delete(queryWrapper);
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(ItemDto salItem) {

        SalItem item=new SalItem();

        item.setId(salItem.getId());
        item.setName(salItem.getName());
        item.setFormula(salItem.getFormula());
        item.setSupplement(salItem.getSupplement());
        item.setCreatedBy(salItem.getCreatedBy());
        item.setUpdateBy(salItem.getUpdateBy());
        item.setCreatedTime(salItem.getCreatedTime());
        item.setDeleteFlag(salItem.getDeleteFlag());
        item.setCustomize(salItem.getCustomize());

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(currentDateTime);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            item.setUpdateTime(df.parse(format));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        QueryWrapper<SalInfoItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("item_id", salItem.getId());
        List<SalInfoItem> salInfoItems = salInfoItemMapper.selectList(queryWrapper);

        if (salInfoItems.isEmpty()){
            return this.updateById(item);
        }

        for (SalInfoItem salInfoItem : salInfoItems) {
            SalInfo salInfo = salInfoMapper.selectById(salInfoItem.getInfoId());

            // 判断是否已发放
            if (Objects.equals(salInfo.getIsGive(), "1")) {
                continue;
            }
            salInfo.setAllmoney(salInfo.getAllmoney()-salInfoItem.getValue());

            if (!Objects.equals(salItem.getFormula(),0.0)){
                salInfoItem.setValue(salInfo.getBasic()*salItem.getFormula());
            }

            if (!Objects.equals(salItem.getSupplement(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+salItem.getSupplement());
            }
            salInfo.setAllmoney(salInfo.getAllmoney()+salInfoItem.getValue());
            salInfoItemMapper.updateById(salInfoItem);
            salInfoMapper.updateById(salInfo);
        }
        return this.updateById(item);
    }
}