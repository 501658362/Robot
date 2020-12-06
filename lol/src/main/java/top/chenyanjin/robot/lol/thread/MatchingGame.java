package top.chenyanjin.robot.lol.thread;


import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;
import top.chenyanjin.robot.lol.util.WinUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
public class MatchingGame extends Thread {

    Robot robot;

    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public MatchingGame(String name) {
        super(name);
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     */
    @Override
    public void run() {
        super.run();

        while (true) {
            log.info("寻找对战");
            boolean b = DmPicUtil.check("对局已找到.bmp", "接受3.bmp");
            if (b) {
                WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
                Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
                robot.mouseMove(819 + rectangle.x, 699 + rectangle.y);
                robot.mousePress(KeyEvent.BUTTON1_MASK);
                robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                // 819 699
                log.info("找到对局");
                DelayUtil.delay(10000L);
            }
            DelayUtil.delay(GlobalData.matchingDelay.get());
        }
    }
}
