package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.dto.SpeedDTO;
import com.car.entity.vo.SpeedVO;
import com.car.mapper.TbCarMapper;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.car.service.TbChannelService;
import com.car.util.DateUtil;
import com.car.util.RStatic;
import com.car.util.RuntimeDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TbCarServiceImpl extends ServiceImpl<TbCarMapper, TbCarEntity> implements TbCarService {
    @Autowired
    private TbCarMapper tbCarMapper;
    @Autowired
    private TbCameraGunService tbCameraGunService;
    @Autowired
    private TbChannelService tbChannelService;
    @Autowired
    private TbCarService tbCarService;

    /**
     * 测速数据
     *
     * @return
     */
    @Override
    public RStatic channelAllData(SpeedDTO speedDTO) {
        try {
            if (speedDTO.getPage()!=null && speedDTO.getPage()!=null)
            {
                speedDTO.setPage((speedDTO.getPage()-1)*speedDTO.getItems());

            }
            List<SpeedVO> cameraGunListCars = new ArrayList<>();
            List<SpeedVO> listCar = new ArrayList<>();
            SpeedVO carVo = new SpeedVO();
            Map<String, Integer> stringIntegerMap = new HashMap<>();


            if ((speedDTO.getStartTime()!=null && speedDTO.getEndTime() != null)||(speedDTO.getStartTime()==null && speedDTO.getEndTime() == null)) {

                cameraGunListCars = tbCarMapper.getMoreDayCars(speedDTO);
                stringIntegerMap = tbCarMapper.moreDayRatio(speedDTO);
            } else {
                System.out.println("****");
                cameraGunListCars = tbCarMapper.getOneDayCars(speedDTO);
                System.out.println("--");
                stringIntegerMap = tbCarMapper.oneDayRatio(speedDTO);
                System.out.println("++");
            }
            if(speedDTO.getPage()!=null)
            {
                return RStatic.ok("查询成功").data("carPage", cameraGunListCars).data("dayRatio", stringIntegerMap).data("page", speedDTO.getPage()+1).data("items", speedDTO.getItems());
            }else {
                return RStatic.ok("查询成功").data("carPage", cameraGunListCars).data("dayRatio", stringIntegerMap).data("page", speedDTO.getPage()).data("items", speedDTO.getItems());

            }

        } catch (Exception e) {
            System.out.println("错误原因：" + e.getMessage());
        }
        return RStatic.error("错误");
    }

    @Override
    public RStatic channelOneCarData(SpeedDTO speedDTO) throws ParseException {
        if (speedDTO.getPage()!=null && speedDTO.getPage()!=null)
        {
            speedDTO.setPage((speedDTO.getPage()-1)*speedDTO.getItems());

        }
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        List<SpeedVO> cameraGunListCars = tbCarMapper.getOneDayCars(speedDTO);

        Date hour = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String now = formatter.format(new Date());
        speedDTO.setStartTime(formatter.parse(now));
        System.out.println("时间"+speedDTO.getStartTime());
        Integer hourInt;
        if (speedDTO.getChannelId() != null) {

                hourInt = Integer.parseInt(df.format(hour));
                System.out.println("==="+hourInt);
                for (int i = 0; i <= hourInt; i++) {
                    Map<String, Integer> hourMap = tbCarMapper.hourRatio(speedDTO, i);
                    map.put(i, hourMap);
                }
        }


        System.out.println("=="+cameraGunListCars.size());
        SpeedVO car = new SpeedVO();
        if(cameraGunListCars.size() > 0)
        {
            car = cameraGunListCars.get(0);
        }else {
            car = null;
        }

        if (speedDTO.getChannelId() != null) {

            Map<String, Integer> stringIntegerMap = tbCarMapper.oneDayRatio(speedDTO);
            return RStatic.ok("查询成功").data("car", car).data("hourRatio", map).data("dayRatio", stringIntegerMap);

        } else {
            return RStatic.ok("查询成功").data("car", car).data("hourRatio", map);

        }
    }

    @Override
    public RStatic export(SpeedDTO speedDTO) {

        try {
            List<SpeedVO> cameraGunListCars = new ArrayList<>();
            List<SpeedVO> listCar = new ArrayList<>();
            SpeedVO carVo = new SpeedVO();
            Map<String, Integer> stringIntegerMap = new HashMap<>();

            if (speedDTO.getEndTime() != null) {

                cameraGunListCars = tbCarMapper.exportMoreDays(speedDTO);
            } else {
                cameraGunListCars = tbCarMapper.exportOneDay(speedDTO);
            }
            return RStatic.ok("查询成功").data("cars",cameraGunListCars);
        }catch (Exception e) {

            return RStatic.error("错误原因："+e.getMessage());
        }
    }

    public static long getDaySub(String beginDateStr, String endDateStr) {

        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }


}