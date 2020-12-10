package top.chenyanjin.robot.lol.util;

import top.chenyanjin.robot.lol.thread.GlobalData;

public class ImgPathUtil {

    public static String getPath(String name) {
        String property = System.getProperty("user.dir");
//        return property + java.io.File.separator + "src" + java.io.File.separator + "main" + java.io.File.separator + "resources"+ java.io.File.separator + "lolimg" + java.io.File.separator + name;
        if ("1920".equals(GlobalData.resolution)) {
            return property + java.io.File.separator + "lolimg" + java.io.File.separator + name;
        }
        if ("1280".equals(GlobalData.resolution)) {
            return property + java.io.File.separator + "lol_img_1280x720" + java.io.File.separator + name;
        }

        return property + java.io.File.separator + "lolimg" + java.io.File.separator + name;

    }
}
