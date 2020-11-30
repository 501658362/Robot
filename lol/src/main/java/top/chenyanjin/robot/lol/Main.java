package top.chenyanjin.robot.lol;

import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.service.ClientService;
import top.chenyanjin.robot.lol.thread.GlobalData;
import top.chenyanjin.robot.lol.thread.LoginGame;
import top.chenyanjin.robot.lol.thread.PlayGame;
import top.chenyanjin.robot.lol.thread.WindowDetection;
import top.chenyanjin.robot.lol.util.DelayUtil;

/**
 * @author CHEN
 */
@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("开始执行");

        Thread wd = new WindowDetection();
        wd.start();

        ClientService clientService = new ClientService();
        while (true){
            switch (GlobalData.mode.get()) {
                case 1:
                    // 对局中
                    break;
                case 2:
                    // 客户端
                    clientService.run();
                    break;
                case 3:
                    // 登录器
                    break;
                default:
                    break;
            }
            DelayUtil.delay(5000L);
        }


    }
}
