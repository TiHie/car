package com.car.service.impl;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbCarEntity;
import com.car.entity.TbRegexCodeEntity;
import com.car.service.TbCameraGunService;
import com.car.service.TbRegexCodeService;
import com.car.util.RuntimeDataUtil;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class TbRegexCodeServiceImplTest {

    @Test
    public void newFileNameMatcherTest(){
//        TbRegexCodeService tbRegexCodeService = new TbRegexCodeServiceImpl();
//        List<TbRegexCodeEntity> regexRuleList = tbRegexCodeService.list();
//        //初始化缓存正则表达式
//        RuntimeDataUtil.regexCodeCache = new ConcurrentHashMap<>(regexRuleList.stream().collect(Collectors.toConcurrentMap(e -> e.getRegexName(), e -> e.getRegexCode())));
//
//        //20201201010203_蓝_粤B00352_卡口车速65.jpg
//        //     * @param regexPatternInChinese 抓拍时间_车牌颜色_车牌名称_通道名称车速
//        TbCarEntity tbCarEntity = new MatchWJZServiceImpl().match("20201201010203_蓝_粤B00352_卡口车速65.jpg", "抓拍时间_车牌颜色_车牌名称_通道名称车速");
//        System.out.println(tbCarEntity.toString());
//        System.out.println(DateTime.now().toString("yyyy-MM-dd"));
    }
}