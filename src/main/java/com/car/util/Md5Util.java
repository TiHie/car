package com.car.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @author mowuwalixilo
 * @date2020/12/16 22:23
 */
public class Md5Util {

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String MD5Hax(String str) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bs = md5.digest(str.getBytes());
        return new String(new Hex().encode(bs));
    }
}
