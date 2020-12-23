package com.car.util;

import com.car.entity.bean.OneImg;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 王进之
 * @since 2020/12/19
 **/
@Slf4j
public class FileUtil {

    public static void main(String[] args) {
        getAllDir("/home/lxl").forEach(System.out::println);
    }

    //获取文件夹下的文件夹
    public static List<String> getAllDir(String path){
        File file = new File(path);
        if (file.exists()){
            File[] files = file.listFiles((f, n) -> {
                return f.isDirectory();
            });
            List<String> list = Arrays.stream(files).map(e->e.getName()+"/").collect(Collectors.toList());
            return list;
        }
        return null;
    }

    public static void getAllFile(File file,List<OneImg> list){
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                getAllFile(listFile,list);
            }else {
                list.add(new OneImg("file",listFile.getAbsolutePath(),listFile.getName()));
            }
        }
    }

    //文件路径，结果列表，匹配文件夹名称的表达式，匹配文件的表达式
    public static void getAllMatchFile(File file,List<OneImg> list,List<String> matchDir,List<String> matchFile){
        if (!file.exists()){
            log.info(file.getName()+":不存在");
            return;
        }
        if (! file.isDirectory()){
            if (matchStr(file.getName(),matchFile)) {
                list.add(new OneImg("file", file.getAbsolutePath(),file.getName()));
            }
            return;
        }

        for (File listFile : file.listFiles()) {
            getAllMatchFile(listFile, list, matchDir, matchFile);
        }
    }

    public static boolean matchStr(String fileName,List<String> matchStr){
        for (String match : matchStr) {
            if (fileName.contains(match)){
                return false;
            }
        }
        return true;
    }

    public static boolean match(String fileName,List<String> matchStr){
        for (String match : matchStr) {
            Pattern pattern = RuntimeDataUtil.matchPattern.get(match);
            if (null == pattern){
                pattern = Pattern.compile(match);
                RuntimeDataUtil.matchPattern.put(match,pattern);
            }
            if (! pattern.matcher(fileName).find()){
                return false;
            }
        }
        return true;
    }
}
