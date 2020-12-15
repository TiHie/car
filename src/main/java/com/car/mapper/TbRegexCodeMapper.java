package com.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.TbRegexCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TbRegexCodeMapper extends BaseMapper<TbRegexCodeEntity> {
}
