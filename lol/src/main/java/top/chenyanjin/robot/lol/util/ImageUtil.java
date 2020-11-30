package top.chenyanjin.robot.lol.util;

import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.thread.GlobalData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageUtil {

    public static List<Point> find(List<String> target) {

        RobotUtil.delay(1000);
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot("currentClient.png"), target);
        return findPicUtil.result;
    }

    public static BufferedImage createGameSnapShot(String fileName) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        WinUtil.active(GlobalData.hwnd);
        BufferedImage screenCapture = RobotUtil.createScreenCapture(rectangle);
        try {
            ImageIO.write(screenCapture, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenCapture;
    }
}
