package com.car.util;

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
        Field field_name = clazz.getDeclaredField(properName);
        //开启访问权限
        field_name.setAccessible(true);
        //为该属性注入值
        field_name.set(obj, value);
        //返回该对象
        return obj;
    }
}
