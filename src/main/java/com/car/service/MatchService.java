package com.car.service;

import com.car.entity.TbCarEntity;

public interface MatchService {
    TbCarEntity match(String name);
    TbCarEntity match(String name,int gunId);
}
