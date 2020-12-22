package com.car.service;

import com.car.entity.TbCameraGunEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.car.entity.bean.OneSpeed;

import java.util.List;

public interface TbCameraGunService extends IService<TbCameraGunEntity> {

    List<OneSpeed> getSpeed();

}