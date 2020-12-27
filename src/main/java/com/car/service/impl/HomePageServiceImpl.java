package com.car.service.impl;


import com.car.entity.vo.HomePageVo;
import com.car.mapper.HomePageDataMapper;
import com.car.service.HomePageService;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author mowuwalixilo
 * @date2020/12/18 15:26
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    HomePageDataMapper homePageDataMapper;

    @Autowired
    TbCarService tbCarService;

    @Autowired
    TbChannelService tbChannelService;

    @Autowired
    TbCameraGunService tbCameraGunService;

    /***
     * 通过车辆ID查询汽车信息
     * @param carId
     * @return
     */
    @Override
    public RStatic selectCarMassageById(Integer carId) {
        return RStatic.ok("查询成功").data("carData",homePageDataMapper.selectCarMassageById(carId));
    }

    /***
     * 首页通道展示
     * @param page
     * @param items
     * @return
     */
    @Override
    public RStatic homePage(String date,Integer page, Integer items) {
        Integer channelCount = homePageDataMapper.getChannelCount();
        Integer startInteger = (page-1)*items;
        List<HomePageVo> homePageVoList = homePageDataMapper.selectHomePageData(date,startInteger, items);
        return RStatic.ok("查询成功").data("homePageVoList",homePageVoList).data("total",channelCount);
    }
}
