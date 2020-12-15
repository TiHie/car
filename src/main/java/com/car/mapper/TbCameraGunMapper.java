package com.car.mapper;

import com.car.entity.TbCameraGunEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.bean.OneSpeed;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbCameraGunMapper extends BaseMapper<TbCameraGunEntity> {

    List<OneSpeed> getSpeed();
}