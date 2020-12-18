package com.car;

import com.car.entity.TbCameraGunEntity;
import com.car.service.ScanService;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TemplateTests {


    @Autowired
    TbCameraGunService tbCameraGunService;

    @Test
    void contextLoads() {
        List<TbCameraGunEntity> entityList = tbCameraGunService.list();
        entityList.forEach(System.out::println);
    }

}