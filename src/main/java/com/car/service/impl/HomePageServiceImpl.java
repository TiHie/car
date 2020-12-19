package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.vo.ChannelVo;
import com.car.mapper.HomePageDataMapper;
import com.car.service.HomePageService;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    public RStatic homePage(Integer page, Integer items) {

        IPage<TbChannelEntity> channelPage = tbChannelService.page(new Page<>(page, items));
        List<Integer> channelIds = null;
        List<Integer> gunIds = null;
        channelIds = channelPage.getRecords().stream().map(e -> e.getId()).collect(toList());
        gunIds = tbCameraGunService.list(new QueryWrapper<TbCameraGunEntity>()
                .in("channel_id",channelIds))
                .stream().map(e->e.getId())
                .collect(toList());
        List<TbCarEntity> list = tbCarService.list(new QueryWrapper<TbCarEntity>().in("camera_gun_id", gunIds)
                .orderByDesc("create_time").last("limit 1"));

        List<TbChannelEntity> records = channelPage.getRecords();
        List<ChannelVo> channelVoList = new ArrayList<>();

        for (int i=0;i<list.size() && i< records.size();i++) {
            ChannelVo channelVo = new ChannelVo();
            channelVo.setChannelEntity(records.get(i));
            channelVo.setImage(list.get(i).getCarImage());
            channelVoList.add(channelVo);
        }
        Page<ChannelVo> pageVo = new Page<>(page,items);
        pageVo.setRecords(channelVoList);
        return RStatic.ok("查询成功").data("pageVo",pageVo);
    }
}
