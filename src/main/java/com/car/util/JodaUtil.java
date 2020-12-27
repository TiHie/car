package com.car.util;

import org.joda.time.DateTime;


import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class JodaUtil {
    //按照时分秒格式获取日期对象
    public static Date getFormatDate(String date,String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        TemporalAccessor parse = dateTimeFormatter.parse(date);
        DateTime dateTime = new DateTime(parse);
        return dateTime.toDate();
    }


    public static void main(String[] args) {
        Date yyyyMMdd = getFormatDate("20200102", "yyyyMMdd");
        System.out.println(yyyyMMdd.getTime());
    }
}
