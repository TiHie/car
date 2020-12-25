package com.car.service.impl;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.service.MatchService;
import com.car.util.DateUtil;
import com.car.util.RuntimeDataUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MatchLXLServiceImpl implements MatchService {

    @Override
    public TbCarEntity match(String fileName, String regStr, List<String> regGroup2EntityField) {

        return null;
    }

    @Override
    public TbCarEntity match(String name, int gunId) {
        name = name.split("\\.")[0];
        //获取摄像枪对象
        TbCameraGunEntity tbCameraGunEntity = RuntimeDataUtil.cameraGunEntityMap.get(gunId);
        //获取表达式，获取分隔符
        String rule = tbCameraGunEntity.getRule();
        String split = tbCameraGunEntity.getSplitStr();
        String[] objectProperties = rule.split(split);
        String[] splitData = name.split(split);
        Map<String,String> dataMap = new HashMap<>();
        for (int i = 0; i < objectProperties.length; i++) {
            //解析是否有连体的，连体的需要正则表达式解析
            if (objectProperties[i].contains("#")){
                //缓存并解析正则表达式
                String[] props = objectProperties[i].split("#");
                for (String prop : props) {
                    String[] dt = prop.split(":");
                    String keyName = dt[0];
                    String pattern = dt[1];
                    //检查是否有缓存对象
                    Pattern ptn = RuntimeDataUtil.patternMap.get(gunId + ":" + pattern);
                    if(null == ptn){//没有缓存
                        ptn = Pattern.compile(pattern);
                        RuntimeDataUtil.patternMap.put(gunId+":"+pattern,ptn);
                    }
                    Matcher matcher = ptn.matcher(splitData[i]);
                    String data = null;
                    if (matcher.find()) {
                        data = matcher.group();
                    }
                    dataMap.put(keyName,data);
                }
            }else {
                dataMap.put(objectProperties[i],splitData[i]);
            }
        }
        //组装 bean 对象
        //shootingTime : 拍摄时间
        //carColor     : 车辆颜色
        //carPlate     : 车牌号
        //channelName  : 通道名称
        //carSpeed     : 车速
        TbCarEntity tbCarEntity = new TbCarEntity();
        if (null != dataMap.get("carSpeed")) {
            tbCarEntity.setSpeed(Integer.valueOf(dataMap.get("carSpeed")));
        }
        tbCarEntity.setLicensePlate(dataMap.get("carPlate"));
        tbCarEntity.setLicensePlateColor(dataMap.get("carColor"));
        if (null != dataMap.get("shootingTime")) {
            Date date = DateUtil.strParseData("yyyyMMddHHmmss", dataMap.get("shootingTime"));
            tbCarEntity.setHourTime(date.getHours());
            tbCarEntity.setShootingTime(date);
            String today = dataMap.get("shootingTime").substring(0,8)+"000000";
            Date todayDay = RuntimeDataUtil.dateMap.get(today);
            if (null == todayDay){
                todayDay = DateUtil.strParseData("yyyyMMddHHmmss",today);
                RuntimeDataUtil.dateMap.put(today,todayDay);
            }
            tbCarEntity.setShootingDate(todayDay);
        }
        return tbCarEntity;
    }

}
