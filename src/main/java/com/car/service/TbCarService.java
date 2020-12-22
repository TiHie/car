package com.car.service;

import com.car.entity.TbCarEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.car.entity.dto.SpeedDTO;
import com.car.util.RStatic;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface TbCarService extends IService<TbCarEntity> {

    /**
     * 查看多辆车的单天或多天数据
     * @param speedDTO
     * @return
     */
    public RStatic channelAllData(SpeedDTO speedDTO);

    /**
     * 点击放大图片出现的信息
     * @param speedDTO
     * @return
     */
    public RStatic channelOneCarData(SpeedDTO speedDTO);

    /**
     * 导出历史查询
     * @param speedDTO
     * @return
     */
    public XSSFWorkbook export(SpeedDTO speedDTO);
}