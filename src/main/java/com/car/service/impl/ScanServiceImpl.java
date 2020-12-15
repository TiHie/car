package com.car.service.impl;

import com.alibaba.fastjson.JSON;
import com.car.entity.TbCarEntity;
import com.car.entity.bean.OneImg;
import com.car.service.ScanService;
import com.car.service.TbCarService;
import com.car.util.LinuxApiUtil;
import com.car.util.RuntimeDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScanServiceImpl implements ScanService {

    @Value("${file.scan}")
    private String scanPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    private TbCarService tbCarService;
    @Autowired
    private MatchLXLServiceImpl matchLXLService;

    /**
     * 扫描所有的未操作过的文件
     * 复制文件到指定路径
     * 修改文件名
     */
    @Override
    public void scanAndUpload(boolean scanAll) {
        RuntimeDataUtil.cameraGunEntityMap.forEach((k,v)->{
            String files = null;
            if (scanAll){
                files = LinuxApiUtil.treeDir(//扫描所有日期
                        "tree -f -J  "+scanPath+v.getFileDir(),
                        "\\\"type\\\":\\\"file\\\" ",
                        " -v alreadyScan");
                files = "["+files+"]";
            }else {
                files = LinuxApiUtil.treeDir(
                        "tree -f -J  "+scanPath+v.getFileDir(),
                        "\\\"type\\\":\\\"file\\\" ",
                        RuntimeDataUtil.today,
                        " -v alreadyScan");
                files = "["+files+"]";
            }

            //批量存储到数据库
            List<TbCarEntity> tbCarEntities = new ArrayList<>();
            List<OneImg> oneImgs = JSON.parseArray(files, OneImg.class);
            for (OneImg img : oneImgs) {
                String[] split = img.getName().split("/");
                String fileName = split[split.length-1];//拿到最后一个元素作为文件名
                LinuxApiUtil.shell(
                        "cp "+img.getName()+" "+uploadFolder+fileName.replaceAll("/","_"),
                        "mv "+img.getName()+" "+img.getName().replaceAll("\\.jpg","_alreadyScan.jpg"));
            }
        });
    }


}
