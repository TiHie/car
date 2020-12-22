package com.car.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王进之
 * @since 2020/12/19
 **/
public class WindowsApiUtil {
    //copy C:\Users\glhtj\Desktop\testSpace\aaa\c.txt C:\Users\glhtj\Desktop\testSpace\ccc && move C:\Users\glhtj\Desktop\testSpace\aaa\c.txt C:\Users\glhtj\Desktop\testSpace\aaa\k.txt
    public static void main(String[] args) {
        System.out.println(
              shell(
                      "copy C:\\Users\\glhtj\\Desktop\\testSpace\\aaa\\c.txt C:\\Users\\glhtj\\Desktop\\testSpace\\ccc && move C:\\Users\\glhtj\\Desktop\\testSpace\\aaa\\c.txt C:\\Users\\glhtj\\Desktop\\testSpace\\aaa\\k.txt"
              )
        );
    }

    public static String shell(String... cmdLines){
        List<String> cmds = new ArrayList<String>();
        cmds.add("cmd");
        cmds.add("/c");
        for (String cmd : cmdLines) {
            cmds.add(cmd);
        }
        return CmdUtil.runParamCmdSpecial(cmds);
    }
}
