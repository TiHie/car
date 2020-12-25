package com.car.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wjz
 * @date 2020/12/24
 */
@Api("重新定义404页面跳转")
@Controller
@CrossOrigin
public class ErrorController {

    @Autowired
    private BasicErrorController basicErrorController;

    @GetMapping("/error")
    public String error(HttpServletRequest request, HttpServletResponse response){
        return "redirect:http://car.hhzzss.cn/index.html";
    }
}
