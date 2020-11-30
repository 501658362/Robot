package top.chenyanjin.robot.lol.thread;


import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;

@Slf4j
public class PageDetection extends Thread {

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
        log.info("检测客户端处于哪个页面");
        GlobalData.clientCurrentPage.set(2);
        DelayUtil.delay(3000L);
    }

}
