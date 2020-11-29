package top.chenyanjin.robot.lol.thread;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginGame extends Thread {


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
            log.info("开始登陆");
            System.out.println(1);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
