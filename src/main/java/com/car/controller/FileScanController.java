package com.car.controller;

import com.car.entity.TbCarEntity;
import com.car.service.ScanService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "图片上传")
@CrossOrigin
@RestController
public class FileScanController {

    @Autowired
    private ScanService scanService;


    @ApiOperation("先使用上传文件接口，再传入文件名和摄像枪 id 即可")
    @PostMapping("/api/file/uploadOne")
    public RStatic uploadOne(String fileName,int gunId){
        TbCarEntity tbCarEntity = scanService.uploadOne(gunId, fileName);
        return RStatic.ok("上传成功").data("info",tbCarEntity);
    }
}
