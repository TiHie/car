package com.car.util;

import org.joda.time.format.DateTimeFormat;

import java.lang.reflect.Field;

/**
 * @author wjz
 * @date 2020/12/15
 */
public class WirePropertyToObjUtil {
    //参数obj：要 注入的对象  properName：属性名   value：属性值
    public static Object wire(Object obj, String properName, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        //获取该类的类型
        Class<? extends Object> clazz = obj.getClass();
        //获取对象的属性
        System.out.println("注射获取属性："+properName);
        Field field_name = clazz.getDeclaredField(properName);
        //开启访问权限
        field_name.setAccessible(true);
        Class<?> type = field_name.getType();
        System.out.println("反射工具类，当前解析字段类名为："+type.getName());
        if(type.getName().contains("Date")){
            value = DateTimeFormat.forPattern("yyyyMMddHHmmss").parseDateTime((String) value).toDate();
            System.out.println("反射工具类，修改反射时间："+value.toString());
        }
        if(type.getName().contains("Integer") || type.getName().contains("int")){
            value = Integer.parseInt((String) value);
        }
        //为该属性注入值
        field_name.set(obj, value);
        if (type.getName().contains("Date")){
            System.out.println("反射时间类设置值为："+field_name.get(obj));
        }
        //返回该对象
        return obj;
    }

}
