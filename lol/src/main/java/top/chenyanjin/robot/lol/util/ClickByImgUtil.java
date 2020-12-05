package top.chenyanjin.robot.lol.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;

@Slf4j
public class ClickByImgUtil {

    public static boolean clickOne(String path) {
        return clickOne(Lists.newArrayList(path));
    }

    public static boolean clickOne(String path, String error) {
        return clickOne(Lists.newArrayList(path));
    }

    public static boolean clickOne(List<String> pathList) {
        List<Point> searchBtnList = ImageUtil.findPoint(pathList);
        if (searchBtnList != null && searchBtnList.size() > 0) {
            RobotUtil.delay(1000);
            RobotUtil.click(searchBtnList.get(0));
            RobotUtil.delay(1000);
            return true;
        } else {
            return false;
        }
    }

    public static boolean clickOne(List<String> pathList, String error) {
        boolean b = clickOne(pathList);
        if (!b) {
            ErrorUtil.error(error);
        }
        return b;
    }


    public static boolean findOneAndClickOne(String path) {
        return findOneAndClickOne(Lists.newArrayList(path));
    }

    public static boolean findOneAndClickOne(String path, String error) {
        return findOneAndClickOne(Lists.newArrayList(path));
    }
    public static boolean findOneAndClickOne(List<String> pathList) {
        Point onePointAndReturn = ImageUtil.findOnePointAndReturn(pathList);
        if (onePointAndReturn != null) {
            RobotUtil.delay(1000);
            RobotUtil.click(onePointAndReturn);
            RobotUtil.delay(1000);
            return true;
        } else {
            return false;
        }
    }
    public static boolean findOneAndClickOne(List<String> pathList, String error) {
        boolean b = findOneAndClickOne(pathList);
        if (!b) {
            ErrorUtil.error(error);
        }
        return b;
    }

}
