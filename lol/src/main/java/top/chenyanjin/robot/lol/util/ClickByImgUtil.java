package top.chenyanjin.robot.lol.util;

import com.google.common.collect.Lists;

import java.awt.*;
import java.util.List;

public class ClickByImgUtil {

    public static boolean clickOne(List<String> pathList){
        List<Point> searchBtnList = ImageUtil.find(pathList);
        if (searchBtnList != null && searchBtnList.size() > 0) {
            RobotUtil.delay(1000);
            RobotUtil.click(searchBtnList.get(0));
            RobotUtil.delay(1000);
            return true;
        } else {
            return false;
        }
    }
    public static boolean clickOne(List<String> pathList, String error){
        boolean b = clickOne(pathList);
        if(!b){
            ErrorUtil.error(error);
        }
        return b;
    }
}
