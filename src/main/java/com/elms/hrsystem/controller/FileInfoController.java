package com.elms.hrsystem.controller;


import com.elms.hrsystem.entity.FileInfo;
import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.dto.FileDto;
import com.elms.hrsystem.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@RestController
@RequestMapping("/File")
public class FileInfoController {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/add")
    public ResultVo<Object> add(@RequestBody FileDto fileInfo) {
        try{
            fileInfoService.insertInfo(fileInfo);
            resultVo.setData("");
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

    @PostMapping("/add/picture")
    public ResultVo<Object> upPicture(MultipartFile file) {
        try{
            resultVo.setData(fileInfoService.uploadFileAvatar(file));
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


    @PostMapping("/listFileApplication")
    public ResultVo<Object> listFileApplication(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum) {
        try{
            List<FileDto> files = fileInfoService.listFileApplication(pageNum, pageSize);

            resultVo.setData(files);
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



    @PostMapping("/selectFileInfoByFid")
    public ResultVo<Object> selectFileInfoByFid(@RequestParam("fid") String fid) {
        try{
            FileDto fileDto = fileInfoService.selectFileInfoByFid(fid);

            resultVo.setData(fileDto);
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


    @PostMapping("/updateInfoByFid")
    public ResultVo<Object> updateInfoByFid(@RequestBody FileInfo fileInfo) {
        try{
            fileInfoService.updateFile(fileInfo);
            resultVo.setData("");
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


    @PostMapping("/add/deleteInfoByFid/{fid}")
    public ResultVo<Object> deleteInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileInfoService.deleteInfoByFid(fid);
            resultVo.setData("");
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





    @PostMapping("/listFileRechecked")
    public ResultVo<Object> showFileRechecked(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum) {
        try{
            List<FileDto> fileRechecked = fileInfoService.listFileRechecked(pageNum, pageSize);

            resultVo.setData(fileRechecked);
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


    @PostMapping("/check/updateCheckInfoByFid/{fid}")
    public ResultVo<Object> updateCheckInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileInfoService.updateCheckInfoByFid(fid);

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


    @PostMapping("/check/updateCheckFalseInfoByFid/{fid}")
    public ResultVo<Object> updateCheckFalseInfoByFid(@PathVariable("fid") String fid) {
        try{
            fileInfoService.updateCheckFalseInfoByFid(fid);
            resultVo.setData("");
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


    @PostMapping("/listFileSelect")
    public ResultVo<Object> listFileSelect(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum) {
        try{
            List<FileDto> fileSelect = fileInfoService.listFileSelect(pageNum, pageSize);

            resultVo.setData(fileSelect);
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



    @PostMapping("/listFileSelectByOrgan")
    public ResultVo<Object> listFileSelectByOrgan(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum,@RequestParam("organ1Name")String organ1Name,@RequestParam("organ2Name")String organ2Name,@RequestParam("organ3Name")String organ3Name) {
        try{
            List<FileDto> fileDtos = fileInfoService.listFileSelectByOrgan(pageNum, pageSize, organ1Name,organ2Name,organ3Name);

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


    @PostMapping("/listFileSelectByProfession")
    public ResultVo<Object> listFileSelectByProfession(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum,@RequestParam("Profession")String Profession){
        try{
            List<FileDto> profession = fileInfoService.listFileSelectByProfession(pageNum, pageSize, Profession);

            resultVo.setData(profession);
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