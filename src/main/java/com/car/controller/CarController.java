package com.car.controller;

import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "车辆照片接口")
@RestController
@CrossOrigin
public class CarController {

    @ApiOperation("测试接口")
    @GetMapping("/api/car/test")
    public RStatic test(){
        return RStatic.ok("测试成功");
    }
}
