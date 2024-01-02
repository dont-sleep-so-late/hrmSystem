package com.elms.hrsystem.controller;


import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.SysOrgan1;
import com.elms.hrsystem.service.SysOrgan1Service;
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

public class SysOrgan1Controller {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private SysOrgan1Service sysOrgan1Service;


    @PostMapping("/organ/queryOrgan1")
    public ResultVo<Object> queryOrgan1() {
        try{
            resultVo.setData(sysOrgan1Service.queryOrgan1());
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/organ/insertOrgan1")
    public ResultVo<Object> insertOrgan1(SysOrgan1 sysOrgan1) {
        try{
            sysOrgan1Service.insertOrgan1(sysOrgan1);
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