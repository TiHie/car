package com.car.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//"yyyyMMddHHmmss"
public class DateUtil {

    //处理时间
    public static Date strParseData(String format, String dateStr){
        if (null == dateStr){
            return null;
        }
        //2020 10 22 15 05 39
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            //仅做本地测试使用
            e.printStackTrace();
            return new Date();//解析失败，返回当前时间
        }
        return parse;
    }
}
