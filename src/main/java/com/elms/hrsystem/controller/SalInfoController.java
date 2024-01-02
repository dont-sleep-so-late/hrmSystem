package com.elms.hrsystem.controller;



import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.SalInfo;
import com.elms.hrsystem.entity.dto.FileDto;
import com.elms.hrsystem.entity.dto.SalDto;
import com.elms.hrsystem.entity.dto.SalGiveDto;
import com.elms.hrsystem.entity.dto.SalInfoDto;
import com.elms.hrsystem.service.FileInfoService;
import com.elms.hrsystem.service.SalInfoService;
import com.elms.hrsystem.service.SysOrgan3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@RestController
@RequestMapping("/Sal")
public class SalInfoController {

    ResultVo<Object> resultVo = new ResultVo<>();
    
    @Autowired
    private SalInfoService salInfoService;


    @Autowired
    private SysOrgan3Service sysOrgan3Service;

    @Autowired
    private FileInfoService fileInfoService;


    @PostMapping("/getFile")
    public ResultVo<Object> getFile(@RequestParam("organ3id") String organ3id, @RequestParam("pid") String pid){
        try{
            List<FileDto> fileDtos = fileInfoService.getFilesByPidAndOrganId(pid, organ3id);
            resultVo.setData(fileDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/add")
    public ResultVo<Object> add(@RequestBody SalDto salDto){

        if(salInfoService.isExistOrgan(salDto.getO3Id(),salDto.getPid(), salDto.getFname())){
            resultVo.setData(null);
            resultVo.setMess("该组织机构下已存在工资信息");
            resultVo.setOk(true);
            return resultVo;
        }

        try{
            SalInfo salInfo = salInfoService.insert(salDto);
            resultVo.setData(salInfo);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/add/queryXinZiBiaoZhun")
    public ResultVo<Object> queryXinZiBiaoZhun() {
        try{

            List<Map<String, String>> maps = sysOrgan3Service.queryXinZiBiaoZhun();

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);

        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/list")
    public ResultVo<Object> list(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try{
            List<SalInfoDto> list = salInfoService.list(pageNum, pageSize);

            resultVo.setData(list);
            resultVo.setMess("true");
            resultVo.setOk(true);

        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/showSalInfoBySid/{sid}")
    public ResultVo<Object> showSalInfoBySid(@PathVariable("sid") String sid) {
        try{
            SalInfoDto salInfoDto = salInfoService.selectSalInfoBySid(sid);

            resultVo.setData(salInfoDto);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }




    @PostMapping("/update")
    public ResultVo<Object> update(@RequestBody SalDto salDto){
        try{
            salInfoService.Update(salDto);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/add/deleteInfoBySid/{sid}")
    public ResultVo<Object> deleteInfoBySid(@PathVariable("sid") String sid) {
        try{
            salInfoService.deleteInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/secondCheck/{sid}")
    public ResultVo<Object> secondCheck(@PathVariable("sid") String sid) {
        try{
            salInfoService.secondCheck(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }



    @PostMapping("/showRechecked")
    public ResultVo<Object> listRechecked(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.listRechecked(pageNum, pageSize);
            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }



    @PostMapping("/check/updateCheckInfoBySid/{sid}")
    public ResultVo<Object> updateCheckInfoBySid(@PathVariable("sid") String sid) {
        try{
            salInfoService.updateCheckInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/check/updateCheckFalseInfoBySid/{sid}")
    public ResultVo<Object> updateCheckFalseInfoBySid(@PathVariable("sid") String sid) {
        try{
            salInfoService.updateCheckFalseInfoBySid(sid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }





    @PostMapping("/listSelect")
    public ResultVo<Object> listSelect(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.listSelect(pageNum, pageSize);
            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/listBy03Name")
    public ResultVo<Object> listBy03Name(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize, @RequestParam("o3name") String o3name){
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.listBy03Name(pageNum, pageSize,o3name);
            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/listByUser")
    public ResultVo<Object> listByUser(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize,@RequestParam("username") String username){
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.listByUser(pageNum, pageSize,username);
            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }





    @PostMapping("/listByMoney")
    public ResultVo<Object> listByMoney(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize, @RequestParam("lowMoney") int lowMoney, @RequestParam("highMoney") int highMoney){
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.listByMoney(pageNum, pageSize,lowMoney, highMoney);
            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/showGiveSalInfo")
    public ResultVo<Object> showGiveSalInfo(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize) {
        try{
            List<SalGiveDto> giveDtos = salInfoService.showGiveSalInfo(pageNum, pageSize);

            resultVo.setData(giveDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }




    @PostMapping("/give/updateSalGiveInfoByo3id/{o3id}/{pid}")
    public ResultVo<Object> updateSalGiveInfoByO3Id(@PathVariable("o3id") String o3id,@PathVariable("pid") String pid) {
        try{
            salInfoService.updateSalGiveInfoByO3Id(o3id,pid );
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/ShowGiveSalInfoByO3Id")
    public ResultVo<Object> ShowGiveSalInfoByO3Id(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize, @RequestParam("o3id") String o3id) {
        try{
            List<SalInfoDto> salInfoDtos = salInfoService.showGiveSalInfoByO3Id(pageNum, pageSize, o3id);

            resultVo.setData(salInfoDtos);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }





    @PostMapping("/give/giveSalAllInfo/{page}/{limit}")
    public ResultVo<Object> giveSalAllInfo(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            List<Map<String, String>> maps = salInfoService.giveSalAllInfoByTime(page, limit);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/GiveAllInfoByTime")
    public ResultVo<Object> giveSalAllInfoByTime(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("startTime") String startTime, @RequestParam("endTime")String endTime) {
        try{
            List<Map<String, String>> maps = salInfoService.giveSalAllInfoByTime(pageNum, pageSize,startTime, endTime);

            resultVo.setData(maps);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(null);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

}