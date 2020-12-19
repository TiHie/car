package com.car.controller;

import com.car.entity.dto.SpeedDTO;
import com.car.service.ScanService;
import com.car.service.TbCarService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "车辆照片接口")
@RestController
@CrossOrigin
public class CarController {
    @Autowired
    private TbCarService tbCarService;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private ScanService scanService;

    @ApiOperation("上传文件")
    @PostMapping("/api/util/uploadFile")
    public RStatic uploadFile(MultipartFile multipartFile,@RequestParam("gunId") Integer gunId){
        try{
            System.out.println(gunId);
            multipartFile.transferTo(new File(uploadFolder+multipartFile.getOriginalFilename()));
            scanService.uploadOne(gunId,multipartFile.getOriginalFilename());
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
    public RStatic uploadFiles(@RequestParam("fileList") List<MultipartFile> multipartFiles,@RequestParam("gunId") Integer gunId) {
        List<String> errorList = new ArrayList<>();
        List<String> successList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            try {
                multipartFile.transferTo(new File(uploadFolder + multipartFile.getOriginalFilename()));
                successList.add(multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                errorList.add(multipartFile.getOriginalFilename());
            }
        }
        scanService.uploadAll(gunId,successList);
        return RStatic.ok("上传成功").
                data("errorList",errorList);
    }

    @ApiOperation("点击查看窗口跳出的信息框")
    @GetMapping("/api/user/channelAllData")
    public RStatic channelAllData(SpeedDTO speedDTO) {
        return tbCarService.channelAllData(speedDTO);
    }

    @ApiOperation("放大图片跳出的信息")
    @GetMapping("/api/user/channelOneCarData")
    public RStatic channelOneCarData(SpeedDTO speedDTO) {
        return tbCarService.channelOneCarData(speedDTO);
    }

}
