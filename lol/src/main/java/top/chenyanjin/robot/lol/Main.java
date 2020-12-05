package top.chenyanjin.robot.lol;

import lombok.extern.slf4j.Slf4j;
import top.chenyanjin.robot.lol.service.ClientService;
import top.chenyanjin.robot.lol.thread.*;
import top.chenyanjin.robot.lol.util.DelayUtil;

/**
 * @author CHEN
 */
@Slf4j
public class Main {
    public static ClientService clientService = new ClientService("clientService");
    public static MatchingGame matchingGame = new MatchingGame("matchingGame");
    public static SelectHeroGame selectHeroGame = new SelectHeroGame("selectHeroGame");
    public static PlayGame playGame = new PlayGame();

    public static void main(String[] args) {

        log.info("开始执行");

        Thread wd = new WindowDetection();
        wd.start();
        selectHeroGame.start();
        playGame.start();
        clientService.start();
//        int i = -1;
//        while (true) {
//            switch (GlobalData.mode.get()) {
//                case 1:
//                    // 对局中
//                    break;
//                case 2:
//                    // 客户端
//                    if (!selectHeroGame.isAlive() && !clientService.isAlive()) {
//                        clientService.start();
//                    }
//                    break;
//                case 3:
//                    // 登录器
//                    break;
//                default:
//                    break;
//            }
//            DelayUtil.delay(5000L);
//        }


    }
}
