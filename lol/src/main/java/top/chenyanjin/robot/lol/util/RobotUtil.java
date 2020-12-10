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
        click(point, true);
    }

    public static void click(Point point, boolean reset) {
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

    public static void click(int x, int y) {
        // 正确位置 不需要加相对位置
        robot.mouseMove(x, y);
        clickWithRandom();
    }

    public static void click(int x, int y, boolean reset) {
        // 正确位置 不需要加相对位置
        robot.mouseMove(x, y);
        clickWithRandom(false);
    }

    public static void clickRelative(int x, int y) {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        robot.mouseMove(x + rectangle.x + random.nextInt(5), y + rectangle.y + random.nextInt(5));
        robot.mousePress(KeyEvent.BUTTON1_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);

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

    public static void threeClick() {
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

    public static void doubleClick(int x, int y) {
        robot.mouseMove(x, y);
        doubleClick();
    }


    public static void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static void clickKey(int keyEvent) {
        robot.keyPress(keyEvent);
        delay(200);
        robot.keyRelease(keyEvent);
    }

    private static void clickWithRandom() {
        clickWithRandom(true);
    }

    private static void clickWithRandom(boolean reset) {
        robot.mousePress(KeyEvent.BUTTON1_MASK);
//        robot.delay(random.nextInt(100) + 50);
        robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        if (reset) {
            //robot.mouseMove(100 + random.nextInt(50), 100 + random.nextInt(50));
        }

    }


}
