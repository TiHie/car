package com.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.entity.TbCarEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.dto.SpeedDTO;
import com.car.entity.vo.SpeedVO;
import com.car.mapper.TbCarMapper;
import com.car.service.TbCameraGunService;
import com.car.service.TbCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.car.service.TbChannelService;
import com.car.util.RStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            speedDTO.setPage((speedDTO.getPage()-1)*speedDTO.getItems());
            TbChannelEntity channel = new TbChannelEntity();
            if (speedDTO.getChannelId() != null) {
                QueryWrapper<TbChannelEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("id", speedDTO.getChannelId());
                channel = tbChannelService.getOne(wrapper);
            }
            List<SpeedVO> cameraGunListCars = new ArrayList<>();
            List<SpeedVO> listCar = new ArrayList<>();
            SpeedVO carVo = new SpeedVO();
            Map<String, Integer> stringIntegerMap = new HashMap<>();


            if (speedDTO.getEndTime() != null) {

                cameraGunListCars = tbCarMapper.getMoreDayCars(speedDTO);
                stringIntegerMap = tbCarMapper.moreDayRatio(speedDTO);
            } else {
                cameraGunListCars = tbCarMapper.getOneDayCars(speedDTO);
                stringIntegerMap = tbCarMapper.oneDayRatio(speedDTO);
            }


            //List<SpeedVO> carPage = cameraGunListCars.stream().skip((speedDTO.getPage() - 1) * speedDTO.getItems()).limit(speedDTO.getItems()).collect(Collectors.toList());

            if (speedDTO.getChannelId() != null) {

                return RStatic.ok("查询成功").data("channelName", channel.getName()).data("carPage", cameraGunListCars).data("dayRatio", stringIntegerMap).data("page", speedDTO.getPage()).data("items", speedDTO.getItems());
            } else {
                return RStatic.ok("查询成功").data("carPage", cameraGunListCars).data("dayRatio", stringIntegerMap).data("page", speedDTO.getPage()).data("items", speedDTO.getItems());
            }
        } catch (Exception e) {
            System.out.println("错误原因：" + e.getMessage());
        }
        return RStatic.error("错误");
    }

    @Override
    public RStatic channelOneCarData(SpeedDTO speedDTO) {

        //speedDTO.setPage((speedDTO.getPage()-1)*speedDTO.getItems());
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        Date hour = new Date();
        SimpleDateFormat df = new SimpleDateFormat("HH");
        Integer hourInt;
        if (speedDTO.getStartTime() != null && speedDTO.getChannelId() != null) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String startTimeStr = formatter.format(speedDTO.getStartTime());
            long day = getDaySub(startTimeStr, formatter.format(new Date()));
            if (day ==0) {
                hourInt = Integer.parseInt(df.format(hour));
                System.out.println("==="+hourInt);
                for (int i = 0; i <= hourInt; i++) {
                    Map<String, Integer> hourMap = tbCarMapper.hourRatio(speedDTO, i);
                    map.put(i, hourMap);
                }
            }else if (day >= 1) {
                hourInt = 24;
                for (int i = 0; i < hourInt; i++) {
                    Map<String, Integer> hourMap = tbCarMapper.hourRatio(speedDTO, i);
                    System.out.println("=="+hourMap.values());
                    map.put(i, hourMap);
                }
            }
        }

        List<SpeedVO> cameraGunListCars = tbCarMapper.getOneDayCars(speedDTO);
        System.out.println("=="+cameraGunListCars.size());
        SpeedVO car = new SpeedVO();
        if(cameraGunListCars.size() > 0)
        {
            car = cameraGunListCars.get(0);
        }else {
            car = null;
        }

        if (speedDTO.getStartTime() != null && speedDTO.getChannelId() != null) {
            Map<String, Integer> stringIntegerMap = tbCarMapper.oneDayRatio(speedDTO);
            return RStatic.ok("查询成功").data("car", car).data("hourRatio", map).data("dayRatio", stringIntegerMap);

        } else {
            return RStatic.ok("查询成功").data("car", car).data("hourRatio", map);

        }
    }

//    @Override
//    public XSSFWorkbook export(SpeedDTO speedDTO) {
//
//        try {
//            List<SpeedVO> cameraGunListCars = new ArrayList<>();
//            List<SpeedVO> listCar = new ArrayList<>();
//            SpeedVO carVo = new SpeedVO();
//            Map<String, Integer> stringIntegerMap = new HashMap<>();
//
//            if (speedDTO.getEndTime() != null) {
//
//                cameraGunListCars = tbCarMapper.exportMoreDays(speedDTO);
//            } else {
//                cameraGunListCars = tbCarMapper.exportOneDay(speedDTO);
//            }
//            return exportCar(cameraGunListCars);
//        }catch (Exception e) {
//            System.out.println("错误原因："+e.getMessage());
//        }
//        return null;
//    }
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

//    public static XSSFWorkbook exportCar(List<SpeedVO> cars){
//        XSSFWorkbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("Cars");
//        Row titleRow = sheet.createRow(0);
//        titleRow.createCell(0).setCellValue("序号");
//        titleRow.createCell(1).setCellValue("通道名");
//        titleRow.createCell(2).setCellValue("拍摄日期");
//        titleRow.createCell(3).setCellValue("车牌");
//        titleRow.createCell(4).setCellValue("车牌颜色");
//        titleRow.createCell(5).setCellValue("状态");
//        titleRow.createCell(6).setCellValue("车速");
//        titleRow.createCell(7).setCellValue("限速");
//        titleRow.createCell(8).setCellValue("摄像枪类型");
//        titleRow.createCell(9).setCellValue("摄像枪地点");
//        titleRow.createCell(10).setCellValue("图片路径");
//        int cell = 1;
//        for (SpeedVO car : cars) {
//            Row row = sheet.createRow(cell);
//            row.createCell(0).setCellValue(cell);
//            row.createCell(1).setCellValue(car.getChannelName());
//            row.createCell(2).setCellValue(car.getShootingTime());
//            row.createCell(3).setCellValue(car.getLicensePlate());
//            row.createCell(4).setCellValue(car.getLicensePlateColor());
//            row.createCell(5).setCellValue(car.getStatus());
//            row.createCell(6).setCellValue(car.getCarSpeed());
//            row.createCell(7).setCellValue(car.getChannelSpeed());
//            row.createCell(8).setCellValue(car.getCameraGunName());
//            row.createCell(9).setCellValue(car.getCameraGunLocation());
//            row.createCell(10).setCellValue(car.getCarImage());
//            cell++;
//        }
//        return wb;
//
//    }
}