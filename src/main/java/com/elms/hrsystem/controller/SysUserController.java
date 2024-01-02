package com.elms.hrsystem.controller;


import com.elms.hrsystem.entity.*;
import com.elms.hrsystem.service.FileProfessionService;
import com.elms.hrsystem.service.SysUserService;
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
@RequestMapping("/Sys")

public class SysUserController {

    ResultVo<Object> resultVo = new ResultVo<>();
    
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FileProfessionService fileProfessionService;

    @PostMapping("/register/{name}/{password}/{rid}")
    public ResultVo<Object> insertUser(@PathVariable("name") String name,
                                       @PathVariable("password") String password,
                                       @PathVariable("rid") String rid) {
        try{
            SysUser sysUser = new SysUser();
            sysUser.setUsername(name);
            sysUser.setPassword(password);
            sysUser.setRId(rid);

            sysUserService.insertUser(sysUser);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (RuntimeException e){
            e.printStackTrace();
            resultVo.setMess(e.getMessage());
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/login")
    public ResultVo<Object> login(SysUser sysUser) {
        List<Map<String, Object>> login = sysUserService.login(sysUser);
        try{
            if(!login.isEmpty()){
                resultVo.setData(login);
                resultVo.setMess("true");
                resultVo.setOk(true);
            }else{
                resultVo.setData("");
                resultVo.setMess("没有该用户");
                resultVo.setOk(false);
            }
            System.out.println(sysUser.getUsername()+","+sysUser.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/login/loginPermission")
    public ResultVo<Object> loginPermission(SysUser sysUser) {
        try{
            resultVo.setData(sysUserService.loginPermission(sysUser));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }



    @PostMapping("/user/showAllUser/{page}/{limit}")
    public ResultVo<Object> showAllUser(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysUserService.showAllUser(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }

    @PostMapping("/user/queryUserByName/{username}/{page}/{limit}")
    public ResultVo<Object> queryUserByName(@PathVariable("username") String username,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysUserService.queryUserByName(username,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/user/queryUserByRole/{role_name}/{page}/{limit}")
    public ResultVo<Object> queryUserByRole(@PathVariable("role_name") String role_name,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysUserService.queryUserByRole(role_name,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/user/deleteUserByUid/{uid}")
    public ResultVo<Object> deleteUserByUid(@PathVariable("uid") String uid) {
        try{
            sysUserService.deleteUserByUid(uid);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/user/updateUserRoleByUid")
    public ResultVo<Object> updateUserRoleByUid(SysUser sysUser) {
        try{
            sysUserService.updateUserRoleByUid(sysUser);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/organ/queryAllOrganization/{page}/{limit}")
    public ResultVo<Object> queryAllOrganization(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysUserService.queryAllOrganization(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/organ/queryOrganizationByName/{organName}/{page}/{limit}")
    public ResultVo<Object> queryOrganizationByName(@PathVariable("organName") String organName,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(sysUserService.queryOrganizationByName(organName,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }



    @PostMapping("/position/queryAllProfession/{page}/{limit}")
    public ResultVo<Object> queryAllProfession(@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(fileProfessionService.queryAllProfession(page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/position/queryProfessionByProfession/{profession}/{page}/{limit}")
    public ResultVo<Object> queryProfessionByProfession(@PathVariable("profession") String profession,@PathVariable("page") int page,@PathVariable("limit") int limit) {
        try{
            resultVo.setData(fileProfessionService.queryProfessionByProfession(profession,page,limit));
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/position/insertProfession")
    public ResultVo<Object> insertProfession(FileProfession fileProfession) {
        try{
            fileProfessionService.insertProfession(fileProfession);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/position/updateProfessionByPid")
    public ResultVo<Object> updateProfessionByPid(@RequestParam("profession") String profession,@RequestParam("p_id") String pId) {
        try{
            fileProfessionService.updateProfessionByPid(profession,pId);
            resultVo.setData("");
            resultVo.setMess("true");
            resultVo.setOk(true);
        }catch (Exception e){
            e.printStackTrace();
            resultVo.setMess("false");
            resultVo.setOk(false);
        }
        return resultVo;
    }


    @PostMapping("/position/deleteProfessionByPid")
    public ResultVo<Object> deleteProfessionByPid(String profession,String pId) {
        try{
            fileProfessionService.deleteProfessionByPid(profession,pId);
            resultVo.setData("");
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