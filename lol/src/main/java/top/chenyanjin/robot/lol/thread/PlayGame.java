package top.chenyanjin.robot.lol.thread;


import com.sun.jna.platform.win32.WinUser;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;
import top.chenyanjin.robot.lol.util.WinUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

@Slf4j
public class PlayGame extends Thread {

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
     */
    public PlayGame() {
        super("PlayGame");
    }

    int gameRule = 0;
    int h = 0;
    int w = 0;
    int buyTimes = 0;
    long gameStart = 0;

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
                GlobalData.matchingDelay.set(30000);
                if (gameStart == 0) {
                    gameStart = System.currentTimeMillis();
                }
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
                    boolean checkBlue = DmPicUtil.check("红色水晶.bmp");
                    boolean checkRed = DmPicUtil.check("蓝色方基地.bmp");
//                        Robot robot = new Robot();
//                        //TODO 这里是写死的坐标  需要改
//                        Color pixelColor = robot.getPixelColor(1900, 823);
//                        Color hongse = robot.getPixelColor(1664, 1059);
//
//                        if (hongse.equals(new Color(23, 38, 52))) {
                    if (checkRed) {
                        gameRule = 2;
                        log.info("红色方");
                    }
//                        if (pixelColor.equals(new Color(45, 33, 38))) {
                    if (checkBlue) {
                        gameRule = 1;
                        log.info("蓝色方");
                    }
                    RobotUtil.delay(1000);
                }
                // 两分钟内 在家里买东西
                if (getMin() <= 2 && buyTimes == 0) {
                    shop();
                }
                RobotUtil.delay(1000);
                RobotUtil.clickKey(KeyEvent.VK_SPACE);
                // 随机学技能
                upgradeSkill();
                // 随机用技能
                skill();
                // 跟随F2
                RobotUtil.clickKey(KeyEvent.VK_F2);
                // 蓝色方 961,771 922,771 888,771
                // 蓝色方 1006,727 1003,682 1008,654
                // A攻击
                attack();
                RobotUtil.clickKey(KeyEvent.VK_D);
                RobotUtil.clickKey(KeyEvent.VK_F);
                RobotUtil.delay(3000);
                long time = getMin();
                if (time == 5) {
                    shop();
                }
                if (time == 7) {
                    shop();
                }
                if (time == 9) {
                    shop();
                }
                if (time == 11) {
                    shop();
                }
                if (time >= 15) {
                    // 发起投降
                    RobotUtil.clickKey(KeyEvent.VK_ENTER);
                    RobotUtil.clickKey(KeyEvent.VK_SLASH);
                    RobotUtil.clickKey(KeyEvent.VK_F);
                    RobotUtil.clickKey(KeyEvent.VK_F);
                    RobotUtil.clickKey(KeyEvent.VK_ENTER);
                }

            }
            gameStart = 0;
            DelayUtil.delay(6000L);
        }
    }

    private long getMin() {
        long min = (System.currentTimeMillis() - gameStart) / (1000 * 60);
        log.info("当前 {} 分钟", min);

        return min;
    }

    private void shop() {
        RobotUtil.clickKey(KeyEvent.VK_B);
        RobotUtil.delay(10000);
        RobotUtil.clickKey(KeyEvent.VK_P);
        RobotUtil.delay(1000);
        RobotUtil.clickRelative(375, 144);
        RobotUtil.delay(1000);
        RobotUtil.clickRelative(170, 215);
        // untilFind("游戏内商品所有物品.bmp", "游戏内商品所有物品1.bmp", "商店all.bmp");

        Point p = DmPicUtil.findPosition("图标3.bmp", "图标1.bmp");
        if (p != null) {
            RobotUtil.click(p.x, p.y);
        }

        untilFind("商店生命值图标.bmp", "商店生命值图标1.bmp");

        long time = getMin();
        log.info("当前时间：{}, buyTimes:{}", time, buyTimes);
        if (time <= 5 && buyTimes == 0) {
            Point position = DmPicUtil.findPosition("钢铁护肩.bmp", "窃法之刃.bmp", "幽魂镰刀.bmp", "杀人戒.bmp");
            if (position != null) {
                RobotUtil.doubleClick(position.x, position.y);
                buyTimes++;
            } else {

            }
        }

        if (time <= 10 && buyTimes <= 2) {
            Point position = DmPicUtil.findPosition("400生命值.bmp");
            if (position != null) {
                RobotUtil.doubleClick(position.x, position.y);
                buyTimes++;
            }

        }

        if (time <= 15 && buyTimes < 5) {
            Point position = DmPicUtil.findPosition("900生命值.bmp");
            if (position != null) {
                RobotUtil.doubleClick(position.x, position.y);
                RobotUtil.doubleClick(position.x, position.y);
                buyTimes++;
            }

        }
        RobotUtil.delay(1000);
        RobotUtil.clickKey(KeyEvent.VK_P);
    }

    private void untilFind(String... name) {
        Point all1 = null;
        int i = 0;
        while (all1 == null) {
            i++;
            all1 = DmPicUtil.findPosition(name);
            RobotUtil.delay(1000);
            if (all1 != null) {
                RobotUtil.click(all1.x, all1.y, false);
            }
            if (i == 3) {
                break;
            }

        }
    }

    private void attack() {
        WinUser.WINDOWPLACEMENT windowplacement = WinUtil.getWindowPlacement(GlobalData.hwnd);
        Rectangle rectangle = windowplacement.rcNormalPosition.toRectangle();
        WinUtil.active(GlobalData.hwnd);
        if (rectangle.height <= 0) {
            rectangle.height = 1080;
            rectangle.width = 1920;
        }
        RobotUtil.clickKey(KeyEvent.VK_A);
        RobotUtil.click((rectangle.width / 2 - (gameRule == 1 ? -100 : 100)) + rectangle.x, (rectangle.height / 2 - 80) + rectangle.y, false);
    }

    private void upgradeSkill() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        RobotUtil.delay(200);
        skill();
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    private void skill() {
        Random random = new Random();
        int i = random.nextInt(4);
        switch (i) {
            case 0:
                RobotUtil.clickKey(KeyEvent.VK_Q);
                break;
            case 1:
                RobotUtil.clickKey(KeyEvent.VK_W);
                break;

            case 2:
                RobotUtil.clickKey(KeyEvent.VK_E);
                break;

            case 3:
                RobotUtil.clickKey(KeyEvent.VK_R);
                break;
            default:
                break;

        }
    }
}
