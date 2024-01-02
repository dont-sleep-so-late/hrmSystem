package com.elms.hrsystem.controller;


import com.elms.hrsystem.service.SalInfoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Arrays;




/**
 * 薪酬项目关联表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@RestController
@RequestMapping("/salinfoitem")
public class SalInfoItemController {

    @Autowired
    private SalInfoItemService salInfoItemService;


}