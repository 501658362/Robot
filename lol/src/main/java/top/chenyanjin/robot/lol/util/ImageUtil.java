package top.chenyanjin.robot.lol.util;

import com.google.common.collect.Lists;
import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.thread.GlobalData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ImageUtil {

    public static List<Point> findPoint(String target) {
        return findPoint(Lists.newArrayList(target));
    }

    public static List<Point> findPoint(List<String> target) {
        RobotUtil.delay(100);
        long start = System.currentTimeMillis();
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot(), target);
        log.debug("找图耗时{}ms", System.currentTimeMillis() - start);
        return findPicUtil.result;
    }

    public static Point findOnePointAndReturn(String target) {
        return findOnePointAndReturn(Lists.newArrayList(target));
    }

    public static Point findOnePointAndReturn(List<String> target) {
        RobotUtil.delay(100);
        long start = System.currentTimeMillis();
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot(), target, true);
        log.debug("找图耗时{}ms", System.currentTimeMillis() - start);
        return findPicUtil.oneResult;
    }

    public static boolean findOnePointAndReturn(List<String> target, String error) {
        boolean b = findOnePointAndReturn(target) != null;
        if (!b) {
            ErrorUtil.error(error);
        }
        return b;
    }
    public static boolean findOnePointAndReturn(String target, String error) {
        return findOnePointAndReturn(Lists.newArrayList(target), error);
    }


    public static boolean findOneThenReturn(String target) {
        return findOneThenReturn(Lists.newArrayList(target));
    }

    public static boolean findOneThenReturn(List<String> target) {
        return findOnePointAndReturn(target) != null;
    }



    public static boolean find(String target) {
        return find(Lists.newArrayList(target));
    }

    public static boolean find(List<String> target) {
        RobotUtil.delay(100);
        long start = System.currentTimeMillis();
        FindPicUtil findPicUtil = new FindPicUtil(createGameSnapShot(), target);
        log.debug("找图耗时{}ms", System.currentTimeMillis() - start);
        return findPicUtil.result.size() > 0;
    }

    public static BufferedImage createGameSnapShot() {
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

    public static BufferedImage createGameSnapShot(String fileName) {
        BufferedImage screenCapture = createGameSnapShot();
        try {
            ImageIO.write(screenCapture, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenCapture;
    }
}
