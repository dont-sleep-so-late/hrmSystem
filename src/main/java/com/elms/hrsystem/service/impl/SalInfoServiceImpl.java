package com.elms.hrsystem.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elms.hrsystem.entity.*;
import com.elms.hrsystem.entity.dto.SalDto;
import com.elms.hrsystem.entity.dto.SalGiveDto;
import com.elms.hrsystem.entity.dto.SalInfoDto;
import com.elms.hrsystem.entity.dto.SalItemDto;
import com.elms.hrsystem.mapper.*;
import com.elms.hrsystem.service.SalInfoService;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@Service
public class SalInfoServiceImpl extends ServiceImpl<SalInfoMapper, SalInfo> implements SalInfoService {

    @Autowired
    private SalInfoMapper salInfoMapper;

    @Autowired
    private SalInfoItemMapper salInfoItemMapper;

    @Autowired
    private SalItemMapper salItemMapper;

    @Autowired
    private SysOrgan1Mapper sysOrgan1Mapper;

    @Autowired
    private SysOrgan2Mapper sysOrgan2Mapper;

    @Autowired
    private SysOrgan3Mapper sysOrgan3Mapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private FileProfessionMapper fileProfessionMapper;


    @Override
    public Boolean isExistOrgan(String O3id, String pid, String fname) {
        QueryWrapper<SalInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","0");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        queryWrapper.eq("o3_id",O3id);
        queryWrapper.eq("f_name",fname);
        List<SalInfo> salInfos = salInfoMapper.selectList(queryWrapper);
        if (!(salInfos.size() == 0)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Transactional(rollbackFor =  Exception.class)
    @Override
    public SalInfo insert(SalDto SalDto) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(currentDateTime);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SalInfo salInfo =new SalInfo();
        try {
            Date date = df.parse(format);
            salInfo.setCreateTime(date);

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        salInfo.setSId(String.valueOf(id));
        salInfo.setO3Id(SalDto.getO3Id());
        salInfo.setPId(SalDto.getPid());
        salInfo.setFName(SalDto.getFname());
        salInfo.setBasic(SalDto.getBasic());
        salInfo.setUId(SalDto.getUid());
        salInfo.setIsGive("0");
        salInfo.setIsReject("0");
        salInfo.setIsCheck("0");
        salInfo.setIsDeleted("0");
        Double allmoney= SalDto.getBasic();


        List<SalItem> salItems=new ArrayList<>();
        for (SalItem item:SalDto.getItems()){
            SalItem select = salItemMapper.selectById(item.getId());
            select.setCustomize(item.getCustomize());
            salItems.add(select);
        }


        for (SalItem salItem:salItems){
            SalInfoItem salInfoItem=new SalInfoItem();
            salInfoItem.setId(String.valueOf(idWorker.nextId()));
            salInfoItem.setInfoId(String.valueOf(id));
            salInfoItem.setCreatedBy(SalDto.getUid());
            salInfoItem.setCreatedTime(date);
            salInfoItem.setUpdateBy(SalDto.getUid());
            salInfoItem.setUpdateTime(date);
            salInfoItem.setItemId(salItem.getId());
            salInfoItem.setValue(0.0);

            if (!Objects.equals(salItem.getFormula(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+SalDto.getBasic()*salItem.getFormula());
            }

            if (!Objects.equals(salItem.getSupplement(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+salItem.getSupplement());
            }

            if(Objects.equals(salItem.getFormula(),0.0)&&Objects.equals(salItem.getSupplement(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+salItem.getCustomize());
            }
            allmoney+=salInfoItem.getValue();
            salInfoItemMapper.insert(salInfoItem);
        }

        salInfo.setAllmoney(allmoney);
        this.save(salInfo);
        return salInfo;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<SalInfoDto> list(int pageNum, int pageSize) {
        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper<SalInfo> queryWrapper=new QueryWrapper();
        queryWrapper.eq("is_check","0");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());

            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }


        return results;
    }


    @Transactional( rollbackFor = Exception.class)
    @Override
    public SalInfoDto selectSalInfoBySid(String sid) {

        SalInfoDto salInfoDto = new SalInfoDto();

        SalInfo salInfo = salInfoMapper.selectById(sid);
        salInfoDto.setSid(sid);
        salInfoDto.setBasic(salInfo.getBasic());
        salInfoDto.setFname(salInfo.getFName());
        salInfoDto.setAllmoney(salInfo.getAllmoney());
        salInfoDto.setCreateTime(salInfo.getCreateTime());

        if (salInfo.getIsReject()=="1"){
            salInfoDto.setIsReject("已驳回");
        }else{
            salInfoDto.setIsReject("已申请");
        }

        SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
        salInfoDto.setO3Name(sysOrgan3.getO3Name());

        SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
        salInfoDto.setUsername(sysUser.getUsername());


        FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
        salInfoDto.setPName(profession.getProfession());

        QueryWrapper<SalInfoItem> queryWrapper = new QueryWrapper();
        queryWrapper.eq("info_id",sid);
        List<SalInfoItem> list = salInfoItemMapper.selectList(queryWrapper);

        List<SalItemDto> listItems = new ArrayList<>();
        for (SalInfoItem salInfoItem : list){
            SalItemDto salItemDto=new SalItemDto();
            SalItem salItem = salItemMapper.selectById(salInfoItem.getItemId());
            salItemDto.setId(salItem.getId());
            salItemDto.setName(salItem.getName());
            salItemDto.setValue(salInfoItem.getValue());
            listItems.add(salItemDto);
        }

        salInfoDto.setItems(listItems);

        return salInfoDto;
    }




    @Transactional(rollbackFor = Exception.class)
    @Override
    public void Update(SalDto SalDto) {
        SalInfo info = this.getById(SalDto.getSid());

        QueryWrapper<SalInfoItem> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("info_id",SalDto.getSid());
        salInfoItemMapper.delete(queryWrapper);


        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(currentDateTime);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = df.parse(format);
            info.setCreateTime(date);
            info.setO3Id(SalDto.getO3Id());
            info.setFName(SalDto.getFname());
            info.setPId(SalDto.getPid());
            info.setBasic(SalDto.getBasic());
            info.setUId(SalDto.getUid());
        Double allmoney=new Double(SalDto.getBasic());

        List<SalItem> salItems=new ArrayList<>();
        for (SalItem item:SalDto.getItems()){
            SalItem select = salItemMapper.selectById(item.getId());
            select.setCustomize(item.getCustomize());
            salItems.add(select);
        }

        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (SalItem salItem:salItems){
            SalInfoItem salInfoItem=new SalInfoItem();
            salInfoItem.setId(String.valueOf(idWorker.nextId()));
            salInfoItem.setInfoId(info.getSId());
            salInfoItem.setCreatedBy(SalDto.getUid());
            salInfoItem.setUpdateBy(SalDto.getUid());
            salInfoItem.setUpdateTime(date);
            salInfoItem.setItemId(salItem.getId());
            salInfoItem.setValue(0.0);

            if (!Objects.equals(salItem.getFormula(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+SalDto.getBasic()*salItem.getFormula());
            }

            if (!Objects.equals(salItem.getSupplement(),0.0)){

                salInfoItem.setValue(salInfoItem.getValue()+salItem.getSupplement());


            }
            if(Objects.equals(salItem.getFormula(),0.0)&&Objects.equals(salItem.getSupplement(),0.0)){
                salInfoItem.setValue(salInfoItem.getValue()+salItem.getCustomize());
            }

            //计算总金额
            allmoney+=salInfoItem.getValue();
            salInfoItemMapper.insert(salInfoItem);
        }

        info.setAllmoney(allmoney);
        this.updateById(info);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteInfoBySid(String sid) {
       this.removeById(sid);
       QueryWrapper queryWrapper=new QueryWrapper();
       queryWrapper.eq("info_id", sid);
       salInfoItemMapper.delete(queryWrapper);
    }

    @Override
    public void secondCheck(String sid) {
        SalInfo salInfo = salInfoMapper.selectById(sid);
        salInfo.setIsReject("0");
        this.updateById(salInfo);
    }


    @Override
    public List<SalInfoDto> listRechecked(int pageNum, int pageSize) {
        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","0");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }


        return results;
    }

    @Override
    public void updateCheckInfoBySid(String sid) {
        SalInfo salInfo = salInfoMapper.selectById(sid);
        salInfo.setIsCheck("1");
        this.updateById(salInfo);
    }

    @Override
    public void updateCheckFalseInfoBySid(String sid) {
        SalInfo salInfo = salInfoMapper.selectById(sid);
        salInfo.setIsReject("1");
        this.updateById(salInfo);
    }



    @Override
    public List<SalInfoDto> listSelect(int pageNum, int pageSize) {
        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }


        return results;
    }

    @Override
    public List<SalInfoDto> listBy03Name(int pageNum, int pageSize, String o3name) {
        QueryWrapper<SysOrgan3> query = new QueryWrapper();
        query.like("o3_name", o3name);
        List<SysOrgan3> organ3s = sysOrgan3Mapper.selectList(query);

        if (organ3s.size()==0||Objects.isNull(organ3s)){
            return null;
        }

        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        List<SalInfo> records =new ArrayList<>();
        for(SysOrgan3 org : organ3s){
            queryWrapper.eq("o3_id",org.getO3Id());
            Page page=new Page<>(pageNum,pageSize);
            IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
            List<SalInfo> pageRecords = salInfoPage.getRecords();
            for (SalInfo record: pageRecords){
                records.add(record);
            }
        }


        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }


        return results;
    }


    @Override
    public List<SalInfoDto> listByUser(int pageNum, int pageSize, String username) {
        QueryWrapper<SysUser> query = new QueryWrapper();
        query.like("username", username);
        List<SysUser> sysUsers = sysUserMapper.selectList(query);

        if(Objects.isNull(sysUsers)||sysUsers.size()==0){
            return null;
        }

        List<SalInfoDto> results = new ArrayList<SalInfoDto>();
        List<SalInfo> records=new ArrayList<>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");

        for(SysUser sysUser:sysUsers){
            queryWrapper.eq("u_id",sysUser.getUId());
            Page page=new Page<>(pageNum,pageSize);

            IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
            List<SalInfo> records1 = salInfoPage.getRecords();
            for (SalInfo record:records1){
                records.add(record);
            }
        }


        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }


        return results;
    }


    @Override
    public List<SalInfoDto> listByMoney(int pageNum, int pageSize, int lowMoney, int highMoney) {
        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        queryWrapper.between("allmoney",lowMoney,highMoney);
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        if (Objects.isNull(records)|| records.size()==0){
            return null;
        }

        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }
        return results;
    }



    @Override
    public List<SalGiveDto> showGiveSalInfo(int pageNum, int pageSize) {


        Map<String,SalGiveDto> SalInfoMap = new HashMap<>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("is_deleted","0");
        queryWrapper.eq("is_give","0");
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        for (SalInfo salInfo : records) {
            SalGiveDto salGiveDto = new SalGiveDto();
            
            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            
            if (SalInfoMap.containsKey(sysOrgan3.getO3Name()+profession.getProfession())){
                SalGiveDto salGiveDto1 = SalInfoMap.get(sysOrgan3.getO3Name() + profession.getProfession());
                salGiveDto1.setTotal(salGiveDto1.getTotal()*2);
                salGiveDto1.setCount(salGiveDto1.getCount()+1);
                SalInfoMap.put(sysOrgan3.getO3Name() + profession.getProfession(),salGiveDto1);

            }else{
                salGiveDto.setSid(salInfo.getSId());
                salGiveDto.setBasic(salInfo.getBasic());
                salGiveDto.setPid(salInfo.getPId());
                salGiveDto.setO3id(salInfo.getO3Id());
                salGiveDto.setTotal(salInfo.getBasic());
                
                salGiveDto.setO3Name(sysOrgan3.getO3Name());
                salGiveDto.setPName(profession.getProfession());

                SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
                salGiveDto.setUsername(sysUser.getUsername());

                SysOrgan3 organ3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
                SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
                SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
                salGiveDto.setO1Name(organ1.getO1Name());
                salGiveDto.setO2Name(organ2.getO2Name());
                salGiveDto.setO3Name(organ3.getO3Name());
                
                salGiveDto.setCount(1);

                SalInfoMap.put(sysOrgan3.getO3Name()+profession.getProfession(),salGiveDto);

            }
        }

        Collection<SalGiveDto> SalGiveDtoLists = SalInfoMap.values();

        List<SalGiveDto> results=new ArrayList<>(SalGiveDtoLists);

        return results;
    }


    @Override
    public List<SalInfoDto> showGiveSalInfoByO3Id(int pageNum, int pageSize, String o3id) {
        List<SalInfoDto> results = new ArrayList<SalInfoDto>();

        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("o3_id",o3id);
        Page page=new Page<>(pageNum,pageSize);

        IPage<SalInfo> salInfoPage = salInfoMapper.selectPage(page, queryWrapper);
        List<SalInfo> records = salInfoPage.getRecords();

        for (SalInfo salInfo : records) {
            SalInfoDto salInfoDto=new SalInfoDto();
            salInfoDto.setAllmoney(salInfo.getAllmoney());
            salInfoDto.setCreateTime(salInfo.getCreateTime());
            salInfoDto.setFname(salInfo.getFName());
            salInfoDto.setSid(salInfo.getSId());
            salInfoDto.setBasic(salInfo.getBasic());

            SysOrgan3 sysOrgan3 = sysOrgan3Mapper.selectById(salInfo.getO3Id());
            salInfoDto.setO3Name(sysOrgan3.getO3Name());

            SysUser sysUser = sysUserMapper.selectById(salInfo.getUId());
            salInfoDto.setUsername(sysUser.getUsername());


            FileProfession profession = fileProfessionMapper.selectById(salInfo.getPId());
            salInfoDto.setPName(profession.getProfession());

            if (salInfo.getIsReject()=="1"){
                salInfoDto.setIsReject("已驳回");
            }else{
                salInfoDto.setIsReject("已申请");
            }
            results.add(salInfoDto);
        }
        return results;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSalGiveInfoByO3Id(String o3id, String pid) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("o3_id", o3id);
        queryWrapper.eq("p_id", pid);
        List<SalInfo> selectList = salInfoMapper.selectList(queryWrapper);
        for(SalInfo salInfo : selectList) {
            salInfo.setIsGive("1");
        }
        this.updateBatchById(selectList);
    }

    @Override
    public List<Map<String, String>> giveSalAllInfoByTime(int page, int limit) {
        return salInfoMapper.giveSalAllInfo(page, limit);
    }

    @Override
    public List<Map<String, String>> giveSalAllInfoByTime(int pageNum, int pageSize, String startTime, String endTime) {
        return salInfoMapper.getAllByIsGiveByTime(pageNum, pageSize, startTime, endTime);
    }
}