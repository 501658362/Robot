package top.chenyanjin.robot.lol.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: chenchaopeng
 * Date: 2020/11/29
 */
public class GlobalData {
    public static ConcurrentHashMap<String, String> data =new ConcurrentHashMap<>();
    public static AtomicInteger mode = new AtomicInteger();

}
