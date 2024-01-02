package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.FileInfo;
import com.elms.hrsystem.entity.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface FileInfoService extends IService<FileInfo> {


    /**
     * 根据pid和organ3id
     * @param pid
     * @param organ3id
     * @return
     */
    public List<FileDto> getFilesByPidAndOrganId(String pid, String organ3id);

    /**
     * 插入档案数据
     *
     * @param fileDto
     */
    public void insertInfo(FileDto fileDto);


    /**
     * 上传头像图片
     * @param file
     * @return
     */
    public String uploadFileAvatar(MultipartFile file);


    /**
     * 展示档案申请所有的信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<FileDto> listFileApplication(int pageNum, int pageSize);





    /**
     *  展示档案申请的详细信息
     * @param fid
     * @return
     */
    public FileDto selectFileInfoByFid(String fid);



    /**
     *  修改内容
     * @param fileInfo
     */
    public void updateFile(FileInfo fileInfo);


    /**
     * 删除内容
     * @param fid
     */
    public void deleteInfoByFid(String fid);


    /**
     * 档案复核:展示所有信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<FileDto> listFileRechecked(int pageNum, int pageSize);


    /**
     * 复核通过
     * @param fid
     */
    public void updateCheckInfoByFid(String fid);


    /**
     * 复核失败(驳回)
     * @param fid
     */
    public void updateCheckFalseInfoByFid(String fid);




    /**
     * 查询“档案查询”的所有信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<FileDto> listFileSelect(int pageNum, int pageSize);


    /**
     * 通过机构查询
     * @param pageNum
     * @param pageSize
     * @param organ1Name
     * @param organ2Name
     * @param organ3Name
     * @return
     */
    public List<FileDto> listFileSelectByOrgan(int pageNum, int pageSize,String organ1Name,String organ2Name,String organ3Name);





    /**
     * 通过职位查询
     * @param pageNum
     * @param pageSize
     * @param Profession
     * @return
     */
    public List<FileDto> listFileSelectByProfession(int pageNum, int pageSize,String Profession);

}