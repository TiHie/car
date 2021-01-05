package com.car.service.impl;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.exception.BizException;
import com.car.service.MatchService;
import com.car.service.TbCameraGunService;
import com.car.util.WirePropertyToObjUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
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

import static java.util.regex.Pattern.*;

/**
 * @author wjz
 * @date 2020/12/15
 */
@Service("MatchWJZServiceImpl")
@Slf4j
public class MatchWJZServiceImpl implements MatchService {



    @Autowired
    TbCameraGunService tbCameraGunService;


    public static void main(String[] args) {
        TbCarEntity match = new MatchWJZServiceImpl().match(null,null,null);
    }



    /**
     * 中文解析器demo版本，已弃用
     * 新方法:
     * @see MatchService#match(java.lang.String, java.lang.String)
     * @param fileName 传入文件名：20201201010203_蓝_粤B00352_卡口车速65.jpg
     * @param regStr  传入从sql中查询出拼出的正则表达式：(\d{14})_([\u4e00-\u9fa5])_([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})_([\u4e00-\u9fa5]{2,}\d{1,3})
     * @param regGroup2EntityField 传入正则捕获组对应实体list:["","shootingTime","licensePlateColor","licensePlate","channelNameAndSpeed"];
     * @return
     */
    @SneakyThrows
    @Override
    @SuppressWarnings("中文解析器demo版本，已弃用，新方法：@see MatchService#match(java.lang.String, java.lang.String)")
    public TbCarEntity match(String fileName,String regStr,List<String> regGroup2EntityField) {
        final DateTimeFormatter timeFormat = DateTimeFormat.forPattern("yyyyMMddHHmmss");
        final DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyyMMdd");

        TbCarEntity tbCarEntity = new TbCarEntity();
        Pattern compile = compile(regStr);
        Matcher matcher = compile.matcher(fileName);
        while (matcher.find()){
            for(int i = 1; i < regGroup2EntityField.size();i++){
                if(regGroup2EntityField.get(i).contains("Time") || regGroup2EntityField.contains("Date")){
                    String dateTimeStr = matcher.group(i);
                    Date shootingTime = timeFormat.parseDateTime(dateTimeStr).toDate();
                    Date shootingDate = dateFormat.parseDateTime(dateTimeStr.substring(0, 8)).toDate();
                    Integer hourTime = Integer.parseInt(dateTimeStr.substring(8,10));
                    tbCarEntity = (TbCarEntity) WirePropertyToObjUtil.wire(tbCarEntity, regGroup2EntityField.get(i),shootingTime);
                    WirePropertyToObjUtil.wire(tbCarEntity,"shootingDate",shootingDate);
                    WirePropertyToObjUtil.wire(tbCarEntity,"hourTime",hourTime);
                    continue;
                }
                tbCarEntity = (TbCarEntity) WirePropertyToObjUtil.wire(tbCarEntity, regGroup2EntityField.get(i), matcher.group(i));
            }
        }

        if(regGroup2EntityField.contains("channelNameAndSpeed")){
            String regStr2SplitSpeed = "([\\u4e00-\\u9fa5]{2,})(\\d{1,3})";
            Matcher matcher1 = compile(regStr2SplitSpeed).matcher(tbCarEntity.getChannelNameAndSpeed());
            if(matcher1.find()){
                String channelName = matcher1.group(1);
                String speed = matcher1.group(2);
                tbCarEntity.setSpeed(Integer.parseInt(speed));
                tbCarEntity.setChannelName(channelName);
            }
        }
        return tbCarEntity;
    }

    /**
     * 按照通道名与文件名进行匹配
     * @param name 文件名
     * @param gunId 通道id
     * @return
     */
    @Override
    public TbCarEntity match(String name, int gunId) {
        TbCameraGunEntity byId = tbCameraGunService.getById(gunId);
        System.out.println(byId);
        String regexStrInChinese = byId.getRule();
        if(compile("([\\u4e00-\\u9fa5]{2,})").matcher(regexStrInChinese).find()) {
            //合法规则(目前主流规则为lxl解析器的英文规则，此处判断是否为正则的中文规则)
            return this.match(name,regexStrInChinese);
        }else {
            throw new BizException("匹配到当前通道使用的是lxl解析器，非中文解析器");
        }
    }
}
