package com.car.mapper;

import com.car.entity.TbLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.car.entity.dto.LogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TbLogMapper extends BaseMapper<TbLogEntity> {

    /**
     * 多条件模糊查询日志
     * @param logDTO
     * @return
     */
    public List<TbLogEntity> likeSearchLog(@Param("logDTO") LogDTO logDTO);
}