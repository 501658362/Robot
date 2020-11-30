package top.chenyanjin.robot.lol.util;

public class DelayUtil {

    public static void delay(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
