package top.chenyanjin.robot.lol.util;

import com.sun.jna.platform.win32.WinUser;
import top.chenyanjin.robot.lol.thread.GlobalData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RobotUtil {
    static Robot robot;
    static Random random = new Random();

    public static int x = 0;
    public static int y = 0;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage createScreenCapture(Rectangle rectangle) {
        return robot.createScreenCapture(rectangle);
    }

    public static void delay(int ms) {
        robot.delay(ms + random.nextInt(100) + 100);
    }

    public static void click(Point point) {
        // 这是相对位置
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        WinUtil.active(GlobalData.hwnd);
        x = point.x + rectangle.x;
        y = point.y + rectangle.y;
        robot.mouseMove(x, y);
        clickWithRandom();
    }

    public static void click(int x, int y) {
        // 正确位置 不需要加相对位置
        robot.mouseMove(x, y);
        clickWithRandom();
    }

    public static void sendTextToInput(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
        //ctrl+V  粘贴
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100 + random.nextInt(100) + 5);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(100 + random.nextInt(100) + 5);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300 + random.nextInt(100) + 5);
    }

    public static void doubleClick() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 50);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 50);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }

    private static void clickWithRandom() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(200) + 300);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }
}
