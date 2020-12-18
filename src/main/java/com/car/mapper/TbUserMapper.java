package com.car.mapper;

import com.car.entity.TbUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.dto.CarMassageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbUserMapper extends BaseMapper<TbUserEntity> {

}