package com.car.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.car.entity.vo.RedisChannelInfo;
import com.car.entity.vo.SpeedVO;
import com.car.mapper.HomePageDataMapper;
import com.car.service.HomePageService;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Set;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
     * @return
     * @param limit
     */
    @Override
    public RStatic homePage(String limit) {
        if(!StringUtils.isNumeric(limit)){
            return RStatic.error("分页参数非法");
        }
//        Integer channelCount = homePageDataMapper.getChannelCount();
//        Integer startInteger = (page-1)*items;
//        if(date == null || StringUtils.isEmpty(date)){
//            date = DateTime.now().toString("yyyy-MM-dd");
//        }
//        List<HomePageVo> homePageVoList = homePageDataMapper.selectHomePageData(date,startInteger, items);

        //更新缓存实现方式，从redis获取首页最新数据：key: csxt_home_page_cache_by_channel_id_{channelId}
        ArrayList<SpeedVO> redisChannelInfos = new ArrayList<>();
        int limitCounter = 0;
        Set<String> keys = stringRedisTemplate.keys("csxt_home_page_cache_by_channel_id_*");
        for (String key:
             keys) {
            if(limitCounter >= Integer.parseInt(limit)){
                break;
            }
            SpeedVO parsedHomePageObj = JSONObject.parseObject(stringRedisTemplate.opsForValue().get(key), SpeedVO.class);
            redisChannelInfos.add(parsedHomePageObj);
            limitCounter++;
        }
        return RStatic.ok("查询成功").data("homePageDtoList",redisChannelInfos).data("total",redisChannelInfos.size());
    }
}
