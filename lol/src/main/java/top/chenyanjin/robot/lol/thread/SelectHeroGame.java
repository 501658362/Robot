package top.chenyanjin.robot.lol.thread;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
public class SelectHeroGame extends Thread {


    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public SelectHeroGame(String name) {
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

                // 选英雄中
                if (DmPicUtil.check(
                        "闪现.bmp"
                )) {
                    log.info("开始选英雄");
                    GlobalData.matchingDelay.set(5000);
                } else {
                    DelayUtil.delay(3000L);
                }


                boolean b = DmPicUtil.check("禁用英雄.bmp");
                if (b) {
                    log.info("开始禁用英雄");
                    //TODO 禁用英雄
                    boolean bb = DmPicUtil.click(true, "禁用不指定英雄1.bmp", "禁用不指定英雄.bmp");
                    if (bb) {
                        DmPicUtil.click(true, "禁用英雄按钮.bmp");

                    }
                }


                boolean hero = DmPicUtil.check(
                        "选择你的英雄.bmp",
                        "锁定英雄.bmp"

                );
                if (hero) {
                    log.info("该选英雄了");
                    // 寒冰射手、风暴之怒、时光守护者、麦林炮手、赏金猎人、符文法师、暗黑子女
                    // 选用英雄
                    // 输入搜索名称 然后搜索英雄名称 看是否可以找到，如果找不到 则用下一个英雄    如果3次都不用 随机选择
                    boolean search = DmPicUtil.click(true, "搜索英雄1.bmp", "搜索英雄.bmp");
                    if (!search) {
                        return;
                    }
                    List<String> defaultList = Lists.newArrayList("众星之子", "战争女神", "寒冰射手", "风暴之怒", "时光守护者", "雪原双子", "麦林炮手", "赏金猎人", "符文法师", "天启者", "黑暗之女", "无极剑圣");
                    GlobalData.heroNameList.addAll(defaultList);
                    boolean find = false;
                    for (String heroName : GlobalData.heroNameList) {
                        RobotUtil.clickKey(KeyEvent.VK_BACK_SPACE);
                        RobotUtil.clickKey(KeyEvent.VK_BACK_SPACE);
                        RobotUtil.clickKey(KeyEvent.VK_BACK_SPACE);
                        RobotUtil.clickKey(KeyEvent.VK_BACK_SPACE);
                        RobotUtil.sendTextToInput(heroName);
                        find = DmPicUtil.click(true, MessageFormat.format("{0}.bmp", heroName));
                        if (find) {
                            break;
                        }
                    }
                    if (!find) {
                        log.error("选不到英雄");
                        return;
                    }

                    // 确认选用
                    DmPicUtil.click(true, "锁定英雄2.bmp","锁定英雄1.bmp");


                }
                DelayUtil.delay(5000L);
            }
            DelayUtil.delay(3000L);
        }

    }
}
