package com.elms.hrsystem.controller;


import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.service.FileProfessionService;
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
public class FileProfessionController {

    ResultVo<Object> resultVo = new ResultVo<>();

    @Autowired
    private FileProfessionService fileProfessionService;

    @PostMapping("/add/queryProfession")
    public ResultVo<Object> queryProfession() {
        try{

            List<Map<String, String>> maps = fileProfessionService.queryProfession();

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