package com.elms.hrsystem.controller;


import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.service.FileNationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-15
 */
@RestController
@RequestMapping("/File")
public class FileNationController {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private FileNationService fileNationService;

    @PostMapping("/add/queryNation")
    public ResultVo<Object> queryNation() {
        try{

            List<Map<String, String>> maps = fileNationService.queryNation();

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


}