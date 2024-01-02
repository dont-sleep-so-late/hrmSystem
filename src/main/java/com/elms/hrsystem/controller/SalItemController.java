package com.elms.hrsystem.controller;



import com.elms.hrsystem.entity.ResultVo;
import com.elms.hrsystem.entity.SalItem;
import com.elms.hrsystem.entity.dto.ItemDto;
import com.elms.hrsystem.service.SalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Arrays;




/**
 * 薪酬项目表
 *
 * @author elms elms2002@163.com
 * @since 1.0.0 2023-12-22
 */
@RestController
@RequestMapping("/salitem")
public class SalItemController {

    @Autowired
    private SalItemService salItemService;

    ResultVo<Object> resultVo = new ResultVo<>();

    @PostMapping("/add")
    public  ResultVo<Object> add(@RequestBody ItemDto salItem){
        try{
            SalItem salItem1 = salItemService.insertSalItem(salItem);
            resultVo.setData(salItem1);
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



    @GetMapping("/list")
    public ResultVo<Object> list(@RequestParam("pageSize")int pageSize,@RequestParam("pageNum")int pageNum){
        try{
            resultVo.setData(salItemService.list(pageSize,pageNum));
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


    @PostMapping("/delete")
    public  ResultVo<Object> delete(@RequestParam("id") String id){
        try{

            boolean delete = salItemService.delete(id);
            resultVo.setData(delete);
            resultVo.setMess("true");
            resultVo.setOk(delete);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(false);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/update")
    public  ResultVo<Object> update(@RequestBody ItemDto salItem){
        try{
            boolean update = salItemService.update(salItem);
            resultVo.setData(update);
            resultVo.setMess("true");
            resultVo.setOk(update);
        }
        catch (Exception e){
            e.printStackTrace();
            resultVo.setData(false);
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


}