package com.elms.hrsystem.controller;



import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.SysOrgan3;
import com.elms.hrsystem.service.SysOrgan3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Arrays;




/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@RestController
@RequestMapping("/Sys")

public class SysOrgan3Controller {


    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private SysOrgan3Service sysOrgan3Service;


    @PostMapping("/organ/queryOrgan3/{o2id}")
    public ResultVo<Object> queryOrgan3(@PathVariable("o2id") String o2id) {
        try{
            resultVo.setData(sysOrgan3Service.queryOrgan3(o2id));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/organ/insertOrgan3")
    public ResultVo<Object> insertOrgan3(SysOrgan3 sysOrgan3) {
        try{
            sysOrgan3Service.insertOrgan3(sysOrgan3);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/organ/deleteOrgan3/{o3id}")
    public ResultVo<Object> deleteOrgan3(@PathVariable("o3id") String o3id) {
        try{
            sysOrgan3Service.deleteOrgan3(o3id);
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


}