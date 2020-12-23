package com.car.service.impl;

import com.car.entity.TbLogEntity;
import com.car.entity.dto.LogDTO;
import com.car.mapper.TbLogMapper;
import com.car.service.TbLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TbLogServiceImpl extends ServiceImpl<TbLogMapper, TbLogEntity> implements TbLogService {

    @Autowired
    private TbLogService tbLogService;
    @Autowired
    private TbLogMapper tbLogMapper;
    @Override
    public RStatic getLog(Integer page, Integer items) {
        List<TbLogEntity> logPage = tbLogService.list().stream().skip((page- 1) * items).limit(items).collect(Collectors.toList());

        return RStatic.ok("查询成功").data("logPage", logPage).data("page",page).data("items",items);
    }

    @Override
    public RStatic deleteLog(List<String> ids) {
        boolean b = tbLogService.removeByIds(ids);
        if (b) {
            return RStatic.ok("删除成功");
        }
        return RStatic.error("删除失败");
    }

    @Override
    public RStatic likeSearchLog(LogDTO logDTO) {
        logDTO.setPage((logDTO.getPage()-1)*logDTO.getItems());
        List<TbLogEntity> logList = tbLogMapper.likeSearchLog(logDTO);
        return RStatic.ok("查询成功").data("logPage", logList);
    }

}