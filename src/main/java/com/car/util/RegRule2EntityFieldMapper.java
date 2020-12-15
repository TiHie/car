package com.car.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wjz
 * @date 2020/12/15
 */
public class RegRule2EntityFieldMapper {
    final private static Map<String,String> reg2EntityField = new HashMap<String, String>(){
        {
            put("抓拍时间","shootingTime");
            put("车牌颜色","licensePlateColor");//抓拍时间、车牌颜色、车牌名称、通道名称车速、通道名称、车速，
            put("车牌名称","licensePlate");
            put("通道名称车速","channelNameAndSpeed");
            put("通道","channelName");
            put("车速","speed");
        }
    };
    public static String getEntityFieldName(String regField){
        return reg2EntityField.get(regField);
    }
}
