package com.car.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @Author FDH
 * @Date 2020/12/17 0:29
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpeedVO {
//    private Map<String, Integer> map;
    private Map<Integer,Map<String, Integer>> hourMap;
}
