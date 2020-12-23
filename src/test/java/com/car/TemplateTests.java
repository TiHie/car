package com.car;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.vo.CameraGunVo;
import com.car.entity.vo.ChannelVo;
import com.car.mapper.HomePageDataMapper;
import com.car.mapper.TbCameraGunMapper;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.car.service.TbChannelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;


@SpringBootTest
class TemplateTests {

    @Autowired
    HomePageDataMapper homePageDataMapper;
    @Autowired
    TbCarService tbCarService;
    @Autowired
    TbChannelService tbChannelService;
    @Autowired
    TbCameraGunService tbCameraGunService;

    @Autowired
    TbCameraGunMapper tbCameraGunMapper;

    @Test
    void contextLoads() {
        IPage<TbChannelEntity> page = tbChannelService.page(new Page<>(1, 8));
        List<Integer> channelIds = null;
        List<Integer> gunIds = null;
        channelIds = page.getRecords().stream().map(e -> e.getId()).collect(toList());
        System.out.println("channelIds size is \n"+channelIds.size());
        //channelIds->查询该channel的最后一张车辆图片对应的camera_gun_id
        gunIds = tbCameraGunService.list(new QueryWrapper<TbCameraGunEntity>()
                .in("channel_id",channelIds))
                .stream().map(e->e.getId())
                .collect(toList());
        List<TbCarEntity> list = tbCarService.list(new QueryWrapper<TbCarEntity>().in("camera_gun_id", gunIds)
                .orderByDesc("create_time").last("limit 1"));
        System.out.println("result list size:"+list.size());
        list.stream().forEach(System.out::println);

        List<TbChannelEntity> records = page.getRecords();
        List<ChannelVo> channelVoList = new ArrayList<>();

        for (int i=0;i<list.size() && i< records.size();i++) {
            ChannelVo channelVo = new ChannelVo();
            channelVo.setChannelEntity(records.get(i));
            channelVo.setImage(list.get(i).getCarImage());
            channelVoList.add(channelVo);
        }
        Page<ChannelVo> pageVo = new Page<>(1,3);
        pageVo.setRecords(channelVoList);
    }


    @Test
    void test2(){
        //第几页
        Integer page = 0;
        //每页多少
        Integer items = 3;
        //总数
        Integer gunCount = tbCameraGunMapper.getCameraGunCount("x");
        //可分多少页 总数/每页多少
        int pages = gunCount / items;
        String str = "二";
        String parameter = "%"+str+"%";

        List<CameraGunVo> cameraGunVoList = tbCameraGunMapper.getCameraGunVo(parameter,page,items);
        System.out.println("=="+cameraGunVoList.toString());
    }
}

