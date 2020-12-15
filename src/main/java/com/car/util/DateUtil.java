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
            System.out.println(dateStr);
            parse = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    public static void main(String[] args) {
        Date yyyyMMddHHmmss = strParseData("yyyyMMddHHmmss", "20201022150539");
        System.out.println(yyyyMMddHHmmss.getHours());
    }
}
