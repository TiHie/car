package com.car.controller;

import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "车辆照片接口")
@RestController
@CrossOrigin
public class CarController {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @ApiOperation("上传文件")
    @PostMapping("/api/util/uploadFile")
    public RStatic uploadFile(MultipartFile multipartFile){
        try{
            multipartFile.transferTo(new File(uploadFolder+multipartFile.getOriginalFilename()));
            return RStatic.ok("上传成功").
                    data("url","http://127.0.0.1:8081"+staticAccessPath.replaceAll("\\*","")+multipartFile.getOriginalFilename()).
                    data("fileName",multipartFile.getOriginalFilename());
        }catch(Exception e){
            e.printStackTrace();
            return RStatic.error("上传失败").
                    data("url",null).
                    data("fileName",multipartFile.getOriginalFilename());
        }
    }

    @ApiOperation("批量上传文件，使用一个名为 fileList 的数组传递")
    @PostMapping("/api/util/uploadFiles")
    public RStatic uploadFiles(@RequestParam("fileList") List<MultipartFile> multipartFiles) {
        List<String> errorList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                System.out.println(multipartFile.getOriginalFilename());
                multipartFile.transferTo(new File(uploadFolder + multipartFile.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                errorList.add(multipartFile.getOriginalFilename());
            }
        }
        return RStatic.ok("上传成功").
                data("errorList",errorList);
    }
}
