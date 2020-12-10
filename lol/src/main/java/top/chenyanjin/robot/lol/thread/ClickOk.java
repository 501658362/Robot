package top.chenyanjin.robot.lol.thread;


import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.util.DelayUtil;
import top.chenyanjin.robot.lol.util.DmPicUtil;

@Slf4j
public class ClickOk extends Thread {

    public ClickOk() {
        super("ClickOk");
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
                log.info("点击ok");
                DmPicUtil.click("ok1.bmp", "重新连接");
                DelayUtil.delay(8000);
            }
            DelayUtil.delay(8000);
        }
    }
}
