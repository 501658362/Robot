package top.chenyanjin.robot.lol.util;

import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.thread.GlobalData;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class DmPicUtil {

    private static DmSoft dm;
    private static int x, y, x1, y1;

    static {
        dm = new DmSoft();
        log.info("version:" + dm.Ver());
        log.info("path:" + dm.GetBasePath());
    }

    public static boolean click(String... name) {
        return click(false, name);
    }
    public static boolean clickNotReset(String... name) {
        return click(false, name);
    }

    public static boolean click(boolean saveError, String... name) {
        return click(true, saveError, name);
    }
    public static boolean click(boolean reset, boolean saveError, String... name) {
        Point position = findPosition(saveError, name);
        if (position != null) {
            RobotUtil.delay(1000);
            RobotUtil.click(position, reset);

            RobotUtil.delay(1000);
            return true;
        }
        return false;
    }

    public static boolean check(String... name) {
        return check(false, name);
    }

    public static boolean check(boolean saveError, String... name) {
        return findPosition(saveError, name) != null;
    }


    public static Point findPosition(boolean saveError, String... name) {
        String s = getPointString(true, name);
        String[] split = s.split("\\|");
        if ("-1".equals(split[0])) {
            if (saveError) {
                ErrorUtil.error(name[0]);
            }
            return null;
        }
        return new Point(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    public static Point findPosition(String... name) {
        return findPosition(false, name);
    }

    public static List<Point> findPositions(boolean saveError, String... name) {
        String s = getPointString(false, name);
        if (s == null || s.isEmpty()) {
            return null;
        }
        String[] split = s.split("\\|");
        if (split.length == 0) {
            //TODO 判断找不到
        }
        return Arrays.stream(split).map(x -> {
            String[] split1 = x.split(",");
            return new Point(Integer.parseInt(split1[1]), Integer.parseInt(split1[2]));
        }).collect(Collectors.toList());
    }

    public static List<Point> findPositions(String... name) {
        return findPositions(false, name);
    }

    private static String getPointString(boolean findOne, String... name) {
        setPosition();
        String collect = Arrays.stream(name).map(ImgPathUtil::getPath).collect(Collectors.joining("|"));
        long start = System.currentTimeMillis();
        String s = null;
        if (findOne) {
            s = dm.FindPicE(x, y, x1 == 0 ? 1920 : x1, y1 == 0 ? 1080 : y1, collect, "000000", 0.9, 0);
        } else {
            s = dm.FindPicEx(x, y, x1 == 0 ? 1920 : x1, y1 == 0 ? 1080 : y1, collect, "000000", 0.9, 0);
        }
        long end = System.currentTimeMillis();
        if((end - start) > 1000){
            log.debug("dm找图耗时{}ms, {}", System.currentTimeMillis() - start, name);
        }
        return s;
    }


    private static void setPosition() {
        try {
            if (GlobalData.hwnd.getPointer() != null) {
                WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
                Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
                x = rectangle.x;
                y = rectangle.y;
                x1 = rectangle.x + rectangle.width;
                y1 = rectangle.y + rectangle.height;
            } else {
                x = 0;
                y = 0;
                x1 = 1920;
                y1 = 1080;
            }

        } catch (Exception e) {
            log.error("获取游戏分辨率失败");
            x = 0;
            y = 0;
            x1 = 1920;
            y1 = 1080;
        }
    }

}
