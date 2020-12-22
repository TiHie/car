package com.car.util;

import java.util.ArrayList;
import java.util.List;

public class LinuxApiUtil {
    //tree 列出指定目录文件并过滤结果
    public static String treeDir(String cmd,String... grepParams){
        for (String param : grepParams) {
            cmd+=" | grep "+param;
        }
        List<String> cmds = new ArrayList<String>();
        cmds.add("bash");
        cmds.add("-c");
        cmds.add(cmd);
        return CmdUtil.runParamCmdSpecial(cmds);
    }
    //执行编写的多行命令
    public static String shell(String... cmdLines){
        List<String> cmds = new ArrayList<String>();
        cmds.add("bash");
        cmds.add("-c");
        for (String cmd : cmdLines) {
            cmds.add(cmd);
        }
        return CmdUtil.runParamCmdSpecial(cmds);
    }
    //复制文件到指定目录
    public static void cpFile(String inputPath,String outputPath){
        CmdUtil.runCmd("cp "+inputPath+" "+outputPath);
    }
    //移动文件或修改文件名
    public static void mvFile(String inputPath,String outPutPath){
        CmdUtil.runCmd("mv "+inputPath+" "+outPutPath);
    }
}
