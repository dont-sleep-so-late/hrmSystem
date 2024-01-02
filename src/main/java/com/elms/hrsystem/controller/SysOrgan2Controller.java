package com.elms.hrsystem.controller;



import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.SysOrgan2;
import com.elms.hrsystem.service.SysOrgan2Service;
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

public class SysOrgan2Controller {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private SysOrgan2Service sysOrgan2Service;

    @PostMapping("/organ/queryOrgan2/{o1id}")
    public ResultVo<Object> queryOrgan2(@PathVariable("o1id") String o1id) {
        try{
            resultVo.setData(sysOrgan2Service.queryOrgan2(o1id));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/organ/insertOrgan2")
    public ResultVo<Object> insertOrgan2(SysOrgan2 sysOrgan2) {
        try{
            sysOrgan2Service.insertOrgan2(sysOrgan2);
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