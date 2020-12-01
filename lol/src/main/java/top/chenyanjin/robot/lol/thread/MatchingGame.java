package top.chenyanjin.robot.lol.thread;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.Main;
import top.chenyanjin.robot.lol.util.ClickByImgUtil;
import top.chenyanjin.robot.lol.util.DelayUtil;

@Slf4j
public class MatchingGame extends Thread {


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

        while (true){
            // Main.clientService.interrupt();
            log.info("寻找对战");
            boolean b = ClickByImgUtil.clickOne(Lists.newArrayList("C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\接受对局.bmp",
                    "C:\\Users\\CHEN\\Desktop\\AutoHotKey\\dm\\lol\\接受1.bmp"
                    ));
            if(b){
                log.info("找到对局");
                DelayUtil.delay(10000L);
                try {
                    Main.clientService.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main.selectHeroGame.start();

            }
            DelayUtil.delay(1000L);
        }

    }
}
