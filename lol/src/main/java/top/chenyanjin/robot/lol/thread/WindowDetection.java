package top.chenyanjin.robot.lol.thread;


import com.sun.jna.platform.win32.WinDef;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.enums.WindowNameEnum;
import top.chenyanjin.robot.lol.util.WinUtil;

@Slf4j
public class WindowDetection extends Thread {


    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public WindowDetection() {
        super.setName("WindowDetection");
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
        log.info("开始检测窗口");
        while (true) {
            boolean flag = false;
            int i = GlobalData.mode.get();
            for (WindowNameEnum value : WindowNameEnum.values()) {
                WinDef.HWND game = WinUtil.findWindow(value);
                if (game != null && i != value.getMode()) {
                    log.info("检测到{}", value.getTitleName());
                    GlobalData.mode.set(value.getMode());
                    GlobalData.hwnd = game;
                    flag = true;
                    WinUtil.active(GlobalData.hwnd);
                    delay();
                    break;
                }
            }
            if (!flag) {
                delay();
            }

        }

    }


    private void delay() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
