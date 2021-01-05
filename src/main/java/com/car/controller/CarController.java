package com.car.controller;

import com.alibaba.fastjson.JSON;
import com.car.entity.TbCarEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.dto.SpeedDTO;
import com.car.entity.vo.RedisChannelInfo;
import com.car.entity.vo.SpeedVO;
import com.car.mapper.TbCarMapper;
import com.car.service.ScanService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.rmi.runtime.Log;

import java.awt.print.PrinterAbortException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "车辆照片接口")
@RestController
@CrossOrigin
@Slf4j
public class CarController {
    @Autowired
    private TbCarService tbCarService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TbChannelService tbChannelService;
    @Autowired TbCarMapper tbCarMapper;

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.scan}")
    private String scanPath;

    @Autowired
    private ScanService scanService;

    @ApiOperation("在扫描目录下创建文件夹")
    @PostMapping("/api/util/createDir")
    public RStatic createDir(@RequestParam("dirName") String dirName){
        File file = new File(scanPath+dirName);
        boolean mkdir = file.mkdir();
        return RStatic.ok("创建成功").data("success",mkdir);
    }

    @ApiOperation("上传文件--这里是系统真是使用的唯一上传文件接口")
    @PostMapping("/api/util/uploadFile")
    public RStatic uploadFile(MultipartFile multipartFile,@RequestParam("gunId") Integer gunId){
        try{
            boolean isLegal = scanService.checkLegitimacy(multipartFile.getOriginalFilename()
                    .substring(0,multipartFile.getOriginalFilename().indexOf(".")),gunId);
            if(!isLegal){return RStatic.error("文件名与扫描枪解析规则不匹配，请修改匹配规则或校正图片名。");}
            multipartFile.transferTo(new File(uploadFolder+multipartFile.getOriginalFilename()));
            TbCarEntity tbCarEntity = scanService.uploadOne(gunId, multipartFile.getOriginalFilename());
            if(tbCarEntity == null){
                return RStatic.error("上传失败");
            }
            //RedisChannelInfo redisChannelInfo = new RedisChannelInfo();

            SpeedVO speedVo = tbCarMapper.getSpeedVo(gunId, tbCarEntity.getId());

//            redisChannelInfo.setLatestCameraId(gunId);
//            redisChannelInfo.setPicUrl(tbCarEntity.getCarImage());
//            TbChannelEntity tbChannelEntity = tbChannelService.findChannelByGunId(gunId);
//            redisChannelInfo.setChannelId(tbChannelEntity.getId());
//            redisChannelInfo.setChannelName(tbChannelEntity.getName());
//            redisChannelInfo.setChannelCreateDate(tbChannelEntity.getCreateTime());
//            SpeedVO speedVO = new SpeedVO();
//            speedVO.setCameraGunId(tbCarEntity.getCameraGunId());
//            speedVO.setCarId(tbCarEntity.getId());
//            speedVO.setCarImage(tbCarEntity.getCarImage());
//            speedVO.setLicensePlate(tbCarEntity.getLicensePlate());
//            speedVO.setShootingDate(tbCarEntity.getShootingDate());
//            speedVO.setStatus(tbCarEntity.getStatus()==true?1:0);
//            redisChannelInfo.setSpeedVO(speedVO);
            stringRedisTemplate.opsForValue().set("csxt_home_page_cache_by_channel_id_"+speedVo.getChannelId(), JSON.toJSONString(SpeedVO.class));
            return RStatic.ok("上传成功").
                    data("url",tbCarEntity.getCarImage()).
                    data("fileName",multipartFile.getOriginalFilename());
        }catch(Exception e){
            e.printStackTrace();
            log.error("上传失败,文件解析异常,可能原因为扫描枪解析规则与文件名不匹配或服务器路径你不存在,异常信息:"+e.getMessage());
            return RStatic.error("上传失败,文件解析异常,可能原因为扫描枪解析规则与文件名不匹配,异常信息:"+e.getMessage()).
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
        List<String> saveError = scanService.uploadAll(gunId, successList);
        return RStatic.ok("上传成功")
                .data("errorList",errorList)
                .data("saveError",saveError);
    }

    @ApiOperation("点击查看窗口跳出的信息框")
    @GetMapping("/api/user/channelAllData")
    public RStatic channelAllData(SpeedDTO speedDTO) {
        return tbCarService.channelAllData(speedDTO);
    }

    @ApiOperation("放大图片跳出的信息")
    @GetMapping("/api/user/channelOneCarData")
    public RStatic channelOneCarData(SpeedDTO speedDTO) throws ParseException {
        return tbCarService.channelOneCarData(speedDTO);
    }

    @ApiOperation("导出历史查询")
    @GetMapping("/api/user/exportCars")
    public RStatic export(SpeedDTO speedDTO) {
        return tbCarService.export(speedDTO);
    }

}
