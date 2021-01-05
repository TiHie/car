package com.car.mapper;

import com.car.entity.TbChannelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TbChannelMapper extends BaseMapper<TbChannelEntity> {

    @Select("select tb_channel.* from tb_channel,tb_camera_gun where tb_camera_gun.channel_id = tb_channel.id and tb_camera_gun.id = #{gunId}")
    TbChannelEntity findChannelByGunId(@Param("gunId") Integer gunId);
}