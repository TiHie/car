package com.car.controller;

import com.car.entity.TbCameraGunEntity;
import com.car.service.CameraGunService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author mowuwalixilo
 * @date2020/12/16 15:04
 */
@Api(tags = "摄像枪表CRUD接口")
@RestController
public class CameraGunControloler {

    @Autowired
    CameraGunService cameraGunService;

    /***
     * 摄像枪接口-添加
     * @param cameraGunEntity
     * @return
     */
    @ApiOperation("摄像枪接口-添加")
    @PostMapping("/api/v1/CameraGun")
    public RStatic addCameraGun(@RequestBody TbCameraGunEntity cameraGunEntity){
        return cameraGunService.addCameraGun(cameraGunEntity);
    }

    /***
     * 摄像枪接口-删除
     * @param map
     * @return
     */
    @ApiOperation("摄像枪接口-删除")
    @DeleteMapping("/api/v1/CameraGun")
    public RStatic deleteCamerGun(@RequestBody Map<String,Object> map){
        return cameraGunService.deleteCamerGun(map);
    }

    /***
     * 摄像枪接口-修改
     * @param cameraGunEntity
     * @return
     */
    @ApiOperation("摄像枪接口-修改")
    @PutMapping("/api/v1/CameraGun")
    public RStatic updateCameraGun(@RequestBody TbCameraGunEntity cameraGunEntity){
        return cameraGunService.updateCameraGun(cameraGunEntity);
    }

    /***
     * 摄像枪接口-查询
     * @param limit
     * @param page
     * @param items
     * @return
     */
    @ApiOperation("摄像枪接口-查询")
    @GetMapping("/api/v1/CameraGun")
    public RStatic selectCameraGun(String limit,int page,int items){
        return cameraGunService.selectCameraGun(limit,page,items);
    }
}
