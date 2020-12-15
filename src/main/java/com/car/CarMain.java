package com.car;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.bean.OneSpeed;
import com.car.service.ScanService;
import com.car.service.TbCameraGunService;
import com.car.util.RuntimeDataUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@SpringBootApplication
public class CarMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CarMain.class, args);
        init(run);
    }

    //初始化运行时数据
    public static void init(ConfigurableApplicationContext run){
        TbCameraGunService tbCameraGunService = run.getBean(TbCameraGunService.class);
        List<TbCameraGunEntity> entityList = tbCameraGunService.list();
        //使用并发的 map
        RuntimeDataUtil.cameraGunEntityMap =
                new ConcurrentHashMap<>(entityList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e)));
        RuntimeDataUtil.today = "2020年10月22日";
        TbCameraGunService gunService = run.getBean(TbCameraGunService.class);
        List<OneSpeed> speedList = gunService.getSpeed();
        RuntimeDataUtil.speedMap =
                new ConcurrentHashMap<>(speedList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e.getSpeed())));
        ScanService scanService = run.getBean(ScanService.class);
        scanService.scanAndUpload(false);
    }
}