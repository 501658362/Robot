package top.chenyanjin.robot.lol;

import com.sun.jna.platform.win32.User32;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotTest {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        int x = 0;
        int y = 0;
        while (true) {
//            User32.INSTANCE.m
            robot.mouseMove(100 + x++, 200 + y++);
//            robot.mousePress(KeyEvent.BUTTON1_MASK);
            robot.delay(3000);
//            robot.mouseRelease(KeyEvent.BUTTON1_MASK);
        }

    }
}
