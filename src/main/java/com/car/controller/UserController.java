package com.car.controller;

import com.car.entity.TbUserEntity;
import com.car.entity.dto.LoginDto;
import com.car.service.UserService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 21:31
 */
@Api(tags = "用户表CRUD接口")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /***
     * 用户管理接口-添加
     * @param tbUserEntity
     * @return
     */
    @ApiOperation("用户管理接口-添加")
    @PostMapping("/api/v1/user")
    public RStatic addUser(@RequestBody TbUserEntity tbUserEntity){
        return userService.addUser(tbUserEntity);
    }

    /***
     * 用户管理接口- 删除
     * @param map
     * @return
     */
    @ApiOperation("用户管理接口-删除")
    @DeleteMapping("/api/v1/user")
    public RStatic deleteUser(@RequestBody Map<String, Object> map){
        return userService.deleteUser(map);
    }

    /***
     * 用户管理接口-修改
     * @param tbUserEntity
     * @return
     */
    @ApiOperation("用户管理接口-修改")
    @PutMapping("/api/v1/user")
    public RStatic updateUser(@RequestBody TbUserEntity tbUserEntity) throws Exception {
        return userService.updateUser(tbUserEntity);
    }

    /***
     * 用户管理接口-查询
     * @param parameter
     * @param page
     * @param items
     * @return
     */
    @ApiOperation("用户管理接口-查询")
    @GetMapping("/api/v1/user")
    public RStatic selectUser(String parameter,int page,int items){
        return userService.selectUser(parameter, page, items);
    }

    /**
     * 用户登录
     * @param
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/api/v1/user/login")
    public RStatic login(@RequestBody LoginDto loginDto, HttpServletRequest request) throws Exception{
        return userService.login(loginDto.getUsername(),loginDto.getPassword(),request);
    }

    /**
     * 管理员生成账号
     * @param tbUserEntity
     * @return
     */
    @ApiOperation("管理员注册账号")
    @PostMapping("/api/v1/user/register")
    public RStatic register(@RequestBody TbUserEntity tbUserEntity) throws Exception {
        return userService.register(tbUserEntity);
    }
}
