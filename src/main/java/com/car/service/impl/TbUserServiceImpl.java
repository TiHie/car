package com.car.service.impl;

import com.car.entity.TbUserEntity;
import com.car.mapper.TbUserMapper;
import com.car.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUserEntity> implements TbUserService {

}