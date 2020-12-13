package com.car.mapper;

import com.car.entity.TbUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbUserMapper extends BaseMapper<TbUserEntity> {

}