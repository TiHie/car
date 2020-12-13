package com.car.service.impl;

import com.car.entity.TbCarEntity;
import com.car.mapper.TbCarMapper;
import com.car.service.TbCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TbCarServiceImpl extends ServiceImpl<TbCarMapper, TbCarEntity> implements TbCarService {

}