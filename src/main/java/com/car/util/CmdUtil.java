package com.car.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CmdUtil {

    //获取命令行执行对象
    public static Runtime runTime() {
        return Runtime.getRuntime();
    }

    public static Process runCmd(String cmd) {
        try {
            return Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getStringResult(Process process) {
        String result = "";
        try (
                InputStream inputStream = process.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String read = null;
            while (null != (read = bufferedReader.readLine())) {
                result+=read;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //带有参数运行 cmd
    public static String runParamCmd(String cmd,String... params){
        for (String param : params) {
            cmd += " "+param;
        }
        return getStringResult(runCmd(cmd));
    }

    public static String runParamCmdSpecial(List<String> cmd){
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        try {
            return getStringResult(processBuilder.start());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
