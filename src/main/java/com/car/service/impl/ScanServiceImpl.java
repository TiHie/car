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

    /**
     * 扫描所有的未操作过的文件
     * 复制文件到指定路径
     * 修改文件名
     */
    @Override
    public void scanAndUpload() {
        RuntimeDataUtil.cameraGunEntityMap.forEach((k,v)->{
            String files = LinuxApiUtil.treeDir(
                    "tree -f -J  "+scanPath+v.getFileDir(),
                    "\\\"type\\\":\\\"file\\\" ",
                    RuntimeDataUtil.today,
                    " -v alreadyScan");
            files = "["+files+"]";

            List<TbCarEntity> tbCarEntities = new ArrayList<>();
            List<OneImg> oneImgs = JSON.parseArray(files, OneImg.class);
            for (OneImg img : oneImgs) {
                String fileName = img.getName().replaceAll(scanPath+v.getFileDir(),"");
                LinuxApiUtil.shell(
                        "cp "+img.getName()+" "+uploadFolder+fileName.replaceAll("/","_"),
                        "mv "+img.getName()+" "+img.getName().replaceAll("\\.jpg","_alreadyScan.jpg"));
            }
        });

    }

}
