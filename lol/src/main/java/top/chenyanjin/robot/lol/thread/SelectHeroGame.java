package top.chenyanjin.robot.lol.thread;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.ClickByImgUtil;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.ImageUtil;
import top.chenyanjin.robot.lol.util.RobotUtil;

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
            while (GlobalData.mode.get() == 2){

                // 选英雄中
                if (ImageUtil.findOneThenReturn(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\编辑符文.bmp",
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\闪现.bmp",
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选择你的英雄1.bmp",
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\禁用不可用.bmp",
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\首选.bmp"
                ))) {
                    log.info("开始选英雄");
                } else {
                    DelayUtil.delay(3000L);
                }


                boolean b = ImageUtil.find("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\禁用英雄.bmp");
                if (b) {
                    log.info("开始禁用英雄");
                    //TODO 禁用英雄
                    //TODO 确认禁用
                }


                boolean hero = ImageUtil.findOneThenReturn(Lists.newArrayList(
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选择你的英雄.bmp",
                        "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\锁定英雄.bmp"

                ));
                if (hero) {
                    log.info("该选英雄了");
                    // 寒冰射手、风暴之怒、时光守护者、麦林炮手、赏金猎人、符文法师、暗黑子女
                    //TODO 选用英雄
                    // 输入搜索名称 然后搜索英雄名称 看是否可以找到，如果找不到 则用下一个英雄    如果3次都不用 随机选择
                    boolean search = ClickByImgUtil.clickOne("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\搜索英雄.bmp", "搜索英雄按钮");
                    if (!search) {
                        return;
                    }
                    List<String> heroNameList = Lists.newArrayList("寒冰射手", "风暴之怒", "时光守护者", "麦林炮手", "赏金猎人", "符文法师", "暗黑子女");
                    boolean find = false;
                    for (String heroName : heroNameList) {
                        RobotUtil.sendTextToInput(heroName);
                        find = ClickByImgUtil.clickOne(MessageFormat.format("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\{0}.bmp", heroName), heroName);
                        if(find){
                            break;
                        }
                    }
                    if (!find) {
                        return;
                    }

                    //TODO 确认选用
                    ClickByImgUtil.findOneAndClickOne("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\锁定英雄1.bmp", "锁定英雄");


                }
                DelayUtil.delay(5000L);
            }
            DelayUtil.delay(3000L);
        }

    }
}
