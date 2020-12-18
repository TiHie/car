package com.car.controller;

import com.car.entity.dto.LogDTO;
import com.car.service.TbLogService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author FDH
 * @Date 2020/12/17 20:40
 **/
@Api(tags = "日志接口")
@RestController
@CrossOrigin
public class LogController {
    @Autowired
    private TbLogService tbLogService;

    @ApiOperation("获取日志")
    @GetMapping("/api/admin/getLog")
    public RStatic getLog(Integer page, Integer items){
        return tbLogService.getLog(page, items);
    }
    @ApiOperation("删除日志（可批量）")
    @PostMapping("/api/admin/deleteLog")
    public RStatic deleteLog(List<String> ids){
        return tbLogService.deleteLog(ids);
    }
    @ApiOperation("多条件模糊查找日志")
    @GetMapping("/api/admin/likeSearchLog")
    public RStatic likeSearchLog(LogDTO logDTO) {
        return tbLogService.likeSearchLog(logDTO);
    }
}
