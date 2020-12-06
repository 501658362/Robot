package top.chenyanjin.robot.lol.thread;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.Main;
import top.chenyanjin.robot.lol.util.DelayUtil;

import java.awt.*;
import java.util.List;

@Slf4j
public class CheckSelectHero extends Thread {


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

//        while (true) {
//            log.info("检测是否为选择英雄页面");
//            List<Point> points = ImageUtil.findPoint(Lists.newArrayList(
//                    "禁用英雄.bmp",
//                    "选择你的英雄.bmp"
//                    )
//            );
//            if(points.size() == 0){
//                log.info("当前非选择英雄页面, 开始 clientService 线程");
//                Main.clientService.start();
//                return;
//            }
//
//
//            DelayUtil.delay(1000L);
//        }

    }
}
