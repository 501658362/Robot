package top.chenyanjin.robot.lol.util;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.constant.WindowNameEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnapshotUtil {

    public static void main(String[] args) {
        WinDef.HWND hwnd = WinUtil.findWindow(WindowNameEnum.CLIENT);

        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        System.out.println(rectangle);
        WinUtil.active(hwnd);

        try {
            Robot robot = new Robot();
            robot.delay(1000);
            BufferedImage screenCapture = robot.createScreenCapture(rectangle);
            ImageIO.write(screenCapture, "png", new File("currentClient.png"));
            int x = (int) (161 + rectangle.getX());
            int y = (int) (396 + rectangle.getY());
            Rectangle loginB = new Rectangle(x, y, 14, 14);
            //ImageIO.write(robot.createScreenCapture(loginB), "png", new File("inviteBtn.png"));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
