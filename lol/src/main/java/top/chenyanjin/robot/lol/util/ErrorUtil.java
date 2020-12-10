package top.chenyanjin.robot.lol.util;

import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.thread.GlobalData;

import java.io.File;

@Slf4j
public class ErrorUtil {

    public static void error(String info) {
        log.error("开启{}，当前为{}模式，{}", GlobalData.clientTeamMode.getName(), GlobalData.clientRuleMode.getName(), info);
        log.error("创建截图");
        String name = "error/" + info + System.currentTimeMillis() + ".png";
        File file = new File("error");
        if (!file.exists()) {
            file.mkdir();
        }
        ImageUtil.createGameSnapShot(name);
    }
}
