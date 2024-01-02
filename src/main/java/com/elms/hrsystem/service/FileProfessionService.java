package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.FileProfession;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface FileProfessionService extends IService<FileProfession> {




    /**
     * 查询特定信息
     * @return
     */
    public List<Map<String,String>> queryProfession();

    /**
     * 展示所有职位
     * @param page
     * @param limit
     * @return
     */
    public  List<Map<String,String>> queryAllProfession(int page,int limit);

    /**
     * 通过职称名查询职称信息
     * @param profession
     * @param page
     * @param limit
     * @return
     */
    public  List<Map<String,String>> queryProfessionByProfession(String profession,int page,int limit);

    /**
     * 新增职位
     * @param fileProfession
     */
    public void insertProfession(FileProfession fileProfession);

    /**
     * 修改职称姓名
     * @param profession
     * @param pId
     */
    public void updateProfessionByPid(String profession,String pId);

    /**
     * 删除职位
     * @param profession
     * @param pId
     */
    public  void deleteProfessionByPid(String profession,String pId);
}