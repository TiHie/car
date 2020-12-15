package com.car.controller;

import com.car.entity.TbCarEntity;
import com.car.service.MatchService;
import com.car.service.TbRegexCodeService;
import com.car.util.RStatic;
import com.car.util.RegRule2EntityFieldMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wjz
 * @date 2020/12/15
 */
@Api(tags = "正则处理demo")
@RestController
@CrossOrigin
public class RegexController {
    @Autowired
    @Qualifier(value = "MatchWJZServiceImpl")
    MatchService matchWJZServiceImpl;
    private static Map<String,String> mockRegDb;

//"(\\d{14})_([\\u4e00-\\u9fa5])_([\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})_([\\u4e00-\\u9fa5]{2,}\\d{1,3})";
    static {
        mockRegDb = new HashMap<String,String>() {
            {
                put("抓拍时间","(\\d{14})");
                put("车牌颜色","([\\u4e00-\\u9fa5])");
                put("车牌名称","([\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})");
                put("通道名称车速","([\\u4e00-\\u9fa5]{2,}\\d{1,3})");
                put("通道名称","([\\u4e00-\\u9fa5]{2,})");
                put("车速","\\d{1,3})");
            }
        };
    }
    //传入-->抓拍时间_车牌颜色_车牌名称_通道名称车速
    @ApiOperation("传入：抓拍时间_车牌颜色_车牌名称_通道名称车速")
    @GetMapping("/api/regexdemo/{regStr}")
    public RStatic regexDemo(@PathVariable("regStr") String regStr){
        String finalRegStr = ""; //service要传的字段
        List<String> regGroup2EntityField = new ArrayList<>();//service要传的字段
        regGroup2EntityField.add("");//第一个元素为空串

        String[] regs = regStr.split("_");
        for (String reg : regs) {
            finalRegStr += mockRegDb.get(reg)+"_";
            regGroup2EntityField.add(RegRule2EntityFieldMapper.getEntityFieldName(reg));
        }
        finalRegStr = finalRegStr.substring(0,finalRegStr.length()-1);
        System.out.println(finalRegStr);
        System.out.println(regGroup2EntityField.toString());

        // TODO: 2020/12/15 wjz文件名解析器入口：第一个参数是要解析的文件名（动态传入），针对同一个通道的文件，后两个参数只需要构造一次；这里的时间是2020年12月1日，日期解析出来是11月 
        TbCarEntity match = matchWJZServiceImpl.match("20201201010203_蓝_粤B00352_卡口车速65.jpg", finalRegStr, regGroup2EntityField);
        if(match == null){
            System.out.println("match is null");
        }else {
            System.out.println(match.getShootingTime());
        }
        System.out.println(match.getHourTime());
        System.out.println(match.getLicensePlate());
        return RStatic.ok("ok").data("match",match);
    }
}
