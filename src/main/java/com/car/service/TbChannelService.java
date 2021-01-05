package com.car.service;

import com.car.entity.TbChannelEntity;
import com.baomidou.mybatisplus.extension.service.IService;

public interface TbChannelService extends IService<TbChannelEntity> {

    TbChannelEntity findChannelByGunId(Integer gunId);

}