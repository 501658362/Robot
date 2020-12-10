package top.chenyanjin.robot.lol.thread;


import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;

import java.awt.*;

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
            while (GlobalData.mode.get() == 2) {
                log.info("寻找对战");
                boolean b = DmPicUtil.check("对局已找到.bmp", "接受1.bmp");
                if (b) {
                    if ("1280".equals(GlobalData.resolution)) {
                        RobotUtil.clickRelative(650, 560);
                    } else {
                        RobotUtil.clickRelative(819, 699);
                    }
                    // 819 699
                    log.info("找到对局");
                    DelayUtil.delay(10000L);
                }
                DelayUtil.delay(GlobalData.matchingDelay.get());
            }
            DelayUtil.delay(GlobalData.matchingDelay.get());
        }
    }
}
