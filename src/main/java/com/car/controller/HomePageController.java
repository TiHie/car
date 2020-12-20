package com.car.controller;

import com.car.service.HomePageService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mowuwalixilo
 * @date2020/12/18 15:23
 */
@Api(tags = "首页数据展示的接口")
@RestController
public class HomePageController {

    @Autowired
    HomePageService homePageService;

    @ApiOperation("首页通道展示")
    @GetMapping("/api/v1/home")
    public RStatic selectHomePageData(Integer page,Integer items){
        return homePageService.homePage(page, items);
    }

}
