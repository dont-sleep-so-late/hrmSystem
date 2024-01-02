package com.elms.hrsystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.elms.hrsystem.entity.SalInfo;
import com.elms.hrsystem.entity.dto.SalDto;
import com.elms.hrsystem.entity.dto.SalGiveDto;
import com.elms.hrsystem.entity.dto.SalInfoDto;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
public interface SalInfoService extends IService<SalInfo> {

    /**
     * 是否存在该项目职称
     *
     * @param O3id
     * @param pid
     * @param fname
     * @return
     */
    public Boolean isExistOrgan(String O3id,String pid,String fname);

    /**
     * 薪酬登记new
     * @param SalDto
     * @return
     */
    public SalInfo insert(SalDto SalDto);


    /**
     * 返回薪资申请表
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<SalInfoDto> list(int pageNum, int pageSize);



    /**
     * 展示信息
     * @param sid
     * @return
     */
    public SalInfoDto selectSalInfoBySid(String sid);



    /**
     * 修改薪酬表
     * @param SalDto
     */
    public void Update(SalDto SalDto);


    /**
     * 删除内容
     * @param sid
     */
    public void deleteInfoBySid(String sid);


    /**
     * 重新提交
     * @param sid
     */
    public void  secondCheck(String sid);



    /**
     * 返回薪资复核的所有内容
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<SalInfoDto> listRechecked(int pageNum, int pageSize);


    /**
     * 复核通过
     * @param sid
     */
    public void updateCheckInfoBySid(String sid);



    /**
     * 复核失败(驳回)
     * @param sid
     */
    public void updateCheckFalseInfoBySid(String sid);


    /**
     * 薪酬查询:查询“档案查询”的所有信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public  List<SalInfoDto> listSelect(int pageNum, int pageSize);





    /**
     * 通过"薪资标准名称"查询
     *
     * @param pageNum
     * @param pageSize
     * @param o3name
     * @return
     */
    public  List<SalInfoDto> listBy03Name(int pageNum, int pageSize,String o3name);



    /**
     * 通过"登记人"查询
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    public  List<SalInfoDto> listByUser(int pageNum, int pageSize,String username);


    /**
     * 通过"薪资总额"查询
     * @param pageNum
     * @param pageSize
     * @param lowMoney
     * @param highMoney
     * @return
     */
    public  List<SalInfoDto> listByMoney(int pageNum, int pageSize, int lowMoney, int highMoney);



    /**
     * 薪酬发放:薪酬发放登记页面
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<SalGiveDto> showGiveSalInfo(int pageNum, int pageSize);


    /**
     * 薪酬发放登记细则:展示所有数据
     * @param pageNum
     * @param pageSize
     * @param o3id
     * @return
     */
    public List<SalInfoDto> showGiveSalInfoByO3Id(int pageNum, int pageSize,String o3id);

    /**
     * 薪酬发放登记细则:提交所有数据
     *
     * @param o3id
     * @param pid
     */
    public void updateSalGiveInfoByO3Id(String o3id,String pid);




    /**
     * 薪酬发放登记细则:发放成功的所有数据
     * @param page
     * @param limit
     * @return
     */
    public List<Map<String,String>> giveSalAllInfoByTime(int page, int limit);

    /**
     *薪酬发放登记细则:发放成功的所有数据ByTime
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Map<String,String>> giveSalAllInfoByTime(int pageNum, int pageSize, String startTime, String endTime);



}