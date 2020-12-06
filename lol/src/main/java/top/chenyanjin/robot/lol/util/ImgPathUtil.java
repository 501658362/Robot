package top.chenyanjin.robot.lol.util;

public class ImgPathUtil {

    public static String getPath(String name) {
        String property = System.getProperty("user.dir");
//        return property + java.io.File.separator + "src" + java.io.File.separator + "main" + java.io.File.separator + "resources"+ java.io.File.separator + "lolimg" + java.io.File.separator + name;
        return property + java.io.File.separator + "lolimg" + java.io.File.separator + name;

    }
}
