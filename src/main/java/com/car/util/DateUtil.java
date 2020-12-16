package com.car.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
            try {
                Random random = new Random();
                int d = random.nextInt(30)+1;
                int h = random.nextInt(24);
                String day = d+"";
                String hour = h+"";
                if (day.length() == 1){
                    day = "0"+day;
                }
                if (hour.length() == 1){
                    hour = "0"+hour;
                }
                parse = simpleDateFormat.parse("202010"+day+hour+"0539");
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        return parse;
    }

    public static void main(String[] args) {
        Date yyyyMMddHHmmss = strParseData("yyyyMMddHHmmss", "20201022150539");
        System.out.println(yyyyMMddHHmmss.getHours());
    }
}
