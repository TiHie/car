package com.car.util;

import com.car.entity.bean.OneImg;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author 王进之
 * @since 2020/12/19
 **/
public class FileUtil {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\glhtj\\Desktop");
        List<OneImg> oneImgs = new ArrayList<>();
        getAllMatchFile(file,oneImgs,
                Arrays.asList("a"),
                Arrays.asList("b"));
        oneImgs.forEach(System.out::println);
    }

    public static void getAllFile(File file,List<OneImg> list){
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                getAllFile(listFile,list);
            }else {
                list.add(new OneImg("file",listFile.getAbsolutePath()));
            }
        }
    }

    //文件路径，结果列表，匹配文件夹名称的表达式，匹配文件的表达式
    public static void getAllMatchFile(File file,List<OneImg> list,List<String> matchDir,List<String> matchFile){
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                if (match(listFile.getName(),matchDir)) {
                    getAllFile(listFile, list);
                }
            }else {
                if (match(listFile.getName(),matchFile)) {
                    list.add(new OneImg("file", listFile.getAbsolutePath()));
                }
            }
        }
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
