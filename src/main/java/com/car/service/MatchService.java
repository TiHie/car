package com.car.service;

import com.car.entity.TbCarEntity;

import java.util.List;

public interface MatchService {
    TbCarEntity match(String fileName, String regStr, List<String> regGroup2EntityField);
    TbCarEntity match(String name,int gunId);
}
