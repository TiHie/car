package com.car.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
            return new Date();//解析失败，返回当前时间
        }
        return parse;
    }

    //获取这个时间到下一日 0 点的 ms 数
    public static long getRemainTime() {
        long zero = System.currentTimeMillis()- ( System.currentTimeMillis()+ TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        long oneDay = 24*60*60*1000;
        return zero+oneDay-System.currentTimeMillis();
    }

    //获取当日文件夹名
    public static String getTodayStr(){
        Date date = new Date();
        String format = "yyyy年MM月dd日";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String result = simpleDateFormat.format(date);
        return result;
    }

    public static Date getTodayDate() throws ParseException {
        String todayStr = getTodayStr();
        String format = "yyyy年MM月dd日";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date parse = simpleDateFormat.parse(todayStr);
        return parse;
    }

    //根据特定格式获取日期处理结果()
    public static String getFormatResult(String format){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String result = simpleDateFormat.format(date);
        return result;
    }

    //获取当日日期 (正则)
    public static String getTodayMatchStr(){
        Date date = new Date();
        String day = RuntimeDataUtil.formatDay.format(date);
        String month = RuntimeDataUtil.formatMonth.format(date);
        String year = RuntimeDataUtil.formatYear.format(date);
        //组装正则表达式
        String result = year+"年"
                +(month.length()==1?"[0-9]*"+month:month)+"月"
                +(day.length()==1?"[0-9]*"+day:day)+"日";
        return result;
    }
}
