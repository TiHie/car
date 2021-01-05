package com.car.util;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.TbChannelEntity;
import com.car.entity.bean.OneSpeed;

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
    //tbRegex数据：k:regexName, v:正则表达式
    public static Map<String,String> regexCodeCache = null;
    //摄像枪数据 k:id v:摄像枪信息
    public static Map<Integer, TbCameraGunEntity> cameraGunEntityMap = null;
    //今天的日期
    public static String today = null;
    //存储正则表达式 k:gunId:pattern  v:正则表达式对象
    public static Map<String, Pattern> patternMap = new ConcurrentHashMap<>();
    //缓存当日0点的时间戳 k:yyyyMMdd000000  v:当日o时时间戳
    public static Map<String, Date> dateMap = new ConcurrentHashMap<>();
    //缓存摄像枪与速度 k:摄像枪id  v:限制速度
    public static Map<Integer, OneSpeed> speedMap = null;

    //日期格式化对象
    public static SimpleDateFormat formatDay = new SimpleDateFormat("d");
    public static SimpleDateFormat formatMonth = new SimpleDateFormat("M");
    public static SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
    
    //系统运行环境
    public static String environment = null;
    //系统连接符
    public static String connectStr = null;

    //缓存的一些正则表达式，用于匹配文件与目录
    public static Map<String,Pattern> matchPattern = new ConcurrentHashMap<>();

    //缓存角色与用户的映射
    public static Map<String,String> roleMap = new ConcurrentHashMap<>();
}
