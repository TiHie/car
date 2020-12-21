package com.car.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class TokenUtil {


    public static String encode(String key, Map<String, Object> param, String salt) {
        if (salt != null) {
            key += salt;
        }
        long currentTimeMillis = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder().signWith(SignatureAlgorithm.HS256, key);

        jwtBuilder = jwtBuilder
                //设置签发时间
                .setClaims(param).setIssuedAt(new Date(currentTimeMillis))
                //设置过期时间为7天
                .setExpiration(new Date(currentTimeMillis + Long.valueOf(60*60*24*1000*7)));

        String token = jwtBuilder.compact();
        return token;
    }


    public static Map<String, Object> decode(String token, String key, String salt) {
        Claims claims = null;
        if (salt != null) {
            key += salt;
        }
        try {
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            return null;
        }
        return claims;
    }

    public static boolean checkToken(String token, String key, String salt){
        try {
            Map<String, Object> decode = decode(token, key, salt);
            String expTime = decode.get("exp").toString();
            boolean check = Long.valueOf(expTime) > System.currentTimeMillis()/1000;
            return check;
        }catch (Exception e){
            return false;
        }
    }
}
