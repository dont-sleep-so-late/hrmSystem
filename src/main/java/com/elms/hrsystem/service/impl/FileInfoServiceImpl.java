package com.elms.hrsystem.service.impl;




import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elms.hrsystem.entity.*;
import com.elms.hrsystem.entity.dto.FileDto;
import com.elms.hrsystem.mapper.*;
import com.elms.hrsystem.service.FileInfoService;
import com.elms.hrsystem.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private FileReligionMapper fileReligionMapper;

    @Autowired
    private FileProfessionMapper fileProfessionMapper;

    @Autowired
    private FileNationMapper filenationMapper;

    @Autowired
    private FileAcademicMapper fileAcademicMapper;

    @Autowired
    private SysOrgan1Mapper sysOrgan1Mapper;

    @Autowired
    private SysOrgan2Mapper sysOrgan2Mapper;

    @Autowired
    private SysOrgan3Mapper sysOrgan3Mapper;

    @Override
    public List<FileDto> getFilesByPidAndOrganId(String pid, String organ3id) {
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<FileInfo>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        queryWrapper.eq("profession_id",pid);
        queryWrapper.eq("organizion_id",organ3id);
        List<FileInfo> fileInfos = fileInfoMapper.selectList(queryWrapper);

        if(fileInfos.size()==0){
            return null;
        }

        List<FileDto> files = new ArrayList();

        for (FileInfo fileInfo:fileInfos){

            FileDto filedto = new FileDto();
            filedto.setFid(fileInfo.getFId());
            filedto.setName(fileInfo.getName());
            files.add(filedto);
        }

        return files;
    }

    @Override
    public void insertInfo(FileDto fileDto) {
        //生成订单时间orderData
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dateTimeFormatter.format(currentDateTime);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FileInfo fileInfo = new FileInfo();
        try {
            fileInfo.setCreateTime(df.parse(format));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        fileInfo.setUId(fileDto.getUid());
        fileInfo.setName(fileDto.getName());
        if(Objects.equals(fileDto.getSex(), "0")){
            fileInfo.setSex("男");
        }else{
            fileInfo.setSex("女");
        }

        fileInfo.setAge(fileDto.getAge());
        fileInfo.setImage(fileDto.getImage());
        fileInfo.setProfessionId(fileDto.getProfessionId());
        fileInfo.setOrganizionId(fileDto.getOrganizionId());
        fileInfo.setNationId(fileDto.getNationId());
        fileInfo.setAcademicId(fileDto.getAcademicId());
        fileInfo.setReligionId(fileDto.getReligionId());
        fileInfo.setEmail(fileDto.getEmail());
        fileInfo.setPhone(fileDto.getPhone());
        fileInfo.setQq(fileDto.getQq());
        fileInfo.setAddress(fileDto.getAddress());
        fileInfo.setPersonalHistory(fileDto.getPersonalHistory());
        fileInfo.setFamilyRelationship(fileDto.getFamilyRelationship());
        fileInfo.setRemarks(fileDto.getRemarks());



        //生成id
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        long id = idWorker.nextId();
        fileInfo.setFId(String.valueOf(id));
        fileInfo.setIsReject("0");
        fileInfo.setIsCheck("0");
        fileInfo.setIsDeleted("0");


        this.save(fileInfo);
    }

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取上传的文件的原名
        int i = file.getOriginalFilename().lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(i);

        String uuid = UUID.randomUUID().toString();
        String filename = "secondhandshop" + "/" + uuid + suffix;

        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tKkYanVmbvgznR8UfVn";
        String accessKeySecret ="DpIfZaJZPzbXFCH8x3WT5v8UBy1fM0";
        String bucketName = "elmsforpicture";
        String objectName = filename;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        try {
            //file.getInputStream() 当前文件的输入流
            ossClient.putObject(bucketName, objectName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        String url = "https://" + bucketName + "." + endpoint + "/" + filename;

        return url;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileDto> listFileApplication(int pageNum, int pageSize) {
        List<FileDto> files = new ArrayList<FileDto>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        Page page=new Page<>(pageNum,pageSize);
        IPage<FileInfo> page1 = fileInfoMapper.selectPage(page, queryWrapper);
        List<FileInfo> records = page1.getRecords();

        for (FileInfo fileInfo : records) {
            FileDto fileDto = new FileDto();

            //FileInfo的set()
            fileDto.setFid(fileInfo.getFId());
            fileDto.setUid(fileInfo.getUId());
            fileDto.setName(fileInfo.getName());
            fileDto.setSex(fileInfo.getSex());
            fileDto.setBirth(fileInfo.getBirth());
            fileDto.setImage(fileInfo.getImage());
            fileDto.setEmail(fileInfo.getEmail());
            fileDto.setPhone(fileInfo.getPhone());
            fileDto.setQq(fileInfo.getQq());
            fileDto.setAddress(fileInfo.getAddress());
            fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
            fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
            fileDto.setRemarks(fileInfo.getRemarks());
            fileDto.setCreateTime(fileInfo.getCreateTime());

            //Organ的set()
            SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
            SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
            SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
            fileDto.setO1Name(organ1.getO1Name());
            fileDto.setO2Name(organ2.getO2Name());
            fileDto.setO3Name(organ3.getO3Name());

            //四个关联表
            FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
            FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
            FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
            FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
            fileDto.setAcademic(academic.getAcademic());
            fileDto.setNation(nation.getNation());
            fileDto.setProfession(profession.getProfession());
            fileDto.setReligion(religion.getReligion());
            files.add(fileDto);
        }


        return files;
    }


    @Override
    public FileDto selectFileInfoByFid(String fid) {
        FileDto fileDto=new FileDto();
        FileInfo fileInfo = fileInfoMapper.selectById(fid);

        //FileInfo的set()
        fileDto.setFid(fileInfo.getFId());
        fileDto.setUid(fileInfo.getUId());
        fileDto.setName(fileInfo.getName());
        fileDto.setAge(fileInfo.getAge());
        fileDto.setSex(fileInfo.getSex());
        fileDto.setBirth(fileInfo.getBirth());
        fileDto.setImage(fileInfo.getImage());
        fileDto.setEmail(fileInfo.getEmail());
        fileDto.setPhone(fileInfo.getPhone());
        fileDto.setQq(fileInfo.getQq());
        fileDto.setAddress(fileInfo.getAddress());
        fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
        fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
        fileDto.setRemarks(fileInfo.getRemarks());
        fileDto.setCreateTime(fileInfo.getCreateTime());
        fileDto.setProfessionId(fileInfo.getProfessionId());
        fileDto.setOrganizionId(fileInfo.getOrganizionId());
        fileDto.setReligionId(fileInfo.getReligionId());
        fileDto.setNationId(fileInfo.getNationId());
        fileDto.setAcademicId(fileInfo.getAcademicId());
        fileDto.setReligionId(fileInfo.getReligionId());
        fileDto.setIsCheck(fileInfo.getIsCheck());
        fileDto.setIsDeleted(fileInfo.getIsDeleted());
        fileDto.setIsReject(fileInfo.getIsReject());

        //Organ的set()
        SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
        SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
        SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
        fileDto.setO1Name(organ1.getO1Name());
        fileDto.setO2Name(organ2.getO2Name());
        fileDto.setO3Name(organ3.getO3Name());

        //四个关联表
        FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
        FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
        FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
        FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
        fileDto.setAcademic(academic.getAcademic());
        fileDto.setNation(nation.getNation());
        fileDto.setProfession(profession.getProfession());
        fileDto.setReligion(religion.getReligion());

        return fileDto;
    }


    @Override
    public void updateFile(FileInfo fileInfo) {
       this.updateById(fileInfo);
    }

    @Override
    public void deleteInfoByFid(String fid) {
        this.removeById(fid);
    }


    @Override
    public List<FileDto> listFileRechecked(int pageNum, int pageSize) {
        List<FileDto> files = new ArrayList<FileDto>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","0");
        Page page=new Page<>(pageNum,pageSize);
        IPage<FileInfo> page1 = fileInfoMapper.selectPage(page, queryWrapper);
        List<FileInfo> records = page1.getRecords();

        for (FileInfo fileInfo : records) {
            FileDto fileDto = new FileDto();

            //FileInfo的set()
            fileDto.setFid(fileInfo.getFId());
            fileDto.setUid(fileInfo.getUId());
            fileDto.setName(fileInfo.getName());
            fileDto.setSex(fileInfo.getSex());
            fileDto.setBirth(fileInfo.getBirth());
            fileDto.setImage(fileInfo.getImage());
            fileDto.setEmail(fileInfo.getEmail());
            fileDto.setPhone(fileInfo.getPhone());
            fileDto.setQq(fileInfo.getQq());
            fileDto.setAddress(fileInfo.getAddress());
            fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
            fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
            fileDto.setRemarks(fileInfo.getRemarks());
            fileDto.setCreateTime(fileInfo.getCreateTime());

            //Organ的set()
            SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
            SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
            SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
            fileDto.setO1Name(organ1.getO1Name());
            fileDto.setO2Name(organ2.getO2Name());
            fileDto.setO3Name(organ3.getO3Name());

            //四个关联表
            FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
            FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
            FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
            FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
            fileDto.setAcademic(academic.getAcademic());
            fileDto.setNation(nation.getNation());
            fileDto.setProfession(profession.getProfession());
            fileDto.setReligion(religion.getReligion());
            files.add(fileDto);
        }


        return files;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCheckInfoByFid(String fid) {
        FileInfo fileInfo = fileInfoMapper.selectById(fid);
        fileInfo.setIsCheck("1");
        this.updateById(fileInfo);
    }

    @Override
    public void updateCheckFalseInfoByFid(String fid) {
        FileInfo fileInfo = fileInfoMapper.selectById(fid);
        fileInfo.setIsReject("1");
        this.updateById(fileInfo);
    }


    @Override
    public List<FileDto> listFileSelect(int pageNum, int pageSize) {
        List<FileDto> files = new ArrayList<FileDto>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        Page page=new Page<>(pageNum,pageSize);
        IPage<FileInfo> page1 = fileInfoMapper.selectPage(page, queryWrapper);
        List<FileInfo> records = page1.getRecords();

        for (FileInfo fileInfo : records) {
            FileDto fileDto = new FileDto();

            //FileInfo的set()
            fileDto.setFid(fileInfo.getFId());
            fileDto.setUid(fileInfo.getUId());
            fileDto.setName(fileInfo.getName());
            fileDto.setSex(fileInfo.getSex());
            fileDto.setBirth(fileInfo.getBirth());
            fileDto.setImage(fileInfo.getImage());
            fileDto.setEmail(fileInfo.getEmail());
            fileDto.setPhone(fileInfo.getPhone());
            fileDto.setQq(fileInfo.getQq());
            fileDto.setAddress(fileInfo.getAddress());
            fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
            fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
            fileDto.setRemarks(fileInfo.getRemarks());
            fileDto.setCreateTime(fileInfo.getCreateTime());

            //Organ的set()
            SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
            SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
            SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
            fileDto.setO1Name(organ1.getO1Name());
            fileDto.setO2Name(organ2.getO2Name());
            fileDto.setO3Name(organ3.getO3Name());

            //四个关联表
            FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
            FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
            FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
            FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
            fileDto.setAcademic(academic.getAcademic());
            fileDto.setNation(nation.getNation());
            fileDto.setProfession(profession.getProfession());
            fileDto.setReligion(religion.getReligion());
            files.add(fileDto);
        }


        return files;
    }

    @Override
    public List<FileDto> listFileSelectByOrgan(int pageNum, int pageSize, String organ1Name, String organ2Name, String organ3Name) {
        List<FileDto> files = new ArrayList<>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        Page page=new Page<>(pageNum,pageSize);
        IPage<FileInfo> page1 = fileInfoMapper.selectPage(page, queryWrapper);
        List<FileInfo> records = page1.getRecords();

        for (FileInfo fileInfo : records) {
            //Organ的set()
            SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
            SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
            SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());

            if(Objects.equals(organ1Name,organ1.getO1Id())&&Objects.equals(organ2Name,organ2.getO2Id())&&Objects.equals(organ3Name,organ3.getO3Id())){
                FileDto fileDto = new FileDto();

                //FileInfo的set()
                fileDto.setFid(fileInfo.getFId());
                fileDto.setUid(fileInfo.getUId());
                fileDto.setName(fileInfo.getName());
                fileDto.setSex(fileInfo.getSex());
                fileDto.setBirth(fileInfo.getBirth());
                fileDto.setImage(fileInfo.getImage());
                fileDto.setEmail(fileInfo.getEmail());
                fileDto.setPhone(fileInfo.getPhone());
                fileDto.setQq(fileInfo.getQq());
                fileDto.setAddress(fileInfo.getAddress());
                fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
                fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
                fileDto.setRemarks(fileInfo.getRemarks());
                fileDto.setCreateTime(fileInfo.getCreateTime());

                fileDto.setO1Name(organ1.getO1Name());
                fileDto.setO2Name(organ2.getO2Name());
                fileDto.setO3Name(organ3.getO3Name());

                //四个关联表
                FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
                FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
                FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
                FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
                fileDto.setAcademic(academic.getAcademic());
                fileDto.setNation(nation.getNation());
                fileDto.setProfession(profession.getProfession());
                fileDto.setReligion(religion.getReligion());
                files.add(fileDto);
                continue;
            }

        }
        return files;
    }


    @Override
    public List<FileDto> listFileSelectByProfession(int pageNum, int pageSize, String Profession) {
        List<FileDto> files = new ArrayList<FileDto>();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", "0");
        queryWrapper.eq("is_reject","0");
        queryWrapper.eq("is_check","1");
        Page page=new Page<>(pageNum,pageSize);
        IPage<FileInfo> page1 = fileInfoMapper.selectPage(page, queryWrapper);
        List<FileInfo> records = page1.getRecords();

        for (FileInfo fileInfo : records) {
            FileProfession profession = fileProfessionMapper.selectById(fileInfo.getProfessionId());
            if (!Objects.equals(profession.getPId(),Profession)){
                continue;
            }
            FileDto fileDto = new FileDto();

            //FileInfo的set()
            fileDto.setFid(fileInfo.getFId());
            fileDto.setUid(fileInfo.getUId());
            fileDto.setName(fileInfo.getName());
            fileDto.setSex(fileInfo.getSex());
            fileDto.setBirth(fileInfo.getBirth());
            fileDto.setImage(fileInfo.getImage());
            fileDto.setEmail(fileInfo.getEmail());
            fileDto.setPhone(fileInfo.getPhone());
            fileDto.setQq(fileInfo.getQq());
            fileDto.setAddress(fileInfo.getAddress());
            fileDto.setPersonalHistory(fileInfo.getPersonalHistory());
            fileDto.setFamilyRelationship(fileInfo.getFamilyRelationship());
            fileDto.setRemarks(fileInfo.getRemarks());
            fileDto.setCreateTime(fileInfo.getCreateTime());


            //Organ的set()
            SysOrgan3 organ3 = sysOrgan3Mapper.selectById(fileInfo.getOrganizionId());
            SysOrgan2 organ2 = sysOrgan2Mapper.selectById(organ3.getO2Id());
            SysOrgan1 organ1 = sysOrgan1Mapper.selectById(organ2.getO1Id());
            fileDto.setO1Name(organ1.getO1Name());
            fileDto.setO2Name(organ2.getO2Name());
            fileDto.setO3Name(organ3.getO3Name());


            //四个关联表
            FileAcademic academic = fileAcademicMapper.selectById(fileInfo.getAcademicId());
            FileNation nation = filenationMapper.selectById(fileInfo.getNationId());
            FileReligion religion = fileReligionMapper.selectById(fileInfo.getReligionId());
            fileDto.setAcademic(academic.getAcademic());
            fileDto.setNation(nation.getNation());
            fileDto.setProfession(profession.getProfession());
            fileDto.setReligion(religion.getReligion());
            files.add(fileDto);
        }
        return files;
    }




}