package com.car.service.impl;

import com.car.entity.TbCarEntity;
import com.car.service.MatchService;
import com.car.util.WirePropertyToObjUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wjz
 * @date 2020/12/15
 */
@Service("MatchWJZServiceImpl")
@Slf4j
public class MatchWJZServiceImpl implements MatchService {

    public static void main(String[] args) {
        TbCarEntity match = new MatchWJZServiceImpl().match(null,null,null);
    }

    /**
     * @param fileName 传入文件名：20201201010203_蓝_粤B00352_卡口车速65.jpg
     * @param regStr  传入从sql中查询出拼出的正则表达式：(\d{14})_([\u4e00-\u9fa5])_([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})_([\u4e00-\u9fa5]{2,}\d{1,3})
     * @param regGroup2EntityField 传入正则捕获组对应实体list:["","shootingTime","licensePlateColor","licensePlate","channelNameAndSpeed"];
     * @return
     */
    @SneakyThrows
    @Override
    public TbCarEntity match(String fileName,String regStr,List<String> regGroup2EntityField) {
        DateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        TbCarEntity tbCarEntity = new TbCarEntity();
//        String fileName = "20201201010203_蓝_粤B00352_卡口车速65.jpg";
//        String regStr = "(\\d{14})_([\\u4e00-\\u9fa5])_([\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})_([\\u4e00-\\u9fa5]{2,}\\d{1,3})";
//        List<String> regGroup2EntityField = new ArrayList<String>() {
//            {
//                add("");
//                add("shootingTime");
//                add("licensePlateColor");
//                add("licensePlate");
//                add("channelNameAndSpeed");
//            }
//        };
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(fileName);
        while (matcher.find()){
            for(int i = 1; i < regGroup2EntityField.size();i++){
                if(regGroup2EntityField.get(i).contains("Time") || regGroup2EntityField.contains("Date")){
                    String dateTimeStr = matcher.group(i);
                    Date shootingTime = timeFormat.parse(dateTimeStr);
                    Date shootingDate = dateFormat.parse(dateTimeStr.substring(0, 8));
                    Integer hourTime = Integer.parseInt(dateTimeStr.substring(8,10));
//                    System.out.println(df.format(parse));
                    tbCarEntity = (TbCarEntity) WirePropertyToObjUtil.wire(tbCarEntity, regGroup2EntityField.get(i),shootingTime);
                    WirePropertyToObjUtil.wire(tbCarEntity,"shootingDate",shootingDate);
                    WirePropertyToObjUtil.wire(tbCarEntity,"hourTime",hourTime);
                    continue;
                }
                tbCarEntity = (TbCarEntity) WirePropertyToObjUtil.wire(tbCarEntity, regGroup2EntityField.get(i), matcher.group(i));
            }
        }

        if(tbCarEntity == null){
            System.out.println("tbcar is null");
        }
        System.out.println(tbCarEntity.getChannelNameAndSpeed());
        if(regGroup2EntityField.contains("channelNameAndSpeed")){
            String regStr2SplitSpeed = "([\\u4e00-\\u9fa5]{2,})(\\d{1,3})";
            Matcher matcher1 = Pattern.compile(regStr2SplitSpeed).matcher(tbCarEntity.getChannelNameAndSpeed());
            if(matcher1.find()){
                String channelName = matcher1.group(1);
                String speed = matcher1.group(2);
                tbCarEntity.setSpeed(Integer.parseInt(speed));
                tbCarEntity.setChannelName(channelName);
            }
        }
        System.out.println(timeFormat.format(tbCarEntity.getShootingTime()));
        System.out.println(tbCarEntity.getLicensePlateColor());
        System.out.println(tbCarEntity.getLicensePlate());
        System.out.println(tbCarEntity.getChannelName());
        System.out.println(tbCarEntity.getSpeed());
        System.out.println(tbCarEntity.getHourTime());
        return tbCarEntity;
    }

    @Override
    public TbCarEntity match(String name, int gunId) {
        return null;
    }
}
