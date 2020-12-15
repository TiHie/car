package com.car.util;

import com.car.entity.TbCameraGunEntity;
import com.car.entity.bean.OneSpeed;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 在程序初始化时候，要添加的数据
 * 这里的数据全局共享
 */
public class RuntimeDataUtil {
    public static Map<Integer, TbCameraGunEntity> cameraGunEntityMap = null;
    public static String today = null;
    public static Map<String, Pattern> patternMap = new ConcurrentHashMap<>();
    public static Map<String, Date> dateMap = new ConcurrentHashMap<>();
    public static Map<Integer, Integer> speedMap = null;
}
