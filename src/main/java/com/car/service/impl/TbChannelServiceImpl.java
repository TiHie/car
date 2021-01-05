package com.car.service.impl;

import com.car.entity.TbChannelEntity;
import com.car.mapper.TbChannelMapper;
import com.car.service.TbChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbChannelServiceImpl extends ServiceImpl<TbChannelMapper, TbChannelEntity> implements TbChannelService {

    @Autowired
    private TbChannelMapper tbChannelMapper;
    @Override
    public TbChannelEntity findChannelByGunId(Integer gunId) {
        return tbChannelMapper.findChannelByGunId(gunId);
    }
}