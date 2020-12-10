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

    public static synchronized BufferedImage createScreenCapture(Rectangle rectangle) {
        return robot.createScreenCapture(rectangle);
    }

    public static synchronized void delay(int ms) {
        robot.delay(ms + random.nextInt(100) + 100);
    }

    public static synchronized void click(Point point) {
        click(point, true);
    }

    public static synchronized void click(Point point, boolean reset) {
        // 这是相对位置
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        WinUtil.active(GlobalData.hwnd);
//        x = point.x + rectangle.x;
//        y = point.y + rectangle.y;
        x = point.x;
        y = point.y;
        robot.mouseMove(x, y);
        clickWithRandom(reset);
    }

    public static synchronized void click(int x, int y) {
        // 正确位置 不需要加相对位置
        robot.mouseMove(x, y);
        clickWithRandom();
    }

    public static synchronized void click(int x, int y, boolean reset) {
        // 正确位置 不需要加相对位置
        robot.mouseMove(x, y);
        clickWithRandom(false);
    }

    public static synchronized void clickRelative(int x, int y) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        robot.mouseMove(x + rectangle.x + random.nextInt(5), y + rectangle.y + random.nextInt(5));
        clickWithRandom();

    }

    public static synchronized void doubleClickRelative(int x, int y) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        doubleClick(x + rectangle.x + random.nextInt(5), y + rectangle.y + random.nextInt(5));
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

    public static synchronized void doubleClick() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 50);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 50);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }

    public static synchronized void threeClick() {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 10);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 10);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(50) + 10);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
    }

    public static synchronized void doubleClick(int x, int y) {
        robot.mouseMove(x, y);
        doubleClick();
    }


    public static synchronized void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static synchronized void mouseMoveR(int x, int y) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        robot.mouseMove(x + rectangle.x + random.nextInt(5), y + rectangle.y + random.nextInt(5));
    }

    public static synchronized void clickKey(int keyEvent) {
        robot.keyPress(keyEvent);
        delay(200);
        robot.keyRelease(keyEvent);
    }

    public static synchronized void clickRightR(int x, int y) {
        mouseMoveR(x, y);
        delay(200);
        robot.mousePress(KeyEvent.BUTTON3_MASK);
        robot.delay(random.nextInt(10) + 10);
        robot.mouseRelease(KeyEvent.BUTTON3_MASK);
    }

    private static void clickWithRandom() {
        clickWithRandom(true);
    }

    private static synchronized void clickWithRandom(boolean reset) {

        robot.mousePress(KeyEvent.BUTTON1_MASK);
//        robot.delay(random.nextInt(100) + 50);
        robot.delay(random.nextInt(10) + 10);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        robot.delay(random.nextInt(10) + 10);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        if (reset) {
            //robot.mouseMove(100 + random.nextInt(50), 100 + random.nextInt(50));
        }

    }


}
