package com.car.service.impl;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.bean.OneSpeed;
import com.car.mapper.TbCameraGunMapper;
import com.car.service.TbCameraGunService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbCameraGunServiceImpl extends ServiceImpl<TbCameraGunMapper, TbCameraGunEntity> implements TbCameraGunService {

    @Autowired
    private TbCameraGunMapper tbCameraGunMapper;

    @Override
    public List<OneSpeed> getSpeed() {
        List<OneSpeed> result = tbCameraGunMapper.getSpeed();
        return result;
    }
}