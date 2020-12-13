package com.car.mapper;

import com.car.entity.TbLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbLogMapper extends BaseMapper<TbLogEntity> {

}