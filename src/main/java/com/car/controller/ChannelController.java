package com.car.controller;

import com.car.entity.TbChannelEntity;
import com.car.service.ChannelService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 20:11
 */
@Api(tags = "通道表CRUD接口")
@RestController
public class ChannelController {

    @Autowired
    ChannelService channelService;

    /***
     * 通道表接口-添加
     * @param channelEntity
     * @return
     */
    @ApiOperation("通道表接口-添加")
    @PostMapping("/api/v1/channel")
    public RStatic addChannel(@RequestBody TbChannelEntity channelEntity){
        return channelService.addChannel(channelEntity);
    }

    /***
     * 通道表接口-删除
     * @param map
     * @return
     */
    @ApiOperation("通道表接口-删除")
    @DeleteMapping("/api/v1/channel")
    public RStatic deleteChannel(@RequestBody Map<String, Object> map){
        return channelService.deleteChannel(map);
    }

    /***
     * 通道表接口-修改
     * @param channelEntity
     * @return
     */
    @PutMapping("/api/v1/channel")
    @ApiOperation("通道表接口-修改")
    public RStatic updateChannel(@RequestBody TbChannelEntity channelEntity){
        return channelService.updateChannel(channelEntity);
    }

    /***
     * 通道表接口-查询
     * @param parameter
     * @param page
     * @param items
     * @return
     */
    @GetMapping("/api/v1/channel")
    @ApiOperation("通道表接口-查询")
    public RStatic seleteChannel(String parameter,int page,int items){
        return channelService.selectChannel(parameter, page, items);
    }
}
