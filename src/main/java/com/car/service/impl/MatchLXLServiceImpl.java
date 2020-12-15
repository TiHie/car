package com.car.service.impl;

import com.car.entity.TbCarEntity;
import com.car.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchLXLServiceImpl implements MatchService {

    @Override
    public TbCarEntity match(String fileName, String regStr, List<String> regGroup2EntityField) {

        return null;
    }

    @Override
    public TbCarEntity match(String name, int gunId) {

        return null;
    }
}
