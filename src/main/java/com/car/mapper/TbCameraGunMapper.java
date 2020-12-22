package com.car.mapper;

import com.car.entity.TbCameraGunEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.bean.OneSpeed;
import com.car.entity.vo.CameraGunVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbCameraGunMapper extends BaseMapper<TbCameraGunEntity> {

    List<OneSpeed> getSpeed();

    /***
     * 查询所有摄像枪
     * @param parameter
     * @param startInteger
     * @param items
     * @return
     */
    public List<CameraGunVo> getCameraGunVo(String parameter,Integer startInteger,Integer items);

    public Integer getCameraGunCount(@Param(value = "parameter") String parameter);
}