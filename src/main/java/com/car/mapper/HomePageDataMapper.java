package com.car.mapper;

import com.car.entity.dto.CarMassageDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author mowuwalixilo
 * @date2020/12/18 12:12
 */
@Mapper
@Repository
public interface HomePageDataMapper {

    /**
     * 传车辆ID，返回单条车辆信息
     * @param carId
     * @return
     */
    public CarMassageDto selectCarMassageById(Integer carId);

    /**
     * 传通道ID，返回当天通过的全部车辆数量
     * @param channelId
     * @return
     */
    public Integer selectAllCarById(Integer channelId);

    /**
     * 传通道ID，返回当天通过的正常车辆数量
     * @param channelId
     * @return
     */
    public Integer selectNormalCarById(Integer channelId);

}
