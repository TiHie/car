package com.car.service;

import com.car.entity.TbLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.car.entity.dto.LogDTO;
import com.car.util.RStatic;

import java.util.List;

public interface TbLogService extends IService<TbLogEntity> {

    /**
     * 初始化日志（已测）
     *
     * @param page
     * @param items
     * @return
     */
    public RStatic getLog(Integer page, Integer items);

    /**
     * 批量删除登录日志（已测）
     *
     * @param ids
     * @return
     */
    public RStatic deleteLog(List<String> ids);

    /**
     * 多条件模糊查询操作日志（已测）
     *
     * @param logDTO
     * @return
     */
    public RStatic likeSearchLog(LogDTO logDTO);


}