package com.car.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.car.entity.TbCarEntity;
import com.car.entity.TbRegexCodeEntity;
import com.car.exception.BizException;
import com.car.service.impl.TbRegexCodeServiceImpl;
import com.car.util.RegRule2EntityFieldMapper;
import com.car.util.RuntimeDataUtil;
import com.car.util.WirePropertyToObjUtil;
import lombok.SneakyThrows;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public interface MatchService {
    TbCarEntity match(String fileName, String regStr, List<String> regGroup2EntityField);
    TbCarEntity match(String name,int gunId);

    /**
     * 性能更高更加简洁的文件解析解决方案
     * 思路：方法传入的中文解析规则，
     * 首先使用默认分隔符"_"分割正则字段，
     * 然后去regex缓存里查找对应字段的解析正则表达式，
     * 最后按照中文正则字段对应的正则表达式将英文正则表达式拼接起来用于匹配文件名
     *
     * @param fileName 20201201010203_蓝_粤B00352_卡口车速65.jpg
     * @param regexPatternInChinese 抓拍时间_车牌颜色_车牌名称_通道名称车速
     * @return TbCarEntity
     */
    @SneakyThrows
    default TbCarEntity match(String fileName, String regexPatternInChinese) {
        Assert.notEmpty(Collections.singleton(fileName),"文件名解析-文件名不能为空");
        Assert.notEmpty(Collections.singleton(regexPatternInChinese),"文件名解析-中文正则不能为空");
        TbCarEntity tbCarEntity = new TbCarEntity();
        StringBuffer regexStringSB = new StringBuffer();
        List<String> regexCodeListToWire = new ArrayList<>();
        String defaultSpliter = "_";
        //解析中文正则，记录下顺序
        List<String> splitedChineseRegexCode = Arrays.asList(regexPatternInChinese.split(defaultSpliter));
        //去cache查找英文正则，若不存在则去db查找并加入cache，若db查找不到，则抛出异常
        splitedChineseRegexCode.forEach(e -> {
            String regexCodeInEnglish = RuntimeDataUtil.regexCodeCache.get(e);
            System.out.println("regeCodeInEnglish:"+regexCodeInEnglish);
            if(StringUtils.isEmpty(regexCodeInEnglish)){
                TbRegexCodeEntity notCacheRegex = new TbRegexCodeServiceImpl().getOne(new QueryWrapper<TbRegexCodeEntity>().eq("regexName", e));
                if(notCacheRegex == null){
                    throw new BizException("系统无此文件匹配字段");
                }
                RuntimeDataUtil.regexCodeCache.put(e,notCacheRegex.getRegexCode());
                regexCodeInEnglish = notCacheRegex.getRegexCode();
            }
            regexStringSB.append(regexCodeInEnglish).append(defaultSpliter);
            regexCodeListToWire.add(RegRule2EntityFieldMapper.getEntityFieldName(e));
        });

        //将查找到的正则按照第一步记录的顺序进行拼接
        System.out.println("文件名匹配服务-文件名："+fileName+",中文正则表达式："+regexStringSB.toString().substring(0, regexStringSB.toString().length() - 1));
        Pattern fileNameCompiler = compile(regexStringSB.toString().substring(0, regexStringSB.toString().length() - 1));
        Matcher fileNameMatcher = fileNameCompiler.matcher(fileName);
        if (fileNameMatcher.find()){
            //matcher.group(0)为全文件名，因此group的索引 = index of splitedChineseRegexCode + 1
            for(int i = 0;i<regexCodeListToWire.size(); i++){
                System.out.println("英文正则字段元素："+regexCodeListToWire.get(i));
                //拦截处理channelNameAndSpeed,会同时为channelName与speed赋值
                if("channelNameAndSpeed".equalsIgnoreCase(regexCodeListToWire.get(i))){
                    System.out.println("找到通道速度一体字段");
                   tbCarEntity.setChannelNameAndSpeed(fileNameMatcher.group(i+1));
                    continue;
                }
                //拦截处理日期，防止反射日期失败(会同时设置三个日期...+hourTime)
                if("shootingTime".equalsIgnoreCase(regexCodeListToWire.get(i))){
                    tbCarEntity.setShootingTime(fileNameMatcher.group(i+1));
                    continue;
                }
                //使用英文正则匹配出文件名数据（
                //      这里有个中文字段名到英文字段名映射的问题,
                //          解决方案：采用默认映射类实现中文到英文字段的映射；
                //      不过还有个问题，系统固定了中文字段到英文属性的映射，无法实现用户自定义，
                //          待实现解决方案：在regexCodeDb或者缓存中添加字段属性映射，将用户自定义的字段与系统默认6个字段进行映射，即可实现用户自定义动态属性）
                System.out.println("属性注入日志：被注入的字段："+regexCodeListToWire.get(i)+"被注入的数据："+fileNameMatcher.group(i+1));
                if(regexCodeListToWire.get(i) == null){
                    System.out.println("---------------error---------：注入属性为空，regexCodeListToWire索引为："+i+"；regexCodeListToWire容量为："+regexCodeListToWire.size());
                    continue;

                }
                WirePropertyToObjUtil.wire(tbCarEntity, regexCodeListToWire.get(i),fileNameMatcher.group(i+1));
            }
        }else{
            throw new BizException("文件名与摄像枪解析规则不匹配,请注意车牌号合法格式为：一个汉字+一个字母+5个数字");
        }

        //验证：组装对象：shootingTime,shootingDate,licensePlate,licensePlateColor,speed,hourTime
        System.out.println("文件名解析验证----");
        System.out.println(tbCarEntity.getShootingTime().toString());
        System.out.println(tbCarEntity.getLicensePlate());
        System.out.println(tbCarEntity.getSpeed());
        System.out.println(tbCarEntity.getLicensePlateColor());
        System.out.println(tbCarEntity.getShootingDate().toString());
        System.out.println();
        return tbCarEntity;
    }
}
