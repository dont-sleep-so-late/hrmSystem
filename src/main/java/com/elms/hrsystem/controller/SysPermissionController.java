package com.elms.hrsystem.controller;


import com.elms.hrsystem.service.SysPermissionService;
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

public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;


}