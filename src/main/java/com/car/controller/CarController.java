package com.car.controller;

import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(tags = "车辆照片接口")
@RestController
@CrossOrigin
public class CarController {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @ApiOperation("上传文件/图片 oss")
    @PostMapping("/api/util/uploadFile")
    public RStatic uploadFile(MultipartFile multipartFile){
        try{
            multipartFile.transferTo(new File(uploadFolder+multipartFile.getOriginalFilename()));
            return RStatic.ok("上传成功").data("url","http://127.0.0.1:8081"+staticAccessPath.replaceAll("\\*","")+multipartFile.getOriginalFilename());
        }catch(Exception e){
            e.printStackTrace();
            return RStatic.error("上传失败").data("url",null);
        }
    }
}
