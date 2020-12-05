package top.chenyanjin.robot.lol.thread;


import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;
import top.chenyanjin.robot.lol.util.WinUtil;

import java.awt.*;

@Slf4j
public class PlayGame extends Thread {

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, name)}.
     */
    public PlayGame() {
        super("PlayGame");
    }

    int gameRule = 0;
    int h = 0;
    int w = 0;

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
            while (GlobalData.mode.get() == 1) {
                log.info("战斗中 ：{}", gameRule);

                WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
                Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
                w = rectangle.width;
                h = rectangle.height;
                log.info(rectangle.toString());
                long start = System.currentTimeMillis();
                while (gameRule == 0) {
                    if ((System.currentTimeMillis() - start) > 60 * 1000 * 3) {
                        // 3分钟超时 设置为默认蓝色方
                        gameRule = 1;
                    }
                    try {
                        Robot robot = new Robot();
                        //TODO 这里是写死的坐标  需要改
                        Color pixelColor = robot.getPixelColor(1900, 823);
                        Color hongse = robot.getPixelColor(1664, 1059);

                        if (hongse.equals(new Color(23, 38, 52))) {
                            gameRule = 2;
                            log.info("红色方");
                        }
                        if (pixelColor.equals(new Color(45, 33, 38))) {
                            gameRule = 1;
                            log.info("蓝色方");
                        }
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    RobotUtil.delay(1000);
                }
                RobotUtil.delay(1000);
                // 随机学技能
                // 随机用技能
                // 跟随F2
                // A攻击


            }

            DelayUtil.delay(6000L);
        }
    }
}
