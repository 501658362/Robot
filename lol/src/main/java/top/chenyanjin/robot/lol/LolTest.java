package top.chenyanjin.robot.lol;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.enums.WindowNameEnum;
import top.chenyanjin.robot.lol.util.WinUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LolTest {


    public static void main(String[] args) {

        //








        WinDef.HWND hwnd = WinUtil.findWindow(WindowNameEnum.LOGIN);

        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        System.out.println(rectangle);
        WinUtil.active(hwnd);

        try {
            Robot robot = new Robot();
            robot.delay(1000);
            BufferedImage screenCapture = robot.createScreenCapture(rectangle);
            int x = (int) (1097 + rectangle.getX());
            int y = (int) (308 + rectangle.getY());
            int x1 = 1197;
            int y2 = 334;
            Rectangle loginB = new Rectangle(x, y, 100, 30);
            //            1097, 308
            // 	1197, 334
            File path = new File("lol\\target");
            File file = new File(path, 2 + "." + "png");
            //ImageIO.write(screenCapture, "png", file);
            ImageIO.write(robot.createScreenCapture(loginB), "png", new File("loginBtn3.png"));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
