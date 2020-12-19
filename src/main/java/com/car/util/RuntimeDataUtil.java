package com.car.util;

import com.car.entity.TbCameraGunEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 在程序初始化时候，要添加的数据
 * 这里的数据全局共享
 */
public class RuntimeDataUtil {
    //摄像枪数据 k:id v:摄像枪信息
    public static Map<Integer, TbCameraGunEntity> cameraGunEntityMap = null;
    //今天的日期
    public static String today = null;
    //存储正则表达式 k:gunId:pattern  v:正则表达式对象
    public static Map<String, Pattern> patternMap = new ConcurrentHashMap<>();
    //缓存当日0点的时间戳 k:yyyyMMdd000000  v:当日o时时间戳
    public static Map<String, Date> dateMap = new ConcurrentHashMap<>();
    //缓存摄像枪与速度 k:摄像枪id  v:限制速度
    public static Map<Integer, Integer> speedMap = null;

    //日期格式化对象
    public static SimpleDateFormat formatDay = new SimpleDateFormat("d");
    public static SimpleDateFormat formatMonth = new SimpleDateFormat("M");
    public static SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");

}
