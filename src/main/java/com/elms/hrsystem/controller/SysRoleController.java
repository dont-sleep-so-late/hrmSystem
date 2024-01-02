package com.elms.hrsystem.controller;



import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.service.SysRoleService;
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

public class SysRoleController {


    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private SysRoleService sysRoleService;



    @PostMapping("/register/queryAllRole")
    public ResultVo<Object> queryAllRole() {
        try{
            resultVo.setData(sysRoleService.queryAllRole());
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