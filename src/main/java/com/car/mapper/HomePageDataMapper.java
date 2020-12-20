package com.car.mapper;

import com.car.entity.dto.CarMassageDto;
import com.car.entity.vo.HomePageVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /***
     * 查询通道总数
     * @return
     */
    public Integer getChannelCount();

    /***
     * 分页查询首页通道数据
     * @param startInteger
     * @param items
     * @return
     */
    public List<HomePageVo> selectHomePageData(Integer startInteger,Integer items);
}
