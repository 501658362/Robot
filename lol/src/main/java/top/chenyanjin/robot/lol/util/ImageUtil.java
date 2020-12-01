package top.chenyanjin.robot.lol.util;

import com.google.common.collect.Lists;
import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.thread.GlobalData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

@Slf4j
public class ImageUtil {

    public static List<Point> findPoint(String target) {
        return findPoint(Lists.newArrayList(target));
    }
    public static List<Point> findPoint(List<String> target) {
        RobotUtil.delay(100);
        long start = System.currentTimeMillis();
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot("currentClient.png"), target);
        log.debug("找图耗时{}ms", System.currentTimeMillis() - start);
        return findPicUtil.result;
    }
    public static boolean find(String target) {
        return find(Lists.newArrayList(target));
    }
    public static boolean find(List<String> target) {
        RobotUtil.delay(100);
        long start = System.currentTimeMillis();
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot("currentClient.png"), target);
        log.debug("找图耗时{}ms", System.currentTimeMillis() - start);
        return findPicUtil.result.size() > 0;
    }

    public static BufferedImage createGameSnapShot(String fileName) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        WinUtil.active(GlobalData.hwnd);
        BufferedImage screenCapture = RobotUtil.createScreenCapture(rectangle);
        //        try {
        //            ImageIO.write(screenCapture, "png", new File(fileName));
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        return screenCapture;
    }
}
