package com.car.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wjz
 * @date 2021/1/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void setValue() {

        redisTemplate.opsForValue().set("name","tom");
    }

    @Test
    public void getValue() {

        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void deleteValue() {

        redisTemplate.delete("name");
    }



}
