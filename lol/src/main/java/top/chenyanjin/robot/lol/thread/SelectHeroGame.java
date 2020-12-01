package top.chenyanjin.robot.lol.thread;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.ClickByImgUtil;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.ImageUtil;

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

            // 选英雄中
            if (ImageUtil.find(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\编辑符文.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\闪现.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选择你的英雄1.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\禁用不可用.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\首选.bmp"
            ))) {
                log.info("开始选英雄");
            } else {
                return;
            }


            boolean b = ImageUtil.find("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\禁用英雄.bmp");
            if (b) {
                log.info("开始禁用英雄");
                //TODO 禁用英雄
                //TODO 确认禁用
            }


            boolean hero = ImageUtil.find("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\选择你的英雄.bmp");
            if (hero) {
                log.info("该选英雄了");
                //TODO 选用英雄
                //TODO 确认选用
            }

            DelayUtil.delay(1000L);
        }

    }
}
