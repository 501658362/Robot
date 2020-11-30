package top.chenyanjin.robot.lol.util;

import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.thread.GlobalData;

@Slf4j
public class ErrorUtil {

    public static void error(String info) {
        log.error("开启{}，当前为{}模式，{}", GlobalData.clientTeamMode.getName(), GlobalData.clientRuleMode.getName(), info);
        log.error("创建截图");
        String name = "error/" + info + System.currentTimeMillis() + ".png";
        ImageUtil.createGameSnapShot(name);
    }
}
